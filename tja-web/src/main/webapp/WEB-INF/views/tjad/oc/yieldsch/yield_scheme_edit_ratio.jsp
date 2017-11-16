<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="df" uri="http://www.diligentfirst.com/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="form-body clearfix">
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
	                <th rowspan="2" style="text-align:center;" class="form-group">土建基准<br>单价(元/m<sup>2</sup>)</br><span class="required">※</span></th>
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
	                        <th nowrap="nowrap" class="form-group" style="text-align:center;width:80px;">比例<span class="required">※</span></th>
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
					    <td nowrap="nowrap" class="form-group"><input type="text" name="yieldMajors[${vs.index}].standardPrice" value="${yieldMajor.standardPrice }" data-rule-number="true" data-rule-required="true" class="form-control"></td>
					    <td nowrap="nowrap" id="sYield${vs.index}" style="text-align:right;">${yieldMajor.standardYield }</td>
					    <td nowrap="nowrap" id="mYield${vs.index}" style="text-align:right;">${yieldMajor.majorYield }</td>
					    <c:if test="${not empty majors }">
					       <c:forEach items="${majors }" var="major" >
						       <c:set var="majorKey" value="${yieldMajor.id}.${major.configCode }"/>
					           <td nowrap="nowrap" id="majorRatio${vs.index}.${major.configCode }" data-major="${major.configCode }">
							   		<input type="text" value="${yieldMajor.majorMap[majorKey].majorRate }" data-rule-number="true" data-rule-required="true" class="form-control">
					           </td>
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
    <div class="col-xs-12" style="padding:10px 0px;">
		<div class="row">
	        <div class="col-md-offset-5 col-md-7">
	            <button type="button" id="save-btn" class="btn blue">保存</button>
	            <button type="button" id="cancel-btn" class="btn default">取消</button>
	        </div>
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
	    <td nowrap="nowrap" class="form-group"><input type="text" name="yieldMajors[{0}].standardPrice" value="" data-rule-number="true" data-rule-required="true" class="form-control"></td>
	    <td nowrap="nowrap" id="sYield{0}" style="text-align:right;"></td>
	    <td nowrap="nowrap" id="mYield{0}" style="text-align:right;"></td>
	    <c:if test="${not empty majors }">
	       <c:forEach items="${majors }" var="major" >
	           <td nowrap="nowrap" id="majorRatio{0}.${major.configCode }" data-major="${major.configCode }">
	           		<input type="text" value="" data-rule-number="true" data-rule-required="true" class="form-control">
	           </td>
	           <td nowrap="nowrap" id="majorYield{0}.${major.configCode }" data-major="${major.configCode }"></td>
	       </c:forEach>
	    </c:if>
	    <td style="text-align:center;"><i class="fa fa-trash-o del-btn"></i></td>
	</tr>
</table>
<script type="text/javascript">
//专业比例列表中删除按钮事件绑定
$(document).on("click", "#majorRatio tbody i.del-btn", function(){
	delMajor(this);
});

//专业比例类型编号切换
$(document).on("change", "#majorRatio select[name$='priceId']", priceCodeChange);

$(function(){
	//添加专业比例记录
	$("#addMajor-btn").on("click", addMajorRatio);
});

/**添加专业比例*/
function addMajorRatio(){
	var trSize = $("#majorRatio tbody tr:not(:last)").length;
	var $item = $("#majorRatio_clone tr").clone();
	$item.html($item.html().format(trSize));
	$item.find("td:eq(0)").text(trSize + 1);
	
	$("#majorRatio tr.total").before($item);
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
	}});
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
	currtr.find("input[name$='standardPrice']").val(uprice);

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

/**显示专业的默认比例*/
function showMajorRate(currtr, ratioJson){
	var ratioObj = eval(ratioJson);
	var majorRate = null;
	currtr.find("td[id^='majorRatio']").each(function(){
		majorRate = ratioObj[$(this).data("major")];
		if(majorRate == null){
			majorRate = "0";
		}
		$(this).find("input").val(majorRate);
	});
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
}
</script>
