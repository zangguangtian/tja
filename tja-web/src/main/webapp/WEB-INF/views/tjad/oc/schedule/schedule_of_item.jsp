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
    <title>分项工作进展</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.work.schedule.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div class="wrapBox ">
    <center>
        <h3><b>分项进展</b></h3>
    </center>
    <div id="content">
        <div class="form-body clearfix">
            <input type="hidden" id="proId" value="${project.id}"/>
            <div class="form-group col-lg-6">
                <label class="control-label col-md-4">主项编号</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" value="${project.preProCode}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">主项名称</label>
                <div class="col-md-7">
                    <input type="text" id="preProName" class="form-control" value="${project.preProName}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">分项编号</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" value="${project.proCode}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">分项名称</label>
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
                    <tags:config type="label" code="${project.proGrade}"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">项目性质</label>
                <div class="col-md-7 input-icon right">
                    <tags:config type="label" code="${project.proProp}"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">管理归属</label>
                <div class="col-md-7 input-icon right">
                    <tags:config type="label" code="${project.projectExtend.ownerShip}"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">项目分类</label>
                <div class="col-md-7 input-icon right">
                    <tags:config type="label" code="${project.proCategory}"/>
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">本部/外协</label>
                <div class="col-md-7 input-icon right">
                    <tags:config type="label" code="${project.projectExtend.innerOuter}"/>
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
                    <tags:config type="label" code="${project.proStatus}"/>
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
                    <input type="text" class="form-control" value="${project.projectExtend.builderName}" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">本年工时成本(万元)</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" value="" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">已结算比例</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" value="" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">累计进度</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" value="" disabled="disabled">
                </div>
            </div>
            <div class="form-group col-lg-6 ">
                <label class="control-label col-md-4">累计产值</label>
                <div class="col-md-7 input-icon right">
                    <input type="text" class="form-control" value="" disabled="disabled">
                </div>
            </div>
        </div>
    </div>

    <form id="itempageSize_0" action="${site }/admin/work/schedule/progmainitem/ajaxhtml/${project.id}" method="post">
    </form>
    <form id="itempageSize_1" action="${site }/admin/work/schedule/workprogitem/ajaxhtml/${project.id}" method="post">
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
            url : "${site}/admin/work/schedule/progmainitem/ajaxhtml/"+proId,
            dataType : "html",
            success : function(data) {
                jQuery("#itempageSize_0").html(data);
            },
            complete: function(XMLHttpRequest, textStatus){

            }
        });
    }
    function loadWorkProg(){
        var proId = jQuery("#proId").val();
        jQuery.ajax({
            type : "POST",
            url : "${site}/admin/work/schedule/workprogitem/ajaxhtml/"+proId,
            dataType : "html",
            success : function(data) {
                jQuery("#itempageSize_1").html(data);
            },
            complete: function(XMLHttpRequest, textStatus){

            }
        });
    }
</script>
</body>
</html>