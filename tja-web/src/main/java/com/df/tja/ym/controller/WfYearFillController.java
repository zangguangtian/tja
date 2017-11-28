/**
 * 项目名称:df-sys-web
 *
 * com.df.framework.yearFill.controller
 *
 * WfYearFillController.java
 * 
 * 2017年9月18日-下午4:45:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.ym.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.df.project.domain.ProjectExtend;
import com.df.tja.constant.TjaConstant;
import com.df.tja.domain.WfMajorProgressRecord;
import com.df.tja.domain.WfYearMonthFill;
import com.df.tja.domain.cust.WfYearMonthFillMore;
import com.df.tja.service.IWfYearMonthFillService;
import com.df.tja.service.IYmConfigService;

/**
 * <p>WfYearFillController</p>
 * 
 * <p>描述：项目年报上报</p>
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
@RequestMapping("/admin/ym/yearFill")
public class WfYearFillController extends WfBaseController {

    @Autowired
    private IWfYearMonthFillService yearMonthFillService;

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
        return "/tjad/ym/yearfill/yearfill_list";
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

        WfYearMonthFillMore yearFill = yearMonthFillService.queryWfYearMonthFill(id, proId, periodId, "2000");

        if (WfConstant.AuditStatus.AUDITING.equals(yearFill.getAuditStatus())
            || WfConstant.AuditStatus.AUDITED.equals(yearFill.getAuditStatus())) {
            return "redirect:/admin/ym/yearFill/toview/" + WfConstant.Operate.VIEW + "/" + yearFill.getId();
        }

        model.addAttribute("yearFill", yearFill);

        BigDecimal ocRebate = ymConfigService.queryOcRebateParam();
        model.addAttribute("ocRebate", ocRebate); // 产值计算系数

        ProjectExtend projectExtend = yearMonthFillService.queryByPrimaryKey(ProjectExtend.class, proId);
        if (projectExtend != null) {
            model.addAttribute("principalRate", projectExtend.getPrincipalRate()); // 项目负责人分配比例
            model.addAttribute("pmRate", projectExtend.getPmRate()); // 项目经理分配比例
            model.addAttribute("secretRate", projectExtend.getSecretRate()); // 项目秘书分配比例
        }

        return "/tjad/ym/yearfill/yearfill_edit";
    }

    /**
     * <p>描述 : 保存</p>
     * @param yearFill
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute WfYearMonthFillMore yearFill) {
        Map<String, String> resultmap = new HashMap<String, String>();
        String msg = SAVE_SUCCESS;
        String auditStatus = yearFill.getAuditStatus();
        WfYearMonthFill ymFill = new WfYearMonthFill();
        BeanUtils.copyProperties(yearFill, ymFill);
        List<WfMajorProgressRecord> majorProgressList = yearFill.getMajorProgressList();
        try {
            String procKey = "WfYearFill";
            String userId = HttpUtil.getUser().getId();
            ProcessArgs processArgs = new ProcessArgs(userId, procKey, auditStatus);

            if (WfConstant.AuditStatus.AUDITING.equals(auditStatus)) {
                // 运营部
                SysUser ocSysUser = userService.queryRoleUser(TjaConstant.FlowTaskRole.YUNYING);
                processArgs.addVariable("ocOrg", ocSysUser.getId());

                processArgs.addVariable("url", "/admin/ym/weekFill/toedit/" + yearFill.getId());
            }

            // 提交或保存
            if (!WfConstant.AuditStatus.ABANDON.equals(auditStatus)) {
                String id = yearMonthFillService.addOrModifyWfYearMonthFill(ymFill, processArgs, majorProgressList);
                if ("1".equals(auditStatus)) {
                    msg = SUBMIT_SUCCESS;
                }
                resultmap.put("id", id);
                resultmap.put("msg", msg);
            } else {
                // 删除
                processService.deleteProcessDefinitionByEntity(WfYearMonthFill.class, yearFill);
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
    public ModelAndView toViewOrApprove(@PathVariable("operate") String operate, @PathVariable("id") String id)
        throws RuntimeException {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        WfYearMonthFillMore yearFill = yearMonthFillService.queryWfYearMonthFill(id, null, null, "2000");
        modelMap.put("yearFill", yearFill);

        //检查操作
        Map<String, String> ins = new HashMap<String, String>(0);
        ins.put("operate", operate);
        ins.put("procId", yearFill.getProcId());
        ins.put("applyer", yearFill.getCreator());
        ins.put("auditStatus", yearFill.getAuditStatus());
        checkOperate(ins, modelMap);

        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        //打印预览
        if (request.getRequestURI().contains("/toprint")) {
            modelMap.put("print", "print");
        }
        modelMap.put("definitionKey", "WfYearFill");
        modelMap.put("executionId", yearFill.getProcId());

        ModelAndView modelAndView = new ModelAndView();

        BigDecimal ocRebate = ymConfigService.queryOcRebateParam();
        modelAndView.addObject("ocRebate", ocRebate); // 产值计算系数

        ProjectExtend projectExtend = yearMonthFillService.queryByPrimaryKey(ProjectExtend.class, yearFill.getProId());
        if (projectExtend != null) {
            modelAndView.addObject("principalRate", projectExtend.getPrincipalRate()); // 项目负责人分配比例
            modelAndView.addObject("pmRate", projectExtend.getPmRate()); // 项目经理分配比例
            modelAndView.addObject("secretRate", projectExtend.getSecretRate()); // 项目秘书分配比例
        }

        modelAndView.addObject("view", 10);
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("/tjad/ym/yearfill/yearfill_view");
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
    public Map<String, String> wfSubmit(@ModelAttribute WfYearMonthFill yearFill, Integer view) {
        Map<String, String> resultmap = new HashMap<String, String>();
        try {
            //如果不需要回写业务，可以直接调用processService的approveWf方法，
            //否则在Service中写接口方法，然后再调用processService中的approveWf方法
            processService.approveWf(yearFill, null);
            resultmap.put("flag", "true");
            String msg = APPROVE_COMPLETE;
            if (WfConstant.AuditStatus.REVOKE.equals(yearFill.getApprove())) {
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

    /**
     * <p>描述 : 历史</p>
     */
    @RequestMapping(value = "/history")
    public String history(Model model, HttpServletRequest request) {
        model.addAttribute("proId", request.getParameter("proId"));
        return "/tjad/ym/yearfill/history";
    }

    /**
     * <p>描述 : 专业进度-历史</p>
     */
    @RequestMapping(value = "/majorhis")
    public String majorProgressHistory(Model model, HttpServletRequest request) {
        model.addAttribute("proId", request.getParameter("proId"));
        model.addAttribute("majorCode", request.getParameter("majorCode"));
        model.addAttribute("category", "2000");
        return "/tjad/ym/major_his";
    }

}
