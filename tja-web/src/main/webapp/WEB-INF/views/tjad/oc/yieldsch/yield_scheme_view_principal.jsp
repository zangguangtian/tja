<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	<div class="col-xs-12">
		<h5 class="form-tit">各专业部门负责人会签<sec:authorize url="/admin/yield/scheme/ajax/principalEdit"><i class="fa fa-edit"></i></sec:authorize></h5>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">设计负责人</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.principalName }</label>
	    </div>
	</div>
	<c:if test="${not empty majors }">
		<c:forEach items="${majors }" var="major" varStatus="vs">
			<div class="col-xs-6 ">
				<label class="control-label col-xs-4">${major.configName }</label>
	            <div class="col-xs-8">
	                <label class="control-label">${yieldDuties[major.configCode].principalName }</label>
				</div>
	        </div>
	    </c:forEach>
	</c:if>
	<div class="col-xs-12 ">
		<label class="control-label col-xs-2">备注</label>
		<div class="col-xs-8">
			<label class="control-label">${yieldScheme.remark }</label>
		</div>
	</div>