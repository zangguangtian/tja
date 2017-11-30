<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>年度产值结算</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="ym.settle.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
	<center>
		<h3>年度产值结算-${periodManage.periodName}</h3>
	</center>
	<div class="">
		<div class="form">
            <div class="row">
                <div class="col-lg-3" style="text-align:center;margin-top:17px;padding-right:30px;">
                                                     流水号:${yieldSettle.seqNo}
                </div>
                <div class="col-lg-9 text-right">
	                <c:if test="${empty yieldSettle.procId }">
	                    <input type="button" value="保存" class="btn blue save" onclick="save(0)">
	                    <input type="button" value="提交" class="btn blue submit" onclick="save(1)">
	                </c:if>
	                <c:if test="${not empty yieldSettle.procId }">
						<input class="btn blue save" type="button" value="重新提交" onclick="save(1)"/>
		           	</c:if>
                    <c:if test="${yieldSettle.canDel }">
                        <input class="btn blue save" type="button" value="删除" onclick="save(9)"/>
                    </c:if> 
                </div>
            </div>
			<!-- BEGIN FORM-->
			<form id="saveForm" action="#" class="row">
			    <input type="hidden" name="wfYieldSettle.id" value="${yieldSettle.id}">
			    <input type="hidden" name="wfYieldSettle.auditStatus" value="${yieldSettle.auditStatus}"/>
			    <input type="hidden" name="wfYieldSettle.procId" value="${yieldSettle.procId}"/>
				<input type="hidden" name="wfYieldSettle.proId" value="${project.id}">
				<input type="hidden" name="wfYieldSettle.periodId" value="${periodManage.id}">
				<input type="hidden" name="wfYieldSettle.wfCategory" value="1000">
				<input type="hidden" name="wfYieldSettle.permitId" value="${permitId}">
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
						    <input type="hidden" name="wfYieldSettle.proCode" class="form-control col-md-3" value="${project.proCode}">
						    <label class="control-label">${project.proCode}</label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="hidden" name="wfYieldSettle.proName" class="form-control" value="${project.proName}">
						    <label class="control-label">${project.proName}</label>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同编号</label>
						<div class="col-md-8">
							<input type="hidden" name="wfYieldSettle.contractCode" class="form-control" value="${project.contractCode}">
						    <label class="control-label">${project.contractCode}</label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="hidden" name="wfYieldSettle.proType" class="form-control" value="${project.proType}">
							<label class="control-label">${project.proType}</label>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同额(¥)</label>
						<div class="col-md-8">
					            <input type="hidden" class="form-control" name="wfYieldSettle.contractAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.contractAmount?project.contractAmount:yieldSettle.contractAmount}' pattern='#,#00.00#'/>">
						        <label class="control-label"><fmt:formatNumber value='${empty yieldSettle.contractAmount?project.contractAmount:yieldSettle.contractAmount}' pattern='#,#00.00#'/></label>
					    
					    </div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">分包扣减(¥)</label>
						<div class="col-md-8">
								<input type="hidden" class="form-control" name="wfYieldSettle.pkgAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.pkgAmount?project.pkgAmount:yieldSettle.pkgAmount}' pattern='#,#00.00#'/>">
						        <label class="control-label"><fmt:formatNumber value='${empty yieldSettle.pkgAmount?project.pkgAmount:yieldSettle.pkgAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案扣减(¥)</label>
						<div class="col-md-8">
								<input type="hidden" class="form-control" name="wfYieldSettle.schemeAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.schemeAmount?project.schemeAmount:yieldSettle.schemeAmount}' pattern='#,#00.00#'/>">
						        <label class="control-label"><fmt:formatNumber value='${empty yieldSettle.schemeAmount?project.schemeAmount:yieldSettle.schemeAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">其他扣减(¥)</label>
						<div class="col-md-8">
								<input type="hidden" class="form-control" name="wfYieldSettle.rebateAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.rebateAmount?project.rebateAmount:yieldSettle.rebateAmount}' pattern='#,#00.00#'/>">
						        <label class="control-label"><fmt:formatNumber value='${empty yieldSettle.rebateAmount?project.rebateAmount:yieldSettle.rebateAmount}' pattern='#,#00.00#'/></label>
							</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8">
						    <input type="hidden" class="form-control" name="wfYieldSettle.pManagers" value="${project.pmLeaders}">
						    <label class="control-label">${project.pmLeaders}</label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8">
						    <input type="hidden" name="wfYieldSettle.pManagers" class="form-control col-md-3" value="${project.pManagers}">
			            	<label class="control-label">${project.pManagers}</label>
						</div>
					</div>
					
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">当年可结算产值(¥)</label>
						<div class="col-md-8">
						   <input type="hidden" class="form-control" name="wfYieldSettle.yearYield"
							value="<fmt:formatNumber value='${empty yieldSettle.yearYield ? project.yield : yieldSettle.yearYield}' pattern='#,#00.00#'/>">
						    <label class="control-label"><fmt:formatNumber value='${empty yieldSettle.yearYield ? project.yield : yieldSettle.yearYield}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">历年已结算产值(¥)</label>
						<div class="col-md-8">
						     <input type="hidden" class="form-control" name="wfYieldSettle.hisyearYield"
									value="<fmt:formatNumber value='${empty yieldSettle.hisyearYield ? hisyearYield : yieldSettle.hisyearYield}' pattern='#,#00.00#'/>">
						    <label class="control-label"><fmt:formatNumber value='${empty yieldSettle.hisyearYield ? hisyearYield : yieldSettle.hisyearYield}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">所处状态<span class="required">※</span></label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
						    <tags:config type="select" otherAttr="data-rule-required='true'" cssClass="form-control" selectCode="${empty yieldSettle.itemStatus ? project.proStatus : yieldSettle.itemStatus}" parentCode="PM.STATUS" name="wfYieldSettle.itemStatus"></tags:config>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3"></label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">创建人</label>
						<div class="col-md-8 input-icon right">
						<input type="hidden" name="wfYieldSettle.creator" value="${empty yieldSettle.creator  ? SysUser.id : yieldSettle.creator}"/>
						<label class="control-label">${empty yieldSettle.creatorName ? SysUser.realName : yieldSettle.creatorName}</label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">创建时间</label>
						<div class="col-md-8 input-icon right">
						 <jsp:useBean id="currentDate" class="java.util.Date"/>
	            		 <fmt:formatDate value="${empty yieldSettle.createDate? currentDate : yieldSettle.createDate}" pattern="yyyy-MM-dd" var="currentDate"/>
						 <input type="hidden" name="wfYieldSettle.createDate" value='${currentDate}' />
						 <label class="control-label">${currentDate }</label>
						</div>
					</div>
					

					<h3 class="form-tit col-lg-12">产值分配</h3>
					<div class="col-lg-12 form-group">
						<div class="col-lg-6 ">
							<div class="row clearfix">
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4">项目负责人</div>
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4 text-right">
							      <label>比例：</label>
							      <input type="text" class="ta_input keyupRate" name="wfYieldSettle.principalRate"  data-rule-number="true"  placeholder="0.00" value="${empty yieldSettle.principalRate?projectExtend.principalRate : yieldSettle.principalRate}" style="display: inline-block;">
								</div>
								<div class="col-lg-4 text-right  col-md-4 col-sm-4 col-xs-4">
									<input type="button" class="btn blue btn_leader" value="添加"> 
								</div>
							</div>
							
							<table class="table table-bordered" id="leader">
								<thead>
									<tr>
										<th class="text-center col-lg-4">姓名</th>
										<th class="text-center">工作量(%)<span class="required">※</span></th>
										<th class="text-center">产值</th>
									</tr>
								</thead>
								<tbody>
								 <c:set var="size" value="0"></c:set>
								 <c:set var="total_staffYield" value="0"></c:set>
								 <c:set var="total_staffRate" value="0"></c:set>
								 <c:if test="${not empty proLeaders}">
								   <c:forEach items="${proLeaders}" var="leader" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">${leader.staffName}</td>
											<td  class="col-lg-4 text-right input-icon left">
											  <i class="fa"></i>
											  <input type="hidden" name="principalAllots[${size}].id" value="${leader.id}">
											  <input type="hidden" name="principalAllots[${size}].wfId" value="${leader.wfId}">
											  <input type="hidden" name="principalAllots[${size}].staffCategory" value="${leader.staffCategory}">
											  <input type="hidden" name="principalAllots[${size}].staffSort" value="${leader.staffSort}">
											  <input type="hidden" name="principalAllots[${size}].staffId" value="${leader.staffId}">
											  <input type="hidden" name="principalAllots[${size}].staffYield" value="${leader.staffYield}">
											  <input type="text" class="form-control text-right twoNum"
														name="principalAllots[${size}].staffRate"
														value="${leader.staffRate}"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true">
													</td>
											<td  class="col-lg-4 text-right">
											 <fmt:formatNumber value='${leader.staffYield}' pattern='#,#00.00#'/>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
										<c:set var="total_staffYield" value="${total_staffYield + leader.staffYield}"></c:set>
										<c:set var="total_staffRate" value="${total_staffRate + leader.staffRate}"></c:set>
								   </c:forEach>
								  </c:if>
								  
								   <c:if test="${empty proLeaders and not empty leaders}">
								   <c:forEach items="${leaders}" var="leader" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">${leader.name}</td>
											<td  class="col-lg-4 text-right input-icon left">
											  <i class="fa"></i>
											  <input type="hidden" name="principalAllots[${size}].id" value="">
											  <input type="hidden" name="principalAllots[${size}].wfId" value="">
											  <input type="hidden" name="principalAllots[${size}].staffCategory" value="${categoryLeader}">
											  <input type="hidden" name="principalAllots[${size}].staffSort" value="${st.index}">
											  <input type="hidden" name="principalAllots[${size}].staffId" value="${leader.staffId}">
											  <input type="hidden" name="principalAllots[${size}].staffYield" value="">
											  <input type="text" class="form-control text-right twoNum"
														name="principalAllots[${size}].staffRate" 
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="">
													</td>
											<td  class="col-lg-4 text-right"></td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
								   </c:forEach>
								  </c:if>
									<tr class="total">
										<td  class="text-center col-lg-4">合计</td>
										<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_staffRate}' pattern='#,#00.00#'/></td>
										<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_staffYield}' pattern='#,#00.00#'/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="col-lg-6 ">
							<div class="row">
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4">项目经理</div>
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4 text-right">
							      <label>比例：</label>
							      <input type="text" class="ta_input keyupRate" name="wfYieldSettle.pmRate"  data-rule-number="true"  placeholder="0.00" value="${empty yieldSettle.pmRate ?projectExtend.pmRate : yieldSettle.pmRate}" style="display: inline-block;">
								</div>
								<div class="col-lg-3 text-right  col-md-4 col-sm-4 col-xs-4">
									<input type="button" class="btn blue btn_pm" value="添加"> 
								</div>
							</div>
							<table class="table table-bordered" id="pm">
								<thead>
									<tr>
										<th  class="text-center col-lg-4">姓名</th>
										<th class="text-center">工作量(%)<span class="required">※</span></th>
										<th class="text-center">产值</th>
									</tr>
								</thead>
								<tbody>
								<c:set var="total_manager_rate" value="0"></c:set>
								<c:set var="total_manager_yield" value="0"></c:set>
								 <c:if test="${not empty proManagers}">
									  <c:forEach items="${proManagers}" var="manager">
									    <tr>
											<td  class="text-center col-lg-4">${manager.staffName}</td>
											<td  class="col-lg-4 text-right input-icon left">
											  <i class="fa"></i>
											  <input type="hidden" name="principalAllots[${size}].id" value="${manager.id}">
											  <input type="hidden" name="principalAllots[${size}].wfId" value="${manager.wfId}">
											  <input type="hidden" name="principalAllots[${size}].staffCategory" value="${manager.staffCategory}">
											  <input type="hidden" name="principalAllots[${size}].staffSort" value="${manager.staffSort}">
											  <input type="hidden" name="principalAllots[${size}].staffId" value="${manager.staffId}">
											  <input type="hidden" name="principalAllots[${size}].staffYield" value="${manager.staffYield}">
											  <input type="text" class="form-control text-right twoNum"
														name="principalAllots[${size}].staffRate"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="${manager.staffRate}">
													</td>
											<td  class="col-lg-4 text-right">
											 <fmt:formatNumber value='${manager.staffYield}' pattern='#,#00.00#'/>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
										<c:set var="total_manager_rate" value="${total_manager_rate + manager.staffRate}"></c:set>
								        <c:set var="total_manager_yield" value="${total_manager_yield + manager.staffYield}"></c:set>
									 </c:forEach>
								 </c:if>
								 <c:if test="${empty proManagers and not empty proPms}">
									  <c:forEach items="${proPms}" var="proPm">
									    <tr>
											<td  class="text-center col-lg-4">${proPm.name}</td>
											<td  class="col-lg-4 text-right input-icon left">
											  <i class="fa"></i>
											  <input type="hidden" name="principalAllots[${size}].id" value="">
											  <input type="hidden" name="principalAllots[${size}].wfId" value="">
											  <input type="hidden" name="principalAllots[${size}].staffCategory" value="${categoryPm}">
											  <input type="hidden" name="principalAllots[${size}].staffSort" value="${st.index}">
											  <input type="hidden" name="principalAllots[${size}].staffId" value="${proPm.id}">
											  <input type="hidden" name="principalAllots[${size}].staffYield" value="">
											  <input type="text" class="form-control text-right twoNum"
														name="principalAllots[${size}].staffRate"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="">
													</td>
											<td  class="col-lg-4 text-right"></td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
									 </c:forEach>
								 </c:if>
								 <tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_manager_rate}' pattern='#,#00.00#'/></td>
									<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_manager_yield}' pattern='#,#00.00#'/></td>
								 </tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="col-lg-12 form-group">
						<div class="col-lg-6 ">
							<div class="row clearfix">
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4">项目秘书</div>
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4 text-right">
							      <label>比例：</label>
							      <input type="text" class="ta_input keyupRate" name="wfYieldSettle.secretRate"  data-rule-number="true"  placeholder="0.00" value="${empty yieldSettle.secretRate?projectExtend.secretRate : yieldSettle.secretRate}" style="display: inline-block;">
								</div>
								<div class="col-lg-4 text-right  col-md-4 col-sm-4 col-xs-4">
									<input type="button" class="btn blue btn_secret" value="添加"> 
								</div>
							</div>
							
							<table class="table table-bordered" id="secretary">
								<thead>
									<tr>
										<th class="text-center col-lg-4">姓名</th>
										<th class="text-center">工作量(%)<span class="required">※</span></th>
										<th class="text-center">产值</th>
									</tr>
								</thead>
								<tbody>
								 <c:set var="total_secret_yield" value="0"></c:set>
								 <c:set var="total_secret_rate" value="0"></c:set>
								 <c:if test="${not empty proSecretarys}">
								   <c:forEach items="${proSecretarys}" var="proSecretary" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">${proSecretary.staffName}</td>
											<td  class="col-lg-4 text-right input-icon left">
											  <i class="fa"></i>
											  <input type="hidden" name="principalAllots[${size}].id" value="${proSecretary.id}">
											  <input type="hidden" name="principalAllots[${size}].wfId" value="${proSecretary.wfId}">
											  <input type="hidden" name="principalAllots[${size}].staffCategory" value="${proSecretary.staffCategory}">
											  <input type="hidden" name="principalAllots[${size}].staffSort" value="${proSecretary.staffSort}">
											  <input type="hidden" name="principalAllots[${size}].staffId" value="${proSecretary.staffId}">
											  <input type="hidden" name="principalAllots[${size}].staffYield" value="${proSecretary.staffYield}">
											  <input type="text" class="form-control text-right twoNum"
														name="principalAllots[${size}].staffRate"
														value="${proSecretary.staffRate}"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true">
													</td>
											<td  class="col-lg-4 text-right">
											 <fmt:formatNumber value='${proSecretary.staffYield}' pattern='#,#00.00#'/>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
										<c:set var="total_secret_yield" value="${total_secret_yield + proSecretary.staffYield}"></c:set>
										<c:set var="total_secret_rate" value="${total_secret_rate + proSecretary.staffRate}"></c:set>
								   </c:forEach>
								  </c:if>
								  
								   <c:if test="${empty proSecretarys and not empty secretarys}">
								   <c:forEach items="${secretarys}" var="secretary" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">${secretarys.name}</td>
											<td  class="col-lg-4 text-right input-icon left">
											  <i class="fa"></i>
											  <input type="hidden" name="principalAllots[${size}].id" value="">
											  <input type="hidden" name="principalAllots[${size}].wfId" value="">
											  <input type="hidden" name="principalAllots[${size}].staffCategory" value="${categorySecret}">
											  <input type="hidden" name="principalAllots[${size}].staffSort" value="${st.index}">
											  <input type="hidden" name="principalAllots[${size}].staffId" value="${secretarys.staffId}">
											  <input type="hidden" name="principalAllots[${size}].staffYield" value="">
											  <input type="text" class="form-control text-right twoNum"
														name="principalAllots[${size}].staffRate" 
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="">
													</td>
											<td  class="col-lg-4 text-right"></td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
								   </c:forEach>
								  </c:if>
									<tr class="total">
										<td  class="text-center col-lg-4">合计</td>
										<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_secret_rate}' pattern='#,#00.00#'/></td>
										<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_secret_yield}' pattern='#,#00.00#'/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="col-lg-12 form-group">
						<div class="col-lg-5 ">
						    <caption>当年专业结算比例</caption>
							<table class="table table-bordered" id="majorSettleRate">
								<thead>
									<tr>
										<th  class="text-center col-lg-4">专业</th>
										<th class="text-center">结算比例(%)<span class="required">※</span></th>
									</tr>
								</thead>
								<tbody>
								<c:set var="total_major_rate" value="0"></c:set>
								<c:if test="${not empty majorRates}">
									  <c:forEach items="${majorRates}" var="majorRate" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">
											  ${majorRate.majorName}
											</td>
											<td  class="col-lg-8 text-right input-icon left">
											   <i class="fa"></i>
											   <input type="hidden" name="majorRates[${st.index}].id" value="${majorRate.id}">
											   <input type="hidden" name="majorRates[${st.index}].wfId" value="${majorRate.wfId}">
											   <input type="hidden" name="majorRates[${st.index}].majorCode" value="${majorRate.majorCode}">
											   <input type="hidden" name="majorRates[${st.index}].majorSort" value="${majorRate.majorSort}">
											   <input type="text" class="form-control text-right twoNum"
														name="majorRates[${st.index}].settleRate"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="${majorRate.settleRate}">
													</td>
										</tr>
										<c:set var="total_major_rate" value="${total_major_rate + majorRate.settleRate}"></c:set>
									  </c:forEach>
								  </c:if>
								  
								 <c:if test="${empty majorRates and not empty majorRateConfigs}">
									  <c:forEach items="${majorRateConfigs}" var="majorRateConfig" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">
											  ${majorRateConfig.majorName}
											</td>
											<td  class="col-lg-8 text-right input-icon left">
											   <i class="fa"></i>
											   <input type="hidden" name="majorRates[${st.index}].id" value="">
											   <input type="hidden" name="majorRates[${st.index}].wfId" value="">
											   <input type="hidden" name="majorRates[${st.index}].majorCode" value="${majorRateConfig.majorCode}">
											   <input type="hidden" name="majorRates[${st.index}].majorSort" value="${st.index}">
											   <input type="text" class="form-control text-right twoNum"
														name="majorRates[${st.index}].settleRate"
														data-rule-number="true"
														placeholder="0.00" 
														data-rule-max="100"
														data-rule-min="0" 
														data-rule-required="true"
														value="${majorRateConfig.settleRate}">
													</td>
										</tr>
										<c:set var="total_major_rate" value="${total_major_rate + config.settleRate}"></c:set>
									  </c:forEach>
								  </c:if>
								  
									<tr class="total">
										<td  class="text-center col-lg-4">合计</td>
										<td  class="col-lg-8 text-right">${total_major_rate}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</form>
			<!-- END FORM-->
			<table id="clone_text" style="display: none">
			 <tr>
				<td  class="text-center col-lg-4"></td>
				<td  class="col-lg-4 text-right input-icon left">
				  <i class="fa"></i>
				  <input type="hidden" name="principalAllots[{0}].id" value="">
				  <input type="hidden" name="principalAllots[{0}].wfId" value="">
				  <input type="hidden" name="principalAllots[{0}].staffCategory" value="">
				  <input type="hidden" name="principalAllots[{0}].staffSort" value="">
				  <input type="hidden" name="principalAllots[{0}].staffId" value="">
				  <input type="hidden" name="principalAllots[{0}].staffYield" value="">
				  <input type="text" class="form-control text-right twoNum"
							name="principalAllots[{0}].staffRate" 
							data-rule-number="true" 
						    data-rule-max="100"
						    data-rule-min="0" 
						    placeholder="0.00"
						    data-rule-required="true"
							value="">
						</td>
				<td  class="col-lg-4 text-right">
				</td>
			 </tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
    var tIndex = ${size};
    var leaderCate = "${categoryLeader}";
    var pmCate = "${categoryPm}";
    var secretCate = "${categorySecret}";
	$(function(){
		
		jQuery(document).on("keyup","input[name$='staffRate']",function(){
			countOutputValue($(this).closest("tr"));
		});
		
		jQuery(document).on("keyup","input.keyupRate",function(){
			countOutputValue($(this).parent().parent().next().find("tbody tr:not(:last)"));
		});
		
		jQuery(document).on("keyup","#majorSettleRate tbody input[name$='settleRate']",function(){
			totalMajor();
		});
		
		jQuery(".btn_leader").on("click",function(){
			selectStaff(selectLeader,'checkbox');
		});
		
		jQuery(".btn_pm").on("click",function(){
			selectStaff(selectPm,'checkbox');
		});
		
		jQuery(".btn_secret").on("click",function(){
			selectStaff(selectSecret,'checkbox');
		});
	});

	//计算产值
	function countOutputValue(_tr){
		  if(_tr.size()>0){
			  jQuery.each(_tr,function(index,item){
				  var $item = $(item);
				  var staffRate = $item.find("input[name$='staffRate']").val();
				  /* if(staffRate!='' && staffRate.substring(staffRate.lastIndexOf("."),staffRate.length-1).length>2){
					  $item.find("input[name$='staffRate']").val(new Number(staffRate).toFixed(2));
				  } */
				  //项目负责人 项目经理 比例
				  var rate = $item.closest("table").prev().find("input[name^='wfYieldSettle']").val();
				  /* if(rate!='' && rate.substring(rate.lastIndexOf("."),rate.length-1).length>2){
					  $item.closest("table").prev().find("input[name^='wfYieldSettle']").val(new Number(rate).toFixed(2));
				  } */
				  
				  //当年可结算产值
				  var yearYield =getNumValue(delcommafy(jQuery("input[name$='.yearYield']").val()));
				  //=当年可结算产值×比例×工作量/10000
				  var staffYield =new Number(yearYield) * new Number(rate) * new Number(staffRate)/10000;
				  var _td = $item.find("td:last");
				  _td.text(toThousands(new Number(staffYield).toFixed(2)));
				  $item.find("input[name$='staffYield']").val(new Number(staffYield).toFixed(2));
				  if(index == _tr.size()-1){
					 initTotal($item.find("input[name$='staffRate']"));  
				  }
			  });
		  }
	}
	
	//合计
	function initTotal(_this){
		var flag = false;
		var jQObj = new Array();
		if(_this == null || typeof _this == 'undefined'){
			//页面加载合计
			jQObj.push(jQuery("#leader"));
			jQObj.push(jQuery("#pm"));
			flag = total(jQObj);
			totalMajor();
		}else{
			// 动态改变工作量 加载合计
			jQObj.push(_this.closest("table"));
			flag = total(jQObj);
		}
		return flag;
	}
	
	//当年专业结算比例合计
	function totalMajor(){
		var flag = true;
		var totalsettleRate = 0.00;
		jQuery.each($("#majorSettleRate tbody tr:not(:last)"),function(index,item){
			var _this = $(item);
			var settleRate = _this.find("input[name$='.settleRate']").val();
			/* if(settleRate!='' && settleRate.charAt(settleRate.length-1) !='.'){
				  settleRate = new Number(new Number(settleRate).toFixed(2));
				  _this.find("input[name$='.settleRate']").val(settleRate);
			  } */
			totalsettleRate = new Number(totalsettleRate) + new Number(settleRate);
		});
		$("#majorSettleRate tr.total").find("td:eq(1)").text(new Number(totalsettleRate).toFixed(2));
		if(new Number(totalsettleRate) !=100){
			flag = false;
		}
		return flag;
	}
	
	function total(obj){
		var flag = true;
		for(var i =0 ;i<obj.length;i++){
			var _obj = obj[i];
			var totalstaffRate = 0.00;
			var totalstaffYield = 0.00;
			jQuery.each(_obj.find("tbody tr:not(:last)"),function(index,item){
				var _this = $(item);
				var staffRate = getNumValue(_this.find("input[name$='staffRate']").val());
				var staffYield =getNumValue(_this.find("input[name$='staffYield']").val());
				totalstaffRate = new Number(totalstaffRate) + new Number(staffRate);
				totalstaffYield = new Number(totalstaffYield) + new Number(staffYield);
			});
			if(new Number(totalstaffRate) !=100){
				flag = false;
			}
			_obj.find("tr.total").find("td:eq(1)").text(new Number(totalstaffRate).toFixed(2));
			_obj.find("tr.total").find("td:eq(2)").text(toThousands(new Number(totalstaffYield).toFixed(2)));
		}
		return flag;
	}
	
	function selectPm(data){
		selectStaffBack(data,"pm");
	}
	
    function selectLeader(data){
    	selectStaffBack(data,"leader");
	}
    
    function selectSecret(data){
    	selectStaffBack(data,"secret");
	}
	
	function selectStaffBack(data,type){
		var _thisTable = jQuery("#leader");
		var staffCategory = leaderCate;
		if(type == "pm"){
			_thisTable = jQuery("#pm");
			staffCategory = pmCate;
		}else if(type == "secret"){
			_thisTable = jQuery("#secretary");
			staffCategory = secretCate;
		}
		jQuery.each(data,function(index,item){
			var info = $(item)[0];
			var trSize = _thisTable.find("tbody tr:not(:last)").size();
			if(trSize == 0){
				var $item = jQuery("#clone_text").clone();
				$item.html($item.html().format(tIndex));
				$item.find("tr input[name$='.staffId']").val(info.id);
				$item.find("tr input[name$='.staffSort']").val(tIndex);
				$item.find("tr input[name$='.staffCategory']").val(staffCategory);
				$item.find("tr td:first").text(info.name);
				_thisTable.find(".total").before($item.find("tr"));
				tIndex ++;
			}else{
				var _size = _thisTable.find("tbody tr input[value='"+info.id+"']").size();
				if(_size <= 0){
					//可以添加
					var $item = jQuery("#clone_text").clone();
					$item.html($item.html().format(tIndex));
					$item.find("tr input[name$='.staffId']").val(info.id);
					$item.find("tr input[name$='.staffSort']").val(tIndex);
					$item.find("tr input[name$='.staffCategory']").val(staffCategory);
					$item.find("tr td:first").text(info.name);
					_thisTable.find(".total").before($item.find("tr"));
					tIndex++;
				}
			}
		});
	}
	
	function save(status) {
		var flag = false;
	    if(status == "9"){
	        $.jalert({"jatext":"确认删除？<br>点[确定]：执行删除操作<br>点[取消]：放弃删除操作", "jatype":"confirm", "onConfirm":function(){
	        	ajaxSave(true,status);
	        }});
	        
	    }else{
	    	if (jQuery("#saveForm").valid()) {
	            flag = true;
	        }
	    	if(flag){
	    		if(!initTotal()){
	    			$.jalert({"jatext":"比例合计必须为100"});
	                flag = false;
	    		}
	    		if(flag && !totalMajor()){
	    			$.jalert({"jatext":"专业结算比例必须为100"});
	                flag = false;
	    		}
	    	}
	    	ajaxSave(flag,status);
	    }
	}
	
	function ajaxSave(flag,status){
		var url ="${site}/admin/ym/yieldSettle/ajax/save";
	    if (flag) {
	    	//把disabled属性去掉
	    	jQuery("input:disabled").removeAttr("disabled");
	    	jQuery.each(jQuery("input[name$='Amount'],input[name$='yearYield']"),function(index,item){
	    		var _item = $(item);
	    		_item.val(delcommafy(_item.val()));
	    	});
	    	$("input[name='wfYieldSettle.auditStatus']").val(status);
		        jQuery.ajax({
		        type : "POST",
		        url : url,
		        data : jQuery('#saveForm').serialize(),
		        async : false,
		        success : function(data) {
		        	if(data.flag == 'true'){
		        	$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
				  		window.location.href="${site}/admin/ym/yieldSettle/list";
		 			}});
		        	}else{
		        		$.jalert({"jatext":data.msg});
		        	}
		        }
	        });
	    }
	}
	
	//千分位处理 去掉千分位
	function delcommafy(num){  
	   num = num.replace(/[ ]/g, "");//去除空格  
	   num=num.replace(/,/gi,'');  
	   return num;  
	}
	
	//添加千分位
	function toThousands(num) {
	    return (num || 0).toString().replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	
	function getNumValue(num) {
	       if (validateInput(num)) {
	           num = parseFloat(num);
	       }
	       else {
	           num = 0;
	       }
	       return num;
	   }

    function validateInput(inputstr) {
	      flag = false;
	      if (inputstr != "") {
	          if (isNaN(inputstr)) {
	              flag = false; //如果输入字符不是数字
	          }
	          else {//输入数字但是小于0
	              if (parseFloat(inputstr) < 0)
	                  flag = false;
	              else
	                  flag = true;
	          }
	      }
	      return flag;
	  }
</script>

</body>
</html>