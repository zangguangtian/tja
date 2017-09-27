<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>期间管理-提前上报</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.period.menu.path" scope="request"/>
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/jsgrid.css?v=${buildVersion}" />
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/theme.css?v=${buildVersion}" />
    <link rel="Stylesheet" type="text/css" href="${site }/resources/css/management.css?v=${buildVersion}">
    <script src="${site}/resources/js/jsgrid/jquery-1.8.3.js?v=${buildVersion}"></script>
    <script src="${site}/resources/js/jsgrid/jsgrid.js?v=${buildVersion}"></script>
</head>
<body>
<div class="wrapBox">
    <div class="wrapdiv" style="margin-top:20px">
		<div id="jsGrid"></div>
	</div>
</div>

<script src="${site}/resources/js/jsgrid/db.js?v=${buildVersion}"></script>
<script type="text/javascript">
var advanceFillGrid = null;
var baseDb = new BaseDb();

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


$(function() {
	var periodSelect_insert = getPeriodSelect();
	var periodSelect_edit = getPeriodSelect();
	
	var Grid = jsGrid.Grid;
    advanceFillGrid = new Grid("#jsGrid", {
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
        controller : advanceFillDb,
        fields : [ {
            name : "number",
            title : "序号",
            align : "center",
            width : "5%"
        }, {
            name : "id",
            type : "text",
            visible: false
        }, {
        	name : "periodName",
        	title : "期间<font class=require>※</font>",
        	type : "text",
        	width : "15%",
        	inserting: false,
            editing: false,
            
            insertTemplate : function() {
	       	    return periodSelect_insert;
        	},
        	editTemplate: function(value, item) {
    			$(periodSelect_edit).val(item.periodId);
	       	    return periodSelect_edit;
        	}
        }, {
        	name : "proCode",
        	title : "项目编号<font class=require>※</font>",
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
        }, {
        	name : "remark",
        	title : "备注",
        	type : "text",
        	width : "30%"
        }, {
            type : "control",
            deleteButton : true
        } ]
    });
    
    $(":text").addClass("form-control");
    
});

$(document).on("click", "#selectPro", function() { 
	_proper = $(this);
	var url = context+"/config/query?NO=PROJECT_SELECT_LIST&MODEL=PM";
	openWindow(url, "选择项目", "800", "600", true, false);
});

//首页、上一页、下一页、尾页事件
$(document).on("click", "div.jsgrid-pager li#first",function(){
	advanceFillGrid.openPage(1);
});

$(document).on("click", "div.jsgrid-pager li#previous",function(){
	advanceFillGrid.openPage(parseInt(advanceFillGrid.pageIndex) - 1);
}); 

$(document).on("click", "div.jsgrid-pager li#next",function(){
	advanceFillGrid.openPage(parseInt(advanceFillGrid.pageIndex) + 1);
}); 

$(document).on("click", "div.jsgrid-pager li#last",function(){
	var itemsCount = advanceFillGrid._loadStrategy._itemsCount;
	var pageSize = advanceFillGrid.pageSize;
	var pagesCount = Math.floor(itemsCount / pageSize) + (itemsCount % pageSize ? 1 : 0);
	advanceFillGrid.openPage(pagesCount);
});

$(document).on("click", "div.jsgrid-pager li#btnGo",function(){
	var pageIndex = $(this).closest("ul.foot-page").find("input#goPage").val();
	advanceFillGrid.openPage(pageIndex);
});

$(document).on("change", "div.jsgrid-pager select#rowsPerPage",function(){
	advanceFillGrid.pageSize = $(this).val();
	advanceFillGrid.openPage(1);
});

(function() {
	var advanceFillDb = {
		loadData: function(filter) {
	  		var url =context+"/admin/oc/period/advanceFill/list";
		  	return baseDb.loadData(filter,url);
	  	},
	  	insertItem: function(insertingClient) {
	  		var periodId = $(".jsgrid-insert-row select").val();
	  		var proId = $(".jsgrid-insert-row td:eq(2) input:eq(1)").val();
	  		
	  		if(periodId.length == 0) {
	  			alert("请选择期间"); 
	  			return;
	  		}
	  		if(proId.length == 0) {
	  			alert("请选择项目");
	  			return;
	  		}
	  		
	  		insertingClient.periodId = periodId;
	  		insertingClient.proId = proId;
	  		
		  	var url =context+"/admin/oc/period/advanceFill/save";
			$.ajax({
				type : "POST",
				url : url,
				data : insertingClient,
				async : false,
				error : function(request) {
					alert("Connection error");
				},
				success : function(data) {
					if (data.flag == 'true') {
						var length = jQuery("div.jsgrid-grid-body table.jsgrid-table tr").size();
						//改变编号
						insertingClient.number = length + 1;
					}
				}
			});
	  	},	
	  	updateItem: function(updatingClient) {
	  		var periodId = $(".jsgrid-edit-row select").val();
	  		var periodName = $(".jsgrid-edit-row select").find("option:selected").text();
	  		var proId = $(".jsgrid-edit-row td:eq(2) input:eq(1)").val();
	  		updatingClient.periodId = periodId;
	  		updatingClient.periodName = periodName;
	  		updatingClient.proId = proId;
		  	var url =context+"/admin/oc/period/advanceFill/save";
		  	baseDb.updateItem(updatingClient,url);
	  	},
	  	deleteItem: function(deletingClient) {
		  	var url =context+"/admin/oc/period/advanceFill/delete";
		  	baseDb.deleteItem(deletingClient,url);
	  	}
	};
	
	window.advanceFillDb = advanceFillDb;
}());

$(function(){
	$(".jsgrid-filter-row input").keydown(function(e){
		if(e.keyCode==13){
			$("#jsGrid").jsGrid("search", {});
		}
	});
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

</script>
</body>
</html>