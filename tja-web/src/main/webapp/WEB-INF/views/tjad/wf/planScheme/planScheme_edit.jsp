<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>方案产值策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="wf.planScheme.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
	<center>
		<h3>方案产值策划</h3>
	</center>
	<div class="">
		<div class="form">
            <div class="row">
                <div class="col-lg-6">
                                                 流水号
                </div>
                <div class="col-lg-6 text-right">
                    <button type="button" class="btn blue save" onclick="save(0)">保存</button>
                    <button type="button" class="btn blue submit" onclick="save(1)">提交</button>
                    <%-- <c:if test="${tModel.cticket.canDel }">
		                <input class="btn-save sub" type="button" value="删除" onclick="save(9)"/>
		            </c:if>
					<c:if test="${empty tModel.cticket.procId }">
						<input class="btn blue submit" type="button" value="提交"  onclick="save(1)"/>
						<input class="btn blue save" type="button" value="保存" onclick="save(0)"/>
					</c:if>
				    <c:if test="${not empty tModel.cticket.procId }">
						<input class="btn-save sub" type="button" value="重新提交" onclick="save(1)"/>
		           	</c:if> --%>
                </div>
            </div>
			<!-- BEGIN FORM-->
			<form id="saveForm" action="#" class="row">
			    <input type="hidden" value="" name="auditStatus"/>
			    <input type="hidden" name="procId" value=""/>
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
						    <input type="hidden" name="id" value="${planScheme.id}">
							<input type="hidden" name="proId" value="${planScheme.proId}">
							<input type="text" name="proCode" class="form-control col-md-3" readonly value="${project.proCode}">
							<a id="selectPro" title="选择" href="javascript:void(0);" class="icon-select"></a>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" name="proName" class="form-control" readonly value="${project.proName}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" name="proType" class="form-control" readonly value="${project.proCategoryName}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目级别</label>
						<div class="col-md-8">
							<tags:config type="select" cssClass="form-control" selectCode="${planScheme.itemGrade}" parentCode="PM.GRADE" name="itemGrade"></tags:config>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" readonly name="pmLeaders" value="${project.pmLeaders}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" readonly name="pManagers" value="${project.pManagers}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计启动时间</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right datetimepicker" name="designStart" value='<fmt:formatDate value="${planScheme.designStart}" pattern="yyyy-MM-dd" />'>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计完成时间</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right datetimepicker" name="designCompleted" value='<fmt:formatDate value="${planScheme.designCompleted}" pattern="yyyy-MM-dd" />'> 
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" data-rule-number="true" name="schemeYield" data-rule-required="true" value="${planScheme.schemeYield}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">承接部门</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="hidden" id="orgId" name="receptDeptId" value="${planScheme.receptDeptId}">
			            	<input type="text" id="orgName" class="form-control col-md-3" value="${planScheme.orgName}" data-rule-required="true" readonly>
			            	<a id="secOrg" title="选择" href="javascript:void(0);" class="icon-select"></a>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">概况</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" name="schemeOverview" value="${planScheme.schemeOverview}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">备注</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" name="remark" value="${planScheme.remark}">
						</div>
					</div>

					<h3 class="form-tit col-lg-12">设计团队</h3>
					<div class="col-lg-5 ">
						<div class="row clearfix">
							<div class="col-lg-12 text-right col-md-4 col-sm-4 col-xs-4">
								<input type="button" class="btn blue btn_tj" value="添加"/> 
							</div>
						</div>
						<table class="table table-bordered edit" id="designTeam">
							<thead>
								<tr class="row">
									<th  class="text-center col-lg-4">姓名</th>
									<th class="text-center">比例(%)※</th>
									<th class="text-center">产值</th>
								</tr>
							</thead>
							<tbody>
							    <c:if test="${not empty shemeTeams}">
							      <c:forEach var="shemeTeam" items="${shemeTeams}" varStatus="st">
							        <tr class="row">
										<td  class="text-center col-lg-4">${shemeTeam.staffName }</td>
										<td  class=" col-lg-4">
										<input type="hidden" name="shemeTeams[${st.index}].id" class="text-right" value="${shemeTeam.id }">
										<input type="hidden" name="shemeTeams[${st.index}].staffId" class="text-right" value="${shemeTeam.staffId }">
										<input type="hidden" name="shemeTeams[${st.index}].staffSort" class="text-right" value="${shemeTeam.staffSort }">
										<input type="text" name="shemeTeams[${st.index}].refRate" data-rule-number="true"  placeholder="0.00" class="text-right" value="${shemeTeam.refRate }">
										</td>
										<td  class=" col-lg-4 text-right"><input type="text" name="shemeTeams[${st.index}].refYield" readonly placeholder="0.00" class="text-right" value="${shemeTeam.refYield }"></td>
									</tr>
							      </c:forEach>
							    </c:if>
							
								<tr class="row total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right"></td>
									<td  class=" col-lg-4 text-right"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-lg-1">
						
					</div>
				</div>
			</form>
			<!-- END FORM-->
			<table id="clone_text" style="display: none">
				<tr class="row">
					<td  class="text-center col-lg-4"></td>
					<td  class=" col-lg-4">
					<input type="hidden" name="shemeTeams[{0}].id" class="text-right">
					<input type="hidden" name="shemeTeams[{0}].staffId" class="text-right">
					<input type="hidden" name="shemeTeams[{0}].staffSort" class="text-right">
					<input type="text" name="shemeTeams[{0}].refRate" data-rule-number="true"  placeholder="0.00" class="text-right">
					</td>
					<td  class=" col-lg-4 text-right"><input type="text" name="shemeTeams[{0}].refYield" readonly placeholder="0.00" class="text-right"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js"></script>
