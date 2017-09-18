var treeElemId = "orgTree";

var editSetting = {
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	edit: {
		enable: true,
		editNameSelectAll: false,
		showRenameBtn: showRenameBtn,
		showRemoveBtn: showRemoveBtn
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	async: {
		enable:true,
		dataType:"json",
		type:"post",
		url:context+"/admin/hr/org/ajax/loadnode",
		autoParam:["id"]
	},
	callback: {
		onClick: searchOrgs,
		beforeRemove: removeOrg,
		onAsyncSuccess: zTreeOnAsyncSuccess
	}
};
var checkSetting = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	async: {
		enable:true,
		dataType:"json",
		type:"post",
		url:context+"/admin/hr/org/ajax/loadnode",
		autoParam:["id"]
	},
	callback: {
		onCheck: orgOnCheck,
		onAsyncSuccess: zTreeOnAsyncSuccess
	}
};

var checkuserSetting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		async: {
			enable:true,
			dataType:"json",
			type:"post",
			url:context+"/admin/hr/org/ajax/loadnode",
			autoParam:["id"]
		},
		callback: {
			onClick: searchOrgs,
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
};

var defaultId = currUserOrgId;
$(document).ready(function() {
	var settingType = $("#settingType").val();
	var url = context+"/admin/hr/org/ajax/loadnode?id="+rootId;
	$.getJSON(url, function(data) {
		var zNodes = {"id":rootId, "name":"易兰国际", "pId":rootParentId, "open":"true", "icon":context+"/resources/images/jzTree/1_open.png", "children":data};
		defaultId = currUserOrgId;			//默认选择节点ID
		if(settingType == "edit"){
			$.fn.zTree.init($("#orgTree"), editSetting, zNodes);
			defaultId = data[0].id;
		}else if(settingType == "radio"){//选择人员类型1
			$.fn.zTree.init($("#orgTree"), checkuserSetting, zNodes);
		}else if(settingType == "checkbox"){//选择人员类型2
			$.fn.zTree.init($("#orgTree"), checkuserSetting, zNodes);
		}else if(settingType == "random"){//选择人员类型3
			$.fn.zTree.init($("#orgTree"), checkuserSetting, zNodes);
		}else{
			$.fn.zTree.init($("#orgTree"), checkSetting, zNodes);
		}
		
		if(rootId == "0"){
			//asyncDefaultNode(defaultId);
			var zTree = $.fn.zTree.getZTreeObj("orgTree");
			var node = zTree.getNodes()[0];  
			zTree.selectNode(node);
			//加载组织员工
			searchOrgs(null, treeElemId, node);
		}else{
			var treeObj = getTreeObj(treeElemId);
			var node = treeObj.getNodeByParam("id", defaultId, null);
			treeObj.selectNode(node);
			searchOrgs(null, treeElemId, node);
		}
		
	});
});

/**
 * 异步展开到默认树节点（当前用户所属组织）
 */
function asyncDefaultNode(defaultId){
	var treeObj = getTreeObj(treeElemId);
	jQuery.ajax({
		type : "GET",
		url : context+"/admin/hr/org/ajax/asyncdefault/"+defaultId,
		dataType : "json",
		success : function(data, status) {
			var $data = eval(data);
			if($data.success=="true"){
				var node = null;
				jQuery.each($data.orgs, function(index){
					node = treeObj.getNodeByParam("id", this.id, null);
					treeObj.expandNode(node, true, false, true, false);
				});
			}else{
				alert("加载组织出错!");
			}
		},
		error : function(req, status, ex) {
			alert("加载组织出错!");
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

/**
 * 异步加载正常结束后，如果rootId为0，则选中当前用户所属组织，并加载组织的用户
 * @param event
 * @param treeId
 * @param treeNode
 * @param msg
 */
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	if(rootId == "0"){
		var treeObj = getTreeObj(treeId);
		var node = treeObj.getNodeByParam("id", defaultId, null);
		treeObj.selectNode(node);
		searchOrgs(null, treeElemId, node);
	}
};

/**
 * 添加鼠标
 * @param treeId
 * @param treeNode
 */
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='添加' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) {
		btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj(treeElemId);
			zTree.addNodes(treeNode, {pId:treeNode.id, name:"新组织", "icon":context+"/resources/images/jzTree/folder.png",});
			var level = treeNode.level;
			var pId = treeNode.id;
			//添加节点的同时保存数据库
			jQuery("#addOrg #level").val(level);
			jQuery("#addOrg #parentId").val(pId);
			jQuery("#addOrg #orgName").val("新组织");
			save(treeNode);
			return false;
		});
	}
	
};


