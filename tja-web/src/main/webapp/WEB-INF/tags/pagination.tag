<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="formId" type="java.lang.String" required="true"%>
<%@attribute name="sycn" type="java.lang.String" required="false"%>
<script type="text/javascript">
<!--
var sync = "${empty sycn? '1': sync}";//1:同步；0：异步
jQuery(document).ready(function(){
	jQuery("#${formId} #first").on("click", function(){
        var formId = $(this).closest("form").attr("id");
	    if(sync == "1"){
            changePage(formId, 1);
		}else{
            ajaxChangePage(formId, 1);
		}
	});

    jQuery("#${formId} #previous").on("click", function(){
        var formId = $(this).closest("form").attr("id");
        if(sync == "1"){
            changePage(formId, ${page.pageNo-1});
        }else{
            ajaxChangePage(formId, ${page.pageNo-1});
        }
	});

    jQuery("#${formId} #next").on("click", function(){
        var formId = $(this).closest("form").attr("id");
        if(sync == "1"){
            changePage(formId, ${page.pageNo+1});
        }else{
            ajaxChangePage(formId, ${page.pageNo+1});
        }
	});

    jQuery("#${formId} #last").on("click", function(){
        var formId = $(this).closest("form").attr("id");
        if(sync == "1"){
            changePage(formId, ${page.totalPageCount});
        }else{
            ajaxChangePage(formId, ${page.totalPageCount});
        }
	});

    jQuery("#${formId} #rowsPerPage").on("change", function(){
        debugger;
        var formId = $(this).closest("form").attr("id");
        if(sync == "1"){
            changePage(formId, ${page.pageNo});
        }else{
            ajaxChangePage(formId, ${page.pageNo});
        }
	});

    jQuery("#${formId} #btnGo").on("click", function(){
        var formId = $(this).closest("form").attr("id");
		var goPage = jQuery("#"+formId+" #goPage").val();
		if(goPage <=0 || goPage > ${page.totalPageCount}){
			alert("页数范围 1-${page.totalPageCount}");
		}else{
            if(sync == "1"){
                changePage(formId, goPage);
            }else{
                ajaxChangePage(formId, goPage);
            }
		}
	});
});

//-->
</script>
<div id="page-foot" class="dfquery-page">
	<div class="page-info">
	             共有 <span>${page.totalCount}</span> 条记录，当前第 <span>${page.pageNo}/${page.totalPageCount}</span> 页
	</div>
	<ul class="foot-page">
	    <li>每页记录数：
	        <select id="rowsPerPage" name="rowsPerPage" style="width:50px;">
	            <option value="5" <c:if test="${page.rowsPerPage == 5}">selected</c:if> >5</option>
	            <option value="10" <c:if test="${page.rowsPerPage == 10 }">selected</c:if>>10</option>
	            <option value="15" <c:if test="${page.rowsPerPage == 15 }">selected</c:if>>15</option>
	            <option value="20" <c:if test="${page.rowsPerPage == 20 }">selected</c:if>>20</option>
	            <option value="30" <c:if test="${page.rowsPerPage == 30 }">selected</c:if>>30</option>
	            <option value="50" <c:if test="${page.rowsPerPage == 50 }">selected</c:if>>50</option>
	            <option value="100" <c:if test="${page.rowsPerPage == 100 }">selected</c:if>>100</option>
	        </select>
	    </li>
	    <c:if test="${page.pageNo==1}">
	        <li style="cursor:default;" class="page-first"></li>
	        <li style="cursor:default;" class="page-prev"></li>
	    </c:if>
	    <c:if test="${page.pageNo!=1}">
	        <li id="first" class="page-first"></li>
	        <li id="previous" class="page-prev"></li>
	    </c:if>
	    <li><input type="text" id="goPage" value="" class="choose-page" onkeyup="value=value.replace(/[^\d]/g,'');"/></li>
	    <li id="btnGo" class="page-go">GO</li>
	    <c:if test="${page.pageNo==page.totalPageCount}">
	        <li style="cursor:default;" class="page-next"></li>
	        <li style="cursor:default;" class="page-last"></li>
	    </c:if>
	    <c:if test="${page.pageNo!=page.totalPageCount}">
	        <li id="next" class="page-next"></li>
	        <li id="last" class="page-last"></li>
	    </c:if>
	</ul>
</div>
