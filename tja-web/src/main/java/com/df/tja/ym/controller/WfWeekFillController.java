/**
 * 项目名称:df-sys-web
 *
 * com.df.framework.weekFill.controller
 *
 * WfWfWeekFillFillController.java
 * 
 * 2017年9月18日-下午4:45:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.ym.controller;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.df.activiti.service.IProcessService;
import com.df.framework.exception.LogicalException;
import com.df.framework.sys.domain.SysUser;
import com.df.framework.sys.service.ISysUserService;
import com.df.framework.util.HttpUtil;
import com.df.framework.util.StringUtil;
import com.df.tja.domain.WfWeekFill;
import com.df.tja.domain.cust.WfWeekFillMore;
import com.df.tja.service.IWfWeekFillService;
import com.df.tja.service.IYmConfigService;

/**
 * <p>WfWfWeekFillFillController</p>
 * 
 * <p>描述：项目周报上报</p>
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
@RequestMapping("/admin/ym/weekFill")
public class WfWeekFillController extends WfBaseController {

    @Autowired
    private IWfWeekFillService weekFillService;

    @Autowired
    private IProcessService processService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IYmConfigService ymConfigService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/tjad/ym/weekfill/weekfill_list";
    }

    /**
     * <p>描述 : 编辑</p>
     * @param proId 项目ID
     * @param periodId 期间ID
     */
    @RequestMapping(value = "/toedit/{id}")
    public String toEdit(@PathVariable("id") String id, Model model) throws RuntimeException {
        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        String proId = request.getParameter("proId");
        String periodId = request.getParameter("periodId");

        WfWeekFillMore weekFill = weekFillService.queryWfWeekFill(id, proId, periodId);

        if (WfConstant.AuditStatus.AUDITING.equals(weekFill.getAuditStatus())
            || WfConstant.AuditStatus.AUDITED.equals(weekFill.getAuditStatus())) {
            return "redirect:/admin/ym/weekFill/toview/" + WfConstant.Operate.VIEW + "/" + weekFill.getId();
        }

        model.addAttribute("weekFill", weekFill);
        BigDecimal weekYieldCoe = ymConfigService.queryOcRebateParam(); // 当周产值计算系数

        model.addAttribute("weekYieldCoe", weekYieldCoe);
        return "/tjad/ym/weekfill/weekfill_edit";
    }

    /**
     * <p>描述 : 保存</p>
     * @param weekFill
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute WfWeekFill weekFill) {
        Map<String, String> resultmap = new HashMap<String, String>();
        String msg = SAVE_SUCCESS;
        String auditStatus = weekFill.getAuditStatus();
        try {
            String procKey = "WfWeekFill";
            String userId = HttpUtil.getUser().getId();
            ProcessArgs processArgs = new ProcessArgs(userId, procKey, auditStatus);

            if (WfConstant.AuditStatus.AUDITING.equals(auditStatus)) {
                // 运营部
                SysUser ocSysUser = userService.queryRoleUser(WfConstant.FlowTaskRole.YUNYING);
                processArgs.addVariable("ocOrg", ocSysUser.getId());

                processArgs.addVariable("url", "/admin/ym/weekFill/toedit/" + weekFill.getId());
            }
            // 提交或保存
            if (!WfConstant.AuditStatus.ABANDON.equals(auditStatus)) {
                String id = weekFillService.addOrModifyWfWeekFill(weekFill, processArgs);
                if ("1".equals(auditStatus)) {
                    msg = SUBMIT_SUCCESS;
                }
                resultmap.put("id", id);
                resultmap.put("msg", msg);
            } else {
                // 删除
                processService.deleteProcessDefinitionByEntity(WfWeekFill.class, weekFill);
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
     * <p>描述 :流程展现页面 </p>
     *
     * @param id 流程id
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = {"/toview/{operate}/{id}", "/toprint/{operate}/{id}"}, method = RequestMethod.GET)
    public ModelAndView toViewOrApprove(@PathVariable("operate") String operate, @PathVariable("id") String id,
                                        String view) throws RuntimeException {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        WfWeekFill week = weekFillService.queryByPrimaryKey(WfWeekFill.class, id);
        WfWeekFillMore weekFill = weekFillService.queryWfWeekFill(week.getId(), week.getProId(), week.getPeriodId());
        modelMap.put("weekFill", weekFill);

        //检查操作
        Map<String, String> ins = new HashMap<String, String>(0);
        ins.put("operate", operate);
        ins.put("procId", weekFill.getProcId());
        ins.put("applyer", weekFill.getCreator());
        ins.put("auditStatus", weekFill.getAuditStatus());
        checkOperate(ins, modelMap);

        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        //打印预览
        if (request.getRequestURI().contains("/toprint")) {
            modelMap.put("print", "print");
        }
        modelMap.put("definitionKey", "WfWeekFill");
        modelMap.put("executionId", weekFill.getProcId());

        ModelAndView modelAndView = new ModelAndView();
        Integer viewType = StringUtil.isNotBlank(view) ? Integer.valueOf(view) : 10;

        modelAndView.addObject("view", viewType);
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("/tjad/ym/weekfill/weekfill_view");
        return modelAndView;
    }

    /**
     * <p>描述 : 流程审批</p>
     * 
     * @param resumeMake
     * @return
     */
    @RequestMapping(value = "/ajax/approve", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> wfSubmit(@ModelAttribute WfWeekFill weekFill, Integer view) {
        Map<String, String> resultmap = new HashMap<String, String>();
        try {
            //如果不需要回写业务，可以直接调用processService的approveWf方法，
            //否则在Service中写接口方法，然后再调用processService中的approveWf方法
            weekFillService.modifySimple(WfWeekFill.class, weekFill);
            processService.approveWf(weekFill, null);
            resultmap.put("flag", "true");
            String msg = APPROVE_COMPLETE;
            if (WfConstant.AuditStatus.REVOKE.equals(weekFill.getApprove())) {
                msg = "撤回成功";
            }
            resultmap.put("msg", msg);
        } catch (LogicalException ex) {
            resultmap.put("flag", "false");
            resultmap.put("msg", ex.getMessage());
        } catch (Exception e) {
            resultmap.put("flag", "false");
            resultmap.put("msg", "操作失败");
            logger.error("", e);
        }
        return resultmap;
    }

}
