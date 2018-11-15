<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>专业策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.major.scheme.menu.path" scope="request"  />
</head>
<body>
<div class="">
	<center>
		<h3>专业策划</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form action="" class="" id="majorSchForm">
			<input type="hidden" name="majorId" value="${stageMajor.schemeMajorId }">
			<div class="form-body clearfix">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目编号</label>
					<div class="col-md-7">
						<label class="control-label">${project.proCode}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<label class="control-label">${project.proName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
					    <label class="control-label">${project.proType}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目级别</label>
					<div class="col-md-7">
						<tags:config type="label" cssClass="form-control" code="${planScheme.itemGrade}"></tags:config>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<label class="control-label">${project.pmLeaders}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<label class="control-label">${project.pManagers}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">所属阶段</label>
					<div class="col-md-7">
						<label class="control-label">${stageMajor.schemeStageName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">策划专业</label>
					<div class="col-md-7">
						<label class="control-label">${stageMajor.schemeMajorName}</label>
					</div>
				</div>
				<div class="form-group col-lg-12 ">
					<label class="control-label col-md-2"><strong>项目WBS</strong></label>
					<div class="col-md-7">
						<label class="control-label">${stageMajor.wbsName}</label>
					</div>
				</div>

				<div class="col-md-12">
					<div class="tabbable-line boxless tabbable-reversed">
				       	<div class="tab-content" style="padding-top:10px;">
				       		<label id="add-node" class="btn blue" style="margin-bottom:10px;margin-left:15px;"><i class="fa fa-plus"></i>节点</label>
				       		<label id="add-person" class="btn blue" style="margin-bottom:10px;"><i class="fa fa-plus"></i>人员</label>
				       		<!-- 专业比例 -->
			       			<div class="col-lg-12 ">
								<table id="majorSchemeTab" class="table table-bordered edit">
									<thead>
										<tr>
											<th class="text-center">选择</th>
											<th class="text-center">子项</th>
											<th class="text-center">比例(%)</th>
											<th class="text-center">任务</th>
											<th class="text-center">比例(%)</th>
											<th class="text-center">项目角色</th>
											<th class="text-center">姓名</th>
											<th class="text-center">比例(%)</th>
											<th class="text-center">任职部门</th>
											<th class="text-center">备注</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="subId" value="A"/>
										<c:set var="taskId" value="A"/>
										<c:forEach items="${subTasks }" var="task">
											<%--既不同子项又不同任务 --%>
											<c:if test="${subId != task.subId && taskId != task.taskId }">
												<tr>
													<%--data-subid放在td上面主要用于统计子项数量 --%>
													<td rowspan="${task.taskUserCount }" class="text-center" data-subid="${task.subId }" data-subchildcount="${task.subChildCount }">
														<input type="radio" name="task" data-subid="${task.subId }" data-taskid="${task.taskId }">
													</td>
													<td rowspan="${task.subTaskCount }">${task.subName }</td>
													<td rowspan="${task.subTaskCount }" class="text-right">${task.subRatio }</td>
													<td rowspan="${task.taskUserCount }">${task.taskName }</td>
													<td rowspan="${task.taskUserCount }" class="text-right">${task.taskRatio }</td>
													<td>${task.userRoleName }</td>
													<td>${task.staffName }</td>
													<td class="text-right">${task.userRatio }</td>
													<td>${task.orgName }</td>
													<td>${task.remark }</td>
												</tr>
											</c:if>
											<%--既同子项又不同任务 --%>
											<c:if test="${subId == task.subId && taskId != task.taskId }">
												<tr>
													<%-- data-subchildcount放在td上面主要用于统计子项下的任务数量 --%>
													<td rowspan="${task.taskUserCount }" class="text-center" data-subchildcount="${task.subChildCount }">
														<input type="radio" name="task" data-subid="${task.subId }" data-taskid="${task.taskId }">
													</td>
													<td rowspan="${task.taskUserCount }">${task.taskName }</td>
													<td rowspan="${task.taskUserCount }" class="text-right">${task.taskRatio }</td>
													<td>${task.userRoleName }</td>
													<td>${task.staffName }</td>
													<td class="text-right">${task.userRatio }</td>
													<td>${task.orgName }</td>
													<td>${task.remark }</td>
												</tr>
											</c:if>
											<%--既同子项又同任务 --%>
											<c:if test="${subId == task.subId && taskId == task.taskId }">
												<tr>
													<td>${task.userRoleName }</td>
													<td>${task.staffName }</td>
													<td class="text-right">${task.userRatio }</td>
													<td>${task.orgName }</td>
													<td>${task.remark }</td>
												</tr>
											</c:if>
											<c:set var="subId" value="${task.subId }"/>
											<c:set var="taskId" value="${task.taskId }"/>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="col-lg-1"></div>
						</div>
			   		</div>
			   	</div>
		
				<div class="">
				   	<div class="row">
				        <div class="col-md-offset-3 col-md-9">
				            <button type="button" class="btn blue" onclick="save()">保存</button>
				            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/pm/project/list' ">取消</button>
				        </div>
				   	</div>
				</div>
	
    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<div class="clearfix"></div>
<script type="text/javascript">
$(function(){
	/**添加节点*/
    jQuery("#add-node").click(addNode);
    
	/**添加人员*/
    jQuery("#add-person").click(addPerson);
});

function addNode(){
	var nodeLen = $("#majorSchemeTab tbody input[type='radio']:checked").length;
	if(nodeLen == 0){
		$.jalert({"jatext":"请在列表中选择一个任务!"});
		return false;
	}
	
	var majorId = $("input[name='majorId']").val();
	var checkedRadio = $("#majorSchemeTab tbody input[type='radio']:checked");
	var subId = checkedRadio.data("subid");
	
	//子项数量
	var subIndexs = $("#majorSchemeTab tbody td[data-subid]").length;
	//选中的任务的子项下，任务的数
	var taskIndexs = checkedRadio.closest("td").data("subchildcount");
	
	layer.open({
        type: 2,
        shade: [0.5, "#393D49"],
        closeBtn: 2,
        title: "添加节点", //不显示标
        area: ["600px", "350px"],
        content: "${site}/admin/major/scheme/ajax/addnode/"+majorId+"/"+subId+"/"+subIndexs+"/"+taskIndexs
    })
}

function addPerson(){
	var nodeLen = $("#majorSchemeTab tbody input[type='radio']:checked").length;
	if(nodeLen == 0){
		$.jalert({"jatext":"请在列表中选择一个任务!"});
		return false;
	}
	var taskId = $("#majorSchemeTab tbody input[type='radio']:checked").data("taskid");
	openWindow("${site}/admin/major/scheme/ajax/adduser/"+taskId+"/"+0, "添加用户", "1000", "600", true, true);
}
</script>
</body>
</html>