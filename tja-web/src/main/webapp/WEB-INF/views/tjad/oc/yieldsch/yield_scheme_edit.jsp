<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>产值策划</title>
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
        <h3>产值策划</h3>
    </center>
    <div class="  ">
        <div class="form">
            <!-- BEGIN FORM-->
            <form id="schemeForm" method="post" class=" ">
                <input type="hidden" id="ratioParam" value="${ratioParam }">
                <input type="hidden" name="id" value="${yieldScheme.id }">
                <df:token/>
                <div class="form-body clearfix">
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">策划编号</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeNo" value="${yieldScheme.schemeNo }" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">更新日期<span class="required">※</span></label>
                        <div class="col-md-8">
                            <input type="text" name="lastUpdate" value="<fmt:formatDate value='${yieldScheme.lastUpdate }' pattern='yyyy-MM-dd'/>" data-rule-required="true" class="form-control datetimepicker">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目编号</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.proCode }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目名称</label>
                        <div class="col-md-8">
                        	<input type="hidden" name="proId" value="${project.id }">
                            <input type="text" value="${project.proName }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目类型</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.proType }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目级别</label>
                        <div class="col-md-8">
                            <tags:config type="label" cssClass="form-control" code="${project.proGrade}"/>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.pmLeaders }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理</label>
                        <div class="col-md-8">
                            <input type="text" value="${project.pManagers }" class="form-control" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">用地面积（M<sup>2</sup>）</label>
                        <div class="col-md-8">
                            <input type="text" name="landArea" value="${yieldScheme.landArea }" class="form-control text-right" placeholder="0.00" data-rule-number="true">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">策划依据</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeBasis" value="${yieldScheme.schemeBasis }" class="form-control">
                        </div>
                    </div>
                    <h3 class="form-tit col-lg-12">专业比例<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span>
                        <input type="button" id="addMajor-btn" class="btn blue " value="添加" style="float: right;"></h3>
					<div class="table-scrollable">
					    <table id="majorRatio" class="table table-striped table-bordered table-advance table-hover dataTable">
					        <thead>
					            <tr>
					                <th rowspan="2" style="ext-align:center;">序号</th>
					                <th rowspan="2" style="text-align:center;" class="form-group">类型名称(系数)<span class="required">※</span></th>
					                <th rowspan="2" style="text-align:center;" class="form-group">类型编号<span class="required">※</span></th>
					                <th rowspan="2" style="text-align:center;" class="form-group">建筑面积(m<sup>2</sup>)<span class="required">※</span></th>
					                <th rowspan="2" style="text-align:center;">土建基准<br>单价(元/m<sup>2</sup>)</br></th>
					                <th rowspan="2" style="text-align:center;">土建基准产值</th>
					                <th rowspan="2" style="text-align:center;">各专业产值<br>(90%)</br></th>
					                <c:if test="${not empty majors }">
					                   <c:forEach items="${majors }" var="major" >
					                       <th nowrap="nowrap" colspan="2" style="text-align:center;">${major.configName }</th>
					                   </c:forEach>
					                </c:if>
					                <th rowspan="2" style="text-align:center;">操作</th>
					            </tr>
					            <tr>
					                <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <th nowrap="nowrap" style="text-align:center;width:80px;">比例</th>
                                           <th nowrap="nowrap" style="text-align:center;width:80px;">产值</th>
                                       </c:forEach>
                                    </c:if>
					            </tr>
					        </thead>
                            <tbody>
                            	<c:if test="${not empty yieldMajors }">
                            		<c:forEach items="${yieldMajors }" var="yieldMajor" varStatus="vs">
                            			<tr>
										    <td nowrap="nowrap" width="40px" style="text-align:center;">${vs.index + 1}</td>
										    <td nowrap="nowrap" class="form-group">
										    	<input type="hidden" name="yieldMajors[${vs.index}].id" value="${yieldMajor.id }">
										    	<input type="text" name="yieldMajors[${vs.index}].name" value="${yieldMajor.name }" class="form-control" data-rule-required="true">
										    </td>
										    <td nowrap="nowrap" class="form-group" style="width:100px;">
										       <select name="yieldMajors[${vs.index}].priceId" class="form-control" data-rule-required="true">
										           <option value="">-请选择-</option>
										       <c:if test="${not empty prices }">
										           <c:forEach items="${prices }" var="price">
										           	   <c:if test="${price.priceId == yieldMajor.priceId }">
											               <option value="${price.priceId }" selected data-uprice="${price.standardPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
										           	   </c:if>
										           	   <c:if test="${price.priceId != yieldMajor.priceId }">
											               <option value="${price.priceId }" data-uprice="${price.standardPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
										           	   </c:if>
										           </c:forEach>
										       </c:if>
										       </select>
										    </td>
										    <td nowrap="nowrap" class="form-group"><input type="text" name="yieldMajors[${vs.index}].buildArea" value="${yieldMajor.buildArea }" data-rule-number="true" data-rule-required="true" class="form-control"></td>
										    <td nowrap="nowrap" id="price${vs.index}" style="text-align:right;">${yieldMajor.standardPrice }</td>
										    <td nowrap="nowrap" id="sYield${vs.index}" style="text-align:right;">${yieldMajor.standardYield }</td>
										    <td nowrap="nowrap" id="mYield${vs.index}" style="text-align:right;">${yieldMajor.majorYield }</td>
										    <c:if test="${not empty majors }">
										       <c:forEach items="${majors }" var="major" >
											       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
										           <td nowrap="nowrap" id="majorRatio${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorRate }</td>
										           <td nowrap="nowrap" id="majorYield${vs.index}.${major.configCode }" data-major="${major.configCode }">${yieldMajor.majorMap[majorKey].majorYield }</td>
										       </c:forEach>
										    </c:if>
										    <td style="text-align:center;"><i class="fa fa-trash-o del-btn"></i></td>
										</tr>
                            		</c:forEach>
                            	</c:if>
                            	
					            <tr class="total">
                                    <td nowrap="nowrap" colspan="3" style="text-align:center;">院内合计</td>
                                    <td nowrap="nowrap" id="totalArea"></td>
                                    <td nowrap="nowrap" id="totalUnitPrice"></td>
                                    <td nowrap="nowrap" id="totalSYield"></td>
                                    <td nowrap="nowrap" id="totalMYield"></td>
                                    <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <td nowrap="nowrap" colspan="2" data-major="${major.configCode}"></td>
                                       </c:forEach>
                                    </c:if>
                                    <td>&nbsp;</td>
					            </tr>
					        </tbody>
					    </table>
					</div>

                    <h3 class="form-tit col-lg-12">土建产值<span class="control-label" style="font-size:12px;">（元）</span></h3>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">实际合同额<span class="required">※</span></label>
                        <div class="col-md-8">
                            <input type="text" name="contractAmount" class="form-control fourAmount" data-rule-required="true" value="${yieldScheme.contractAmount }">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">分包扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="pkgAmount" class="form-control fourAmount" value="${yieldScheme.pkgAmount }">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">方案扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeAmount" class="form-control fourAmount" value="${yieldScheme.schemeAmount }" readonly>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">其他扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="rebateAmount" class="form-control fourAmount" value="${yieldScheme.rebateAmount }">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">土建总产值</label>
                        <div class="col-md-8">
                            <input type="text" name="totalAmount" class="form-control" value="${yieldScheme.totalAmount }" readonly>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">各专业产值</label>
                        <div class="col-md-8">
                            <input type="text" name="majorAmount" class="form-control" value="${yieldScheme.majorAmount }" readonly>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人（%）<span class="required">※</span></label>
                        <div class="col-md-8">
                            <input type="text" name="principalRate" class="form-control twoProUser" value="${yieldScheme.principalRate }" data-rule-required="true">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人（产值）</label>
                        <div class="col-md-8">
                            <input type="text" name="principalYield" class="form-control" value="${yieldScheme.principalYield }" readonly>
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理（%）<span class="required">※</span></label>
                        <div class="col-md-8">
                            <input type="text" name="pmRate" class="form-control twoProUser" value="${yieldScheme.pmRate }" data-rule-required="true">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理（产值）</label>
                        <div class="col-md-8">
                            <input type="text" name="pmYield" class="form-control" value="${yieldScheme.pmYield }" readonly>
                        </div>
                    </div>
                    <c:if test="${not empty majors }">
						<c:forEach items="${majors }" var="major" varStatus="vs">
		                    <div class="form-group col-lg-6 ">
		                        <label class="control-label col-md-3">${major.configName }产值</label>
		                        <div class="col-md-8">
		                            <input type="hidden" name="yieldMajorDuties[${vs.index }].majorCode" value="${major.configCode }">
		                            <input type="text" name="yieldMajorDuties[${vs.index }].majorYield" data-majorcode="${major.configCode }" value="${yieldDuties[major.configCode].majorYield }" class="form-control eachMajorYield" readonly>
		                        </div>
		                    </div>
	                    </c:forEach>
	                </c:if>
                    
                    <h3 class="form-tit col-lg-12">各专业产值<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span></h3>
					<div class="table-scrollable">
					    <table id="majorYield" class="table table-striped table-bordered table-advance table-hover dataTable">
					        <thead>
					            <tr>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">阶段</th>
					                <c:if test="${not empty majors }">
					                   <c:forEach items="${majors }" var="major" >
					                       <th nowrap="nowrap" colspan="2" style="text-align:center;" data-smcode="${major.configCode }">${major.configName }</th>
					                   </c:forEach>
					                </c:if>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">产值合计</th>
					            </tr>
					            <tr>
					                <c:if test="${not empty majors }">
                                       <c:forEach items="${majors }" var="major" >
                                           <th nowrap="nowrap" style="text-align:center;width:80px;">比例</th>
                                           <th nowrap="nowrap" style="text-align:center;width:80px;">产值</th>
                                       </c:forEach>
                                    </c:if>
					            </tr>
					        </thead>
                            <tbody>
                            	<c:forEach items="${schemeStages }" var="schemeStage" varStatus="ssVs">
						            <tr class="${schemeStage[0] }">
	                                    <td nowrap="nowrap" style="text-align:center;">${schemeStage[1] }</td>
	                                    <c:if test="${not empty majors }">
	                                       <c:forEach items="${majors }" var="major" varStatus="vs" >
	                                       	   <c:set var="ssKey" value="1000.${major.configCode }" />
	                                           <td nowrap="nowrap" style="width:80px;">
	                                           	   <c:if test="${ssVs.index == 0 }">
		                                               <input type="hidden" name="yieldStageMajors[${vs.index*2 }].category" value="1000">
		                                               <input type="hidden" name="yieldStageMajors[${vs.index*2 }].majorCode" value="${major.configCode }">
	                                           	   </c:if>
	                                               <c:choose>
	                                                   <c:when test="${schemeStage[0] == 'preliminary' }">
			                                               <input type="text" name="yieldStageMajors[${vs.index*2 }].preliminary" value="${stageMajors[ssKey].preliminary }"  data-smcode="${major.configCode }" class="form-control majorratio">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'drawing' }">
			                                               <input type="text" name="yieldStageMajors[${vs.index*2 }].drawing" value="${stageMajors[ssKey].drawing }"  data-smcode="${major.configCode }" class="form-control majorratio">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'subTotal' }">
			                                               <input type="text" name="yieldStageMajors[${vs.index*2 }].subTotal" value="${stageMajors[ssKey].subTotal }"  data-smcode="${major.configCode }" readonly class="form-control majorratio">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'coordination' }">
			                                               <input type="text" name="yieldStageMajors[${vs.index*2 }].coordination" value="${stageMajors[ssKey].coordination }"  data-smcode="${major.configCode }" class="form-control majorratio">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'cap' }">
			                                               <input type="text" name="yieldStageMajors[${vs.index*2 }].cap" value="${stageMajors[ssKey].cap }"  data-smcode="${major.configCode }" class="form-control majorratio">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'check' }">
			                                               <input type="text" name="yieldStageMajors[${vs.index*2 }].check" value="${stageMajors[ssKey].check }"  data-smcode="${major.configCode }" class="form-control majorratio">
	                                                   </c:when>
	                                               </c:choose>
	                                           </td>
	                                           <c:set var="ssKey" value="2000.${major.configCode }" />
	                                           <td nowrap="nowrap" style="text-align:right;width:80px;">
	                                           	   <c:if test="${ssVs.index == 0 }">
		                                               <input type="hidden" name="yieldStageMajors[${vs.index*2+1 }].category" value="2000">
		                                               <input type="hidden" name="yieldStageMajors[${vs.index*2+1 }].majorCode" value="${major.configCode }">
	                                           	   </c:if>
	                                               <c:choose>
	                                                   <c:when test="${schemeStage[0] == 'preliminary' }">
	                                                       <input type="text" name="yieldStageMajors[${vs.index*2+1 }].preliminary" value="${stageMajors[ssKey].preliminary }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'drawing' }">
	                                                       <input type="text" name="yieldStageMajors[${vs.index*2+1 }].drawing" value="${stageMajors[ssKey].drawing }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'subTotal' }">
	                                                       <input type="text" name="yieldStageMajors[${vs.index*2+1 }].subTotal" value="${stageMajors[ssKey].subTotal }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'coordination' }">
	                                                       <input type="text" name="yieldStageMajors[${vs.index*2+1 }].coordination" value="${stageMajors[ssKey].coordination }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'cap' }">
	                                                       <input type="text" name="yieldStageMajors[${vs.index*2+1 }].cap" value="${stageMajors[ssKey].cap }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
	                                                   </c:when>
	                                                   <c:when test="${schemeStage[0] == 'check' }">
	                                                       <input type="text" name="yieldStageMajors[${vs.index*2+1 }].check" value="${stageMajors[ssKey].check }"  data-smcode="${major.configCode }" readonly class="form-control majoryield">
	                                                   </c:when>
	                                               </c:choose>
	                                           </td>
	                                       </c:forEach>
	                                    </c:if>
	                                    <td nowrap="nowrap" style="text-align:center;"></td>
						            </tr>
                            	</c:forEach>
					        </tbody>
					    </table>
					</div>


                    <h3 class="form-tit col-lg-12">各专业部门负责人会签</h3>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">设计负责人</label>
                        <div class="col-md-8">
                            <input type="hidden" name="principalId" value="${yieldScheme.principalId }">
                            <input type="text" id="principalName" value="${yieldScheme.principalName }" class="form-control col-md-3">
                            <a title="选择" href="javascript:void(0);" class="icon-select"></a>
                        </div>
                    </div>
                    <c:if test="${not empty majors }">
						<c:forEach items="${majors }" var="major" varStatus="vs">
							<div class="form-group col-lg-6 ">
								<label class="control-label col-md-3">${major.configName }</label>
		                        <div class="col-md-8">
		                        	<input type="hidden" name="yieldMajorDuties[${vs.index }].principalId" value="${yieldDuties[major.configCode].principalId }">
		                            <input type="text" id="${major.configCode }.principalName" value="${yieldDuties[major.configCode].principalName }" class="form-control col-md-3">
		                            <a title="选择" href="javascript:void(0);" class="icon-select"></a>
		                        </div>
		                    </div>
	                    </c:forEach>
	                </c:if>
	                <div class="form-group col-lg-12 " style="margin-left: 4%">
						<label class="control-label col-md-1">备注</label>
						<div class="col-md-10">
							<textarea class="form-control" rows="5" name="remark">${yieldScheme.remark }</textarea>
						</div>
					</div>
                    <div class="">
                       <div class="row">
                           <div class="col-md-offset-3 col-md-9">
                               <button type="button" id="save-btn" class="btn blue">保存</button>
                           </div>
                       </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
    </div>
