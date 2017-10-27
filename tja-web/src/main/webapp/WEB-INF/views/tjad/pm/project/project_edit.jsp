<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="pm.project.menu.path" scope="request" />
	<title>项目信息管理-编辑</title>
	<link href="/tja-web/resources/css/management.css?v=1509003163334" rel="Stylesheet" type="text/css">
</head>
<body>
<div class="">
	<center>
		<h3>项目信息管理</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form action="" class="" id="projectForm">
			<input type="hidden" name="id" value="${project.id}">
			<input type="hidden" name="projectExtend.id" value="${project.projectExtend.id}">
			<div class="form-body clearfix">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目编号</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.proCode}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.proName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
					    <input type="text" class="form-control" value="${project.proType}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目级别</label>
					<div class="col-md-7">
						<tags:config type="select" name="proGrade" cssClass="form-control" parentCode="PM.GRADE" selectCode="${project.proGrade}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">合同编号</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.contractCode}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">所处状态</label>
					<div class="col-md-7">
						<tags:config type="select" name="proStatus" cssClass="form-control" parentCode="PM.STATUS" selectCode="${project.proStatus}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">实际合同额(¥)</label>
					<div class="col-md-7">
						<input type="text" class="form-control text-right" value="${project.contractAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">分包扣减(¥)</label>
					<div class="col-md-7">
						<input type="text" class="form-control text-right" value="${project.pkgAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">方案扣减(¥)</label>
					<div class="col-md-7">
						<input type="text" class="form-control text-right" value="${project.schemeAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">其他扣减(¥)</label>
					<div class="col-md-7">
						<input type="text" class="form-control text-right" value="${project.rebateAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.pmLeaders}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.pManagers}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">承接部门</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.dutyDeptName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">负责建筑师</label>
					<div class="col-md-7">
						<input name="projectExtend.builderId" type="hidden" value="${project.projectExtend.builderId}">
						<input name="projectExtend.builderName" type="text" class="form-control" value="${project.projectExtend.builderName}" style="float:left;" readonly>
				      	<a id="selectBuilder" title="选择" href="javascript:void(0);" class="icon-select"></a>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">总建筑面积(万平米)</label>
					<div class="col-md-7">
						<input type="text" class="form-control text-right" value="${project.projectExtend.buildArea}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">策划产值(¥)</label>
					<div class="col-md-7">
						<input type="text" class="form-control text-right" value="${project.projectExtend.schemeYield}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">施工图开始时间</label>
					<div class="col-md-7">
						<input name="projectExtend.drawingStart" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.drawingStart}"/>' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">施工图(里程碑)完成时间</label>
					<div class="col-md-7">
						<input name="projectExtend.drawingEnd" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.drawingEnd}"/>' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">开工时间</label>
					<div class="col-md-7">
						<input name="projectExtend.workingStart" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.workingStart}"/>' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">(预计)竣工时间</label>
					<div class="col-md-7">
						<input name="projectExtend.workingEnd" class="form-control datetimepicker" type="text"
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.workingEnd}"/>' >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">本年工时成本(万元)</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${project.projectExtend.timeCost}" disabled="disabled"> 
					</div>
				</div>

				<div class="col-md-12">
					<div class="tabbable-line boxless tabbable-reversed">
				    	<ul class="nav nav-tabs">
				        	<li class="active">
				            	<a href="#tab_0" data-toggle="tab" aria-expanded="true">项目角色</a>
				            </li>
				            <li class="">
				                <a href="#tab_1" data-toggle="tab" aria-expanded="false">专业角色</a>
				            </li>
				            <li class="">
				                <a href="#tab_2" data-toggle="tab" aria-expanded="false">专业比例</a>
				        	</li>
				        </ul>
				       	<div class="tab-content">
				       		<!-- 项目角色 -->
				            <div class="tab-pane active" id="tab_0">
				            	<div class="col-lg-5 ">
									<table class="table table-bordered edit">
										<thead>
											<tr class="form-group">
												<th class="text-center col-lg-4">角色</th>
												<th class="text-center">分配比例(%)<span class="required">※</span></th>
											</tr>
										</thead>
										<tbody>
											<tr class="form-group">
												<td class="text-center col-lg-4">项目负责人</td>
												<td class="col-lg-8 input-icon left">
													<i class="fa"></i>
													<input name="projectExtend.principalRate" 
														   value="${project.projectExtend.principalRate}" 
														   onkeyup="rateAdd()" 
														   type="text" 
														   placeholder="0.00" 
														   data-rule-required="true" 
														   data-rule-number="true" 
														   data-rule-max="100" 
														   data-rule-min="0" 
														   class="text-right">
												</td>
											</tr>
											<tr class="form-group">
												<td class="text-center col-lg-4">项目经理</td>
												<td class="col-lg-8 input-icon left">
													<i class="fa"></i>
													<input name="projectExtend.pmRate" 
														   value="${project.projectExtend.pmRate}" 
														   onkeyup="rateAdd()" 
														   type="text" 
														   placeholder="0.00" 
														   data-rule-required="true" 
														   data-rule-number="true" 
														   data-rule-max="100" 
														   data-rule-min="0" 
														   class="text-right">
												</td>
											</tr>
											<tr>
												<td class="text-center col-lg-4">合计</td>
												<td class="col-lg-8 text-right" id="rateSum">${project.projectExtend.principalRate + project.projectExtend.pmRate}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-lg-1"></div>
							</div>
				       		<!-- 专业角色 -->
				       		<div class="tab-pane" id="tab_1">
				       			<div class="col-lg-5 ">
									<table class="table table-bordered edit">
										<thead>
											<tr class="form-group">
												<th class="text-center col-lg-4">角色</th>
												<th class="text-center">分配比例(%)<span class="required">※</span></th>
											</tr>
										</thead>
										<tbody>
											<c:set var="rateIndex" value="0"/>
											<c:forEach items="${project.majorRoleRateList }" var="rate" varStatus="s">
											<c:if test="${rate.allotCategory eq '1000'}">
												<tr class="form-group">
													<td class="text-center col-lg-4">${rate.allotName }</td>
													<td class="col-lg-8 input-icon left">
														<i class="fa"></i>
														<input name="majorRoleRateList[${rateIndex}].id" type="hidden" value="${rate.id }">
														<input name="majorRoleRateList[${rateIndex}].proId" type="hidden" value="${project.id }">
														<input name="majorRoleRateList[${rateIndex}].allotCategory" type="hidden" value="1000">
														<input name="majorRoleRateList[${rateIndex}].allotCode" type="hidden" value="${rate.allotCode }">
														<input name="majorRoleRateList[${rateIndex}].allotRate" 
															   value="${rate.allotRate }" 
															   type="text" 
															   placeholder="0.00" 
															   data-rule-required="true" 
															   data-rule-number="true" 
															   data-rule-max="100" 
														   	   data-rule-min="0" 
															   class="text-right">
													</td>
												</tr>
												<c:set var="rateIndex" value="${rateIndex+1}"/>
											</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="col-lg-1"></div>
							</div>
				       		<!-- 专业比例 -->
				       		<div class="tab-pane" id="tab_2">
				       			<div class="col-lg-5 ">
									<table class="table table-bordered edit">
										<thead>
											<tr>
												<th class="text-center col-lg-4">专业</th>
												<th class="text-center">分配比例(%)</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="rateSum" value="0"/>
											<c:forEach items="${project.majorRoleRateList }" var="rate" varStatus="s">
											<c:if test="${rate.allotCategory eq '2000'}">
												<tr>
													<td class="text-center col-lg-4">${rate.allotName }</td>
													<td class="col-lg-8 text-right">${rate.allotRate }</td>
												</tr>
											<c:set var="rateSum" value="${rateSum + rate.allotRate }"/>
											</c:if>
											</c:forEach>
												<tr>
													<td class="text-center col-lg-4">合计</td>
													<td class="col-lg-8 text-right">${rateSum }</td>
												</tr>
										</tbody>
									</table>
								</div>
								<div class="col-lg-1"></div>
							</div>
						</div>
			   		</div>
			   	</div>
		
				<div class="">
				   	<div class="row">
				        <div class="col-md-offset-3 col-md-9">
				            <button type="button" class="btn blue" onclick="save()">保存</button>
				            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/pm/project/list' ">取消</button>
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

