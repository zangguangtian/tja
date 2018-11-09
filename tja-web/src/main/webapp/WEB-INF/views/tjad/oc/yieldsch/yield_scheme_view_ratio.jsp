<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	<div class="col-xs-12">
		<h5 class="form-tit">专业比例
			<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span>
			<sec:authorize url="/admin/yield/scheme/ajaxhtml/ratioEdit"><i class="fa fa-edit"></i></sec:authorize>
			<i id="more-btn" class="fa fa-chevron-down font-icon"><h6>展开</h6></i>
		</h5>
	</div>
	<div class="col-xs-12">
	    <table id="majorRatio" class="table table-striped table-bordered table-advance table-hover dataTable">
	        <thead>
	            <tr>
	                <th style="text-align:center;">类型名称(系数)</th>
	                <th style="text-align:center;">类型编号</th>
	                <th style="text-align:center;">建筑面积(m<sup>2</sup>)</th>
	                <th style="text-align:center;">土建基准<br>单价(元/m<sup>2</sup>)</br></th>
	                <th style="text-align:center;">土建基准产值</th>
	                <th style="text-align:center;">各专业产值<br>(90%)</br></th>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" >
	                       <th style="text-align:center;">${major.configName }</th>
	                   </c:forEach>
	                </c:if>
	            </tr>
	        </thead>
	        <tbody>
	        	<c:if test="${not empty yieldMajors }">
	        		<c:forEach items="${yieldMajors }" var="yieldMajor" varStatus="vs">
	        			<tr style="display:none;">
						    <td rowspan="2">${yieldMajor.name }</td>
						    <td rowspan="2">${yieldMajor.typeCode }</td>
						    <td rowspan="2" class="buildArea">${yieldMajor.buildArea }</td>
						    <td rowspan="2">${yieldMajor.standardPrice }</td>
						    <td rowspan="2" class="standardYield">${yieldMajor.standardYield }</td>
						    <td rowspan="2" class="majorTotalYield">${yieldMajor.majorYield }</td>
						    <c:if test="${not empty majors }">
						       <c:forEach items="${majors }" var="major" >
							       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
						           <td id="majorRatio${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorRate }</td>
						       </c:forEach>
						    </c:if>
						</tr>
						<tr style="display:none;">
							<c:if test="${not empty majors }">
						       <c:forEach items="${majors }" var="major" >
							       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
						           <td id="majorYield${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorYield }</td>
						       </c:forEach>
						    </c:if>
						</tr>
	        		</c:forEach>
	        	</c:if>
	        	
	            <tr class="minus" style="display:none;">
	                <td nowrap="nowrap" colspan="2" style="text-align:center;">专业扣减</td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	                <c:if test="${not empty majors }">
						<c:forEach items="${majors }" var="major" varStatus="vs">
							<td nowrap="nowrap" data-major="${major.configCode}">${yieldDuties[major.configCode].minusYield }</td>
					    </c:forEach>
					</c:if>
	            </tr>
	            <tr class="total">
	                <td nowrap="nowrap" colspan="2" style="text-align:center;">院内合计</td>
	                <td nowrap="nowrap" id="totalArea"></td>
	                <td nowrap="nowrap" id="totalUnitPrice"></td>
	                <td nowrap="nowrap" id="totalSYield"></td>
	                <td nowrap="nowrap" id="totalMYield"></td>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" >
	                       <td nowrap="nowrap" data-major="${major.configCode}"></td>
	                   </c:forEach>
	                </c:if>
	            </tr>
	        </tbody>
	    </table>
	</div>