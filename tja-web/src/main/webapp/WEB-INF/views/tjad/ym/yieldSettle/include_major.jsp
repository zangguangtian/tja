<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="col-md-12 form-group">
	<div class="tabbable-line boxless tabbable-reversed">
		<ul class="nav nav-tabs">
		    <c:if test="${not empty majorModels}">
		      <c:forEach items="${majorModels}" var="majorModel" varStatus="st">
			    <li <c:if test="${st.index == 0}">class="active"</c:if>><a href="#tab_${st.index}" data-toggle="tab" aria-expanded="true">${majorModel.majorName}</a></li>
			  </c:forEach>
			</c:if>
		</ul>
		<div class="tab-content" style="padding: 10px;">
		<c:if test="${not empty majorModels}">
		<c:set var="roleSize" value="0"></c:set>
		<c:set var="rateSize" value="0"></c:set>
		   <c:forEach items="${majorModels}" var="majorModel" varStatus="st">
			<div class="tab-pane <c:if test='${st.index == 0}'>active</c:if>" id="tab_${st.index}">
			   <input type="hidden" name="majorAllotRate" value="${majorModel.majorAllotRate}">
			   <c:if test="${not empty majorModel.majorRoleRates}">
			   <h3 class="form-tit col-lg-12">当年本专业结算比例(%)：${majorModel.majorAllotRate}</h3>
			      <c:forEach items="${majorModel.majorRoleRates}" var="majorRoleRate" varStatus="st">
			        <c:if test="${majorRoleRate.majorCode == majorModel.majorCode}">
			        
			      <!--   两个一组的写法 开始 -->
			        <c:if test="${st.index%2 == '0'}">
					   </ul>
					 </c:if>
					 <c:if test="${st.index%2 == '0'}">
					   <ul class="col-lg-12">
					 </c:if>
				 <!--   两个一组的写法 结束 -->
				 
						<div class="col-lg-5">
							<div class="">
								<div class="row">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].wfId" value="${majorRoleRate.wfId}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].id" value="${majorRoleRate.id}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].majorCode" value="${majorRoleRate.majorCode}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].majorName" value="${majorModel.majorName}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].roleCode" value="${majorRoleRate.roleCode}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].roleName" value="${majorRoleRate.roleName}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].majorRateId" value="${majorRoleRate.majorRateId}">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">${majorRoleRate.roleName}</div>
									<div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
										比例：
										<c:if test="${view == 2 }">
											<input type="text" class="ta_input twoNum"
												name="majorRoleRates[${roleSize}].allotRate"
												placeholder="0.00"
												data-rule-number="true"
												data-rule-required="true"
												data-rule-max="100"
												data-rule-min="0" 
												value="${majorRoleRate.allotRate}">
										</c:if>
										 <c:if test="${view > 2 }">
										 ${majorRoleRate.allotRate}
										 </c:if>
									</div>
									<c:if test="${view == 2 }">
										<div class="col-lg-4 text-right  col-md-4 col-sm-4 col-xs-4">
										  <input type="button" class="btn blue btn_tj" value="添加"> 
									    </div>
								    </c:if>
								</div>
								<c:set var="roleSize" value="${roleSize+1}"></c:set>
								<table class="table table-bordered">
									<thead>
										<tr class="row">
											<th class="text-center col-lg-4">姓名</th>
											<th class="text-center">工作量(%)<c:if test="${view == 2 }"><span class="required">※</span></c:if></th>
											<th class="text-center">产值</th>
										</tr>
									</thead>
									<tbody>
									 <c:set var="totalRate" value="0"></c:set>
									 <c:set var="totalYield" value="0"></c:set>
									    <c:if test="${not empty majorModel.majorRoleAllots}">
									      <c:forEach items="${majorModel.majorRoleAllots}" var="majorRoleAllot" varStatus="in">
									        <c:if test="${majorRoleAllot.roleCode == majorRoleRate.roleCode and majorRoleAllot.majorCode == majorRoleRate.majorCode}">
												<c:set var="totalRate" value="${majorRoleAllot.staffRate + totalRate}"></c:set>
												<c:set var="totalYield" value="${majorRoleAllot.staffYield + totalYield}"></c:set>
												<tr class="row roleAllot">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].wfId" value="${majorRoleAllot.wfId}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].id" value="${majorRoleAllot.id}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].majorCode" value="${majorRoleAllot.majorCode}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].majorName" value="${majorModel.majorName}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].category" value="1000">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].roleCode" value="${majorRoleAllot.roleCode}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].roleName" value="${majorRoleRate.roleName}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].staffId" value="${majorRoleAllot.staffId}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].staffSort" value="${empty majorRoleAllot.staffSort ? in.index : majorRoleAllot.staffSort}">
													<input type="hidden" name="majorRoleAllots[${rateSize}].staffYield" value="${majorRoleAllot.staffYield}">
													<td class="text-center col-lg-4">
													  ${majorRoleAllot.staffName}
													</td>
													<td class="text-right col-lg-4 input-icon left">
													<c:if test="${view == 2 }">
												    <i class="fa"></i>
													<input type="text"
														name="majorRoleAllots[${rateSize}].staffRate"
														class="form-control text-right twoNum"
														placeholder="0.00"
													    data-rule-number="true"
													    data-rule-required="true"
													    data-rule-max="100"
													    data-rule-min="0" 
														value="${majorRoleAllot.staffRate}">
													</c:if>
													<c:if test="${view > 2 }">${majorRoleAllot.staffRate}</c:if>
													</td>
													<td class=" col-lg-4 text-right">
													<fmt:formatNumber value='${majorRoleAllot.staffYield}' pattern='#,#00.00#'/>
													</td>
												</tr>
												<c:set var="rateSize" value="${rateSize + 1}"></c:set>
											</c:if>
										  </c:forEach>
										</c:if>
										<tr class="row total">
											<td class="text-center col-lg-4">合计</td>
											<td class="col-lg-4 text-right">${totalRate}</td>
											<td class=" col-lg-4 text-right">${totalYield}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					  <div class="col-lg-1"></div>
				</c:if>
			   </c:forEach>
		     </c:if>
			</div>
		  </c:forEach>
		 </c:if>
		</div>
	</div>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>	
