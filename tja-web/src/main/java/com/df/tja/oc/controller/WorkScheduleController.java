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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.log.LoggerProxy;
import com.df.framework.rest.vo.BaseResponse;
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
import com.df.tja.service.IOcDesignScheduleService;
import com.df.tja.service.IOcScheduleService;
import com.df.tja.service.IOcSchemeService;

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
    @RequestMapping(value = "/main/{proId}", method = RequestMethod.GET)
    public String viewMain(@PathVariable("proId")String proId, Model model) throws RuntimeException{
        CustProject projectMore = projectService.queryByProId(proId);
        model.addAttribute("project", projectMore);
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
        
        //检查本周是否存在填报主记录
        OcSchedule entity = new OcSchedule();
        entity.setProId(proId);
        entity.setOtherCondition(" GETDATE() BETWEEN WEEK_START AND WEEK_END");
        Integer count = ocScheduleService.queryCountByCondition(OcSchedule.class, entity);
        if (count == 0) {
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
        }
        return "/tjad/oc/schedule/schedule_of_design";
    }

    /**
     * <p>描述 : 设计师填报页面加载完整模式列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/design/ajaxload/{phaseId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse loadFull(@PathVariable("phaseId") String phaseId){
        BaseResponse response = null;
        try {
            List<CustOcDesignSchedule> designs = designScheduleService.queryDesignSchedulesById(phaseId);
            response = new BaseResponse("true", "加载成功");
            response.putDataValue("designs", designs);
        } catch (RuntimeException ex) {
            LoggerProxy.error(ex.getMessage(), ex);
            response = new BaseResponse("false", "加载失败");
        }
        return response;
    }

}
