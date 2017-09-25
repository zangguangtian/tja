/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc.controller
 *
 * StandardPriceController.java
 * 
 * 2017年9月23日-上午11:00:16
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
import com.df.framework.exception.LogicalException;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcStandardPrice;
import com.df.tja.service.IStandardPriceService;

/**
 * <p>StandardPriceController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月23日 上午11:00:16</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/oc/stPrice")
public class StandardPriceController extends BaseController {

    @Autowired
    private IStandardPriceService standardPriceService;

    /**
     * 
     * <p>描述 : 进入页面</p>
     *
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/topage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toPage(Model model) throws RuntimeException {
        Map<String, Object> outparms = new HashMap<String, Object>();
        standardPriceService.queryStandardPriceInfo(outparms);
        model.addAllAttributes(outparms);
        return "/tjad/oc/stPrice/stPrice";
    }

    /**
     * 
     * <p>描述 : </p>
     *
     * @param ocStandardPrice
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/ajax/search", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> search(OcStandardPrice ocStandardPrice) {
        Map<String, Object> mess = new HashMap<String, Object>();
        try {
            Pagination page = new Pagination();
            page.setPageNo(ocStandardPrice.getPageIndex());
            page.setRowsPerPage(ocStandardPrice.getPageSize());
            ocStandardPrice.setPageIndex(null);
            ocStandardPrice.setPageSize(null);
            List<OcStandardPrice> ocStandardPrices = standardPriceService.queryStandardPrices(ocStandardPrice, page);
            mess.put("data", ocStandardPrices);
            mess.put("itemsCount", page.getTotalCount());
        } catch (Exception e) {
            logger.error("", e);
        }
        return mess;
    }

    @RequestMapping(value = "/ajax/asave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> eSave(OcStandardPrice ocStandardPrice) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            standardPriceService.createOrModifyStandardPrice(ocStandardPrice);
            mess.put("flag", "true");
            mess.put("msg", "保存成功!");
        } catch (LogicalException ex) {
            mess.put("flag", "false");
            mess.put("msg", ex.getMess());
            logger.error("", ex);
        } catch (Exception e) {
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
    public Map<String, String> delete(OcStandardPrice ocStandardPrice) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            standardPriceService.deleteStandardPrice(ocStandardPrice);
            mess.put("flag", "true");
            mess.put("msg", "删除成功");
        } catch (Exception e) {
            mess.put("flag", "false");
            mess.put("msg", "删除失败");
            logger.error("", e);
        }
        return mess;
    }
}
