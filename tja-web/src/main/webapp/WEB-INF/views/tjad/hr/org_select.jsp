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
	       <div style="text-align:center;" >
		       <span ><input type="button" id="confirmBtn" class="btn-save mt13 " value="确定" /></span>
			   <span ><input type="button" id="closeBtn" class="btn-cancle mt13  ml10 " value="关闭" /></span>
		   </div>
		</div>
	</div>
</div>
<script type="application/javascript">
jQuery(document).ready(function(){
	jQuery("#confirmBtn").on("click", function(){
		var $targetId = jQuery("#targetId").val();
		var $targetName = jQuery("#targetName").val();
		var $orgId = jQuery("#orgId");
		if($orgId != undefined && $orgId != null){
			jQuery("#"+$targetId, window.opener.document).val($orgId.val());
		}
		var $orgName = jQuery("#orgName");
		if($orgName != undefined && $orgName != null){
			jQuery("#"+$targetName, window.opener.document).val($orgName.val());
		}
		window.close();
	})
	
	jQuery("#closeBtn").on("click", function(){
		window.close();
	})
});
</script>
</body>
</html>
