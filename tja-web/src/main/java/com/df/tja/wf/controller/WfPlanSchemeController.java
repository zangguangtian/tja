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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.df.activiti.constant.WfConstant;
import com.df.activiti.controller.WfBaseController;
import com.df.activiti.domain.ProcessArgs;
import com.df.framework.exception.LogicalException;
import com.df.framework.sys.domain.SysUser;
import com.df.framework.sys.service.ISysUserService;
import com.df.framework.util.HttpUtil;
import com.df.tja.constant.TjaConstant;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.WfPlanScheme;
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
public class WfPlanSchemeController extends WfBaseController {

    @Autowired
    private IWfPlanSchemeService wfPlanSchemeService;

    @Autowired
    private ISysUserService userService;

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
            if (WfConstant.AuditStatus.AUDITING.equals(scheme.getAuditStatus())
                || WfConstant.AuditStatus.AUDITED.equals(scheme.getAuditStatus())) {
                return "redirect:/admin/wf/planScheme/toview/" + WfConstant.Operate.VIEW + "/" + scheme.getId();
            }
            model.addAllAttributes(attributes);
        }
        OcPeriodManage manage = new OcPeriodManage();
        manage.setTypeCode("OC.PERIOD.TYPE.SETTLE");
        List<OcPeriodManage> list = wfPlanSchemeService.queryByCondition(OcPeriodManage.class, manage);
        model.addAttribute("periodManages", list);
        return "/tjad/wf/planScheme/planScheme_edit";
    }

    /**
     * 
     * <p>描述 : 保存</p>
     *
     * @author DQ.zhou
     * @param ticket
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/ajax/esave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> aSave(@ModelAttribute WfPlanScheme planScheme) {
        Map<String, String> mess = new HashMap<String, String>();
        String msg = SAVE_SUCCESS;
        String auditStatus = planScheme.getAuditStatus();
        try {
            String procKey = "WfPlanScheme";
            String userId = HttpUtil.getUser().getId();
            ProcessArgs processArgs = new ProcessArgs(userId, procKey, auditStatus);

            if (WfConstant.AuditStatus.AUDITING.equals(auditStatus)) {
                //向processArgs中传流程参数

                SysUser ocSysUser = userService.queryRoleUser(TjaConstant.FlowTaskRole.YUNYING);
                SysUser orgleader = userService.queryOrgLeaderById(planScheme.getReceptDeptId());
                processArgs.addVariable("ocOrg", ocSysUser.getId());
                processArgs.addVariable("orgLeader", orgleader.getId());
            }

            //提交或保存
            if (!WfConstant.AuditStatus.ABANDON.equals(auditStatus)) {
                wfPlanSchemeService.addOrModifyPlanScheme(planScheme, processArgs);
                if ("1".equals(auditStatus)) {
                    msg = SUBMIT_SUCCESS;
                }
                mess.put("msg", msg);
            } else { //删除
                processService.deleteProcessDefinitionByEntity(WfPlanScheme.class, planScheme);
                mess.put("msg", "删除成功");
            }
            mess.put("flag", "true");
        } catch (LogicalException e) {
            mess.put("flag", "false");
            mess.put("msg", e.getMessage());
            logger.error("", e);
        } catch (RuntimeException e) {
            mess.put("flag", "false");
            mess.put("msg", "保存失败");
            logger.error("", e);
        }
        return mess;
    }

    /**
     * 
     * <p>描述 :流程展现页面 </p>
     *
     * @author DQ.zhou
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = {"/toview/{operate}/{id}", "/toprint/{operate}/{id}"}, method = RequestMethod.GET)
    public ModelAndView toViewOrApprove(@PathVariable("operate") String operate, @PathVariable("id") String id)
        throws RuntimeException {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        wfPlanSchemeService.queryPlanSchemeById(modelMap, id);
        WfPlanScheme scheme = (WfPlanScheme) modelMap.get("planScheme");

        //检查操作
        Map<String, String> ins = new HashMap<String, String>(0);
        ins.put("operate", operate);
        ins.put("procId", scheme.getProcId());
        ins.put("applyer", scheme.getCreator());
        ins.put("auditStatus", scheme.getAuditStatus());
        checkOperate(ins, modelMap);

        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        //打印预览
        if (request.getRequestURI().contains("/toprint")) {
            modelMap.put("print", "print");
            modelMap.put("definitionKey", "WfPlanScheme");
            modelMap.put("executionId", scheme.getProcId());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("/tjad/wf/planScheme/planScheme_view");
        return modelAndView;
    }

    /**
     * <p>描述 : 流程审批</p>
     * 
     * @author DQ.zhou
     * @param wfPlanScheme
     * @return
     */
    @RequestMapping(value = "/ajax/approve", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> wfSubmit(@ModelAttribute WfPlanScheme wfPlanScheme) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            //如果不需要回写业务，可以直接调用processService的approveWf方法，
            //否则在Service中写接口方法，然后再调用processService中的approveWf方法
            processService.approveWf(wfPlanScheme, null);
            mess.put("flag", "true");
            String msg = APPROVE_COMPLETE;
            if (WfConstant.AuditStatus.REVOKE.equals(wfPlanScheme.getApprove())) {
                msg = "撤回成功";
            }
            mess.put("msg", msg);
        } catch (LogicalException ex) {
            mess.put("flag", "false");
            mess.put("msg", ex.getMessage());
        } catch (Exception e) {
            mess.put("flag", "false");
            mess.put("msg", "审批失败");
            logger.error("", e);
        }
        return mess;
    }
}
