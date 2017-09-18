

//创建一个闭包  
(function($) {
	$.fn.ecolorg = function(options) {
		var defaults = {
			action : 'open',
			url : context+'/admin/hr/org/toselect',
			wName : '选择组织',
			width : '400',
			height : '600',
			targetId : null
		};
		
		var settings = $.extend({}, defaults, options);//将一个空对象做为第一个参数
		
		if(settings.targetId == null){
			alert("请传入返回ID元素name属性!");
			return;
		}
		
		if(settings.action == 'open'){
			openorg(this, settings);
		}
	}
	
	// 私有函数：打开选择组织机构页面的方法
	function openorg(obj, options) {
		var targetName = $(obj).attr("id");
		var url = options.url + "?targetId="+options.targetId+"&targetName="+targetName;
		openWindow(url, options.wName, options.width, options.height, true, false);
	}
})(jQuery);