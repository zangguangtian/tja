<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <title>首页</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="ym.week.menu.path" scope="request"  />
  </head>
  
<body>
<!-- BEGIN PAGE TITLE-->
<h1 class="page-title"> 
    公告：<small class="">请各位项目负责人/项目经理在11月16日前完成2017年第45周的周报上报！</small>
</h1>
<!-- END PAGE TITLE-->
<!-- END PAGE HEADER-->
<!-- BEGIN DASHBOARD STATS 1-->
<div class="row">
    <div class="index_con clearfix">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-blue">
            <div class="col-lg-3">
                <a href="${site }/admin/wf/process/approve/search" class="icon-btn">
                    <img src="../assets/pages/img/u629.png" alt="" />
                    <div> 待审事务 </div>
                    <span class="badge badge-danger"> ${approvePage.totalCount } </span>
                </a>
            </div>
            <div class="col-lg-9">
            <c:if test="${not empty tasks }">
                <c:forEach items="${tasks }" var="task">
                    <p>
	                    <a href="${site}${task.url }" title="${task.title}">${task.title }</a>
	                    <span>${task.submiter }</span>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-green-seagreen">
            <div class="col-lg-3">
                <a href="javascript:;" class="icon-btn">
                    <img src="../assets/pages/img/u629.png" alt="" />
                    <div>待阅事务</div>
                    <span class="badge badge-danger"> 2 </span>
                </a>
            </div>
            <div class="col-lg-9">
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
            </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-red-pink ">
            <div class="col-lg-3">
                <a href="${site}/admin/ym/weekFill/list" class="icon-btn">
                    <img src="../assets/pages/img/u629.png" alt="" />
                    <div> 项目周报</div>
                    <span class="badge badge-danger"> ${weeksCount} </span>
                </a>
            </div>
            <div class="col-lg-9">
            <c:if test="${not empty weeks}">
            	<c:forEach items="${weeks}" var="week">
                	<p>
	                	<a href="${site}/admin/ym/weekFill/${week.proId}?periodId=${week.periodId}">项目周报：${week.proCode}${week.proName}</a>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-purple-plum">
            <div class="col-lg-3">
                <a href="javascript:;" class="icon-btn">
                    <img src="../assets/pages/img/u629.png" alt="" />
                    <div>预计月表 </div>
                    <span class="badge badge-danger"> 2 </span>
                </a>
            </div>
            <div class="col-lg-9">
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
                <p>
                    <a href="#">项目周报：16-BJ-006海口龙华区……</a>
                </p>
            </div>
        </div>

    </div>
</div>
<div class="clearfix"></div>
<!-- END DASHBOARD STATS 1-->
</body>
</html>
