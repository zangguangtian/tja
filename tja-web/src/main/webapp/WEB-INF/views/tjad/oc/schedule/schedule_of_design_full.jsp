<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:forEach items="${designs }" var="design" varStatus="vs">
	<tr data-divisorid="${design.userId }">
		<td>${vs.index + 1 }</td>
		<td>${design.majorName }</td>
		<td>${design.subName }</td>
		<td>${design.taskName }</td>
		<td>${design.userRoleName }</td>
		<td>${design.staffName }</td>
		<td>${design.orgName }</td>
		<td>${design.preSchedule }</td>
		<td><input type="text" name="currSchedule" class="form-control" value="${empty design.currSchedule? 0:design.currSchedule }" data-rule-required="true" data-rule-number="true"></td>
		<td>${design.currSchedule - design.preSchedule}</td>
		<td>
			<select name="scheduleStatus" class="form-control" data-rule-required="true">
				<option value="" >—请选择—</option>
				<c:forEach items="${statuses }" var="status">
					<option value="${status.configCode }" <c:if test="${design.scheduleStatus == status.configCode}">selected</c:if> >${status.configName }</option>
				</c:forEach>
			</select>
		</td>
		<td><input type="text" name="remark" class="form-control" value="${design.remark }"></td>
	</tr>
</c:forEach>