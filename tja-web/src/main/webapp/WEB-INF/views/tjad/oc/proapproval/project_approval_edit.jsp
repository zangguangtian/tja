<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <title>分期项目立项编辑</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.project.request.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div>
    <center>
        <h3><b>分期项目立项</b></h3>
    </center>
    <hr/>
    <div class=" ">
        <div class="form">
            <!-- BEGIN FORM-->
            <form method="post" class="" id="installmentForm">
                <input type="hidden" name="id" value="${project.id}">
                <input type="hidden" name="projectExtend.id" value="${project.projectExtend.id}">
                <input type="hidden" name="preProId" value="${project.preProId}">
                <div class="form-body clearfix">
                    <div class="form-group col-lg-6">
                        <label class="control-label col-md-4">主项目编号</label>
                        <div class="col-md-7">
                            <input type="text" id="preProCode" class="form-control" value="${project.preProCode}" style="float:left;" readonly="readonly">
                            <a id="theMain" title="选择" href="javascript:void(0);" class="icon-select"></a>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目名称</label>
                        <div class="col-md-7">
                            <input type="text" id="preProName" class="form-control" value="${project.preProName}" disabled="disabled">
                        </div>
                    </div>
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
                        <div class="col-md-7 input-icon right">
                            <tags:config type="select" name="proGrade" cssClass="form-control" otherAttr="data-rule-required='true'" parentCode="PM.GRADE" selectCode="${project.proGrade}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目性质</label>
                        <div class="col-md-7 input-icon right">
                            <tags:config type="select" name="proProp" cssClass="form-control" otherAttr="data-rule-required='true'" parentCode="PM.NATRUE" selectCode="${project.proProp}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">管理归属</label>
                        <div class="col-md-7 input-icon right">
                            <tags:config type="select" name="projectExtend.ownerShip" cssClass="form-control" otherAttr="data-rule-required='true'" parentCode="PM.BELONG" selectCode="${project.projectExtend.ownerShip}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目分类</label>
                        <div class="col-md-7 input-icon right">
                            <tags:config type="select" name="proCategory" cssClass="form-control" otherAttr="data-rule-required='true'" parentCode="PM.CATEGORY" selectCode="${project.proCategory}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">本部/外协</label>
                        <div class="col-md-7 input-icon right">
                            <tags:config type="select" name="projectExtend.innerOuter" cssClass="form-control" otherAttr="data-rule-required='true'" parentCode="PM.METHOD" selectCode="${project.projectExtend.innerOuter}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">合同编号</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" value="${project.contractCode}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">所处状态</label>
                        <div class="col-md-7 input-icon right">
                            <tags:config type="select" name="proStatus" cssClass="form-control" otherAttr="data-rule-required='true'" parentCode="PM.STATUS" selectCode="${project.proStatus}"/>
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
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">承接部门</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" value="${project.dutyDeptName}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">负责建筑师</label>
                        <div class="col-md-7 input-icon right">
                            <input name="projectExtend.builderId" type="hidden" value="${project.projectExtend.builderId}">
                            <input name="projectExtend.builderName" data-rule-required="true" onclick="tizh()" id="selectBuilder" type="text" class="form-control" value="${project.projectExtend.builderName}" style="float:left;" readonly>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">实际合同额(¥)</label>
                        <div class="col-md-7 input-icon right">
                            <input type="text" name="contractAmount" class="form-control text-right" data-rule-required="true" data-rule-number="true" value="${project.contractAmount}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">分包扣减(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.pkgAmount}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">方案扣减(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.schemeAmount}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">其他扣减(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.rebateAmount}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">总建筑面积(万平米)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.projectExtend.buildArea}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">策划产值(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.projectExtend.schemeYield}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">施工图开始时间</label>
                        <div class="col-md-7 input-icon right">
                            <input name="projectExtend.drawingStart" class="form-control datetimepicker" type="text" data-rule-required="true"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.drawingStart}"/>' >
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">施工图(里程碑)完成时间</label>
                        <div class="col-md-7 input-icon right">
                            <input name="projectExtend.drawingEnd" class="form-control datetimepicker" type="text" data-rule-required="true"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.drawingEnd}"/>' >
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">开工时间</label>
                        <div class="col-md-7 input-icon right">
                            <input name="projectExtend.workingStart" class="form-control datetimepicker" type="text" data-rule-required="true"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.workingStart}"/>' >
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">(预计)竣工时间</label>
                        <div class="col-md-7 input-icon right">
                            <input name="projectExtend.workingEnd" class="form-control datetimepicker" type="text" data-rule-required="true"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${project.projectExtend.workingEnd}"/>' >
                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="button" class="btn blue" onclick="save()">保存</button>
                            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/project/approval/list' ">取消</button>
                        </div>
                    </div>
                </div>

            </form>
            <!-- END FORM-->
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
    });

    /*选择主项目*/
    jQuery("#preProCode").on("click", function() {
        var url = context+"/config/query?NO=OC_SELECT_THEMAIN&MODEL=OC";
        openWindow(url, "选择项目", "800", "600", true, false);
    });

    /* 选择后回调方法 */
    function selectTheMain(objs){
        for(var i=0 ; i<objs.length; i++){
            reCall(objs[i]);
        }
    }
    /* 取值：data.字段名 */
    function reCall(data){
        $("input[name='preProId']").val(data.ID);
        $("#preProCode").val(data.PRO_CODE);
        $("#preProName").val(data.PRO_NAME);
    }

    function tizh() {
        selectStaff(selectStaffBack,"radio");
    }

    function selectStaffBack(data){
        $("input[name='projectExtend.builderId']").val(data[0].id);
        $("input[name='projectExtend.builderName']").val(data[0].name);
    }

    function save() {
        if(!jQuery("#installmentForm").valid()){
            return;
        }
        var url = "${site}/admin/project/approval/ajax/save";
        jQuery.ajax({
            type : "POST",
            url : url,
            data : jQuery('#installmentForm').serialize(),
            success : function(data) {
                if(data.flag == 'true'){
                    $.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
                        window.location.href="${site}/admin/project/approval/list";
                    }});
                }else{
                    $.jalert({"jatext":data.msg});
                }
            }
        });
    }

    /* 进入主项目信息页面 */
   jQuery("#theMain").on("click",function () {
        var preProId = jQuery("input[name='preProId']").val();
        if(preProId == ''){
            jQuery.jalert({"jatext":"当前项目没有主项目信息！"});
            return;
        }
        window.location = "${site}/admin/project/approval/tothemain/"+preProId;
    });

</script>
</body>
</html>
