<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="ym.week.menu.path" scope="request" />
	<title>项目周报-填报</title>
</head>
<body>
<div class="">
	<center>
		<h3>项目周报-${weekFill.periodName}</h3>
		<h6><fmt:formatDate pattern="yyyy/MM/dd" value="${weekFill.rangeStart}"/> ~ <fmt:formatDate pattern="yyyy/MM/dd" value="${weekFill.rangeEnd}"/></h6>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="saveForm">
			
			<input type="hidden" name="id" value="${weekFill.id}">
			<input type="hidden" name="proId" value="${weekFill.proId}">
			<input type="hidden" name="periodId" value="${weekFill.periodId}">
			<input type="hidden" name="view" value="${view}">
			<input type="hidden" name="approve" value="">
			<input type="hidden" name="auditStatus" value="${!empty weekFill.auditStatus ? weekFill.auditStatus : '0'}">
			<input type="hidden" name="procId" value="${weekFill.procId }">
			
			<div class="form-body clearfix" style="padding-bottom: 0">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">流水号</label>
					<div class="col-md-7">
						<c:set var="seqTip" value="(保存后自动生成)"></c:set>
						<input type="text" class="form-control" value="${empty weekFill.id ? seqTip : weekFill.seqNo}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4"></label>
					<div class="col-md-7 text-right">
						<c:if test="${weekFill.canDel}">
			                <input type="button" class="btn blue save" value="删除" onclick="save(9)">
			            </c:if> 
		                <c:if test="${empty weekFill.procId}">
		                    <input type="button" class="btn blue save" value="保存" onclick="save(0)">
		                    <input type="button" class="btn blue submit" value="提交" onclick="save(1)">
		                </c:if>
		                <c:if test="${not empty weekFill.procId and weekFill.auditStatus!='1' and weekFill.auditStatus!='2'}">
							<input type="button" class="btn blue save" value="重新提交" onclick="save(1)">
			           	</c:if>
					</div>
				</div>
			</div>
			
			<div class="form-body clearfix">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目编号</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${weekFill.proCode}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${weekFill.proName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
						<tags:config type="label" code="${weekFill.proType}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目级别</label>
					<div class="col-md-7">
						<tags:config type="label" code="${weekFill.proGrade}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">实际合同额(¥)</label>
					<div class="col-md-7">
						<input name="contractAmount" type="hidden" value="${weekFill.contractAmount}">
						<input type="text" class="form-control" value="${weekFill.contractAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">分包扣减(¥)</label>
					<div class="col-md-7">
						<input name="pkgAmount" type="hidden" value="${weekFill.pkgAmount}">
						<input type="text" class="form-control" value="${weekFill.pkgAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">方案扣减(¥)</label>
					<div class="col-md-7">
						<input name="schemeAmount" type="hidden" value="${weekFill.schemeAmount}">
						<input type="text" class="form-control" value="${weekFill.schemeAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">其他扣减(¥)</label>
					<div class="col-md-7">
						<input name="rebateAmount" type="hidden" value="${weekFill.rebateAmount}">
						<input type="text" class="form-control" value="${weekFill.rebateAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${weekFill.proFzrName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${weekFill.proJlName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">所处状态</label>
					<div class="col-md-7">
						<input name="itemStatus" value="${weekFill.proStatus}" type="hidden">
						<tags:config type="label" code="${weekFill.proStatus}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">所处阶段<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<tags:config type="select" name="phaseCode" otherAttr='data-rule-required="true"' cssClass="form-control" parentCode="YM.PHASESTATUS" selectCode="${weekFill.phaseCode}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">对应阶段启动时间</label>
					<div class="col-md-7">
						<input name="phaseStart" class="form-control datetimepicker" type="text" onblur="getDuration()"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${weekFill.phaseStart}" />' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">持续时间(天)</label>
					<div class="col-md-7">
						<input name="duration" type="hidden" value="${weekFill.duration}">
						<input name="duration" type="text" class="form-control" value="${weekFill.duration}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">当周进度(%)<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<input name="weekProgress" type="text" data-rule-required="true" data-rule-number="true" placeholder="0.00" class="form-control" onkeyup="getWeekYield()" value="${weekFill.weekProgress}">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">当周产值(¥)</label>
					<div class="col-md-7">
						<input name="weekYield" type="hidden" value="${weekFill.weekYield}">
						<input name="weekYield" class="form-control" type="text" value="${weekFill.weekYield}" disabled="disabled">
					</div>
				</div>
				
				<div class="form-group col-lg-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">当周工作及进展情况<span class="required">※</span></label>
					<div class="col-md-9 input-icon right" style="width: 78%;">
						<i class="fa"></i>
						<textarea name="weekEvolve" rows="3" data-rule-required="true" class="form-control">${weekFill.weekEvolve}</textarea>
					</div>
				</div>
				<div class="form-group col-lg-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">下阶段工作计划<span class="required">※</span></label>
					<div class="col-md-9 input-icon right" style="width: 78%;">
						<i class="fa"></i>
						<textarea name="workPlan" rows="3" data-rule-required="true" class="form-control">${weekFill.workPlan}</textarea>
					</div>
				</div>
				<div class="form-group col-lg-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">备案情况</label>
					<div class="col-md-9" style="width: 78%;">
						<textarea name="filing" rows="3" class="form-control">${weekFill.filing}</textarea>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">创建人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" 
						       value="${empty weekFill.creator ? SysUser.realName : weekFill.creator}" 
						       disabled="disabled"> 
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">创建时间</label>
					<div class="col-md-7">
						<input type="text" class="form-control" 
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${empty weekFill.createDate ? currentDate : weekFill.createDate}" />'
							   disabled="disabled"> 
					</div>
				</div>
				
    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div class="clearfix"></div>

<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${buildVersion}"></script>
<script type="text/javascript">
$(function(){
	// 初始化时间控件
	$(".datetimepicker").datetimepicker({
		format: "yyyy-mm-dd",
		minView: "month",
		todayBtn: 1,
	    autoclose: 1
	}).on("changeDate",function(ev){
		getDuration();
	});
});

Date.prototype.diff = function(date){
	if(!date instanceof Date) return 0;
	if(isNaN(date.getTime())) return 0;
	return (this.getTime() - date.getTime())/(24 * 60 * 60 * 1000);
};

function getDuration(){
	var phaseStart = new Date($("input[name='phaseStart']").val());
	var now = new Date();
	var diff = now.diff(phaseStart).toFixed(0)||0;
	$("input[name='duration']").val(diff);
}

function getWeekYield(){
	var weekProgress = parseFloat($("input[name='weekProgress']").val())||0.00;
	if(weekProgress < 0) $("input[name='weekProgress']").val(0.00);
	if(weekProgress > 100) $("input[name='weekProgress']").val(100.00);
	weekProgress = parseFloat($("input[name='weekProgress']").val())||0.00;
	
	var contractAmount = parseFloat($("input[name='contractAmount']").val())||0.00;
	var pkgAmount = parseFloat($("input[name='pkgAmount']").val())||0.00;
	var schemeAmount = parseFloat($("input[name='schemeAmount']").val())||0.00;
	var rebateAmount = parseFloat($("input[name='rebateAmount']").val())||0.00;
	
	var weekYieldCoe = "${weekYieldCoe}";
	var weekYield = ((contractAmount-pkgAmount-schemeAmount-rebateAmount)*weekYieldCoe*weekProgress/100).toFixed(2);
	$("input[name='weekYield']").val(weekYield);
}

function save(status){
    if(status == "9"){
    	$.jalert({"jatext":"确认删除？<br>点[确定]：执行删除操作<br>点[取消]：放弃删除操作", "jatype":"confirm", "onConfirm":function(){
        	ajaxSava(status);
        }});
    }else{
    	if($("#saveForm").valid()){
    		ajaxSava(status);
        }
    }
}

function ajaxSava(status){
	$("input[name='auditStatus']").val(status);
	var url ="${site}/admin/ym/weekFill/ajax/save";
	$.ajax({
		type : "post",
	 	url : url,
	 	async : false,
	 	data : $("#saveForm").serialize(),
	 	error : function(request) {
	 		$.jalert({"jatext":"Connection error"});
	 	},
	 	success : function(data) {
	 		if(data.flag == "true"){
	 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
	 				var backUrl = (status == "9" ? "${site}/admin/ym/weekFill/list" : "${site}/admin/ym/weekFill/toedit/"+data.id+"?proId=${weekFill.proId}&periodId=${weekFill.periodId}");
			  		window.location.href = backUrl;
	 			}});
	 		}else{
	 			$.jalert({"jatext":data.msg});
	 		}
	 	}
	});
}
</script>
</body>
</html>