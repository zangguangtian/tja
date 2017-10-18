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
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/jsgrid.css?v=${buildVersion}" />
    <link rel="stylesheet" type="text/css" href="${site}/resources/css/jsgrid/theme.css?v=${buildVersion}" />
    <link href="${site }/resources/css/management.css" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
   <div id="jsGrid"></div>
 </div>
 <%-- <script src="${site}/resources/js/jsgrid/jquery-1.8.3.js"></script> --%>
 <script src="${site}/resources/js/jsgrid/jsgrid.js?v=${buildVersion}"></script>
 <script src="${site}/resources/js/jsgrid/db.js?v=${buildVersion}"></script>
	<script type="text/javascript">
		var awardsGrid = null;
		var baseDb = new BaseDb();
		$(function() {
			jsGrid.validators.number = {
					message:function (value, item) {
						return "请输入数字";
					},
					validator: function(value, item) {
						return /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
					}
			 }
			
			var Grid = jsGrid.Grid;
			Grid.prototype.loadData = function(filter) {
		            filter = filter || (this.filtering ? this.getFilter() : {});

		            $.extend(filter, this._loadStrategy.loadParams(), this._sortingParams());

		            var args = this._callEventHandler(this.onDataLoading, {
		                filter: filter
		            });

		            return this._controllerCall("loadData", filter, args.cancel, function(loadedData) {
		            	
		            	if(loadedData.data != null && loadedData.data != 'undefined' && loadedData.data != ''){
		            		var standardPriceData = loadedData.data;
		            		for(var i =0;i<standardPriceData.length;i++){
		            			var codes = standardPriceData[i].codes;
		            			var values = standardPriceData[i].values;
		            			if(values != null){
			            			var dataCodes = codes.split(",");
			            			var dataValues = values.split(",");
			            			for(var j = 0;j<dataCodes.length;j++){
			            				var priceData = standardPriceData[i];
			            				priceData[""+dataCodes[j]+""] = dataValues[j];
			            			}
		            			}
		            		}
		            	}
		            	
		                if(!loadedData)
		                    return;

		                this._loadStrategy.finishLoad(loadedData);

		                this._callEventHandler(this.onDataLoaded, {
		                    data: loadedData
		                });
		            });
		        }
			
			    Grid.prototype.clearInsert = function() {
		            if(window.totalValidate != false){
		            	var insertRow = this._createInsertRow();
		                this._insertRow.replaceWith(insertRow);
		                this._insertRow = insertRow;
		                this.refresh();
		            }else{
		            	jQuery("tr.jsgrid-insert-row").find("td.checkTotal").addClass("jsgrid-invalid");
		            	jQuery("tr.jsgrid-insert-row").find("td.checkTotal").attr("title","各专业比例合计必须为100");
		            }
		        }
			
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
					insertcss:"checkTotal",
					pagerRenderer : baseDb.pagerrenderer,
					noDataContent : "",
					deleteConfirm : "确认删除?",
					invalidMessage:"",
					controller : awardsDeclareDb,
					fields : [ {
						name : "number",
						title : "序号",
						width : "5%"
					},{
						name : "categoryCode",
						title : "分类<font class=required>※</font>",
						type : "text",
						width : "10%",
						validate: {
				            validator: "required",
				            message: function(value, item) {
				                return "分类是必填项!";
				            }
				        }, 
				        css: "jsgrid-form-control",
				        filtering:true
					},{
						name : "typeCode",
						title : "类型<font class=required>※</font>",
						type: "text",
						width : "10%",
						filtering:true,
						css: "jsgrid-form-control",
						validate: [  {
				            validator: "required",
				            message: function(value, item) {
				                return "类型是必填项!";
				            }
					     },
		                 { 
					    	validator: function (value,item) {
					    		var v =value;
					    		var i = item;
					    	    var fag = true;
					    		jQuery(".jsgrid-grid-body tr").not(".jsgrid-grid-body tr[style='display: none;']").not(".jsgrid-edit-row").each(function(){
									var _this = $(this);
					    			var value = _this.find("td:eq(2)").text().trim();
					    			var row = jQuery(".jsgrid-filter-row");
									var filter = jQuery(".jsgrid-filter-row").css("display");
									if(filter == 'none'){
										row = jQuery(".jsgrid-insert-row");
									}else{
										row =jQuery(".jsgrid-edit-row");
									}
									var insertValue = row.find("td:eq(2) input").val();
									if(insertValue == value){
										fag = false;
									}
								});
					    		return fag;
							},
				            message: function(value, item) {
				                return "类型不能相同,请重新选择!";
				            }
			             }
		               ]
					}, {
						name : "typeName",
						title : "类型名称",
						type : "text",
						width : "20%",
						css: "jsgrid-form-control",
						filtering:true
					}, {
						name : "unitPrice",
						title : "土建基准单价（元）",
						type : "text",
						validate:"number",
						width : "10%",
						css: "jsgrid-form-control",
						filtering:false
					}, 
					${gridModel},
					{
						name : "remark",
						title : "备注",
						type : "text",
						width : "10%",
						css: "jsgrid-form-control",
						filtering:false
					},{
						type : "control"
					}]
				});
			 $("input").addClass("form-control");
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

			  var totalScale = 0;
			  var props = "";
			  for(var o in insertingClient){ 
				  if(o.indexOf("_")>0){
					  props+= o + "=" + insertingClient[o] + "&";
					  totalScale += new Number(insertingClient[o]); 
				  }
			  } 
			  insertingClient.keyValue = props.substring(0,props.length-1);
			  if(totalScale != new Number(100)){
				  window.totalValidate = false;
				  $.jalert({"jatext":"各专业比例合计必须为100"});
				  return;
			  }
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
							window.totalValidate = true;
						}
					}
				});
		  },	
		  updateItem: function(updatingClient) {
			  var url =context+"/admin/oc/stPrice/ajax/asave";
				delete updatingClient.createDate;
				delete updatingClient.modifyDate;
			 
			 var totalScale = 0;	
			 var props = "";
			  for(var o in updatingClient){ 
				  if(o.indexOf("_")>0){
					  props+= o + "=" + updatingClient[o] + "&";
					  totalScale += new Number(updatingClient[o]); 
				  }
			  } 
			  updatingClient.keyValue = props.substring(0,props.length-1);
			  if(totalScale != new Number(100)){
				  $.jalert({"jatext":"各专业比例合计必须为100"});
				  return;
			  }
			  baseDb.updateItem(updatingClient,url);
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