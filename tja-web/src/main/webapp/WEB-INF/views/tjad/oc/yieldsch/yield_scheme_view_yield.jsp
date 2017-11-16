<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	<div class="col-xs-12">
		<h5 class="form-tit">各专业产值<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span><i class="fa fa-edit"></i></h5>
	</div>
	<div class="col-xs-12">
	    <table id="majorYield" class="table table-striped table-bordered table-advance table-hover dataTable">
	        <thead>
	            <tr>
	                <th nowrap="nowrap" style="text-align:center;">阶段</th>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" >
	                       <th nowrap="nowrap" style="text-align:center;" data-smcode="${major.configCode }">${major.configName }</th>
	                   </c:forEach>
	                </c:if>
	                <th nowrap="nowrap" style="text-align:center;">产值合计</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<c:forEach items="${schemeStages }" var="schemeStage" varStatus="ssVs">
	        		<tr>
	        			<td rowspan="2" style="text-align:center;">${schemeStage[1] }</td>
	        			<c:if test="${not empty majors }">
	        				<c:forEach items="${majors }" var="major" varStatus="vs" >
	        					<c:set var="ssKey" value="1000.${major.configCode }" />
	        					<td style="text-align:right;">
	                               <c:choose>
	                                   <c:when test="${schemeStage[0] == 'preliminary' }">${stageMajors[ssKey].preliminary }</c:when>
	                                   <c:when test="${schemeStage[0] == 'drawing' }">${stageMajors[ssKey].drawing }</c:when>
	                                   <c:when test="${schemeStage[0] == 'subTotal' }">${stageMajors[ssKey].subTotal }</c:when>
	                                   <c:when test="${schemeStage[0] == 'coordination' }">${stageMajors[ssKey].coordination }</c:when>
	                                   <c:when test="${schemeStage[0] == 'cap' }">${stageMajors[ssKey].cap }</c:when>
	                                   <c:when test="${schemeStage[0] == 'check' }">${stageMajors[ssKey].check }</c:when>
	                               </c:choose>
	                           </td>
	        				</c:forEach>
	        			</c:if>
	        			<td rowspan="2"></td>
	        		</tr>
	        		<tr>
	        			<c:if test="${not empty majors }">
	        				<c:forEach items="${majors }" var="major" varStatus="vs" >
	        					<c:set var="ssKey" value="2000.${major.configCode }" />
	                            <td style="text-align:right;">
	                               <c:choose>
	                                   <c:when test="${schemeStage[0] == 'preliminary' }">${stageMajors[ssKey].preliminary }</c:when>
	                                   <c:when test="${schemeStage[0] == 'drawing' }">${stageMajors[ssKey].drawing }</c:when>
	                                   <c:when test="${schemeStage[0] == 'subTotal' }">${stageMajors[ssKey].subTotal }</c:when>
	                                   <c:when test="${schemeStage[0] == 'coordination' }">${stageMajors[ssKey].coordination }</c:when>
	                                   <c:when test="${schemeStage[0] == 'cap' }">${stageMajors[ssKey].cap }</c:when>
	                                   <c:when test="${schemeStage[0] == 'check' }">${stageMajors[ssKey].check }</c:when>
	                               </c:choose>
	                           </td>
	        				</c:forEach>
	        			</c:if>
	        		</tr>
	        	</c:forEach>
	        </tbody>
	    </table>
	</div>