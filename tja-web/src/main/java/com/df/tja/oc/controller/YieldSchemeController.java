/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc.controller
 *
 * YieldSchemeController.java
 * 
 * 2017年11月14日-下午3:48:18
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.oc.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.HttpUtil;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.StringUtil;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.constant.TjaConstant;
import com.df.tja.domain.OcYieldScheme;
import com.df.tja.domain.cust.CustOcYieldMajor;
import com.df.tja.domain.cust.CustOcYieldMajorDuty;
import com.df.tja.domain.cust.CustOcYieldScheme;
import com.df.tja.domain.cust.CustOcYieldStageMajor;
import com.df.tja.service.IYieldSchemeService;
import com.df.tja.service.IYmConfigService;

/**
 * <p>YieldSchemeController</p>
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
@RequestMapping("/admin/yield/scheme")
public class YieldSchemeController extends BaseController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IYmConfigService ymConfigService;

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private IYieldSchemeService yieldSchemeService;

    private String[][] schemeStages = new String[][] {{"preliminary", "初设"}, {"drawing", "施工图"}, {"subTotal", "小计"},
        {"coordination", "施工配合"}, {"cap", "施工配合-封顶"}, {"check", "施工配合-验收"}};

    /**
     * 
     * <p>描述 : 施工图产值策划列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() throws RuntimeException {
        return "/tjad/oc/yieldsch/yield_scheme_list";
    }

    /**
     * 
     * <p>描述 : 加载基本信息编辑页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/baseEdit/{id}", method = RequestMethod.POST)
    public String baseEdit(@PathVariable("id") String id, Model model) {
        //取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //项目信息
        CustProject project = projectService.queryByProId(yieldScheme.getProId());
        model.addAttribute("project", project);
        return "/tjad/oc/yieldsch/yield_scheme_edit_base";
    }

    /**
     * 
     * <p>描述 : 保存基本信息 </p>
     *
     * @param yieldScheme
     * @return
     */
    @RequestMapping(value = "/ajax/baseSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> baseSave(CustOcYieldScheme yieldScheme) {
        Map<String, String> result = new HashMap<String, String>(0);
        try {
            yieldSchemeService.modify(OcYieldScheme.class, yieldScheme);
            result.put("flag", "true");
            result.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException ex) {
            result.put("flag", "false");
            result.put("msg", SAVE_ERROR);
        }
        return result;
    }

    /**
     * 
     * <p>描述 : 加载基本信息查看页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/baseView/{id}", method = RequestMethod.POST)
    public String baseView(@PathVariable("id") String id, Model model) {
        //取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //项目信息
        CustProject project = projectService.queryByProId(yieldScheme.getProId());
        model.addAttribute("project", project);
        return "/tjad/oc/yieldsch/yield_scheme_view_base";
    }

    /**
     * 
     * <p>描述 : 加载专业比例编辑页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/ratioEdit/{id}", method = RequestMethod.POST)
    public String ratioEdit(@PathVariable("id") String id, Model model) {
        //查询所有土建基准单价及专业比例 
        List<CustOcYieldMajor> prices = yieldSchemeService.queryMajorAllPrices(id);
        model.addAttribute("prices", prices);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询专业及比例
        List<CustOcYieldMajor> yieldMajors = yieldSchemeService.queryOcYieldMajors(id);
        model.addAttribute("yieldMajors", yieldMajors);
        return "/tjad/oc/yieldsch/yield_scheme_edit_ratio";
    }

    /**
     * 
     * <p>描述 : 保存专业比例 </p>
     *
     * @param yieldScheme
     * @return
     */
    @RequestMapping(value = "/ajax/ratioSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> ratioSave(@RequestBody CustOcYieldScheme yieldScheme) {
        Map<String, String> result = new HashMap<String, String>(0);
        try {
            yieldSchemeService.createYieldSchemeRatio(yieldScheme);
            result.put("flag", "true");
            result.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException ex) {
            LoggerUtil.error(YieldSchemeController.class, null, ex);
            result.put("flag", "false");
            result.put("msg", SAVE_ERROR);
        }
        return result;
    }

    /**
     * 
     * <p>描述 : 加载专业比例查看页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/ratioView/{id}", method = RequestMethod.POST)
    public String ratioView(@PathVariable("id") String id, Model model) {
        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询专业及比例
        List<CustOcYieldMajor> yieldMajors = yieldSchemeService.queryOcYieldMajors(id);
        model.addAttribute("yieldMajors", yieldMajors);
        return "/tjad/oc/yieldsch/yield_scheme_view_ratio";
    }

    /**
     * 
     * <p>描述 : 加载土建产值编辑页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/civilEdit/{id}", method = RequestMethod.POST)
    public String civilEdit(@PathVariable("id") String id, Model model) {
        ///取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldMajorDuty> duties = yieldSchemeService.queryOcYieldMajorDutiesBySchemeId(id);
        model.addAttribute("yieldDuties", duties);
        return "/tjad/oc/yieldsch/yield_scheme_edit_civil";
    }

    /**
     * 
     * <p>描述 : 保存土建产值 </p>
     *
     * @param yieldScheme
     * @return
     */
    @RequestMapping(value = "/ajax/civilSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> civilSave(@RequestBody CustOcYieldScheme yieldScheme) {
        Map<String, String> result = new HashMap<String, String>(0);
        try {
            yieldSchemeService.createYieldSchemeCivil(yieldScheme);
            result.put("flag", "true");
            result.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException ex) {
            LoggerUtil.error(YieldSchemeController.class, null, ex);
            result.put("flag", "false");
            result.put("msg", SAVE_ERROR);
        }
        return result;
    }

    /**
     * 
     * <p>描述 : 加载土建产值查看页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/civilView/{id}", method = RequestMethod.POST)
    public String civilView(@PathVariable("id") String id, Model model) {
        ///取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldMajorDuty> duties = yieldSchemeService.queryOcYieldMajorDutiesBySchemeId(id);
        model.addAttribute("yieldDuties", duties);
        return "/tjad/oc/yieldsch/yield_scheme_view_civil";
    }

    /**
     * 
     * <p>描述 : 加载各专业产值编辑页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/stageEdit/{id}", method = RequestMethod.POST)
    public String stageEdit(@PathVariable("id") String id, Model model) {

        model.addAttribute("schemeStages", schemeStages);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldStageMajor> stageMajors = yieldSchemeService.queryOcYieldStageMajorsBySchemeId(id);
        model.addAttribute("stageMajors", stageMajors);

        return "/tjad/oc/yieldsch/yield_scheme_edit_stage";
    }

    /**
     * 
     * <p>描述 : 保存各专业产值 </p>
     *
     * @param yieldScheme
     * @return
     */
    @RequestMapping(value = "/ajax/stageSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> stageSave(@RequestBody CustOcYieldScheme yieldScheme) {
        Map<String, String> result = new HashMap<String, String>(0);
        try {
            yieldSchemeService.createYieldSchemeStage(yieldScheme);
            result.put("flag", "true");
            result.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException ex) {
            LoggerUtil.error(YieldSchemeController.class, null, ex);
            result.put("flag", "false");
            result.put("msg", SAVE_ERROR);
        }
        return result;
    }

    /**
     * 
     * <p>描述 : 加载各专业产值查看页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/stageView/{id}", method = RequestMethod.POST)
    public String stageView(@PathVariable("id") String id, Model model) {
        model.addAttribute("schemeStages", schemeStages);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldStageMajor> stageMajors = yieldSchemeService.queryOcYieldStageMajorsBySchemeId(id);
        model.addAttribute("stageMajors", stageMajors);
        return "/tjad/oc/yieldsch/yield_scheme_view_stage";
    }

    /**
     * 
     * <p>描述 : 加载各专业产值编辑页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/principalEdit/{id}", method = RequestMethod.POST)
    public String principalEdit(@PathVariable("id") String id, Model model) {
        //取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldMajorDuty> duties = yieldSchemeService.queryOcYieldMajorDutiesBySchemeId(id);
        model.addAttribute("yieldDuties", duties);

        return "/tjad/oc/yieldsch/yield_scheme_edit_principal";
    }

    /**
     * 
     * <p>描述 : 保存各专业产值 </p>
     *
     * @param yieldScheme
     * @return
     */
    @RequestMapping(value = "/ajax/principalSave", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> principalSave(@RequestBody CustOcYieldScheme yieldScheme) {
        Map<String, String> result = new HashMap<String, String>(0);
        try {
            yieldSchemeService.createYieldSchemePrincipal(yieldScheme);
            result.put("flag", "true");
            result.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException ex) {
            LoggerUtil.error(YieldSchemeController.class, null, ex);
            result.put("flag", "false");
            result.put("msg", SAVE_ERROR);
        }
        return result;
    }

    /**
     * 
     * <p>描述 : 加载各专业产值查看页面</p>
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/principalView/{id}", method = RequestMethod.POST)
    public String principalView(@PathVariable("id") String id, Model model) {
        //取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldMajorDuty> duties = yieldSchemeService.queryOcYieldMajorDutiesBySchemeId(id);
        model.addAttribute("yieldDuties", duties);
        return "/tjad/oc/yieldsch/yield_scheme_view_principal";
    }

    /**
     * 打印预览
     * @param id
     * @return
     */
    @RequestMapping(value = {"/viewprev/{id}", "/view/{id}", "/toprint/{id}"}, method = RequestMethod.GET)
    public String toprint(@PathVariable("id") String id, Model model) {
        HttpServletRequest request = HttpUtil.getHttpServletRequest();
        if (request.getRequestURI().contains("/viewprev")) { //此地址后面跟的是项目ID
            //因为一个项目只会做一个施工图产值策划，所以在打开编辑页面时就插入OcYieldScheme记录
            OcYieldScheme entity = new OcYieldScheme();
            entity.setProId(id);
            String schemeId = null;
            List<OcYieldScheme> schemes = yieldSchemeService.queryByCondition(OcYieldScheme.class, entity);
            if (schemes != null && !schemes.isEmpty()) {
                schemeId = schemes.get(0).getId();
            } else {
                schemeId = yieldSchemeService.createYieldScheme(id);
            }
            return "redirect:/admin/yield/scheme/view/" + StringUtil.emptyValues(schemeId, "0");
        }

        //打印预览
        if (request.getRequestURI().contains("/toprint")) {
            model.addAttribute("print", "print");
        }
        model.addAttribute("schemeStages", schemeStages);

        //取90%
        BigDecimal ratioParam = ymConfigService.queryOcRebateParam();
        if (ratioParam == null) {
            ratioParam = new BigDecimal("0");
        }
        model.addAttribute("ratioParam", ratioParam);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //取策划主表
        OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
        model.addAttribute("yieldScheme", yieldScheme);

        //项目信息
        CustProject project = projectService.queryByProId(yieldScheme.getProId());
        model.addAttribute("project", project);

        //查询专业及比例
        List<CustOcYieldMajor> yieldMajors = yieldSchemeService.queryOcYieldMajors(id);
        model.addAttribute("yieldMajors", yieldMajors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldStageMajor> stageMajors = yieldSchemeService.queryOcYieldStageMajorsBySchemeId(id);
        model.addAttribute("stageMajors", stageMajors);

        //查询各专业部门产值及负责人
        Map<String, CustOcYieldMajorDuty> duties = yieldSchemeService.queryOcYieldMajorDutiesBySchemeId(id);
        model.addAttribute("yieldDuties", duties);
        return "/tjad/oc/yieldsch/yield_scheme_view";
    }
}
