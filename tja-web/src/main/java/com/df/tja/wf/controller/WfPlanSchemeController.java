/**
 * 项目名称:tja-web
 *
 * com.df.tja.controller.wf
 *
 * WfPlanScheme.java
 * 
 * 2017年9月18日-上午11:30:16
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.wf.controller;

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

import com.df.activiti.domain.ProcessArgs;
import com.df.framework.base.controller.BaseController;
import com.df.framework.util.HttpUtil;
import com.df.tja.domain.WfPlanScheme;
import com.df.tja.domain.cust.WfPlanSchemeModel;
import com.df.tja.service.IWfPlanSchemeService;

/**
 * <p>WfPlanScheme</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 上午11:30:16</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/wf/planScheme")
public class WfPlanSchemeController extends BaseController {

    @Autowired
    private IWfPlanSchemeService wfPlanSchemeService;

    /**
     * 
     * <p>描述 : 跳到list页面</p>
     *
     * @return
     */
    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String search() {
        return "/tjad/wf/planScheme/planScheme_list";
    }

    /**
     * 
     * <p>描述 : 修改或添加明细页面</p>
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/toedit/{id}", method = {RequestMethod.GET})
    public String toEdit(Model model, @PathVariable("id") String id) {
        if (!"0".equals(id)) {
            Map<String, Object> attributes = new HashMap<String, Object>();
            //修改
            wfPlanSchemeService.queryPlanSchemeById(attributes, id);
            WfPlanScheme scheme = (WfPlanScheme) attributes.get("planScheme");
            /*if (Constants.AuditStatus.AUDITING.equals(scheme.getAuditStatus())
                || Constants.AuditStatus.AUDITED.equals(scheme.getAuditStatus())) {
                return "redirect:/admin/wf/ticket/toview/" + Constants.Operate.VIEW + "/" + scheme.getId();
            }*/
            model.addAllAttributes(attributes);
        }
        return "/tjad/wf/planScheme/planScheme_edit";
    }

    /**
     * 
     * <p>描述 : 保存或提交</p>
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/esave", method = RequestMethod.POST)
    public Map<String, String> aSave(@ModelAttribute WfPlanScheme planScheme,
                                     @ModelAttribute WfPlanSchemeModel planSchemeModel) throws Exception {
        Map<String, String> mess = new HashMap<String, String>();
        String msg = SAVE_SUCCESS;
        try {
            String procKey = "WfPlanScheme";
            String userId = HttpUtil.getUser().getId();
            ProcessArgs processArgs = new ProcessArgs(userId, procKey, "1");
            wfPlanSchemeService.addOrModifyPlanScheme(planScheme, processArgs, planSchemeModel);
            mess.put("msg", msg);
            mess.put("flag", "true");
        } catch (RuntimeException e) {
            mess.put("flag","false");
            mess.put("msg", "保存失败");
            logger.error("", e);
        }
        return mess;
    }
    

}
