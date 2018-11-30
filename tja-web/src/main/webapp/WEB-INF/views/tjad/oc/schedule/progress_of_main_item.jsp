<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <b>进展情况</b>
    <table id="fq" class="table table-striped table-bordered table-advance table-hover dataTable">
        <thead>
        <tr>
            <th>序号</th>
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
        <c:forEach items="${ocStepFillList}" var="stepFill" varStatus="vs">
            <tr>
                <td style="text-align:right;">${vs.index+1}</td>
                <td style="text-align:right;"><a onclick="toaddmod('${stepFill.id}')">${stepFill.weekStart}--${stepFill.weekEnd}</a></td>
                <td style="text-align:right;"><tags:config type="label" code="${stepFill.stepStatus}"/></td>
                <td style="text-align:right;">${stepFill.workContent}</td>
                <td style="text-align:right;">${stepFill.workPlan}</td>
                <td style="text-align:right;">${stepFill.remark}</td>
                <td style="text-align:right;">${stepFill.modifier}</td>
                <td style="text-align:right;"><fmt:formatDate pattern="yyyy-MM-dd" value="${stepFill.modifyDate}"/></td>
            </tr>
            <c:set var="index" value="${index + 1}"/>
        </c:forEach>
        </tbody>
    </table>
</div>
<%-- 分页标签，formId是查询表单form的ID，是必须的--%>
<tags:pagination formId="itempageSize_0" sycn="0"/>