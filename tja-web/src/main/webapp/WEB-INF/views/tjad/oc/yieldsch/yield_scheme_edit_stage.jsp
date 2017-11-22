<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <h3 class="form-tit col-lg-12">各专业产值<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span></h3>
	<div class="table-scrollable">
	    <table id="majorYield" class="table table-striped table-bordered table-advance table-hover dataTable">
	        <thead>
	            <tr>
	                <th nowrap="nowrap" rowspan="2" style="text-align:center;">阶段</th>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" >
	                       <th nowrap="nowrap" colspan="2" style="text-align:center;" data-smcode="${major.configCode }">${major.configName }</th>
	                   </c:forEach>
	                </c:if>
	                <th nowrap="nowrap" rowspan="2" style="text-align:center;">产值合计</th>
	            </tr>
	            <tr>
	                <c:if test="${not empty majors }">
                       <c:forEach items="${majors }" var="major" >
                           <th nowrap="nowrap" style="text-align:center;width:80px;">比例</th>
                           <th nowrap="nowrap" style="text-align:center;width:80px;">产值</th>
                       </c:forEach>
                    </c:if>
	            </tr>
	        </thead>
            <tbody>
            	<c:forEach items="${schemeStages }" var="schemeStage" varStatus="ssVs">
		            <tr class="${schemeStage[0] }">
                        <td nowrap="nowrap" style="text-align:center;">${schemeStage[1] }</td>
                        <c:if test="${not empty majors }">
                           <c:forEach items="${majors }" var="major" varStatus="vs" >
                           	   <c:set var="ssKey" value="1000.${major.configCode }" />
                               <td nowrap="nowrap" style="width:80px;">
                               	   <c:if test="${ssVs.index == 0 }">
                                       <input type="hidden" name="majorCode${vs.index*2 }" value="${major.configCode }">
                               	   </c:if>
                                   <c:choose>
                                       <c:when test="${schemeStage[0] == 'preliminary' }">
                                           <input type="text" name="preliminary${vs.index*2 }" value="${stageMajors[ssKey].preliminary }"  data-smcode="${major.configCode }" class="form-control majorratio">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'drawing' }">
                                           <input type="text" name="drawing${vs.index*2 }" value="${stageMajors[ssKey].drawing }"  data-smcode="${major.configCode }" class="form-control majorratio">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'subTotal' }">
                                           <input type="text" name="subTotal${vs.index*2 }" value="${stageMajors[ssKey].subTotal }"  data-smcode="${major.configCode }" readonly class="form-control majorratio">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'coordination' }">
                                           <input type="text" name="coordination${vs.index*2 }" value="${stageMajors[ssKey].coordination }"  data-smcode="${major.configCode }" class="form-control majorratio">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'cap' }">
                                           <input type="text" name="cap${vs.index*2 }" value="${stageMajors[ssKey].cap }"  data-smcode="${major.configCode }" class="form-control majorratio">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'check' }">
                                           <input type="text" name="check${vs.index*2 }" value="${stageMajors[ssKey].check }"  data-smcode="${major.configCode }" class="form-control majorratio">
                                       </c:when>
                                   </c:choose>
                               </td>
                               <c:set var="ssKey" value="2000.${major.configCode }" />
                               <td nowrap="nowrap" style="text-align:right;width:80px;">
                                   <c:choose>
                                       <c:when test="${schemeStage[0] == 'preliminary' }">
                                           <input type="text" name="preliminary${vs.index*2+1 }" value="${stageMajors[ssKey].preliminary }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'drawing' }">
                                           <input type="text" name="drawing${vs.index*2+1 }" value="${stageMajors[ssKey].drawing }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'subTotal' }">
                                           <input type="text" name="subTotal${vs.index*2+1 }" value="${stageMajors[ssKey].subTotal }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'coordination' }">
                                           <input type="text" name="coordination${vs.index*2+1 }" value="${stageMajors[ssKey].coordination }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'cap' }">
                                           <input type="text" name="cap${vs.index*2+1 }" value="${stageMajors[ssKey].cap }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
                                       </c:when>
                                       <c:when test="${schemeStage[0] == 'check' }">
                                           <input type="text" name="check${vs.index*2+1 }" value="${stageMajors[ssKey].check }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
                                       </c:when>
                                   </c:choose>
                               </td>
                           </c:forEach>
                        </c:if>
                        <td nowrap="nowrap" style="text-align:center;"></td>
		            </tr>
            	</c:forEach>
	        </tbody>
	    </table>
	</div>
    <div class="col-xs-12" style="padding:10px 0px;">
		<div class="row">
	        <div class="col-md-offset-5 col-md-7">
	            <button type="button" id="save-btn" class="btn blue">保存</button>
	            <button type="button" id="cancel-btn" class="btn default">取消</button>
	        </div>
        </div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	calEachStageYieldTotal();
});
</script>