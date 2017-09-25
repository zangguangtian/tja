//申报奖项
function BaseDb() {

}

BaseDb.prototype.pagerrenderer = function (obj) {
	var element = $("<div style='width:100%;height:100%;'></div>");
	var pgInfo = $("<div class=\"page-info\">共有 <span>"+obj.itemCount+"</span>条记录，当前第 <span>"+obj.pageIndex+"/"+obj.pageCount+"</span>页</div>");
	element.append(pgInfo);
	
	var pgBtn = $("<ul class=\"foot-page\"></ul>");
	var pgBtnHtml = "<li>每页记录数&nbsp;";
	pgBtnHtml += "<select id=\"rowsPerPage\" name=\"page.rowsPerPage\" style=\"width:50px;\">";
	pgBtnHtml += "<option value=\"5\">5</option>";
	pgBtnHtml += "<option value=\"10\">10</option>";
	pgBtnHtml += "<option value=\"15\">15</option>";
	pgBtnHtml += "<option value=\"20\">20</option>";
	pgBtnHtml += "<option value=\"30\">30</option>";
	pgBtnHtml += "<option value=\"50\">50</option>";
	pgBtnHtml += "<option value=\"100\">100</option></select></li>";
	if(obj.pageIndex == 1){
		pgBtnHtml += "<li style=\"cursor:default;\" class=\"page-first\"></li>";
		pgBtnHtml += "<li style=\"cursor:default;\" class=\"page-prev\"></li>";
	}else{
		pgBtnHtml += "<li id=\"first\" class=\"page-first\"></li>";
		pgBtnHtml += "<li id=\"previous\" class=\"page-prev\"></li>";
	}
	pgBtnHtml += "<li><input type=\"text\" id=\"goPage\" value=\"\" class=\"choose-page\" onkeyup=\"value=value.replace(/[^\\d]/g,'');\"/></li>";
	pgBtnHtml += "<li id=\"btnGo\" class=\"page-go\">GO</li>";
	if(obj.pageIndex == obj.pageCount){
		pgBtnHtml += "<li style=\"cursor:default;\" class=\"page-next\"></li>";
		pgBtnHtml += "<li style=\"cursor:default;\" class=\"page-last\"></li>";
	}else{
		pgBtnHtml += "<li id=\"next\" class=\"page-next\"></li>";
		pgBtnHtml += "<li id=\"last\" class=\"page-last\"></li>";
	}
	pgBtn.append(pgBtnHtml);
	element.append(pgBtn);
	element.find("select#rowsPerPage").val(obj.pageSize);
	return element;
},

BaseDb.prototype.loadData = function(filter, url) {
	return jQuery.ajax({
		type : "POST",
		url : url,
		data : filter
	});
},

BaseDb.prototype.insertItem = function(insertingClient, url) {
	jQuery.ajax({
		type : "POST",
		url : url,
		data : insertingClient,
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
		//	alert(data.msg);
			if (data.flag == 'true') {
				var length = jQuery(
						"div.jsgrid-grid-body table.jsgrid-table tr").size();
				//改变编号，修改时间，修改人
				insertingClient.number = length + 1;
				insertingClient.modifyTime = modifyTime;
				insertingClient.modifierName = modifierName;
			}
		}
	});

},

BaseDb.prototype.updateItem = function(updatingClient,url) {
	jQuery.ajax({
		type : "POST",
		url : url,
		data : updatingClient,
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			//alert(data.msg);
		}
	});
},

BaseDb.prototype.deleteItem = function(deletingClient, url) {
	jQuery.ajax({
		type : "POST",
		url : url,
		data : deletingClient,
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
		//	alert(data.msg);
			if (data.flag == 'true') {
			}
		}
	});
}
 
//自定义日期类型
var MyDateField = function(config) {
    jsGrid.Field.call(this, config);
};
 
MyDateField.prototype = new jsGrid.Field({
 
    css: "date-field",            // redefine general property 'css'
    align: "center",              // redefine general property 'align'
 
    myCustomProperty: "foo",      // custom property
 
    sorter: function(date1, date2) {
        return new Date(date1) - new Date(date2);
    },
 
    itemTemplate: function(value) {
    	return  value==null ? '' :new Date(value).format("yyyy-MM-dd");
    	
    },
 
    insertTemplate: function(value) {
        return this._insertValue = $('<input type="text" style="text-align:center;" onfocus="WdatePicker()"/>');
    },
 
    editTemplate: function(value) {
    	var newFormatDate = value==null ? '' :new Date(value).format("yyyy-MM-dd");
    	this._editValue = $("<input type='text' style='text-align:center;' name='holidayDate' value='"+newFormatDate+"' onfocus='WdatePicker()'/>");
        return this._editValue;
    },
 
    insertValue: function() {
    	return $(this._insertValue[0]).val();
    },
 
    editValue: function(value) {
    	return $(this._editValue[0]).val();
    },
 
    filterTemplate:function(){
    	var $result = this._filterValue = $('<input type="text" style="text-align:center;" onfocus="WdatePicker()"/>');
    	return $result;
    },
    filterValue: function() {
        return  $(this._filterValue[0]).val();
    }
    
});

jsGrid.fields.date = MyDateField;
