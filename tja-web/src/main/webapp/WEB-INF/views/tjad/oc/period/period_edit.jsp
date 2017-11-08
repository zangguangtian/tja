<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="oc.period.menu.path" scope="request" />
	<title>期间管理-编辑</title>
</head>
<body>
<div class="">
	<center>
		<h3>期间管理</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form action="" class="" id="periodForm">
			<input type="hidden" name="id" value="${period.id}">
			<div class="form-body clearfix">
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">类型<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<tags:config name="typeCode" otherAttr='data-rule-required="true"' type="select" cssClass="form-control" parentCode="OC.PERIOD.TYPE" selectCode="${period.typeCode}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">期间<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<input name="periodName" data-rule-required="true" type="text" class="form-control" value="${period.periodName}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">期间范围-起始</label>
					<div class="col-md-7">
						<input name="rangeStart" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${period.rangeStart}"/>' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">期间范围-结束</label>
					<div class="col-md-7">
						<input name="rangeEnd" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${period.rangeEnd}"/>' >
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">说明<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<input name="explain" data-rule-required="true" type="text" class="form-control" value="${period.explain}">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">状态<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<tags:config name="statusCode" otherAttr='data-rule-required="true"' type="select" cssClass="form-control" parentCode="OC.PERIOD.STATUS" selectCode="${period.statusCode}"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">开始日期<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<input name="startDate" data-rule-required="true" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${period.startDate}"/>' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">结束日期<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<input name="endDate" data-rule-required="true" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${period.endDate}"/>' >
					</div>
				</div>
			</div>
				
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-lg-4">备注</label>
					<div class="col-lg-7">
						<textarea name="remark" class="form-control" rows="5">${period.remark}</textarea>
					</div>
				</div>
			</div>
				
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">创建人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${empty period.id ? SysUser.realName : period.creatorName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">创建时间</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${empty period.id ? currentDate : period.createDate}"/>' disabled="disabled">
					</div>
				</div>
			</div>
			<c:if test="${!empty period.id }">
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">更新人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${period.modifierName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">更新时间</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${period.modifyDate}"/>' disabled="disabled">
					</div>
				</div>
			</div>
			</c:if>
				
			<div class="">
			   	<div class="row">
			        <div class="col-md-offset-3 col-md-9">
			            <button type="button" class="btn blue" onclick="save()">保存</button>
			            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/oc/period/list' ">取消</button>
			        </div>
			   	</div>
			</div>
	
    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div class="clearfix"></div>

<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js"></script>
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

function save(){
	if (jQuery("#periodForm").valid()) {
		var url ="${site}/admin/oc/period/ajax/save";
		$.ajax({
			type : "post",
		 	url : url,
		 	data : $("#periodForm").serialize(),
		 	success : function(data) {
		 		if(data.flag == "true"){
		 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
				  		window.location.href="${site}/admin/oc/period/"+data.periodId;
		 			}});
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
		 	}
		});
	}
}

</script>
</body>
</html>