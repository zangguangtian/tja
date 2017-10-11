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

    /**
     * <p>描述 : 施工配合项目月报</p>
     */
    @RequestMapping(value = "/month")
    public String month() {
        return "/tjad/rp/rp_month";
    }

    /**
     * <p>描述 : 产值月报汇总</p>
     */
    @RequestMapping(value = "/ymonth")
    public String ymonth() {
        return "/tjad/rp/rp_ymonth";
    }

    /**
     * <p>描述 : 产值月报-按状态</p>
     */
    @RequestMapping(value = "/ysmonth")
    public String ysmonth() {
        return "/tjad/rp/rp_ysmonth";
    }

    /**
     * <p>描述 : 年度结算产值总表</p>
     */
    @RequestMapping(value = "/year")
    public String year() {
        return "/tjad/rp/rp_year";
    }

    /**
     * <p>描述 : 部门结算产值总表</p>
     */
    @RequestMapping(value = "/dept")
    public String dept() {
        return "/tjad/rp/rp_dept";
    }

    /**
     * <p>描述 : 部门结算产值明细</p>
     */
    @RequestMapping(value = "/deptDetial")
    public String deptDetial() {
        return "/tjad/rp/rp_dept_detial";
    }

    /**
     * <p>描述 : 各阶段专业交叉-产值</p>
     */
    @RequestMapping(value = "/stageMajor")
    public String stageMajor() {
        return "/tjad/rp/rp_stage_major";
    }

    /**
     * <p>描述 : 各阶段产值结算统计</p>
     */
    @RequestMapping(value = "/stage")
    public String stage() {
        return "/tjad/rp/rp_stage";
    }
}
