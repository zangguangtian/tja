<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="ym.week.menu.path" scope="request" />
	<title>项目周报-审核</title>
</head>
<body>
<div class="">
	<center>
		<h3>项目周报-${weekFill.periodName}</h3>
		<h6><fmt:formatDate pattern="yyyy年MM月dd日" value="${weekFill.rangeStart}"/> ~ <fmt:formatDate pattern="yyyy年MM月dd日" value="${weekFill.rangeEnd}"/></h6>
	</center>
	<div class="<c:if test="${not empty print }">print</c:if>">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/ym/weekFill/ajax/approve" method="post">
			
			<input type="hidden" name="id" value="${weekFill.id}">
			<input type="hidden" name="proId" value="${weekFill.proId}">
			<input type="hidden" name="periodId" value="${weekFill.periodId}">
			<input type="hidden" name="view" value="${view}">
			<input type="hidden" name="approve" value="2">
			<input type="hidden" name="auditStatus" value="${!empty weekFill.auditStatus ? weekFill.auditStatus : '0'}">
			<input type="hidden" name="procId" value="${weekFill.procId }">
			
			<div class="form-body clearfix" style="padding-bottom: 0">
				<div class="col-xs-6 ">
					<label class="control-label col-xs-4">流水号</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.seqNo}</label>
					</div>
				</div>
				<div class="col-xs-6 ">
					<label class="control-label col-xs-4"></label>
					<div class="col-xs-8 text-right">
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
					<label class="control-label col-xs-4">项目编号</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.proCode}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">项目名称</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.proName}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">项目类型</label>
					<div class="col-xs-8">
						<input type="text" class="form-control" value="${weekFill.proType}" disabled="disabled">
						<%-- <tags:config type="label" code="${weekFill.proType}"/> --%>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">项目级别</label>
					<div class="col-xs-8">
						<tags:config type="label" code="${weekFill.proGrade}"/>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">实际合同额(¥)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.contractAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">分包扣减(¥)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.pkgAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">方案扣减(¥)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.schemeAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">其他扣减(¥)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.rebateAmount}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">项目负责人</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.pmLeaders}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">项目经理</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.pManagers}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">所处状态</label>
					<div class="col-xs-8">
						<tags:config type="label" code="${weekFill.proStatus}"/>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">所处阶段</label>
					<div class="col-xs-8">
						<tags:config type="label" code="${weekFill.phaseCode}"/>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">对应阶段启动时间</label>
					<div class="col-xs-8">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${weekFill.phaseStart}"/></label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">持续时间(天)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.duration}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">当周进度(%)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.weekProgress}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">当周产值(¥)</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.weekYield}</label>
					</div>
				</div>
				
				<div class="form-group col-xs-12 ">
					<label class="control-label col-xs-2">当周工作及进展情况</label>
					<div class="col-xs-10">
						<label class="control-label">${weekFill.weekEvolve}</label>
					</div>
				</div>
				<div class="form-group col-xs-12 ">
					<label class="control-label col-xs-2">下阶段工作计划</label>
					<div class="col-xs-10">
						<label class="control-label">${weekFill.workPlan}</label>
					</div>
				</div>
				<div class="form-group col-xs-12 ">
					<label class="control-label col-xs-2">备案情况</label>
					<div class="col-xs-10">
						<label class="control-label">${weekFill.filing}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">创建人</label>
					<div class="col-xs-8">
						<label class="control-label">${weekFill.creator}</label>
					</div>
				</div>
				<div class="form-group col-xs-6 ">
					<label class="control-label col-xs-4">创建时间</label>
					<div class="col-xs-8">
						<label class="control-label"><fmt:formatDate pattern="yyyy-MM-dd" value="${weekFill.createDate}"/></label>
					</div>
				</div>
				
				<c:if test="${view == 2}">
				<div class="col-xs-12">
					<h5 class="form-tit">运营评定</h5>
				</div>
				<div class="form-group col-xs-4 ">
					<label class="control-label col-xs-4">备案情况<span class="required">※</span></label>
					<div class="col-xs-8 input-icon right">
						<i class="fa"></i>
						<tags:config name="filingEstimate" type="select" otherAttr='data-rule-required="true"' cssClass="form-control" parentCode="YM.FILING" selectCode="${weekFill.filingEstimate}"/>
					</div>
				</div>
				<div class="form-group col-xs-4 ">
					<label class="control-label col-xs-4">合同收费<span class="required">※</span></label>
					<div class="col-xs-8 input-icon right">
						<i class="fa"></i>
						<tags:config name="feeEstimate" type="select" otherAttr='data-rule-required="true"' cssClass="form-control" parentCode="YM.CONTRACT.FEE" selectCode="${weekFill.feeEstimate}"/>
					</div>
				</div>
				<div class="form-group col-xs-4 ">
					<label class="control-label col-xs-4">运营评定<span class="required">※</span></label>
					<div class="col-xs-8 input-icon right">
						<i class="fa"></i>
						<tags:config name="operationEstimate" type="select" otherAttr='data-rule-required="true"' cssClass="form-control" parentCode="YM.OPERATION" selectCode="${weekFill.operationEstimate}"/>
					</div>
				</div>
				</c:if>
				
				<c:if test="${view > 2}">
				<div class="col-xs-12">
					<h5 class="form-tit">运营评定</h5>
				</div>
				<div class="form-group col-xs-4 ">
					<label class="control-label col-xs-4">备案情况</label>
					<div class="col-xs-8">
						<tags:config type="label" cssClass="form-control" code="${weekFill.filingEstimate}"/>
					</div>
				</div>
				<div class="form-group col-xs-4 ">
					<label class="control-label col-xs-4">合同收费</label>
					<div class="col-xs-8">
						<tags:config type="label" cssClass="form-control" code="${weekFill.feeEstimate}"/>
					</div>
				</div>
				<div class="form-group col-xs-4 ">
					<label class="control-label col-xs-4">运营评定</label>
					<div class="col-xs-8">
						<tags:config type="label" cssClass="form-control" code="${weekFill.operationEstimate}"/>
					</div>
				</div>
				</c:if>

				<div class="clearfix"></div>

				<jsp:include page="../../../framework/activiti/wf_approve.jsp" flush="true"/>
		        <tags:histask procId="${weekFill.procId}"/>
    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div class="clearfix"></div>

<script type="text/javascript">
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
                success : function(data) {
                	$.jalert({"jatext":data.msg});
                    window.location.href = "${site}/admin/ym/weekFill/list";
                }
            });
        }});
    }
});

/*打印预览*/
$("#printview-btn").dfprint({
    action : "printview",
    url : "${site}/admin/ym/weekFill/toprint/v/${weekFill.id}"
});

/*打印*/
$("#print-btn").dfprint({
    action : "print"
});
</script>
</body>
</html>