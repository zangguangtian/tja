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
    $("#majorRatio tbody tr:not(.total)").toggle(function(){
        if($(this).is(":visible")){
        	$("#more-btn").removeClass("fa-chevron-down").addClass("fa-chevron-up");
            $("#more-btn h6").text("收起");
        }else{
        	$("#more-btn").removeClass("fa-chevron-up").addClass("fa-chevron-down");
        	$("#more-btn h6").text("展开");
        }
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
	if (jQuery("#schemeForm").valid()) {
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
	if (jQuery("#schemeForm").valid()) {
		var datas = {};
		datas.id = $("input[name='id']").val();
		datas.proId = $("input[name='proId']").val();
		
		var canSave = true; //是否可以保存的标志
		var major = null, ratioJson = null, yieldMajors = [];
		var ratioTotal = new Number(0);
		$("#majorRatio tbody tr:not(:last)").each(function(index, item){
			major = {};
			major.id = $(item).find("input[name^='id']").val();
			major.name = $(item).find("input[name^='name']").val();
			major.priceId = $(item).find("select[name^='priceId']").val();
			major.buildArea = $(item).find("input[name^='buildArea']").val();
			major.standardPrice = $(item).find("input[name^='standardPrice']").val();
			
			ratioJson = {};
			ratioTotal = new Number(0);
			$(item).find("input[name^='majorRate']").each(function(rIndex, rItem){
				ratioTotal = ratioTotal + new Number($(rItem).val());
				ratioJson[$(rItem).data("major")] = $(rItem).val();
			});
			major.ratioJson = JSON.stringify(ratioJson);
			yieldMajors.push(major);
			
			if(ratioTotal.toFixed(2) != "100.00"){
				$.jalert({"jatext":major.name + "的各专业比例之和不等于100%"});
				canSave = false;
				return false;
			}
		});
		datas.yieldMajors = yieldMajors;
		if(canSave){
			jQuery.ajax({
				type : "POST",
				url : context+"/admin/yield/scheme/ajax/ratioSave",
				data : JSON.stringify(datas),
				dataType : "json",
		        contentType : "application/json",
				success : function(data) {
					if(data.flag == "true"){
						loadRatioView();
			 		}else{
			 			$.jalert({"jatext":data.msg});
			 		}
				}
			});
		}
	}
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
			calViewYLTotal();
		}
	});
}

/**
 * 加载土建产值编辑
 */
function loadCivilEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/civilEdit/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#civil-info-div").html(data);
		}
	});
}

/**
 * 保存土建产值
 */
function saveCivil(){
	if (jQuery("#schemeForm").valid()) {
		var datas = {};
		datas.id = $("input[name='id']").val();
		datas.contractAmount = $("input[name='contractAmount']").val();
		datas.pkgAmount = $("input[name='pkgAmount']").val();
		datas.schemeAmount = $("input[name='schemeAmount']").val();
		datas.rebateAmount = $("input[name='rebateAmount']").val();
		datas.principalRate = $("input[name='principalRate']").val();
		datas.pmRate = $("input[name='pmRate']").val();
		jQuery.ajax({
			type : "POST",
			url : context+"/admin/yield/scheme/ajax/civilSave",
			data : JSON.stringify(datas),
			dataType : "json",
	        contentType : "application/json",
			success : function(data) {
				if(data.flag == "true"){
					loadCivilView();
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
			}
		});
	}
}

/**加载土建产值查看*/
function loadCivilView(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/civilView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#civil-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){

		}
	});
}

/**
 * 加载各专业产值编辑
 */
function loadStageEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/stageEdit/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#stage-info-div").html(data);
		}
	});
}

/**
 * 保存各专业产值
 */
function saveStage(){
	if (jQuery("#schemeForm").valid()) {
		var datas = {};
		datas.id = $("input[name='id']").val();
		
		var stageMajors = [], stageMajor = null;
		var rows = $("#majorYield tbody tr").length;
		var cols = $("#majorYield tbody tr:eq(0) td").length - 1;	//减1是去掉产值合计列
		if(cols > 2 && rows > 0){
			var cell = null;
			for(var col = 1; col < cols; col = col + 2){
				stageMajor = {};
				for(var row = 0; row < rows; row++){
					cell = $("#majorYield tbody").find("tr:eq("+row+") td:eq("+col+")");
					if(row == 0){
						stageMajor.majorCode = cell.find("input[name^='majorCode']").val();
						stageMajor.preliminary = cell.find("input[name^='preliminary']").val();
					}else if(row == 1){
						stageMajor.drawing = cell.find("input[name^='drawing']").val();
					}else if(row == 2){
						stageMajor.subTotal = cell.find("input[name^='subTotal']").val();
					}else if(row == 3){
						stageMajor.coordination = cell.find("input[name^='coordination']").val();
					}else if(row == 4){
						stageMajor.cap = cell.find("input[name^='cap']").val();
					}else if(row == 5){
						stageMajor.check = cell.find("input[name^='check']").val();
					}
				}
				stageMajors.push(stageMajor);
			}
		}
		datas.yieldStageMajors = stageMajors;
		
		jQuery.ajax({
			type : "POST",
			url : context+"/admin/yield/scheme/ajax/stageSave",
			data : JSON.stringify(datas),
			dataType : "json",
	        contentType : "application/json",
			success : function(data) {
				if(data.flag == "true"){
					loadStageView();
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
			}
		});
	}
}

/**加载各专业产值查看*/
function loadStageView(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajax/stageView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#stage-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			calViewMYTotal();
		}
	});
}