</div>
<table id="majorRatio_clone" style="display:none;">
    <tr>
	    <td nowrap="nowrap" width="40px" style="text-align:center;">{0}</td>
	    <td nowrap="nowrap" class="form-group"><input type="text" name="yieldMajors[{0}].name" value="" class="form-control" data-rule-required="true"></td>
	    <td nowrap="nowrap" class="form-group" style="width:100px;">
	       <select name="yieldMajors[{0}].priceId" class="form-control" data-rule-required="true">
	           <option value="">-请选择-</option>
	       <c:if test="${not empty prices }">
	           <c:forEach items="${prices }" var="price">
	               <option value="${price.priceId }" data-uprice="${price.standardPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
	           </c:forEach>
	       </c:if>
	       </select>
	    </td>
	    <td nowrap="nowrap" class="form-group"><input type="text" name="yieldMajors[{0}].buildArea" value="" data-rule-required="true" data-rule-number="true" class="form-control"></td>
	    <td nowrap="nowrap" id="price{0}" style="text-align:right;"></td>
	    <td nowrap="nowrap" id="sYield{0}" style="text-align:right;"></td>
	    <td nowrap="nowrap" id="mYield{0}" style="text-align:right;"></td>
	    <c:if test="${not empty majors }">
	       <c:forEach items="${majors }" var="major" >
	           <td nowrap="nowrap" id="majorRatio{0}.${major.configCode }" data-major="${major.configCode }"></td>
	           <td nowrap="nowrap" id="majorYield{0}.${major.configCode }" data-major="${major.configCode }"></td>
	       </c:forEach>
	    </c:if>
	    <td style="text-align:center;"><i class="fa fa-trash-o del-btn"></i></td>
	</tr>
