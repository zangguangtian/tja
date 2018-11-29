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
                <input type="hidden" name="pid" value="${project.id}">
                <input type="hidden" name="peid" value="${project.projectExtend.id}">
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
                            <input type="radio" name="schemeFlag" value="1" <c:if test="${project.projectExtend.schemeFlag==true}">checked</c:if>>是
                            <input type="radio" name="schemeFlag" value="0" <c:if test="${project.projectExtend.schemeFlag==false || empty project.projectExtend.schemeFlag}">checked</c:if>>否
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-4">产值额</label>
                        <div class="col-md-7">
                            <input class="form-control" name="schemeAmount" type="text" value="${project.projectExtend.schemeAmount}">
                        </div>
                    </div>
                    <div class="form_group">
                        <label class="control-label col-md-2" style="font-weight: bold">项目WBS&nbsp&nbsp&nbsp&nbsp</label>
                        <input type="hidden" name="sid" value="${ocScheme.id}"/>
                        <tags:config type="select" name="proWbs" otherAttr="style='width: 100px'" cssClass="form-control" parentCode="OC.PROJECT.WBS" selectCode="${ocScheme.proWbs}"/>
                    </div>
                    <div class="">
                        <!-- 简化模式 -->
                        <div class="form-body clearfix" id="tab_0">
                            <div class="">
                                <label id="addUser" class="btn blue" style="margin-bottom:10px;"><i class="fa fa-plus"></i>人员</label>
                                <table class="table table-bordered edit">
                                    <thead>
                                    <tr class="form-group">
                                        <th class="text-center col-lg-1">序号</th>
                                        <th class="text-center col-lg-2">项目角色</th>
                                        <th class="text-center col-lg-1">姓名</th>
                                        <th class="text-center col-lg-1">比例</th>
                                        <th class="text-center col-lg-4">任职部门</th>
                                        <th class="text-center col-lg-2">备注</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="no_1" value="0"/>
                                    <c:forEach items="${ocSchemeDivisorModelList}" var="ocSchemeDivisorModel">
                                    <tr class="form-group">
                                        <td class="text-center col-lg-1">${no_1+1}<input type="hidden" name="ocSchemeDivisors[${no_1}].id" value="${ocSchemeDivisorModel.id}"/></td>
                                        <td class="col-lg-2 text-right">${ocSchemeDivisorModel.configName}</td>
                                        <td class="col-lg-1 text-right">${ocSchemeDivisorModel.staffName}</td>
                                        <td class="col-lg-1 text-right"><input type="text" name="ocSchemeDivisors[${no_1}].schemeRatio" value="${ocSchemeDivisorModel.schemeRatio}"/></td>
                                        <td class="col-lg-4 text-right">${ocSchemeDivisorModel.orgName}</td>
                                        <td class="col-lg-2 text-right"><input type="text" name="ocSchemeDivisors[${no_1}].remark" value="${ocSchemeDivisorModel.remark}"></td>
                                    </tr>

                                        <c:set var="no_1" value="${no_1+1}"/>
                                    </c:forEach>
                                    <%--<c:if test="${ocSchemeDivisorModelList != null}">
                                        <tr class="form-group">
                                            <td class="text-center col-lg-1">合计</td>
                                            <td class="col-lg-2 text-right"></td>
                                            <td class="col-lg-1 text-right"></td>
                                            <td class="col-lg-1 text-right"><label id="schemeRatioSimple"></label></td>
                                            <td class="col-lg-4 text-right"></td>
                                            <td class="col-lg-2 text-right"></td>
                                        </tr>
                                    </c:if>--%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-1"></div>
                        </div>
                        <!-- 完整模式 -->
                        <div class="form-body clearfix" id="tab_1">
                            <div class=" ">
                                <label id="addNode" class="btn blue" style="margin-bottom:10px;"><i class="fa fa-plus"></i>节点</label>
                                <%--<input type="button" class="btn green" id="addNode"  value="节点"/>--%>
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
                                    <c:set var="no" value="0"/>
                                    <c:forEach items="${ocSchemeStageMajorList}" var="ocSchemeStageMajor">
                                        <c:if test="${ocSchemeStageMajor.schemeStageId != span}">
                                            <tr class="form-group">
                                                <td class="text-center col-lg-1">${no+1}</td>
                                                <td class="col-lg-1 text-right" rowspan="${ocSchemeStageMajor.schemeStageCount}">
                                                    <input value="${ocSchemeStageMajor.schemeStageId}" type="radio" name="full" class="text-center"/>
                                                </td>
                                                <td class="col-lg-3 text-right" rowspan="${ocSchemeStageMajor.schemeStageCount}">
                                                    <input type="hidden" name="ocSchemeStageMajors[${no}].schemeStageId" value="${ocSchemeStageMajor.schemeStageId}"/>${ocSchemeStageMajor.schemeStageName}
                                                </td>
                                                <td class="col-lg-2 text-right" rowspan="${ocSchemeStageMajor.schemeStageCount}">
                                                    <input type="text" data-rule-number="true" name="ocSchemeStageMajors[${no}].schemeStageRatio" value="${ocSchemeStageMajor.schemeStageRatio}">
                                                </td>
                                                <td class="col-lg-3 text-right"><input type="hidden" name="ocSchemeStageMajors[${no}].schemeMajorId" value="${ocSchemeStageMajor.schemeMajorId}"/> ${ocSchemeStageMajor.schemeMajorName}</td>
                                                <td class="col-lg-2 text-right">
                                                    <c:if test="${ocSchemeStageMajor.schemeMajorRatio != null}">
                                                        <input type="text" data-rule-number="true" name="ocSchemeStageMajors[${no}].schemeMajorRatio" value="${ocSchemeStageMajor.schemeMajorRatio}">
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${ocSchemeStageMajor.schemeStageId == span}">
                                            <tr class="form-group">
                                                <td class="text-center col-lg-1"><c:out value="${no+1}"/><input type="hidden" name="ocSchemeStageMajors[${no}].schemeStageId" value="${ocSchemeStageMajor.schemeStageId}"/>
                                                    <input type="hidden" name="ocSchemeStageMajors[${no}].schemeStageRatio" value="${ocSchemeStageMajor.schemeStageRatio}">
                                                </td>
                                                <%--<td class="col-lg-2 text-right"><input type="text" name="ocSchemeStageMajors.schemeStageRatio" value="${ocSchemeStageMajor.schemeStageRatio}"></td>--%>
                                                <td class="col-lg-3 text-right"><input type="hidden" name="ocSchemeStageMajors[${no}].schemeMajorId" value="${ocSchemeStageMajor.schemeMajorId}"/>${ocSchemeStageMajor.schemeMajorName}</td>
                                                <td class="col-lg-2 text-right">
                                                    <c:if test="${ocSchemeStageMajor.schemeMajorRatio != null}">
                                                        <input type="text" data-rule-number="true" name="ocSchemeStageMajors[${no}].schemeMajorRatio" value="${ocSchemeStageMajor.schemeMajorRatio}">
                                                    </c:if>
                                                </td>
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
        if(upd == true){
            jQuery('select[name="proWbs"]').attr("disabled","disabled");
        }

    });

    function save() {
        var url = "${site}/admin/project/planning/ajax/save";

        var _sel = jQuery("select[name='proWbs']");
        var proWbs = _sel.find("option:selected").val();
        if(proWbs == 'OC.PROJECT.WBS.SIMPLE'){
            jQuery('#tab_1 input').attr("disabled","disabled");

            //人员比例
            var schemeRatioSimple = 0;
            jQuery.each($("#projectForm #tab_0 tbody tr"),function(index){
                var stageRatio = new Number(jQuery("input[name='ocSchemeDivisors["+index+"].schemeRatio").val());
                if(!isNaN(stageRatio)){
                    schemeRatioSimple += stageRatio;
                }
            });
            if(schemeRatioSimple > 100){
                jQuery.jalert({"jatext":"人员比例值不能大于100"});
                return;
            }
        }else{
            jQuery('#tab_0 input').attr("disabled","disabled");

            //阶段比例
            var stageRatioSum = 0;
            jQuery.each($("#projectForm #tab_1 tbody tr"),function(index){
                var stageRatio = new Number(jQuery("input[name='ocSchemeStageMajors["+index+"].schemeStageRatio'][type='text']").val());
                if(!isNaN(stageRatio)){
                    stageRatioSum += stageRatio;
                }
            });
        }

        if(stageRatioSum > 100){
            jQuery.jalert({"jatext":"阶段比例值不能大于100"});
            return;
        }
        jQuery("select[name='proWbs']").removeAttr("disabled");
        jQuery.ajax({
            type : "POST",
            url : url,
            data : jQuery('#projectForm').serialize(),
            success : function(data) {
                if(data.flag == 'true'){
                    $.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
                        window.location.href="${site}/admin/project/planning/list";
                    }});
                }else{
                    $.jalert({"jatext":data.msg});
                }
            }
        });
    }
    
    //添加节点
    jQuery("#addNode").on("click",function(){
        var parentId = $("input[name='full']:checked").val();
        var proId = jQuery("input[name='pid']").val();
        var schemeId = jQuery("input[name='sid']").val();
        openWindow("${site}/admin/project/planning/ajax/addnode/"+proId+"/"+schemeId+"/"+parentId, "添加节点", "1000", "600", true, true);
    });

    //添加人员
    jQuery("#addUser").on("click",function(){
        var proId = jQuery("input[name='pid']").val();
        var schemeId = jQuery("input[name='sid']").val();
        openWindow("${site}/admin/project/planning/ajax/adduser/"+proId+"/"+schemeId, "添加人员", "1000", "600", true, true);
    });

    jQuery("select[name=proWbs]").on("change",function () {
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
