/**
 * Created by zhoufa on 2016/8/2.
 */
jQuery(document).ready(function(){

    $("#orgStaffList-ul li").find("span:nth-child(2)").off().on("click", function(){
        deteteStaff(this);
    });

    jQuery("#confirmBtn").on("click", function(){
        var selectObj = new Array();
        if ("checkbox" === openType) {
            $("#orgStaffList-ul li").each(function () {
                selectObj.push($(this).find("input[type=hidden]"));
            });
        } else {
            var $radioSelected = jQuery("input[type=radio]:checked");
            if ($radioSelected.length>0) {
                selectObj.push(jQuery("input[type=radio]:checked"));
            }
        }

        var length = selectObj.length;
        if (length > 0) {
            var $callMethod = jQuery("#callMethod").val();
            var targetName = jQuery("#targetName").val();
            eval("opener."+$callMethod+"(selectObj, targetName)");
            window.close();
        }else {
            alert("请选择相关人员");
        }
    })

    jQuery("#closeBtn").on("click", function(){
        window.close();
    })

    jQuery("#btnSearch").on("click", search);//在树结构页面查询
});

$(document).keypress(function(e) {
    // 回车键事件
    if(e.which == 13) {
        search();
    }
});

/**
 *根据名字查询
 */
search = function() {
	var searchtype = "org";
	if(jQuery("#treeNodeId").length == 0){	//说明是按设计定级查询
		searchtype = "dlevel";
	}
	
    var name = jQuery("#name").val();
    jQuery("#frmSearch").empty().append(jQuery('<input type="hidden" name="name"/>').val(name));
    
    
    var orgName = jQuery("#orgName").val();
    if(orgName != undefined && orgName != null && orgName !=""){
    	jQuery("#frmSearch").append(jQuery('<input type="hidden" name="orgName"/>').val(orgName));
    	if(searchtype == "org"){
    		//查询条件中输入了组织名称时，左边树结构选中根结点
    		jQuery("#treeNodeId").val("0");
    	}
    }
    var settingType = $("#settingType").val();
    //判断不要发送多次，用遮罩层判断
    var length = jQuery("#loadingDiv").length;
    if(length == 0){
    	if(searchtype == "org"){
    		var orgId = jQuery("#treeNodeId").val().trim();
    		if(orgId && orgId !="" && length==0){
    	        jQuery.ajax({
    	            type : "POST",
    	            url : context+"/admin/hr/org/ajax/searchstaff/"+orgId,
    	            data:jQuery("#frmSearch").serialize(),
    	            dataType : "json",
    	            beforeSend: function(){
    	                addcloud();
    	            },
    	            success : function(data, status) {
    	                jQuery("#orgStaffList").empty();
    	                var $data = eval(data);
    	                appendContent($data, settingType);
    	            },
    	            error : function(req, status, ex) {
    	                alert("获取组织人员列表出错!");
    	            },
    	            complete: function(XMLHttpRequest, textStatus){
    	                removecloud();
    	            }
    	        });
    	    }
    	}else{
			//设计定级、专业选择selectType
			var selectType = jQuery("#selectType").val();
			var code = jQuery("#treeNodeCode").val().trim();

			console.log(selectType);
    		jQuery("#frmSearch")
				.append(jQuery("<input/>").attr({"type": "hidden", "name": "code"}).val(code))
				.append(jQuery("<input/>").attr({"type": "hidden", "name": "selectType"}).val(selectType));
    		jQuery.ajax({
    			type : "POST",
    			url : context+"/admin/hr/ajax/searchstaff",
    			data:jQuery("#frmSearch").serialize(),
    			dataType : "json",
    			beforeSend: function(){
    				addcloud();
    			},
    			success : function(data, status) {
    				var $data = eval(data);
    				appendContent($data, settingType);
    			},
    			error : function(req, status, ex) {
    				alert("获取组织人员列表出错!");
    			},
    			complete: function(XMLHttpRequest, textStatus){
    				removecloud();
    			}
    		});
    	}
    }
}

function  chooseStaff(obj) {
    var $copyObj = $(obj).clone();
    $copyObj[0].type = "hidden";
    if($(obj)[0].checked){
        //选中
        var name = $(obj).data("name");
        var id =  $(obj).data("id");
        var  isSame =  seachNode($("#orgStaffList-ul li"),id,'0');
        if(isSame == '1'){
            var $li = jQuery("<li/>")
                .append(
                    jQuery("<span/>").text(name))
                .append(
                    jQuery("<span/>").attr({"attachId": id, "class": "attach-del-icon"}))
                .append($copyObj);
            jQuery("#orgStaffList-ul").append($li);
        }
    }
}

//选中所有的staff
function chooseAllStaff(obj,className){
	addcloud();
	jQuery.each($(className).find("tr"),function(index,item){
		var currTr = $(item);
		var inputCheckbox = currTr.find("td:eq(0)").find("input[type='checkbox']");
		inputCheckbox[0].checked='checked';
		var $copyObj =inputCheckbox.clone();
	    $copyObj[0].type = "hidden";
		var name = inputCheckbox.data("name");
		var id = inputCheckbox.data("id");
	     var  isSame =  seachNode($("#orgStaffList-ul li"),id,'0');
	        if(isSame == '1'){
	            var $li = jQuery("<li/>")
	                .append(
	                    jQuery("<span/>").text(name))
	                .append(
	            jQuery("<span/>").attr({"attachId": id, "class": "attach-del-icon"})).append($copyObj);
	            jQuery("#orgStaffList-ul").append($li);
	        }
	});
   removecloud();
}

//查找相同的节点
function seachNode(obj,id,flag) {
    var f = 1;
    jQuery.each(obj,function(index,item){
        var $item = $(item);
        var attachId =$($item[0]).find("span:nth-child(2)").attr("attachId");
        if(id == attachId && flag == '1'){
            $($item[0]).remove();
            return;
        }else if(id == attachId && flag == '0'){
            f=0;
            return flag;
        }
    });
    return f;
}

//删除用户
function deteteStaff(obj) {
    $(obj).parent().remove();
}