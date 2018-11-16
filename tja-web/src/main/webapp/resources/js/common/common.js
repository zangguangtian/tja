var FL_WIN_BORDER_HEIGHT = 28;

function getWinCenterX(w) {
	return (window.screen.width - w) / 2;
}

function getWinCenterY(h) {
	return (window.screen.height - h - FL_WIN_BORDER_HEIGHT) / 2;
}

/**
 * 
 * 打开窗口，并将其居中。注意：如果跨站点调用则会出错。
 * 
 * @param url 打开窗口的URL
 * @param wName 打开窗口名称
 * @param w 宽
 * @param h 高
 * @param scrollbars 是否可滚动
 * @param bResizable 是否可重设大小
 * @returns
 */
function openWindow(url, wName, w, h, scrollbars, bResizable) {
	var newwindow = openWindowEx(url, wName, w, h, getWinCenterX(w), getWinCenterY(h)/2, scrollbars, bResizable);

	if(newwindow != null) {
		newwindow.focus();
	}

	return newwindow;
}

/**
 * 
 * 打开窗口，但不改变窗口位置和大小。注意：从而允许跨站点调用。
 * 
 * @param url 打开窗口的URL
 * @param wName 打开窗口名称
 * @param w 宽
 * @param h 高
 * @param top 
 * @param left 
 * @param scrollbars 是否可滚动
 * @param bResizable 是否可重设大小
 * @returns
 */
function openWindowEx(url, wName, w, h, left, top, scrollbars, bResizable) {
	var newwindow;
	if(w==null || w==0){w=400}
	if(h==null || h==0){h=370}
	var feature="width="+w+",height="+h;
	if(left != null){
		feature+=",left="+left;
	}
	if(top != null){
		feature+=",top="+top;
	}
	if(scrollbars!=null && scrollbars){feature+=",scrollbars=yes"}
	if(bResizable!=null && bResizable) {
		feature+=",resizable=yes"
	} else {
		feature+=",resizable=no"
	}
	return window.open(url,wName,feature);
}

String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {    
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                	var reg= new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}

function numToCny(money) {
	var cnNums = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); //汉字的数字
	  var cnIntRadice = new Array("", "拾", "佰", "仟"); //基本单位
	  var cnIntUnits = new Array("", "万", "亿", "兆"); //对应整数部分扩展单位
	  var cnDecUnits = new Array("角", "分", "毫", "厘"); //对应小数部分单位
	  var cnInteger = "整"; //整数金额时后面跟的字符
	  var cnIntLast = "元"; //整型完以后的单位
	  var maxNum = 999999999999999.9999; //最大处理的数字
	  var IntegerNum; //金额整数部分
	  var DecimalNum; //金额小数部分
	  var ChineseStr = ""; //输出的中文金额字符串
	  var parts; //分离金额后用的数组，预定义
	  if (money == "") {
	    return "";
	  }
	  money = parseFloat(money);
	  if (money >= maxNum) {
	    alert('超出最大处理数字');
	    return "";
	  }
	  if (money == 0) {
	    ChineseStr = cnNums[0] + cnIntLast + cnInteger;
	    return ChineseStr;
	  }
	  money = money.toString(); //转换为字符串
	  if (money.indexOf(".") == -1) {
	    IntegerNum = money;
	    DecimalNum = '';
	  } else {
	    parts = money.split(".");
	    IntegerNum = parts[0];
	    DecimalNum = parts[1].substr(0, 4);
	  }
	  if (parseInt(IntegerNum, 10) > 0) { //获取整型部分转换
	    var zeroCount = 0;
	    var IntLen = IntegerNum.length;
	    for (var i = 0; i < IntLen; i++) {
	      var n = IntegerNum.substr(i, 1);
	      var p = IntLen - i - 1;
	      var q = p / 4;
	      var m = p % 4;
	      if (n == "0") {
	        zeroCount++;
	      } else {
	        if (zeroCount > 0) {
	          ChineseStr += cnNums[0];
	        }
	        zeroCount = 0; //归零
	        ChineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
	      }
	      if (m == 0 && zeroCount < 4) {
	        ChineseStr += cnIntUnits[q];
	      }
	    }
	    ChineseStr += cnIntLast;
	    //整型部分处理完毕
	  }
	  if (DecimalNum != '') { //小数部分
	    var decLen = DecimalNum.length;
	    for (var i = 0; i < decLen; i++) {
	      var n = DecimalNum.substr(i, 1);
	      if (n != '0') {
	        ChineseStr += cnNums[Number(n)] + cnDecUnits[i];
	      }
	    }
	  }
	  if (ChineseStr == '') {
	    ChineseStr += cnNums[0] + cnIntLast + cnInteger;
	  } else if (DecimalNum == '') {
	    ChineseStr += cnInteger;
	  }
	  return ChineseStr;
}

var buttonLoading = '<div class="spinner">'
	  +'<div class="bounce1"></div>'
	  +'<div class="bounce2"></div>'
	  +'<div class="bounce3"></div>'
+'</div>';
//button 加载中效果
//param @$thisButton: 当前button Jquery对象，@btnText: null表示置灰 ,@isTimeout true 表示是否等待ajax返回结果
function buttonToLoadingMethod($thisButton,btnText,isTimeOut){
	var btnW = $thisButton.width();
	if(isTimeOut){
		$thisButton.attr('disabled','disabled').width(btnW).html(buttonLoading);
		setTimeout(function(){
			$thisButton.removeAttr('disabled').removeAttr('style').html(btnText);
		},300);
		return false;
	}
	if(btnText){
		$thisButton.removeAttr('disabled').removeAttr('style').html(btnText);
		return false;
	}
	$thisButton.attr('disabled','disabled').width(btnW).html(buttonLoading);
};