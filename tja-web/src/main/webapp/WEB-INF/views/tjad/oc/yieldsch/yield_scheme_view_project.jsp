<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	<div class="col-xs-12">
		<h5 class="form-tit">项目管理产值<span class="control-label" style="font-size:12px;">（元）</span>
			<sec:authorize url="/admin/yield/scheme/ajax/projectEdit"><i class="fa fa-edit"/></sec:authorize></h5>
	</div>
	<div class="col-xs-12">
	    <table id="majorYield" class="table table-striped table-bordered table-advance table-hover dataTable">
	    	<thead>
	            <tr>
	            	<th>&nbsp;</th>
	                <th nowrap="nowrap" style="text-align:center;">比例（%）</th>
	                <th nowrap="nowrap" style="text-align:center;">产值</th>
	                <th nowrap="nowrap" style="text-align:center;">初设施工图阶段</th>
	                <th nowrap="nowrap" style="text-align:center;">施工配合阶段1</th>
	                <th nowrap="nowrap" style="text-align:center;">施工配合阶段2</th>
	            </tr>
	        </thead>
	    	<tbody>
	    		<tr>
	    			<td style="text-align:right;">项目负责人</td>
	    			<td style="text-align:right;">${yieldScheme.principalRate }</td>
	    			<td style="text-align:right;">${yieldScheme.principalYield }</td>
	    			<td style="text-align:right;">${yieldScheme.principalDDStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.principalCCOStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.principalCCTStageYield }</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align:right;">项目经理</td>
	    			<td style="text-align:right;">${yieldScheme.pmRate }</td>
	    			<td style="text-align:right;">${yieldScheme.pmYield }</td>
	    			<td style="text-align:right;">${yieldScheme.pmDDStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.pmCCOStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.pmCCTStageYield }</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align:right;">项目秘书</td>
	    			<td style="text-align:right;">${yieldScheme.secretRate }</td>
	    			<td style="text-align:right;">${yieldScheme.secretYield }</td>
	    			<td style="text-align:right;">${yieldScheme.secretDDStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.secretCCOStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.secretCCTStageYield }</td>
	    		</tr>
	    	</tbody>
	    </table>
	</div>
