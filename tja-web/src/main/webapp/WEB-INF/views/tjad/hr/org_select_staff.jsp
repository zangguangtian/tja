<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta charset="utf-8" />
<title>人员选择</title>
<meta name="decorator" content="empty" />
<link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
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
			
				<div id="selectUser"></div>
				
				
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
	<script type="text/javascript" src="${site}/resources/js/hr/select_staff.js?v=${buildVersion}"></script>
	<script type="application/javascript">
	var openType = "${openType}";
	</script>
</body>
</html>
