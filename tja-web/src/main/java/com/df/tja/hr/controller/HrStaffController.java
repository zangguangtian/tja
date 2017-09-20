/**
 * 项目名称:tja-web
 *
 * com.df.tja.hr.controller
 *
 * HrStaffController.java
 * 
 * 2017年9月19日-下午5:24:03
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.hr.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.df.framework.base.controller.BaseController;
import com.df.framework.util.StringUtil;
import com.df.hr.domain.cust.OrgPeopleVO;

/**
 * <p>HrStaffController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月19日 下午5:24:03</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/hr")
public class HrStaffController extends BaseController {

    /**
     * 
     * <p>描述 : 通用页面选择人员</p>
     *
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/staff/toselectstaff", method = {RequestMethod.GET, RequestMethod.POST})
    public String toselectstaff(HttpServletRequest request, Model model) throws RuntimeException {
        String callMethod = request.getParameter("callMethod");
        String openType = request.getParameter("openType");//通用页面选择人员的类型，radio; checkbox; random 三种
        String userJson = request.getParameter("user");
        OrgPeopleVO vo = new OrgPeopleVO();
        if (StringUtil.isNotBlank(userJson)) {
            JSONObject obj = new JSONObject(userJson);
            if (StringUtil.isNotBlank((String) obj.get("pFlag"))) {
                vo.setPositiveFlag(Boolean.parseBoolean((String) obj.get("pFlag")));
            }
        }

        model.addAttribute("openType", openType);

        model.addAttribute("vo", vo);
        model.addAttribute("callMethod", callMethod);
        return "/tjad/hr/org_select_staff";
    }

}
