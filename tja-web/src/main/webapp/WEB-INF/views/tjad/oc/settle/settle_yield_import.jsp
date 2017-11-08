<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="empty"/>
    <title>可结算产值管理-导入</title>
    <style type="text/css">
	    #uniform-undefined{
	    width: 85px;
	    position: absolute;
	    right: 20px;
	    background-image: url(${site}/resources/images/sprite.png);
	    height: 37px;
	 }
    </style>
</head>
<body>
<div class="">
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="impForm">
			<div class="clearfix">
				<div class="form-group col-xs-12 ">
					<label class="control-label col-xs-2" style="margin-top:5px;">期间</label>
					<div class="col-xs-3" style="margin:5px 0px;">
						<select class="form-control" name="period">
							<c:if test="${!empty periodSelect}">
							<c:forEach items="${periodSelect}" var="period" varStatus="s">
								<option value="${period.id}">${period.periodName}</option>
							</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="col-xs-4">
					  	<div class="uploader" id="uniform-undefined" >
					  	<span class="action" style="background-image:url(${site}/resources/images/sprite.png);background-position: center;position: absolute;left: 37%;top: 31%;">导入</span>
							<input type="file" name="attach" size="19" style="opacity: 0;width: 85px; height: 100%;cursor:pointer;" fileTypes="xls|xlsx">
						</div>
					</div>
				</div>
			</div>
			</form>
			<!-- END FORM-->
			 <div class="clearfix showResult" style="padding:0px 6px;">
			 <div class="form-group col-lg-12 ">
                 <label class="control-label col-md-3">数据检查结果</label>
                 <div class="col-md-8">
                   <span>总记录数：</span>
                   <span class="total"></span>
                   <span>正常数据：</span>
                   <span class="normal"></span>
                   <span>异常数据：</span>
                   <span class="error"></span>
                 </div>
             </div>
			</div>
			<div class="wrapper">
				<div class="wrapdiv">
					<div class="wrapBox ">
					    <div id="content">
					    </div>
					</div>
				</div>
			</div>
			<div style="position: absolute;bottom: 8%;right: 5%;">
			   	<div class="row">
			        <div class="col-md-offset-3 col-md-9">
			            <button type="button" class="btn blue" onclick="save()">确定</button>
			        </div>
			   	</div>
			</div>
		</div>
	</div>
</div>
            
<div class="clearfix"></div>
<script type="text/javascript" src="${site}/resources/js/fileupload/jquery.fileupload.js"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
	ajaxImpInfo("");
	
	jQuery("input[type='file'][name='attach']").on("click", function(){
		$(this).fileupload({
			form: "impForm",
	  		url: "${site}/admin/oc/settle/ajax/upload",
		    type : "POST",
		    dataType: 'json',
		    add: function (e, data) {
		  	  var goUpload = true;
		  	  var uploadFile = data.files[0];
		  	  if (!(/\.(xls|xlsx)$/i).test(uploadFile.name)) {
		  		jQuery.jalert({"jatext":"文件类型错误。请上传xls类型文件！"});
		  		  goUpload = false;
		  	  }
		  	  if (goUpload == true) {
		  		  layer.load(1, {
		  			  shade: [0.3,'#fff'] //0.1透明度的白色背景
		  		  });
		  		  data.submit();
		  	  }
		    },
			done: function (e, data) {
				layer.closeAll('loading');
				if(!data.result.status){
				  if(typeof (data.result.mess.message) == 'undefined'){
					  jQuery.jalert({"jatext":data.result.mess});
				  }else{
					  jQuery.jalert({"jatext":data.result.mess.message});
				  }
				}else{
				 //导入成功  加载通用查询 
				 //jQuery(".showResult").removeAttr("style");
				 $(".showResult").find("span.total").text(data.result.totalRecord);
				 $(".showResult").find("span.normal").text(data.result.validRecord);
				 $(".showResult").find("span.error").text(data.result.errorRecord);
				 
				 ajaxImpInfo(data.result.dateFormat);
				 
				}
	        },
	        fail : function(e, data){
	        	layer.closeAll('loading');
	    		jQuery.jalert({"jatext":"上传失败"});
	        }
	 	});
	});
});


function ajaxImpInfo(date){
	var sUrl ="${site}/config/ajax/query";
	jQuery.ajax({
		type: "POST",
		url:sUrl,
		data:{"NO":"OC_SETTLEYIELD_IMP" ,"MODEL":"OC","qarg.date" : date},
		async: false,
	    success: function(data) {
	    	$("#content").empty();
			$("#content").append(data); 
	    }
	});
}

function save(){
	window.opener.location.reload(); //刷新父窗口中的网页
	window.close();//关闭当前窗窗口
}
</script>
</body>
</html>