</table>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${site}/resources/js/ztree/ztree-3.4-extend.js?v=${buildVersion}"></script>
<script type="text/javascript">
//打印
jQuery("#print-btn").dfprint({
    action : "printview",
    width : "1000",
    url : "${site}/admin/yield/scheme/toprint/${yieldScheme.id }"
});

//专业比例类型编号切换
$(document).on("change", "#majorRatio select[name$='priceId']", priceCodeChange);

//专业比例列表中建筑面积事件绑定
$(document).on("blur", "#majorRatio input[name$='buildArea']", buildAreaChange);

//专业比例列表中删除按钮事件绑定
$(document).on("click", "#majorRatio tbody i.del-btn", function(){
	delMajor(this);
});

//土建产值中四个金额事件绑定
$(document).on("blur", "input.fourAmount", fourAmountChange);

//土建产值中项目负责人、项目经理事件绑定
$(document).on("blur", "input.twoProUser", calProUserYield);

//各专业产值中比例事件绑定
$(document).on("blur", "#majorYield input.majorratio:not([readonly])", majorRatioChange);

var principalObj = null;
$(function(){
    // 初始化时间控件
    $(".datetimepicker").datetimepicker({
        format: "yyyy-mm-dd",
        minView: "month",
        todayBtn: 1,
        autoclose: 1
    });
    
    /**添加专业比例*/
    $("#addMajor-btn").on("click", addmajorRatio);

	// 选择责任人
	$("a.icon-select").on("click",function(){
		principalObj = this;
		selectStaff(selectStaffBack,"radio");
	});
	
	/** 保存*/
	$("#save-btn").on("click", save);

    /**计算院内合计*/
    calYLTotal();

    /**计算各专业产值中每个阶段的产值合计*/
    calEachStageYieldTotal();
});

