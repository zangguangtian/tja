<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
 	<div class="col-xs-12">
		<h5 class="form-tit">基本信息<i class="fa fa-edit"></i></h5>
	</div>
	<div class="form-group col-lg-6 ">
	<label class="control-label col-md-3">策划编号</label>
		<div class="col-md-8">
			<input type="text" name="schemeNo" value="${yieldScheme.schemeNo }" class="form-control">
     	</div>
 	</div>
 	<div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">更新日期<span class="required">※</span></label>
        <div class="col-md-8">
            <input type="text" name="lastUpdate" value="<fmt:formatDate value='${yieldScheme.lastUpdate }' pattern='yyyy-MM-dd'/>" data-rule-required="true" class="form-control datetimepicker">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目编号</label>
        <div class="col-md-8">
            <input type="text" value="${project.proCode }" class="form-control" disabled="disabled">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目名称</label>
        <div class="col-md-8">
        	<input type="hidden" name="proId" value="${project.id }">
            <input type="text" value="${project.proName }" class="form-control" disabled="disabled">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目类型</label>
        <div class="col-md-8">
            <input type="text" value="${project.proType }" class="form-control" disabled="disabled">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目级别</label>
        <div class="col-md-8">
            <tags:config type="label" cssClass="form-control" code="${project.proGrade}"/>
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目负责人</label>
        <div class="col-md-8">
            <input type="text" value="${project.pmLeaders }" class="form-control" disabled="disabled">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">项目经理</label>
        <div class="col-md-8">
            <input type="text" value="${project.pManagers }" class="form-control" disabled="disabled">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">用地面积（M<sup>2</sup>）</label>
        <div class="col-md-8">
            <input type="text" name="landArea" value="${yieldScheme.landArea }" class="form-control text-right" placeholder="0.00" data-rule-number="true">
        </div>
    </div>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">策划依据</label>
        <div class="col-md-8">
            <input type="text" name="schemeBasis" value="${yieldScheme.schemeBasis }" class="form-control">
        </div>
    </div>
    <div class="col-xs-12" style="padding:10px 0px;">
		<div class="row">
	        <div class="col-md-offset-5 col-md-7">
	            <button type="button" id="save-btn" class="btn blue">保存</button>
	            <button type="button" id="cancel-btn" class="btn default">取消</button>
	        </div>
        </div>
	</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
$(function(){
    // 初始化时间控件
    $(".datetimepicker").datetimepicker({
        format: "yyyy-mm-dd",
        minView: "month",
        todayBtn: 1,
        autoclose: 1
    });
});
</script>
</body>
</html>
