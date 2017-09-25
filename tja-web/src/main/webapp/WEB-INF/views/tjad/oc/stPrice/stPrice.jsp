<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>土建基准单价及专业比例</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.standard.price.menu.path" scope="request"  />
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/jsgrid.css" />
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/theme.css" />
    <link href="${site }/resources/css/management.css" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
   <div id="jsGrid"></div>
 </div>
 <%-- <script src="${site}/resources/js/jsgrid/jquery-1.8.3.js"></script> --%>
 <script src="${site}/resources/js/jsgrid/jsgrid.js"></script>
 <script src="${site}/resources/js/jsgrid/db.js"></script>
	<script type="text/javascript">
		var awardsGrid = null;
		var baseDb = new BaseDb();
		$(function() {
			 var Grid = jsGrid.Grid;
			 awardsGrid = new Grid("#jsGrid", {
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
					controller : awardsDeclareDb,
					fields : [ {
						name : "number",
						title : "序号",
						width : "5%"
					},{
						name : "categoryCode",
						title : "分类",
						type : "text",
						width : "20%"
					},{
						name : "typeCode",
						title : "类型",
						type: "text",
						width : "30%",
					}, {
						name : "typeName",
						title : "类型名称",
						type : "text",
						width : "30%",
					}, {
						name : "unitPrice",
						title : "土建基准单价（元）",
						type : "number",
						width : "10%"
					}, 
					${gridModel},
					{
						name : "remark",
						title : "备注",
						type : "number",
						width : "10%"
					},{
						type : "control"
					}]
				});
		});
		
		//首页、上一页、下一页、尾页事件
		jQuery(document).on("click", "div.jsgrid-pager li#first",function(){
			awardsGrid.openPage(1);
	    });

		jQuery(document).on("click", "div.jsgrid-pager li#previous",function(){
			awardsGrid.openPage(parseInt(awardsGrid.pageIndex) - 1);
	    }); 

		jQuery(document).on("click", "div.jsgrid-pager li#next",function(){
			awardsGrid.openPage(parseInt(awardsGrid.pageIndex) + 1);
	    }); 

		jQuery(document).on("click", "div.jsgrid-pager li#last",function(){
			var itemsCount = awardsGrid._loadStrategy._itemsCount;
			var pageSize = awardsGrid.pageSize;
			var pagesCount = Math.floor(itemsCount / pageSize) + (itemsCount % pageSize ? 1 : 0);
			awardsGrid.openPage(pagesCount);
	    });
		
		jQuery(document).on("click", "div.jsgrid-pager li#btnGo",function(){
			var pageIndex = jQuery(this).closest("ul.foot-page").find("input#goPage").val();
			awardsGrid.openPage(pageIndex);
	    });
		
		jQuery(document).on("change", "div.jsgrid-pager select#rowsPerPage",function(){
			awardsGrid.pageSize = jQuery(this).val();
			awardsGrid.openPage(1);
	    });
	
	(function() {
		var awardsDeclareDb = {
		  loadData: function(filter) {
			   var url =context+"/admin/oc/stPrice/ajax/search";
			  var data = baseDb.loadData(filter,url);
			  return data;
		  },
		  insertItem: function(insertingClient) {
			  var url =context+"/admin/oc/stPrice/ajax/asave";
			  jQuery.ajax({
					type : "POST",
					url : url,
					data : insertingClient,
					async : false,
					error : function(request) {
						alert("Connection error");
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
			  var url =context+"/admin/oc/stPrice/ajax/asave";
				delete updatingClient.createDate;
				delete updatingClient.modifyDate;
				
				
			//  baseDb.updateItem(updatingClient,url);
		  },
		  deleteItem: function(deletingClient) {
			  var url =context+"/admin/oc/stPrice/ajax/delete";
				delete deletingClient.createDate;
				delete deletingClient.modifyDate;
			  baseDb.deleteItem(deletingClient,url);
		  }
		}
		
		window.awardsDeclareDb = awardsDeclareDb;
	}());   
	</script>
</body>
</html>