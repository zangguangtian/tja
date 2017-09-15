/**
 * 项目名称:tja-web
 *
 * com.df.tja.controller
 *
 * DashBoard.java
 * 
 * 2017年9月12日-下午6:25:42
 *
 * 2017 TabZhu-版权所有 
 */

package com.df.tja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.df.framework.base.controller.BaseController;

/**
 * <p>DashBoard</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月12日 下午6:25:42</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashBoard extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/tjad/dashboard/dashboard";
    }
}