<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
$(function(){
	// 初始化时间控件
	$(".datetimepicker").datetimepicker({
		format: "yyyy-mm-dd",
		minView: "month",
		todayBtn: 1,
	    autoclose: 1
	});
	// 选择负责建筑师
	$("#selectBuilder").on("click",function(){
		selectStaff(selectStaffBack,"radio");
	});
});

function rateAdd(){
	var principalRate = parseFloat($("input[name='projectExtend.principalRate']").val())*100||0.00;
	var pmRate = parseFloat($("input[name='projectExtend.pmRate']").val())*100||0.00;
	var sum = ((principalRate + pmRate)/100).toFixed(2);
	$("#rateSum").text(sum);
}

function selectStaffBack(data){
	console.log(data);
	$("input[name='projectExtend.builderId']").val(data[0].id);
	$("input[name='projectExtend.builderName']").val(data[0].name);
}

function save(){
	if (jQuery("#projectForm").valid()) {
		if (parseFloat($("#rateSum").text()) > 100) {
			$.jalert({"jatext":"项目角色分配比例不能超过100"});
			return;
		}
		
		var allotCodeList = $("input[name$='.allotCode']");
		var allotRateList = $("input[name$='.allotRate']");
		var rateObj = {};
	 	for (var i = 0; i < allotCodeList.length; i++) {
	 		rateObj[allotCodeList[i].value] = parseFloat(allotRateList[i].value);
		}
	 	if(rateObj["PM.MAJORROLE.LEADER"] > 100){
	 		$.jalert({"jatext":"专业负责人分配比例不能超过100"});
			return;
	 	}
	 	if((rateObj["PM.MAJORROLE.CHECKER"]+rateObj["PM.MAJORROLE.APPROVER"]+rateObj["PM.MAJORROLE.DESIGNER"]) > 100){
	 		$.jalert({"jatext":"校对人+审核人+设计人/制图人 分配比例不能超过100"});
			return;
	 	}
	
		var url ="${site}/admin/pm/project/ajax/save";
		$.ajax({
			type : "post",
		 	url : url,
		 	data : $("#projectForm").serialize(),
		 	error : function(request) {
		 		$.jalert({"jatext":"Connection error"});
		 	},
		 	success : function(data) {
		 		if(data.flag == "true"){
		 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
				  		window.location.href="${site}/admin/pm/project/edit/${project.id}";
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