function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};

/**
 * 获取树对象
 * @param treeId
 * @returns
 */
function getTreeObj(treeId){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	return treeObj;
}

/**
 * 获得当前选中的节点
 */
function getCurrNode(treeId){
	var treeObj = jQuery.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getSelectedNodes();//得到选中的节点集
	if (nodes.length==0) {//如果没有选中的节点,就取树的所有节点作为节点集
		nodes = treeObj.getNodes();
	}
	return nodes[0];//取节点集的第一个节点
}

/**
 * 获取当前选中节点的level值
 */
function currNodeLevel(treeId){
	var node = getCurrNode(treeId);
	return node.level;
}

/**
 * 获取当前选中节点的ID值
 */
function currNodeId(treeId){
	var node = getCurrNode(treeId);
	return node.id;
}

/**
 * 获取当前选中节点的ID值
 */
function currNodeName(treeId){
	var node = getCurrNode(treeId);
	return node.name;
}


/**
 * 获取当前选中节点的父节点名称
 */
function currNodePName(treeId){
	var node = getCurrNode(treeId);
	node = node.getParentNode();
	return node.name;
}


/**
 * 获取当前选中节点的父节点对象
 */
function currNodePOBJ(treeId){
	var node = getCurrNode(treeId);
	node = node.getParentNode();
	return node;
}

/*刷新树结构*/
function refreshNode(type, treeNode) {
	var treeObj = jQuery.fn.zTree.getZTreeObj(treeElemId);
	var sNode = getCurrNode(treeElemId);
	if(!sNode.isParent){//如果节点不是父结点,就将此节点设置为父节点
//		sNode.isParent = true;
		jQuery("#"+sNode.tId+"_remove").remove();
	}
	if(type == 1){
		/*var id = currNodeId(treeElemId);
		var node = treeObj.getNodeByParam("id", id);*/
		var nodeName = jQuery("#frmAdd #orgName").val();
		sNode.name = nodeName; 
		treeObj.updateNode(sNode);
		treeObj.expandNode(sNode, true, true, true);
	}
	
	if(treeNode){//当不为空的时候
		//异步加载节点的子节点,并展开此节点,刷新的是当前操作的节点，有可能不是选中的节点，故不能异步刷新选中节点
		treeObj.reAsyncChildNodes(treeNode, "refresh");
		treeObj.selectNode(treeNode);
	}
	//加载右边子组织列表
	searchOrgs(null, treeElemId, sNode);
}

/**
 * 通过ajax获取子组织列表
 */
function searchOrgs(event, treeId, treeNode){
	var id = treeNode.id;
	jQuery("#treeNodeId").val(id);//为了页面查询传一个当前选中节点
	if(id == null && id == ""){
		alert("请先选择一个左边的组织!");
		return;
	}
	/*id = currNodeId(treeId);
	if(id == 0){//如果为一级节点的话，就选中第一个子节点,并设置为选中状态
		id = treeNode.children[0].id;
		var treeObj = getTreeObj(treeElemId);
		var node = treeObj.getNodeByParam("id", id);
		treeObj.selectNode(node);
	}*/
	var nodeName = currNodeName(treeId);
	//加载组织
	if(jQuery(".next-div div#choose-one").hasClass("currents")){
		loadOrg(id);
	}else{//加载组织中的员工
		orgPeopleList(id);
	}
}

