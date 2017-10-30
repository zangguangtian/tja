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
			<form id="saveForm" action="${site}/admin/ym/yieldSettle/ajax/approve" class="row">
				<input type="hidden" name="wfCategory" value="1000">
				<input type="hidden" name="id" value="${yieldSettle.id}">
		        <input type="hidden" name="procId" value="${yieldSettle.procId }">
		        <input type="hidden" name="approve" value="2"/>
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
							<input type="text" name="proCode" class="form-control col-md-3" disabled value="${project.proCode}">
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
			            	<input type="text" name="pManagers" class="form-control col-md-3" value="${project.pManagers}" disabled>
						</div>
					</div>
					
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">当年可结算产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" name="yearYield" value="${project.yield}" disabled>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">历年已结算产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" name="hisyearYield" value="${project.yield}" disabled>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">所处状态</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
						    <tags:config type="label" cssClass="form-control" selectCode="${yieldSettle.itemStatus}" parentCode="PM.STATUS" name="itemStatus"></tags:config>
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
								 <c:if test="${not empty proManagers}">
									  <c:forEach items="${proManagers}" var="manager">
									    <tr>
											<td  class="text-center col-lg-4">${manager.staffName}</td>
											<td  class="col-lg-4 text-right">
											  <input type="text" class="form-control text-right" name="principalAllots[${size}].staffRate"  data-rule-number="true"  placeholder="0.00" value="${manager.staffRate}" disabled>
											</td>
											<td  class="col-lg-4 text-right">
											<input type="text" class="form-control text-right" name="principalAllots[${size}].staffYield" value="${manager.staffYield}" placeholder="0.00" disabled>
											</td>
										</tr>
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
								<c:if test="${not empty majorRates}">
									  <c:forEach items="${majorRates}" var="majorRate" varStatus="st">
									    <tr>
											<td  class="text-center col-lg-4">
											  ${majorRate.majorName}
											</td>
											<td  class="col-lg-8 text-right">
											   <input type="text" class="form-control text-right" name="majorRates[${st.index}].settleRate"  data-rule-number="true"  placeholder="0.00" value="${majorRate.settleRate}" disabled>
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
					
					<c:if test="${view == 2 }">
						<jsp:include page="include_major.jsp"/>
					</c:if>
					
					<c:if test="${view > 2 }">
					
					</c:if>
					
					<jsp:include page="../../../framework/activiti/wf_approve.jsp" flush="true"/>
	                <tags:histask procId="${yieldSettle.procId }"/>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script type="text/javascript">
    var tIndex = ${size};
    var leaderCate = "${categoryLeader}";
    var pmCate = "${categoryPm}";
	$(function(){
		
		jQuery(document).on("keyup","#leader tbody input[name$='staffRate']",function(){
			countOutputValue($(this));
		});
		
		jQuery(document).on("keyup","#pm tbody input[name$='staffRate']",function(){
			countOutputValue($(this));
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
	function countOutputValue(_this){
		  var staffRate = _this.val();
		  //项目负责人 项目经理 比例
		  var rate = _this.closest("table").prev().find("input[name^='wfYieldSettle']").val();
		  //合同额 
		  var contractAmount = jQuery("input[name$='.contractAmount']").val();
		  //分包扣减
		  var pkgAmount = jQuery("input[name$='.pkgAmount']").val();
		  //方案扣减
		  var schemeAmount = jQuery("input[name$='.schemeAmount']").val();
		  //其他扣减
		  var rebateAmount = jQuery("input[name$='.rebateAmount']").val();
		  
		  if(rate == '' || typeof rate == 'undefined' || isNaN(rate)){
			  rate = 0.00;
		  }
		  if(contractAmount == '' || typeof contractAmount == 'undefined' || isNaN(contractAmount)){
			  contractAmount = 0.00;
		  }
		  if(pkgAmount == '' || typeof pkgAmount == 'undefined' || isNaN(pkgAmount)){
			  pkgAmount = 0.00;
		  }
		  if(schemeAmount == '' || typeof schemeAmount == 'undefined' || isNaN(schemeAmount)){
			  schemeAmount = 0.00;
		  }
		  if(rebateAmount == '' || typeof rebateAmount == 'undefined' || isNaN(rebateAmount)){
			  rebateAmount = 0.00;
		  }
		  //=(实际合同额-分包扣减-方案扣减-其他扣减)×0.9×比例×工作量/10000
		  var staffYield =(new Number(contractAmount) - new Number(pkgAmount) - new Number(schemeAmount) - new Number(rebateAmount)) * 0.9 * new Number(rate) * staffRate/10000;
		  var _input = _this.closest("tr").find("td:last").find("input");
		  _input.val(new Number(staffYield).toFixed(2));
		  var flag = initTotal(_this);
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
			var trs = _thisTable.find("tbody tr:not(:last)");
			var trSize = _thisTable.find("tbody tr:not(:last)").size();
			if(trSize == 0){
				var $item = jQuery("#clone_text").clone();
				$item.html($item.html().format(tIndex));
				$item.find("tr input[name$='.staffId']").val(info.id);
				$item.find("tr input[name$='.staffSort']").val(tIndex);
				$item.find("tr input[name$='.staffCategory']").val(staffCategory);
				$item.find("tr td:first").text(info.name);
				_thisTable.find(".total").before($item.find("tr"));
				trSize ++;
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
					trSize ++;
					tIndex++;
				}
			}
		});
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
	    url : "${site}/admin/ym/yieldSettle/toprint/v/${yieldSettle.id}"
	});

	/*打印*/
	jQuery("#print-btn").dfprint({
	    action : "print"
	});
</script>

</body>
</html>