<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="ym.month.menu.path" scope="request" />
	<title>项目月报-填报</title>
</head>
<body>
<div class="">
	<center>
		<h3>项目预估进度月报-${monthFill.periodName}</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="saveForm">
			
			<input type="hidden" name="id" value="${monthFill.id}">
			<input type="hidden" name="pgCategory" value="1000">
			<input type="hidden" name="proId" value="${monthFill.proId}">
			<input type="hidden" name="periodId" value="${monthFill.periodId}">
			<input type="hidden" name="view" value="${view}">
			<input type="hidden" name="approve" value="">
			<input type="hidden" name="auditStatus" value="${!empty monthFill.auditStatus ? monthFill.auditStatus : '0'}">
			<input type="hidden" name="procId" value="${monthFill.procId }">
			
			<div class="form-body clearfix" style="padding-bottom: 0">
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">流水号</label>
					<div class="col-md-7">
						<c:set var="seqTip" value="(保存后自动生成)"></c:set>
						<input type="text" class="form-control" value="${empty monthFill.id ? seqTip : monthFill.seqNo}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4"></label>
					<div class="col-md-7 text-right">
						<c:if test="${monthFill.canDel}">
			                <input type="button" class="btn blue save" value="删除" onclick="save(9)">
			            </c:if> 
		                <c:if test="${empty monthFill.procId}">
		                    <input type="button" class="btn blue save" value="保存" onclick="save(0)">
		                    <input type="button" class="btn blue submit" value="提交" onclick="save(1)">
		                </c:if>
		                <c:if test="${not empty monthFill.procId and monthFill.auditStatus!='1' and monthFill.auditStatus!='2'}">
							<input type="button" class="btn blue save" value="重新提交" onclick="save(1)">
			           	</c:if>
					</div>
				</div>
			</div>
			
			<div class="form-body clearfix">
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目编号</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${monthFill.proCode}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${monthFill.proName}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${monthFill.proType}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目级别</label>
					<div class="col-md-7">
						<tags:config type="label" code="${monthFill.proGrade}"/>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">实际合同额(¥)</label>
					<div class="col-md-7">
						<input name="contractAmount" type="hidden" value="${monthFill.contractAmount}">
						<input type="text" class="form-control" value="${monthFill.contractAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">分包扣减(¥)</label>
					<div class="col-md-7">
						<input name="pkgAmount" type="hidden" value="${monthFill.pkgAmount}">
						<input type="text" class="form-control" value="${monthFill.pkgAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">方案扣减(¥)</label>
					<div class="col-md-7">
						<input name="schemeAmount" type="hidden" value="${monthFill.schemeAmount}">
						<input type="text" class="form-control" value="${monthFill.schemeAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">其他扣减(¥)</label>
					<div class="col-md-7">
						<input name="rebateAmount" type="hidden" value="${monthFill.rebateAmount}">
						<input type="text" class="form-control" value="${monthFill.rebateAmount}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${monthFill.pmLeaders}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<input type="text" class="form-control" value="${monthFill.pManagers}" disabled="disabled">
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">所处状态<span class="required">※</span></label>
					<div class="col-md-7 input-icon right">
						<i class="fa"></i>
						<tags:config type="select" name="itemStatus" otherAttr='data-rule-required="true"' cssClass="form-control" parentCode="PM.STATUS" selectCode="${monthFill.itemStatus}"/>
					</div>
				</div>
				
				<div class="form-group col-xs-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">施工进度描述</label>
					<div class="col-md-9" style="width: 78%;">
						<textarea name="progressExplain" rows="3" class="form-control">${monthFill.progressExplain}</textarea>
					</div>
				</div>
				<div class="form-group col-xs-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">备注</label>
					<div class="col-md-9" style="width: 78%;">
						<textarea name="remark" rows="3" class="form-control">${monthFill.remark}</textarea>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">创建人</label>
					<div class="col-md-7">
						<input type="text" class="form-control" 
						       value="${empty monthFill.creator ? SysUser.realName : monthFill.creator}" 
						       disabled="disabled"> 
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">创建时间</label>
					<div class="col-md-7">
						<input type="text" class="form-control" 
							   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${empty monthFill.createDate ? currentDate : monthFill.createDate}" />'
							   disabled="disabled"> 
					</div>
				</div>
				
				<h5 class="form-tit col-xs-12">专业进度</h5>
				<div class="col-xs-12">
					<table id="majorTable" class="table table-bordered edit">
						<thead>
							<tr>
								<th class="text-center col-md-2">专业</th>
								<th class="text-center col-md-1">当月完成进度比例(%)</th>
								<th class="text-center col-md-1">专业分配比例(%)</th>
								<th class="text-center col-md-2">对应产值(¥)</th>
								<th class="text-center col-md-1">累计进度比例(%)</th>
								<th class="text-center col-md-2">累计产值(¥)</th>
								<th class="text-center col-md-2">剩余产值(¥)</th>
								<th class="text-center col-md-1">历史进度</th>
							</tr>
						</thead>
						<tbody>
						<c:if test="${fn:length(monthFill.majorProgressList) > 0}">
						<c:forEach items="${monthFill.majorProgressList}" var="o" varStatus="s">
							<tr>
								<td class="text-center">
									<input name="majorProgressList[${s.index}].id" type="hidden" value="${o.id}">
									<input name="majorProgressList[${s.index}].majorCode" type="hidden" value="${o.majorCode}">
									<tags:config type="label" code="${o.majorCode}"/>
								</td>
								<td class="text-right">
									<input name="majorProgressList[${s.index}].completeRate" type="text" placeholder="0.00" data-rule-number="true" class="form-control text-right" value="${o.completeRate}">
								</td>
								<td class="text-right">
									${o.alloteRate}
									<input name="majorProgressList[${s.index}].alloteRate" type="hidden" value="${o.alloteRate}">
								</td>
								<td class="text-right">
									<input name=".refYield" type="text" class="text-right" value="${o.refYield}" disabled>
									<input name="majorProgressList[${s.index}].refYield" type="hidden" value="${o.refYield}">
								</td>
								<td class="text-right">
									<input name=".accRate" type="text" class="text-right" value="${o.accRate}" title="${o.accRate}" disabled>
									<input name="majorProgressList[${s.index}].accRate" type="hidden" value="${o.accRate}">
								</td>
								<td class="text-right">
									<input name=".accYield" type="text" class="text-right" value="${o.accYield}" title="${o.accYield}" disabled>
									<input name="majorProgressList[${s.index}].accYield" type="hidden" value="${o.accYield}">
								</td>
								<td class="text-right">
									<input name=".otherYield" type="text" class="text-right" value="" disabled>
								</td>
								<td class="text-center"><a href="javascript:void(0)" onclick="majorHistory('${o.majorCode}')">查看</a></td>
							</tr>
						</c:forEach>
							<tr>
								<td class="text-center">合计</td>
								<td class="text-right"></td>
								<td class="text-right"></td>
								<td class="text-right"></td>
								<td class="text-right"></td>
								<td class="text-right"></td>
								<td class="text-right"></td>
								<td class="text-center"></td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
				
    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div class="clearfix"></div>