/**添加专业比例*/
function addmajorRatio(){
	var trSize = $("#majorRatio tbody tr:not(:last)").length;
	var $item = $("#majorRatio_clone tr").clone();
	$item.html($item.html().format(trSize));
	$item.find("td:eq(0)").text(trSize + 1);
	
	$("#majorRatio tr.total").before($item);
}

/**打印*/
function printScheme(){
	
}

/**专业比例类型编号切换*/
function priceCodeChange(){
	var priceObj = $(this).find("option:selected");
	var currtr = $(this).closest("tr");
	var uprice = priceObj.data("uprice");
	if(uprice == null){
		uprice = "0";
	}
	//设置土建基准单价
	currtr.find("td[id^='price']").text(uprice);

    //计算土建基准产值
    var buildArea = currtr.find("input[name$='buildArea']").val();
    calSYield(currtr, uprice, buildArea);
    
    var ratioJson = priceObj.data("ratio");
    showMajorRate(currtr, ratioJson);

    /**计算各专业的产值*/
    calMajorYield(currtr, ratioJson);
    
    /**计算院内合计*/
    calYLTotal();
}

/**建筑面积切换*/
function buildAreaChange(){
	var currtr = $(this).closest("tr");
	var uprice = currtr.find("select[name$='priceId'] option:selected").data("uprice");
	var ratioJson = currtr.find("select[name$='priceId'] option:selected").data("ratio");
		
    //计算土建基准产值
    var buildArea = currtr.find("input[name$='buildArea']").val();
    calSYield(currtr, uprice, buildArea);

    /**计算各专业的产值*/
    calMajorYield(currtr, ratioJson);
    
    /**计算院内合计*/
    calYLTotal();
}

