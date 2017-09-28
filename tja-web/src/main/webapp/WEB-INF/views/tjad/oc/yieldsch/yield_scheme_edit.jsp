<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
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
                            <input type="text" name="modifyDate" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目编号</label>
                        <div class="col-md-8">
                            <input type="text"  class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目名称</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目类型</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目级别</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" disabled="disabled">
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

                    <h3 class="form-tit col-lg-12">产值分配</h3>
                    <div class="col-lg-5 ">
                        <div class="row clearfix">
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                                                                                 项目负责人
                            </div>
                            <div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
                                                                                                比例：
                                <input type="text" class="ta_input">
                            </div>
                            <div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
                                <button class="btn green btn_tj"> 
                                                                                                                 添加
                                     <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                                <tr >
                                    <th  class="text-center col-lg-4">姓名</th>
                                    <th class="text-center">工作量(%)※</th>
                                    <th class="text-center">产值</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr >
                                    <td  class="text-center col-lg-4">张三</td>
                                    <td  class=" col-lg-4"><input type="text" placeholder="123" class="text-right"></td>
                                    <td  class=" col-lg-4 text-right">560001</td>
                                </tr>
                                <tr >
                                    <td  class="text-center col-lg-4">李四四</td>
                                    <td  class=" col-lg-4"><input type="text" placeholder="123" class="text-right"></td>
                                    <td  class=" col-lg-4 text-right">400003</td>
                                </tr>
                                <tr >
                                    <td  class="text-center col-lg-4">合计</td>
                                    <td  class="col-lg-4 text-right">00000</td>
                                    <td  class=" col-lg-4 text-right">411027</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                   <div class="">
                       <div class="row">
                           <div class="col-md-offset-3 col-md-9">
                               <button type="submit" class="btn blue">Submit</button>
                               <button type="button" class="btn default">Cancel</button>
                           </div>
                       </div>
                   </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
</body>
</html>