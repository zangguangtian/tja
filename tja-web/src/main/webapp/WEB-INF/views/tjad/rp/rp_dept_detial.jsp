<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>部门结算产值明细</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="rp.dept.detail.menu.path" scope="request"/>
</head>
<body>
<form id="frmSearch" method="post" action="${site }/admin/rp/deptDetial">
<div class="col-md-6" style="margin:10px 0px;padding: 0px;">
    <div class="col-md-6">
      <label class="control-label" style="margin-right: 5%;">期间</label>
      <select class="form-control" name="periodId" style="display: inline-block;width: 160px;">
        <c:if test="${not empty periodSelects}">
          <c:forEach items="${periodSelects}" var="periodSelect">
            <option <c:if test="${periodSelect.id == periodId}">selected</c:if> value="${periodSelect.id}">${periodSelect.periodName}</option>
          </c:forEach>
        </c:if>
      </select>  
    </div>
    <div class="form-group  col-md-6">
      <label class="control-label" style="margin-right: 5%;float: left;">部门<span class="required">※</span></label>
      <input type="hidden" id="orgId" name="orgId" value="${orgId}">
	  <input type="text" id="orgName" name="orgName" class="form-control col-md-3" value="${orgName}" data-rule-required="true" readonly style="width: 160px;">
    </div>
</div>

<div class="form-group col-md-4" style="margin:10px 0px;padding: 0px;">
    <input type="button" value="查询" class="btn blue " onclick="search()">
    <input type="button" value="导出" class="btn blue " onclick="exportExec()">
</div>

<div class="table-scrollable">
    <table class="table table-striped table-bordered table-advance table-hover dataTable">
        <thead>
            <tr>
                <th nowrap="nowrap" style="width:5%;text-align:center;" >序号</th>
                <th nowrap="nowrap" style="width:20%;text-align:center;">项目编号</th>
                <th nowrap="nowrap" style="width:20%;text-align:center;">项目名称</th>
                <c:if test="${not empty staffs}">
                <c:forEach items="${staffs}" var="staff">
                  <th nowrap="nowrap" style="width:12%;text-align:center;">${staff.value}</th>
                </c:forEach>
                </c:if>
                <th nowrap="nowrap" style="width:15%;text-align:center;">合计</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty custProjects }">
	            <c:forEach items="${custProjects }" var="custProject" varStatus="vs">
	            <tr>
	                <td nowrap="nowrap" >${vs.index+1}</td>
	                <td nowrap="nowrap" >${custProject.proCode }</td>
	                <td nowrap="nowrap" >${custProject.proName }</td>
	                <c:set value="0" var="totalYield"></c:set>
                    <c:forEach items="${staffs}" var="staff">
	                  <td nowrap="nowrap"><fmt:formatNumber value='${empty custProject.staffYields[staff.key]?0.00:custProject.staffYields[staff.key]}' pattern='#,#00.00#'/></td>
	                  <c:set value="${totalYield + (empty custProject.staffYields[staff.key]?0.00:custProject.staffYields[staff.key])}" var="totalYield"></c:set>
	                </c:forEach>
	                <td nowrap="nowrap" ><fmt:formatNumber value='${totalYield}' pattern='#,#00.00#'/></td>
	               </tr>
	           </c:forEach>
           </c:if>
        </tbody>
    </table>
</div>
<%-- 分页标签，formId是查询表单form的ID，是必须的--%>
<tags:pagination formId="frmSearch" />
</form>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
$(function(){
	jQuery("#orgName").on("click",function(){
		selectOrg(selectOrgCallback);
	});
});

function selectOrgCallback(data){
	$("#orgId").val(data.id);
	$("#orgName").val(data.name);
}

//搜索
function search(){
	if (jQuery("#frmSearch").valid()) {
		jQuery("#frmSearch").submit();
    }
}

//导出
function exportExec(){
	jQuery("#frmSearch").attr("action","${site }/admin/rp/deptDetial/export");
	jQuery("#frmSearch").submit();
	jQuery("#frmSearch").attr("action","${site }/admin/rp/deptDetial");
}

</script>
</body>
</html>