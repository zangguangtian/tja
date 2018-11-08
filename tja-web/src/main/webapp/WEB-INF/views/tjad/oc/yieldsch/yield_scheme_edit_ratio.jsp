<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <h3 class="form-tit col-lg-12">专业比例<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span>
        <input type="button" id="addMajor-btn" class="btn blue " value="添加" style="float: right;"></h3>
	<div class="table-scrollable">
	    <table id="majorRatio" class="table table-striped table-bordered table-advance table-hover dataTable">
	        <thead>
	            <tr>
	                <th rowspan="2" style="ext-align:center;">序号</th>
	                <th rowspan="2" style="text-align:center;" class="form-group">类型名称(系数)<span class="required">※</span></th>
	                <th rowspan="2" style="text-align:center;" class="form-group">类型编号<span class="required">※</span></th>
	                <th rowspan="2" style="text-align:center;" class="form-group">建筑面积(m<sup>2</sup>)<span class="required">※</span></th>
	                <th rowspan="2" style="text-align:center;" class="form-group">土建基准<br>单价(元/m<sup>2</sup>)</br><span class="required">※</span></th>
	                <th rowspan="2" style="text-align:center;">土建基准产值</th>
	                <th rowspan="2" style="text-align:center;">各专业产值<br>(90%)</br></th>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" >
	                       <th nowrap="nowrap" colspan="2" style="text-align:center;">${major.configName }</th>
	                   </c:forEach>
	                </c:if>
	                <th rowspan="2" style="text-align:center;">操作</th>
	            </tr>
	            <tr>
					<c:if test="${not empty majors }">
	                    <c:forEach items="${majors }" var="major" >
	                        <th nowrap="nowrap" class="form-group" style="text-align:center;width:80px;">比例<span class="required">※</span></th>
	                        <th nowrap="nowrap" style="text-align:center;width:80px;">产值</th>
	                    </c:forEach>
	                </c:if>
	            </tr>
	        </thead>
			<tbody>
				<c:if test="${not empty yieldMajors }">
					<c:forEach items="${yieldMajors }" var="yieldMajor" varStatus="vs">
					<tr>
					    <td nowrap="nowrap" style="text-align:center;">${vs.index + 1}</td>
					    <td nowrap="nowrap" class="form-group">
					    	<input type="hidden" name="id${vs.index}" value="${yieldMajor.id }">
					    	<input type="text" name="name${vs.index}" value="${yieldMajor.name }" class="form-control" data-rule-required="true">
					    </td>
					    <td nowrap="nowrap" class="form-group" style="width:100px;">
					       <select name="priceId${vs.index}" class="form-control" data-rule-required="true">
					           <option value="">-请选择-</option>
					       <c:if test="${not empty prices }">
					           <c:forEach items="${prices }" var="price">
					           	   <c:if test="${price.priceId == yieldMajor.priceId }">
						               <option value="${price.priceId }" selected data-uprice="${price.standardPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
					           	   </c:if>
					           	   <c:if test="${price.priceId != yieldMajor.priceId }">
						               <option value="${price.priceId }" data-uprice="${price.standardPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
					           	   </c:if>
					           </c:forEach>
					       </c:if>
					       </select>
					    </td>
					    <td nowrap="nowrap" class="form-group"><input type="text" name="buildArea${vs.index}" value="${yieldMajor.buildArea }" data-rule-number="true" data-rule-required="true" class="form-control"></td>
					    <td nowrap="nowrap" class="form-group"><input type="text" name="standardPrice${vs.index}" value="${yieldMajor.standardPrice }" data-rule-number="true" data-rule-required="true" class="form-control"></td>
					    <td nowrap="nowrap" id="sYield${vs.index}" style="text-align:right;">${yieldMajor.standardYield }</td>
					    <td nowrap="nowrap" id="mYield${vs.index}" style="text-align:right;">${yieldMajor.majorYield }</td>
					    <c:if test="${not empty majors }">
					       <c:forEach items="${majors }" var="major" >
						       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
					           <td nowrap="nowrap" class="form-group" style="width:80px;">
							   		<input type="text" name="majorRate${vs.index}.${major.configCode }" value="${yieldMajor.majorMap[majorKey].majorRate }" data-major="${major.configCode }" data-rule-number="true" data-rule-required="true" class="form-control">
					           </td>
					           <td nowrap="nowrap" id="majorYield${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorYield }</td>
					       </c:forEach>
					    </c:if>
					    <td style="text-align:center;"><i class="fa fa-trash-o del-btn"></i></td>
					</tr>
					</c:forEach>
				</c:if>
            	<tr class="minus">
	                <td nowrap="nowrap" colspan="3" style="text-align:center;">专业扣减</td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	                <td nowrap="nowrap"></td>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" varStatus="vs">
	                       <td nowrap="nowrap" colspan="2">
	                       	 <input type="text" name="minusYield${vs.index}.${major.configCode }" value="" data-major="${major.configCode }" data-rule-number="true" data-rule-required="true" class="form-control">
	                       </td>
	                   </c:forEach>
	                </c:if>
	                <td>&nbsp;</td>
            	</tr>
            	<tr class="total">
	                <td nowrap="nowrap" colspan="3" style="text-align:center;">院内合计</td>
	                <td nowrap="nowrap" id="totalArea"></td>
	                <td nowrap="nowrap" id="totalUnitPrice"></td>
	                <td nowrap="nowrap" id="totalSYield"></td>
	                <td nowrap="nowrap" id="totalMYield"></td>
	                <c:if test="${not empty majors }">
	                   <c:forEach items="${majors }" var="major" >
	                       <td nowrap="nowrap" colspan="2" data-major="${major.configCode}"></td>
	                   </c:forEach>
	                </c:if>
	                <td>&nbsp;</td>
            	</tr>
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
<table id="majorRatio_clone" style="display:none;">
    <tr>
	    <td nowrap="nowrap" style="text-align:center;">{0}</td>
	    <td nowrap="nowrap" class="form-group"><input type="text" name="name{0}" value="" disabled class="form-control" data-rule-required="true"></td>
	    <td nowrap="nowrap" class="form-group" style="width:100px;">
	       <select name="priceId{0}" class="form-control" disabled data-rule-required="true">
	           <option value="">-请选择-</option>
	       <c:if test="${not empty prices }">
	           <c:forEach items="${prices }" var="price">
	               <option value="${price.priceId }" data-uprice="${price.standardPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
	           </c:forEach>
	       </c:if>
	       </select>
	    </td>
	    <td nowrap="nowrap" class="form-group"><input type="text" name="buildArea{0}" value="" disabled data-rule-required="true" data-rule-number="true" class="form-control"></td>
	    <td nowrap="nowrap" class="form-group"><input type="text" name="standardPrice{0}" value="" disabled data-rule-number="true" data-rule-required="true" class="form-control"></td>
	    <td nowrap="nowrap" id="sYield{0}" style="text-align:right;"></td>
	    <td nowrap="nowrap" id="mYield{0}" style="text-align:right;"></td>
	    <c:if test="${not empty majors }">
	       <c:forEach items="${majors }" var="major" >
	       	   <td nowrap="nowrap" class="form-group" style="width:80px;">
			   		<input type="text" name="majorRate{0}.${major.configCode }" value="" disabled data-major="${major.configCode }" data-rule-number="true" data-rule-required="true" class="form-control">
	           </td>
	           <td nowrap="nowrap" id="majorYield{0}.${major.configCode }" data-major="${major.configCode }"></td>
	       </c:forEach>
	    </c:if>
	    <td style="text-align:center;"><i class="fa fa-trash-o del-btn"></i></td>
	</tr>
</table>
<script type="text/javascript">
$(function(){
	/**打开就计算院内合计*/
	calYLTotal();
	//添加专业比例记录
	$("#addMajor-btn").on("click", addMajorRatio);
});

/**添加专业比例*/
function addMajorRatio(){
	var trSize = $("#majorRatio tbody tr:not(:last)").length;
	var $item = $("#majorRatio_clone tr").clone();
	$item.find("input,select").removeAttr("disabled");
	$item.html($item.html().format(trSize));
	$item.find("td:eq(0)").text(trSize + 1);
	
	$("#majorRatio tr.total").before($item);
}
</script>
