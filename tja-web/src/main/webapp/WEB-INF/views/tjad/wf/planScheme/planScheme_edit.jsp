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
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
	<center>
		<h3>方案产值策划</h3>
	</center>
	<div class="">
		<div class="form">
            <div class="form-group col-lg-12">
                <div class="form-group col-lg-5 ">
					<label class="control-label col-md-3">流水号</label>
					<div class="col-md-8">
						<input type="text" class="form-control" value="${planScheme.seqNo}" disabled="disabled" title="${planScheme.seqNo}">
					</div>
				</div>
                
                <div class="col-lg-7 text-right">
	                <c:if test="${empty planScheme.procId }">
	                    <input type="button" value="保存" class="btn blue save" onclick="save(0)">
	                    <input type="button" value="提交" class="btn blue submit" onclick="save(1)">
	                </c:if>
	                <c:if test="${not empty planScheme.procId }">
						<input class="btn blue save" type="button" value="重新提交" onclick="save(1)"/>
		           	</c:if>
                    <c:if test="${planScheme.canDel }">
                        <input class="btn blue save" type="button" value="删除" onclick="save(9)"/>
                    </c:if> 
                </div>
            </div>
			<!-- BEGIN FORM-->
			<form id="saveForm" action="#" class="row">
			    <input type="hidden" name="id" value="${planScheme.id}">
			    <input type="hidden" name="auditStatus" value=""/>
			    <input type="hidden" name="procId" value=""/>
				<input type="hidden" name="proId" value="${planScheme.proId}">
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
							<input type="text" name="proCode" class="form-control col-md-3" disabled value="${project.proCode}">
							<a id="selectPro" title="选择" href="javascript:void(0);" class="icon-select"></a>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" name="proName" class="form-control" disabled value="${project.proName}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" name="proType" class="form-control" disabled value="${project.proType}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目级别</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<tags:config type="select" cssClass="form-control" selectCode="${planScheme.itemGrade}" parentCode="PM.GRADE" name="itemGrade"></tags:config>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="pmLeaders" value="${project.pmLeaders}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8">
							<input type="text" class="form-control" disabled name="pManagers" value="${project.pManagers}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计启动时间</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control datetimepicker" name="designStart" value='<fmt:formatDate value="${planScheme.designStart}" pattern="yyyy-MM-dd" />'>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计完成时间</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control datetimepicker" name="designCompleted" value='<fmt:formatDate value="${planScheme.designCompleted}" pattern="yyyy-MM-dd" />'> 
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案产值(¥)<span class="required">※</span></label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" data-rule-number="true" name="schemeYield" data-rule-required="true" value="<fmt:formatNumber value='${planScheme.schemeYield}' pattern='#,#00.00#'/>">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">承接部门<span class="required">※</span></label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="hidden" id="orgId" name="receptDeptId" value="${planScheme.receptDeptId}">
			            	<input type="text" id="orgName" class="form-control col-md-3" value="${planScheme.orgName}" data-rule-required="true" readonly>
			            	<a id="secOrg" title="选择" href="javascript:void(0);" class="icon-select"></a>
						</div>
					</div>
					
					<div class="form-group col-lg-12 " style="padding-left: 4%;">
						<label class="control-label col-md-1">概况</label>
						<div class="col-md-10 input-icon right">
						    <i class="fa"></i>
						    <textarea class="form-control" rows="3" name="schemeOverview">${planScheme.schemeOverview}</textarea>
						</div>
					</div>
					<div class="form-group col-lg-12 " style="padding-left: 4%;">
						<label class="control-label col-md-1">备注</label>
						<div class="col-md-10 input-icon right">
						    <i class="fa"></i>
							<textarea class="form-control" rows="3" name="remark">${planScheme.remark}</textarea>
						</div>
					</div> 

					<h3 class="form-tit col-lg-12">设计团队</h3>
					<div class="col-lg-5 form-group">
						<div class="clearfix">
							<div class="col-lg-12 text-right col-md-4 col-sm-4 col-xs-4">
								<input type="button" class="btn blue btn_tj" value="添加"/> 
							</div>
						</div>
						<table class="table table-bordered edit" id="designTeam">
							<thead>
								<tr>
									<th  class="text-center col-lg-4">姓名</th>
									<th class="text-center form-group">比例(%)<span class="required">※</span></th>
									<th class="text-center">产值</th>
								</tr>
							</thead>
							<tbody>
							    <c:if test="${not empty shemeTeams}">
							      <c:forEach var="shemeTeam" items="${shemeTeams}" varStatus="st">
							        <tr>
										<td  class="text-center col-lg-4">${shemeTeam.staffName }</td>
										<td  class="col-lg-4 input-icon left">
										<i class="fa"></i>
										<input type="hidden" name="shemeTeams[${st.index}].id" class="text-right" value="${shemeTeam.id }">
										<input type="hidden" name="shemeTeams[${st.index}].staffId" class="text-right" value="${shemeTeam.staffId }">
										<input type="hidden" name="shemeTeams[${st.index}].staffSort" class="text-right" value="${shemeTeam.staffSort }">
										<input type="hidden" name="shemeTeams[${st.index}].refYield" class="text-right" value="${shemeTeam.refYield }">
										<input type="text" name="shemeTeams[${st.index}].refRate"
													class="text-right"
													data-rule-number="true" 
													data-rule-max="100"
													data-rule-min="0" 
													placeholder="0.00"
													data-rule-required="true"
													value="${shemeTeam.refRate }">
												</td>
										<td  class=" col-lg-4 text-right">
										 <fmt:formatNumber value='${shemeTeam.refYield }' pattern='#,#00.00#'/>
										</td>
									</tr>
							      </c:forEach>
							    </c:if>
							
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right"></td>
									<td  class=" col-lg-4 text-right"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-lg-12">
						<p>注：</p> 
						<p>1、特级项目需明确设计总监并全面负责，Ⅰ级项目根据具体情况确定设计总监。       </p>
						<p>2、项目进度计划需严格执行。若有更改需升版方案项目进度计划表，并重新签署生效。 </p>
						<p>3、产值包括标书制作与方案设计两个部分。                                       </p>
					</div>
				</div>
			</form>
			<!-- END FORM-->
			<table id="clone_text" style="display: none">
				<tr>
					<td  class="text-center col-lg-4"></td>
					<td  class="col-lg-4 input-icon left">
					<i class="fa"></i>
					<input type="hidden" name="shemeTeams[{0}].id" class="text-right">
					<input type="hidden" name="shemeTeams[{0}].staffId" class="text-right">
					<input type="hidden" name="shemeTeams[{0}].staffSort" class="text-right">
					<input type="hidden" name="shemeTeams[{0}].refYield" class="text-right" value="">
					<input type="text" name="shemeTeams[{0}].refRate"
							class="text-right"
							data-rule-number="true" 
							data-rule-max="100"
							data-rule-min="0" 
							placeholder="0.00"
							data-rule-required="true">
						</td>
					<td  class=" col-lg-4 text-right"><input type="text" name="shemeTeams[{0}].refYield" disabled placeholder="0.00" class="text-right"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
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
			  if(refRate.charAt(refRate.length-1) !='.'){
				  refRate = new Number(new Number(refRate).toFixed(2));
				  _this.val(refRate);
			  }
			  var schemeYield =	delcommafy($("input[name='schemeYield']").val());
			  if(schemeYield == '' || typeof schemeYield == 'undefined' || isNaN(schemeYield)){
				  schemeYield = 0;
			  }
			  if(refRate == '' || typeof refRate == 'undefined' || isNaN(refRate)){
				  refRate = 0;
			  }
			  var _lastTd = _this.closest("tr").find("td:last");
			  var refYield = new Number(schemeYield) * new Number(refRate)/100;
			  _lastTd.text(toThousands(new Number(refYield).toFixed(2)));
			  _this.closest("tr").find("input[name$='refYield']").val(new Number(refYield).toFixed(2));
			  initTotal();
		});
		
		jQuery(document).on("keyup","input[name='schemeYield']",function(){
			countSchemeYield();
		});
		
		jQuery("#secOrg").on("click",function(){
			selectOrg(selectOrgCallback);
		});
		
		jQuery(".btn_tj").on("click",function(){
			selectStaff(selectStaffBack,'checkbox');
		});
		initTotal();
	});

	function countSchemeYield(){
		var schemeYield = delcommafy($("input[name='schemeYield']").val());
		if(schemeYield == '' || typeof schemeYield == 'undefined' || isNaN(schemeYield)){
			  schemeYield = 0;
		  }
		$("#designTeam tbody tr").each(function(){
			var _this = $(this);
			var refRate = _this.find("input[name$='refRate']").val();
			if(refRate == '' || typeof refRate == 'undefined' || isNaN(refRate)){
				  refRate = 0;
			}
 			var _lastTd = _this.find("td:last");
			var refYield = new Number(schemeYield) * new Number(refRate)/100;
			_lastTd.text(toThousands(new Number(refYield).toFixed(2)));
		    _this.find("input[name$='refYield']").val(new Number(refYield).toFixed(2));
			
			initTotal();
		});
	}
	
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
		if(new Number(totalRefRate) !=100){
			flag = false;
		}
		$("#designTeam tr.total").find("td:eq(1)").text(new Number(totalRefRate).toFixed(2));
		$("#designTeam tr.total").find("td:eq(2)").text(toThousands(new Number(totalRefYield).toFixed(2)));
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
	        success : function(data) {
	        	console.log(data);
	        	if(data.flag == 'true'){
	        		$("input[name='proType']").val(data.project.proType == null?'':data.project.proType );
	        		$("input[name='pmLeaders']").val(data.project.pmLeaders == null?'':data.project.pmLeaders);
	        		$("input[name='pManagers']").val(data.project.pManagers == null?'':data.project.pManagers);
	        		$("#orgName").val(data.project.dutyDeptName == null?'':data.project.dutyDeptName);
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
	        $.jalert({"jatext":"确认删除？<br>点[确定]：执行删除操作<br>点[取消]：放弃删除操作", "jatype":"confirm", "onConfirm":function(){
	        	ajaxSave(true,status);
	        }});
	        
	    }else{
	    	if (jQuery("#saveForm").valid()) {
	            flag = true;
	        }
	    	if(flag){
	    		if(!initTotal()){
	    			$.jalert({"jatext":"比例合计必须为100"});
	                flag = false;
	    		}
	    	}
	    	ajaxSave(flag,status);
	    }
	}
	
	function ajaxSave(flag,status){
		var url ="${site}/admin/wf/planScheme/ajax/esave";
	    if (flag) {
	    	//千分位处理
	    	$("input[name='schemeYield']").val(delcommafy($("input[name='schemeYield']").val()));
	    	$("input:disabled").removeAttr("disabled")
	    	$("input[name='auditStatus']").val(status);
		        jQuery.ajax({
		        type : "POST",
		        url : url,
		        data : jQuery('#saveForm').serialize(),
		        async : false,
		        success : function(data) {
		        	if(data.flag == 'true'){
		        	$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
				  		window.location.href="${site}/admin/wf/planScheme/search";
		 			}});
		        	}else{
		        		$.jalert({"jatext":data.msg});
		        	}
		        }
	        });
	    }
	}
	
	//千分位处理 去掉千分位
	function delcommafy(num){  
	   num = num.replace(/[ ]/g, "");//去除空格  
	   num=num.replace(/,/gi,'');  
	   return num;  
	}
	
	//添加千分位
	function toThousands(num) {
	    return (num || 0).toString().replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	
</script>

</body>
</html>