<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<df:readProp var="menu-path" value="oc.yield.settle.menu.path" scope="request" />
	<title>可结算产值管理-编辑</title>
</head>
<body>
<div class="">
	<center>
		<h3>年度可结算产值-2017</h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form action="" class="" id="settleYieldForm">
			<input type="hidden" name="id" value="${settleYield.id}">
			<div class="form-body clearfix">
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目编号</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.proCode}">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目名称</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.proName}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">合同编号</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.itemNo}">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目类型</label>
					<div class="col-md-7">
						<tags:config type="label" code="${settleYield.proCategory}" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">合同额</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.contractAmount}">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">分包额</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.pkgAmount}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目负责人</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.proFzrName}">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">项目经理</label>
					<div class="col-md-7">
						<input class="form-control" type="text" disabled="disabled" value="${settleYield.proJlName}">
					</div>
				</div>
			</div>
				
			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-lg-4">预估产值(¥)</label>
					<div class="col-lg-7">
						<input name="estimateYield" value="${settleYield.estimateYield}" type="text" placeholder="0.00" data-rule-number="true" class="form-control" >
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-lg-4">可结算产值(¥)<span class="required">※</span></label>
					<div class="col-lg-7 input-icon right">
						<i class="fa"></i>
						<input name="settleYield" value="${settleYield.settleYield}" placeholder="0.00" data-rule-number="true" data-rule-required="true" type="text" class="form-control" >
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">更新人</label>
					<div class="col-md-7">
						<input type="text" disabled="disabled" value="${settleYield.modifier}" class="form-control">
					</div>
				</div>
				<div class="form-group col-lg-6 ">
					<label class="control-label col-md-4">更新时间</label>
					<div class="col-md-7">
						<input type="text" disabled="disabled" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${settleYield.modifyDate}"/>' class="form-control">
					</div>
				</div>
			</div>

				
			<div class="">
			   	<div class="row">
			        <div class="col-md-offset-3 col-md-9">
			            <button type="button" class="btn blue" onclick="save()">保存</button>
			            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/oc/settle/list' ">取消</button>
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
	// 初始化

});

function save(){
	if (jQuery("#settleYieldForm").valid()) {
		
		var estimateYield = $("input[name='estimateYield']").val()||0.00;
		var settleYield = $("input[name='settleYield']").val()||0.00;
		if(estimateYield <= 0 || settleYield <= 0) {
			$.jalert({"jatext":"预估产值、可结算产值需>0"});
			return;
		}
		
		var url ="${site}/admin/oc/settle/ajax/save";
		$.ajax({
			type : "post",
		 	url : url,
		 	data : $("#settleYieldForm").serialize(),
		 	error : function(request) {
		 		$.jalert({"jatext":"Connection error"});
		 	},
		 	success : function(data) {
		 		if(data.flag == "true"){
		 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
				  		window.location.href="${site}/admin/oc/settle/${settleYield.id}";
		 			}});
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
		 	}
		});
	}
}

</script>
</body>
</html>