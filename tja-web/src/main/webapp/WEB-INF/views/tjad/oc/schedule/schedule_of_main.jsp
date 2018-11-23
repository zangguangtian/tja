<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <meta charset="utf-8"/>
    <title>主项工作进展</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.work.schedule.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div class="wrapBox ">
    <center>
        <h3><b>主项进展</b></h3>
    </center>
    <hr/>
    <div id="content">
        <div class="form-body clearfix">
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">项目编号</label>
                <div class="col-md-7 input-icon right">
                    <input type="text" class="form-control text-right" value="${project.proCode}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">项目名称</label>
                <div class="col-md-7 input-icon right">
                    <input type="text" class="form-control text-right" value="${project.proName}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">合同编号</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.contractCode}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">实际合同额(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.contractAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总分包扣减(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.pkgAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总方案扣减(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.schemeAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总其他扣减(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.rebateAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总建筑面积(万平米)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.projectExtend.buildArea}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总策划产值(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.projectExtend.schemeYield}" disabled="disabled"/>
                </div>
            </div>
        </div>
    </div>
    <div class="form-body clearfix">
        <b>进展情况</b>
        <table id="fq" class="table table-striped table-bordered table-advance table-hover dataTable">
            <thead>
            <tr>
                <th>序号</th>
                <th nowrap="nowrap" style="text-align:center;">分项名称</th>
                <th nowrap="nowrap" style="text-align:center;">分项状态</th>
                <th nowrap="nowrap" style="text-align:center;">周期时间</th>
                <th nowrap="nowrap" style="text-align:center;">进展状态</th>
                <th nowrap="nowrap" style="text-align:center;">工作内容及进展情况</th>
                <th nowrap="nowrap" style="text-align:center;">下阶段工作计划</th>
                <th nowrap="nowrap" style="text-align:center;">备案情况</th>
                <th nowrap="nowrap" style="text-align:center;">更新人</th>
                <th nowrap="nowrap" style="text-align:center;">更新时间</th>

            </tr>
            </thead>
            <tbody>
            <c:set var="index" value="1"/>
            <c:set var="merge" value="0"/>
            <c:forEach items="${ocStepFillList}" var="stepFill">
                <c:if test="${merge != stepFill.proName}">
                <tr>
                    <td style="text-align:right;">${index}</td>
                    <td style="text-align:right;" rowspan="${stepFill.mergeCount}">${stepFill.proName}</td>
                    <td style="text-align:right;" rowspan="${stepFill.mergeCount}">${stepFill.proStatusName}</td>
                    <td style="text-align:right;">${stepFill.weekStart}--${stepFill.weekEnd}</td>
                    <td style="text-align:right;">${stepFill.stepStatus}</td>
                    <td style="text-align:right;">${stepFill.workContent}</td>
                    <td style="text-align:right;">${stepFill.workPlan}</td>
                    <td style="text-align:right;">${stepFill.remark}</td>
                    <td style="text-align:right;">${stepFill.modifier}</td>
                    <td style="text-align:right;"><fmt:formatDate pattern="yyyy-MM-dd" value="${stepFill.modifyDate}"/></td>
                </tr>
                </c:if>
                <c:if test="${merge == stepFill.proName}">
                    <tr>
                        <td style="text-align:right;">${index}</td>
                        <td style="text-align:right;">${stepFill.weekStart}--${stepFill.weekEnd}</td>
                        <td style="text-align:right;">${stepFill.stepStatus}</td>
                        <td style="text-align:right;">${stepFill.workContent}</td>
                        <td style="text-align:right;">${stepFill.workPlan}</td>
                        <td style="text-align:right;">${stepFill.remark}</td>
                        <td style="text-align:right;">${stepFill.modifier}</td>
                        <td style="text-align:right;"><fmt:formatDate pattern="yyyy-MM-dd" value="${stepFill.modifyDate}"/></td>
                    </tr>
                </c:if>
                <c:set var="index" value="${index + 1}"/>
                <c:set var="merge" value="${stepFill.proName}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>