/**
 * 获取组织列表
 * @param orgId
 */
function orgList(orgId){
	jQuery.ajax({
		type : "POST",
		url : context+"/admin/hr/org/ajax/search/"+orgId,
		dataType : "json",
		success : function(data, status) {
			jQuery("#orglist").empty();
			var $data = eval(data);
			jQuery.each($data, function(index){
				jQuery("#orglist").append(
					jQuery('<tr/>')
					.append(jQuery('<td nowrap="nowrap" width="40"/>').text(index + 1))
					.append(jQuery('<td/>').text(this.orgCode))
					.append(jQuery('<td/>').append(jQuery('<a id="'+this.id+'" href="javascript:void(0);" class="link-a"/>').text(this.orgName)))
					.append(jQuery('<td/>').text(this.orgPic==null?"":this.orgPic))
					.append(jQuery('<td/>').text(this.typeName==null?"":this.typeName))
					.append(jQuery('<td/>').text(this.sort==null?"":this.sort))
				);
			});
		},
		error : function(req, status, ex) {
			alert("获取组织列表出错!");
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

/**
 * 获取组织信息
 * @param orgId
 */
function loadOrg(orgId){
	jQuery("#frmAdd").show();

	jQuery.ajax({
		type : "POST",
		url : context + "/admin/hr/org/ajax/toedit/"+orgId,
		dataType : "json",
		success : function(data, status) {

			var $data = eval(data);
			if($data.id != 0){
				jQuery("#frmAdd #id").val($data.id);
				jQuery("#frmAdd #orgCode").val($data.orgCode);
				jQuery("#frmAdd #orgName").val($data.orgName.trim());
				jQuery("#frmAdd select[name='orgType']").val($data.orgType);
				var pic = $data.orgPic;
				if(pic){
					pic = pic.trim();
				}
				jQuery("#frmAdd #orgPic").val(pic);
				jQuery("#frmAdd #orgPicName").val($data.orgPicName);

				jQuery("#frmAdd #orgLeader").val($data.orgLeader);
				jQuery("#frmAdd #orgLeaderName").val($data.orgLeaderName);

				var orgStatusConvert = 1;
				if($data.orgStatus == true){
					orgStatusConvert = 1;
				}else{
					orgStatusConvert = 0;
				}
				jQuery("#frmAdd #orgStatus").val(orgStatusConvert);
				if($data.startDate == null){
					jQuery("#frmAdd #startDate").val(new Date().format("yyyy-MM-dd") );
				}else{
					jQuery("#frmAdd #startDate").val($data.startDate);
				}
				
				jQuery("#frmAdd #endDate").val($data.endDate);
				
				jQuery("#frmAdd #sort").val($data.sort);
				var pName = currNodePName(treeElemId);
				jQuery("#pName").html(pName);
				jQuery("#frmAdd #remark").val($data.remark);
				
			}else{//当选择跟节点的时候，把组织信息的form表单隐藏
				jQuery("#frmAdd").hide();
			}
		},
		error : function(req, status, ex) {
			alert("加载出错!");
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

function orgPeopleList(orgId){
	var settingType = $("#settingType").val();
	var loadUser = $("#loadUser").val();
	//只有需要加载用户的时候才加载
	if(loadUser == "1"){
		jQuery.ajax({
			type : "POST",
			url : context+"/admin/hr/org/ajax/searchstaff/"+orgId,
			data:jQuery("#userForm").serialize(),
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

function appendContent($data, settingType){
	if(settingType == "checkbox"){
		jQuery("#chooseAll").removeAttr("checked");
	}
	jQuery("#orgStaffList").empty();
	jQuery.each($data, function(index){
		if(settingType == "radio" || settingType == "checkbox"){
			jQuery("#orgStaffList").append(
				jQuery('<tr/>')
					.append(jQuery('<td nowrap="nowrap"/>')
						.append(jQuery('<input/>').attr(
								{"type":settingType,"name":"selectStaff", "data-id":this.id, "data-name":this.name,
									"data-orgName":this.orgName, "data-entryDate":this.entryDate, "data-sex":this.sex, "data-office":this.office, "data-jobTitle":this.jobTitle,
									"data-school":this.school, "data-endDate":this.endDate, "data-designLevel":this.designLevel,
									"data-designSubLevel":this.designSubLevel, "data-clubLevel":this.clubLevel, "data-clubSubLevel":this.clubSubLevel,
									"data-orgId": this.orgId, "data-ratingValue":this.ratingValue, "data-jobNum": this.jobNum, "data-clubLevelCode":this.clubLevelCode, "data-designLevelCode": this.designLevelCode})))
					/*.append(jQuery('<td nowrap="nowrap"/>').text(index + 1))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.jobNum))*/
					.append(jQuery('<td nowrap="nowrap"/>').text(this.name))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.orgName==null?"":this.orgName))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.designLevel==null?"":this.designLevel))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.ratingValue==null?"":this.ratingValue))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.sex == "1"?"男":"女"))
			);
		}else if(settingType == "random"){
			jQuery("#orgStaffList").append(
				jQuery('<li/>')
					.append(jQuery('<input/>').attr(
							{"id": "random"+index,"type":"radio","name":"random", "data-id":this.id, "data-name":this.name, 
								"data-orgName":this.orgName, "data-entryDate":this.entryDate, "data-sex":this.sex, 
								"data-office":this.office, "data-jobTitle":this.jobTitle, "data-school":this.school, "data-endDate":this.endDate, "data-designLevel":this.designLevel,
								"data-designSubLevel":this.designSubLevel, "data-clubLevel":this.clubLevel, "data-clubSubLevel":this.clubSubLevel,
								"data-orgId": this.orgId, "data-ratingValue":this.ratingValue, "data-jobNum": this.jobNum, "data-clubLevelCode":this.clubLevelCode, "data-designLevelCode": this.designLevelCode,"data-mobile":this.mobile}))
					.append(jQuery('<label/>').attr({"for": "random"+index}).text(this.name)));
		}else{
			jQuery("#orgStaffList").append(
				jQuery('<tr/>')
					.append(jQuery('<td nowrap="nowrap"/>').text(index + 1))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.jobNum))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.name))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.sex == "1"?"男":"女"))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.designLevel==null?"":this.designLevel))
					.append(jQuery('<td nowrap="nowrap"/>').text(this.clubLevel==null?"":this.clubLevel))
			);
		}
	});

	if(jQuery("#treeNodeId").length > 0){
		//选中节点
		var treeObj = getTreeObj(treeElemId);
		var id = jQuery("#treeNodeId").val().trim();
		var node = treeObj.getNodeByParam("id", id, null);
		treeObj.selectNode(node, false);
	}
}