<script type="text/javascript">
var ocRebate = "${ocRebate}"; //产值计算系数
var principalRate = "${principalRate}"||0; //项目负责人分配比例
var pmRate = "${pmRate}"||0; //项目经理分配比例

var contractAmount = parseFloat($("input[name='contractAmount']").val())||0; //实际合同额
var pkgAmount = parseFloat($("input[name='pkgAmount']").val())||0; //分包扣减
var schemeAmount = parseFloat($("input[name='schemeAmount']").val())||0; //方案扣减
var rebateAmount = parseFloat($("input[name='rebateAmount']").val())||0; //其他扣减

var YIELDCAL = ((contractAmount-pkgAmount-schemeAmount-rebateAmount)*ocRebate*(100-principalRate-pmRate)/100).toFixed(2);
//对应产值(¥) = YIELDCAL*专业分配比例/100*当月完成进度比例/100
//剩余产值(¥) = YIELDCAL*专业分配比例/100-累计产值

$(function(){
	getSum();
	$("#majorTable").find("input[name$='.completeRate']").each(function(index,element){
		$(element).on("keyup", function(){
			inCompleteRate(element);
		});
	});
});

function getSum(){
	var sum_alloteRate = 0,
		sum_refYield = 0,
		sum_accYield = 0,
		sum_otherYield = 0;
	
	$("#majorTable").find("tbody tr").each(function(index,element){
		var alloteRate = parseFloat($(element).find("input[name$='.alloteRate']").val())||0; //专业分配比例
		var refYield = parseFloat($(element).find("input[name$='.refYield']").val())||0; //对应产值
		var accYield = parseFloat($(element).find("input[name$='.accYield']").val())||0; //累计产值
		var otherYield = YIELDCAL*alloteRate/100-accYield; //剩余产值(¥) = YIELDCAL*专业分配比例/100-累计产值
		
		$(element).find("input[name$='.otherYield']").val(otherYield.toFixed(2));
		
		sum_alloteRate += alloteRate;
		sum_refYield += refYield;
		sum_accYield += accYield;
		sum_otherYield += otherYield;
	});
	
	$("#majorTable tr:last").find("td:eq(2)").text(sum_alloteRate.toFixed(2));
	$("#majorTable tr:last").find("td:eq(3)").text(sum_refYield.toFixed(2));
	$("#majorTable tr:last").find("td:eq(5)").text(sum_accYield.toFixed(2));
	$("#majorTable tr:last").find("td:eq(6)").text(sum_otherYield.toFixed(2));
}

