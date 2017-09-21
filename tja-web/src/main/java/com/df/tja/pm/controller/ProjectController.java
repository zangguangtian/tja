/**
 * 项目名称:df-sys-web
 *
 * com.df.framework.project.controller
 *
 * ProjectController.java
 * 
 * 2017年9月18日-下午4:45:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.pm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.project.domain.more.ProjectMore;
import com.df.project.service.IProjectService;

/**
 * <p>ProjectController</p>
 * 
 * <p>描述：</p>
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
@RequestMapping("/admin/pm/project")
public class ProjectController extends BaseController {

    @Autowired
    IProjectService projectService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/tjad/pm/project/project_list";
    }

    /**
     * <p>描述 : 编辑</p>
     * @param proCode 项目编号
     */
    @RequestMapping(value = "/edit/{proId}")
    public String edit(@PathVariable("proId") String proId, Model model) throws RuntimeException {
        ProjectMore projectMore = projectService.queryByProId(proId);
        model.addAttribute("project", projectMore);
        return "/tjad/pm/project/project_edit";
    }

    /**
     * 
     * <p>描述 : 保存</p>
     * @param projectMore
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute ProjectMore projectMore) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            projectService.modifyProject(projectMore);
            resultMap.put("flag", "true");
            resultMap.put("msg", SAVE_SUCCESS);
        } catch (RuntimeException e) {
            resultMap.put("flag", "false");
            resultMap.put("msg", SAVE_ERROR);
            logger.error("", e);
        }
        return resultMap;
    }

}
