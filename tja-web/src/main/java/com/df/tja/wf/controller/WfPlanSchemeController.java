/**
 * 项目名称:tja-web
 *
 * com.df.tja.controller.wf
 *
 * WfPlanScheme.java
 * 
 * 2017年9月18日-上午11:30:16
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.wf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>WfPlanScheme</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 上午11:30:16</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/wf/planScheme")
public class WfPlanSchemeController {


    /**
     * 
     * <p>描述 : 跳到list页面</p>
     *
     * @return
     */
    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String search() {
        return "/tjad/wf/planScheme/planScheme_list";
    }

    /**
     * 
     * <p>描述 : 修改或添加明细页面</p>
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/toedit/{id}", method = {RequestMethod.GET})
    public String toEdit(Model model, @PathVariable("id") String id) {
        if (!"0".equals(id)) {
            //修改

        }
        return "/tjad/wf/planScheme/planScheme_edit";
    }

}
