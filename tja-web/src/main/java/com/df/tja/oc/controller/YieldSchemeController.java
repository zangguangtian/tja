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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.df.framework.base.controller.BaseController;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.domain.OcStandardPrice;
import com.df.tja.service.IStandardPriceService;
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
    private IStandardPriceService standardPriceService;

    @Autowired
    private IYmConfigService ymConfigService;

    @Autowired
    private ISysConfigService sysConfigService;

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
        //取项目
        CustProject project = projectService.queryByProId(proId);
        model.addAttribute("project", project);

        //取专业
        List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode("PM.MAJOR");
        model.addAttribute("majors", majors);

        //查询所有土建基准单价及专业比例 
        List<OcStandardPrice> prices = standardPriceService.queryAllStandardPrices();
        model.addAttribute("prices", prices);

        BigDecimal ratioParam = ymConfigService.queryOcRebateParam();
        if (ratioParam == null) {
            ratioParam = new BigDecimal("0");
        }
        model.addAttribute("ratioParam", ratioParam);

        if (!"0".equals(id)) {

        }
        return "/tjad/oc/yieldsch/yield_scheme_edit";
    }
}
