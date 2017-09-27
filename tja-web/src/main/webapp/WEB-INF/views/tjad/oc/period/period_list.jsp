<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>期间管理</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.period.menu.path" scope="request"/>
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
		data:{"NO":"PERIOD_LIST", "MODEL":"OC"},
		async: false,
	    error: function(request) {
	    	jQuery.jalert({"jatext":"Connection error"});
	    },
	    success: function(data) {
	    	$("#content").empty();
			$("#content").append(data); 
	    }
	});
	$("ul[name='rul']").after('<span class="o-btn"><input type=button value="添加期间" onclick="toadd();" ><input type=button value="提前上报" style="margin-left: 10px;" onclick="report();" ></span>');
});

function toadd(){
    window.location.href="${site}/admin/oc/period/0";
}

function report(){
    window.location.href="${site}/admin/oc/period/advanceFill";
}

</script>
</body>
</html>