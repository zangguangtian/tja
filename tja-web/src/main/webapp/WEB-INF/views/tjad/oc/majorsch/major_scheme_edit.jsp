<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>专业策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.major.scheme.menu.path" scope="request"  />
</head>
<body>
<div class="">
	<center>
		<h3>专业策划</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form action="" class="" id="majorSchForm">
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
						<tags:config type="label" cssClass="form-control" code="${planScheme.itemGrade}"></tags:config>
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
											<tr class="form-group">
												<td class="text-center col-lg-4">项目秘书</td>
												<td class="col-lg-8 input-icon left">
													<i class="fa"></i>
													<input name="projectExtend.secretRate" 
														   value="${project.projectExtend.secretRate}" 
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
												<td class="col-lg-8 text-right" id="rateSum">${project.projectExtend.principalRate + project.projectExtend.pmRate + project.projectExtend.secretRate}</td>
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
<script type="text/javascript">

</script>
</body>
</html>