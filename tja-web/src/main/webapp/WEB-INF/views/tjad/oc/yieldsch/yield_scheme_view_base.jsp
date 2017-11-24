<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	<div class="col-xs-12">
		<h5 class="form-tit">基本信息<sec:authorize url="/admin/yield/scheme/ajax/baseEdit"><i class="fa fa-edit"/></sec:authorize></h5>
	</div>
	<div class="col-xs-6 ">
		<label class="control-label col-xs-4">策划编号</label>
		<div class="col-xs-8">
			<label class="control-label">${yieldScheme.schemeNo }</label>
		</div>
	</div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">更新日期</label>
        <div class="col-xs-8">
            <label class="control-label"><fmt:formatDate value='${yieldScheme.lastUpdate }' pattern='yyyy-MM-dd'/></label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">项目编号</label>
        <div class="col-xs-8">
            <label class="control-label">${project.proCode }</label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">项目名称</label>
        <div class="col-xs-8">
            <label class="control-label">${project.proName }</label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">项目类型</label>
        <div class="col-xs-8">
            <label class="control-label">${project.proType }</label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">项目级别</label>
        <div class="col-xs-8">
            <tags:config type="label" cssClass="control-label" code="${project.proGrade}"/>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">项目负责人</label>
        <div class="col-xs-8">
            <label class="control-label">${project.pmLeaders }</label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">项目经理</label>
        <div class="col-xs-8">
            <label class="control-label">${project.pManagers }</label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">用地面积（M<sup>2</sup>）</label>
        <div class="col-xs-8">
            <label class="control-label">${yieldScheme.landArea }</label>
        </div>
    </div>
    <div class="col-xs-6 ">
        <label class="control-label col-xs-4">策划依据</label>
        <div class="col-xs-8">
            <label class="control-label">${yieldScheme.schemeBasis }</label>
        </div>
    </div>
