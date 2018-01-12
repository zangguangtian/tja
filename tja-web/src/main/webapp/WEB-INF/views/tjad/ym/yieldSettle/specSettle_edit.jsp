<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>年度产值结算-特批</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="ym.settle.menu.path" scope="request"  />
    <link href="${site }/resources/css/management.css?v=${buildVersion}" rel="Stylesheet" type="text/css">
</head>
<body>
 <div class="">
	<center>
		<h3>年度产值结算-${periodManage.periodName}</h3>
	</center>
	<div class="">
		<div class="form">
            <div class="row">
                <div class="col-lg-3" style="text-align:center;margin-top:17px;padding-right:30px;">
                                                     流水号:${yieldSettle.seqNo}
                </div>
                <div class="col-lg-9 text-right">
	                <c:if test="${empty yieldSettle.procId }">
	                    <input type="button" value="保存" class="btn blue save" onclick="save(0)">
	                    <input type="button" value="提交" class="btn blue submit" onclick="save(1)">
	                </c:if>
	                <c:if test="${not empty yieldSettle.procId }">
						<input class="btn blue save" type="button" value="重新提交" onclick="save(1)"/>
		           	</c:if>
                    <c:if test="${yieldSettle.canDel }">
                        <input class="btn blue save" type="button" value="删除" onclick="save(9)"/>
                    </c:if> 
                </div>
            </div>
			<!-- BEGIN FORM-->
			<form id="saveForm" action="#" class="row">
			    <input type="hidden" name="wfYieldSettle.id" value="${yieldSettle.id}">
			    <input type="hidden" name="wfYieldSettle.auditStatus" value=""/>
			    <input type="hidden" name="wfYieldSettle.procId" value=""/>
				<input type="hidden" name="wfYieldSettle.proId" value="${project.id}">
				<input type="hidden" name="wfYieldSettle.periodId" value="${periodManage.id}">
				<input type="hidden" name="wfYieldSettle.wfCategory" value="2000">
				<input type="hidden" name="wfYieldSettle.permitId" value="${permitId}">
				<div class="form-body clearfix">
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目编号</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proCode" class="form-control col-md-3" disabled value="${project.proCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目名称</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proName" class="form-control" disabled value="${project.proName}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同编号</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proType" class="form-control" disabled value="${project.contractCode}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目类型</label>
						<div class="col-md-8">
							<input type="text" name="wfYieldSettle.proType" class="form-control" disabled value="${project.proType}">
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">合同额(¥)</label>
						<div class="col-md-8">
								<input type="text" class="form-control" disabled
									name="wfYieldSettle.contractAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.contractAmount?project.contractAmount:yieldSettle.contractAmount}' pattern='#,#00.00#'/>">
							</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">分包扣减(¥)</label>
						<div class="col-md-8">
								<input type="text" class="form-control" disabled
									name="wfYieldSettle.pkgAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.pkgAmount?project.pkgAmount:yieldSettle.pkgAmount}' pattern='#,#00.00#'/>">
							</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">方案扣减(¥)</label>
						<div class="col-md-8">
								<input type="text" class="form-control" disabled
									name="wfYieldSettle.schemeAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.schemeAmount?project.schemeAmount:yieldSettle.schemeAmount}' pattern='#,#00.00#'/>">
							</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">其他扣减(¥)</label>
						<div class="col-md-8">
								<input type="text" class="form-control" disabled
									name="wfYieldSettle.rebateAmount"
									value="<fmt:formatNumber value='${empty yieldSettle.rebateAmount?project.rebateAmount:yieldSettle.rebateAmount}' pattern='#,#00.00#'/>">
							</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目负责人</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
							<input type="text" class="form-control" disabled name="wfYieldSettle.pManagers" value="${project.pmLeaders}">
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">项目经理</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
			            	<input type="text" name="wfYieldSettle.pManagers" class="form-control col-md-3" value="${project.pManagers}" disabled>
						</div>
					</div>
					
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">当年特批产值(¥)</label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i> <input type="text" class="form-control"
									name="wfYieldSettle.yearYield"
									value="<fmt:formatNumber value='${empty yieldSettle.yearYield ? project.yield : yieldSettle.yearYield}' pattern='#,#00.00#'/>"
									disabled>
							</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3"></label>
						<div class="col-md-8 input-icon right">
						    <i class="fa"></i>
						</div>
					</div>
					
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">创建人</label>
						<div class="col-md-8 input-icon right">
						<input type="hidden" name="wfYieldSettle.creator" value="${empty yieldSettle.creator  ? SysUser.id : yieldSettle.creator}"/>
						<label class="control-label">${empty yieldSettle.creatorName ? SysUser.realName : yieldSettle.creatorName}</label>
						</div>
					</div>
					<div class="form-group col-lg-6 ">
						<label class="control-label col-md-3">创建时间</label>
						<div class="col-md-8 input-icon right">
						 <jsp:useBean id="currentDate" class="java.util.Date"/>
	            		 <fmt:formatDate value="${empty yieldSettle.createDate? currentDate : yieldSettle.createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="currentDate"/>
						 <label class="control-label">${currentDate }</label>
						</div>
					</div>
					

					<h3 class="form-tit col-lg-12">${majorName}</h3>
					<div class="col-lg-5 form-group">
						<div class="clearfix">
							<div class="col-lg-12 text-right col-md-4 col-sm-4 col-xs-4">
								<input type="button" class="btn blue btn_tj" value="添加"/> 
							</div>
						</div>
						<table class="table table-bordered edit" id="majorRole">
							<thead>
								<tr>
									<th  class="text-center col-lg-4">姓名</th>
									<th class="text-center form-group">工作量(%)<span class="required">※</span></th>
									<th class="text-center">产值</th>
								</tr>
							</thead>
							<tbody>
							   
							    <c:if test="${not empty majorRoleAllots}">
							      <c:forEach var="majorRoleAllot" items="${majorRoleAllots}" varStatus="st">
							        <tr>
										<td  class="text-center col-lg-4">${majorRoleAllot.staffName }</td>
										<td  class=" col-lg-4 input-icon left">
										<i class="fa"></i>
										<input type="hidden" name="majorRoleAllots[${st.index}].id" class="text-right" value="${majorRoleAllot.id}">
										<input type="hidden" name="majorRoleAllots[${st.index}].staffId" class="text-right" value="${majorRoleAllot.staffId}">
										<input type="hidden" name="majorRoleAllots[${st.index}].category" class="text-right" value="2000">
										<input type="hidden" name="majorRoleAllots[${st.index}].majorCode" class="text-right" value="${majorRoleAllot.majorCode}">
										<input type="hidden" name="majorRoleAllots[${st.index}].staffSort" class="text-right" value="${majorRoleAllot.staffSort}">
										<input type="hidden" name="majorRoleAllots[${st.index}].staffYield" value="${majorRoleAllot.staffYield}">
										<input type="text"
											        name="majorRoleAllots[${st.index}].staffRate"
													data-rule-number="true"
												    placeholder="0.00"
												    data-rule-max="100"
						                            data-rule-min="0" 
						                            data-rule-required="true"
													class="text-right twoNum" value="${majorRoleAllot.staffRate}">
												</td>
										<td  class=" col-lg-4 text-right"><fmt:formatNumber value='${majorRoleAllot.staffYield}' pattern='#,#00.00#'/></td>
									</tr>
							      </c:forEach>
							    </c:if>
							
							    <c:if test="${not empty staffs and empty majorRoleAllots}">
							      <c:forEach var="staff" items="${staffs}" varStatus="st">
							        <tr>
										<td  class="text-center col-lg-4">${staff.name }</td>
										<td  class=" col-lg-4  input-icon left">
										<i class="fa"></i>
										<input type="hidden" name="majorRoleAllots[${st.index}].id" class="text-right" value="">
										<input type="hidden" name="majorRoleAllots[${st.index}].staffId" class="text-right" value="${staff.id}">
										<input type="hidden" name="majorRoleAllots[${st.index}].category" class="text-right" value="2000">
										<input type="hidden" name="majorRoleAllots[${st.index}].majorCode" class="text-right" value="${majorCode}">
										<input type="hidden" name="majorRoleAllots[${st.index}].staffSort" class="text-right" value="${st.index}">
										<input type="hidden" name="majorRoleAllots[${st.index}].staffYield" value="">
										<input type="text"
													name="majorRoleAllots[${st.index}].staffRate"
													data-rule-number="true" 
													placeholder="0.00"
													data-rule-max="100"
						                            data-rule-min="0" 
						                            data-rule-required="true"
													class="text-right twoNum" 
													value="">
												</td>
										<td  class=" col-lg-4 text-right"></td>
									</tr>
							      </c:forEach>
							    </c:if>
							
								<tr class="total">
									<td  class="text-center col-lg-4">合计</td>
									<td  class="col-lg-4 text-right"></td>
									<td  class=" col-lg-4 text-right"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</form>
			<!-- END FORM-->
			<table id="clone_text" style="display: none">
				 <tr>
					<td  class="text-center col-lg-4"></td>
					<td  class=" col-lg-4  input-icon left">
					<i class="fa"></i>
					<input type="hidden" name="majorRoleAllots[{0}].id" class="text-right" value="">
					<input type="hidden" name="majorRoleAllots[{0}].staffId" class="text-right" value="">
					<input type="hidden" name="majorRoleAllots[{0}].category" class="text-right" value="2000">
					<input type="hidden" name="majorRoleAllots[{0}].majorCode" class="text-right" value="${majorCode}">
					<input type="hidden" name="majorRoleAllots[{0}].staffSort" class="text-right" value="">
					<input type="hidden" name="majorRoleAllots[{0}].staffYield" value="">
					<input type="text" name="majorRoleAllots[{0}].staffRate"
							data-rule-number="true" 
							placeholder="0.00" 
							data-rule-max="100"
						    data-rule-min="0" 
						    data-rule-required="true"
						    class="text-right twoNum" 
							value="">
						</td>
					<td  class=" col-lg-4 text-right"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${buildVersion}"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
	$(function(){
		
		jQuery(document).on("keyup","#majorRole tbody input[name$='staffRate']",function(){
			  var _this = $(this);
			  var staffRate = _this.val();
			  var yearYield =delcommafy($("input[name$='yearYield']").val());
			  var _td = _this.closest("tr").find("td:last");
			  var staffYield = new Number(yearYield) * new Number(staffRate)/100;
			  var yield = new Number(staffYield).toFixed(2)
			  _td.text(toThousands(yield));
			  _this.closest("tr").find("input[name$='.staffYield']").val(yield);
			  var flag = initTotal();
		});
		
		jQuery(".btn_tj").on("click",function(){
			selectStaff(selectStaffBack,'checkbox');
		});
		initTotal();
	});

	//合计
	function initTotal(){
		var flag = true;
		var totalstaffRate = 0.00;
		var totalstaffYield = 0.00;
		jQuery.each($("#majorRole tbody tr:not(:last)"),function(index,item){
			var _this = $(item);
			var staffRate = getNumValue(_this.find("input[name$='staffRate']").val());
			var staffYield =getNumValue(_this.find("input[name$='staffYield']").val());
			totalstaffRate = new Number(totalstaffRate) + new Number(staffRate);
			totalstaffYield = new Number(totalstaffYield) + new Number(staffYield);
		});
		if(new Number(totalstaffRate) !=100){
			flag = false;
		}
		$("#majorRole tr.total").find("td:eq(1)").text(new Number(totalstaffRate).toFixed(2));
		$("#majorRole tr.total").find("td:eq(2)").text(toThousands(new Number(totalstaffYield).toFixed(2)));
		return flag;
	}
	
	function selectStaffBack(data){
		jQuery.each(data,function(index,item){
			var info = $(item)[0];
			var trs = jQuery("#majorRole").find("tbody tr:not(:last)");
			var trSize = jQuery("#majorRole").find("tbody tr:not(:last)").size();
			if(trSize == 0){
				var $item = jQuery("#clone_text").clone();
				$item.html($item.html().format(trSize));
				$item.find("tr input[name$='.staffId']").val(info.id);
				$item.find("tr input[name$='.staffSort']").val(trSize);
				$item.find("tr td:first").text(info.name);
				jQuery(".total").before($item.find("tr"));
				trSize ++;
			}else{
				var _size = jQuery("#majorRole").find("tbody tr input[value='"+info.id+"']").size();
				if(_size <= 0){
					//可以添加
					var $item = jQuery("#clone_text").clone();
					$item.html($item.html().format(trSize));
					$item.find("tr input[name$='.staffId']").val(info.id);
					$item.find("tr input[name$='.staffSort']").val(trSize);
					$item.find("tr td:first").text(info.name);
					jQuery(".total").before($item.find("tr"));
					trSize ++;
				}
			}
		});
	}
	
	
	function save(status) {
		var flag = false;
	    if(status == "9"){
	        $.jalert({"jatext":"确认删除？<br>点[确定]：执行删除操作<br>点[取消]：放弃删除操作", "jatype":"confirm", "onConfirm":function(){
	        	ajaxSave(true,status);
	        }});
	        
	    }else{
	    	if (jQuery("#saveForm").valid()) {
	            flag = true;
	        }
	    	if(flag){
	    		if(!initTotal()){
	    			$.jalert({"jatext":"比例合计必须为100"});
	                flag = false;
	    		}
	    	}
	    	ajaxSave(flag,status);
	    }
	}
	
	function ajaxSave(flag,status){
		var url ="${site}/admin/ym/yieldSettle/ajax/save";
	    if (flag) {
	    	//把disabled属性去掉
	    	jQuery("input:disabled").removeAttr("disabled");
	    	jQuery.each(jQuery("input[name$='Amount'],input[name$='yearYield']"),function(index,item){
	    		var _item = $(item);
	    		_item.val(delcommafy(_item.val()));
	    	});
	    	$("input[name='wfYieldSettle.auditStatus']").val(status);
		        jQuery.ajax({
		        type : "POST",
		        url : url,
		        data : jQuery('#saveForm').serialize(),
		        async : false,
		        success : function(data) {
		        	if(data.flag == 'true'){
		        	$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
				  		window.location.href="${site}/admin/ym/yieldSettle/list";
		 			}});
		        	}else{
		        		$.jalert({"jatext":data.msg});
		        	}
		        }
	        });
	    }
	}
	
	//千分位处理 去掉千分位
	function delcommafy(num){  
	   num = num.replace(/[ ]/g, "");//去除空格  
	   num=num.replace(/,/gi,'');  
	   return num;  
	}
	
	//添加千分位
	function toThousands(num) {
	    return (num || 0).toString().replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
	
	function getNumValue(num) {
	       if (validateInput(num)) {
	           num = parseFloat(num);
	       }
	       else {
	           num = 0;
	       }
	       return num;
	   }

    function validateInput(inputstr) {
	      flag = false;
	      if (inputstr != "") {
	          if (isNaN(inputstr)) {
	              flag = false; //如果输入字符不是数字
	          }
	          else {//输入数字但是小于0
	              if (parseFloat(inputstr) < 0)
	                  flag = false;
	              else
	                  flag = true;
	          }
	      }
	      return flag;
	  }
</script>

</body>
</html>