<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String settingType=request.getParameter("settingType");
String loadUser=request.getParameter("loadUser");
if( StringUtils.isBlank(loadUser)){
    loadUser = "1";
}
%>
<div id="nav_resource">
	<input type="hidden" id="settingType" value="<%=settingType  %>" />
	<input type="hidden" id="loadUser" value="<%=loadUser  %>" />
	<input type="hidden" id="node_orgId" value="" />
	<input type="hidden" id="node_orgName" value="" />
    <ul id="orgTree" class="ztree"></ul>
</div>
<link rel="stylesheet" href="${site}/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${site}/resources/js/plugins/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js"></script>
<script type="text/javascript">
//<!--
var rootId = "0";
var rootParentId = "-1";
//-->
</script>
