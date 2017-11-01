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
            <div class="row">
                <div class='col-lg-3 <c:if test="${not empty print }">printSeq</c:if> <c:if test="${empty print }">seq</c:if>'>
                                                     流水号:${yieldSettle.seqNo}
                </div>
               <div class="col-lg-9 text-right">
                    <c:if test="${canRevoke }">
                        <input type="button" id="reject-btn" value="撤回" class="btn blue">
                    </c:if>
                    <c:if test="${not empty canPrintView}">
                        <input type="button" id="printview-btn" value="打印预览" class="btn blue"/>
                    </c:if>
                    <c:if test="${not empty canPrint}">
                        <input type="button" id="print-btn" value="打印" class="btn blue"/>
                    </c:if>
               </div>
            </div>
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/ym/yieldSettle/ajax/approve" class="row">
				<input type="hidden" name="wfCategory" value="1000">
				<input type="hidden" name="id" value="${yieldSettle.id}">
		        <input type="hidden" name="procId" value="${yieldSettle.procId }">
		        <input type="hidden" name="approve" value="2"/>
		        <input type="hidden" name="view" value="${view}"/>
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
							<input type="text" name="proCode" class="form-control " disabled value="${project.proCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" name="proName" class="form-control" disabled value="${project.proName}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同编号</label>
						<div class="col-md-8">
							<input type="text" name="proType" class="form-control" disabled value="${project.contractCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" name="proType" class="form-control" disabled value="${project.proType}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同额(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="contractAmount" value="${project.contractAmount}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">分包扣减(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="pkgAmount" value="${project.pkgAmount}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案扣减(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="schemeAmount" value="${project.schemeAmount}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">其他扣减(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="rebateAmount" value="${project.rebateAmount}"> 
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" disabled name="pManagers" value="${project.pmLeaders}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
			            	<input type="text" name="pManagers" class="form-control " value="${project.pManagers}" disabled>
						</div>
					</div>
					
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">当年可结算产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" name="yearYield" value="${yieldSettle.yearYield}" disabled>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">历年已结算产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" name="hisyearYield" value="${yieldSettle.hisyearYield}" disabled>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">所处状态</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
						    <tags:config type="label" code="${yieldSettle.itemStatus}" />
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
						<input type="hidden" name="creator" value="${yieldSettle.creator}"/>
						<label class="control-label">${yieldSettle.creatorName}</label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">创建时间</label>
						<div class="col-md-8 input-icon right">
						 <jsp:useBean id="currentDate" class="java.util.Date"/>
	            		 <fmt:formatDate value="${yieldSettle.createDate}" pattern="yyyy-MM-dd" var="currentDate"/>
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
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="${leader.staffYield}" placeholder="0.00" disabled>
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
									<td  class="col-lg-4 text-right">${total_le_yield}</td>
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
											<input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="${manager.staffYield}" placeholder="0.00" disabled>
											</td>
										</tr>
										<c:set var="total_pm_rate" value="${manager.staffRate+total_pm_rate}"></c:set>
								        <c:set var="total_pm_yield" value="${manager.staffYield+total_pm_yield}"></c:set>
									 </c:forEach>
								 </c:if>
								 <tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right">${total_pm_rate}</td>
									<td  class="col-lg-4 text-right">${total_pm_yield}</td>
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
										<th class="text-center">结算比例(%)<span class="required">※</span></th>
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
	 <input type="text" name="majorRoleAllots[{0}].staffRate"
					placeholder="0.00"
					data-rule-number="true"
					data-rule-required="true"
					class="form-control text-right" 
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

	function getNumValue(controlid) {
	       var num = controlid.val();
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
	
	//计算产值
	function countOutputValue(_tr){
		if(_tr.size()>0){
			jQuery.each(_tr,function(index,item){
				var _this = $(item).find("input[name$='.staffRate']");
				var staffRate = getNumValue(_this);
				  //专业角色  比例
				  var rate = getNumValue(_this.closest("table").prev().find("input[name$='allotRate']"));
				  //当年可结算产值(¥)
				  var yearYield = getNumValue(jQuery("input[name='yearYield']"));
				  //项目经理 比例
				  var pmRate = getNumValue(jQuery("input[name='pmRate']"));
				  //项目负责人 比例
				  var principalRate = getNumValue(jQuery("input[name='principalRate']"));
				  
				  //本专业结算比例
				  var majorAllotRate = getNumValue(jQuery("div.tab-content div.active").find("input[name='majorAllotRate']"));
				  
				  var roleCode = $(item).find("input[name$='.roleCode']").val();
				  var staffYield = 0.00;
				  if("PrjMajorLeader" == roleCode){
					  // 专业负责人个人产值=当年可结算产值(¥)×(100-项目负责人比例-项目经理比例)×本专业结算比例×专业负责人比例×工作量/100000000
					  staffYield = new Number(yearYield)*(100-new Number(principalRate) - new Number(pmRate))*(new Number(majorAllotRate))*(new Number(rate))*(new Number(staffRate))/100000000;
				  }else{
					//专业负责人比例
					var allotRate = getNumValue(jQuery("input[name$='.roleCode'][value='PrjMajorLeader']").closest("div.row").find("input[name$='.allotRate']"));  
					//校对人、审核人、设计人/制图人个人产值=当年可结算产值(¥)×(100-项目负责人比例-项目经理比例)×本专业结算比例×(100-专业负责人比例)×本角色比例×工作量/10000000000
					  staffYield = new Number(yearYield) * (100-new Number(principalRate) - new Number(pmRate))*(new Number(majorAllotRate))*(100 - new Number(allotRate))*(new Number(rate))*(new Number(staffRate))/100000000;
				  }
				  var _td = $(item).find("td:last");
				  _td.text(new Number(staffYield).toFixed(2));
				  $(item).find("input[name$='.staffYield']").val(new Number(staffYield).toFixed(2));
				  if(index == _tr.size()-1){
					  initTotal(_this);
				  }
			});
		}
	}
	
	//合计
	function initTotal(_this){
		var totalstaffRate = 0.00;
		var totalstaffYield = 0.00;
		jQuery.each(_this.closest("table").find("tbody tr:not(:last)"),function(index,item){
			var _this = $(item);
			var staffRate = getNumValue(_this.find("input[name$='staffRate']"));
			var staffYield =getNumValue(_this.find("input[name$='staffYield']"));
		    totalstaffRate = new Number(totalstaffRate) + new Number(staffRate);
		    totalstaffYield = new Number(totalstaffYield) + new Number(staffYield);
		});
		_this.closest("table").find("tr.total").find("td:eq(1)").text(new Number(totalstaffRate).toFixed(2));
		_this.closest("table").find("tr.total").find("td:eq(2)").text(new Number(totalstaffYield).toFixed(2));
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
	                error : function(request) {
	                	$.jalert({"jatext":"Connection error"});
	                },
	                success : function(data) {
	                	$.jalert({"jatext":data.msg});
	                    window.location.href = "${site}/admin/ym/yieldSettle/list";
	                }
	            });
	        }});
	    }
	});
	
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