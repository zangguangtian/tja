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
    <ul id="orgTree" class="ztree" style="overflow-y: auto; width: 98%; height: 100%;"></ul>
</div>
<link rel="stylesheet" href="${site}/resources/css/zTreeStyle/zTreeStyle.css?v=${buildVersion}" type="text/css">
<script type="text/javascript" src="${site}/resources/js/plugins/ztree/jquery.ztree.all.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
//<!--
var rootId = "PRO0001I";
var rootParentId = "0";
//-->
</script>
