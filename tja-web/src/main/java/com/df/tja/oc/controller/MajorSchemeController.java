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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.df.framework.base.controller.BaseController;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;

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
@RequestMapping("/admin/major/scheme")
public class MajorSchemeController extends BaseController {

    @Autowired
    private IProjectService projectService;

    /**
     * 
     * <p>描述 : 专业策划列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() throws RuntimeException {
        return "/tjad/oc/majorsch/major_scheme_list";
    }

    /**
     * 
     * <p>描述 : 专业策划编辑</p>
     *
     * @param proId
     * @param schemeId
     * @param majorId
     * @return
     */
    @RequestMapping(value = "/edit/{proId}/{schemeId}/{majorId}")
    public String edit(@PathVariable("proId") String proId, @PathVariable("schemeId") String schemeId,
                       @PathVariable("majorId") String majorId, Model model) {
        //获取项目信息
        CustProject custProject = projectService.queryProInfoById(proId);
        model.addAttribute("project", custProject);
        return "/tjad/oc/majorsch/major_scheme_edit";
    }
}
