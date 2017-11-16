/**计算查看页面专业比例列表中的院内合计*/
function calViewYLTotal(){
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
 * 计算查看页面各专业产值列表中各阶段的产值合计
 */
function calViewMYTotal(){
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

/**
 * 专业比例列表更多展示
 */
function viewRatioMore(){
	$("#more-btn").on("click", function(){
        $("#majorRatio tbody tr:not(.total)").toggle(function(){
            if($(this).is(":visible")){
            	$("#more-btn").removeClass("fa-chevron-down").addClass("fa-chevron-up");
                $("#more-btn h6").text("收起");
            }else{
            	$("#more-btn").removeClass("fa-chevron-up").addClass("fa-chevron-down");
            	$("#more-btn h6").text("展开");
            }
        });
    });
}

/**
 * 加载基本信息编辑
 */
function loadBaseEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/baseEdit/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#base-info-div").html(data);
		}
	});
}

/**
 * 保存基本信息
 */
function saveBase(){
	var datas = {};
	datas.id = $("input[name='id']").val();
	datas.schemeNo = $("input[name='schemeNo']").val();
	datas.lastUpdate = $("input[name='lastUpdate']").val();
	datas.landArea = $("input[name='landArea']").val();
	datas.schemeBasis = $("input[name='schemeBasis']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/baseSave",
		data : datas,
		dataType : "json",
		success : function(data) {
			if(data.flag == "true"){
				loadBaseView();
	 		}else{
	 			$.jalert({"jatext":data.msg});
	 		}
		}
	});
}

/**加载基本信息查看*/
function loadBaseView(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/baseView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#base-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

/**
 * 加载专业比例编辑
 */
function loadRatioEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/ratioEdit/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#ratio-info-div").html(data);
		}
	});
}

/**
 * 保存专业比例
 */
function saveRatio(){
	var datas = {};
	datas.id = $("input[name='id']").val();
	datas.schemeNo = $("input[name='schemeNo']").val();
	datas.lastUpdate = $("input[name='lastUpdate']").val();
	datas.landArea = $("input[name='landArea']").val();
	datas.schemeBasis = $("input[name='schemeBasis']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/ratioSave",
		data : datas,
		dataType : "json",
		success : function(data) {
			if(data.flag == "true"){
				loadRationView();
	 		}else{
	 			$.jalert({"jatext":data.msg});
	 		}
		}
	});
}

/**加载专业比例查看*/
function loadRatioView(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/ratioView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#ratio-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}