<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="col-md-12">
	<div class="tabbable-line boxless tabbable-reversed">
		<ul class="nav nav-tabs">
		    <c:if test="${not empty majorModels}">
		      <c:forEach items="${majorModels}" var="majorModel" varStatus="st">
			    <li class="active"><a href="#tab_${st.index}" data-toggle="tab" aria-expanded="true">${majorModel.majorName}</a></li>
			  </c:forEach>
			</c:if>
		</ul>
		<div class="tab-content">
		<c:if test="${not empty majorModels}">
		   <c:forEach items="${majorModels}" var="majorModel" varStatus="st">
			<div class="tab-pane active" id="tab_${st.index}">
			   <c:if test="${not empty majorModels.majorRoleRates}">
			      <c:forEach items="${majorModels.majorRoleRates}" var="majorRoleRate" varStatus="st">
			        <c:if test="${majorRoleRate.majorCode == majorModel.majorCode}">
						<div class="col-lg-5">
							<div class="">
								<div class="row">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${st.index}].wfId" value="${majorRoleRate.wfId}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${st.index}].id" value="${majorRoleRate.id}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${st.index}].majorCode" value="${majorRoleRate.majorCode}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${st.index}].roleCode" value="${majorRoleRate.roleCode}">
								    <input type="hidden" class="ta_input" name="majorRoleRates[${st.index}].majorRateId" value="${majorRoleRate.majorRateId}">
									<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">${majorRoleRate.roleName}</div>
									<div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
										比例：
										<c:if test="${view == 2 }">
										 <input type="text" class="ta_input" value="${majorRoleRate.allotRate}">
										 </c:if>
										 <c:if test="${view > 2 }">
										 ${majorRoleRate.allotRate}
										 </c:if>
									</div>
									<div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
										<button class="btn green btn_tj">
											添加 <i class="fa fa-plus"></i>
										</button>
									</div>
								</div>
								<table class="table table-bordered">
									<thead>
										<tr class="row">
											<th class="text-center col-lg-4">姓名</th>
											<th class="text-center">工作量(%)※</th>
											<th class="text-center">产值</th>
										</tr>
									</thead>
									<tbody>
									    <c:if test="${not empty majorModels.majorRoleAllots}">
									      <c:forEach items="${majorModels.majorRoleAllots}" var="majorRoleAllot" varStatus="in">
									        <c:if test="${majorRoleAllot.roleCode == majorRoleRate.roleCode and majorRoleAllot.majorCode == majorRoleRate.majorCode}">
												<tr class="row">
												<input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].wfId" value="${majorRoleRate.wfId}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].id" value="${majorRoleRate.id}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].majorCode" value="${majorRoleRate.majorCode}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].category" value="1000">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].roleCode" value="${majorRoleRate.roleCode}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].staffId" value="${majorRoleRate.staffId}">
												    <input type="hidden" class="ta_input" name="majorRoleAllots[${st.index}].staffSort" value="${empty majorRoleRate.staffSort ? in.index : majorRoleRate.staffSort}">
													<td class="text-center col-lg-4">
													  ${majorRoleAllot.staffName}
													</td>
													<td class=" col-lg-4">
													<c:if test="${view == 2 }">
													 <input type="text" name="majorRoleAllots[${in.index}].staffRate" placeholder="0.00" class="text-right" value="${majorRoleAllot.staffRate}">
													</c:if>
													<c:if test="${view > 2 }">${majorRoleAllot.staffRate}</c:if>
													</td>
													<td class=" col-lg-4 text-right">
													<c:if test="${view == 2 }">
													<input type="text" placeholder="0.00" name="majorRoleAllots[${in.index}].staffYield" class="text-right" value="${majorRoleAllot.staffYield}">
													</c:if>
													<c:if test="${view > 2 }">
													  ${majorRoleAllot.staffYield}
													</c:if>
													</td>
												</tr>
											</c:if>
										  </c:forEach>
										</c:if>
										<tr class="row">
											<td class="text-center col-lg-4">合计</td>
											<td class="col-lg-4 text-right">00000</td>
											<td class=" col-lg-4 text-right">411027</td>
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