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
    <title>主项目信息查看</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.project.request.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div>
    <center>
        <h3><b>主项目信息管理</b></h3>
    </center>
    <hr/>
    <div class=" ">
        <div class="form">
            <!-- BEGIN FORM-->
            <form method="post" class="" id="installmentForm">
                <input type="hidden" name="id" value="${project.id}">
                <input type="hidden" name="projectExtend.id" value="${project.projectExtend.id}">
                <div class="form-body clearfix">
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目编号</label>
                        <div class="col-md-7 input-icon right">
                            <input type="text" name="proCode" class="form-control" data-rule-required="true" value="${project.proCode}">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目名称</label>
                        <div class="col-md-7 input-icon right">
                            <input type="text" name="proName" class="form-control" data-rule-required="true" value="${project.proName}">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">合同编号</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" value="${project.contractCode}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">实际合同额(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.contractAmount}" disabled="disabled"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">总分包扣减(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.pkgAmount}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">总方案扣减(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.schemeAmount}" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">总其他扣减(¥)</label>
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
                        <label class="control-label col-md-4">总策划产值(¥)</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control text-right" value="${project.projectExtend.schemeYield}" disabled="disabled">
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
                <div class="form-body clearfix">
                    <b>分期项目</b>
                    <table id="fq" class="table table-striped table-bordered table-advance table-hover dataTable">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th nowrap="nowrap" style="text-align:center;">项目编号</th>
                            <th nowrap="nowrap" style="text-align:center;">项目名称</th>
                            <th nowrap="nowrap" style="text-align:center;">项目类型</th>
                            <th nowrap="nowrap" style="text-align:center;">项目级别</th>
                            <th nowrap="nowrap" style="text-align:center;">所处状态</th>
                            <th nowrap="nowrap" style="text-align:center;">分包扣减</th>
                            <th nowrap="nowrap" style="text-align:center;">方案扣减</th>
                            <th nowrap="nowrap" style="text-align:center;">其他扣减</th>
                            <th nowrap="nowrap" style="text-align:center;">建筑面积</th>
                            <th nowrap="nowrap" style="text-align:center;">策划产值</th>
                            <th nowrap="nowrap" style="text-align:center;">&nbsp;</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="index" value="1"/>
                        <c:forEach items="${projectList}" var="pro">
                        <tr>
                            <td style="text-align:right;">${index}</td>
                            <td style="text-align:right;"><a href="${site}/admin/project/approval/${pro.id}" >${pro.proCode}</a></td>
                            <td style="text-align:right;">${pro.proName}</td>
                            <td style="text-align:right;">${pro.proType}</td>
                            <td style="text-align:right;">${pro.proGradeName}</td>
                            <td style="text-align:right;">${pro.proStatusName}</td>
                            <td style="text-align:right;">${pro.pkgAmount}</td>
                            <td style="text-align:right;">${pro.schemeAmount}</td>
                            <td style="text-align:right;">${pro.rebateAmount}</td>
                            <td style="text-align:right;">${pro.projectExtend.buildArea}</td>
                            <td style="text-align:right;">${pro.projectExtend.schemeYield}</td>
                            <td style="text-align:center;">
                                <div style="width:auto;min-width:25px;display:inline-block;">
                                    <span class="f-del" title="删除" onclick="remove('${pro.id}')"></span>
                                </div>
                            </td>
                        </tr>
                        <c:set var="index" value="${index + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
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

    //选择负责工程师
    /*jQuery("#selectBuilder").on("click",function () {
        selectStaff(selectStaffBack,"radio");
    });*/

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
        var url = "${site}/admin/project/approval/mainajax/save";
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

    function remove(id) {
        jQuery.jalert({"jatype":"confirm", "jatext":"确定要删除当前选中分期项目吗", "onConfirm":function(){
            var url = "${site}/admin/project/approval/ajax/remove/"+id;
            jQuery.ajax({
                type : "POST",
                url : url,
                success : function(data) {
                    if(data.flag == 'true'){
                        $.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
                            window.location.reload();
                        }});
                    }else{
                        $.jalert({"jatext":data.msg});
                    }
                }
            });
        }});
    }

</script>
</body>
</html>