<script type="text/javascript">
 var _this = null;
 $(function(){
	 jQuery(".btn_tj").on("click",function(){
		 _this = $(this);
		 selectStaff(selectStaffsForAdd,'checkbox');
		});
 });
 
 function selectStaffsForAdd(data){
	 var tIndex = $("form tr.roleAllot").size();
	 var _thisTable = _this.parent().parent().next();
	 var _div = _this.parent().parent();
	 var majorCode = _div.find("input[name$='.majorCode']").val();
	 var roleCode = _div.find("input[name$='.roleCode']").val();
	jQuery.each(data,function(index,item){
		var info = $(item)[0];
		var trSize = _thisTable.find("tbody tr:not(:last)").size();
		if(trSize == 0){
			var $item = jQuery("#clone_text").clone();
			$item.html($item.html().format(tIndex));
			$item.find("tr input[name$='.staffId']").val(info.id);
			$item.find("tr input[name$='.staffSort']").val(tIndex);
			$item.find("tr input[name$='.majorCode']").val(majorCode);
			$item.find("tr input[name$='.roleCode']").val(roleCode);
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
				$item.find("tr input[name$='.majorCode']").val(majorCode);
				$item.find("tr input[name$='.roleCode']").val(roleCode);
				$item.find("tr td:first").text(info.name);
				_thisTable.find(".total").before($item.find("tr"));
				tIndex++;
			}
		}
	});
  }
 
//计算产值
function countOutputValue(_tr){
	if(_tr.size()>0){
		jQuery.each(_tr,function(index,item){
			var _this = $(item).find("input[name$='.staffRate']");
			var staffRate = _this.val();
			
			  //专业角色  比例
			  var rate = _this.closest("table").prev().find("input[name$='allotRate']").val();
			  
			  //当年可结算产值(¥)
			  var yearYield = getNumValue(delcommafy(jQuery("input[name$='yearYield']").val()));
			  //项目经理 比例
			  var pmRate = getNumValue(jQuery("input[name$='pmRate']").val());
			  //项目负责人 比例
			  var principalRate = getNumValue(jQuery("input[name$='principalRate']").val());
			  
			  //本专业结算比例
			  var majorAllotRate = getNumValue(jQuery("div.tab-content div.active").find("input[name$='majorAllotRate']").val());
			  
			  var roleCode = $(item).find("input[name$='.roleCode']").val();
			  var staffYield = 0.00;
			  if("PrjMajorLeader" == roleCode){
				  // 专业负责人个人产值=当年可结算产值(¥)×(100-项目负责人比例-项目经理比例)×本专业结算比例×专业负责人比例×工作量/100000000
				  staffYield = new Number(yearYield)*(100-new Number(principalRate) - new Number(pmRate))*(new Number(majorAllotRate))*(new Number(rate))*(new Number(staffRate))/100000000;
			  }else{
				//专业负责人比例
				var allotRate = getNumValue(jQuery("input[name$='.roleCode'][value='PrjMajorLeader']").closest("div.row").find("input[name$='.allotRate']").val());  
				//校对人、审核人、设计人/制图人个人产值=当年可结算产值(¥)×(100-项目负责人比例-项目经理比例)×本专业结算比例×(100-专业负责人比例)×本角色比例×工作量/10000000000
				  staffYield = new Number(yearYield) * (100-new Number(principalRate) - new Number(pmRate))*(new Number(majorAllotRate))*(100 - new Number(allotRate))*(new Number(rate))*(new Number(staffRate))/100000000;
			  }
			  var _td = $(item).find("td:last");
			  _td.text(toThousands(new Number(staffYield).toFixed(2)));
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
		var staffRate = getNumValue(_this.find("input[name$='staffRate']").val());
		var staffYield =getNumValue(_this.find("input[name$='staffYield']").val());
	    totalstaffRate = new Number(totalstaffRate) + new Number(staffRate);
	    totalstaffYield = new Number(totalstaffYield) + new Number(staffYield);
	});
	_this.closest("table").find("tr.total").find("td:eq(1)").text(new Number(totalstaffRate).toFixed(2));
	_this.closest("table").find("tr.total").find("td:eq(2)").text(toThousands(new Number(totalstaffYield).toFixed(2)));
}
</script>