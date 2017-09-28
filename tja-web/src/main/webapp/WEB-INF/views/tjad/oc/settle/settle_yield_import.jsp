<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="decorator" content="empty"/>
    <title>可结算产值管理-导入</title>
</head>
<body>
<div class="">
	<center>
		<h3></h3>
	</center>
	<div class=" ">
		<div class="form">
			<!-- BEGIN FORM-->
			<form id="ffImport">
			<div class="form-body clearfix">
				<div class="form-group col-xs-12 ">
					<label class="control-label col-xs-2">项目编号</label>
					<div class="col-xs-3">
						<select class="form-control">
							<c:if test="${!empty periodSelect}">
							<c:forEach items="${periodSelect}" var="period" varStatus="s">
								<option value="${period.id}">${period.periodName}</option>
							</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="col-xs-4">
						<span class="btn btn-success fileinput-button " style="top: -1px;">
							<span id="fileSpan">选择文件</span>
							<input id="input_file" type="file" name="file_execl" class="" fileTypes="xls|xlsx">
					  	</span> 
					</div>
					<div class="col-xs-1">
						<input class="form-control" type="button" value="导入">
					</div>
				</div>
                
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" onclick="window.close()">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="SaveImport()">保存</button>
	            </div>
	            
			</div>
			</form>
			<!-- END FORM-->
			
            
		</div>
	</div>
</div>
            
<div class="clearfix"></div>

<script type="text/javascript">
$(function(){

});


</script>
</body>
</html>