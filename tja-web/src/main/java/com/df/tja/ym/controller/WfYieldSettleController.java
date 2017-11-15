/**
 * 项目名称:tja-web
 *
 * com.df.tja.ym.controller
 *
 * WfYieldSettleController.java
 * 
 * 2017年10月18日-下午4:06:57
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.ym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.servlet.ModelAndView;

import com.df.activiti.constant.WfConstant;
import com.df.activiti.controller.WfBaseController;
import com.df.activiti.domain.ProcessArgs;
import com.df.framework.exception.LogicalException;
import com.df.framework.sys.domain.SysUser;
import com.df.framework.sys.service.ISysUserService;
import com.df.framework.util.HttpUtil;
import com.df.hr.domain.cust.CustStaff;
import com.df.project.service.IProjectService;
import com.df.tja.constant.TjaConstant;
import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;
import com.df.tja.service.IWfYieldSettleService;

/**
 * <p>WfYieldSettleController</p>
 * 
 * <p>描述：年度产值结算 控制器</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月18日 下午4:06:57</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/ym/yieldSettle")
public class WfYieldSettleController extends WfBaseController {

    @Autowired
    private IWfYieldSettleService wfYieldSettleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IProjectService projectService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/tjad/ym/yieldSettle/yieldSettle_list";
    }


        /**
    * <p>描述 : 编辑</p>
    * @param proId 项目ID
    * @param periodId 期间ID
    */
    @RequestMapping(value = "/toedit/{id}/{editType}")
    public String toEdit(@PathVariable("id") String id, @PathVariable("editType") String editType, Model model)
        throws RuntimeException {
        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        String proId = request.getParameter("proId");
        String periodId = request.getParameter("periodId");
        String syId = request.getParameter("syId");

        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("proId", proId);
        inParams.put("periodId", periodId);
        inParams.put("syId", syId);
        inParams.put("id", id);
        inParams.put("editType", editType);

        Map<String, Object> outParams = new HashMap<String, Object>();
        wfYieldSettleService.queryYieldSettle(inParams, outParams);

        //普通年度产值结算流程
        String url = "/tjad/ym/yieldSettle/yieldSettle_edit";
        String redUrl = "redirect:/admin/ym/yieldSettle/toview/" + WfConstant.Operate.VIEW + "/";
        if ("2000".equals(editType)) {
            // 特批年度产值结算流程
            url = "/tjad/ym/yieldSettle/specSettle_edit";
            redUrl = "redirect:/admin/ym/yieldSettle/ordin/toview/" + WfConstant.Operate.VIEW + "/";
        }

        if (!"0".equals(id)) {
            WfYieldSettle yieldSettle = (WfYieldSettle) outParams.get("yieldSettle");

            if (WfConstant.AuditStatus.AUDITING.equals(yieldSettle.getAuditStatus())
                || WfConstant.AuditStatus.AUDITED.equals(yieldSettle.getAuditStatus())) {
                return redUrl + yieldSettle.getId();
            }
        }
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
        Map<String, String> resultmap = new HashMap<String, String>();
        WfYieldSettle yieldSettle = settleModel.getWfYieldSettle();
        yieldSettle.setWfCategory(yieldSettle.getWfCategory().trim());
        String msg = SAVE_SUCCESS;
        String auditStatus = yieldSettle.getAuditStatus();
        try {
            String procKey = "WfSpecialSettle"; //年度产值结算 - 特批流程
            if ("1000".equals(yieldSettle.getWfCategory().trim())) {
                procKey = "WfOrdinSettle";
            }
            String userId = HttpUtil.getUser().getId();
            ProcessArgs processArgs = new ProcessArgs(userId, procKey, auditStatus);

            if (WfConstant.AuditStatus.AUDITING.equals(auditStatus)) {
                // 运营部
                SysUser ocSysUser = userService.queryRoleUser(TjaConstant.FlowTaskRole.YUNYING);
                processArgs.addVariable("ocOrg", ocSysUser.getId());
                processArgs.addVariable("url", "/admin/ym/yieldSettle/toedit/{formId}/2000");
                if ("1000".equals(yieldSettle.getWfCategory().trim())) {
                    Set<String> majorUsers = new HashSet<String>();
                    //各专业负责人审批
                    List<WfYieldMajorRate> majorRates = settleModel.getMajorRates();
                    List<String> majorCodes = new ArrayList<String>();
                    if (majorRates != null && majorRates.size() > 0) {
                        for (WfYieldMajorRate majorRate : majorRates) {
                            majorCodes.add(majorRate.getMajorCode());
                        }
                    }

                    List<CustStaff> custStaffs = projectService.queryMajorMainLeaderByCode(majorCodes,
                        yieldSettle.getProId());

                    for (CustStaff custStaff : custStaffs) {
                        majorUsers.add(custStaff.getUserId());
                    }

                    if (majorUsers.size() <= 0) {
                        throw new LogicalException("专业未取到审批人!");
                    }
                    processArgs.addVariable("majorUsers", majorUsers);

                    Map<String, List<String>> userMajorMap = projectService.queryMajorMainLeaderMapByCode(majorCodes,
                        yieldSettle.getProId());

                    processArgs.addVariable("userMajorMap", userMajorMap);
                    processArgs.addVariable("url", "/admin/ym/yieldSettle/toedit/{formId}/1000");
                }
            }

            // 提交或保存
            if (!WfConstant.AuditStatus.ABANDON.equals(auditStatus)) {
                wfYieldSettleService.addOrModifyYieldSettle(yieldSettle, processArgs, settleModel);
                if ("1".equals(auditStatus)) {
                    msg = SUBMIT_SUCCESS;
                }
                resultmap.put("msg", msg);
            } else {
                // 删除
                processService.deleteProcessDefinitionByEntity(WfYieldSettle.class, yieldSettle);
                resultmap.put("msg", "删除成功");
            }
            resultmap.put("flag", "true");
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
     * 
     * <p>描述 : </p>
     *
     * @param operate
     * @param id
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = {"/toview/{operate}/{id}", "/toprint/{operate}/{id}"}, method = RequestMethod.GET)
    public ModelAndView toViewOrApprove(@PathVariable("operate") String operate, @PathVariable("id") String id)
        throws RuntimeException {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        wfYieldSettleService.queryYieldSettleForView(modelMap, id, null);

        WfYieldSettle yieldSettle = (WfYieldSettle) modelMap.get("yieldSettle");

        //检查操作
        Map<String, String> ins = new HashMap<String, String>(0);
        ins.put("operate", operate);
        ins.put("procId", yieldSettle.getProcId());
        ins.put("applyer", yieldSettle.getCreator());
        ins.put("auditStatus", yieldSettle.getAuditStatus());
        checkOperate(ins, modelMap);

        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        //打印预览
        if (request.getRequestURI().contains("/toprint")) {
            modelMap.put("print", "print");
            modelMap.put("definitionKey", "WfSpecialSettle");
            modelMap.put("executionId", yieldSettle.getProcId());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("/tjad/ym/yieldSettle/specSettle_view");
        return modelAndView;
    }

    @RequestMapping(value = {"/ordin/toview/{operate}/{id}", "/ordin/toprint/{operate}/{id}"})
    public ModelAndView toViewOrApproveOrdin(@PathVariable("operate") String operate, @PathVariable("id") String id,
                                             String view) throws RuntimeException {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (StringUtils.isBlank(view)) {
            view = "100";
        }
        wfYieldSettleService.queryYieldSettleForView(modelMap, id, Integer.parseInt(view));

        WfYieldSettle yieldSettle = (WfYieldSettle) modelMap.get("yieldSettle");
        modelMap.put("view", Integer.parseInt(view));
        //检查操作
        Map<String, String> ins = new HashMap<String, String>(0);
        ins.put("operate", operate);
        ins.put("procId", yieldSettle.getProcId());
        ins.put("applyer", yieldSettle.getCreator());
        ins.put("auditStatus", yieldSettle.getAuditStatus());
        checkOperate(ins, modelMap);

        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        //打印预览
        if (request.getRequestURI().contains("/toprint")) {
            modelMap.put("print", "print");
            modelMap.put("definitionKey", "WfOrdinSettle");
            modelMap.put("executionId", yieldSettle.getProcId());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("/tjad/ym/yieldSettle/yieldSettle_view");
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
    public Map<String, String> wfSubmit(@ModelAttribute WfYieldSettle yieldSettle,
                                        @ModelAttribute WfYieldSettleModel settleModel, Integer view) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            //如果不需要回写业务，可以直接调用processService的approveWf方法，
            //否则在Service中写接口方法，然后再调用processService中的approveWf方法
            wfYieldSettleService.approveWfYieldSettle(yieldSettle, settleModel, view);
            mess.put("flag", "true");
            String msg = APPROVE_COMPLETE;
            if (WfConstant.AuditStatus.REVOKE.equals(yieldSettle.getApprove())) {
                msg = "撤回成功";
            }
            mess.put("msg", msg);
        } catch (LogicalException ex) {
            mess.put("flag", "false");
            mess.put("msg", ex.getMessage());
        } catch (RuntimeException e) {
            mess.put("flag", "false");
            mess.put("msg", "审批失败");
            logger.error("", e);
        }
        return mess;
    }
}

