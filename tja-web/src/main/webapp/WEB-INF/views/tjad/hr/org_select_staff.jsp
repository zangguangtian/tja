<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta charset="utf-8" />
<title>人员选择</title>
<meta name="decorator" content="empty" />
<link href="${site }/resources/css/management.css" rel="Stylesheet" type="text/css">
</head>
<body>
<style>
ul,ol,li{list-style-type:none;}

.radio-ul li {
	float: left;
	margin-right: 10px;
	width: 130px;
	padding: 5px 2px;
}
</style>
	<div class="wrapBox ">
		<div class="wrapdiv">
			<div class="fl-l" style="position: absolute;">
				<input type="hidden" id="callMethod" value="${callMethod }" /> <input
					type="hidden" id="targetName" value="${targetName }" />
				<div id="left_menu_cnt">
					<jsp:include page="organize_tree.jsp" flush="true">
						<jsp:param value="${openType}" name="settingType" />
					</jsp:include>
				</div>
			</div>
			<div class="" style="margin-left: 230px;">
				<!-- 这个表单是放查询的条件 -->
				<input id="treeNodeId" type="hidden" name="orgId" /> <input
					type="hidden" value="${vo.positiveFlag }" name="positiveFlag" />
				<div class="search-customer mt20" style="height: 40px;">
					<ul class="search-ul pt10 fl-l">
						<li>姓名 <input type="text" id="name" /></li>
						<li>组织名称<input type="text" id="orgName" /></li>
					</ul>
					<div class="fl-l">
						<span><input type="button" id="btnSearch"
							class="set-current mr10" value="查询" /></span>
					</div>
					<div class="cl"></div>
				</div>
				<div style="overflow-x: auto; width: 98%; max-height: 280px;">
					<c:if test="${openType=='radio' or openType=='checkbox'}">
						<table class="tb-customer mt10">
							<thead class="tb-list-head">
								<tr class="tb-head-center">
									<td nowrap="nowrap" style="text-align: center;"><input
										type="checkbox" id="chooseAll" /></td>
									<td nowrap="nowrap">姓名</td>
									<td nowrap="nowrap">组织名称</td>
									<td nowrap="nowrap">设计定级</td>
									<td nowrap="nowrap">UC</td>
									<td nowrap="nowrap">性别</td>
								</tr>
							</thead>
							<tbody id="orgStaffList" class="tb-list-body"></tbody>
						</table>
					</c:if>
					<c:if test="${openType == 'random'}">
						<table class="tb-customer mt20  ">
							<tbody class="tb-customer-bodys">
								<tr>
									<td class="choose-name-lists" id="orgStaffList"></td>
								</tr>
							</tbody>
						</table>
					</c:if>
				</div>
				<c:if test="${openType=='checkbox'}">
					<div style="width: 98%;">
						<table class="tb-customer mt20  user-grant-tb">
							<thead class="tb-customer-head">
								<tr>
									<td>待确认用户:</td>
								</tr>
							</thead>
							<tbody class="tb-customer-bodys">
								<tr>
									<td class="choose-name-lists">
										<ul class="user-attach-ul " id="orgStaffList-ul"
											style="overflow-y: auto; width: 100%; height: 100px;">
											<c:if test="${!empty roleUsers}">
												<c:forEach items="${roleUsers }" var="role"
													varStatus="status">
													<li><span>${role.realName }</span><span
														attachId="${role.id }" class='attach-del-icon'></span></li>
												</c:forEach>
											</c:if>
										</ul>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<form id="frmSearch"></form>
	<script type="text/javascript" src="${site}/resources/js/hr/select_staff.js"></script>
	<script type="application/javascript">
		
	var openType = "${openType}";
jQuery(document).ready(function(){
	var inputType = "radio";
	if ("checkbox" === openType) {
		inputType = "checkbox";
	}
	$("#orgStaffList").find("input[type="+inputType+"]").off().on("click", function(){
		chooseStaff(this);
	});
	
	$("#chooseAll").off().on("click",function(){
		if($(this)[0].checked){
			chooseAllStaff(this,"#orgStaffList");
		}else{
			jQuery("#orgStaffList").find("input[type='checkbox']:checked").removeAttr("checked");
		}
	});
});

	</script>
</body>
</html>
