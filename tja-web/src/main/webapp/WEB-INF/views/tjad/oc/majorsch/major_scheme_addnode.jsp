<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>添加节点</title>
</head>
<body>
<!-- BEGIN CONTENT BODY -->
<div class="page-content">
    <!-- BEGIN PAGE HEADER-->
	<div class="">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="majorNodeFrm" method="post">
				<input type="hidden" name="majorId" value="${majorId }"> 
				<input type="hidden" name="subSort" value="${subSort }"> 
				<input type="hidden" name="subId" value="${subId }"> 
				<input type="hidden" name="taskSort" value="${taskSort }"> 
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-4">节点类型</label>
						<div class="col-md-7">
							<select name="nodeCategory" class="form-control">
								<option value="s">子项</option>
								<option value="t">任务</option>
							</select>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-4">名称<span class="required">※</span></label>
						<div class="col-md-7 input-icon right">
                            <i class="fa"></i>
							<input type="text" name="divisorName" class="form-control" data-rule-required="true">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-4">比例<span class="required">※</span></label>
						<div class="col-md-7 input-icon right">
                            <i class="fa"></i>
						    <input type="text" name="schemeRatio" class="form-control" data-rule-required="true" data-rule-number="true">
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
<script type="text/javascript">
$(function(){
    $("#save-btn").on("click", function(){
    	if(!jQuery("#majorNodeFrm").valid()){
            return;
        }
    	var url = "${site }/admin/major/scheme/ajax/addnode";
        jQuery.ajax({
            type : "POST",
            url : url,
            data : jQuery("#majorNodeFrm").serialize(),
            dataType : "json",
            success : function(data, status) {
            	jQuery.jalert({"jatype":"refresh", "jatext": data.mess, "onConfirm":function(){
                    if(data.success == "true"){
                        parent.layer.closeAll();
                        parent.location.reload();
                    }
                }});
            },
            complete: function(XMLHttpRequest, textStatus){
                
            }
        });
    });
    
    $("#cancel-btn").on("click", function(){
    	parent.layer.closeAll();
    });
});
</script>
</body>
</html>
