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
            <div class="row">
                <div class='col-lg-3 <c:if test="${not empty print }">printSeq</c:if> <c:if test="${empty print }">seq</c:if>'>
                                                     流水号:${planScheme.seqNo}
                </div>
                <div class="col-lg-9 text-right">
                    <c:if test="${canRevoke }">
                        <input type="button" id="reject-btn" value="撤回" class="btn blue">
                    </c:if>
                    <c:if test="${not empty canPrintView}">
                        <input type="button" id="printview-btn" value="打印预览" class="btn blue"/>
                    </c:if>
                    <c:if test="${not empty canPrint}">
                        <input type="button" id="print-btn" value="打印" class="btn blue"/>
                    </c:if>
                </div>
            </div>
			<!-- BEGIN FORM-->
			<form id="approveForm" action="${site}/admin/wf/planScheme/ajax/approve" method="post" class="row">
			    <input type="hidden" name="id" value="${planScheme.id}">
		        <input type="hidden" name="procId" value="${planScheme.procId }">
		        <input type="hidden" name="approve" value="2"/>
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
							<input type="text" name="proCode" class="form-control col-md-3" disabled value="${project.proCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" name="proName" class="form-control" disabled value="${project.proName}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" name="proType" class="form-control" disabled value="${project.proCategoryName}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目级别</label>
						<div class="col-md-8">
							<tags:config type="label" cssClass="form-control" code="${planScheme.itemGrade}"></tags:config>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8">
							<input type="text" name="pmLeaders" class="form-control" disabled value="${project.pmLeaders}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="pManagers" value="${project.pManagers}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计启动时间</label>
						<div class="col-md-8">
							<input type="text" class="form-control datetimepicker" name="designStart" disabled value='<fmt:formatDate value="${planScheme.designStart}" pattern="yyyy-MM-dd" />'>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计完成时间</label>
						<div class="col-md-8">
							<input type="text" class="form-control datetimepicker" name="designCompleted" disabled value='<fmt:formatDate value="${planScheme.designCompleted}" pattern="yyyy-MM-dd" />'> 
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案产值(￥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" data-rule-number="true" name="schemeYield" disabled data-rule-required="true" value="${planScheme.schemeYield}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">承接部门</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
			            	<input type="text" id="orgName" class="form-control col-md-3" value="${planScheme.orgName}" data-rule-required="true" disabled>
						</div>
					</div>
					<div class="form-group col-lg-12 " style="margin-left: 4%">
						<label class="control-label col-md-1">概况</label>
						<div class="col-md-10">
						    <textarea class="form-control" rows="3" name="schemeOverview" disabled>${planScheme.schemeOverview}</textarea>
						</div>
					</div>
					<div class="form-group col-lg-12 " style="margin-left: 4%">
						<label class="control-label col-md-1">备注</label>
						<div class="col-md-10">
							<textarea class="form-control" rows="3" name="remark" disabled>${planScheme.remark}</textarea>
						</div>
					</div> 

					<h3 class="form-tit col-lg-12">设计团队</h3>
					<div class="col-lg-5 ">
						<table class="table table-bordered edit" id="designTeam">
							<thead>
								<tr>
									<th  class="text-center col-lg-4">姓名</th>
									<th class="text-center">比例(%)※</th>
									<th class="text-center">产值</th>
								</tr>
							</thead>
							<tbody>
							    <c:if test="${not empty shemeTeams}">
							      <c:forEach var="shemeTeam" items="${shemeTeams}" varStatus="st">
							        <tr>
										<td  class="text-center col-lg-4">${shemeTeam.staffName }</td>
										<td  class=" col-lg-4">
										<input type="text" name="shemeTeams[${st.index}].refRate" data-rule-number="true" class="text-right" value="${shemeTeam.refRate }" disabled>
										</td>
										<td  class=" col-lg-4 text-right"><input type="text" name="shemeTeams[${st.index}].refYield" disabled class="text-right" value="${shemeTeam.refYield }"></td>
									</tr>
							      </c:forEach>
							    </c:if>
							
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right"></td>
									<td  class=" col-lg-4 text-right"></td>
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
                error : function(request) {
                	$.jalert({"jatext":"Connection error"});
                },
                success : function(data) {
                	$.jalert({"jatext":data.msg});
                    window.location.href = "${site}/admin/wf/planScheme/search";
                }
            });
        }});
    }
});	

$(function(){
	initTotal();
});

function initTotal(){
	var flag = true;
	var totalRefRate = 0.00;
	var totalRefYield = 0.00;
	jQuery.each($("#designTeam tbody tr:not(:last)"),function(index,item){
		var _this = $(item);
		var refRate = _this.find("input[name$='refRate']").val();
		var refYield = _this.find("input[name$='refYield']").val();
		if(refYield == '' || typeof refYield == 'undefined' || isNaN(refYield)){
			refYield = 0;
		  }
		  if(refRate == '' || typeof refRate == 'undefined' || isNaN(refRate)){
			  refRate = 0;
		  }
		totalRefRate = new Number(totalRefRate) + new Number(refRate);
		totalRefYield = new Number(totalRefYield) + new Number(refYield);
	});
	if(new Number(totalRefRate) !=100){
		flag = false;
	}
	$("#designTeam tr.total").find("td:eq(1)").text(new Number(totalRefRate).toFixed(2));
	$("#designTeam tr.total").find("td:eq(2)").text(new Number(totalRefYield).toFixed(2));
	return flag;
}

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