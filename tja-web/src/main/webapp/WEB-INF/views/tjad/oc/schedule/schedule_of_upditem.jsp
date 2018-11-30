<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>更新分项进展</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.work.schedule.menu.path" scope="request"  />
</head>
<body>
<div class="">
    <h5>&nbsp;&nbsp;&nbsp;&nbsp;更新进展</h5>
    <div class=" ">
        <div class="form">
            <form action="" class="" id="stepFillForm">
            <input type="hidden" name="id" value="${ocStepFill.id}"/>
            <div class="form-body clearfix">
                <div class="form-group col-md-6 ">
                    <label class="control-label col-md-4">分项状态</label>
                    <div class="col-md-7">
                        <tags:config type="select" name="divisorStatus" cssClass="form-control" parentCode="PM.STATUS" selectCode="${ocStepFill.divisorStatus}" />
                    </div>
                </div>
                <div class="form-group col-md-6 ">
                    <label class="control-label col-md-4">进展状态</label>
                    <div class="col-md-7">
                        <tags:config type="select" name="stepStatus" otherAttr="data-rule-required='true'" cssClass="form-control" parentCode="OC.SCHEDULE.STATUS" selectCode="${ocStepFill.stepStatus}"/>
                    </div>
                </div>
                <div class="form-group col-md-12 ">
                    <label class="control-label col-md-2">工作内容和进展情况<span class="required">※</span></label>
                    <div class="col-md-7 input-icon right">
                        <input type="text" name="workContent" data-rule-required="true" class="form-control" value="${ocStepFill.workContent}"/>
                    </div>
                </div>
                <div class="form-group col-md-12 ">
                    <label class="control-label col-md-2">下阶段工作进展<span class="required">※</span></label>
                    <div class="col-md-7 input-icon right">
                        <input type="text" name="workPlan" data-rule-required="true" class="form-control" value="${ocStepFill.workPlan}"/>
                    </div>
                </div>
                <div class="form-group col-md-12 ">
                    <label class="control-label col-md-2">备案情况</label>
                    <div class="col-md-7">
                        <input type="text" name="remark" class="form-control" value="${ocStepFill.remark}"/>
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
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#save-btn").on("click", function(){
        if(!jQuery("#stepFillForm").valid()){
            return;
        }
        var url = "${site}/admin/work/schedule/ajax/item/progress";
        jQuery.ajax({
            type : "POST",
            url : url,
            data : jQuery("#stepFillForm").serialize(),
            dataType : "json",
            success : function(data) {
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
</script>
</body>
</html>
