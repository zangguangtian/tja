<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <title>首页</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="go.home.menu.path" scope="request"  />
  	<style type="text/css">
  	.common-message {
	    width: 98%;
	    height: 50px;
	    position: relative;
	}
	.tip {
	    display: inline-block;
	    bottom: 0px;
	    position: absolute;
	    margin-left: 5px;
	}
	.tip h5{
		font-weight:bolder;
		line-height:50px;
		margin:0;
	}
	.scroll {
	    width: 93%;
	    height: 50px;
	    margin: -10px 0px;
	    position: absolute;
	    overflow: hidden;
	    right: 0px;
	}
	.scrli, .scrli li{
		line-height:50px;
	    list-style: none;
	    margin: 0;
	    padding: 0;
	    font-size: 14px;
	}
	.icon-btn{
		height: 110px;
	}
	.page-content .icon-btn div {
	    font-size: 12px;
	    margin: 15px 0 10px 0;
	    font-weight: 500;
	}
	.page-content .col-lg-9 p a {
		width: 250px;
	}
  	</style>
  </head>
  
<body>
<!-- BEGIN PAGE TITLE-->
<div class="page-title common-message"> 
	<div class="tip"><h5>公告</h5></div>
	<div class="scroll">
		<ul class="scrli">  
			<c:forEach items="${sysNoticeList}" var="sysNotice">
			<li>${sysNotice.noticeContent}</li>
			</c:forEach>
		</ul>
	</div>
</div>
<!-- END PAGE TITLE-->
<!-- END PAGE HEADER-->
<!-- BEGIN DASHBOARD STATS 1-->
<div class="row">
    <div class="index_con clearfix">
    
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-blue">
            <div class="col-lg-3">
                <a href="${site }/admin/wf/process/approve/search" class="icon-btn">
                    <img src="${site}/resources/pages/img/u629.png" alt="" />
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
                <a href="${site }/admin/wf/process/read/search" class="icon-btn">
                    <img src="${site}/resources/pages/img/u631.png" alt="" />
                    <div> 待阅事务 </div>
                    <span class="badge badge-danger"> ${readPage.totalCount} </span>
                </a>
            </div>
            <div class="col-lg-9">
                <c:if test="${not empty messes}">
                <c:forEach items="${messes}" var="mess">
                    <p>
                    	<c:if test="${not empty mess.url}">
							<a href="${site}${mess.url}" title="${mess.content}">${mess.content}</a>
						</c:if>
						<c:if test="${empty mess.url}">
							<a href="javascript:void(0)" title="${mess.content}" onclick="showContent('${mess.id}')">${mess.content }</a>
						</c:if>
	                    <span>${mess.submiter }</span>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        
        <%-- <sec:authorize url="/admin/ym/weekFill/list">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-red-pink ">
            <div class="col-lg-3">
                <a href="${site}/admin/ym/weekFill/list" class="icon-btn">
                    <img src="${site}/resources/pages/img/u701.png" alt="" />
                    <div>项目周报</div>
                    <span class="badge badge-danger"> ${weekCount} </span>
                </a>
            </div>
            <div class="col-lg-9">
            <c:if test="${not empty weeks}">
            	<c:forEach items="${weeks}" var="week">
                	<p>
	                	<a href="${site}/admin/ym/weekFill/toedit/${week.id}?proId=${week.proId}&periodId=${week.periodId}">项目周报：${week.proCode}${week.proName}</a>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        </sec:authorize>
        
        <sec:authorize url="/admin/ym/monthFill/list">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-purple-plum">
            <div class="col-lg-3">
                <a href="${site}/admin/ym/monthFill/list" class="icon-btn">
                    <img src="${site}/resources/pages/img/u701.png" alt="" />
                    <div>预估进度月报</div>
                    <span class="badge badge-danger"> ${monthCount} </span>
                </a>
            </div>
            <div class="col-lg-9">
			<c:if test="${not empty months}">
            	<c:forEach items="${months}" var="month">
                	<p>
	                	<a href="${site}/admin/ym/monthFill/toedit/${month.id}?proId=${month.proId}&periodId=${month.periodId}">项目月报：${month.proCode}${month.proName}</a>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        </sec:authorize>
        
        <sec:authorize url="/admin/ym/yearFill/list">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-blue">
            <div class="col-lg-3">
                <a href="${site}/admin/ym/yearFill/list" class="icon-btn">
                    <img src="${site}/resources/pages/img/u701.png" alt="" />
                    <div>实际进度年报</div>
                    <span class="badge badge-danger"> ${yearCount} </span>
                </a>
            </div>
            <div class="col-lg-9">
			<c:if test="${not empty years}">
            	<c:forEach items="${years}" var="year">
                	<p>
	                	<a href="${site}/admin/ym/yearFill/toedit/${year.id}?proId=${year.proId}&periodId=${year.periodId}">项目周报：${year.proCode}${year.proName}</a>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        </sec:authorize>
        
        <sec:authorize url="/admin/ym/yieldSettle/list">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 bg-green-seagreen">
            <div class="col-lg-3">
                <a href="${site}/admin/ym/yieldSettle/list" class="icon-btn">
                    <img src="${site}/resources/pages/img/u707.png" alt="" />
                    <div>年度产值结算</div>
                    <span class="badge badge-danger"> ${yieldSettleCount} </span>
                </a>
            </div>
            <div class="col-lg-9">
			<c:if test="${not empty yieldSettles}">
            	<c:forEach items="${yieldSettles}" var="yieldSettle">
                	<p>
	                	<a href="${site}/admin/ym/yieldSettle/toedit/${yieldSettle.id}/${yieldSettle.settleCode}?proId=${yieldSettle.proId}&periodId=${yieldSettle.periodId}&syId=${yieldSettle.syId}">年度结算：${yieldSettle.proCode}${yieldSettle.proName}</a>
                    </p>
                </c:forEach>
            </c:if>
            </div>
        </div>
        </sec:authorize> --%>

    </div>
</div>
<div class="clearfix"></div>
<!-- END DASHBOARD STATS 1-->
<script type="text/javascript">
$(function(){ 
    setInterval('autoScroll(".scroll")', 5000);
});

function autoScroll(obj){  
	$(obj).find(".scrli")
		  .animate({marginTop: "-50px"}, 1000, function(){
			  $(this).css({marginTop: "0px"}).find("li:first").appendTo(this);
		  });
}

function showContent(id){
	$.get(context+"/admin/wf/process/read/ajax/edit/"+id, {}, function(html, textStatus){
		layer.open({
		    type: 1,
			shade: [0.5, '#393D49'],
			closeBtn: 2,
			title: "待阅", //不显示标
			area: ['650px', '300px'],
			content:html,
			btn: ["关闭"],
			yes:function(index, layero){
				window.parent.location.reload();
				layer.closeAll('page');
			}
		});
	});
}

</script>
</body>
</html>
