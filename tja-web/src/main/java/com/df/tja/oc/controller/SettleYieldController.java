/**
 * 项目名称:df-sys-web
 *
 * com.df.framework.settleYield.controller
 *
 * SettleYieldController.java
 * 
 * 2017年9月18日-下午4:45:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.oc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.df.framework.base.controller.BaseController;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.OcSettleYield;
import com.df.tja.service.IPeriodService;
import com.df.tja.service.ISettleYieldService;

/**
 * <p>SettleYieldController</p>
 * 
 * <p>描述：可结算产值管理</p>
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
@RequestMapping("/admin/oc/settle")
public class SettleYieldController extends BaseController {

    @Autowired
    ISettleYieldService settleYieldService;

    @Autowired
    IPeriodService periodService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/tjad/oc/settle/settle_yield_list";
    }

    /**
     * <p>描述 : 编辑</p>
     * @param id
     */
    @RequestMapping(value = "/{id}")
    public String edit(@PathVariable("id") String id, Model model) throws RuntimeException {
        if (!"0".equals(id)) {
            OcSettleYield settleYield = settleYieldService.querySettleYield(id);
            model.addAttribute("settleYield", settleYield);
        }
        return "/tjad/oc/settle/settle_yield_edit";
    }

    /**
     * <p>描述 : 保存</p>
     * @param settleYield 实体
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute OcSettleYield settleYield) {
        Map<String, String> resultmap = new HashMap<String, String>();
        try {
            String settleYieldId = settleYieldService.createOrModifySettleYield(settleYield);
            resultmap.put("settleYieldId", settleYieldId);
            resultmap.put("flag", "true");
            resultmap.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException e) {
            resultmap.put("flag", "false");
            resultmap.put("msg", "保存失败");
            logger.error("", e);
        }
        return resultmap;
    }

    /**
     * <p>描述 : 导入</p>
     */
    @RequestMapping(value = "/import")
    public String toImport(Model model) {
        OcPeriodManage period = new OcPeriodManage();
        period.setTypeCode("OC.PERIOD.TYPE.YEAR");
        List<OcPeriodManage> periodSelect = periodService.queryByCondition(OcPeriodManage.class, period);
        model.addAttribute("periodSelect", periodSelect);
        return "/tjad/oc/settle/settle_yield_import";
    }

    /**
     * 
     * <p>描述 : </p>
     *
     * @param index
     * @param attach
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajax/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> multUpload(@ModelAttribute("attach") MultipartFile attach, String period) {
        Map<String, Object> results = new HashMap<String, Object>(0);
        try {
            settleYieldService.createImpSettleYield(attach, period, results);
            results.put("status", "true");
            results.put("mess", "上传成功!");
        } catch (RuntimeException ex) {
            results.put("status", "false");
            results.put("mess", "上传失败!");
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("upload failure!", ex);
            }
            throw ex;
        }
        return results;
    }

}
