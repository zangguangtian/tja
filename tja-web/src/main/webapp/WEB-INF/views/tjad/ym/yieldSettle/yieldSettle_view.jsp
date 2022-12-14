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
	<div class="<c:if test="${not empty print }">print</c:if>">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/ym/yieldSettle/ajax/approve" class="row">
				<input type="hidden" name="wfCategory" value="1000">
				<input type="hidden" name="id" value="${yieldSettle.id}">
		        <input type="hidden" name="procId" value="${yieldSettle.procId }">
		        <input type="hidden" name="approve" value="2"/>
		        <input type="hidden" name="view" value="${view}"/>
				<div class="form-body clearfix" style="padding-bottom: 0">
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">流水号</label>
						<div class="col-xs-8">
							<label class="control-label">${yieldSettle.seqNo}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4"></label>
						<div class="col-xs-8 text-right">
							<c:if test="${canRevoke}">
								<input type="button" id="reject-btn" value="撤回" class="btn blue">
							</c:if>
				           	<c:if test="${not empty canPrintView}">
								<input type="button" id="printview-btn" value="打印预览" class="btn blue">
							</c:if>
							<c:if test="${not empty canPrint}">
								<input type="button" id="print-btn" value="打印" class="btn blue">
							</c:if>
						</div>
					</div>
			    </div>
				
				<div class="form-body clearfix">
				
				    <div class="form-group col-xs-6">
						<label class="control-label col-xs-4">项目编号</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proCode}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目名称</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proName}</label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">合同编号</label>
						<div class="col-xs-8">
							<label class="control-label">${project.contractCode}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目类型</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proType}</label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">合同额(¥)</label>
						<div class="col-xs-8">
						    <label class="control-label"><fmt:formatNumber value='${yieldSettle.contractAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">分包扣减(¥)</label>
						<div class="col-xs-8">
						    <label class="control-label"><fmt:formatNumber value='${yieldSettle.pkgAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">方案扣减(¥)</label>
						<div class="col-xs-8">
						    <label class="control-label"><fmt:formatNumber value='${yieldSettle.schemeAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">其他扣减(¥)</label>
						<div class="col-xs-8">
						    <label class="control-label"><fmt:formatNumber value='${yieldSettle.rebateAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目负责人</label>
						<div class="col-xs-8">
							<label class="control-label">${project.pmLeaders}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目经理</label>
						<div class="col-xs-8">
			            	<label class="control-label">${project.pManagers}</label>
						</div>
					</div>
					
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">当年可结算产值(¥)</label>
						<div class="col-xs-8">
						    <label class="control-label">
						     <input type="hidden" name="yearYield" value="${yieldSettle.yearYield}"/>
						     <fmt:formatNumber value='${yieldSettle.yearYield}' pattern='#,#00.00#'/>
						    </label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">历年已结算产值(¥)</label>
						<div class="col-xs-8">
						    <label class="control-label"><fmt:formatNumber value='${yieldSettle.hisyearYield}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">所处状态</label>
						<div class="col-xs-8">
						    <tags:config type="label" code="${yieldSettle.itemStatus}" />
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4"></label>
						<div class="col-xs-8">
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">创建人</label>
						<div class="col-xs-8">
						<label class="control-label">${yieldSettle.creatorName}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">创建时间</label>
						<div class="col-xs-8">
						 <jsp:useBean id="currentDate" class="java.util.Date"/>
	            		 <fmt:formatDate value="${yieldSettle.createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="currentDate"/>
						 <label class="control-label">${currentDate }</label>
						</div>
					</div>
					

					<h3 class="form-tit col-lg-12">产值分配</h3>
					<div class="col-lg-12">
						<div class="col-lg-6 ">
							<div class="row clearfix">
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4">项目负责人</div>
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4 text-right">
							      <label>比例：</label>
							      <input type="hidden" name="principalRate" value="${yieldSettle.principalRate}">
							      ${yieldSettle.principalRate}
								</div>
							</div>
							
							<table class="table table-bordered" id="leader">
								<thead>
									<tr>
										<th class="text-center col-lg-4">姓名</th>
										<th class="text-center">工作量(%)</th>
										<th class="text-center">产值</th>
									</tr>
								</thead>
								<tbody>
								 <c:set var="size" value="0"></c:set>
								 <c:set var="total_le_rate" value="0"></c:set>
								 <c:set var="total_le_yield" value="0"></c:set>
								 <c:if test="${not empty proLeaders}">
								   <c:forEach items="${proLeaders}" var="leader" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">${leader.staffName}</td>
											<td  class="col-lg-4 text-right">
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffRate" value="${leader.staffRate}" data-rule-number="true"  placeholder="0.00" disabled>
											</td>
											<td  class="col-lg-4 text-right">
											  <fmt:formatNumber value='${leader.staffYield}' pattern='#,#00.00#'/>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
										<c:set var="total_le_rate" value="${leader.staffRate+total_le_rate}"></c:set>
								        <c:set var="total_le_yield" value="${leader.staffYield+total_le_yield}"></c:set>
								   </c:forEach>
								  </c:if>
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right">${total_le_rate}</td>
									<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_le_yield}' pattern='#,#00.00#'/></td>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="col-lg-6 ">
							<div class="row">
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4">项目经理</div>
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4 text-right">
							      <label>比例：</label>
							      <input type="hidden" name="pmRate" value="${yieldSettle.pmRate}">
							      ${yieldSettle.pmRate}
								</div>
							</div>
							<table class="table table-bordered" id="pm">
								<thead>
									<tr>
										<th  class="text-center col-lg-4">姓名</th>
										<th class="text-center">工作量(%)</th>
										<th class="text-center">产值</th>
									</tr>
								</thead>
								<tbody>
								 <c:set var="total_pm_rate" value="0"></c:set>
								 <c:set var="total_pm_yield" value="0"></c:set>
								 <c:if test="${not empty proManagers}">
									  <c:forEach items="${proManagers}" var="manager">
									    <tr>
											<td  class="text-center col-lg-4">${manager.staffName}</td>
											<td  class="col-lg-4 text-right">
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffRate"  placeholder="0.00" value="${manager.staffRate}" disabled>
											</td>
											<td  class="col-lg-4 text-right">
											 <fmt:formatNumber value='${manager.staffYield}' pattern='#,#00.00#'/>
											</td>
										</tr>
										<c:set var="total_pm_rate" value="${manager.staffRate+total_pm_rate}"></c:set>
								        <c:set var="total_pm_yield" value="${manager.staffYield+total_pm_yield}"></c:set>
									 </c:forEach>
								 </c:if>
								 <tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right">${total_pm_rate}</td>
									<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_pm_yield}' pattern='#,#00.00#'/></td>
								 </tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="col-lg-12">
						<div class="col-lg-6 ">
							<div class="row clearfix">
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4">项目秘书</div>
							    <div class="col-lg-4  col-md-4 col-sm-4 col-xs-4 text-right">
							      <label>比例：</label>
							      <input type="hidden" name="secretRate" value="${yieldSettle.secretRate}">
							      ${yieldSettle.secretRate}
								</div>
							</div>
							
							<table class="table table-bordered" id="secretary">
								<thead>
									<tr>
										<th class="text-center col-lg-4">姓名</th>
										<th class="text-center">工作量(%)</th>
										<th class="text-center">产值</th>
									</tr>
								</thead>
								<tbody>
								 <c:set var="size" value="0"></c:set>
								 <c:set var="total_secret_rate" value="0"></c:set>
								 <c:set var="total_secret_yield" value="0"></c:set>
								 <c:if test="${not empty proSecretarys}">
								   <c:forEach items="${proSecretarys}" var="proSecretary" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">${proSecretary.staffName}</td>
											<td  class="col-lg-4 text-right">
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffRate" value="${proSecretary.staffRate}" data-rule-number="true"  placeholder="0.00" disabled>
											</td>
											<td  class="col-lg-4 text-right">
											  <fmt:formatNumber value='${proSecretary.staffYield}' pattern='#,#00.00#'/>
											</td>
										</tr>
										<c:set var="size" value="${size+1}"></c:set>
										<c:set var="total_secret_rate" value="${proSecretary.staffRate+total_secret_rate}"></c:set>
								        <c:set var="total_secret_yield" value="${proSecretary.staffYield+total_secret_yield}"></c:set>
								   </c:forEach>
								  </c:if>
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right">${total_secret_rate}</td>
									<td  class="col-lg-4 text-right"><fmt:formatNumber value='${total_secret_yield}' pattern='#,#00.00#'/></td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="col-lg-12">
						<div class="col-lg-5 ">
						    <caption>当年专业结算比例</caption>
							<table class="table table-bordered" id="majorSettleRate">
								<thead>
									<tr>
										<th  class="text-center col-lg-4">专业</th>
										<th class="text-center">结算比例(%)</th>
									</tr>
								</thead>
								<tbody>
								<c:set var="totalSettleRate" value="0"></c:set>
								<c:if test="${not empty majorRates}">
									  <c:forEach items="${majorRates}" var="majorRate" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">
											  ${majorRate.majorName}
											</td>
											<td  class="col-lg-8 text-right">
											   <input type="text" class="form-control text-right" name="majorRates[${st.index}].settleRate"  placeholder="0.00" value="${majorRate.settleRate}" disabled>
											   <c:set var="totalSettleRate" value="${majorRate.settleRate+totalSettleRate}"></c:set>
											</td>
										</tr>
									  </c:forEach>
								  </c:if>
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-8 text-right">${totalSettleRate}</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
					<jsp:include page="include_major.jsp"/>
					<jsp:include page="../../../framework/activiti/wf_approve.jsp" flush="true"/>
	                <tags:histask procId="${yieldSettle.procId }"/>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div>
 <table id="clone_text" style="display: none">
 <tr class="row roleAllot">
 <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].wfId" value="">
    <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].id" value="">
    <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].majorCode" value="">
    <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].category" value="1000">
    <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].roleCode" value="">
    <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].staffId" value="">
    <input type="hidden" class="ta_input" name="majorRoleAllots[{0}].staffSort" value="">
    <input type="hidden" name="majorRoleAllots[{0}].staffYield" value="">
	<td class="text-center col-lg-4"></td>
	<td class="col-lg-4 input-icon left">
	 <i class="fa"></i>
	 <input type="text" name="majorRoleAllots[{0}].staffRate"
					placeholder="0.00"
					data-rule-number="true"
					data-rule-required="true"
					class="form-control text-right twoNum" 
					data-rule-max="100"
					data-rule-min="0" 
					value="">
				</td>
	<td class=" col-lg-4 text-right">
	</td>
  </tr>
 </table>
