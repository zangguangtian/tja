<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="pm.staff.scheme.menu.path" scope="request" />
	<title>项目人员策划-编辑</title>
</head>
<body>
<div class="">
	<center>
		<h3>项目人员策划</h3>
	</center>
	<div class="">
		<input type="hidden" name="id" value="${project.id}" disabled="disabled">
		<div class="form-body clearfix">
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">项目编号</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.proCode}" disabled="disabled">
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">项目名称</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.proName}" disabled="disabled">
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">项目类型</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.proType}" disabled="disabled">
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">项目级别</label>
				<div class="col-md-7">
					<tags:config type="label" cssClass="form-control" code="${project.proGrade}"/>
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">合同额(¥)</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.contractAmount}" disabled="disabled">
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">分包额(¥)</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.pkgAmount}" disabled="disabled">
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">项目负责人</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.pmLeaders}" disabled="disabled">
				</div>
			</div>
			<div class="form-group col-lg-6 ">
				<label class="control-label col-md-4">项目经理</label>
				<div class="col-md-7">
					<input type="text" class="form-control" value="${project.pManagers}" disabled="disabled">
				</div>
			</div>
   		</div>
	</div>
	
	<div class="col-lg-12">
	<form id="proStaffForm">
		<h4>项目成员</h4>
		<table class="table table-bordered edit">
			<thead>
				<tr>
					<th class="text-center col-md-1">序号</th>
					<th class="text-center col-md-1">专业</th>
					<th class="text-center col-md-1">项目角色</th>
					<th class="text-center col-md-1">姓名</th>
					<th class="text-center col-md-2">任职部门</th>
					<th class="text-center col-md-2">主要负责人</th>
					<th class="text-center col-md-4">备注</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${proStaffList }" var="staff" varStatus="s">
				<tr>
					<td class="text-center">
						${s.index+1 }
						<input name="proStaffList[${s.index}].id" value="${staff.id }" type="hidden">
						<input name="proStaffList[${s.index}].memberType" value="${staff.memberType }" type="hidden">
						<input name="proStaffList[${s.index}].majorCode" value="${staff.majorCode }" type="hidden" disabled="disabled">
						<input name="proStaffList[${s.index}].memberRole" value="${staff.memberRole }" type="hidden" disabled="disabled">
					</td>
					<td class="text-center">${staff.majorName }</td>
					<td class="text-center">${staff.memberName }</td>
					<td class="text-center">${staff.name }</td>
					<td class="text-center">${staff.orgName }</td>
					<td class="text-center">
						<input type="checkbox" name="proStaffList[${s.index}].mainFlag" data-group='${staff.majorCode}<c:if test="${staff.memberType eq '1000'}">.${staff.memberRole}</c:if>' value="1" onchange="setMainFlag(this)" <c:if test="${staff.mainFlag }">checked="checked"</c:if> >
					</td>
					<td class="">
						<input name="proStaffList[${s.index}].remark" value="${staff.remark }" type="text" class="form-control">
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	
	   	<div class="row">
	        <div class="col-md-offset-3 col-md-8">
	            <button type="button" class="btn blue" onclick="save()">保存</button>
	            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/pm/proStaff/list' ">取消</button>
	        </div>
	   	</div>
	   	
	</form>
	</div>
	
</div>
<div class="clearfix"></div>

<script type="text/javascript">

function setMainFlag(_this){
	if($(_this).is(":checked")){
		var thisGroup = $(_this).data("group");
		$(_this).closest("body").find("input[data-group='"+thisGroup+"']").prop("checked", false);
		$(_this).prop("checked", true);
	}
}

function save(){
	var url ="${site}/admin/pm/proStaff/ajax/save";
	$.ajax({
		type : "post",
	 	url : url,
	 	data : $("#proStaffForm").serialize(),
	 	success : function(data) {
	 		if(data.flag == "true"){
	 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
			  		window.location.href="${site}/admin/pm/proStaff/${project.id}";
	 			}});
	 		}else{
	 			$.jalert({"jatext":data.msg});
	 		}
	 	}
	});
}
</script>
</body>
</html>