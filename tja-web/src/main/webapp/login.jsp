<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>TJA-运营及产值管理系统</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${site }/resources/global/plugins/font-awesome/css/font-awesome.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <link href="${site }/resources/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <link href="${site }/resources/global/plugins/bootstrap/css/bootstrap.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <link href="${site }/resources/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="${site }/resources/global/plugins/select2/css/select2.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <link href="${site }/resources/global/plugins/select2/css/select2-bootstrap.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${site }/resources/global/css/components.min.css?v=${buildVersion}" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${site }/resources/global/css/plugins.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="${site }/resources/pages/css/login.min.css?v=${buildVersion}" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
    </head>
    <!-- END HEAD -->

    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="" onclick="return false;" >&nbsp;</a>
        </div>
        <!-- END LOGO -->
        
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" action="${site}/admin/login.sec" method="post">
                <h3 class="form-title font-green">同济人运营及产值管理系统</h3>
                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
	                <div class="alert alert-danger">
	                    <button class="close" data-close="alert"></button>
	                    <span> ${SPRING_SECURITY_LAST_EXCEPTION} </span>
	                </div>
                </c:if>
                
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">用户名</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="userName" /> </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密&nbsp;&nbsp;码</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="passWord" /> </div>
                <div class="form-actions">
                    <button type="submit" class="btn green btn-block uppercase">登录</button>
                </div>
            </form>
            <!-- END LOGIN FORM -->
        </div>
        <!-- END LOGIN -->
        <!--[if lt IE 9]>
		<script src="${site }/resources/global/plugins/respond.min.js?v=${buildVersion}"></script>
		<script src="${site }/resources/global/plugins/excanvas.min.js?v=${buildVersion}"></script> 
		<script src="${site }/resources/global/plugins/ie8.fix.min.js?v=${buildVersion}"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${site }/resources/global/plugins/jquery.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/bootstrap/js/bootstrap.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/js.cookie.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/jquery.blockui.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js?v=${buildVersion}" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${site }/resources/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/jquery-validation/js/additional-methods.min.js?v=${buildVersion}" type="text/javascript"></script>
        <script src="${site }/resources/global/plugins/select2/js/select2.full.min.js?v=${buildVersion}" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${site }/resources/global/scripts/app.min.js?v=${buildVersion}" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${site }/resources/pages/scripts/login.min.js?v=${buildVersion}" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
        <script type="text/javascript">
        $(function(){
        	$("input[name='userName']").focus();
        });
        </script>
    </body>

</html>