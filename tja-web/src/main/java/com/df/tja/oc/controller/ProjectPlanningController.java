package com.df.tja.oc.controller;

import com.df.project.domain.Project;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.domain.OcScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>ProjectPlanningController</p>
 *
 * <p>描述：项目策划</p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月08日 下午5:26:18</p>
 *
 * @author deng.jiayan
 *
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("/admin/project/planning")
public class ProjectPlanningController {


    @Autowired
    IProjectService projectService;

    /**
     *
     * <p>描述 : 项目策划列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() throws RuntimeException {
        return "/tjad/oc/proplanning/project_planning_list";
    }

    /**
     * <p>描述 : 项目策划信息编辑页面  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/{proId}",method = RequestMethod.GET)
    public String toedit(@PathVariable("proId") String proId, Model model) throws  RuntimeException {
        CustProject project = projectService.queryByProId(proId);
        model.addAttribute("project",project);
        /** 是否策划 */
        Integer isOcScheme = projectService.queryCountByCondition(OcScheme.class,project);
        if(isOcScheme != null && isOcScheme != 0){

        }
        return "/tjad/oc/proplanning/project_planning_edit";
    }

}
