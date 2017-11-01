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
							<input type="text" name="wfYieldSettle.proCode" class="form-control col-md-3" disabled value="${project.proCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proName" class="form-control" disabled value="${project.proName}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同编号</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proType" class="form-control" disabled value="${project.contractCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proType" class="form-control" disabled value="${project.proType}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同额(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="wfYieldSettle.contractAmount" value="${project.contractAmount}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">分包扣减(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="wfYieldSettle.pkgAmount" value="${project.pkgAmount}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案扣减(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="wfYieldSettle.schemeAmount" value="${project.schemeAmount}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">其他扣减(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="wfYieldSettle.rebateAmount" value="${project.rebateAmount}"> 
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" disabled name="wfYieldSettle.pManagers" value="${project.pmLeaders}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
			            	<input type="text" name="wfYieldSettle.pManagers" class="form-control col-md-3" value="${project.pManagers}" disabled>
						</div>
					</div>
					
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">当年可结算产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" name="wfYieldSettle.yearYield" value="${empty yieldSettle.yearYield ? project.yield : yieldSettle.yearYield}" disabled>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">历年已结算产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" name="wfYieldSettle.hisyearYield" value="${empty yieldSettle.hisyearYield ? hisyearYield : yieldSettle.hisyearYield}" disabled>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">所处状态<span class="required">※</span></label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
						    <tags:config type="select" cssClass="form-control" selectCode="${empty yieldSettle.itemStatus ? project.proStatus : yieldSettle.itemStatus}" parentCode="PM.STATUS" name="wfYieldSettle.itemStatus"></tags:config>
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
											  <input type="text" class="form-control text-right"
														name="principalAllots[${size}].staffRate"
														value="${leader.staffRate}"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true">
													</td>
											<td  class="col-lg-4 text-right">
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="${leader.staffYield}" placeholder="0.00" disabled>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
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
											  <input type="text" class="form-control text-right"
														name="principalAllots[${size}].staffRate" 
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="">
													</td>
											<td  class="col-lg-4 text-right">
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="" placeholder="0.00" disabled>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
								   </c:forEach>
								  </c:if>
									<tr class="total">
										<td  class="text-center col-lg-4">合计</td>
										<td  class="col-lg-4 text-right"></td>
										<td  class="col-lg-4 text-right"></td>
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
											  <input type="text" class="form-control text-right"
														name="principalAllots[${size}].staffRate"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="${manager.staffRate}">
													</td>
											<td  class="col-lg-4 text-right">
											<input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="${manager.staffYield}" placeholder="0.00" disabled>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
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
											  <input type="text" class="form-control text-right"
														name="principalAllots[${size}].staffRate"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="">
													</td>
											<td  class="col-lg-4 text-right">
											<input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="" placeholder="0.00" disabled>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
									 </c:forEach>
								 </c:if>
								 <tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right"></td>
									<td  class="col-lg-4 text-right"></td>
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
											   <input type="text" class="form-control text-right"
														name="majorRates[${st.index}].settleRate"
														data-rule-number="true" 
														data-rule-max="100"
														data-rule-min="0" 
														placeholder="0.00"
														data-rule-required="true"
														value="${majorRate.settleRate}">
													</td>
										</tr>
									  </c:forEach>
								  </c:if>
								  
								 <c:if test="${empty majorRates and not empty configs}">
									  <c:forEach items="${configs}" var="config" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">
											  ${config.configName}
											</td>
											<td  class="col-lg-8 text-right input-icon left">
											   <i class="fa"></i>
											   <input type="hidden" name="majorRates[${st.index}].id" value="">
											   <input type="hidden" name="majorRates[${st.index}].wfId" value="">
											   <input type="hidden" name="majorRates[${st.index}].majorCode" value="${config.configCode}">
											   <input type="hidden" name="majorRates[${st.index}].majorSort" value="${st.index}">
											   <input type="text" class="form-control text-right"
														name="majorRates[${st.index}].settleRate"
														data-rule-number="true"
														placeholder="0.00" 
														data-rule-max="100"
														data-rule-min="0" 
														data-rule-required="true"
														value="${config.configValue}">
													</td>
										</tr>
									  </c:forEach>
								  </c:if>
								  
									<tr class="total">
										<td  class="text-center col-lg-4">合计</td>
										<td  class="col-lg-8 text-right"></td>
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
				  <input type="text" class="form-control text-right"
							name="principalAllots[{0}].staffRate" 
							data-rule-number="true" 
						    data-rule-max="100"
						    data-rule-min="0" 
						    placeholder="0.00"
						    data-rule-required="true"
							value="">
						</td>
				<td  class="col-lg-4 text-right">
				  <input type="text" class="form-control text-right" name="principalAllots[{0}].staffYield" placeholder="0.00" value="" disabled>
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
		
		initTotal(null);
	});

	//计算产值
	function countOutputValue(_tr){
		  if(_tr.size()>0){
			  jQuery.each(_tr,function(index,item){
				  var $item = $(item);
				  var staffRate = $item.find("input[name$='staffRate']").val();
				  //项目负责人 项目经理 比例
				  var rate = $item.closest("table").prev().find("input[name^='wfYieldSettle']").val();
				  //当年可结算产值
				  var yearYield = jQuery("input[name$='.yearYield']").val();
				  
				  if(rate == '' || typeof rate == 'undefined' || isNaN(rate)){
					  rate = 0.00;
				  }
				  if(yearYield == '' || typeof yearYield == 'undefined' || isNaN(yearYield)){
					  yearYield = 0.00;
				  }
				  //=当年可结算产值×比例×工作量/10000
				  var staffYield =new Number(yearYield) * new Number(rate) * staffRate/10000;
				  var _input = $item.find("td:last").find("input");
				  _input.val(new Number(staffYield).toFixed(2));
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
		if(settleRate == '' || typeof settleRate == 'undefined' || isNaN(settleRate)){
			settleRate = 0.00;
		  }
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
				var staffRate = _this.find("input[name$='staffRate']").val();
				var staffYield = _this.find("input[name$='staffYield']").val();
				if(staffYield == '' || typeof staffYield == 'undefined' || isNaN(staffYield)){
					staffYield = 0;
				  }
				  if(staffRate == '' || typeof staffRate == 'undefined' || isNaN(staffRate)){
					  staffRate = 0;
				  }
				  totalstaffRate = new Number(totalstaffRate) + new Number(staffRate);
				  totalstaffYield = new Number(totalstaffYield) + new Number(staffYield);
			});
			if(new Number(totalstaffRate) !=100){
				flag = false;
			}
			_obj.find("tr.total").find("td:eq(1)").text(new Number(totalstaffRate).toFixed(2));
			_obj.find("tr.total").find("td:eq(2)").text(new Number(totalstaffYield).toFixed(2));
		}
		return flag;
	}
	
	function selectPm(data){
		selectStaffBack(data,"pm");
	}
	
    function selectLeader(data){
    	selectStaffBack(data,"leader");
	}
	
	function selectStaffBack(data,type){
		var _thisTable = jQuery("#leader");
		var staffCategory = leaderCate;
		if(type == "pm"){
			_thisTable = jQuery("#pm");
			staffCategory = pmCate;
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
	    	$("input[name='wfYieldSettle.auditStatus']").val(status);
		        jQuery.ajax({
		        type : "POST",
		        url : url,
		        data : jQuery('#saveForm').serialize(),
		        async : false,
		        error : function(request) {
		        	$.jalert({"jatext":"Connection error"});
		        },
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
</script>

</body>
</html>