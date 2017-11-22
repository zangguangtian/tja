<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <h3 class="form-tit col-lg-12">土建产值<span class="control-label" style="font-size:12px;">（元）</span></h3>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">实际合同额<span class="required">※</span></label>
        <div class="col-md-8">
            <input type="text" name="contractAmount" class="form-control fourAmount" data-rule-required="true" value="${yieldScheme.contractAmount }">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">分包扣减</label>
        <div class="col-md-8">
            <input type="text" name="pkgAmount" class="form-control fourAmount" value="${yieldScheme.pkgAmount }">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">方案扣减</label>
        <div class="col-md-8">
            <input type="text" name="schemeAmount" class="form-control fourAmount" value="${yieldScheme.schemeAmount }" readonly>
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">其他扣减</label>
        <div class="col-md-8">
            <input type="text" name="rebateAmount" class="form-control fourAmount" value="${yieldScheme.rebateAmount }">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">土建总产值</label>
        <div class="col-md-8">
            <input type="text" name="totalAmount" class="form-control" value="${yieldScheme.totalAmount }" readonly>
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">各专业产值</label>
        <div class="col-md-8">
            <input type="text" name="majorAmount" class="form-control" value="${yieldScheme.majorAmount }" readonly>
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目负责人（%）<span class="required">※</span></label>
        <div class="col-md-8">
            <input type="text" name="principalRate" class="form-control twoProUser" value="${yieldScheme.principalRate }" data-rule-required="true">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目负责人（产值）</label>
        <div class="col-md-8">
            <input type="text" name="principalYield" class="form-control" value="${yieldScheme.principalYield }" readonly>
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目经理（%）<span class="required">※</span></label>
        <div class="col-md-8">
            <input type="text" name="pmRate" class="form-control twoProUser" value="${yieldScheme.pmRate }" data-rule-required="true">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目经理（产值）</label>
        <div class="col-md-8">
            <input type="text" name="pmYield" class="form-control" value="${yieldScheme.pmYield }" readonly>
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目秘书（%）<span class="required">※</span></label>
        <div class="col-md-8">
            <input type="text" name="secretRate" class="form-control twoProUser" value="${yieldScheme.secretRate }" data-rule-required="true">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目秘书（产值）</label>
        <div class="col-md-8">
            <input type="text" name="secretYield" class="form-control" value="${yieldScheme.secretYield }" readonly>
        </div>
    </div>
    <c:if test="${not empty majors }">
		<c:forEach items="${majors }" var="major" varStatus="vs">
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-3">${major.configName }产值</label>
                <div class="col-md-8">
                    <input type="text" name="stageMajorYield${vs.index }" data-majorcode="${major.configCode }" value="${yieldDuties[major.configCode].majorYield }" class="form-control eachMajorYield" readonly>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <div class="col-xs-12" style="padding:10px 0px;">
		<div class="row">
	        <div class="col-md-offset-5 col-md-7">
	            <button type="button" id="save-btn" class="btn blue">保存</button>
	            <button type="button" id="cancel-btn" class="btn default">取消</button>
	        </div>
        </div>
	</div>
</div>