<script type="text/javascript" src="${site}/resources/js/common/common.js"></script>
<script type="text/javascript">
	$(function(){
		// 初始化时间控件
		$(".datetimepicker").datetimepicker({
			format: "yyyy-mm-dd",
			minView: "month",
			todayBtn: 1,
		    autoclose: 1
		});
		
		jQuery(document).on("keyup","#designTeam tbody input[name$='refRate']",function(){
			  var _this = $(this);
			  var refRate = _this.val();
			  var schemeYield =	$("input[name='schemeYield']").val();
			  if(schemeYield == '' || typeof schemeYield == 'undefined' || isNaN(schemeYield)){
				  schemeYield = 0;
			  }
			  if(refRate == '' || typeof refRate == 'undefined' || isNaN(refRate)){
				  refRate = 0;
			  }
			  var refYieldInput = _this.closest("tr").find("td:last").find("input");
			  var refYield = new Number(schemeYield) * new Number(refRate)/100;
			  refYieldInput.val(new Number(refYield).toFixed(2));
			  
			  var flag = initTotal();
			  if(!flag){
				  _this.val(0.00);
				  refYieldInput.val(0.00);
				  initTotal();
			  }
			  
			});
		
		jQuery("#secOrg").on("click",function(){
			selectOrg(selectOrgCallback);
		});
		
		jQuery(".btn_tj").on("click",function(){
			selectStaff(selectStaffBack,'checkbox');
		});
		initTotal();
	});

	//合计
	function initTotal(){
		var flag = true;
		var totalRefRate = 0.00;
		var totalRefYield = 0.00;
		jQuery.each($("#designTeam tbody tr:not(:last)"),function(index,item){
			var _this = $(item);
			var refRate = _this.find("input[name$='refRate']").val();
			var refYield = _this.find("input[name$='refYield']").val();
			if(refYield == '' || typeof refYield == 'undefined' || isNaN(refYield)){
				refYield = 0;
			  }
			  if(refRate == '' || typeof refRate == 'undefined' || isNaN(refRate)){
				  refRate = 0;
			  }
			totalRefRate = new Number(totalRefRate) + new Number(refRate);
			totalRefYield = new Number(totalRefYield) + new Number(refYield);
		});
		if(new Number(totalRefRate) >100){
			flag = false;
		}
		$("#designTeam tr.total").find("td:eq(1)").text(new Number(totalRefRate).toFixed(2));
		$("#designTeam tr.total").find("td:eq(2)").text(new Number(totalRefYield).toFixed(2));
		return flag;
	}
	
	/*选择项目*/
	jQuery("#selectPro").on("click", function() { 
		_proper = $(this);
		var url = context+"/config/query?NO=PROJECT_SELECT_LIST&MODEL=PM";
		openWindow(url, "选择项目", "800", "600", true, false);
	});
	
	/* 选择后回调方法 */
	function selectRes(objs){
		for(var i=0 ; i<objs.length; i++){
				reCall(objs[i]);
		}
	}
	/* 取值：data.字段名 */
	function reCall(data){
		$("input[name='proId']").val(data.ID);
		$("input[name='proCode']").val(data.PRO_CODE);
		$("input[name='proName']").val(data.PRO_NAME);
		seachOtherProInfo(data.ID);
	}
	
	function selectOrgCallback(data){
		$("#orgId").val(data.id);
		$("#orgName").val(data.name);
	}
	
	function selectStaffBack(data){
		jQuery.each(data,function(index,item){
			var info = $(item)[0];
			var trs = jQuery("#designTeam").find("tbody tr:not(:last)");
			var trSize = jQuery("#designTeam").find("tbody tr:not(:last)").size();
			if(trSize == 0){
				var $item = jQuery("#clone_text").clone();
				$item.html($item.html().format(trSize));
				$item.find("tr input[name$='.staffId']").val(info.id);
				$item.find("tr input[name$='.staffSort']").val(trSize);
				$item.find("tr td:first").text(info.name);
				jQuery(".total").before($item.find("tr"));
				trSize ++;
			}else{
				var _size = jQuery("#designTeam").find("tbody tr input[value='"+info.id+"']").size();
				if(_size <= 0){
					//可以添加
					var $item = jQuery("#clone_text").clone();
					$item.html($item.html().format(trSize));
					$item.find("tr input[name$='.staffId']").val(info.id);
					$item.find("tr input[name$='.staffSort']").val(trSize);
					$item.find("tr td:first").text(info.name);
					jQuery(".total").before($item.find("tr"));
					trSize ++;
				}
			}
			
		});
	}
	
	function seachOtherProInfo(proId){
		jQuery.ajax({
	        type : "GET",
	        url : "${site}/admin/pm/project/ajax/proinfo",
	        data : {proId:proId},
	        async : false,
	        error : function(request) {
	        	alert("Connection error");
	        },
	        success : function(data) {
	        	console.log(data);
	        	if(data.flag == 'true'){
	        		$("input[name='proType']").val(data.project.proCategoryName == null?'':data.project.proCategoryName );
	        		$("input[name='pmLeaders']").val(data.project.pmLeaders == null?'':data.project.pmLeaders);
	        		$("input[name='pManagers']").val(data.project.pManagers == null?'':data.project.pManagers);
	        		$("#orgName").val(data.project.orgName == null?'':data.project.orgName);
	        		$("#orgId").val(data.project.dutyDeptId == null?'':data.project.dutyDeptId);
	        		if(data.project.proGrade != null && data.project.proGrade != ''){
	        			$("select[name='itemGrade']").val(data.project.proGrade);	
	        		}
	        	}
	        }
        });
		
	}
	
	function save(status) {
		var flag = false;
	    if(status == "9"){
	        if(window.confirm("确认删除？\r\n\r点[确定]：执行删除操作\r\n\r点[取消]：放弃删除操作")){
	            flag = true;
	        }
	    }else{
	    	if (jQuery("#saveForm").valid()) {
	            flag = true;
	        }
	    	if(!initTotal()){
	    		flag = false;
	    	}else{
	    		flag = true;
	    	}
	    }
		var url ="${site}/admin/wf/planScheme/ajax/esave";
	    if (flag) {
	    	$("input[name='auditStatus']").val(status);
	        jQuery.ajax({
	        type : "POST",
	        url : url,
	        data : jQuery('#saveForm').serialize(),
	        async : false,
	        error : function(request) {
	        	alert("Connection error");
	        },
	        success : function(data) {
	        	if(data.flag == 'true'){
	        	alert(data.msg);
	        	window.location.href="${site}/admin/wf/planScheme/search";
	        	}else{
	        		alert(data.msg);
	        	}
	        }
	        });
	    }
	}
	
</script>

</body>
</html>