/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc.controller
 *
 * PermitYieldController.java
 * 
 * 2017年9月27日-下午4:35:40
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.oc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.tja.constant.TjaConstant;
import com.df.tja.domain.OcPermitYield;
import com.df.tja.service.IPermitYieldService;

/**
 * <p>PermitYieldController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月27日 下午4:35:40</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/oc/permitYield")
public class PermitYieldController extends BaseController {

    @Autowired
    private IPermitYieldService permitYieldService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 
     * <p>描述 :进入页面 </p>
     *
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/topage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toPage(Model model) throws RuntimeException {
        return "/tjad/oc/ptYield/ptYield";
    }


    @RequestMapping(value = "/ajax/search", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> search(OcPermitYield ocPermitYield) {
        Map<String, Object> mess = new HashMap<String, Object>();
        try {
            Pagination page = new Pagination();
            page.setPageNo(ocPermitYield.getPageIndex());
            page.setRowsPerPage(ocPermitYield.getPageSize());
            ocPermitYield.setPageIndex(null);
            ocPermitYield.setPageSize(null);
            List<OcPermitYield> ocStandardPrices = permitYieldService.queryPermitYield(ocPermitYield, page);
            mess.put("data", ocStandardPrices);
            mess.put("itemsCount", page.getTotalCount());
        } catch (RuntimeException e) {
            logger.error("", e);
        }
        return mess;
    }

    @RequestMapping(value = "/ajax/asave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> eSave(OcPermitYield ocPermitYield) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            permitYieldService.createOrModifyPermitYield(ocPermitYield);
            mess.put("flag", "true");
            mess.put("msg", "保存成功!");
        } catch (RuntimeException e) {
            mess.put("flag", "false");
            mess.put("msg", "保存失败!");
            logger.error("", e);
        }
        return mess;
    }

    /**
     * 
     * <p>描述 : 删除</p>
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajax/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, String> delete(OcPermitYield ocPermitYield) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            permitYieldService.deleteByIds(OcPermitYield.class, ocPermitYield.getId());
            mess.put("flag", "true");
            mess.put("msg", "删除成功");
        } catch (Exception e) {
            mess.put("flag", "false");
            mess.put("msg", "删除失败");
            logger.error("", e);
        }
        return mess;
    }

    /**
     * 
     * <p>描述 : 专业下拉框</p>
     *
     * @return
     */
    @RequestMapping(value = "/ajax/major")
    @ResponseBody
    public Map<String, Object> seachMajor() {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
            List<SysConfig> list = configService.querySysConfigsByParentCode(TjaConstant.SysCode.PM_MAJOR_PARENT_CODE);
            resultmap.put("list", list);
            resultmap.put("flag", "true");
        } catch (Exception e) {
            resultmap.put("flag", "false");
            logger.error("", e);
        }
        return resultmap;
    }
}
