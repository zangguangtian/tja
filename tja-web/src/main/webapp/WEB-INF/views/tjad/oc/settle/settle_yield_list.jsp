<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>可结算产值管理</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.yield.settle.menu.path" scope="request"/>
</head>
<body>
<div class="wrapBox ">
    <div id="content">
    </div>
</div>
<script type="text/javascript">
jQuery(function(){
	var sUrl ="${site}/config/ajax/query";
	jQuery.ajax({
		type: "POST",
		url:sUrl,
		data:{"NO":"YIELDSETTLE_LIST", "MODEL":"OC"},
		async: false,
	    error: function(request) {
	    	jQuery.jalert({"jatext":"Connection error"});
	    },
	    success: function(data) {
	    	$("#content").empty();
			$("#content").append(data); 
	    }
	});
	$("ul[name='rul']").after('<span class="o-btn"><input type=button value="导入" style="margin-right: 10%;" onclick="toImport();" ></span>');
	$("ul[name='rul']").after('<span class="o-btn"><input type=button value="模板下载" onclick="download();" ></span>');
});

function toImport(){
    var url = "${site}/admin/oc/settle/import";
    openWindow(url, "可结算产值管理-导入", "800", "600", true, false);
}

function download(){
	window.location.href="${site}/download/settle_download.xlsx";
}

</script>
</body>
</html>