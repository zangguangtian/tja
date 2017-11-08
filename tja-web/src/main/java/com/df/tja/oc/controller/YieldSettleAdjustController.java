/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc.controller
 *
 * YieldSettleController.java
 * 
 * 2017年11月6日-下午2:53:14
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.oc.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;
import com.df.tja.service.IYieldSettleAdjustService;

/**
 * <p>YieldSettleController</p>
 * 
 * <p>描述：已结算产值调整</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月6日 下午2:53:14</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/oc/settleAdjust")
public class YieldSettleAdjustController extends BaseController {

    @Autowired
    private IYieldSettleAdjustService yieldSettleService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "/tjad/oc/ylSettle/settleAdjust_list";
    }

    @RequestMapping(value = "/toedit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") String id, Model model) throws RuntimeException {

        WfYieldSettle yieldSettle = yieldSettleService.queryByPrimaryKey(WfYieldSettle.class, id);
        String url = "/tjad/oc/ylSettle/ylSettle";
        //1000：年度产值结算；2000：年度产值结算特批
        if (yieldSettle != null && "2000".equals(yieldSettle.getWfCategory().trim())) {
            url = "/tjad/oc/ylSettle/scSettle";
        }
        Map<String, Object> outParams = new HashMap<String, Object>();
        yieldSettleService.queryYieldSettle(outParams, yieldSettle);
        model.addAllAttributes(outParams);
        return url;
    }

    /**
     * 
     * <p>描述 : 保存</p>
     *
     * @param settleModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute WfYieldSettleModel settleModel) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            yieldSettleService.createOrEditYieldSettle(settleModel);
            resultMap.put("flag", "true");
            resultMap.put("msg", "保存成功");
        } catch (LogicalException e) {
            resultMap.put("flag", "false");
            resultMap.put("msg", e.getMessage());
            logger.error("", e);
        } catch (RuntimeException ex) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "保存失败");
            logger.error("", ex);
        }
        return resultMap;
    }
}
