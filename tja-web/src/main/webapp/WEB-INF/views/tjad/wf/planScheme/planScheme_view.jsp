<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>方案产值策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="wf.planScheme.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
    <style type="text/css">
       .printSeq{
        text-align: left;
	    margin-top: 17px;
	    padding-right: 30px;
	    margin-left: 6%;
       }
       .seq{
        text-align:center;
        margin-top:17px;
        padding-right:30px;
       }
    </style>
</head>
<body>
 <div class="">
	<center>
		<h3>方案产值策划</h3>
	</center>
	<div class='<c:if test="${not empty print }">print</c:if>'>
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/wf/planScheme/ajax/approve" method="post" class="row">
				    <input type="hidden" name="id" value="${planScheme.id}">
			        <input type="hidden" name="procId" value="${planScheme.procId }">
			        <input type="hidden" name="approve" value="2"/>
	                <div class="form-body clearfix" style="padding-bottom: 0">
						<div class="col-xs-6 ">
							<label class="control-label col-xs-4">流水号</label>
							<div class="col-xs-8">
								<label class="control-label">${planScheme.seqNo}</label>
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
	                
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目编号</label>
						<div class="col-xs-8">
						   <label class="control-label">${project.proCode}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目名称</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proName}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目类型</label>
						<div class="col-xs-8">
							<label class="control-label">${project.proType}</label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">项目级别</label>
						<div class="col-xs-8">
							<tags:config type="label" cssClass="form-control" code="${planScheme.itemGrade}"></tags:config>
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
						<label class="control-label col-xs-4">设计启动时间</label>
						<div class="col-xs-8">
						    <label class="control-label"><fmt:formatDate value="${planScheme.designStart}" pattern="yyyy-MM-dd" /></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">设计完成时间</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatDate value="${planScheme.designCompleted}" pattern="yyyy-MM-dd" /></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">方案产值(￥)</label>
						<div class="col-xs-8">
							<label class="control-label"><fmt:formatNumber value='${planScheme.schemeYield}' pattern='#,#00.00#'/></label>
						</div>
					</div>
					<div class="form-group col-xs-6 ">
						<label class="control-label col-xs-4">承接部门</label>
						<div class="col-xs-8">
						    <label class="control-label">${planScheme.orgName}</label>
						</div>
					</div>
					<div class="form-group col-xs-12 ">
						<label class="control-label col-xs-2">概况</label>
						<div class="col-xs-10">
						    <label class="control-label">${planScheme.schemeOverview}</label>
						</div>
					</div>
					<div class="form-group col-xs-12 ">
						<label class="control-label col-xs-2">备注</label>
						<div class="col-xs-10">
							<label class="control-label">${planScheme.remark}</label>
						</div>
					</div> 

					<h3 class="form-tit col-lg-12">设计团队</h3>
					<div class="col-lg-5 ">
						<table class="table table-bordered edit" id="designTeam">
							<thead>
								<tr>
									<th  class="text-center col-xs-2">姓名</th>
									<th class="text-center col-xs-2">比例(%)</th>
									<th class="text-center col-xs-2">产值</th>
								</tr>
							</thead>
							<tbody>
							    <c:set var="totalYield" value="0"></c:set>
							    <c:set var="totalRate" value="0"></c:set>
							    <c:if test="${not empty shemeTeams}">
							      <c:forEach var="shemeTeam" items="${shemeTeams}" varStatus="st">
							        <tr>
										<td  class="text-center">${shemeTeam.staffName }</td>
										<td  class="text-right">${shemeTeam.refRate }</td>
										<td  class="text-right"><fmt:formatNumber value='${shemeTeam.refYield }' pattern='#,#00.00#'/></td>
									</tr>
									<c:set var="totalRate" value="${totalRate + shemeTeam.refRate }"></c:set>
									<c:set var="totalYield" value="${totalYield + shemeTeam.refYield }"></c:set>
							      </c:forEach>
							    </c:if>
							
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right">${totalRate}</td>
									<td  class=" col-lg-4 text-right"><fmt:formatNumber value='${totalYield}' pattern='#,#00.00#'/></td>
								</tr>
							</tbody>
						</table>
					</div>
					
	                <jsp:include page="../../../framework/activiti/wf_approve.jsp" flush="true"/>
	                <tags:histask procId="${planScheme.procId }"/>
				</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
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
                    window.location.href = "${site}/admin/wf/planScheme/search";
                }
            });
        }});
    }
});	

/*打印预览*/
jQuery("#printview-btn").dfprint({
    action : "printview",
    url : "${site}/admin/wf/planScheme/toprint/v/${planScheme.id}"
});

/*打印*/
jQuery("#print-btn").dfprint({
    action : "print"
});
</script>
</body>
</html>