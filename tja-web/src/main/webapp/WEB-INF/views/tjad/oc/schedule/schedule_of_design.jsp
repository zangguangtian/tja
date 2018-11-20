<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>分项角色进展</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.work.schedule.menu.path" scope="request"  />
</head>
<body>
<div class="">
	<center>
		<h3>分项角色进展</h3>
	</center>
	<div class=" ">
		<div class="form">
			<div class="form-body clearfix">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">分项编号</label>
					<div class="col-md-7">
						<label class="control-label">${project.proCode}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">分项名称</label>
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
						<label class="control-label">${project.proGradeName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目性质</label>
					<div class="col-md-7">
					    <label class="control-label">${project.proProp}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">管理归属</label>
					<div class="col-md-7">
						<label class="control-label">${project.projectExtend.ownerShip}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目分类</label>
					<div class="col-md-7">
						<tags:config type="lable" cssClass="control-label" code="${project.proCategory }"></tags:config>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">本部/外协</label>
					<div class="col-md-7">
						<tags:config type="lable" cssClass="control-label" code="${project.projectExtend.innerOuter }"></tags:config>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">合同编号</label>
					<div class="col-md-7">
					    <label class="control-label">${project.contractCode}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">所处状态</label>
					<div class="col-md-7">
						<tags:config type="lable" cssClass="control-label" code="${project.proStatus }"></tags:config>
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
					<label class="control-label col-md-4">承接部门</label>
					<div class="col-md-7">
						<label class="control-label">${project.dutyDeptName}</label>
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">负责建筑师</label>
					<div class="col-md-7">
						<label class="control-label">${project.projectExtend.builderName}</label>
					</div>
				</div>

				<div class="col-md-12">
					<div class="tabbable-line boxless tabbable-reversed">
				       	<div class="tab-content" style="padding-top:10px;border-top:none;">
				       		<div class="col-lg-12">
								<h5 class="form-tit">工作进度</h5>
								<c:if test="${ocScheme.proWbs == 'OC.PROJECT.WBS.FULL' }">
									<div class="form-group col-lg-2" style="position: absolute;top: 6px;margin-left: 80px;">
										<select id="phase" class="form-control">
											<c:forEach items="${phases }" var="phase">
												<option value="${phase.id }">${phase.divisorName }</option>
											</c:forEach>
										</select>
									</div>
								</c:if>
							</div>
				       		<!-- 专业比例 -->
			       			<div class="col-lg-12 ">
			       				<c:if test="${ocScheme.proWbs == 'OC.PROJECT.WBS.FULL' }">
									<table id="designSchedule" class="table table-bordered edit">
										<thead>
											<tr class="form-group">
												<th class="text-center">序号</th>
												<th class="text-center">专业</th>
												<th class="text-center">子项</th>
												<th class="text-center">任务</th>
												<th class="text-center">项目角色</th>
												<th class="text-center">姓名</th>
												<th class="text-center">任职部门</th>
												<th class="text-center">上周进度</th>
												<th class="text-center" style="width:10%;">本周进展(%)<span class="required">※</span></th>
												<th class="text-center">本周占比</th>
												<th class="text-center">进度状态</th>
												<th class="text-center">备注</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
			       				</c:if>
			       				<c:if test="${ocScheme.proWbs != 'OC.PROJECT.WBS.FULL' }">
									<table id="designSchedule" class="table table-bordered edit">
										<thead>
											<tr class="form-group">
												<th class="text-center">序号</th>
												<th class="text-center">项目角色</th>
												<th class="text-center">姓名</th>
												<th class="text-center">任职部门</th>
												<th class="text-center">上周进度</th>
												<th class="text-center" style="width:10%;">本周进展(%)<span class="required">※</span></th>
												<th class="text-center">本周占比</th>
												<th class="text-center">进度状态</th>
												<th class="text-center">备注</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
			       				</c:if>
							</div>
							<div class="col-lg-1"></div>
						</div>
			   		</div>
			   	</div>
		
				<div class="">
				   	<div class="row">
				        <div class="col-md-offset-3 col-md-9">
				            <button type="button" class="btn blue" onclick="save(this)">保存</button>
				            <input type="button" class="btn default" onclick=" window.location.href='${site}/admin/major/scheme/list' " value="取消">
				        </div>
				   	</div>
				</div>
    		</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
<script type="text/javascript">
$(function(){
	alert("1");
});
</script>
</body>
</html>