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
	//所有专业的总产值减去所有专业扣减
	$("#majorRatio tr.minus td[data-major]").each(function(){
		thisVal = $(this).text() || 0;
		totalYield -= new Number(thisVal);
	});
	
	//每个专业的总产值
	var totalMajorYield = null;
	//用于处理尾差
	var tempTotalYield = new Number(0);
	var majorMinu = null;
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
			
			//专业扣减
			majorMinu = $($("#majorRatio tr.minus td[data-major='"+ $(this).data("major") +"']")[0]).text() || 0;
			//减去此专业的专业扣减
			totalMajorYield -= new Number(majorMinu);
			
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

/**专业比例列表更多展示*/
$(document).on("click", "#more-btn", function(){
	viewRatioMore();
});

/**打开基本信息编辑*/
$(document).on("click", "#base-info-div .fa-edit", function(){
	singleEdit(this);
	loadBaseEdit();
});

/**基本信息编辑页面保存*/
$(document).on("click", "#base-info-div #save-btn", function(){
	saveBase();
});

/**基本信息编辑页面取消*/
$(document).on("click", "#base-info-div #cancel-btn", function(){
	loadBaseView();
});

/**打开专业比例编辑*/
$(document).on("click", "#ratio-info-div .fa-edit", function(){
	singleEdit(this);
	loadRatioEdit();
});

/**专业比例编辑页面保存*/
$(document).on("click", "#ratio-info-div #save-btn", function(){
	saveRatio();
});

/**专业比例编辑页面取消*/
$(document).on("click", "#ratio-info-div #cancel-btn", function(){
	loadRatioView("cancel");
});

/**打开土建产值编辑*/
$(document).on("click", "#civil-info-div .fa-edit", function(){
	singleEdit(this);
	loadCivilEdit();
});

/**土建产值编辑页面保存*/
$(document).on("click", "#civil-info-div #save-btn", function(){
	saveCivil();
});

/**土建产值编辑页面取消*/
$(document).on("click", "#civil-info-div #cancel-btn", function(){
	loadCivilView("cancel");
});

/**打开各专业产值编辑*/
$(document).on("click", "#stage-info-div .fa-edit", function(){
	singleEdit(this);
	loadStageEdit();
});

/**各专业产值编辑页面保存*/
$(document).on("click", "#stage-info-div #save-btn", function(){
	saveStage();
});

/**各专业产值编辑页面取消*/
$(document).on("click", "#stage-info-div #cancel-btn", function(){
	loadStageView();
});

/**打开项目管理产值编辑*/
$(document).on("click", "#project-info-div .fa-edit", function(){
	singleEdit(this);
	loadProjectEdit();
});

/**项目管理产值编辑页面保存*/
$(document).on("click", "#project-info-div #save-btn", function(){
	saveProject();
});

/**项目管理产值编辑页面取消*/
$(document).on("click", "#project-info-div #cancel-btn", function(){
	loadProjectView();
});

/**打开各专业部门负责人会签编辑*/
$(document).on("click", "#principal-info-div .fa-edit", function(){
	singleEdit(this);
	loadPrincipalEdit();
});

/**各专业部门负责人会签编辑页面保存*/
$(document).on("click", "#principal-info-div #save-btn", function(){
	savePrincipal();
});

/**各专业部门负责人会签编辑页面取消*/
$(document).on("click", "#principal-info-div #cancel-btn", function(){
	loadPrincipalView();
});

/**
 * 保证一次只能打开一个编辑部分
 */