/**专业比例记录删除*/
function delMajor(obj){
	$.jalert({"jatype":"confirm", "jatext":"确定要删除吗", "onConfirm":function(){
		var nameVal = null;
		var thisIndex = $(obj).closest("tr").index();
		$(obj).closest("tr").nextAll("tr:not(.total)").each(function(index, item){
			$(item).find("td:eq(0)").text(thisIndex + index + 1);
			
			$(item).find("[name^='yieldMajors']").each(function(){
				nameVal = $(this).attr("name");
				nameVal = nameVal.replace(/[\d]/ig, thisIndex + index);
				$(this).attr("name", nameVal);
			});
		});		
		$(obj).closest("tr").remove();
		
	    /**计算院内合计*/
	    calYLTotal();
	}});
}

/**显示专业的默认比例*/
function showMajorRate(currtr, ratioJson){
	var ratioObj = eval(ratioJson);
	var majorRate = null;
	currtr.find("td[id^='majorRatio']").each(function(){
		majorRate = ratioObj[$(this).data("major")];
		if(majorRate == null){
			majorRate = "0";
		}
		$(this).text(majorRate);
	});
}

/**土建产值四个金额修改*/
function fourAmountChange(){
	var totalAmount = new Number(0);
	var contractAmount = $("input[name='contractAmount']").val();
	if(contractAmount != null && contractAmount != ""){
		totalAmount = new Number(contractAmount);
	}
	
	var $thisVal = null;
	$("input[name='pkgAmount'],input[name='schemeAmount'],input[name='rebateAmount']").each(function(){
		$thisVal = $(this).val();
		if($thisVal != null && $thisVal != ""){
			totalAmount = totalAmount - new Number($thisVal);
		}
	});
	$("input[name='totalAmount']").val(totalAmount.toFixed(2));
	
	var ratioParam = $("#ratioParam").val();
	if(ratioParam != null && ratioParam !=""){
		totalAmount = totalAmount * new Number(ratioParam);
	}else{
		totalAmount = new Number(0);
	}
	$("input[name='majorAmount']").val(totalAmount.toFixed(2));
	
	/**计算项目负责人、项目经理的产值*/
	calProUserYield();
	
	/**计算土建产值中各专业的产值*/
	calEachMajorYield();
}

