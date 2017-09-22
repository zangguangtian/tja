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
