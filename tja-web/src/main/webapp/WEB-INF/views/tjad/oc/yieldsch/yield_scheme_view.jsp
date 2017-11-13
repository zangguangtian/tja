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
    	<input type="button" id="print-btn" value="打印" class="btn blue" style="float:right;position:absolute;right:55px;">
        <h3>施工图产值策划</h3>
    </center>
    <div class="print">
        <div class="form">
            <!-- BEGIN FORM-->
            <form id="schemeForm" method="post">
                <input type="hidden" id="ratioParam" value="${ratioParam }">
                <input type="hidden" name="id" value="${yieldScheme.id }">
                <div class="form-body clearfix" style="padding-bottom: 0">
                	<div class="col-xs-6 ">
						<label class="control-label col-xs-4">策划编号</label>
						<div class="col-xs-8">
							<label class="control-label">${yieldScheme.schemeNo }</label>
						</div>
					</div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">更新日期</label>
                        <div class="col-xs-8">
                            <label class="control-label"><fmt:formatDate value='${yieldScheme.lastUpdate }' pattern='yyyy-MM-dd'/></label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目编号</label>
                        <div class="col-xs-8">
                            <label class="control-label">${project.proCode }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目名称</label>
                        <div class="col-xs-8">
                            <label class="control-label">${project.proName }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目类型</label>
                        <div class="col-xs-8">
                            <label class="control-label">${project.proType }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目级别</label>
                        <div class="col-xs-8">
                            <tags:config type="label" cssClass="control-label" code="${project.proGrade}"/>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目负责人</label>
                        <div class="col-xs-8">
                            <label class="control-label">${project.pmLeaders }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目经理</label>
                        <div class="col-xs-8">
                            <label class="control-label">${project.pManagers }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">用地面积（M<sup>2</sup>）</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.landArea }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">策划依据</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.schemeBasis }</label>
                        </div>
                    </div>
                    <div class="col-xs-12">
						<h5 class="form-tit">专业比例<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span></h5>
					</div>
					<div>
					    <table id="majorRatio" class="table table-striped table-bordered table-advance table-hover dataTable">
					        <thead>
					            <tr>
					                <th style="text-align:center;">类型名称(系数)</th>
					                <th style="text-align:center;">类型编号</th>
					                <th style="text-align:center;">建筑面积(m<sup>2</sup>)</th>
					                <th style="text-align:center;">土建基准<br>单价(元/m<sup>2</sup>)</br></th>
					                <th style="text-align:center;">土建基准产值</th>
					                <th style="text-align:center;">各专业产值<br>(90%)</br></th>
					                <c:if test="${not empty majors }">
					                   <c:forEach items="${majors }" var="major" >
					                       <th style="text-align:center;">${major.configName }</th>
					                   </c:forEach>
					                </c:if>
					            </tr>
					        </thead>
                            <tbody>
                            	<c:if test="${not empty yieldMajors }">
                            		<c:forEach items="${yieldMajors }" var="yieldMajor" varStatus="vs">
                            			<tr>
										    <td rowspan="2">${yieldMajor.name }</td>
										    <td rowspan="2">${yieldMajor.typeCode }</td>
										    <td rowspan="2" class="buildArea">${yieldMajor.buildArea }</td>
										    <td rowspan="2">${yieldMajor.standardPrice }</td>
										    <td rowspan="2" class="standardYield">${yieldMajor.standardYield }</td>
										    <td rowspan="2" class="majorTotalYield">${yieldMajor.majorYield }</td>
										    <c:if test="${not empty majors }">
										       <c:forEach items="${majors }" var="major" >
											       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
										           <td id="majorRatio${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorRate }</td>
										       </c:forEach>
										    </c:if>
										</tr>
										<tr>
											<c:if test="${not empty majors }">
										       <c:forEach items="${majors }" var="major" >
											       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
										           <td id="majorYield${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorYield }</td>
										       </c:forEach>
										    </c:if>
										</tr>
                            		</c:forEach>
                            	</c:if>
                            	
					            <tr class="total">
                                    <td nowrap="nowrap" colspan="2" style="text-align:center;">院内合计</td>
                                    <td nowrap="nowrap" id="totalArea"></td>
                                    <td nowrap="nowrap" id="totalUnitPrice"></td>
                                    <td nowrap="nowrap" id="totalSYield"></td>
                                    <td nowrap="nowrap" id="totalMYield"></td>
                                    <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <td nowrap="nowrap" data-major="${major.configCode}"></td>
                                       </c:forEach>
                                    </c:if>
					            </tr>
					        </tbody>
					    </table>
					</div>
					
					<div class="col-xs-12">
						<h5 class="form-tit">土建产值<span class="control-label" style="font-size:12px;">（元）</span></h5>
					</div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">实际合同额</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.contractAmount }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">分包扣减</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.pkgAmount }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">方案扣减</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.schemeAmount }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">其他扣减</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.rebateAmount }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">土建总产值</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.totalAmount }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">各专业产值</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.majorAmount }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目负责人（%）</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.principalRate }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目负责人（产值）</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.principalYield }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目经理（%）</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.pmRate }</label>
                        </div>
                    </div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">项目经理（产值）</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.pmYield }</label>
                        </div>
                    </div>
                    <c:if test="${not empty majors }">
						<c:forEach items="${majors }" var="major" varStatus="vs">
		                    <div class="col-xs-6 ">
		                        <label class="control-label col-xs-4">${major.configName }产值</label>
		                        <div class="col-xs-8">
		                            <input type="hidden" name="yieldMajorDuties[${vs.index }].majorCode" value="${major.configCode }">
		                            <label class="control-label">${yieldDuties[major.configCode].majorYield }</label>
		                        </div>
		                    </div>
	                    </c:forEach>
	                </c:if>
                    
                    <div class="col-xs-12">
						<h5 class="form-tit">各专业产值<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span></h5>
					</div>
					<div>
					    <table id="majorYield" class="table table-striped table-bordered table-advance table-hover dataTable">
					        <thead>
					            <tr>
					                <th nowrap="nowrap" style="text-align:center;">阶段</th>
					                <c:if test="${not empty majors }">
					                   <c:forEach items="${majors }" var="major" >
					                       <th nowrap="nowrap" style="text-align:center;" data-smcode="${major.configCode }">${major.configName }</th>
					                   </c:forEach>
					                </c:if>
					                <th nowrap="nowrap" style="text-align:center;">产值合计</th>
					            </tr>
					        </thead>
					        <tbody>
					        	<c:forEach items="${schemeStages }" var="schemeStage" varStatus="ssVs">
					        		<tr>
					        			<td rowspan="2" style="text-align:center;">${schemeStage[1] }</td>
					        			<c:if test="${not empty majors }">
					        				<c:forEach items="${majors }" var="major" varStatus="vs" >
					        					<c:set var="ssKey" value="1000.${major.configCode }" />
					        					<td style="text-align:right;">
	                                               <c:choose>
	                                                   <c:when test="${schemeStage[0] == 'preliminary' }">${stageMajors[ssKey].preliminary }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'drawing' }">${stageMajors[ssKey].drawing }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'subTotal' }">${stageMajors[ssKey].subTotal }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'coordination' }">${stageMajors[ssKey].coordination }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'cap' }">${stageMajors[ssKey].cap }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'check' }">${stageMajors[ssKey].check }</c:when>
	                                               </c:choose>
	                                           </td>
					        				</c:forEach>
					        			</c:if>
					        			<td rowspan="2"></td>
					        		</tr>
					        		<tr>
					        			<c:if test="${not empty majors }">
					        				<c:forEach items="${majors }" var="major" varStatus="vs" >
					        					<c:set var="ssKey" value="2000.${major.configCode }" />
	                                            <td style="text-align:right;">
	                                               <c:choose>
	                                                   <c:when test="${schemeStage[0] == 'preliminary' }">${stageMajors[ssKey].preliminary }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'drawing' }">${stageMajors[ssKey].drawing }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'subTotal' }">${stageMajors[ssKey].subTotal }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'coordination' }">${stageMajors[ssKey].coordination }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'cap' }">${stageMajors[ssKey].cap }</c:when>
	                                                   <c:when test="${schemeStage[0] == 'check' }">${stageMajors[ssKey].check }</c:when>
	                                               </c:choose>
	                                           </td>
					        				</c:forEach>
					        			</c:if>
					        		</tr>
					        	</c:forEach>
					        </tbody>
					    </table>
					</div>

					<div class="col-xs-12">
						<h5 class="form-tit">各专业部门负责人会签</h5>
					</div>
                    <div class="col-xs-6 ">
                        <label class="control-label col-xs-4">设计负责人</label>
                        <div class="col-xs-8">
                            <label class="control-label">${yieldScheme.principalName }</label>
                        </div>
                    </div>
                    <c:if test="${not empty majors }">
						<c:forEach items="${majors }" var="major" varStatus="vs">
							<div class="col-xs-6 ">
								<label class="control-label col-xs-4">${major.configName }</label>
		                        <div class="col-xs-8">
		                            <label class="control-label">${yieldDuties[major.configCode].principalName }</label>
								</div>
		                    </div>
	                    </c:forEach>
	                </c:if>
	                <div class="col-xs-12 ">
						<label class="control-label col-xs-2">备注</label>
						<div class="col-xs-8">
							<label class="control-label">${yieldScheme.remark }</label>
						</div>
					</div>
				</div>
				<br><br><br>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
