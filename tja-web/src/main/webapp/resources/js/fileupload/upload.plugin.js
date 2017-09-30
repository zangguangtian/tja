
//创建一个闭包  
(function($) {
	$.fn.ecolfile = function(options) {
		var defaults = {
			action : 'upload',
			model : 'sm',
			formname : 'saveForm',
			multipleRId : '0',			//一个表单是否有多个relationId
			doneback : 'showattach',
			progressallback : 'progressattach'
		};

		var settings = $.extend({}, defaults, options);//将一个空对象做为第一个参数

		if(settings.action == 'upload'){
			upload(this, settings);
		}
		
		if(settings.action == 'delete'){
			deletefile(this, settings);
		}
	};

	// 私有函数：上传附件
	function upload(obj, options) {
		var $this = $(obj);
		if(!$this.is("input[type='file']")){
			return;
		}
		
		var urlStr = null;
		if(options.multipleRId == '0'){
			urlStr = context+'/admin/'+options.model+'/file/ajax/singleupload';
			if($this.prop("multiple")){
				//获取当前file控件在页面中所有file控件中的下标
				var index = $("input[type='file'][multiple]").index($this);
				if(index == undefined || index == null || index == ""){
					index = 0;
				}
				urlStr = context+'/admin/'+options.model+'/file/ajax/multupload/'+index;
			}
		}else{	//一个表单有多个relationId
			//获取当前file控件在页面中所有file控件中的下标
			var index = $("input[type='file']").index($this);
			if(index == undefined || index == null || index == ""){
				index = 0;
			}
			urlStr = context+'/admin/'+options.model+'/file/ajax/multform/upload/'+index;
		}

		var fileTypes = $this.attr("fileTypes");
		var typeExp = new RegExp('\S*');
		if(fileTypes != undefined && fileTypes != null && fileTypes != ""){
			typeExp = new RegExp(fileTypes, "i");
		}
		//字节
		var maxSize = $this.attr("maxSize");
		if(maxSize == undefined || maxSize == null || maxSize == ""){
			maxSize = "20000000";//20M
		}
		
		$this.fileupload({
	    	url: urlStr,
	    	type : "POST",
	        dataType: 'json',
	        //maxFileSize: maxSize,
	        acceptFileTypes: typeExp,
	        messages: {
	        	acceptFileTypes: '文件类型不正确!',
	        	maxFileSize:'文件不能超过'+maxSize+'字节!'
	        },
	        done: function (e, data) {
	        	var dataStr = $.toJSON(data.result);
	        	eval(options.doneback+"("+dataStr+")");
	        },
	        progressall: function (e, data) {
	        	var dataStr = $.toJSON(data);
	        	if(options.progressallback!=null && options.progressallback != ""){
	        		eval(options.progressallback+"("+dataStr+")");
	        	}
	        }
	        //,dropZone: $('#dropzone')
		});
	}
	
	// 私有函数：删除附件
	function deletefile(obj, options) {
		var $this = $(obj);
		var id = $this.attr("attachId");
		if(id == undefined || id == null || id == ""){
			return;
		}
		
		var url = context+'/admin/'+options.model+'/file/ajax/delete/'+id;
		jQuery.ajax({
			type : "POST",
			url : url,
			data : jQuery("#"+options.formname).serialize(),
			async : false,
			error : function(request) {
				alert("删除出错!");
			},
			success : function(data) {
				var dataStr = $.toJSON(data);
	        	eval(options.doneback+"("+dataStr+")");
			}
		});
	}
})(jQuery);

function showattach(){
	
}

function progressattach(){
	
}
