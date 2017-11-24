<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
	<h3 class="form-tit col-lg-12">项目管理产值</h3>
	<div class="col-xs-12">
	    <table id="projectYield" class="table table-striped table-bordered table-advance table-hover dataTable">
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
	    			<td><input type="text" name="principalRate" value="${yieldScheme.principalRate }" class="form-control twoProUser"></td>
	    			<td style="text-align:right;">${yieldScheme.principalYield }</td>
	    			<td style="text-align:right;">${yieldScheme.principalDDStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.principalCCOStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.principalCCTStageYield }</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align:right;">项目经理</td>
	    			<td ><input type="text" name="pmRate" value="${yieldScheme.pmRate }" class="form-control twoProUser"></td>
	    			<td style="text-align:right;">${yieldScheme.pmYield }</td>
	    			<td style="text-align:right;">${yieldScheme.pmDDStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.pmCCOStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.pmCCTStageYield }</td>
	    		</tr>
	    		<tr>
	    			<td style="text-align:right;">项目秘书</td>
	    			<td ><input type="text" name="secretRate" value="${yieldScheme.secretRate }" class="form-control twoProUser"></td>
	    			<td style="text-align:right;">${yieldScheme.secretYield }</td>
	    			<td style="text-align:right;">${yieldScheme.secretDDStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.secretCCOStageYield }</td>
	    			<td style="text-align:right;">${yieldScheme.secretCCTStageYield }</td>
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