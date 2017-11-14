/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc
 *
 * YieldSchemeController.java
 * 
 * 2017年9月21日-下午3:08:24
 *
 * 2017 TabZhu-版权所有 
 */

package com.df.tja.oc.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.ObjectUtils;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.constant.TjaConstant;
import com.df.tja.domain.OcYieldScheme;
import com.df.tja.domain.cust.CustOcYieldMajor;
import com.df.tja.domain.cust.CustOcYieldMajorDuty;
import com.df.tja.domain.cust.CustOcYieldScheme;
import com.df.tja.domain.cust.CustOcYieldStageMajor;
import com.df.tja.service.IWfPlanSchemeService;
import com.df.tja.service.IYieldSchemeService;
import com.df.tja.service.IYmConfigService;

/**
 * <p>YieldSchemeController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月21日 下午3:08:24</p>
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

    @Autowired
    private IWfPlanSchemeService wfPlanSchemeService;

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
     * <p>描述 : 施工图产值策划编辑</p>
     *
     * @param proId
     * @return
     */
    @RequestMapping(value = "/edit/{proId}/{id}", method = RequestMethod.GET)
    public String toedit(@PathVariable("proId") String proId, @PathVariable("id") String id, Model model)
        throws RuntimeException {
        model.addAttribute("schemeStages", schemeStages);

        //取项目
        CustProject project = projectService.queryByProId(proId);
        model.addAttribute("project", project);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode(TjaConstant.SGTMajor.MAJORPARENT,
            TjaConstant.SGTMajor.IGNORED_MAJOR);
        model.addAttribute("majors", majors);

        //查询所有土建基准单价及专业比例 
        List<CustOcYieldMajor> prices = yieldSchemeService.queryMajorAllPrices(id);
        model.addAttribute("prices", prices);

        //取90%
        BigDecimal ratioParam = ymConfigService.queryOcRebateParam();
        if (ratioParam == null) {
            ratioParam = new BigDecimal("0");
        }
        model.addAttribute("ratioParam", ratioParam);

        CustOcYieldScheme ocYieldScheme = new CustOcYieldScheme();
        if (!"0".equals(id)) {
            OcYieldScheme yieldScheme = yieldSchemeService.queryOcYieldSchemeById(id);
            if (yieldScheme != null) {
                ObjectUtils.copyProperties(yieldScheme, ocYieldScheme);
            }

            //查询专业及比例
            List<CustOcYieldMajor> yieldMajors = yieldSchemeService.queryOcYieldMajors(id);
            model.addAttribute("yieldMajors", yieldMajors);

            //查询各专业部门产值及负责人
            Map<String, CustOcYieldMajorDuty> duties = yieldSchemeService.queryOcYieldMajorDutiesBySchemeId(id);
            model.addAttribute("yieldDuties", duties);

            //查询各专业部门产值及负责人
            Map<String, CustOcYieldStageMajor> stageMajors = yieldSchemeService.queryOcYieldStageMajorsBySchemeId(id);
            model.addAttribute("stageMajors", stageMajors);
        }

        //查询本项目的方案产值
        BigDecimal planYield = wfPlanSchemeService.queryPlanYieldByProId(proId);
        ocYieldScheme.setSchemeAmount(planYield);
        model.addAttribute("yieldScheme", ocYieldScheme);

        return "/tjad/oc/yieldsch/yield_scheme_edit";
    }

    /**
     * 施工图产值策划保存
     * @param yieldScheme
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(CustOcYieldScheme yieldScheme) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            if (!isRepeatSubmit()) { //防止重复提交
                yieldSchemeService.createOrModifyYieldScheme(yieldScheme);
                resultMap.put("flag", "true");
                resultMap.put("msg", SAVE_SUCCESS);
            } else {
                resultMap.put("flag", "false");
                resultMap.put("msg", REPEAT_SUBMIT_MESS);
            }
        } catch (RuntimeException ex) {
            resultMap.put("flag", "false");
            resultMap.put("msg", "保存失败");
            logger.error("", ex);
        }
        return resultMap;
    }

    /**
     * 打印预览
     * @param id
     * @return
     */
    @RequestMapping(value = "/toprint/{id}", method = RequestMethod.GET)
    public String toprint(@PathVariable("id") String id, Model model) {
        model.addAttribute("schemeStages", schemeStages);

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
