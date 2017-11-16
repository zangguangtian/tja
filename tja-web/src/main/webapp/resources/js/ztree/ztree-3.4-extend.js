	var treeElemId = "orgTree";
	 selectOrg = function(callback){
		 layer.open({
			    type: 2,
				shade: [0.5, "#393D49"],
				closeBtn: 2,
				title: "选择部门", //不显示标
				area: ["300px", "400px"],
				content:context+"/admin/hr/org/toselect",
				btn: ["确定"],
				yes:function(index, layero){
					 var body = layer.getChildFrame('body', index);
					var data ={};
					data.id=body.find("input[id='node_orgId']").val();
					data.name=body.find("input[id='node_orgName']").val();
					if(typeof callback === "function"){
						callback(data);
					}
					layer.closeAll();
				}
			});
	 }

	 selectStaff = function(callback,openType){
		 layer.open({
		    type: 2,
			shade: [0.5, "#393D49"],
			closeBtn: 2,
			title: "选择人员", //不显示标
			area: ["1000px", "600px"],
			content:context+"/admin/hr/staff/toselectstaff?openType="+openType,
			btn: ["确定"],
			yes:function(index, layero){
				var data = new Array();
				var body = layer.getChildFrame('body', index);
				if(openType == 'checkbox'){
					jQuery.each(body.find("ul#orgStaffList-ul li"),function(index,item){
						var orgStaff = {};
						var _this = $(item);
						var _input = _this.find("input[type='hidden']");
						orgStaff.id = _input.data("id");
						orgStaff.name = _input.data("name");
						orgStaff.orgname = _input.data("orgname");
						orgStaff.entrydate = _input.data("entrydate");
						orgStaff.sex = _input.data("sex");
						orgStaff.office = _input.data("office");
						orgStaff.orgid = _input.data("orgid");
						orgStaff.jobnum = _input.data("jobnum");
						orgStaff.clublevelcode = _input.data("clublevelcode");
						data.push(orgStaff);
					});
				}else if(openType == 'radio'){
					var _input = body.find("#orgStaffList input[type='radio']:checked");
					var orgStaff = {};
					orgStaff.id = _input.data("id");
					orgStaff.name = _input.data("name");
					orgStaff.orgname = _input.data("orgname");
					orgStaff.entrydate = _input.data("entrydate");
					orgStaff.sex = _input.data("sex");
					orgStaff.office = _input.data("office");
					orgStaff.orgid = _input.data("orgid");
					orgStaff.jobnum = _input.data("jobnum");
					orgStaff.clublevelcode = _input.data("clublevelcode");
					data.push(orgStaff);
				}
				if(typeof callback === "function"){
					callback(data);
				}
				layer.closeAll();
			}
		});
	 }
	 
	 
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
			check: {
				enable: false,
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
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
	};
	
	$(document).ready(function() {
		initLoadTree();
    });
	function initLoadTree(){
		if(typeof rootId != 'undefined' && typeof rootParentId != 'undefined'){
			var settingType = $("#settingType").val();
			var url = context+"/admin/hr/org/ajax/loadnode?id="+rootId;
			$.getJSON(url, function(data) {
				var zNodes = {"id":rootId, "name":"同济人建筑设计公司", "pId":rootParentId, "open":"true", "icon":context+"/resources/images/jzTree/1_open.png", "children":data};
				console.log(zNodes);
			    if(settingType == "radio"){//选择人员类型1
					$.fn.zTree.init($("#orgTree"), checkuserSetting, zNodes);
				}else if(settingType == "checkbox"){//选择人员类型2
					$.fn.zTree.init($("#orgTree"), checkuserSetting, zNodes);
				}else if(settingType == "random"){//选择人员类型3
					$.fn.zTree.init($("#orgTree"), checkuserSetting, zNodes);
				}else{
					$.fn.zTree.init($("#orgTree"), checkSetting, zNodes);
				}
				
				if(rootId == "PRO0001I"){
					var zTree = $.fn.zTree.getZTreeObj("orgTree");
					var node = zTree.getNodes()[0];  
					zTree.selectNode(node);
					//加载组织员工
					searchOrgs(null, treeElemId, node);
				}
			});
		}
	}

	/**
	 * 异步加载正常结束后，如果rootId为0，则选中当前用户所属组织，并加载组织的用户
	 * @param event
	 * @param treeId
	 * @param treeNode
	 * @param msg
	 */
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		if(rootId == "PRO0001I"){
			var treeObj = getTreeObj(treeId);
			var node = treeObj.getNodeByParam("id", defaultId, null);
			treeObj.selectNode(node);
			searchOrgs(null, treeElemId, node);
		}
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
//			sNode.isParent = true;
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

	//加载组织下的人员
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
						.append(jQuery('<td nowrap="nowrap"/>').text(this.name))
						.append(jQuery('<td nowrap="nowrap"/>').text(this.orgName==null?"":this.orgName))
						.append(jQuery('<td nowrap="nowrap"/>').text(this.designLevel==null?"":this.designLevel))
						.append(jQuery('<td nowrap="nowrap"/>').text(this.sex == null?"":(this.sex == "1"?"男":"女")))
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
		jQuery("div#nav_resource").find("input[id='node_orgId']").val(treeNode.id);
		jQuery("div#nav_resource").find("input[id='node_orgName']").val(treeNode.name);
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