<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <h3 class="form-tit col-lg-12">各专业部门负责人会签</h3>
    <div class="form-group col-lg-6 ">
        <label class="control-label col-md-3">设计负责人</label>
        <div class="col-md-8">
            <input type="hidden" name="principalId" value="${yieldScheme.principalId }">
            <input type="text" id="principalName" value="${yieldScheme.principalName }" class="form-control col-md-3">
            <a title="选择" href="javascript:void(0);" class="icon-select"></a>
        </div>
    </div>
    <c:if test="${not empty majors }">
		<c:forEach items="${majors }" var="major" varStatus="vs">
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-3">${major.configName }</label>
                <div class="col-md-8">
                	<input type="hidden" name="principalId${vs.index }" data-majorcode="${major.configCode }" value="${yieldDuties[major.configCode].principalId }">
                    <input type="text" id="${major.configCode }.principalName" value="${yieldDuties[major.configCode].principalName }" class="form-control col-md-3">
                    <a title="选择" href="javascript:void(0);" class="icon-select"></a>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <div class="form-group col-lg-12 " style="margin-left: 4%">
		<label class="control-label col-md-1">备注</label>
		<div class="col-md-10">
			<textarea class="form-control" rows="5" name="remark">${yieldScheme.remark }</textarea>
		</div>
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
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
var principalObj = null;
$(function(){
	// 选择责任人
	$("a.icon-select").on("click",function(){
		principalObj = this;
		selectStaff(selectStaffCallBack, "radio");
	});
});

/**选择责任人后的回调方法*/
function selectStaffCallBack(data){
	$(principalObj).siblings("input[name^='principalId']").val(data[0].id);
	$(principalObj).siblings("input[id$='principalName']").val(data[0].name);
}
</script>