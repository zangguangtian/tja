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
	<title>项目月报-审核</title>
</head>
<body>
<div class="">
	<center>
		<h3>项目预估进度月报-${monthFill.periodName}</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/ym/monthFill/ajax/approve" method="post">
			
			<input type="hidden" name="id" value="${monthFill.id}">
			<input type="hidden" name="pgCategory" value="1000">
			<input type="hidden" name="proId" value="${monthFill.proId}">
			<input type="hidden" name="periodId" value="${monthFill.periodId}">
			<input type="hidden" name="view" value="${view}">
			<input type="hidden" name="approve" value="2">
			<input type="hidden" name="auditStatus" value="${!empty monthFill.auditStatus ? monthFill.auditStatus : '0'}">
			<input type="hidden" name="procId" value="${monthFill.procId }">
			
			<div class="form-body clearfix" style="padding-bottom: 0">
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">流水号</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.seqNo}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4"></label>
					<div class="col-md-7 text-right">
						<c:if test="${canRevoke}">
							<input type="button" id="reject-btn" value="撤回" class="btn blue">
						</c:if>
			           	<c:if test="${not empty canPrintView}">
							<input type="button" id="printview-btn" value="打印预览" class="btn blue">
						</c:if>
						<c:if test="${not empty canPrint}">
							<input type="button" id="print-btn" value="打印" class="btn blue">
						</c:if>
					</div>
				</div>
			</div>
			
			<div class="form-body clearfix">
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目编号</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.proCode}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.proName}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.proType}</label>
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
						<label class="control-label">${monthFill.contractAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">分包扣减(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.pkgAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">方案扣减(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.schemeAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">其他扣减(¥)</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.rebateAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.pmLeaders}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.pManagers}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">所处状态</label>
					<div class="col-md-7">
						<tags:config type="label" code="${monthFill.itemStatus}"/>
					</div>
				</div>
				
				<div class="form-group col-xs-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">施工进度描述</label>
					<div class="col-md-9" style="width: 78%;">
						<textarea rows="3" class="form-control" disabled>${monthFill.progressExplain}</textarea>
					</div>
				</div>
				<div class="form-group col-xs-12 ">
					<label class="control-label col-md-2" style="margin-left: -0.6%;">备注</label>
					<div class="col-md-9" style="width: 78%;">
						<textarea rows="3" class="form-control" disabled>${monthFill.remark}</textarea>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">创建人</label>
					<div class="col-md-7">
						<label class="control-label">${monthFill.creator}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-md-4">创建时间</label>
					<div class="col-md-7">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${monthFill.createDate}"/></label>
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
									<input type="hidden" value="${o.id}">
									<input type="hidden" value="${o.majorCode}">
									<tags:config type="label" code="${o.majorCode}"/>
								</td>
								<td class="text-right">
									${o.completeRate}
								</td>
								<td class="text-right">
									${o.alloteRate}
								</td>
								<td class="text-right">
									${o.refYield}
								</td>
								<td class="text-right">
									${o.accRate}
								</td>
								<td class="text-right">
									${o.accYield}
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

				<jsp:include page="../../../framework/activiti/wf_approve.jsp" flush="true"/>
		        <tags:histask procId="${monthFill.procId}"/>
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

function majorHistory(majorCode){
	var url = "${site}/admin/ym/monthFill/majorhis?proId=${monthFill.proId}&majorCode="+majorCode;
    openWindow(url, "实际进度-历史", "800", "600", true, false);
}

$(document).on("click", "#reject-btn", function(){
    $("input[type='hidden'][name='approve']").val("4");
    var url = $("#approveForm").attr("action");
    if ($("#approveForm").valid()) {
    	$.jalert({"jatype":"confirm", "jatext":"确定撤回？<br>点[确定]：执行撤回操作<br>点[取消]：放弃撤回操作", "onConfirm":function(){
    		$.ajax({
                type : "POST",
                url : url,
                data : $("#approveForm").serialize(),
                async : false,
                error : function(request) {
                	$.jalert({"jatext":"Connection error"});
                },
                success : function(data) {
                	$.jalert({"jatext":data.msg});
                    window.location.href = "${site}/admin/ym/monthFill/list";
                }
            });
        }});
    }
});

/*打印预览*/
$("#printview-btn").dfprint({
    action : "printview",
    url : "${site}/admin/ym/monthFill/toprint/v/${monthFill.id}"
});

/*打印*/
$("#print-btn").dfprint({
    action : "print"
});
</script>
</body>
</html>