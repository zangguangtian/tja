<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>施工图产值策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.yield.scheme.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
    <style type="text/css">
        #majorRatio td[data-major]{width:80px;text-align:right;}
        #majorRatio tr.total td{text-align:right;}
    </style>
</head>
<body>
<div class="">
    <center>
    	<c:if test="${empty print }">
	    	<input type="button" id="prev-print-btn" value="打印预览" class="btn blue" style="float:right;position:absolute;right:55px;">
    	</c:if>
    	<c:if test="${not empty print }">
    		<input type="button" id="print-btn" value="打印" class="btn blue" style="float:right;position:absolute;right:55px;">
    	</c:if>
        <h3>施工图产值策划</h3>
    </center>
    <div <c:if test="${empty print }"> class="view"</c:if><c:if test="${not empty print }"> class="print"</c:if>>
        <div class="form">
            <!-- BEGIN FORM-->
            <form id="schemeForm" method="post">
                <input type="hidden" id="ratioParam" value="${ratioParam }">
                <input type="hidden" name="id" value="${yieldScheme.id }">
                <input type="hidden" name="proId" value="${project.id }">
                <input type="hidden" name="relationId" value="${yieldScheme.relationId }">
				<input type="hidden" name="atturl" value="${site}/admin/sys/file/ajax/list/0/${yieldScheme.relationId}">
                <div class="form-body clearfix" style="padding-bottom: 0">
                	<div id="base-info-div" class="col-md-12">
                		<jsp:include page="yield_scheme_view_base.jsp"/>
                	</div>
	                <div id="ratio-info-div" class="col-md-12">
	                    <jsp:include page="yield_scheme_view_ratio.jsp"/>
					</div>
					<div id="civil-info-div" class="col-md-12">	
						<jsp:include page="yield_scheme_view_civil.jsp"/>
	                </div>
	                <div id="stage-info-div" class="col-md-12">    
	                    <jsp:include page="yield_scheme_view_stage.jsp"/>
					</div>
					<div id="principal-info-div" class="col-md-12">
						<jsp:include page="yield_scheme_view_principal.jsp"/>
					</div>
				</div>
				<c:if test="${empty print }">
					<jsp:include page="../../../framework/common/fileupload/include_attach.jsp"/>
				</c:if>
				<br><br><br>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/oc/yieldscheme.js?v=${buildVersion}"></script>
<script type="text/javascript">
/*打印*/
$("#prev-print-btn").dfprint({
	action : "printview",
    width : "1000",
    url : "${site}/admin/yield/scheme/toprint/${yieldScheme.id }"
});

/*打印*/
$("#print-btn").dfprint({
    action : "print"
});

$(function(){
    /**计算院内合计*/
    calViewYLTotal();
    
    /**计算各专业产值列表中各阶段的产值合计*/
    calViewMYTotal();
});

/**专业比例列表更多展示*/
$(document).on("click", "#more-btn", function(){
	viewRatioMore();
});

/**打开基本信息编辑*/
$(document).on("click", "#base-info-div .fa-edit", function(){
	loadBaseEdit();
});

/**基本信息编辑页面保存*/
$(document).on("click", "#base-info-div #save-btn", function(){
	saveBase();
});

/**基本信息编辑页面取消*/
$(document).on("click", "#base-info-div #cancel-btn", function(){
	loadBaseView();
});

/**打开专业比例编辑*/
$(document).on("click", "#ratio-info-div .fa-edit", function(){
	loadRatioEdit();
});

/**专业比例编辑页面保存*/
$(document).on("click", "#ratio-info-div #save-btn", function(){
	saveRatio();
});

/**专业比例编辑页面取消*/
$(document).on("click", "#ratio-info-div #cancel-btn", function(){
	loadRatioView();
});

/**打开土建产值编辑*/
$(document).on("click", "#civil-info-div .fa-edit", function(){
	loadCivilEdit();
});

/**土建产值编辑页面保存*/
$(document).on("click", "#civil-info-div #save-btn", function(){
	saveCivil();
});

/**土建产值编辑页面取消*/
$(document).on("click", "#civil-info-div #cancel-btn", function(){
	loadCivilView();
});

/**打开各专业产值编辑*/
$(document).on("click", "#stage-info-div .fa-edit", function(){
	loadStageEdit();
});

/**各专业产值编辑页面保存*/
$(document).on("click", "#stage-info-div #save-btn", function(){
	saveStage();
});

/**各专业产值编辑页面取消*/
$(document).on("click", "#stage-info-div #cancel-btn", function(){
	loadStageView();
});

/**打开各专业部门负责人会签编辑*/
$(document).on("click", "#principal-info-div .fa-edit", function(){
	loadPrincipalEdit();
});

/**各专业部门负责人会签编辑页面保存*/
$(document).on("click", "#principal-info-div #save-btn", function(){
	savePrincipal();
});

/**各专业部门负责人会签编辑页面取消*/
$(document).on("click", "#principal-info-div #cancel-btn", function(){
	loadPrincipalView();
});
</script>
</body>
</html>