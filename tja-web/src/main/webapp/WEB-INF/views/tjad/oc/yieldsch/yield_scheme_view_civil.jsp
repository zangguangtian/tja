<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	<div class="col-xs-12">
		<h5 class="form-tit">土建产值<span class="control-label" style="font-size:12px;">（元）</span><i class="fa fa-edit"></i></h5>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">实际合同额</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.contractAmount }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">分包扣减</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.pkgAmount }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">方案扣减</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.schemeAmount }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">其他扣减</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.rebateAmount }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">土建总产值</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.totalAmount }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">各专业产值</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.majorAmount }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">项目负责人（%）</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.principalRate }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">项目负责人（产值）</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.principalYield }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">项目经理（%）</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.pmRate }</label>
	    </div>
	</div>
	<div class="col-xs-6 ">
	    <label class="control-label col-xs-4">项目经理（产值）</label>
	    <div class="col-xs-8">
	        <label class="control-label">${yieldScheme.pmYield }</label>
	    </div>
	</div>
	<c:if test="${not empty majors }">
		<c:forEach items="${majors }" var="major" varStatus="vs">
	        <div class="col-xs-6 ">
	            <label class="control-label col-xs-4">${major.configName }产值</label>
	            <div class="col-xs-8">
	                <label class="control-label" data-majorcode="${major.configCode }">${yieldDuties[major.configCode].majorYield }</label>
	            </div>
	        </div>
	    </c:forEach>
	</c:if>