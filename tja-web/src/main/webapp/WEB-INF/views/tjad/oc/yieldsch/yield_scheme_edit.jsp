<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>施工图产值策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.yield.scheme.menu.path" scope="request"  />
</head>
<body>
<div class="">
    <center>
        <h3>施工图产值策划</h3>
    </center>
    <div class="  ">
        <div class="form">
            <!-- BEGIN FORM-->
            <form action="#" class=" ">
                <div class="form-body clearfix">
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">策划编号</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeNo" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">更新日期</label>
                        <div class="col-md-8">
                            <input type="text" name="modifyDate" class="form-control datetimepicker">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目编号</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.proCode }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目名称</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.proName }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目类型</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.proType }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目级别</label>
                        <div class="col-md-8">
                            <tags:config type="label" cssClass="form-control" code="${project.proGrade}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.pmLeaders }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.pManagers }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">用地面积（M<sup>2</sup>）</label>
                        <div class="col-md-8">
                            <input type="text" name="landArea" class="form-control text-right" placeholder="0.00">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">策划依据</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeBasis" class="form-control">
                        </div>
                    </div>
                    <h3 class="form-tit col-lg-12">专业比例<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span>
                        <input type="button" class="btn blue " value="添加" style="float: right;"></h3>
					<div class="table-scrollable">
					    <table class="table table-striped table-bordered table-advance table-hover dataTable">
					        <thead>
					            <tr>
					                <th nowrap="nowrap" rowspan="2" style="ext-align:center;">序号</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">类型名称(系数)</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">类型编号</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">建筑面试(m<sup>2</sup>)</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">土建基准单价(元/m<sup>2</sup>)</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">土建基准产值</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">各专业产值(90%)</th>
					                <c:if test="${not empty majors }">
					                   <c:forEach items="${majors }" var="major" >
					                       <th nowrap="nowrap" colspan="2" style="text-align:center;">${major.configName }</th>
					                   </c:forEach>
					                </c:if>
					            </tr>
					            <tr>
					                <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <th nowrap="nowrap" style="text-align:center;">比例</th>
                                           <th nowrap="nowrap" style="text-align:center;">产值</th>
                                       </c:forEach>
                                    </c:if>
					            </tr>
					        </thead>
                            <tbody>
                                <tr>
                                    <td nowrap="nowrap" width="40px" style="text-align:center;">1</td>
					                <td nowrap="nowrap">产值管理模块配置项</td>
					                <td nowrap="nowrap">10</td>
					                <td nowrap="nowrap">是</td>
					                <td nowrap="nowrap">ADMIN</td>
					                <td nowrap="nowrap">2017-09-21 09:27:05</td>
					                <td nowrap="nowrap">产值管理模块配置项</td>
                                    <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <td nowrap="nowrap" style="text-align:center;">比例</td>
                                           <td nowrap="nowrap" style="text-align:center;">产值</td>
                                       </c:forEach>
                                    </c:if>
					            </tr>
					            <tr>
                                    <td nowrap="nowrap" colspan="3" style="text-align:center;">院内合计</td>
                                    <td nowrap="nowrap">15</td>
                                    <td nowrap="nowrap">是</td>
                                    <td nowrap="nowrap">ADMIN</td>
                                    <td nowrap="nowrap">2017-09-21 10:22:45</td>
                                    <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <td nowrap="nowrap" style="text-align:center;">比例</td>
                                           <td nowrap="nowrap" style="text-align:center;">产值</td>
                                       </c:forEach>
                                    </c:if>
					            </tr>
					        </tbody>
					    </table>
					</div>

                    <h3 class="form-tit col-lg-12">土建产值<span class="control-label" style="font-size:12px;">（元）</span></h3>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">实际合同额</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">分包扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">方案扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">其他扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">土建总产值</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">各专业产值</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人（%）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人（产值）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理（%）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理（产值）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    
                    <h3 class="form-tit col-lg-12">各专业产值<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span></h3>

                    <h3 class="form-tit col-lg-12">各专业部门负责人会签</h3>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">设计负责人</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    
                    
                    
                    
                    <div class="">
                       <div class="row">
                           <div class="col-md-offset-3 col-md-9">
                               <button type="button" class="btn blue">保存</button>
                           </div>
                       </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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
</script>
</body>
</html>