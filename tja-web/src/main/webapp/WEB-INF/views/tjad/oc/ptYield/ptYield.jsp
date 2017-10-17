<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>特批产值下达</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.permit.yield.menu.path" scope="request"  />
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/jsgrid.css?v=${buildVersion}" />
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/theme.css?v=${buildVersion}" />
    <link href="${site }/resources/css/management.css" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
   <div id="jsGrid"></div>
 </div>
 <script src="${site}/resources/js/jsgrid/jsgrid.js?v=${buildVersion}"></script>
 <script src="${site}/resources/js/jsgrid/db.js?v=${buildVersion}"></script>
	<script type="text/javascript">
		var awardsGrid = null;
		var baseDb = new BaseDb();
		$(function() {
			var periodSelect = getPeriodSelect();
			
			var major = getMajor();
			
			var Grid = jsGrid.Grid;
			permitGrid = new Grid("#jsGrid", {
					height : "auto",
					width : "100%",
					filtering : true,
					editing : true,
					inserting : true,
					sorting : false,
					paging : true,
					autoload : true,
					pageLoading : true,
					pagerRenderer : baseDb.pagerrenderer,
					noDataContent : "",
					deleteConfirm : "确认删除?",
					invalidMessage:"",
					controller : permitDeclareDb,
					fields : [ {
						name : "number",
						title : "序号",
						width : "5%"
					}, {
			        	name : "periodName",
			        	title : "期间<font class=required>※</font>",
			        	type : "text",
			        	width : "15%",
			        	inserting: false,
			            editing: false,
			            css: "jsgrid-form-control",
			            insertTemplate : function() {
				       	    return periodSelect;
			        	},
			        	editTemplate: function(value, item) {
			    			$(periodSelect).val(item.periodId);
				       	    return periodSelect;
			        	}
			        }, {
			        	name : "proCode",
			        	title : "项目编号<font class=required>※</font>",
			        	type : "text",
			        	width : "15%",
			        	inserting: false,
			            editing: false,
			            insertTemplate : function() {
			        		var _proselect = $("<div class='col-md-10'></div>");
			        		$(_proselect).append('<input type="text" name="proCode" class="form-control col-md-3" disabled="disabled" >')
			        					 .append('<input type="hidden" name="proId" >')
			        					 .append('<a id="selectPro" title="选择" href="javascript:void(0);" class="icon-select"></a>');

			        		return _proselect;
			        	},
			        	editTemplate: function(value, item) {
			        		var _proselect = $("<div class='col-md-10'></div>");
			        		$(_proselect).append('<input type="text" name="proCode" class="form-control col-md-3" disabled="disabled" value='+value+'>')
			        					 .append('<input type="hidden" name="proId" value='+item.proId+'>')
			        					 .append('<a id="selectPro" title="选择" href="javascript:void(0);" class="icon-select"></a>');

			        		return _proselect;
			        	}
			        }, {
			        	name : "proName",
			        	title : "项目名称",
			        	type : "text",
			        	width : "20%",
			        	inserting: false,
			            editing: false,
			            
			        	insertTemplate : function(){
			        		return "<input type='text' name='proName' class='form-control' disabled='disabled'>";
			        	},
			        	editTemplate : function(value, item) {
			        		return "<input type='text' name='proName' class='form-control' disabled='disabled' value="+value+">";
			        	}
			        },{
			        	name : "majorName",
			        	title : "专业<font class=required>※</font>",
			        	type : "text",
			        	css: "jsgrid-form-control",
			        	width : "15%",
			        	inserting: false,
			            editing: false,
			            insertTemplate : function() {
				       	    return major;
			        	},
			        	editTemplate: function(value, item) {
			    			$(major).val(item.majorCode);
				       	    return major;
			        	}
			        },{
						name : "permitYield",
						title : "特批产值",
						type : "number",
						width : "10%",
						css: "jsgrid-form-control",
						filtering:false
					},{
						name : "remark",
						title : "备注",
						type : "text",
						width : "10%",
						css: "jsgrid-form-control",
						filtering:true
					},{
						type : "control"
					}]
				});
			$(":text").addClass("form-control");
		});
		
		//首页、上一页、下一页、尾页事件
		jQuery(document).on("click", "div.jsgrid-pager li#first",function(){
			permitGrid.openPage(1);
	    });

		jQuery(document).on("click", "div.jsgrid-pager li#previous",function(){
			permitGrid.openPage(parseInt(permitGrid.pageIndex) - 1);
	    }); 

		jQuery(document).on("click", "div.jsgrid-pager li#next",function(){
			permitGrid.openPage(parseInt(permitGrid.pageIndex) + 1);
	    }); 

		jQuery(document).on("click", "div.jsgrid-pager li#last",function(){
			var itemsCount = permitGrid._loadStrategy._itemsCount;
			var pageSize = permitGrid.pageSize;
			var pagesCount = Math.floor(itemsCount / pageSize) + (itemsCount % pageSize ? 1 : 0);
			permitGrid.openPage(pagesCount);
	    });
		
		jQuery(document).on("click", "div.jsgrid-pager li#btnGo",function(){
			var pageIndex = jQuery(this).closest("ul.foot-page").find("input#goPage").val();
			permitGrid.openPage(pageIndex);
	    });
		
		jQuery(document).on("change", "div.jsgrid-pager select#rowsPerPage",function(){
			permitGrid.pageSize = jQuery(this).val();
			permitGrid.openPage(1);
	    });
	
	(function() {
		var permitDeclareDb = {
		  loadData: function(filter) {
			   var url =context+"/admin/oc/permitYield/ajax/search";
			  var data = baseDb.loadData(filter,url);
			  return data;
		  },
		  insertItem: function(insertingClient) {
			  var url =context+"/admin/oc/permitYield/ajax/asave";

			  var periodId = $(".jsgrid-insert-row select[name='periodId']").val();
		  	  var proId = $(".jsgrid-insert-row td:eq(2) input:eq(1)").val();
		  	  var majorCode = $(".jsgrid-insert-row select[name='majorCode']").val();
		  	  
		  		if(periodId.length == 0) {
		  			$.jalert({"jatext":"请选择期间"});
		  			return;
		  		}
		  		if(proId.length == 0) {
		  			$.jalert({"jatext":"请选择项目"});
		  			return;
		  		}
		  		if(majorCode.length == 0) {
		  			$.jalert({"jatext":"请选择专业"});
		  			return;
		  		}
		  		
		  		insertingClient.periodId = periodId;
		  		insertingClient.proId = proId;
		  		insertingClient.majorCode = majorCode;
			  jQuery.ajax({
					type : "POST",
					url : url,
					data : insertingClient,
					async : false,
					error : function(request) {
						$.jalert({"jatext":"Connection error"});
					},
					success : function(data) {
						if (data.flag == 'true') {
							var length = jQuery(
									"div.jsgrid-grid-body table.jsgrid-table tr").size();
							//改变编号，修改时间，修改人
							insertingClient.number = length + 1;
						}
					}
				});
		  },	
		  updateItem: function(updatingClient) {
			  var url =context+"/admin/oc/permitYield/ajax/asave";
			  var periodId = $(".jsgrid-edit-row select[name='periodId']").val();
			  var periodName = $(".jsgrid-edit-row select[name='periodId']").find("option:selected").text();
			  var majorCode = $(".jsgrid-edit-row select[name='majorCode']").val();
			  var majorName = $(".jsgrid-edit-row select[name='majorCode']").find("option:selected").text();
		  	  var proId = $(".jsgrid-edit-row td:eq(2) input:eq(1)").val();
		  	  
		  	var proName = $(".jsgrid-edit-row input[type='text'][name='proName']").val();
		  	var proCode = $(".jsgrid-edit-row input[type='text'][name='proCode']").val();
		  	
		  		updatingClient.periodId = periodId;
		  		updatingClient.proId = proId;
		  		updatingClient.majorCode = majorCode;
			  baseDb.updateItem(updatingClient,url);
			  updatingClient.periodName = periodName;
			  updatingClient.majorName  = majorName;
			  updatingClient.proName  = proName;
			  updatingClient.proCode  = proCode;
		  },
		  deleteItem: function(deletingClient) {
			  var url =context+"/admin/oc/permitYield/ajax/delete";
			  baseDb.deleteItem(deletingClient,url);
		  }
		}
		
		window.permitDeclareDb = permitDeclareDb;
	}());   
	
	$(document).on("click", "#selectPro", function() { 
		_proper = $(this);
		var url = context+"/config/query?NO=PROJECT_SELECT_LIST&MODEL=PM";
		openWindow(url, "选择项目", "800", "600", true, false);
	});
	
	/* 选择后回调方法 */
	function selectRes(objs){
		for(var i=0 ; i<objs.length; i++){
				reCall(objs[i]);
		}
	}

	/* 取值：data.字段名  */
	function reCall(data){
		$("input[name='proId']").val(data.ID);
		$("input[name='proCode']").val(data.PRO_CODE);
		$(".jsgrid-edit-row td:eq(3) input").val(data.PRO_NAME);
		$(".jsgrid-insert-row td:eq(3) input").val(data.PRO_NAME);
	}
	
	getMajor = function(){
		var _select = $("<select name='majorCode' class='form-control'></select>");
		var url = context+"/admin/oc/permitYield/ajax/major";
		$.ajax({
			url: url,
			success: function(data){
				if(data.flag == "true"){
					var majors = data.list;
					for (var i = 0; i < majors.length; i++) {
						$(_select).append("<option value="+majors[i].configCode+">"+majors[i].configName+"</option>");
					}
		 		}
		    }
		});
		return _select;
	}
	
	getPeriodSelect = function(){
		var _select = $("<select name='periodId' class='form-control'></select>");
		var url = context+"/admin/oc/period/ajax/select";
		$.ajax({
			url: url,
			success: function(data){
				if(data.flag == "true"){
					var periodSelect = data.periodSelect;
					for (var i = 0; i < periodSelect.length; i++) {
						$(_select).append("<option value="+periodSelect[i].id+">"+periodSelect[i].periodName+"</option>");
					}
		 		}
		    }
		});
		return _select;
	};
	</script>
</body>
</html>