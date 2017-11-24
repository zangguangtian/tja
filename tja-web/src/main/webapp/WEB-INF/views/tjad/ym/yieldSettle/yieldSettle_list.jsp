<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>年度产值结算</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="ym.settle.menu.path" scope="request"/>
</head>
<body>
<div class="wrapBox ">
	<div class="col-md-12">
		<div class="tabbable-line boxless tabbable-reversed">
		    <c:set var="allAuth" value="YM_SETTLE_HIS_LIST" />
			<sec:authorize url="/admin/ym/yieldSettle/toedit">
			<c:set var="allAuth" value="YM_SETTLE_LIST" />
	    	<ul class="nav nav-tabs">
	        	<li class="active">
	            	<a href="#tab_0" data-toggle="tab" aria-expanded="true">当期</a>
	            </li>
	            <li class="">
	                <a href="#tab_1" data-toggle="tab" aria-expanded="false">历史</a>
	            </li>
	        </ul>
	        </sec:authorize>
	       	<div class="tab-content">
	       		<!-- 当期 -->
	            <div class="tab-pane active" id="tab_0">
	            	<div class="col-lg-12" id="content">

					</div>
				</div>
	       		<!-- 历史 -->
	       		<div class="tab-pane" id="tab_1">
	       			<div class="col-lg-12" id="content2">
	
					</div>
				</div>
			</div>
	  		</div>
	</div>
</div>
<script type="text/javascript">
jQuery(function(){
	loadList("YM_SETTLE_LIST", $("#content"));
	$("a[href='#tab_0']").on("click", function(){
		loadList("YM_SETTLE_LIST", $("#content"));
	});
	$("a[href='#tab_1']").on("click", function(){
		loadList("YM_SETTLE_HIS_LIST", $("#content2"));
	});
});

function loadList(NO, content_div){
	var sUrl ="${site}/config/ajax/query";
	jQuery.ajax({
		type: "POST",
		url:sUrl,
		data:{"NO":NO, "MODEL":"YM", "qarg.userId":"${SysUser.id}"},
		async: false,
	    success: function(data) {
	    	content_div.empty();
	    	content_div.append(data); 
	    }
	});
}

</script>
</body>
</html>