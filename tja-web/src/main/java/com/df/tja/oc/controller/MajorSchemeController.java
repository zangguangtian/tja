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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.exception.LogicalException;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.domain.cust.CustSchemeMajorNode;
import com.df.tja.domain.cust.OcSchemeMajorTask;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import com.df.tja.service.IMajorSchemeService;

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

    @Autowired
    private IMajorSchemeService majorSchemeService;

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
     * <p>描述 : 专业策划编辑 </p>
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

        OcSchemeStageMajor stageMajor = majorSchemeService.queryOcSchemeStageMajorById(majorId);
        model.addAttribute("stageMajor", stageMajor);

        //查询专业下的子项、任务列表
        List<OcSchemeMajorTask> subTasks = majorSchemeService.queryMajorTaskById(majorId);
        model.addAttribute("subTasks", subTasks);

        return "/tjad/oc/majorsch/major_scheme_edit";
    }

    /**
     * 
     * <p>描述 : 打开添加节点页面 </p>
     *
     * @param id
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/ajax/addnode/{majorId}/{subId}/{subSort}/{taskSort}", method = RequestMethod.GET)
    public String toAddNode(@PathVariable("majorId") String majorId, @PathVariable("subId") String subId,
                            @PathVariable("subSort") Integer subSort, @PathVariable("taskSort") Integer taskSort,
                            Model model)
            throws RuntimeException {
        if (subSort == null) {
            subSort = 0;
        }
        if (taskSort == null) {
            taskSort = 0;
        }
        model.addAttribute("subSort", subSort + 1);
        model.addAttribute("taskSort", taskSort + 1);
        model.addAttribute("majorId", majorId);
        model.addAttribute("subId", subId);
        return "/tjad/oc/majorsch/major_scheme_addnode";
    }

    /**
     * 
     * <p>描述 : 添加节点保存</p>
     *
     * @param sysConfig
     * @return
     */
    @RequestMapping(value = "/ajax/addnode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addNode(CustSchemeMajorNode schemeNode) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            majorSchemeService.createSchemeMajorNode(schemeNode);
            mess.put("success", "true");
            mess.put("mess", "保存成功!");
        } catch (LogicalException ex) {
            mess.put("success", "false");
            mess.put("mess", ex.getMess());
        } catch (RuntimeException ex) {
            mess.put("success", "false");
            mess.put("mess", "保存失败!");
        }
        return mess;
    }

    /**
     * 
     * <p>描述 : 打开添加用户页面 </p>
     *
     * @param id
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/ajax/adduser/{taskId}/{userSort}", method = RequestMethod.GET)
    public String toAddUser(@PathVariable("taskId") String taskId, @PathVariable("userSort") Integer userSort,
                            Model model)
            throws RuntimeException {
        if (userSort == null) {
            userSort = 0;
        }
        model.addAttribute("userSort", userSort + 1);
        model.addAttribute("taskId", taskId);
        return "/tjad/oc/majorsch/major_scheme_adduser";
    }

    /**
     * 
     * <p>描述 : 添加节点保存</p>
     *
     * @param sysConfig
     * @return
     */
    @RequestMapping(value = "/ajax/adduser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUser(@RequestBody CustSchemeMajorNode schemeUser) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            schemeUser.setNodeCategory("u");
            majorSchemeService.createSchemeMajorNode(schemeUser);
            mess.put("success", "true");
            mess.put("mess", "保存成功!");
        } catch (LogicalException ex) {
            mess.put("success", "false");
            mess.put("mess", ex.getMess());
        } catch (RuntimeException ex) {
            mess.put("success", "false");
            mess.put("mess", "保存失败!");
        }
        return mess;
    }
}
