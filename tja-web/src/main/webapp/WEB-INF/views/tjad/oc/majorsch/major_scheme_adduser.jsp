<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>添加用户</title>
</head>
<body>
<!-- BEGIN CONTENT BODY -->
<div class="page-content">
    <!-- BEGIN PAGE HEADER-->
	<div class="">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="majorUserFrm" method="post">
				<input type="hidden" name="taskId" value="${taskId }"> 
				<input type="hidden" name="userSort" value="${userSort }"> 
				<div class="form-body clearfix">
					<div class="col-md-12">
						<div class="tabbable-line boxless tabbable-reversed">
					       	<div class="tab-content" style="padding-top:10px;border-top:none;">
					       		<!-- 专业比例 -->
								<table id="staffRoleTab" class="table table-bordered edit">
									<thead>
										<tr>
											<th class="text-center">角色</th>
											<th class="text-center">姓名</th>
											<th class="text-center">部门</th>
											<th class="text-center">比例</th>
											<th class="text-center" style="width: 5%">
												<span id="add-role" title="添加" class="add-tr"><i class="fa fa-plus-square fa-lg plus-btn"></i></span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><tags:config type="select" parentCode="PM.MAJORROLE" ignoreCodes="PrjMajorLeader" name="staffRole" cssClass="form-control" /></td>
											<td><input type="hidden" name="staffId"><input type="text" name="staffName" class="form-control" placeholder="请选择" readonly="readonly"></td>
											<td><input type="text" name="orgName" class="form-control" readonly="readonly"></td>
											<td><input type="text" name="schemeRatio" class="form-control" data-rule-number="true"></td>
											<td><i class="fa fa-trash-o del-btn"></i></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="">
					   	<div class="row">
					        <div class="col-md-offset-3 col-md-9">
					            <input type="button" id="save-btn" class="btn blue" value="保存">
				                <input type="button" id="cancel-btn" class="btn default" value="取消">
					        </div>
					   	</div>
					</div>
	    		</div>
			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>
<table id="staffRoleTemp" style="display:none;">
	<tr>
		<td><tags:config type="select" parentCode="PM.MAJORROLE" ignoreCodes="PrjMajorLeader" name="staffRole" cssClass="form-control" /></td>
		<td><input type="hidden" name="staffId"><input type="text" name="staffName" class="form-control" placeholder="请选择" readonly="readonly"></td>
		<td><input type="text" name="orgName" class="form-control" readonly="readonly"></td>
		<td><input type="text" name="schemeRatio" class="form-control" data-rule-number="true"></td>
		<td><i class="fa fa-trash-o del-btn"></i></td>
	</tr>
</table>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
var userObj = null;
$(function(){
    $("#add-role").on("click", addRole);
    
 	// 选择人
	$("input[name='staffName']").on("click",function(){
		userObj = this;
		selectStaff(selectStaffCallBack, "radio");
	});

    $("#save-btn").on("click", saveRole);
    
    $("#cancel-btn").on("click", function(){
    	window.close();
    });
});

function addRole(){
	var $tr = $("#staffRoleTemp").find("tr").clone(true);
	$("#staffRoleTab tbody").append($tr);
}

/**选择人后的回调方法*/
function selectStaffCallBack(data){
	$(userObj).siblings("input[name='staffId']").val(data[0].id);
	$(userObj).val(data[0].name);
	$(userObj).closest("tr").find("input[name='orgName']").val(data[0].orgname);
}

function saveRole(){
   	if(!jQuery("#majorUserFrm").valid()){
		return;
	}
   	
   	var staffs = [];
   	var staff = null;
   	$("#staffRoleTab tbody tr").each(function(){
   		staff = {};
   		staff.staffRole = $(this).find("select[name='staffRole']").val();
   		staff.staffId = $(this).find("input[name='staffId']").val();
   		staff.schemeRatio = $(this).find("input[name='schemeRatio']").val();
   		staffs.push(staff);
   	});
   	
   	var schemeUser = {};
   	schemeUser.taskId = $("input[name='taskId']").val();
   	schemeUser.userSort = $("input[name='userSort']").val();
   	schemeUser.userDivisors = staffs;
   	
   	var url = "${site }/admin/major/scheme/ajax/adduser";
    jQuery.ajax({
        type : "POST",
        url : url,
	 	contentType: "application/json",
        data : JSON.stringify(schemeUser),
        dataType : "json",
        success : function(data, status) {
        	jQuery.jalert({"jatype":"refresh", "jatext": data.mess, "onConfirm":function(){
                if(data.success == "true"){
                    window.close();
                    opener.location.reload();
                }
            }});
        },
        complete: function(XMLHttpRequest, textStatus){
            
        }
    });
}
</script>
</body>
</html>
