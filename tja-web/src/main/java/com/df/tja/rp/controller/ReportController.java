/**
 * 项目名称:tja-web
 *
 * com.df.tja.rp.controller
 *
 * ReportController.java
 * 
 * 2017年10月11日-上午10:26:55
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.rp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.df.framework.base.controller.BaseController;

/**
 * <p>ReportController</p>
 * 
 * <p>描述：统计报表</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月11日 上午10:26:55</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

@Controller
@RequestMapping("/admin/rp")
public class ReportController extends BaseController {

    /**
     * <p>描述 : 施工图项目周报</p>
     */
    @RequestMapping(value = "/week")
    public String week() {
        return "/tjad/rp/rp_week";
    }
}
