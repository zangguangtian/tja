package com.df.tja.oc.controller;

import com.df.framework.base.controller.BaseController;
import com.df.framework.exception.LogicalException;
import com.df.project.domain.ProjectExtend;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectApprovalService;
import com.df.project.service.IProjectService;
import com.df.tja.domain.OcScheme;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.OcSchemeDivisorModel;
import com.df.tja.domain.cust.OcSchemeMode;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import com.df.tja.service.IOcSchemeDivisorService;
import com.df.tja.service.IOcSchemeService;
import com.df.tja.service.IOcSchemeStageMajorService;
import com.df.tja.service.IProjectPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>ProjectApprovalController</p>
 *
 * <p>描述：项目</p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月19日 下午4:44:18</p>
 *
 * @author deng.jiayan
 *
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("/admin/project/approval")
public class ProjectApprovalController extends BaseController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IProjectApprovalService projectApprovalService;

    /**
     *
     * <p>描述 : 项目立项列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list() throws RuntimeException {
        return "/tjad/oc/proapproval/project_approval_list";
    }

    /**
     * <p>描述 : 项目策划信息编辑页面  </p>
     *
     * @return proId
     * @throws RuntimeException
     */
    @RequestMapping(value = "/{proId}",method = {RequestMethod.GET, RequestMethod.POST})
    public String toedit(@PathVariable("proId") String proId, Model model) throws  RuntimeException {
        CustProject projectMore = projectService.queryByProId(proId);
        model.addAttribute("project", projectMore);
        return "/tjad/oc/proapproval/project_approval_edit";
    }

    /**
     * <p>描述 : 保存</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ajax/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> save(@ModelAttribute CustProject custProject){
        Map<String, String> result = new HashMap<>();
        try {
            projectApprovalService.submitProjectApproval(custProject,1);
            result.put("flag", "true");
            result.put("msg", "保存成功");
        } catch (RuntimeException e) {
            result.put("flag", "false");
            result.put("msg", "保存失败");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>描述 : 进入主项目查看页面</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/tothemain/{preProId}",method = RequestMethod.GET)
    public String toTheMain(@PathVariable("preProId")String preProId, Model model)throws RuntimeException{
        Map<String,List<CustProject>> map = projectService.queryTheMainByPreProId(preProId);
        CustProject theMain = map.get("pre").get(0);
        List<CustProject> custList = map.get("custList");
        model.addAttribute("project",theMain);
        model.addAttribute("projectList",custList);
        return "/tjad/oc/proapproval/project_themain_check";
    }

    /**
     * <p>描述 : 主项目查看保存</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/mainajax/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> mainsave(@ModelAttribute CustProject custProject){
        Map<String, String> result = new HashMap<>();
        try {
            projectApprovalService.submitProjectApproval(custProject,2);
            result.put("flag", "true");
            result.put("msg", "保存成功");
        } catch (RuntimeException e) {
            result.put("flag", "false");
            result.put("msg", "保存失败");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>描述 : 主项目查看保存</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ajax/remove/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> remove(@PathVariable("id") String id){
        Map<String, String> result = new HashMap<>();
        try {
            projectApprovalService.removeAssociated(id);
            result.put("flag", "true");
            result.put("msg", "删除成功");
        } catch (RuntimeException e) {
            result.put("flag", "false");
            result.put("msg", "删除失败");
            throw new RuntimeException(e);
        }
        return result;
    }

}
