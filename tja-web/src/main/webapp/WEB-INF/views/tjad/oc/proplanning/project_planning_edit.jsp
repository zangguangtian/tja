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
                        <tags:config type="select" name="proWbs" otherAttr="style='width: 100px'" cssClass="form-control" parentCode="OC.PROJECT.WBS" selectCode="${ocScheme.proWbs}"/>
                    </div>
                    <div class="tab-content">
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
                        <!-- 完整模式 -->
                        <div class="tab-pane active" id="tab_1">
                            <div class="col-lg-5 ">
                                <button class="btn green" id="addNode">节点</button>
                                <table class="table table-bordered edit">
                                    <thead>
                                    <tr class="form-group">
                                        <th class="text-center col-lg-1">序号</th>
                                        <th class="text-center col-lg-1">选择</th>
                                        <th class="text-center col-lg-3">阶段</th>
                                        <th class="text-center col-lg-2">比例</th>
                                        <th class="text-center col-lg-3">专业</th>
                                        <th class="text-center col-lg-2">比例</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="span" value="i"/>
                                    <c:set var="no" value="1"/>
                                    <c:forEach items="${ocSchemeStageMajorList}" var="ocSchemeStageMajor">
                                        <c:if test="${ocSchemeStageMajor.schemeStageId != span}">
                                            <tr class="form-group">
                                                <td class="text-center col-lg-1">${no}</td>
                                                <td class="col-lg-1 text-right" rowspan="${ocSchemeStageMajor.schemeStageCount}"><input type="radio" name="full" class="text-center"/></td>
                                                <td class="col-lg-3 text-right" rowspan="${ocSchemeStageMajor.schemeStageCount}">${ocSchemeStageMajor.schemeStageName}</td>
                                                <td class="col-lg-2 text-right">${ocSchemeStageMajor.schemeStageRatio}</td>
                                                <td class="col-lg-3 text-right">${ocSchemeStageMajor.schemeMajorName}</td>
                                                <td class="col-lg-2 text-right">${ocSchemeStageMajor.schemeMajorRatio}</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${ocSchemeStageMajor.schemeStageId == span}">
                                            <tr class="form-group">
                                                <td class="text-center col-lg-1"><c:out value="${no}"/></td>
                                                <td class="col-lg-2 text-right">${ocSchemeStageMajor.schemeStageRatio}</td>
                                                <td class="col-lg-3 text-right">${ocSchemeStageMajor.schemeMajorName}</td>
                                                <td class="col-lg-2 text-right">${ocSchemeStageMajor.schemeMajorRatio}</td>
                                            </tr>
                                        </c:if>
                                        <c:set var="span" value="${ocSchemeStageMajor.schemeStageId}"/>
                                        <c:set var="no" value="${no+1}"/>
                                    </c:forEach>
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
    <div style="display: none">
        <div class="em_con1 " id="template_return">
            <div class="em_form form">
                <form method="post">
                    <div class="form_group">
                        <label class="control-label col-md-2" style="font-weight: bold">节点类型&nbsp&nbsp&nbsp&nbsp</label>
                        <select name="" class="form-control" style="width: 100px">
                            <option onclick="switchNode(0)">阶段</option>
                            <option onclick="switchNode(1)">专业</option>
                        </select>
                    </div>
                    <div class="tab-content">
                        <!-- 阶段 -->
                        <div class="tab-pane active" id="node_0">
                            <div class="col-lg-5 ">
                                <div class="form-group col-lg-6">
                                    <label class="control-label col-md-4">序号</label>
                                    <div class="col-md-7">
                                        <input class="form-control"  type="text" value="">
                                    </div>
                                </div>
                                <div class="form-group col-lg-6 ">
                                    <label class="control-label col-md-4">名称</label>
                                    <div class="col-md-7">
                                        <select name="" class="form-control" style="width: 100px">
                                            <option onclick="">阶段</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group col-lg-6 ">
                                    <label class="control-label col-md-4">比例</label>
                                    <div class="col-md-7">
                                        <input class="form-control"  type="text" value="">
                                    </div>
                                </div>
                                <div class="form-group col-lg-6 ">
                                    <label class="control-label col-md-4">专业负责人</label>
                                    <div class="col-md-7">
                                        <input class="form-control"  type="text" value="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-1"></div>
                        </div>
                    </div>
                        <div class="tab-content">
                            <!-- 专业 -->
                            <div class="tab-pane active" id="node_1">
                                <div class="col-lg-5 ">
                                    <div class="form-group col-lg-6">
                                        <label class="control-label col-md-4">序号</label>
                                        <div class="col-md-7">
                                            <input class="form-control"  type="text" value="">
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-6 ">
                                        <label class="control-label col-md-4">名称</label>
                                        <div class="col-md-7">
                                            <input class="form-control"  type="text" value="">
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-6 ">
                                        <label class="control-label col-md-4">比例</label>
                                        <div class="col-md-7">
                                            <input class="form-control" type="text" value="">
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-6 ">
                                    </div>
                                </div>
                                <div class="col-lg-1"></div>
                                <div class="col-lg-1"></div>
                            </div>
                        </div>
                    <div class="clearfix">
                        <div class="em_sub">
                            <button type="button" class="btn blue" onclick="saveReturn()">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    jQuery(function () {
        jQuery("select[name='proWbs'] option:first").remove();
        var proWbs = jQuery("select[name=proWbs]").val();
        if(proWbs == 'OC.PROJECT.WBS.SIMPLE'){
            jQuery("#tab_1").hide();
            jQuery("#tab_0").show();
        }else{
            jQuery("#tab_1").show();
            jQuery("#tab_0").hide();
        }
        var upd = ${upd};
        if(upd === false){
            jQuery('select[name="proWbs"]').disabled = true;
        }
    });

    function save() {
        
    }
    
    //添加节点
    jQuery("#addNode").on("click",function(){
        var returnHtml = $("#template_return").parent().html();
        layer.open({
            type: 1,
            title: "复职",
            area: ['600px', '320px'], //宽高
            shade: [0.5, "#393D49"],
            closeBtn: 2,
            content: returnHtml
        });
    });

    function switchTable(data) {
        if(data==1){
            jQuery("#tab_1").show();
            jQuery("#tab_0").hide();
        }else{
            jQuery("#tab_1").hide();
            jQuery("#tab_0").show();
        }
    }
    jQuery("select[name=proWbs]").on("click",function () {
        var proWbs = jQuery("select[name=proWbs]").val();
        if(proWbs == 'OC.PROJECT.WBS.SIMPLE'){
            jQuery("#tab_1").hide();
            jQuery("#tab_0").show();
        }else{
            jQuery("#tab_1").show();
            jQuery("#tab_0").hide();
        }
    });
</script>
</body>
</html>
