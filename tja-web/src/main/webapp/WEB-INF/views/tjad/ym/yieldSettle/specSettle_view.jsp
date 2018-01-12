<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>年度产值结算-特批</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="ym.settle.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
	<center>
		<h3>年度产值结算-${periodManage.periodName}</h3>
	</center>
	<div class="<c:if test="${not empty print }">print</c:if>">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/ym/yieldSettle/ajax/approve" method="post" class="row">
				<input type="hidden" name="wfCategory" value="2000">
				<input type="hidden" name="id" value="${yieldSettle.id}">
		        <input type="hidden" name="procId" value="${yieldSettle.procId }">
		        <input type="hidden" name="approve" value="2"/>
				
				<div class="form-body clearfix" style="padding-bottom: 0">
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">流水号</label>
						<div class="col-xs-8">
							<label class="control-label">${yieldSettle.seqNo}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
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
					<div class="form-group col-xs-6">
						<label class="control-label col-xs-4">项目编号</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proCode}</label>
						</div>
					</div>
					<div class="form-group col-xs-6">
						<label class="control-label col-xs-4">项目名称</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proName}</label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">合同编号</label>
						<div class="col-xs-8">
							<label class="control-label">${project.contractCode}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目类型</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proType}</label>
						</div>
					</div>
					
					<div class="form-group col-xs-6">
						<label class="control-label col-xs-4">合同额(¥)</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatNumber value='${yieldSettle.contractAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-xs-6">
						<label class="control-label col-xs-4">分包扣减(¥)</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatNumber value='${yieldSettle.pkgAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-xs-6">
						<label class="control-label col-xs-4">方案扣减(¥)</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatNumber value='${yieldSettle.schemeAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">其他扣减(¥)</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatNumber value='${yieldSettle.rebateAmount}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目负责人</label>
						<div class="col-xs-8">
							<label class="control-label">${project.pmLeaders}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目经理</label>
						<div class="col-xs-8">
			            	<label class="control-label">${project.pManagers}</label>
						</div>
					</div>
					
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">当年特批产值(¥)</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatNumber value='${yieldSettle.yearYield}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4"></label>
						<div class="col-xs-8">
						</div>
					</div>
					
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">创建人</label>
						<div class="col-xs-8">
						<label class="control-label">${yieldSettle.creatorName}</label>
						</div>
					</div>
					<div class="form-group col-xs-6">
						<label class="control-label col-xs-4">创建时间</label>
						<div class="col-xs-8">
						 <jsp:useBean id="currentDate" class="java.util.Date"/>
	            		 <fmt:formatDate value="${yieldSettle.createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="currentDate"/>
						 <label class="control-label">${currentDate }</label>
						</div>
					</div>
					
	                <div class="col-xs-12">
						<h5 class="form-tit">${majorName}</h5>
					</div>
					<div class="col-lg-5 ">
						<table class="table table-bordered edit" id="majorRole">
							<thead>
								<tr>
									<th  class="text-center col-xs-2">姓名</th>
									<th class="text-center col-xs-2">工作量(%)</th>
									<th class="text-center col-xs-2">产值</th>
								</tr>
							</thead>
							<tbody>
							    <c:set value="total_rate" var="0"></c:set>
							    <c:set value="total_yield" var="0"></c:set>
							    <c:if test="${not empty majorRoleAllots}">
							      <c:forEach var="majorRoleAllot" items="${majorRoleAllots}" varStatus="st">
							        <tr>
										<td  class="text-center">${majorRoleAllot.staffName }</td>
										<td  class=" text-right">${majorRoleAllot.staffRate}</td>
										<td  class="text-right">
										 <fmt:formatNumber value='${majorRoleAllot.staffYield}' pattern='#,#00.00#'/>
										</td>
									</tr>
									<c:set value="${total_rate + majorRoleAllot.staffRate}" var="total_rate"></c:set>
							        <c:set value="${total_yield + majorRoleAllot.staffYield}" var="total_yield"></c:set>
							      </c:forEach>
							    </c:if>
								<tr class="total">
									<td  class="text-center">合计</td>
									<td  class="text-right">${total_rate}</td>
									<td  class="text-right">${total_yield}</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<jsp:include page="../../../framework/activiti/wf_approve.jsp" flush="true"/>
	                <tags:histask procId="${yieldSettle.procId }"/>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">

	$(document).on("click", "#reject-btn", function(){
	    jQuery("input[type='hidden'][name='approve']").val("4");
	    var url = jQuery("#approveForm").attr("action");
	    if (jQuery("#approveForm").valid()) {
	    	jQuery.jalert({"jatype":"confirm", "jatext":"确定撤回？<br>点[确定]：执行撤回操作<br>点[取消]：放弃撤回操作", "onConfirm":function(){
	    		jQuery.ajax({
	                type : "POST",
	                url : url,
	                data : jQuery("#approveForm").serialize(),
	                async : false,
	                success : function(data) {
	                	$.jalert({"jatext":data.msg});
	                    window.location.href = "${site}/admin/ym/yieldSettle/list";
	                }
	            });
	        }});
	    }
	});
	
	/*打印预览*/
	jQuery("#printview-btn").dfprint({
	    action : "printview",
	    url : "${site}/admin/ym/yieldSettle/toprint/v/${yieldSettle.id}"
	});

	/*打印*/
	jQuery("#print-btn").dfprint({
	    action : "print"
	});
</script>

</body>
</html>