/**
 * 项目名称:df-sys-web
 *
 * com.df.framework.period.controller
 *
 * PeriodController.java
 * 
 * 2017年9月18日-下午4:45:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.oc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.exception.LogicalException;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.util.StringUtil;
import com.df.tja.domain.OcPeriodAdvanceFill;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.service.IPeriodService;

/**
 * <p>PeriodController</p>
 * 
 * <p>描述：期间管理</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午4:45:52</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/oc/period")
public class PeriodController extends BaseController {

    @Autowired
    IPeriodService periodService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/tjad/oc/period/period_list";
    }

    /**
     * <p>描述 : 编辑</p>
     * @param id 期间ID
     */
    @RequestMapping(value = "/{id}")
    public String edit(@PathVariable("id") String id, Model model) throws RuntimeException {
        if (!"0".equals(id)) {
            OcPeriodManage period = periodService.queryPeriod(id);
            model.addAttribute("period", period);
        }
        return "/tjad/oc/period/period_edit";
    }

    /**
     * <p>描述 : 保存</p>
     * @param period 期间实体
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute OcPeriodManage period) {
        Map<String, String> resultmap = new HashMap<String, String>();
        try {
            String periodId = periodService.createOrModifyPeriod(period);
            resultmap.put("periodId", periodId);
            resultmap.put("flag", "true");
            resultmap.put("msg", SAVE_SUCCESS);
        } catch (LogicalException e) {
            resultmap.put("flag", "false");
            resultmap.put("msg", e.getMessage());
            logger.error("", e);
        } catch (RuntimeException e) {
            resultmap.put("flag", "false");
            resultmap.put("msg", "保存失败");
            logger.error("", e);
        }
        return resultmap;
    }

    /**
     * <p>描述 : 提前上报</p>
     */
    @RequestMapping(value = "/advanceFill")
    public String advanceFill() {
        return "/tjad/oc/period/advance_fill";
    }

    /**
     * <p>描述 : 提前上报-列表</p>
     * @param advanceFill
     */
    @RequestMapping(value = "/advanceFill/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> advanceFillList(OcPeriodAdvanceFill advanceFill) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
            Pagination page = new Pagination();
            page.setPageNo(advanceFill.getPageIndex());
            page.setRowsPerPage(advanceFill.getPageSize());
            advanceFill.setPageIndex(null);
            advanceFill.setPageSize(null);

            List<OcPeriodAdvanceFill> list = periodService.queryAdvanceFillList(advanceFill, page);

            resultmap.put("data", list);
            resultmap.put("itemsCount", page.getTotalCount());
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultmap;
    }

    /**
     * <p>描述 : 提前上报-保存</p>
     * @param advanceFill
     */
    @RequestMapping(value = "/advanceFill/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> advanceFillSave(OcPeriodAdvanceFill advanceFill) {
        Map<String, String> resultmap = new HashMap<String, String>();
        try {
            if (StringUtil.isNotBlank(advanceFill.getId())) {
                periodService.modify(OcPeriodAdvanceFill.class, advanceFill);
            } else {
                periodService.addEntity(OcPeriodAdvanceFill.class, advanceFill);
            }
            resultmap.put("flag", "true");
            resultmap.put("msg", "保存成功!");
        } catch (Exception e) {
            resultmap.put("flag", "false");
            resultmap.put("msg", "保存失败!");
            logger.error("", e);
        }
        return resultmap;
    }

    /**
     * <p>描述 : 提前上报-删除</p>
     * @param advanceFill
     */
    @RequestMapping(value = "/advanceFill/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> advanceFillDelete(OcPeriodAdvanceFill advanceFill) {
        Map<String, String> resultmap = new HashMap<String, String>();
        try {
            periodService.deleteByIds(OcPeriodAdvanceFill.class, advanceFill.getId());
            resultmap.put("flag", "true");
            resultmap.put("msg", "删除成功!");
        } catch (Exception e) {
            resultmap.put("flag", "false");
            resultmap.put("msg", "删除失败!");
            logger.error("", e);
        }
        return resultmap;
    }

    /**
     * <p>描述 : 期间下拉框</p>
     * @param advanceFill
     */
    @RequestMapping(value = "/ajax/select")
    @ResponseBody
    public Map<String, Object> periodSelect(HttpServletRequest request) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        List<OcPeriodManage> periodSelect = new ArrayList<OcPeriodManage>(0);
        try {
            String parameter = request.getParameter("type");
            OcPeriodManage ocPeriodManage = new OcPeriodManage();
            if (StringUtils.isNotBlank(parameter)) {
                ocPeriodManage.setTypeCode(parameter);
            }
            periodSelect = periodService.queryByCondition(OcPeriodManage.class, ocPeriodManage);
            resultmap.put("periodSelect", periodSelect);
            resultmap.put("flag", "true");
        } catch (Exception e) {
            resultmap.put("flag", "false");
            logger.error("", e);
        }
        return resultmap;
    }

}
