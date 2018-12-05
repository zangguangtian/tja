<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>专业工作进展</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.work.schedule.menu.path" scope="request"  />
</head>
<body>
<div class="wrapBox ">
    <center>
        <h3><b>分项专业进展</b></h3>
    </center>
    <div id="content">
        <div class="form-body clearfix">
            <input type="hidden" id="proId" value="${project.id}"/>
            <input type="hidden" id="scheduleId" value="${scheduleId}"/>
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
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-group col-lg-6">
            <label class="control-label col-md-6">阶段：</label>
            <div class="col-md-3">
                <select style="width: 100px" name="stage" class="form-control">
                    <c:forEach items="${stage}" var="stage">
                        <option value="${stage.id}">${stage.divisorName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-lg-6">
            <label class="control-label col-md-2">专业：</label>
            <div class="col-md-3">
                <select style="width: 100px" name="major" class="form-control">
                    <c:forEach items="${major}" var="major">
                        <option value="${major.id}">${major.divisorName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <button type="button" style="float: right;margin-left: 30px" class="btn blue" onclick="toaddmajor(0)">新增</button>
    </div>
    <div class="col-md-12">
    <form id="majorPageSize_0" action="" method="post">
    </form>
    </div>
    <div class="col-md-12">
    <form id="majorPageSize_1" action="" method="post">
    </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var stage = jQuery("select[name='stage']").val();
        var major = jQuery("select[name='major']").val();
        if(stage==null){
            jQuery.jalert({"jatext":"没有阶段信息！"});
            return;
        }
        if(major==null){
            jQuery.jalert({"jatext":"没有专业信息！"});
            return;
        }
        //加载进展信息
        loadProg(major);
        loadWork(major);
    });

    jQuery("select[name='stage']").on("change",function () {
        var proId = jQuery("#proId").val();
        var stage = jQuery("select[name='stage']").val();
        var url = "${site}/admin/work/schedule/ajax/updmajor/change/"+proId+"/"+stage;
        $.ajax({
            type : "POST",
            url : url,
            dataType : "json",
            success: function(data) {
                var selectlist =data;
                $("select[name='major']").empty();
                for(var i=0;i<selectlist.length;i++)
                {
                    $("select[name='major']").append("<option value='"+data[i].id+"'>"+data[i].divisorName+"</option>");
                }
                loadMajor();
            },
            error: function() {
                alert("faile to request data");
            }
        });
    });

    function loadMajor() {
        var major = jQuery("select[name='major']").val();
        if(major==null){
            jQuery.jalert({"jatext":"没有专业信息！"});
        }
        loadProg(major);
        loadWork(major);
    }

    jQuery("select[name='major']").on("change",function () {
        var major = jQuery(this).val();
        loadProg(major);
        loadWork(major);
    });

    function loadProg(data){
        var proId = jQuery("#proId").val();
        var parentId = data;
        var url = "${site}/admin/work/schedule/progmajor/ajaxhtml/"+proId+"/"+parentId;
        jQuery.ajax({
            type : "POST",
            url : url,
            dataType : "html",
            success : function(data) {
                jQuery("#majorPageSize_0").html(data);
                jQuery("#majorPageSize_0").attr("action", url);
            },
            complete: function(XMLHttpRequest, textStatus){

            }
        });
    }

    function loadWork(data) {
        var proId = jQuery("#proId").val();
        var majorId = data;
        var url = "${site}/admin/work/schedule/workmajor/ajaxhtml/"+proId+"/"+majorId;
        jQuery.ajax({
            type : "POST",
            url : url,
            dataType : "html",
            success : function(data) {
                jQuery("#majorPageSize_1").html(data);
                jQuery("#majorPageSize_1").attr("action", url);
            },
            complete: function(XMLHttpRequest, textStatus){

            }
        });
    }

    function toaddmajor(data) {
        var stepId = data;
        var scheduleId = jQuery("#scheduleId").val();
        var proId = jQuery("#proId").val();
        var majorId = jQuery("select[name='major']").val();
        if(majorId==null){
            jQuery.jalert({"jatext":"请选择专业！"});
            return;
        }
        openWindow("${site}/admin/work/schedule/ajax/updpromajor/"+stepId+"/"+scheduleId+"/"+proId+"/"+majorId, "更新专业进展", "1000", "600", true, true);
    }

</script>
</body>
</html>