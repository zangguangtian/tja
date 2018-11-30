<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
    <b>工作进度</b>
    <c:if test="${ocCurrweekScheduleList != null}">
        <table id="" class="table table-striped table-bordered table-advance table-hover dataTable">
            <thead>
            <tr>
                <th>序号</th>
                <th nowrap="nowrap" style="text-align:center;">分项名称</th>
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
            <c:set var="merge" value="0"/>
            <c:forEach items="${ocCurrweekScheduleList}" var="curr" varStatus="vs">
                <c:if test="${merge != curr.proName}">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;" rowspan="${curr.mergeCount}"><a href="${site }/admin/work/schedule/item/${curr.proId}"> ${curr.proName}</a></td>
                        <td style="text-align:right;">${curr.configName}</td>
                        <td style="text-align:right;">${curr.staffName}</td>
                        <td style="text-align:right;">${curr.orgName}</td>
                        <td style="text-align:right;">${curr.prevSchedule}</td>
                        <td style="text-align:right;">${curr.currSchedule}</td>
                        <td style="text-align:right;">${curr.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${curr.scheduleStatus}"/> </td>
                    </tr>
                </c:if>
                <c:if test="${merge == curr.proName}">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;">${curr.configName}</td>
                        <td style="text-align:right;">${curr.staffName}</td>
                        <td style="text-align:right;">${curr.orgName}</td>
                        <td style="text-align:right;">${curr.prevSchedule}</td>
                        <td style="text-align:right;">${curr.currSchedule}</td>
                        <td style="text-align:right;">${curr.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${curr.scheduleStatus}"/></td>
                    </tr>
                </c:if>
                <c:set var="merge" value="${curr.proName}"/>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${ocCurrweekSchedules != null}">
        <table id="" class="table table-striped table-bordered table-advance table-hover dataTable">
            <thead>
            <tr>
                <th>序号</th>
                <th nowrap="nowrap" style="text-align:center;">分项名称</th>
                <th nowrap="nowrap" style="text-align:center;">累计进度</th>
                <th nowrap="nowrap" style="text-align:center;">阶段</th>
                <th nowrap="nowrap" style="text-align:center;">累计进度</th>
                <th nowrap="nowrap" style="text-align:center;">专业</th>
                <th nowrap="nowrap" style="text-align:center;">累计进度</th>
                <th nowrap="nowrap" style="text-align:center;">子项</th>
                <th nowrap="nowrap" style="text-align:center;">任务</th>
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
            <c:set var="merge" value="0"/>
            <c:set var="stage" value="0"/>
            <c:set var="major" value="0"/>
            <c:forEach items="${ocCurrweekSchedules}" var="week" varStatus="vs">
                <!-- 不同项不同阶段不同专业 -->
                <c:if test="${merge != week.proName}">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;" rowspan="${week.mergeCount}"><a href="${site }/admin/work/schedule/item/${week.proId}">${week.proName}</a></td>
                        <td style="text-align:right;" rowspan="${week.mergeCount}"></td>
                        <td style="text-align:right;" rowspan="${week.stageCount}">${week.schemeStageName}</td>
                        <td style="text-align:right;" rowspan="${week.stageCount}"></td>
                        <td style="text-align:right;" rowspan="${week.majorCount == 0 ? 1 : week.majorCount}">${week.schemeMajorName}</td>
                        <td style="text-align:right;" rowspan="${week.majorCount == 0 ? 1 : week.majorCount}"></td>
                        <td style="text-align:right;">${week.subName}</td>
                        <td style="text-align:right;">${week.taskName}</td>
                        <td style="text-align:right;">${week.configName}</td>
                        <td style="text-align:right;">${week.staffName}</td>
                        <td style="text-align:right;">${week.orgName}</td>
                        <td style="text-align:right;">${week.prevSchedule}</td>
                        <td style="text-align:right;">${week.currSchedule}</td>
                        <td style="text-align:right;">${week.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${week.scheduleStatus}"/></td>
                    </tr>
                </c:if>
                <!-- 同项不同阶段不同专业 -->
                <c:if test="${merge == week.proName && stage != week.schemeStageName}">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;" rowspan="${week.stageCount}">${week.schemeStageName}</td>
                        <td style="text-align:right;" rowspan="${week.stageCount}"></td>
                        <td style="text-align:right;" rowspan="${week.majorCount == 0 ? 1 : week.majorCount}">${week.schemeMajorName}</td>
                        <td style="text-align:right;" rowspan="${week.majorCount == 0 ? 1 : week.majorCount}"></td>
                        <td style="text-align:right;">${week.subName}</td>
                        <td style="text-align:right;">${week.taskName}</td>
                        <td style="text-align:right;">${week.configName}</td>
                        <td style="text-align:right;">${week.staffName}</td>
                        <td style="text-align:right;">${week.orgName}</td>
                        <td style="text-align:right;">${week.prevSchedule}</td>
                        <td style="text-align:right;">${week.currSchedule}</td>
                        <td style="text-align:right;">${week.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${week.scheduleStatus}"/></td>
                    </tr>
                </c:if>
                <!-- 同项同阶段不同专业 -->
                <c:if test="${merge == week.proName && stage == week.schemeStageName && major != week.schemeMajorName}">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;" rowspan="${week.majorCount == 0 ? 1 : week.majorCount}">${week.schemeMajorName}</td>
                        <td style="text-align:right;" rowspan="${week.majorCount == 0 ? 1 : week.majorCount}"></td>
                        <td style="text-align:right;">${week.subName}</td>
                        <td style="text-align:right;">${week.taskName}</td>
                        <td style="text-align:right;">${week.configName}</td>
                        <td style="text-align:right;">${week.staffName}</td>
                        <td style="text-align:right;">${week.orgName}</td>
                        <td style="text-align:right;">${week.prevSchedule}</td>
                        <td style="text-align:right;">${week.currSchedule}</td>
                        <td style="text-align:right;">${week.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${week.scheduleStatus}"/></td>
                    </tr>
                </c:if>
                <!-- 同项同阶段同专业 -->
                <c:if test="${merge == week.proName && stage == week.schemeStageName && major == week.schemeMajorName}">
                    <tr>
                        <td style="text-align:right;">${vs.index+1}</td>
                        <td style="text-align:right;">${week.subName}</td>
                        <td style="text-align:right;">${week.taskName}</td>
                        <td style="text-align:right;">${week.configName}</td>
                        <td style="text-align:right;">${week.staffName}</td>
                        <td style="text-align:right;">${week.orgName}</td>
                        <td style="text-align:right;">${week.prevSchedule}</td>
                        <td style="text-align:right;">${week.currSchedule}</td>
                        <td style="text-align:right;">${week.weekShare}</td>
                        <td style="text-align:right;"><tags:config type="label" cssClass="form-control" code="${week.scheduleStatus}"/></td>
                    </tr>
                </c:if>
                <c:set var="merge" value="${week.proName}"/>
                <c:set var="stage" value="${week.schemeStageName}"/>
                <c:set var="major" value="${week.schemeMajorName}"/>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<%-- 分页标签，formId是查询表单form的ID，是必须的--%>
<tags:pagination formId="pageSize_1" sycn="0"/>