/**各专业产值列表中比例切换*/
function majorRatioChange(){
	var smcode = $(this).data("smcode");
	
	var preliminary = $("#majorYield input.majorratio[name$='preliminary'][data-smcode='"+smcode+"']").val();
	if(preliminary == null || preliminary == ""){
		preliminary = "0";
	}
	
	var drawing = $("#majorYield input.majorratio[name$='drawing'][data-smcode='"+smcode+"']").val();
	if(drawing == null || drawing == ""){
		drawing = "0";
	}
	
	var coordination = $("#majorYield input.majorratio[name$='coordination'][data-smcode='"+smcode+"']").val();
	if(coordination == null || coordination == ""){
		coordination = "0";
	}
	
	var cap = $("#majorYield input.majorratio[name$='cap'][data-smcode='"+smcode+"']").val();
	if(cap == null || cap == ""){
		cap = "0";
	}
	
	var check = $("#majorYield input.majorratio[name$='check'][data-smcode='"+smcode+"']").val();
	if(check == null || check == ""){
		check = "0";
	}
	$("#majorYield input.majorratio[name$='subTotal'][data-smcode='"+smcode+"']").val((new Number(preliminary) + new Number(drawing)).toFixed(2));
	
	//计算指定专业的各阶段产值
	calEachStageYield(smcode);

    /**计算各专业产值中每个阶段的产值合计*/
    calEachStageYieldTotal();
}

/**选择责任人后的回调方法*/
function selectStaffBack(data){
	$(principalObj).siblings("input[name$='principalId']").val(data[0].id);
	$(principalObj).siblings("input[id$='principalName']").val(data[0].name);
}

/**
 * 检查各专业产值中的比例是否正确
 */
function checkEMRatio(){
	var flag = false;
	var smcode = null;
	$("#majorYield thead th[data-smcode]").each(function(){
		smcode = $(this).data("smcode");
		var preliminary = $("#majorYield input.majorratio[name$='preliminary'][data-smcode='"+smcode+"']").val();
		if(preliminary == null || preliminary == ""){
			preliminary = "0";
		}
		
		var drawing = $("#majorYield input.majorratio[name$='drawing'][data-smcode='"+smcode+"']").val();
		if(drawing == null || drawing == ""){
			drawing = "0";
		}
		
		var coordination = $("#majorYield input.majorratio[name$='coordination'][data-smcode='"+smcode+"']").val();
		if(coordination == null || coordination == ""){
			coordination = "0";
		}
		
		var cap = $("#majorYield input.majorratio[name$='cap'][data-smcode='"+smcode+"']").val();
		if(cap == null || cap == ""){
			cap = "0";
		}
		
		var check = $("#majorYield input.majorratio[name$='check'][data-smcode='"+smcode+"']").val();
		if(check == null || check == ""){
			check = "0";
		}

		var temp = new Number(preliminary) + new Number(drawing) + new Number(coordination);
		if(temp.toFixed(2) != "100.00" && temp.toFixed(2) != "0.00" ){
			var majorName = $(this).text();
			$.jalert({"jatext":majorName+"专业的初设、施工图、施工配合比例应该为100%"});
			flag = true;
			return false;
		}
		
		temp = new Number(cap) + new Number(check);
		if(temp.toFixed(2) != "100.00" && temp.toFixed(2) != "0.00"){
			var majorName = $(this).text();
			$.jalert({"jatext":majorName+"专业的施工配合-封顶、施工配合-验收比例应该为100%"});
			flag = true;
			return false;
		}
	});
	return flag;
}

