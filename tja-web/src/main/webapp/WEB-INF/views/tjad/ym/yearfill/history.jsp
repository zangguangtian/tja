<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="empty"/>
    <title>实际进度-历史</title>
</head>
<body>
<div class="wrapBox" style="height: 600px">
	<div class="col-xs-12" id="content">
	
	</div>
</div>
<script type="text/javascript">
var proId = "${proId}";

$(function(){
	loadList("YEAR_HIS", $("#content"));
});

function loadList(NO, content_div){
	var sUrl ="${site}/config/ajax/query";
	$.ajax({
		type: "POST",
		url:sUrl,
		data:{"NO":NO, "MODEL":"YM", "qarg.proId":proId},
		async: false,
	    error: function(request) {
	    	$.jalert({"jatext":"Connection error"});
	    },
	    success: function(data) {
	    	content_div.empty();
	    	content_div.append(data); 
	    }
	});
}

</script>
</body>
</html>