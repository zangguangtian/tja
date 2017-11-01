<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="col-md-12 form-group">
	<div class="tabbable-line boxless tabbable-reversed">
		<ul class="nav nav-tabs">
		    <c:set var="tabSize" value="0"></c:set>
		    <c:if test="${not empty majorModels}">
		      <c:forEach items="${majorModels}" var="majorModel" varStatus="st">
			    <li <c:if test="${st.index == 0}">class="active"</c:if>><a href="#tab_${st.index}" data-toggle="tab" aria-expanded="true">${majorModel.majorName}</a></li>
			    <c:set var="tabSize" value="${tabSize+1}"></c:set>
			  </c:forEach>
			</c:if>
		</ul>
		<div class="tab-content">
		<input type="hidden" name="tabs" value="${tabSize}">
		<c:if test="${not empty majorModels}">
		<c:set var="roleSize" value="0"></c:set>
		<c:set var="rateSize" value="0"></c:set>
		   <c:forEach items="${majorModels}" var="majorModel" varStatus="st">
			<div class="tab-pane <c:if test='${st.index == 0}'>active</c:if>" id="tab_${st.index}">
			   <input type="hidden" name="majorAllotRate" value="${majorModel.majorAllotRate}">
			   <h3 class="form-tit col-lg-12">当年本专业结算比例(%)：${majorModel.majorAllotRate}</h3>
			   <c:if test="${not empty majorModel.majorRoleRates}">
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
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].roleCode" value="${majorRoleRate.roleCode}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${roleSize}].majorRateId" value="${majorRoleRate.majorRateId}">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">${majorRoleRate.roleName}</div>
									<div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
										比例：
										<c:if test="${view == 2 }">
											<input type="text" class="ta_input"
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
											<th class="text-center">工作量(%)※</th>
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
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].category" value="1000">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${rateSize}].roleCode" value="${majorRoleAllot.roleCode}">
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
														class="form-control text-right"
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
													<%-- <c:if test="${view == 2 }">
													<input type="text" placeholder="0.00" name="majorRoleAllots[${rateSize}].staffYield" class="form-control text-right" value="${majorRoleAllot.staffYield}" readonly>
													</c:if>
													<c:if test="${view > 2 }">
													  ${majorRoleAllot.staffYield}
													</c:if> --%>
													${majorRoleAllot.staffYield}
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
</script>