/**
 * 通过ajax在保存
 */
function save(treeNode){
	jQuery.ajax({
		type : "POST",
		url : context + "/admin/hr/org/ajax/save",
		data : jQuery("#addOrg").serialize(),
		dataType : "json",
		success : function(data, status) {
			var $data = eval(data);
			if($data.status == "true"){
				refreshNode(0, treeNode);
			}else{
				alert($data.msg);
			}
		},
		error : function(req, status, ex) {
			alert("添加出错!");
		},
		complete: function(XMLHttpRequest, textStatus){
			/*jQuery(".btn-cancle").click();*/
		}
	});
}

/**
 * 删除组织
 * @param event
 * @param treeId
 * @param treeNode
 */
function removeOrg(treeId, treeNode) {
	var flag = false;
	if (window.confirm("确定删除？\r\n\r点[确定]：执行删除操作\r\n\r点[取消]：放弃删除操作")) {
		var id = treeNode.id;
		jQuery.ajax({
			type : "POST",
			url : context + "/admin/hr/org/ajax/remove/" + id,
			dataType : "json",
			success : function(data, status) {
				if (data.status) {
					flag = true;
				}
				alert(data.msg);
			},
			error : function(req, status, ex) {
				alert("删除组织出错!");
			},
			complete : function(XMLHttpRequest, textStatus) {
				removeComplete(treeId, treeNode);
				return flag;
			}
		});
	}else{
		return flag;
	}
}

