package com.df.tja.oc.controller;

import com.df.framework.base.controller.BaseController;
import com.df.framework.exception.LogicalException;
import com.df.project.domain.Project;
import com.df.project.domain.ProjectExtend;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.domain.OcScheme;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.CustSchemeMajorNode;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ProjectPlanningController extends BaseController {


    @Autowired
    private IProjectService projectService;

    @Autowired
    private IOcSchemeService ocSchemeService;

    @Autowired
    private IOcSchemeStageMajorService ocSchemeStageMajorService;

    @Autowired
    private IOcSchemeDivisorService ocSchemeDivisorService;

    @Autowired
    private IProjectPlanningService projectPlanningService;

    /**
     *
     * <p>描述 : 项目策划列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list() throws RuntimeException {
        return "/tjad/oc/proplanning/project_planning_list";
    }

    /**
     * <p>描述 : 项目策划信息编辑页面  </p>
     *
     * @return proId
     * @throws RuntimeException
     */
    @RequestMapping(value = "/{proId}",method = {RequestMethod.GET, RequestMethod.POST})
    public String toedit(@PathVariable("proId") String proId, Model model) throws  RuntimeException {
        CustProject project = projectService.queryByProId(proId);
        model.addAttribute("project",project);
        /** 策划 */
        OcScheme ocScheme = ocSchemeService.queryByProId(proId);
        if(ocScheme != null && !ocScheme.getProWbs().equals("")){
            model.addAttribute("upd",true);
        }else{
            ocScheme = ocSchemeService.addOcScheme(proId);
            model.addAttribute("upd",false);
        }
        model.addAttribute("ocScheme",ocScheme);

        /** 完整模式 */
        List<OcSchemeStageMajor> ocSchemeStageMajorList = ocSchemeStageMajorService.queryFullList(proId);
        model.addAttribute("ocSchemeStageMajorList",ocSchemeStageMajorList);

        /** 简化模式 */
        List<OcSchemeDivisorModel> ocSchemeDivisorModelList = ocSchemeDivisorService.querySimpleList(proId);
        model.addAttribute("ocSchemeDivisorModelList",ocSchemeDivisorModelList);

        return "/tjad/oc/proplanning/project_planning_edit";
    }

    /**
     * <p>描述 : 保存</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ajax/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> save(@ModelAttribute OcSchemeMode ocSchemeMode){
        Map<String, String> result = new HashMap<>();
        try {
            projectPlanningService.submitProjectPlanning(ocSchemeMode);
            result.put("flag", "true");
            result.put("msg", "保存成功");
        } catch (RuntimeException e) {
            result.put("flag", "false");
            result.put("msg", "保存失败");
            throw new RuntimeException(e);
        } catch (LogicalException ex) {
            result.put("flag", "false");
            result.put("msg", ex.getMess());
        }
        return result;
    }

    /**
     * <p>描述 : 打开添加节点页面 </p>
     *
     * @param proId
     * @param schemeId
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/ajax/addnode/{proId}/{schemeId}/{parentId}", method = RequestMethod.GET)
    public String toAddNode(@PathVariable("proId") String proId, @PathVariable("schemeId") String schemeId,
                            @PathVariable("parentId") String parentId,Model model)
            throws RuntimeException {
        model.addAttribute("parentId", parentId);
        model.addAttribute("proId", proId);
        model.addAttribute("schemeId", schemeId);
        return "/tjad/oc/proplanning/project_planning_addnode";
    }

    /**
     * <p>描述 : 节点保存</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ajax/nodesave",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> nodesave(@ModelAttribute OcSchemeDivisor ocSchemeDivisor){
        Map<String, String> result = new HashMap<>();
        try {
            ocSchemeDivisorService.addNode(ocSchemeDivisor);
            result.put("flag", "true");
            result.put("msg", "保存成功");
        } catch (RuntimeException e) {
            result.put("flag", "false");
            result.put("msg", "保存失败");
            throw new RuntimeException(e);
        }catch (LogicalException ex) {
            result.put("flag", "false");
            result.put("msg", ex.getMess());
        }
        return result;
    }

    /**
     * <p>描述 : 打开添加人员页面 </p>
     *
     * @param proId
     * @param schemeId
     * @param model
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/ajax/adduser/{proId}/{schemeId}", method = RequestMethod.GET)
    public String toAddUser(@PathVariable("proId") String proId, @PathVariable("schemeId") String schemeId,
                            Model model)
            throws RuntimeException {
        model.addAttribute("proId", proId);
        model.addAttribute("schemeId", schemeId);
        return "/tjad/oc/proplanning/project_planning_adduser";
    }

    /**
     * <p>描述 : 人员保存</p>
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/ajax/usersave",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> usersave(@RequestBody CustSchemeMajorNode custSchemeMajorNode){
        Map<String, String> result = new HashMap<>();
        try {
            for (OcSchemeDivisor ocSchemeDivisor :custSchemeMajorNode.getUserDivisors()) {
                ocSchemeDivisorService.addUser(ocSchemeDivisor);
            }
            result.put("flag", "true");
            result.put("msg", "保存成功");
        } catch (RuntimeException e) {
            result.put("flag", "false");
            result.put("msg", "保存失败");
            throw new RuntimeException(e);
        }catch (LogicalException ex) {
            result.put("flag", "false");
            result.put("msg", ex.getMess());
        }
        return result;
    }

}
