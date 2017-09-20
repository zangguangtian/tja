/**
 * 项目名称:tja-web
 *
 * com.df.tja.hr.controller
 *
 * OrgController.java
 * 
 * 2017年9月18日-下午3:48:16
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.df.framework.base.controller.BaseController;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.hr.domain.cust.CustTreeNode;
import com.df.hr.domain.cust.OrgPeopleVO;
import com.df.hr.service.IOrganizeService;

/**
 * <p>OrgController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:48:16</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/hr/org")
public class OrgController extends BaseController {

    @Autowired
    private IOrganizeService organizeService;

    @RequestMapping(value = "/toselect", method = {RequestMethod.GET, RequestMethod.POST})
    public String toselect(HttpServletRequest request, Model model) throws RuntimeException {
        String targetId = request.getParameter("targetId");
        String targetName = request.getParameter("targetName");
        model.addAttribute("targetId", targetId);
        model.addAttribute("targetName", targetName);
        return "/tjad/hr/org_select";
    }

    /**
     * 
     * <p>描述 : 按ID加载下级节点</p>
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/ajax/loadnode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<CustTreeNode> loadNode(HttpServletRequest request) throws RuntimeException {
        String id = request.getParameter("id");
        String context = request.getContextPath();
        List<CustTreeNode> treeNodes = organizeService.queryOrgChildren(id, context);
        return treeNodes;
    }

    /**
     * 
     * <p>描述 : 加载子机构列表</p>
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/ajax/searchstaff/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<OrgPeopleVO> searchOrgStaff(@PathVariable("id") String id, OrgPeopleVO search,
                                            @ModelAttribute("page") Pagination page, HttpServletRequest request)
        throws RuntimeException {
        search.setOrgId(id);

        String name = request.getParameter("name");
        search.setName(name);
        String orgName = request.getParameter("orgName");
        search.setOrgName(orgName);
        //TODO 未分页，根据需要考虑是否需要分页
        /*if(page == null || page.getPageNo() == 0) {
            page = new Pagination(1);
        }*/
        List<OrgPeopleVO> peoples = organizeService.queryOrgPeopleInfo(null, search);
        return peoples;
    }
}