/**
 * ajax删除组织组织节点完成后
 * @param treeId
 * @param treeNode
 */
function removeComplete(treeId, treeNode){
	var treeObj = jQuery.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getSelectedNodes();//得到选中的节点集
	if(nodes.length == 0){//当前选中的节点数为0，则说明删除的是选中的节点
		var node = null;
		var parentNode = treeNode.getParentNode();
		//如果当前选中的节点没有兄弟节点
		if(parentNode.children != null && parentNode.children.length == 0){
			node = parentNode;
		}else{
			node = parentNode.children[parentNode.children.length - 1];
		}
		var treeObj = getTreeObj(treeElemId);
		treeObj.selectNode(node);
		//删除后查询列表
		if(node != null){
			searchOrgs(null, treeElemId, node);
		}
	}
}

/**
 * 始终不显示修改按钮
 * @param treeId
 * @param treeNode
 * @returns {Boolean}
 */
function showRenameBtn(treeId, treeNode) {
	return false;
}

/**
 * 有条件显示是否显示删除按钮
 * @param treeId
 * @param treeNode
 * @returns
 */
function showRemoveBtn(treeId, treeNode) {
	return !treeNode.isParent;
}

/**
 * 节点选中后的回调方法
 * @param event
 * @param treeId
 * @param treeNode
 */
function orgOnCheck(event, treeId, treeNode) {
	if(treeNode.checked){
		var $orgId = jQuery('<input type="hidden" id="orgId"/>').val(treeNode.id);
		var $orgName = jQuery('<input type="hidden" id="orgName"/>').val(treeNode.name);
		jQuery("#nav_resource").append($orgId).append($orgName);
	}else{
		jQuery("#nav_resource #orgId").remove();
		jQuery("#nav_resource #orgName").remove();
	}
};

/**
 * 添加遮罩层
 */
function addcloud() {
    var bodyWidth = $(document).width();
    var bodyHeight = $(document).height();
    var bgObj = document.createElement("div" );
    bgObj.setAttribute( 'id', 'bgDiv' );
    bgObj.style.position = "absolute";
    bgObj.style.top = "0";
    bgObj.style.background = "#000000";
    bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75" ;
    bgObj.style.opacity = "0.5";
    bgObj.style.left = "0";
    bgObj.style.width = bodyWidth + "px";
    bgObj.style.height = bodyHeight + "px";
    bgObj.style.zIndex = "10000"; //设置它的zindex属性，让这个div在z轴最大，用户点击页面任何东西都不会有反应|
    document.body.appendChild(bgObj); //添加遮罩
    var loadingObj = document.createElement("div");
    loadingObj.setAttribute( 'id', 'loadingDiv' );
    loadingObj.style.position = "absolute";
    loadingObj.style.top = bodyHeight / 2 - 32 + "px";
    loadingObj.style.left = bodyWidth / 2 + "px";
    loadingObj.style.background = "url("+context+"/resources/images/colorbox/loading.gif)" ;
    loadingObj.style.width = "32px";
    loadingObj.style.height = "32px";
    loadingObj.style.zIndex = "10000"; 
    document.body.appendChild(loadingObj); //添加loading动画-
}

/**
 * 移除遮罩层
 */
function removecloud() {
    $( "#loadingDiv").remove();
    $( "#bgDiv").remove();
}