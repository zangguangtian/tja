<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.df.ecoland.constant.Constants"%>
<%@ page import="com.df.framework.util.StringUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String settingType=request.getParameter("settingType");
String loadUser=request.getParameter("loadUser");
if(StringUtil.isBlank(loadUser)){
    loadUser = "1";
}
%>
<div id="nav_resource">
	<input type="hidden" id="settingType" value="<%=settingType  %>" />
	<input type="hidden" id="loadUser" value="<%=loadUser  %>" />
    <ul id="orgTree" class="ztree"></ul>
</div>
<script type="text/javascript">
//<!--
var currUserOrgId = "${SysUser.orgId}";
var rootId = "<%=BaseConstant.HR_ORG.ORG_ROOT_ID %>";
var rootParentId = "<%=BaseConstant.HR_ORG.ORG_ROOT_PARENT_ID %>";
//-->
</script>
<link rel="stylesheet" href="${site}/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${site}/resources/js/plugins/ztree/jquery.ztree.all.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js"></script>