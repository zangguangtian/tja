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

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.df.framework.base.controller.BaseController;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.service.IReportService;

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

    @Autowired
    private IReportService reportService;

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
    public String deptDetial(@ModelAttribute("page") Pagination page, Model model, String orgId, String periodId,
                             String orgName, HttpServletRequest request) {
        if (page == null || page.getPageNo() == 0) {
            page = new Pagination(1);
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("orgId", orgId);
        param.put("periodId", periodId);
        param.put("orgName", orgName);
        reportService.queryYieldDeptDetial(page, param);
        model.addAttribute("page", page);
        model.addAllAttributes(param);
        return "/tjad/rp/rp_dept_detial";
    }

    @RequestMapping(value = "/deptDetial/export")
    public void exportDeptDetial(String orgId, String periodId,
                                 HttpServletResponse response, HttpServletRequest request) {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("orgId", orgId);
            param.put("periodId", periodId);
            HSSFWorkbook workbook = reportService.createExportDeptDetial(param, null);

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = format.format(new Date());
            String filename = "部门结算产值明细_" + time + ".xls";
            // 清空response
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                                                      + new String(filename.getBytes("GBK"), "ISO-8859-1") + "\"");
            OutputStream output = response.getOutputStream();
            workbook.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            logger.error("导入出错:" + e);
        }
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
