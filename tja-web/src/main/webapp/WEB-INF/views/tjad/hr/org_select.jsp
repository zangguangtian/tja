<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>组织选择</title>
    <meta name="decorator" content="empty"/>
</head>
<body>
<div class="wrapBox ">
	<div class="wrapdiv">
		<div class="fl-l" style="width:100%;">
			<input type="hidden" id="targetId" value="${targetId }"/>
			<input type="hidden" id="targetName" value="${targetName }"/>
		   	<div id="left_menu_cnt" style="width:100%;height:100%;overflow:auto;">
				<jsp:include page="organize_tree.jsp" flush="true">
					<jsp:param value="check" name="settingType"/>
					<jsp:param value="0" name="loadUser"/>
				</jsp:include>
	       	</div>
		</div>
	</div>
</div>
</body>
</html>
