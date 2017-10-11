<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>年度结算产值总表</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="rp.year.menu.path" scope="request"/>
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
		data:{"NO":"RP_YEAR", "MODEL":"RP"},
		async: false,
	    error: function(request) {
	    	jQuery.jalert({"jatext":"Connection error"});
	    },
	    success: function(data) {
	    	$("#content").empty();
			$("#content").append(data); 
	    }
	});
});
</script>
</body>
</html>