/**
 * Created by wang.changjiu on 2016/8/2.
 */
jQuery(document).ready(function(){

	jQuery(document).on("click","#selectUser tbody input[type='"+openType+"']",function(){
		chooseStaff(this);
	});
	
	jQuery(document).on("click","#checkAll",function(){
		if($(this)[0].checked){
			chooseAllStaff(this,"#selectUser");
		}
	});
	
	jQuery(document).on("click","#orgStaffList-ul li span:nth-child(2)",function(){
		deteteStaff(this);
	});
	
});

function  chooseStaff(obj) {
    var $copyObj = $(obj).clone();
    $copyObj[0].type = "hidden";
    if($(obj)[0].checked){
        //选中
        var data = $(obj).attr("data-val");
  	      data = data.replace(/=/gm,'":"');
		  data = data.replace(/,/gm,'","');
		  data = data.replace('{','{"');
		  data = data.replace('}','"}');
		  data = data.replace(/[ ]/g,"");
		  data = eval("("+data+")");
        
		  var name =  data.name;
		  var id =  data.id;
        
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
	jQuery.each($(className).find("tbody tr"),function(index,item){
		var currTr = $(item);
		var inputCheckbox = currTr.find("td:eq(0)").find("input[type='checkbox']");
		chooseStaff(inputCheckbox);
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
