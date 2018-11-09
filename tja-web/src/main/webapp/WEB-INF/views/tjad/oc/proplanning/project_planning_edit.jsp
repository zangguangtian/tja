<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <title>项目策划编辑</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.project.scheme.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
<div>
    <center>
        <h3><b>项目策划</b></h3>
    </center>
    <hr/>
    <div class=" ">
        <div class="form">
            <!-- BEGIN FORM-->
            <form method="post" class="" id="projectForm">
                <input type="hidden" name="id" value="${project.id}">
                <input type="hidden" name="projectExtend.id" value="${project.projectExtend.id}">
                <div class="form-body clearfix">
                    <div class="form-group col-lg-6">
                        <label class="control-label col-md-4">项目编号</label>
                        <div class="col-md-7">
                            <label class="control-label">${project.proCode}</label>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目名称</label>
                        <div class="col-md-7">
                            <label class="control-label">${project.proName}</label>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目类型</label>
                        <div class="col-md-7">
                            <label class="control-label">${project.proType}</label>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目级别</label>
                        <div class="col-md-7">
                            <tags:config type="label" cssClass="control-label" code="${project.proGrade}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目负责人</label>
                        <div class="col-md-7">
                            <label class="control-label">${project.pmLeaders}</label>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">项目经理</label>
                        <div class="col-md-7">
                            <label class="control-label">${project.pManagers}</label>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">方案产值</label>
                        <div class="col-md-7">
                            <input type="radio" name="schemeFlag" value="1" <c:if test="${project.projectExtend.schemeFlag=='1'}">checked</c:if>>是
                            <input type="radio" name="schemeFlag" value="0" <c:if test="${project.projectExtend.schemeFlag=='0' || empty project.projectExtend.schemeFlag}">checked</c:if>>否
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">产值额</label>
                        <div class="col-md-7">
                            <input class="form-control"  type="text" value="${project.projectExtend.schemeAmount}">
                        </div>
                    </div>
                    <div class="form_group">
                        <label class="control-label col-md-2" style="font-weight: bold">项目WBS&nbsp&nbsp&nbsp&nbsp</label>
                        <select name="" class="form-control" style="width: 100px">
                            <option onclick="switchTable(0)">简化模式</option>
                            <option onclick="switchTable(1)">完整模式</option>
                        </select>
                    </div>
                    <div class="tab-content">
                        <!-- 完整模式 -->
                        <div class="tab-pane active" id="tab_1">
                            <div class="col-lg-5 ">
                                <table class="table table-bordered edit">
                                    <thead>
                                    <tr class="form-group">
                                        <th class="text-center col-lg-2">序号</th>
                                        <th class="text-center col-lg-2">选择</th>
                                        <th class="text-center col-lg-2">阶段</th>
                                        <th class="text-center col-lg-2">比例</th>
                                        <th class="text-center col-lg-2">专业</th>
                                        <th class="text-center col-lg-2">比例</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="form-group">
                                        <td class="text-center col-lg-2"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-1"></div>
                        </div>
                        <!-- 简化模式 -->
                        <div class="tab-pane" id="tab_0">
                            <div class="col-lg-5 ">
                                <table class="table table-bordered edit">
                                    <thead>
                                    <tr class="form-group">
                                        <th class="text-center col-lg-1">序号</th>
                                        <th class="text-center col-lg-1">选择</th>
                                        <th class="text-center col-lg-2">项目角色</th>
                                        <th class="text-center col-lg-2">姓名</th>
                                        <th class="text-center col-lg-2">比例</th>
                                        <th class="text-center col-lg-2">任职部门</th>
                                        <th class="text-center col-lg-2">备注</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr class="form-group">
                                        <td class="text-center col-lg-1"></td>
                                        <td class="col-lg-1 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                        <td class="col-lg-2 text-right"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-1"></div>
                        </div>
                    </div>
                </div>
                <div class="">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="button" class="btn blue" onclick="save()">保存</button>
                            <button type="button" class="btn default" onclick=" window.location.href='${site}/admin/project/planning/list' ">取消</button>
                        </div>
                    </div>
                </div>

            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript">
    function switchTable(data) {
        if(data==1){
            jQuery("#tab_1").show();
            jQuery("#tab_0").hide();
        }else{
            jQuery("#tab_1").hide();
            jQuery("#tab_0").show();
        }
    }
</script>
</body>
</html>