/*打印*/
$("#print-btn").dfprint({
    action : "print"
});

$(function(){
    /**计算院内合计*/
    calYLTotal();
    
    /**计算各专业产值列表中各阶段的产值合计*/
    calMYTotal();
});

/**计算专业比例列表中的院内合计*/
function calYLTotal(){
	var thisVal = null;
	//计算院内建筑面积
	var totalArea = new Number(0);
	$("#majorRatio td.buildArea").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalArea += new Number(thisVal);
	});
	$("#totalArea").text(totalArea.toFixed(2));
	
	//计算院内土建基准产值
	var totalSYield = new Number(0);
	$("#majorRatio td.standardYield").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalSYield += new Number(thisVal);
	});
	$("#totalSYield").text(totalSYield.toFixed(2));
	
	//计算院内各专业产值
	var totalMYield = new Number(0);
	$("#majorRatio td.majorTotalYield").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalMYield += new Number(thisVal);
	});
	$("#totalMYield").text(totalMYield.toFixed(2));
	
	//计算院内土建基准单价
	var totalUPrice = new Number(0);
	if(totalArea > 0){
		totalUPrice = totalSYield / totalArea;
	}
	$("#totalUnitPrice").text(totalUPrice.toFixed(2));
	
	//计算每个专业的院内合计
	//所有专业的总产值
	var totalYield = new Number(0);
	$("#majorRatio td[id^='majorYield']").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalYield += new Number(thisVal);
	});
	
	//每个专业的总产值
	var totalMajorYield = null;
	//用于处理尾差
	var tempTotalYield = new Number(0);
	//针对每个专业，计算院内合计
	$("#majorRatio tr.total td[data-major]").each(function(){
		totalMajorYield = new Number(0);
		//所有专业的产值和大于0
		if(totalYield > 0){
			//计算各专业的产值和
			$("#majorRatio td[id^='majorYield'][data-major='"+ $(this).data("major") +"']").each(function(){
				thisVal = $(this).text();
				if(thisVal == null){
					thisVal = "0";
				}
				totalMajorYield += new Number(thisVal);
			});
			totalMajorYield = totalMajorYield * new Number(100);
			totalMajorYield = totalMajorYield / totalYield;
			
			//处理尾差
			if((tempTotalYield + new Number(totalMajorYield.toFixed(2))) < new Number(100)){
				tempTotalYield += new Number(totalMajorYield.toFixed(2));
			}else{
				totalMajorYield = new Number(100) - tempTotalYield;
			}
		}
		$(this).text(totalMajorYield.toFixed(2));
	});
}

/**
 * 计算各专业产值列表中各阶段的产值合计
 */
function calMYTotal(){
	var thisVal = null;
	$("#majorYield tbody tr:odd").each(function(index, item){
		var tempTotalYield = new Number(0);
		$(item).find("td").each(function(){
			thisVal = $(this).text();
			if(thisVal == null){
				thisVal = "0";
			}
			tempTotalYield += new Number(thisVal);
		});
		$(item).prev("tr").find("td:last").text(tempTotalYield.toFixed(2));
	});
}
</script>
</body>
</html>