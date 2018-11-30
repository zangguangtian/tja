/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc.controller
 *
 * MajorSchemeController.java
 * 
 * 2017年11月14日-下午3:48:18
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.oc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.df.framework.hibernate.persistence.Pagination;
import com.df.project.service.IProjectApprovalService;
import com.df.tja.domain.OcStepFill;
import com.df.tja.domain.cust.OcCurrweekSchedule;
import com.df.tja.domain.cust.OcStepFillMore;
import com.df.tja.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.df.framework.base.controller.BaseController;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.DateUtil;
import com.df.framework.util.StringUtil;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.domain.OcSchedule;
import com.df.tja.domain.OcScheme;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.CustOcDesignSchedule;

/**
 * <p>MajorSchemeController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月14日 下午3:48:18</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/work/schedule")
public class WorkScheduleController extends BaseController {

    @Autowired
    private IProjectService projectService;
    
    @Autowired
    private IOcSchemeService ocSchemeService;
    
    @Autowired
    private IOcScheduleService ocScheduleService;
    
    @Autowired
    private IOcDesignScheduleService designScheduleService;
    
    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private IOcStepFillService ocStepFillService;

    @Autowired
    private IOcScheduleFillService ocScheduleFillService;

    /**
     *
     * <p>描述 : 工作进展列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() throws RuntimeException {
        return "/tjad/oc/schedule/work_schedule_list";
    }
    
    /**
     * <p>描述 : 打开主项工作进展页面  </p>
     * @param proId 主项ID
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/main/{proId}", method = {RequestMethod.GET,RequestMethod.POST})
    public String viewMain(@PathVariable("proId")String proId , Model model) throws RuntimeException{
        Map<String, List<CustProject>> projectMore = projectService.queryTheMainByPreProId(proId);
        List<CustProject> projects = projectMore.get("pre");
        model.addAttribute("project", projects.get(0));
        return "/tjad/oc/schedule/schedule_of_main";
    }
    
    /**
     * <p>描述 : 打开分项工作进展页面  </p>
     * 
     * @param proId 分项ID
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/item/{proId}", method = RequestMethod.GET)
    public String viewItem(@PathVariable("proId")String proId, Model model) throws RuntimeException{
        CustProject projectMore = projectService.queryByProId(proId);
        model.addAttribute("project", projectMore);
        return "/tjad/oc/schedule/schedule_of_item";
    }

    /**
     * <p>描述 : 打开专业工作进展页面  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/major/{proId}", method = RequestMethod.GET)
    public String viewMajor(@PathVariable("proId")String proId, Model model) throws RuntimeException{
        CustProject projectMore = projectService.queryByProId(proId);
        model.addAttribute("project", projectMore);
        return "/tjad/oc/schedule/schedule_of_major";
    }

    /**
     * <p>描述 : 打开设计师填报页面  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/design/{proId}/{schemeId}", method = RequestMethod.GET)
    public String toDesignFill(@PathVariable("proId") String proId, @PathVariable("schemeId") String schemeId,
                               Model model)
        throws RuntimeException {
        //查询项目信息
        CustProject projectMore = projectService.queryByProId(proId);
        model.addAttribute("project", projectMore);

        //查询策划信息
        OcScheme ocScheme = ocSchemeService.queryByProId(proId);
        model.addAttribute("ocScheme", ocScheme);

        if("OC.PROJECT.WBS.FULL".equals(ocScheme.getProWbs())) { //完整模式
            //查项目的所有策划阶段
            OcSchemeDivisor entitySrch = new OcSchemeDivisor();
            entitySrch.setProId(proId);
            entitySrch.setDivisorGrade(1);
            List<OcSchemeDivisor> divisors = ocSchemeService.queryByCondition(OcSchemeDivisor.class, entitySrch);
            model.addAttribute("phases", divisors);
        }else {  //简化模式
            // 查简化列表
        }
        
        String scheduleId = null;
        //检查本周是否存在填报主记录
        OcSchedule entity = new OcSchedule();
        entity.setProId(proId);
        entity.setOtherCondition(" GETDATE() BETWEEN WEEK_START AND WEEK_END");
        List<OcSchedule> list = ocScheduleService.queryByCondition(OcSchedule.class, entity);
        if (list == null || list.isEmpty()) {
            SysConfig sysConfig = sysConfigService.querySysConfigByCode("OC.SCHEDULE.WEEK.START");
            int firstDay = Calendar.MONDAY;
            if(sysConfig != null && StringUtil.isNotBlank(sysConfig.getConfigValue())) {
                firstDay = Integer.parseInt(sysConfig.getConfigValue());
            }
            
            Date now = new Date();
            Date weekStart = DateUtil.getFirstDayOfWeek(now, firstDay);
            Date weekEnd = DateUtil.nextSevenDate(weekStart);
            
            entity.setProId(proId);
            entity.setSchemeId(schemeId);
            entity.setWeekStart(weekStart);
            entity.setWeekEnd(weekEnd);
            ocScheduleService.addEntity(OcSchedule.class, entity);
            scheduleId = entity.getId();
        }else {
            scheduleId = list.get(0).getId();
        }
        
        model.addAttribute("scheduleId", scheduleId);
        return "/tjad/oc/schedule/schedule_of_design";
    }
    
    @RequestMapping(value = "/design/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> saveDesignFill(@RequestBody CustOcDesignSchedule designSchedule) throws RuntimeException {
        Map<String, String> resultMap = new HashMap<String, String>(0);
        try {
            designScheduleService.createDesignSchedules(designSchedule);
            resultMap.put("success", "true");
            resultMap.put("mess", "保存成功!");
        } catch (RuntimeException ex) {
            resultMap.put("success", "false");
            resultMap.put("mess", "保存失败!");
        }
        return resultMap;
    }

    /**
     * <p>描述 : 设计师填报页面加载完整模式列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/design/ajaxhtml/load/{phaseId}", method = RequestMethod.POST)
    public String loadFull(@PathVariable("phaseId") String phaseId, Model model){
        List<SysConfig> statuses = sysConfigService.querySysConfigsByParentCode("OC.SCHEDULE.STATUS");
        model.addAttribute("statuses", statuses);
        
        List<CustOcDesignSchedule> designs = designScheduleService.queryDesignSchedulesById(phaseId);
        model.addAttribute("designs", designs);
        return "/tjad/oc/schedule/schedule_of_design_full";
    }

    /**
     * <p>描述 : 主项工作进度  </p>
     * @param proId 主项ID
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/workprog/ajaxhtml/{proId}", method = {RequestMethod.GET,RequestMethod.POST})
    public String loadWork(@PathVariable("proId")String proId, @ModelAttribute("page") Pagination page, Model model) throws RuntimeException{

        if (page == null || page.getPageNo() == 0) {
            page = new Pagination(1,10);
        }
        //简化模式
        List<OcCurrweekSchedule> ocCurrweekScheduleList = ocScheduleFillService.querySimleByProId(proId,page,1);
        if(ocCurrweekScheduleList.size() > 0){
            model.addAttribute("ocCurrweekScheduleList",ocCurrweekScheduleList);
        }else {

            //完整模式
            List<OcCurrweekSchedule> ocCurrweekSchedules = ocScheduleFillService.queryFullByProId(proId, page, 1);
            if (ocCurrweekSchedules.size() > 0) {
                model.addAttribute("ocCurrweekSchedules", ocCurrweekSchedules);
            }
        }
        model.addAttribute("page", page);
        return "/tjad/oc/schedule/work_of_progress";
    }

    /**
     * <p>描述 : 主项进展情况  </p>
     * @param proId 主项ID
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/progmain/ajaxhtml/{proId}", method = {RequestMethod.GET,RequestMethod.POST})
    public String loadProg(@PathVariable("proId")String proId, @ModelAttribute("page") Pagination page, Model model) throws RuntimeException{

        if (page == null || page.getPageNo() == 0) {
            page = new Pagination(1,10);
        }
        List<OcStepFillMore> ocStepFillMoreList = ocStepFillService.queryByPreProId(proId,page,1);
        model.addAttribute("ocStepFillList", ocStepFillMoreList);
        model.addAttribute("page", page);
        return "/tjad/oc/schedule/progress_of_main";
    }

    /**
     * <p>描述 : 分项工作进度  </p>
     * @param proId
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/workprogitem/ajaxhtml/{proId}", method = {RequestMethod.GET,RequestMethod.POST})
    public String loadItemWork(@PathVariable("proId")String proId, @ModelAttribute("page") Pagination page, Model model) throws RuntimeException{

        if (page == null || page.getPageNo() == 0) {
            page = new Pagination(1,10);
        }
        //简化模式
        List<OcCurrweekSchedule> ocCurrweekScheduleList = ocScheduleFillService.querySimleByProId(proId,page,2);
        if(ocCurrweekScheduleList.size() > 0){
            model.addAttribute("ocCurrweekScheduleList",ocCurrweekScheduleList);
        }else {

            //完整模式
            List<OcCurrweekSchedule> ocCurrweekSchedules = ocScheduleFillService.queryFullByProId(proId, page, 2);
            if (ocCurrweekSchedules.size() > 0) {
                model.addAttribute("ocCurrweekSchedules", ocCurrweekSchedules);
            }
        }
        model.addAttribute("page", page);
        return "/tjad/oc/schedule/work_of_progress_item";
    }

    /**
     * <p>描述 : 分项进展情况  </p>
     * @param proId
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/progmainitem/ajaxhtml/{proId}", method = {RequestMethod.GET,RequestMethod.POST})
    public String loadItemsProg(@PathVariable("proId")String proId, @ModelAttribute("page") Pagination page, Model model) throws RuntimeException{

        if (page == null || page.getPageNo() == 0) {
            page = new Pagination(1,10);
        }
        List<OcStepFillMore> ocStepFillMoreList = ocStepFillService.queryByPreProId(proId,page,2);
        model.addAttribute("ocStepFillList", ocStepFillMoreList);
        model.addAttribute("page", page);
        return "/tjad/oc/schedule/progress_of_main_item";
    }

    /**
     * <p>描述 : 打开更新项目进展页面 </p>
     *
     * @param stepId
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/ajax/updpro/{stepId}", method = RequestMethod.GET)
    public String toItemProgree(@PathVariable(value = "stepId") String stepId,Model model) throws RuntimeException {

        OcStepFill ocStepFill = ocStepFillService.queryById(stepId);
        if(ocStepFill != null){
            model.addAttribute("ocStepFill", ocStepFill);
        }
        return "/tjad/oc/schedule/schedule_of_upditem";
    }

    /**
     * <p>描述 : 项目进展信息保存</p>
     *
     * @param ocStepFill
     * @return
     */
    @RequestMapping(value = "/ajax/item/progress", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> saveItemProgree(@ModelAttribute OcStepFill ocStepFill){
        Map<String,String> result = new HashMap<>();
        try{
            ocStepFillService.addOrUpdStepFill(ocStepFill);
            result.put("flag", "true");
            result.put("msg", "保存成功!");
        }catch(Exception e){
            result.put("flag", "false");
            result.put("msg", "保存失败!");
            throw new RuntimeException(e);
        }
        return result;
    }
}
