<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>方案产值策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="wf.planScheme.menu.path" scope="request"  />
</head>
<body>
<div class="wrapBox ">
    <div id="content">
    </div>
    <c:set value="false" var="allAuth"></c:set>
    <sec:authorize url="/admin/wf/planScheme/toedit">
      <c:set value="true" var="allAuth"></c:set>
    </sec:authorize>
</div>
<script type="text/javascript">
jQuery(function(){
    var sUrl ="${site}/config/ajax/query";
    jQuery.ajax({
        type: "POST",
        url:sUrl,
        data:{"NO":"PLAN_SCHEME_LIST", "MODEL":"WF", "qarg.userId":"${SysUser.id}"},
        async: false,
        success: function(data) {
            $("#content").empty();
            $("#content").append(data); 
        }
    });
    if(${allAuth}){
    	$("ul[name='rul']").after('<span class="o-btn"><input type=button value="发起" onclick="toadd();" ></span>');
    }
});

function  toadd(){
    window.location.href="${site}/admin/wf/planScheme/toedit/0";
}

</script>
</body>
</html>