/**保存*/
function save(){
	if (jQuery("#schemeForm").valid()) {
		if(!checkEMRatio()){
			var url ="${site}/admin/yield/scheme/ajax/save";
			$.ajax({
				type : "post",
			 	url : url,
			 	data : $("#schemeForm").serialize(),
			 	success : function(data) {
			 		if(data.flag == "true"){
			 			$.jalert({"jatext":data.msg, "jatype":"refresh", "onConfirm":function(){
			 				window.location.href="${site}/admin/yield/scheme/list";
			 			}});
			 		}else{
			 			$.jalert({"jatext":data.msg});
			 		}
			 	}
			});
		}
	}
}

/**计算专业比例列表中土建基准产值、各专业产值*/
function calSYield(currtr, uprice, barea){
	if(uprice == null){
		uprice = "0";
	}
	if(barea == null){
		barea = "0";
	}
	var sYield = new Number(uprice) * new Number(barea);
	currtr.find("td[id^='sYield']").text(sYield.toFixed(2));
	
	var ratioParam = $("#ratioParam").val();
	var mYield = sYield * new Number(ratioParam);
	currtr.find("td[id^='mYield']").text(mYield.toFixed(2));
}

/**计算专业比例列表中专业的产值*/
function calMajorYield(currtr, ratioJson){
    var majorYield = null;
	var majorRate = null;
	var ratioObj = eval(ratioJson);
    var sYield = currtr.find("td[id^='mYield']").text();
    currtr.find("td[id^='majorYield']").each(function(){
    	//如果先输入建筑面积就没有ratioObj
    	if(ratioObj != null){
	        majorRate = ratioObj[$(this).data("major")];
    	}
        if(majorRate == null){
            majorRate = "0";
        }
        
        majorYield = new Number(majorRate) * new Number(sYield) / new Number(100);
        $(this).text(majorYield.toFixed(2));
    });
}