function inCompleteRate(element){
	var completeRate = parseFloat($(element).val())||0; //当月完成进度比例
	var alloteRate = parseFloat($(element).closest("tr").find("input[name$='.alloteRate']").val())||0; //专业分配比例
	var accRate = parseFloat($(element).closest("tr").find("input[name$='.accRate']").attr("title"))||0; //累计进度比例
	var accYield = parseFloat($(element).closest("tr").find("input[name$='.accYield']").attr("title"))||0; //累计产值
	var theRestRate = (10000-accRate*100)/100; //剩余比例
	
	if(completeRate < 0) $(element).val(0.00);
	if(completeRate > theRestRate) $(element).val(theRestRate);
	completeRate = parseFloat($(element).val())||0;
	
	var refYield = YIELDCAL*alloteRate/100*completeRate/100; //对应产值(¥) = YIELDCAL*专业分配比例/100*当月完成进度比例/100
	accRate += completeRate;
	accYield += refYield;
	
	$(element).closest("tr").find("input[name$='.refYield']").val(refYield.toFixed(2));
	$(element).closest("tr").find("input[name$='.accRate']").val(accRate.toFixed(2));
	$(element).closest("tr").find("input[name$='.accYield']").val(accYield.toFixed(2));
	
	getSum();
}

function majorHistory(majorCode){
	var url = "${site}/admin/ym/monthFill/majorhis?proId=${monthFill.proId}&majorCode="+majorCode;
    openWindow(url, "实际进度-历史", "800", "600", true, false);
}

function save(status){
    if(status == "9"){
    	$.jalert({"jatext":"确认删除？<br>点[确定]：执行删除操作<br>点[取消]：放弃删除操作", "jatype":"confirm", "onConfirm":function(){
        	ajaxSava(status);
        }});
    }else{
    	if($("#saveForm").valid()){
    		ajaxSava(status);
        }
    }
}

function ajaxSava(status){
	$("input[name='auditStatus']").val(status);
	var url ="${site}/admin/ym/monthFill/ajax/save";
	$.ajax({
		type : "post",
	 	url : url,
	 	async : false,
	 	data : $("#saveForm").serialize(),
	 	error : function(request) {
	 		$.jalert({"jatext":"Connection error"});
	 	},
	 	success : function(data) {
	 		if(data.flag == "true"){
	 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
	 				var backUrl = (status == "9" ? "${site}/admin/ym/monthFill/list" : "${site}/admin/ym/monthFill/toedit/"+data.id+"?proId=${monthFill.proId}&periodId=${monthFill.periodId}");
			  		window.location.href = backUrl;
	 			}});
	 		}else{
	 			$.jalert({"jatext":data.msg});
	 		}
	 	}
	});
}
</script>
</body>
</html>