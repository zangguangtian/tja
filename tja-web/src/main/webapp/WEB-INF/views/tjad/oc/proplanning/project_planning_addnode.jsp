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
<div class="">
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form method="post" id="nodeForm">
				<input name="proId" type="hidden" value="${proId}"/>
				<input name="schemeId" type="hidden" value="${schemeId}"/>
				<input type="hidden" name="parentId" value="${parentId}"/>
				<div class="form-body clearfix">
					<div class="form_group col-lg-6">
						<label class="control-label col-md-2" style="font-weight: bold">节点类型&nbsp&nbsp&nbsp&nbsp</label>
						<div class="col-md-7">
							<select name="isPanent" class="form-control" style="width: 100px">
								<option value="phase">阶段</option>
								<option value="professional">专业</option>
							</select>
						</div>
					</div>
					<div class="form-group col-lg-6">
						<label class="control-label col-md-2">名称<span class="required">※</span></label>
						<div class="col-md-7 input-icon right">
							<input class="form-control" name="divisorName" type="text" data-rule-required="true">
							<tags:config type="select" parentCode="PM.MAJOR" name="divisorName" cssClass="form-control"/>
						</div>
					</div>
					<div class="form-group col-lg-6">
						<label class="control-label col-md-2">比例<span class="required">※</span></label>
						<div class="col-md-7 input-icon right">
							<input class="form-control" name="schemeRatio" type="text" data-rule-required="true" data-rule-number="true">
						</div>
					</div>
					<div class="form-group col-lg-6" id="isProfessional">
						<label class="control-label col-md-2">专业负责人</label>
						<div class="col-md-7">
							<input type="hidden" name="divisorDirector"><input type="text" id="director" class="form-control" placeholder="请选择" readonly="readonly">
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
    jQuery("#isProfessional").hide();
    jQuery("select[name='divisorName']").hide();


    var isParent = jQuery("input[name='parentId']").val();
    if(typeof(isParent) == 'undefined'){
        jQuery("select[name='isPanent']").attr("disabled","disabled");
    }

    jQuery("select[name='isPanent']").on("change",function () {
        var isPanent = $(this).val();
        if(isPanent == 'phase'){
            jQuery("#isProfessional").hide();
            jQuery("select[name='divisorName']").hide();
            jQuery("input[name='divisorName']").show();
        }else{
            jQuery("#isProfessional").show();
            jQuery("select[name='divisorName']").show();
            jQuery("input[name='divisorName']").hide();
        }
    });

	// 选择专业负责人
    $("#director").on("click",function(){
        userObj = this;
        selectStaff(selectStaffCallBack, "radio");
    });

    /**选择专业负责人后的回调方法*/
    function selectStaffCallBack(data){
        $(userObj).siblings("input[name='divisorDirector']").val(data[0].id);
        $(userObj).val(data[0].name);
    }

    $("#save-btn").on("click", function(){
    	/*if(!jQuery("#majorSchAddNodeForm").valid()){
            return;
        }*/
    	var _sel = jQuery("select[name='isPanent']");
    	var isPanent = _sel.find("option:selected").val();
    	if(isPanent == 'phase'){
            jQuery("input[name='parentId']").attr("disabled","disabled");
            jQuery("input[name='divisorDirector']").attr("disabled","disabled");
            jQuery("select[name='divisorName']").attr("disabled","disabled");
		}else{
            jQuery("input[name='divisorName']").attr("disabled","disabled");
		}
        var form = jQuery("#nodeForm").serialize();
        var url = "${site}/admin/project/planning/ajax/nodesave";
        jQuery.ajax({
            type : "POST",
            url : url,
            data : jQuery("#nodeForm").serialize(),
            dataType : "json",
            success : function(data, status) {
            	jQuery.jalert({"jatype":"refresh", "jatext": data.msg, "onConfirm":function(){
                    if(data.flag == "true"){
                        window.close();
                        opener.location.reload();
                    }
                }});
            },
            complete: function(XMLHttpRequest, textStatus){
                
            }
        });
    });
    
    $("#cancel-btn").on("click", function(){
        window.close();
    });
});
</script>
</body>
</html>
