<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="pm.project.menu.path" scope="request" />
	<title>项目信息管理-编辑</title>
	<link href="/tja-web/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div class="">
	<center>
		<sec:authorize url="/admin/pm/project/edit">
			<input type="button" id="edit-btn" value="编辑" class="btn blue" style="float:right;position:absolute;right:55px;">
		</sec:authorize>
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
						<label class="control-label">${project.proCode}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<label class="control-label">${project.proName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
					    <label class="control-label">${project.proType}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目级别</label>
					<div class="col-md-7">
						<tags:config type="label" cssClass="control-label" code="${project.proGrade}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">合同编号</label>
					<div class="col-md-7">
						<label class="control-label">${project.contractCode}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">所处状态</label>
					<div class="col-md-7">
						<tags:config type="label" cssClass="control-label" code="${project.proStatus}"/>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">实际合同额(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${project.contractAmount}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">分包扣减(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${project.pkgAmount}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">方案扣减(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${project.schemeAmount}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">其他扣减(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${project.rebateAmount}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<label class="control-label">${project.pmLeaders}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<label class="control-label">${project.pManagers}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">承接部门</label>
					<div class="col-md-7">
						<label class="control-label">${project.dutyDeptName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">负责建筑师</label>
					<div class="col-md-7">
						<label class="control-label">${project.projectExtend.builderName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">总建筑面积(万平米)</label>
					<div class="col-md-7">
						<label class="control-label">${project.projectExtend.buildArea}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">策划产值(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${project.projectExtend.schemeYield}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">施工图开始时间</label>
					<div class="col-md-7">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.drawingStart}"/></label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">施工图(里程碑)完成时间</label>
					<div class="col-md-7">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.drawingEnd}"/></label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">开工时间</label>
					<div class="col-md-7">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.workingStart}"/></label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">(预计)竣工时间</label>
					<div class="col-md-7">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.workingEnd}"/></label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">本年工时成本(万元)</label>
					<div class="col-md-7">
						<label class="control-label">${project.projectExtend.timeCost}</label> 
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
												<th class="text-center">分配比例(%)</th>
											</tr>
										</thead>
										<tbody>
											<tr class="form-group">
												<td class="text-center col-lg-4">项目负责人</td>
												<td class="col-lg-8 text-right">${project.projectExtend.principalRate}</td>
											</tr>
											<tr class="form-group">
												<td class="text-center col-lg-4">项目经理</td>
												<td class="col-lg-8 text-right">${project.projectExtend.pmRate}</td>
											</tr>
											<tr class="form-group">
												<td class="text-center col-lg-4">项目秘书</td>
												<td class="col-lg-8 text-right">${project.projectExtend.secretRate}</td>
											</tr>
											<tr>
												<td class="text-center col-lg-4">合计</td>
												<td class="col-lg-8 text-right" id="rateSum">
													${project.projectExtend.principalRate + project.projectExtend.pmRate + project.projectExtend.secretRate}
												</td>
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
												<th class="text-center">分配比例(%)</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${project.majorRoleRateList}" var="rate" varStatus="s">
											<c:if test="${rate.allotCategory eq '1000'}">
												<tr class="form-group">
													<td class="text-center col-lg-4">${rate.allotName}</td>
													<td class="col-lg-8 text-right">${rate.allotRate}</td>
												</tr>
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
											<c:forEach items="${project.majorRoleRateList}" var="rate" varStatus="s">
											<c:if test="${rate.allotCategory eq '2000'}">
												<tr>
													<td class="text-center col-lg-4">${rate.allotName}</td>
													<td class="col-lg-8 text-right">${rate.allotRate}</td>
												</tr>
											<c:set var="rateSum" value="${rateSum + rate.allotRate}"/>
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
	
    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div class="clearfix"></div>

<script type="text/javascript">
$(function(){
	$("#edit-btn").on("click", function(){
		window.location.href = "${site}/admin/pm/project/edit/${project.id}";
	})
});
</script>
</body>
</html>