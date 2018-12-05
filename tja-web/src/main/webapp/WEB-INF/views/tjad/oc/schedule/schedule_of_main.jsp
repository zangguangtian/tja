<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <meta charset="utf-8"/>
    <title>主项工作进展</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.work.schedule.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div class="wrapBox ">
    <center>
        <h3><b>主项进展</b></h3>
    </center>
    <hr/>
    <div id="content">
        <input type="hidden" id="proId" value="${project.id}"/>
        <div class="form-body clearfix">
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">项目编号</label>
                <div class="col-md-7 input-icon right">
                    <input type="text" class="form-control text-right" value="${project.proCode}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">项目名称</label>
                <div class="col-md-7 input-icon right">
                    <input type="text" class="form-control text-right" value="${project.proName}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">合同编号</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.contractCode}" disabled="disabled"/>
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
                    <input type="text" class="form-control text-right" value="${project.pkgAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总方案扣减(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.schemeAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总其他扣减(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.rebateAmount}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总建筑面积(万平米)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.projectExtend.buildArea}" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">总策划产值(¥)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control text-right" value="${project.projectExtend.schemeYield}" disabled="disabled"/>
                </div>
            </div>
        </div>
    </div>
    <form id="pageSize_0" action="${site }/admin/work/schedule/progmain/ajaxhtml/1/${project.id}" method="post">
    </form>
    <form id="pageSize_1" action="${site }/admin/work/schedule/workprog/ajaxhtml/1/${project.id}" method="post">
    </form>
</div>
<script type="text/javascript">
    $(function(){
        //进展情况加载
        loadProgMain();
        //工作进度加载
        loadWorkProg();
    });
    function loadProgMain(){
        var proId = jQuery("#proId").val();
        jQuery.ajax({
            type : "POST",
            url : "${site}/admin/work/schedule/progmain/ajaxhtml/1/"+proId,
            dataType : "html",
            success : function(data) {
                jQuery("#pageSize_0").html(data);
            },
            complete: function(XMLHttpRequest, textStatus){

            }
        });
    }
    function loadWorkProg(){
        var proId = jQuery("#proId").val();
        jQuery.ajax({
            type : "POST",
            url : "${site}/admin/work/schedule/workprog/ajaxhtml/1/"+proId,
            dataType : "html",
            success : function(data) {
                jQuery("#pageSize_1").html(data);
            },
            complete: function(XMLHttpRequest, textStatus){

            }
        });
    }
</script>
</body>
</html>