/**计算专业比例列表中的院内合计*/
function calYLTotal(){
	var thisVal = null;
	//计算院内建筑面积
	var totalArea = new Number(0);
	$("#majorRatio input[name$='buildArea']").each(function(){
		thisVal = $(this).val();
		if(thisVal == null){
			thisVal = "0";
		}
		totalArea += new Number(thisVal);
	});
	$("#totalArea").text(totalArea.toFixed(2));
	
	//计算院内土建基准产值
	var totalSYield = new Number(0);
	$("#majorRatio td[id^='sYield']").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalSYield += new Number(thisVal);
	});
	$("#totalSYield").text(totalSYield.toFixed(2));
	
	//计算院内各专业产值
	var totalMYield = new Number(0);
	$("#majorRatio td[id^='mYield']").each(function(){
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
	
	/**计算土建产值中各专业的产值*/
	calEachMajorYield();
}

/**计算项目负责人、项目经理的产值*/
function calProUserYield(){
	//各专业产值
	var majorAmount = $("input[name='majorAmount']").val();
	if(majorAmount == null || majorAmount == ""){
		majorAmount = "0";
	}
	
	//项目负责人（%）
	var principalRate = $("input[name='principalRate']").val();
	if(principalRate == null || principalRate == ""){
		principalRate = "0";
	}
	
	//项目经理（%）
	var pmRate = $("input[name='pmRate']").val();
	if(pmRate == null || pmRate == ""){
		pmRate = "0";
	}
	
	$("input[name='principalYield']").val((new Number(majorAmount) * new Number(principalRate) / new Number(100)).toFixed(2));
	$("input[name='pmYield']").val((new Number(majorAmount) * new Number(pmRate) / new Number(100)).toFixed(2));
}

/**计算土建产值中各专业的产值*/
function calEachMajorYield(){
	//各专业总产值
	var majorAmount = $("input[name='majorAmount']").val();
	if(majorAmount == null || majorAmount == ""){
		majorAmount = "0";
	}
	//各专业的产值
	var $eachMWL = null;	//院内合计权重
	var majorCode = null;
	var $eachMajorYield = null, tempTotalYield = new Number(0);
	$("input.eachMajorYield[data-majorcode]").each(function(){
		majorCode = $(this).data("majorcode");
		$eachMWL = $("#majorRatio tr.total").find("td[data-major='"+majorCode+"']").text();
		if($eachMWL == null || $eachMWL == ""){
			$eachMWL = "0";
		}
		$eachMajorYield = new Number(majorAmount) * new Number($eachMWL) / new Number(100);
		
		//处理尾差
		if((tempTotalYield + new Number($eachMajorYield.toFixed(2))) < new Number(majorAmount)){
			tempTotalYield += new Number($eachMajorYield.toFixed(2));
		}else{
			$eachMajorYield = new Number(majorAmount) - tempTotalYield;
		}
		$(this).val($eachMajorYield.toFixed(2));
	});
	
	/**计算各专业产值各阶段每个专业的产值 */
	calAllStageYield();
}

/**计算各专业产值中指定专业的各阶段的产值*/
function calEachStageYield(majorCode){
	//土建产值中对应专业的产值
	var majorYield = $("input[name$='majorYield'][data-majorcode='"+majorCode+"'].eachMajorYield").val();
	if(majorYield == null || majorYield == ""){
		majorYield = "0";
	}
	
	//计算指定专业初设阶段的产值
	var pRate = $("#majorYield input.majorratio[name$='preliminary'][data-smcode='"+majorCode+"']").val();
	if(pRate == null || pRate == ""){
		pRate = "0";
	}
	var pYield = new Number(majorYield) * new Number(pRate) / new Number(100);
	$("#majorYield input.majoryield[name$='preliminary'][data-smcode='"+majorCode+"']").val(pYield.toFixed(2));
	
	//计算指定专业施工图阶段的产值
	var dRate = $("#majorYield input.majorratio[name$='drawing'][data-smcode='"+majorCode+"']").val();
	if(dRate == null || dRate == ""){
		dRate = "0";
	}
	var dYield = new Number(majorYield) * new Number(dRate) / new Number(100);
	$("#majorYield input.majoryield[name$='drawing'][data-smcode='"+majorCode+"']").val(dYield.toFixed(2));

	//计算指定专业小计阶段的产值
	var sRate = $("#majorYield input.majorratio[name$='subTotal'][data-smcode='"+majorCode+"']").val();
	if(sRate == null || sRate == ""){
		sRate = "0";
	}
	var sYield = new Number(pYield.toFixed(2)) + new Number(dYield.toFixed(2));
	$("#majorYield input.majoryield[name$='subTotal'][data-smcode='"+majorCode+"']").val(sYield.toFixed(2));
	
	//计算指定专业施工配合阶段的产值
	var cYield = new Number(majorYield) - new Number(sYield.toFixed(2));
	$("#majorYield input.majoryield[name$='coordination'][data-smcode='"+majorCode+"']").val(cYield.toFixed(2));

	//计算指定专业施工配合-封顶阶段的产值
	var capRate = $("#majorYield input.majorratio[name$='cap'][data-smcode='"+majorCode+"']").val();
	if(capRate == null || capRate == ""){
		capRate = "0";
	}
	var capYield = new Number(cYield.toFixed(2)) * new Number(capRate) / new Number(100);
	$("#majorYield input.majoryield[name$='cap'][data-smcode='"+majorCode+"']").val(capYield.toFixed(2));

	//计算指定专业施工配合-验收阶段的产值
	var ckYield = new Number(cYield.toFixed(2)) - new Number(capYield.toFixed(2));
	$("#majorYield input.majoryield[name$='check'][data-smcode='"+majorCode+"']").val(ckYield.toFixed(2));
}

/**计算各专业产值中各阶段所有专业的产值*/
function calAllStageYield(){
	var stages = new Array();
	$("#majorYield th[data-smcode]").each(function(){
		stages.push($(this).data("smcode"));
	});
	for(var i=0; i<stages.length; i++){
		calEachStageYield(stages[i]);
	}
}

/**计算各专业产值中各阶段的产值合计*/
function calEachStageYieldTotal(){
	$("#majorYield tbody tr").each(function(){
		var total = new Number(0);
		$(this).find("input:text.majoryield").each(function(){
			total += new Number($(this).val());
		});
		$(this).find("td:last").text(total.toFixed(2));
	});
}
</script>
</body>
</html>