<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>施工图产值策划</title>
    <%--每个jsp页面所在菜单的treePath属性值 --%>
    <df:readProp var="menu-path" value="oc.yield.scheme.menu.path" scope="request"  />
    <style type="text/css">
        #majorRatio td[data-major]{width:80px;text-align:right;}
        #majorRatio tr.total td{text-align:right;}
    </style>
</head>
<body>
<div class="">
    <center>
        <h3>施工图产值策划</h3>
    </center>
    <div class="  ">
        <div class="form">
            <!-- BEGIN FORM-->
            <form action="#" class=" ">
                <input type="hidden" id="ratioParam" value="${ratioParam }">
                <div class="form-body clearfix">
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">策划编号</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeNo" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">更新日期</label>
                        <div class="col-md-8">
                            <input type="text" name="modifyDate" class="form-control datetimepicker">
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
                            <input type="text" name="landArea" class="form-control text-right" placeholder="0.00">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">策划依据</label>
                        <div class="col-md-8">
                            <input type="text" name="schemeBasis" class="form-control">
                        </div>
                    </div>
                    <h3 class="form-tit col-lg-12">专业比例<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span>
                        <input type="button" id="addMajor-btn" class="btn blue " value="添加" style="float: right;"></h3>
					<div class="table-scrollable">
					    <table id="majorRatio" class="table table-striped table-bordered table-advance table-hover dataTable">
					        <thead>
					            <tr>
					                <th nowrap="nowrap" rowspan="2" style="ext-align:center;">序号</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">类型名称(系数)</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">类型编号</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">建筑面积(m<sup>2</sup>)</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">土建基准<br>单价(元/m<sup>2</sup>)</br></th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">土建基准产值</th>
					                <th nowrap="nowrap" rowspan="2" style="text-align:center;">各专业产值<br>(90%)</br></th>
					                <c:if test="${not empty majors }">
					                   <c:forEach items="${majors }" var="major" >
					                       <th nowrap="nowrap" colspan="2" style="text-align:center;">${major.configName }</th>
					                   </c:forEach>
					                </c:if>
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
					            </tr>
					        </tbody>
					    </table>
					</div>

                    <h3 class="form-tit col-lg-12">土建产值<span class="control-label" style="font-size:12px;">（元）</span></h3>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">实际合同额</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">分包扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">方案扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">其他扣减</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">土建总产值</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">各专业产值</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人（%）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目负责人（产值）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理（%）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">项目经理（产值）</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    
                    <h3 class="form-tit col-lg-12">各专业产值<span class="control-label" style="font-size:12px;">（比例：%  产值：元）</span></h3>

                    <h3 class="form-tit col-lg-12">各专业部门负责人会签</h3>
                    <div class="form-group col-lg-6 ">
                        <label class="control-label col-md-3">设计负责人</label>
                        <div class="col-md-8">
                            <input type="text" name="" class="form-control">
                        </div>
                    </div>
                    
                    <div class="">
                       <div class="row">
                           <div class="col-md-offset-3 col-md-9">
                               <button type="button" class="btn blue">保存</button>
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
	    <td nowrap="nowrap"><input type="text" name="yieldMajors[{0}].name" value="" class="form-control"></td>
	    <td nowrap="nowrap" style="width:100px;">
	       <select name="yieldMajors[{0}].priceId" class="form-control">
	           <option value="">-请选择-</option>
	       <c:if test="${not empty prices }">
	           <c:forEach items="${prices }" var="price">
	               <option value="${price.id }" data-uprice="${price.unitPrice }" data-ratio='${price.ratioJson }' >${price.typeCode }-${price.typeName }</option>
	           </c:forEach>
	       </c:if>
	       </select>
	    </td>
	    <td nowrap="nowrap"><input type="text" name="yieldMajors[{0}].buildArea" value="" data-rule-number="true" class="form-control"></td>
	    <td nowrap="nowrap" id="price{0}" style="text-align:right;"></td>
	    <td nowrap="nowrap" id="sYield{0}" style="text-align:right;"></td>
	    <td nowrap="nowrap" id="mYield{0}" style="text-align:right;"></td>
	    <c:if test="${not empty majors }">
	       <c:forEach items="${majors }" var="major" >
	           <td nowrap="nowrap" id="majorRatio{0}.${major.configCode }" data-major="${major.configCode }"></td>
	           <td nowrap="nowrap" id="majorYield{0}.${major.configCode }" data-major="${major.configCode }"></td>
	       </c:forEach>
	    </c:if>
	</tr>
</table>
<script type="text/javascript" src="${site}/resources/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
//专业比例类型编号切换
$(document).on("change", "#majorRatio select[name$='priceId']", priceCodeChange);

$(document).on("blur", "#majorRatio input[name$='buildArea']", buildAreaChange);

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
});

/**添加专业比例*/
function addmajorRatio(){
	var trSize = $("#majorRatio tbody tr:not(:last)").length;
	var $item = $("#majorRatio_clone tr").clone();
	$item.html($item.html().format(trSize));
	$item.find("td:eq(0)").text(trSize + 1);
	
	$("#majorRatio tr.total").before($item);
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

/**计算土建基准产值、各专业产值*/
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

/**计算专业的产值*/
function calMajorYield(currtr, ratioJson){
    var majorYield = null;
	var majorRate = null;
	var ratioObj = eval(ratioJson);
    var sYield = currtr.find("td[id^='mYield']").text();
    currtr.find("td[id^='majorYield']").each(function(){
        majorRate = ratioObj[$(this).data("major")];
        if(majorRate == null){
            majorRate = "0";
        }
        
        majorYield = new Number(majorRate) * new Number(sYield) / new Number(100);
        $(this).text(majorYield.toFixed(2));
    });
}

/**计算院内合计*/
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
	var totalYield = new Number(0);
	$("#majorRatio td[id^='majorYield']").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalYield += new Number(thisVal);
	});
	
	var totalMajorYield = null;
	$("#majorRatio tr.total td[data-major]").each(function(){
		totalMajorYield = new Number(0);
		if(totalYield > 0){
			$("#majorRatio td[id^='majorYield'][data-major='"+ $(this).data("major") +"']").each(function(){
				thisVal = $(this).text();
				if(thisVal == null){
					thisVal = "0";
				}
				totalMajorYield += new Number(thisVal);
			});
			totalMajorYield = totalMajorYield * new Number(100);
			totalMajorYield = totalMajorYield / totalYield;
		}
		$(this).text(totalMajorYield.toFixed(2));
	});
}
</script>
</body>
</html>