</div>
<script type="text/javascript">
	$(function(){
		jQuery(document).on("keyup","input[name$='.staffRate']",function(){
			countOutputValue($(this).closest("tr"));
		});
		jQuery(document).on("keyup","input[name$='.allotRate']",function(){
			countOutputValue($(this).parent().parent().next().find("tbody tr:not(:last)"));
		});
	});

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
	
	$(document).on("click", "#reject-btn", function(){
	    jQuery("input[type='hidden'][name='approve']").val("4");
	    var url = jQuery("#approveForm").attr("action");
	    if (jQuery("#approveForm").valid()) {
	    	jQuery.jalert({"jatype":"confirm", "jatext":"确定撤回？<br>点[确定]：执行撤回操作<br>点[取消]：放弃撤回操作", "onConfirm":function(){
	    		jQuery.ajax({
	                type : "POST",
	                url : url,
	                data : jQuery("#approveForm").serialize(),
	                async : false,
	                success : function(data) {
	                	$.jalert({"jatext":data.msg});
	                    window.location.href = "${site}/admin/ym/yieldSettle/list";
	                }
	            });
	        }});
	    }
	});
	
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
	
	/*打印预览*/
	jQuery("#printview-btn").dfprint({
	    action : "printview",
	    url : "${site}/admin/ym/yieldSettle/ordin/toprint/v/${yieldSettle.id}"
	});

	/*打印*/
	jQuery("#print-btn").dfprint({
	    action : "print"
	});
</script>

</body>
</html>