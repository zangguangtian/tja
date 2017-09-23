/**
 * 项目名称:df-sys-web
 *
 * com.df.framework.project.controller
 *
 * ProStaffController.java
 * 
 * 2017年9月18日-下午4:45:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.pm.controller;

import java.util.HashMap;
import java.util.List;
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
import com.df.project.domain.ProBudgetStaff;
import com.df.project.domain.more.ProBudgetStaffMore;
import com.df.project.domain.more.ProjectMore;
import com.df.project.service.IProStaffService;
import com.df.project.service.IProjectService;

/**
 * <p>ProStaffController</p>
 * 
 * <p>描述：项目人员策划</p>
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
@RequestMapping("/admin/pm/proStaff")
public class ProStaffController extends BaseController {

    @Autowired
    IProjectService projectService;

    @Autowired
    IProStaffService proStaffService;

    /**
     * <p>描述 : 列表</p>
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "/tjad/pm/proStaff/proStaff_list";
    }

    /**
     * <p>描述 : 详细</p>
     * @param proCode 项目编号
     */
    @RequestMapping(value = "/{proId}")
    public String detial(@PathVariable("proId") String proId, Model model) throws RuntimeException {
        ProjectMore projectMore = projectService.queryByProId(proId);
        List<ProBudgetStaff> proStaffList = proStaffService.queryBudgetStaffList(proId);
        model.addAttribute("project", projectMore);
        model.addAttribute("proStaffList", proStaffList);
        return "/tjad/pm/proStaff/proStaff_detial";
    }

    /**
     * 
     * <p>描述 : 保存</p>
     * @param projectMore
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    public Map<String, String> save(@ModelAttribute ProBudgetStaffMore pbsMore) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            List<ProBudgetStaffMore> proStaffList = pbsMore.getProStaffList();
            if (proStaffList != null && !proStaffList.isEmpty()) {
                proStaffService.modifyBudgetStaff(proStaffList);
            }
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
