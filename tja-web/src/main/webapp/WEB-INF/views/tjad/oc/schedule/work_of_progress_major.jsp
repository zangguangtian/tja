<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <b>工作进度</b>
        <table id="" class="table table-striped table-bordered table-advance table-hover dataTable">
            <thead>
            <tr>
                <th>序号</th>
                <th nowrap="nowrap" style="text-align:center;">子项</th>
                <th nowrap="nowrap" style="text-align:center;">累计进度</th>
                <th nowrap="nowrap" style="text-align:center;">任务</th>
                <th nowrap="nowrap" style="text-align:center;">累计进度</th>
                <th nowrap="nowrap" style="text-align:center;">项目角色</th>
                <th nowrap="nowrap" style="text-align:center;">姓名</th>
                <th nowrap="nowrap" style="text-align:center;">任职部门</th>
                <th nowrap="nowrap" style="text-align:center;">上周进度</th>
                <th nowrap="nowrap" style="text-align:center;">本周进度</th>
                <th nowrap="nowrap" style="text-align:center;">本周占比</th>
                <th nowrap="nowrap" style="text-align:center;">进度状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ocCurrweekSchedules}" var="week" varStatus="vs">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;">${week.subName}</td>
                        <td style="text-align:right;"></td>
                        <td style="text-align:right;">${week.taskName}</td>
                        <td style="text-align:right;"></td>
                        <td style="text-align:right;">${week.configName}</td>
                        <td style="text-align:right;">${week.staffName}</td>
                        <td style="text-align:right;">${week.orgName}</td>
                        <td style="text-align:right;">${week.prevSchedule}</td>
                        <td style="text-align:right;">${week.currSchedule}</td>
                        <td style="text-align:right;">${week.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${week.scheduleStatus}"/></td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
</div>
<%-- 分页标签，formId是查询表单form的ID，是必须的--%>
<tags:pagination formId="majorPageSize_1" sycn="0"/>