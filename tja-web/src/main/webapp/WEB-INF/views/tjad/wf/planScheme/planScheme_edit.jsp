<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>系统配置</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="wf.planScheme.menu.path" scope="request"  />
</head>
<body>
 <div class="">
	<center>
		<h3>年度产值结算-2017</h3>
	</center>
	<div class="  ">
		<div class="form">
            <div class="row">
                <div class="col-lg-6">
                                                 流水号
                </div>
                <div class="col-lg-6 text-right">
                    <button type="button" class="btn green">保存</button>
                    <button type="button" class="btn default">提交</button>
                </div>
            </div>
			<!-- BEGIN FORM-->
			<form action="#" class="row">
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目级别</label>
						<div class="col-md-8">
						   <select class="form-control">
								<option value="">1</option>
								<option value="">2</option>
								<option value="">3</option>
								<option value="">4</option>
								<option value="">5</option>
								<option value="">6</option>
								<option value="">7</option>
							</select>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" placeholder="0.00">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" placeholder="0.00">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计启动时间</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" placeholder="0.00">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">设计完成时间</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" placeholder="0.00">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案产值(¥)</label>
						<div class="col-md-8">
							<input type="text" class="form-control" placeholder="xxx">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">承接部门</label>
						<div class="col-md-8">
							<input type="hidden" id="orgId" name="orgId" value="">
			            	<input type="text" id="orgName" class="form-control" value="" data-rule-required="true" readonly="readonly">
			            	<a id="secOrg" title="选择" href="javascript:void(0);" class="icon-select"></a>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">概况</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" placeholder="0.00">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">备注</label>
						<div class="col-md-8">
							<input type="text" class="form-control text-right" placeholder="0.00">
						</div>
					</div>

					<h3 class="form-tit col-lg-12">设计团队</h3>
					<div class="col-lg-5 ">
						<div class="row clearfix">
							<!-- <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
								姓名
							</div>
							<div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
								比例：
								<input type="text" class="ta_input">
							</div> -->
							<div class="col-lg-4 text-right col-md-4 col-sm-4 col-xs-4">
								<button class="btn green btn_tj"> 
								         添加
                                 <i class="fa fa-plus"></i>
                                </button>
							</div>
						</div>
						<table class="table table-bordered">
							<thead>
								<tr class="row">
									<th  class="text-center col-lg-4">姓名</th>
									<th class="text-center">比例(%)※</th>
									<th class="text-center">产值</th>
								</tr>
							</thead>
							<tbody>
								<tr class="row">
									<td  class="text-center col-lg-4">张三</td>
									<td  class=" col-lg-4"><input type="text" placeholder="123" class="text-right"></td>
									<td  class=" col-lg-4 text-right">560001</td>
								</tr>
								<tr class="row">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right">00000</td>
									<td  class=" col-lg-4 text-right">411027</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-lg-1">
						
					</div>
				</div>
			</form>
			<!-- END FORM-->
			<div>
				<tr class="row none" id="">
					<td  class="text-center col-lg-4"></td>
					<td  class=" col-lg-4"><input type="text" placeholder="" class="text-right"></td>
					<td  class=" col-lg-4 text-right"></td>
				</tr>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend-plugs.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-user-extend-plugs.js"></script>
<script type="text/javascript">
	jQuery("#secOrg").on("click", function(){
		jQuery("#orgId").val("");
		jQuery("#orgName").val("");
		jQuery("#orgName").ecolorg({"targetId":"orgId"});
	})
</script>

</body>
</html>