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
	<center>
		<h3></h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="impForm">
			<div class="form-body clearfix">
				<div class="form-group col-xs-12 ">
					<label class="control-label col-xs-2">项目编号</label>
					<div class="col-xs-3">
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
			
            
		</div>
	</div>
</div>
            
<div class="clearfix"></div>
<script type="text/javascript" src="${site}/resources/js/fileupload/jquery.fileupload.js"></script>
<script type="text/javascript">
jQuery(document).on("click",jQuery(" input[type='file'][name='attach']"),function(){
	$(this).fileupload({
		form: "impForm",
  		url: "${site}/admin/oc/settle/ajax/upload",
	    type : "POST",
	    dataType: 'json',
	    add: function (e, data) {
	  	  var goUpload = true;
	  	  var uploadFile = data.files[0];
	  	  if (!(/\.(xls|xlsx)$/i).test(uploadFile.name)) {
	  		  alert('文件类型错误。请上传xls类型文件！');
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
			if(!data.result.flag){
			  if(typeof (data.result.mess.message) == 'undefined'){
				  alert(data.result.mess);
			  }else{
				  alert(data.result.mess.message); 
			  }
			}else{
			  window.location.href = "${site}imp/expense/toPageCurr.action";
			}
        },
        fail : function(e, data){
        	layer.closeAll('loading');
    		alert("上传失败");
        }
 	});
});


</script>
</body>
</html>