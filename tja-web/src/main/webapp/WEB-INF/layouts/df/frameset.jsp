<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="java.util.List"%>
<%@page import="com.df.framework.util.HttpUtil"%>
<%@page import="com.df.framework.sys.domain.SysMenu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title><decorator:title/>-运营及产值管理系统-TJA</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <jsp:include page="frameset_base.jsp" flush="true"/>
        <decorator:head/>
        <decorator:usePage id="pageOwner" />
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <div class="page-wrapper">
            <!-- BEGIN HEADER -->
            <div class="page-header navbar navbar-fixed-top">
                <!-- BEGIN HEADER INNER -->
                <div class="page-header-inner ">
                    <!-- BEGIN LOGO -->
                    <div class="page-logo">
                        <a href="${site }/admin/dashboard" style="text-decoration: none;">
                            <img src="${site }/resources/layouts/layout/img/logo.png" alt="logo" class="logo-default" style="width:40px;height:40px;margin:5px 0px;" /> 
                            <span class="logo-default" style="color: #b4bcc8;">同济人运营及产值管理</span>
                        </a>
                        <div class="menu-toggler sidebar-toggler">
                            <span></span>
                        </div>
                    </div>
                    
                    
                    <%String treePath = (String)pageOwner.getRequest().getAttribute("menu-path");
                    List<SysMenu> menus = HttpUtil.getMenus();
                    if(treePath != null && !"".equals(treePath)){
                        int start = treePath.indexOf("@");
                        int end = treePath.indexOf("@", start + 1);
                        String modelId = treePath.substring(start + 1, end);
                        if(modelId != null && !"".equals(modelId)){
                            SysMenu modelMenu = new SysMenu();
                            modelMenu.setId(modelId);
                            if(menus.indexOf(modelMenu) > -1){
                                List<SysMenu> subMenus = menus.get(menus.indexOf(modelMenu)).getChildren();
                                request.setAttribute("subMenus", subMenus);
                            }
                        }
                        request.setAttribute("treePath", treePath);
                    }%>
                    
                    <ul class="nav navbar-nav hidden-sm hidden-xs">
                        <c:forEach items="${SysMenus }" var="menu" varStatus="vs">
                            <%--一级菜单 --%>
                            <li class="dropdown menu-dropdown">
                            <c:if test="${menu.urlType=='1000' }">
                                <a class="more-dropdown-sub" href="${site}${menu.menuUrl}">
                                    <i class="${menu.iconClass }"></i> ${menu.name } </a>
                            </c:if>
                            <c:if test="${menu.urlType=='2000' }">
                                <a class="more-dropdown-sub" href="javascript:void(0)" onclick="${menu.menuUrl}">
                                    <i class="${menu.iconClass }"></i> ${menu.name } </a>
                            </c:if>
                            <c:if test="${menu.urlType=='3000' }">
                                <a class="more-dropdown-sub" href="${menu.menuUrl}" target="_blank">
                                    <i class="${menu.iconClass }"></i> ${menu.name } </a>
                            </c:if>
                                <c:if test="${menu.hChild == '1' }">
	                                <ul class="dropdown-menu dropdown-menu-fw">
	                                    <c:forEach items="${menu.children }" var="subMenu" varStatus="svs">
	                                        <%--二级菜单 --%>
		                                    <li <c:if test="${subMenu.hChild == '1' }"> class="dropdown-submenu "</c:if>>
		                                    	<c:if test="${subMenu.urlType=='1000' }">
		                                        <a href="${site}${subMenu.menuUrl}">
		                                            <i class="${empty subMenu.iconClass? 'icon-note':subMenu.iconClass }"></i> ${subMenu.name } </a>
		                                        </c:if>
		                                    	<c:if test="${subMenu.urlType=='2000' }">
		                                        <a href="javascript:void(0)" onclick="${subMenu.menuUrl}">
		                                            <i class="${empty subMenu.iconClass? 'icon-note':subMenu.iconClass }"></i> ${subMenu.name } </a>
		                                        </c:if>
		                                    	<c:if test="${subMenu.urlType=='3000' }">
		                                        <a href="${subMenu.menuUrl}" target="_blank">
		                                            <i class="${empty subMenu.iconClass? 'icon-note':subMenu.iconClass }"></i> ${subMenu.name } </a>
		                                        </c:if>
		                                        <c:if test="${subMenu.hChild == '1' }">
			                                        <ul class="dropdown-menu">
	                                                    <c:forEach items="${subMenu.children }" var="thirdMenu" varStatus="tvs">
	                                                        <%--三级菜单 --%>
	                                                        <li <c:if test="${thirdMenu.hChild == '1' }"> class="dropdown-submenu "</c:if>>
	                                                        	<c:if test="${thirdMenu.urlType=='1000' }">
					                                            <a href="${site}${thirdMenu.menuUrl}" class="nav-link ">
					                                                <i class="${empty thirdMenu.iconClass? 'icon-note':thirdMenu.iconClass }"></i> ${thirdMenu.name } </a>
					                                            </c:if>    
	                                                        	<c:if test="${thirdMenu.urlType=='2000' }">
					                                            <a href="javascript:void(0)" onclick="${thirdMenu.menuUrl}" class="nav-link ">
					                                                <i class="${empty thirdMenu.iconClass? 'icon-note':thirdMenu.iconClass }"></i> ${thirdMenu.name } </a>
					                                            </c:if>    
	                                                        	<c:if test="${thirdMenu.urlType=='3000' }">
					                                            <a href="${thirdMenu.menuUrl}" target="_blank" class="nav-link ">
					                                                <i class="${empty thirdMenu.iconClass? 'icon-note':thirdMenu.iconClass }"></i> ${thirdMenu.name } </a>
					                                            </c:if>    
					                                            <c:if test="${thirdMenu.hChild == '1' }">
                                                                    <ul class="dropdown-menu">
                                                                        <c:forEach items="${thirdMenu.children }" var="fourMenu" varStatus="tvs">
                                                                            <%--四级菜单 --%>
                                                                            <li>
                                                                            	<c:if test="${fourMenu.urlType=='1000' }">
									                                           	<a href="${site}${fourMenu.menuUrl}" class="nav-link "> ${fourMenu.name } </a>
									                                           	</c:if>
                                                                            	<c:if test="${fourMenu.urlType=='2000' }">
									                                           	<a href="javascript:void(0)" onclick="${fourMenu.menuUrl}" class="nav-link "> ${fourMenu.name } </a>
									                                           	</c:if>
                                                                            	<c:if test="${fourMenu.urlType=='3000' }">
									                                           	<a href="${fourMenu.menuUrl}" target="_blank" class="nav-link "> ${fourMenu.name } </a>
									                                           	</c:if>
									                                        </li>
                                                                        </c:forEach>
                                                                    </ul>
					                                            </c:if>
					                                        </li>
	                                                    </c:forEach>
			                                        </ul>
		                                        </c:if>
		                                    </li>
	                                    </c:forEach>
	                                </ul>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                       
                    <!-- END LOGO -->
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                        <span></span>
                    </a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <img alt="" class="img-circle" src="${site }/resources/layouts/layout/img/avatar3_small.jpg" />
                                    <span class="username username-hide-on-mobile"> ${SysUser.realName } </span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="javascript:void(0)" onclick="openUserInfo()">
                                            <i class="icon-user"></i> 个人信息 </a>
                                    </li>
                                    <c:if test="${SysUser.authenticationType == 'DB_AUTH' }">
	                                    <li>
	                                        <a href="javascript:void(0)" onclick="modifyPwd('login')">
	                                            <i class="icon-user"></i> 修改密码 </a>
	                                    </li>
                                    </c:if>
                                    <li>
                                        <a href="${site }/admin/logout.sec">
                                            <i class="icon-key"></i> 退出登录 </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END USER LOGIN DROPDOWN -->
                        </ul>
                    </div>
                    <!-- END TOP NAVIGATION MENU -->
                </div>
                <!-- END HEADER INNER -->
            </div>
            <!-- END HEADER -->
            <!-- BEGIN HEADER & CONTENT DIVIDER -->
            <div class="clearfix"> </div>
            <!-- END HEADER & CONTENT DIVIDER -->
            <!-- BEGIN CONTAINER -->
            <div class="page-container">
                <!-- BEGIN SIDEBAR -->
                <div class="page-sidebar-wrapper">
                    <!-- BEGIN SIDEBAR -->
                    <div class="page-sidebar navbar-collapse collapse">
                        <!-- BEGIN SIDEBAR MENU -->
                        <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                            <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                            <c:if test="${not empty subMenus }">
                                <c:forEach items="${subMenus }" var="subMenu" varStatus="svs">
                                    <%--二级菜单--%>
                                    <li class="nav-item <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }"> active open</c:if> ">
                                        <c:if test="${subMenu.urlType=='1000' }">
                                        <a href="${site }${subMenu.menuUrl }" class="nav-link nav-toggle">
                                            <i class="icon-note"></i>
                                            <span class="title">${subMenu.name }</span>
                                            <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }">
                                                <span class="selected"></span>
                                            </c:if>
                                            <c:if test="${subMenu.hChild == '1' }">
                                                <span class="arrow <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }"> open</c:if>"></span>
                                            </c:if>
                                        </a>
                                        </c:if>
                                        <c:if test="${subMenu.urlType=='2000' }">
                                        <a href="javascript:void(0)" onclick="${subMenu.menuUrl}" class="nav-link nav-toggle">
                                            <i class="icon-note"></i>
                                            <span class="title">${subMenu.name }</span>
                                            <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }">
                                                <span class="selected"></span>
                                            </c:if>
                                            <c:if test="${subMenu.hChild == '1' }">
                                                <span class="arrow <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }"> open</c:if>"></span>
                                            </c:if>
                                        </a>
                                        </c:if>
                                        <c:if test="${subMenu.urlType=='3000' }">
                                        <a href="${subMenu.menuUrl}" target="_blank" class="nav-link nav-toggle">
                                            <i class="icon-note"></i>
                                            <span class="title">${subMenu.name }</span>
                                            <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }">
                                                <span class="selected"></span>
                                            </c:if>
                                            <c:if test="${subMenu.hChild == '1' }">
                                                <span class="arrow <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }"> open</c:if>"></span>
                                            </c:if>
                                        </a>
                                        </c:if>
                                        <c:if test="${subMenu.hChild == '1' }">
                                            <ul class="sub-menu" <c:if test="${fn:indexOf(treePath, subMenu.idAndSep)>-1 }">style="display:block;"</c:if> >
                                            <c:forEach items="${subMenu.children }"  var="thirdMenu" varStatus="tvs">
                                                <%--三级菜单 --%>
                                                <li class="nav-item <c:if test="${fn:indexOf(treePath, thirdMenu.idAndSep)>-1 }"> active open</c:if> ">
                                                    <c:if test="${thirdMenu.urlType=='1000' }">
                                                    <a href="${site }${thirdMenu.menuUrl }" class="nav-link nav-toggle">
                                                        <i class="icon-note"></i>
                                                        <span class="title">${thirdMenu.name }</span>
                                                        <c:if test="${thirdMenu.hChild == '1' }">
                                                            <span class="arrow <c:if test="${fn:indexOf(treePath, thirdMenu.idAndSep)>-1 }"> open</c:if>"></span>
                                                        </c:if>
                                                    </a>
                                                    </c:if>
                                                    <c:if test="${thirdMenu.urlType=='2000' }">
                                                    <a href="javascript:void(0)" onclick="${thirdMenu.menuUrl}" class="nav-link nav-toggle">
                                                        <i class="icon-note"></i>
                                                        <span class="title">${thirdMenu.name }</span>
                                                        <c:if test="${thirdMenu.hChild == '1' }">
                                                            <span class="arrow <c:if test="${fn:indexOf(treePath, thirdMenu.idAndSep)>-1 }"> open</c:if>"></span>
                                                        </c:if>
                                                    </a>
                                                    </c:if>
                                                    <c:if test="${thirdMenu.urlType=='3000' }">
                                                    <a href="${thirdMenu.menuUrl}" target="_blank" class="nav-link nav-toggle">
                                                        <i class="icon-note"></i>
                                                        <span class="title">${thirdMenu.name }</span>
                                                        <c:if test="${thirdMenu.hChild == '1' }">
                                                            <span class="arrow <c:if test="${fn:indexOf(treePath, thirdMenu.idAndSep)>-1 }"> open</c:if>"></span>
                                                        </c:if>
                                                    </a>
                                                    </c:if>
                                                    <c:if test="${thirdMenu.hChild == '1' }">
                                                        <ul class="sub-menu" <c:if test="${fn:indexOf(treePath, thirdMenu.idAndSep)>-1 }">style="display:block;"</c:if> >
                                                        <c:forEach items="${thirdMenu.children }"  var="fourMenu" varStatus="tvs">
                                                            <%--四级菜单 --%>
                                                            <li class="nav-item <c:if test="${fn:indexOf(treePath, fourMenu.idAndSep)>-1 }"> active open</c:if> "">
                                                            	<c:if test="${fourMenu.urlType=='1000' }">
                                                            	<a href="${site }${fourMenu.menuUrl }" class="nav-link nav-toggle">
                                                                    <i class="icon-note"></i>
                                                                    <span class="title">${fourMenu.name }</span>
                                                                </a>
                                                                </c:if>
                                                            	<c:if test="${fourMenu.urlType=='2000' }">
                                                            	<a href="javascript:void(0)" onclick="${fourMenu.menuUrl}" class="nav-link nav-toggle">
                                                                    <i class="icon-note"></i>
                                                                    <span class="title">${fourMenu.name }</span>
                                                                </a>
                                                                </c:if>
                                                            	<c:if test="${fourMenu.urlType=='3000' }">
                                                            	<a href="${fourMenu.menuUrl}" target="_blank" class="nav-link nav-toggle">
                                                                    <i class="icon-note"></i>
                                                                    <span class="title">${fourMenu.name }</span>
                                                                </a>
                                                                </c:if>
                                                            </li>
                                                        </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </li>
                                            </c:forEach>
                                            </ul>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                        <!-- END SIDEBAR MENU -->
                    </div>
                    <!-- END SIDEBAR -->
                </div>
                <!-- END SIDEBAR -->
                <!-- BEGIN CONTENT -->
                <div class="page-content-wrapper">
                    <!-- BEGIN CONTENT BODY -->
                    <div class="page-content">
                        <!-- BEGIN PAGE HEADER-->
                        <!-- BEGIN PAGE BAR -->
                        <div class="page-bar">
                            <ul class="page-breadcrumb">
                            <%
                            List<SysMenu> paths = HttpUtil.getCurrPath(treePath, menus);
                            request.setAttribute("navPaths", paths);
                            %>
                            <c:if test="${not empty navPaths }">
                                <c:forEach items="${navPaths }" var="nms" varStatus="nvs">
                                    <li>
                                        <c:choose>
                                            <c:when test="${nvs.index < fn:length(navPaths) - 1}">
                                                <a href="${site }${nms.menuUrl }">${nms.name }</a>
                                                <i class="fa fa-circle"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <span>${nms.name }</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </c:if>
                            </ul>
                        </div>
                        <!-- END PAGE BAR -->
                        <decorator:body/>
                        <div class="clearfix"></div>
                    </div>
                    <!-- END CONTENT BODY -->
                </div>
                <!-- END CONTENT -->
            </div>
            <!-- END CONTAINER -->
        </div>
</body>
    
<script type="text/javascript">
function openUserInfo(){
	var url = "${site}/admin/sys/user/userInfo";
    openWindow(url, "个人信息", "800", "600", true, false);
}

function modifyPwd(ptype){
	layer.open({
        type: 2,
        shade: [0.5, "#393D49"],
        closeBtn: 2,
        title: "修改密码", //不显示标
        area: ["400px", "350px"],
        content: "${site}/admin/sys/user/ajax/topwd/"+ptype
    })
}
</script>
</html>