function singleEdit(obj){
	$(obj).closest("div[id$='info-div']").siblings("div").each(function(){
		if($(this).find("[id='cancel-btn']").length > 0){
			$(this).find("[id='cancel-btn']").click();
		}
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

//专业比例列表中删除按钮事件绑定
$(document).on("click", "#majorRatio tbody i.del-btn", function(){
	delMajor(this);
});

//专业比例类型编号切换
$(document).on("change", "#majorRatio select[name^='priceId']", priceCodeChange);

//专业比例类型编号切换
$(document).on("blur", "#majorRatio input[name^='buildArea']", otherFactorChange);
$(document).on("blur", "#majorRatio input[name^='standardPrice']", otherFactorChange);
$(document).on("blur", "#majorRatio input[name^='majorRate']", otherFactorChange);

//专业比例中专业扣减失去焦点事件
$(document).on("blur", "#majorRatio input[name^='minusYield']", majorMinuChange);

//土建产值中四个金额事件绑定
$(document).on("blur", "input.fourAmount", fourAmountChange);

//项目管理产值中项目负责人、项目经理、项目秘书事件绑定
$(document).on("blur", "input.twoProUser", calProUserYield);

//各专业产值中比例事件绑定
$(document).on("blur", "#majorYield input.majorratio:not([readonly])", majorRatioChange);

/**专业比例记录删除*/
function delMajor(obj){
	$.jalert({"jatype":"confirm", "jatext":"确定要删除吗", "onConfirm":function(){
		var nameVal = null;
		var thisIndex = $(obj).closest("tr").index();
		$(obj).closest("tr").nextAll("tr:not(.total):not(.minus)").each(function(index, item){
			$(item).find("td:eq(0)").text(thisIndex + index + 1);
			//此处删除只管处理序号，input元素的下标无需处理
		});
		$(obj).closest("tr").remove();
		
		/**计算院内合计*/
	    calYLTotal();
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
	currtr.find("input[name^='standardPrice']").val(uprice);

    //计算土建基准产值
    var buildArea = currtr.find("input[name^='buildArea']").val();
    calSYield(currtr, uprice, buildArea);
    
    /**显示专业的默认比例*/
    var ratioJson = priceObj.data("ratio");
    showMajorRate(currtr, ratioJson);

    /**按行计算各专业的产值*/
    calMajorYield(currtr, ratioJson);
    
    /**计算院内合计*/
    calYLTotal();
}

/**专业比例建筑面积、基准单价、比例切换*/
function otherFactorChange(){
	var currtr = $(this).closest("tr");

    //计算土建基准产值
    var buildArea = currtr.find("input[name^='buildArea']").val();
    var uprice = currtr.find("input[name^='standardPrice']").val();
    calSYield(currtr, uprice, buildArea);

    var ratioJson = {};
    currtr.find("input[name^='majorRate']").each(function(){
    	ratioJson[$(this).data("major")] = $(this).val();
    });
    
    /**按行计算各专业的产值*/
    calMajorYield(currtr, ratioJson);
    
    /**计算院内合计*/
    calYLTotal();
}

/**专业比例中的专业扣减修改*/
function majorMinuChange(){
	var majorCode = $(this).data("major");
	var thisVal = null;
	var totalYield = new Number(0);
	$("#majorRatio td[id^='majorYield'][data-major='"+ majorCode +"']").each(function(){
		thisVal = $(this).text() || 0;
		totalYield += new Number(thisVal);
	});
	
	var temp = new Number($(this).val()) - totalYield;
	if(temp > 0){
		$.jalert({"jatext":"专业扣减值不能超过本专业的产值之和："+totalYield.toFixed(2)});
		return;
	}
	calYLMinu();
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
	currtr.find("input[name^='majorRate']").each(function(){
		majorRate = ratioObj[$(this).data("major")];
		if(majorRate == null){
			majorRate = "0";
		}
		$(this).val(majorRate);
	});
}

/**计算专业比例列表中专业的产值*/
function calMajorYield(currtr, ratioJson){
    var majorYield = null;
	var majorRate = null;
	var ratioObj = null;
    var sYield = currtr.find("td[id^='mYield']").text();
    
    if(typeof ratioJson == "string"){
    	ratioObj = eval(ratioJson);
    }else{
    	ratioObj = ratioJson;
    }

    var temp = new Number(0);
    currtr.find("td[id^='majorYield']").each(function(){
    	majorRate = $(this).prev("td").find("input[name^='majorRate']").val();
        if(majorRate == null){
            majorRate = "0";
        }
        
        majorYield = new Number(majorRate) * new Number(sYield) / new Number(100);
        if(temp + new Number(majorYield.toFixed(2)) < new Number(sYield)){
        	temp = temp + new Number(majorYield.toFixed(2));
        }else{
        	majorYield = new Number(sYield) - temp;
        }
        $(this).text(majorYield.toFixed(2));
    });
}

/**
 * 计算专业比例列表中专业扣减后的院内合计
 * @returns
 */
function calYLMinu(){
	var thisVal = null;
	//所有专业的总产值
	var totalYield = new Number(0);
	$("#majorRatio td[id^='majorYield']").each(function(){
		thisVal = $(this).text();
		if(thisVal == null){
			thisVal = "0";
		}
		totalYield += new Number(thisVal);
	});
	//所有专业的总产值减去所有专业扣减
	$("#majorRatio tr.minus input[name^='minusYield']").each(function(){
		thisVal = $(this).val() || 0;
		totalYield -= new Number(thisVal);
	});
	
	//每个专业的总产值
	var totalMajorYield = null, majorMinu = null;
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
			
			//专业扣减
			majorMinu = $($("#majorRatio input[name^='minusYield'][data-major='"+ $(this).data("major") +"']")[0]).val() || 0;
			//减去此专业的专业扣减
			totalMajorYield -= new Number(majorMinu);
			
			//计算合计比例
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

/**计算专业比例列表中的院内合计*/
function calYLTotal(){
	var thisVal = null;
	//计算院内建筑面积
	var totalArea = new Number(0);
	$("#majorRatio input[name^='buildArea']").each(function(){
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
	calYLMinu();
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
	
	/**计算土建产值中各专业的产值*/
	calEachMajorYield();
}


/**计算项目负责人、项目经理的产值*/
function calProUserYield(){
	//各专业产值
	var majorAmount = $("label#totalAmountLabel").text();
	if(majorAmount == null || majorAmount == ""){
		majorAmount = "0";
	}
	
	var ddStageParam = $("#ddStageParam").val();
	if(ddStageParam == null || ddStageParam == ""){
		ddStageParam = "0";
	}
	
	var ccoStageParam = $("#ccoStageParam").val();
	if(ccoStageParam == null || ccoStageParam == ""){
		ccoStageParam = "0";
	}
	
	var cctStageParam = $("#cctStageParam").val();
	if(cctStageParam == null || cctStageParam == ""){
		cctStageParam = "0";
	}
	
	var userRate = $(this).val();
	if(userRate == null || userRate == ""){
		userRate = "0";
	}
	
	var userYield = "0";
	var otherYield = "0";
	$(this).closest("tr").find("td:gt(1)").each(function(index, item){
		if(index == 0){
			userYield = (new Number(majorAmount) * new Number(userRate) / new Number(100)).toFixed(2);
			$(item).text(userYield);
		}else {
			if(index == 1){
				otherYield = (new Number(userYield) * new Number(ddStageParam)).toFixed(2);
			}else if(index == 2){
				otherYield = (new Number(userYield) * (new Number(1) - new Number(ddStageParam)) * new Number(ccoStageParam)).toFixed(4);
			}else if(index == 3){
				otherYield = (new Number(userYield) * (new Number(1) - new Number(ddStageParam)) * new Number(cctStageParam)).toFixed(4);
			}else{
				otherYield = "0";
			}
			$(item).text(otherYield);
		}
	});
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
}

/**各专业产值列表中比例切换*/
function majorRatioChange(){
	var smcode = $(this).data("smcode");
	
	var preliminary = $("#majorYield input.majorratio[name^='preliminary'][data-smcode='"+smcode+"']").val();
	if(preliminary == null || preliminary == ""){
		preliminary = "0";
	}
	
	var drawing = $("#majorYield input.majorratio[name^='drawing'][data-smcode='"+smcode+"']").val();
	if(drawing == null || drawing == ""){
		drawing = "0";
	}
	
	var subTotal = (new Number(preliminary) + new Number(drawing)).toFixed(2);
	$("#majorYield input.majorratio[name^='subTotal'][data-smcode='"+smcode+"']").val(subTotal);
	
	var coordination = (new Number(100) - new Number(subTotal)).toFixed(2);
	$("#majorYield input.majorratio[name^='coordination'][data-smcode='"+smcode+"']").val(coordination);
	
	var cap = $("#majorYield input.majorratio[name^='cap'][data-smcode='"+smcode+"']").val();
	if(cap == null || cap == ""){
		cap = "0";
	}
	
	var check = (new Number(100) - new Number(cap)).toFixed(2);
	$("#majorYield input.majorratio[name^='check'][data-smcode='"+smcode+"']").val(check);
	
	//计算指定专业的各阶段产值
	calEachStageYield(smcode);

    /**计算各专业产值中每个阶段的产值合计*/
    calEachStageYieldTotal();
}

/**计算各专业产值中指定专业的各阶段的产值*/
function calEachStageYield(majorCode){
	//土建产值中对应专业的产值
	var majorYield = $("#civil-info-div label[data-majorcode='"+majorCode+"']").text();
	if(majorYield == null || majorYield == ""){
		majorYield = "0";
	}
	
	//计算指定专业初设阶段的产值
	var pRate = $("#majorYield input.majorratio[name^='preliminary'][data-smcode='"+majorCode+"']").val();
	if(pRate == null || pRate == ""){
		pRate = "0";
	}
	var pYield = new Number(majorYield) * new Number(pRate) / new Number(100);
	$("#majorYield input.majoryield[name^='preliminary'][data-smcode='"+majorCode+"']").val(pYield.toFixed(2));
	
	//计算指定专业施工图阶段的产值
	var dRate = $("#majorYield input.majorratio[name^='drawing'][data-smcode='"+majorCode+"']").val();
	if(dRate == null || dRate == ""){
		dRate = "0";
	}
	var dYield = new Number(majorYield) * new Number(dRate) / new Number(100);
	$("#majorYield input.majoryield[name^='drawing'][data-smcode='"+majorCode+"']").val(dYield.toFixed(2));

	//计算指定专业小计阶段的产值
	var sRate = $("#majorYield input.majorratio[name^='subTotal'][data-smcode='"+majorCode+"']").val();
	if(sRate == null || sRate == ""){
		sRate = "0";
	}
	var sYield = new Number(pYield.toFixed(2)) + new Number(dYield.toFixed(2));
	$("#majorYield input.majoryield[name^='subTotal'][data-smcode='"+majorCode+"']").val(sYield.toFixed(2));
	
	//计算指定专业施工配合阶段的产值
	var cYield = new Number(majorYield) - new Number(sYield.toFixed(2));
	$("#majorYield input.majoryield[name^='coordination'][data-smcode='"+majorCode+"']").val(cYield.toFixed(2));

	//计算指定专业施工配合-封顶阶段的产值
	var capRate = $("#majorYield input.majorratio[name^='cap'][data-smcode='"+majorCode+"']").val();
	if(capRate == null || capRate == ""){
		capRate = "0";
	}
	var capYield = new Number(cYield.toFixed(2)) * new Number(capRate) / new Number(100);
	$("#majorYield input.majoryield[name^='cap'][data-smcode='"+majorCode+"']").val(capYield.toFixed(2));

	//计算指定专业施工配合-验收阶段的产值
	var ckYield = new Number(cYield.toFixed(2)) - new Number(capYield.toFixed(2));
	$("#majorYield input.majoryield[name^='check'][data-smcode='"+majorCode+"']").val(ckYield.toFixed(2));
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

/**
 * 加载基本信息编辑
 */
function loadBaseEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/baseEdit/"+schemeId,
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
		url : context+"/admin/yield/scheme/ajaxhtml/baseView/"+schemeId,
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
		url : context+"/admin/yield/scheme/ajaxhtml/ratioEdit/"+schemeId,
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
		var major = null, ratioJson = null, yieldMajors = [], duty = null, yieldDuties = [];
		var ratioTotal = new Number(0);
		//取tbody中除最后两行以外的其他行
		$("#majorRatio tbody tr:not(.total):not(.minus)").each(function(index, item){
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
		
		//取专业扣减
		$("#majorRatio tbody tr.minus input[name^='minusYield']").each(function(index, item){
			duty = {};
			duty.majorCode = $(item).data("major");
			duty.minusYield = $(item).val();
			yieldDuties.push(duty);
		});
		datas.yieldMajorDuties = yieldDuties;
		
		if(canSave){
			jQuery.ajax({
				type : "POST",
				url : context+"/admin/yield/scheme/ajax/ratioSave",
				data : JSON.stringify(datas),
				dataType : "json",
		        contentType : "application/json",
				success : function(data) {
					if(data.flag == "true"){
						loadRatioView("save");
			 		}else{
			 			$.jalert({"jatext":data.msg});
			 		}
				}
			});
		}
	}
}

/**加载专业比例查看*/
function loadRatioView(opt){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/ratioView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#ratio-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			calViewYLTotal();
			if(opt == "save"){
				loadCivilView(opt);
			}
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
		url : context+"/admin/yield/scheme/ajaxhtml/civilEdit/"+schemeId,
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
		/*datas.principalRate = $("input[name='principalRate']").val();
		datas.pmRate = $("input[name='pmRate']").val();
		datas.secretRate = $("input[name='secretRate']").val();*/
		
		var majorDuties = [], majorDuty = null;
		$("#civil-info-div input[name^='stageMajorYield'][data-majorcode]").each(function(){
			majorDuty = {};
			majorDuty.majorCode = $(this).data("majorcode");
			majorDuty.majorYield = $(this).val();
			majorDuties.push(majorDuty);
		});
		datas.yieldMajorDuties = majorDuties;
		
		jQuery.ajax({
			type : "POST",
			url : context+"/admin/yield/scheme/ajax/civilSave",
			data : JSON.stringify(datas),
			dataType : "json",
	        contentType : "application/json",
			success : function(data) {
				if(data.flag == "true"){
					loadCivilView("save");
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
			}
		});
	}
}

/**加载土建产值查看*/
function loadCivilView(opt){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/civilView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#civil-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			if(opt == "save"){	//如果是保存，则需要重新加载各专业产值
				loadStageView();
				loadProjectView();
			}
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
		url : context+"/admin/yield/scheme/ajaxhtml/stageEdit/"+schemeId,
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
		url : context+"/admin/yield/scheme/ajaxhtml/stageView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#stage-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			calViewMYTotal();
		}
	});
}

/**
 * 加载项目管理产值编辑
 */
function loadProjectEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/projectEdit/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#project-info-div").html(data);
		}
	});
}

/**
 * 保存项目管理产值
 */
function saveProject(){
	if (jQuery("#schemeForm").valid()) {
		var datas = {};
		datas.id = $("input[name='id']").val();
		datas.principalRate = $("input[name='principalRate']").val();
		datas.pmRate = $("input[name='pmRate']").val();
		datas.secretRate = $("input[name='secretRate']").val();
		
		jQuery.ajax({
			type : "POST",
			url : context+"/admin/yield/scheme/ajax/projectSave",
			data : JSON.stringify(datas),
			dataType : "json",
	        contentType : "application/json",
			success : function(data) {
				if(data.flag == "true"){
					loadProjectView();
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
			}
		});
	}
}

/**加载项目管理产值查看*/
function loadProjectView(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/projectView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#project-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}



/**
 * 加载各专业部门负责人会签编辑
 */
function loadPrincipalEdit(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/principalEdit/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#principal-info-div").html(data);
		}
	});
}

/**
 * 保存各专业部门负责人会签
 */
function savePrincipal(){
	if (jQuery("#schemeForm").valid()) {
		var datas = {};
		datas.id = $("input[name='id']").val();
		datas.principalId = $("input[name='principalId']").val();
		datas.remark = $("textarea[name='remark']").val();
		
		var majorDuties = [], majorDuty = null;
		$("#principal-info-div input[name^='principalId'][data-majorcode]").each(function(){
			majorDuty = {};
			majorDuty.majorCode = $(this).data("majorcode");
			majorDuty.principalId = $(this).val();
			majorDuties.push(majorDuty);
		});
		datas.yieldMajorDuties = majorDuties;
		
		jQuery.ajax({
			type : "POST",
			url : context+"/admin/yield/scheme/ajax/principalSave",
			data : JSON.stringify(datas),
			dataType : "json",
	        contentType : "application/json",
			success : function(data) {
				if(data.flag == "true"){
					loadPrincipalView();
		 		}else{
		 			$.jalert({"jatext":data.msg});
		 		}
			}
		});
	}
}

/**加载各专业部门负责人会签查看*/
function loadPrincipalView(){
	var schemeId = $("input[name='id']").val();
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/yield/scheme/ajaxhtml/principalView/"+schemeId,
		dataType : "text",
		success : function(data) {
			$("#principal-info-div").html(data);
		},
		complete: function(XMLHttpRequest, textStatus){

		}
	});
}