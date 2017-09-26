/**
 * 项目名称:tja-web
 *
 * com.df.tja.oc
 *
 * YieldSchemeController.java
 * 
 * 2017年9月21日-下午3:08:24
 *
 * 2017 TabZhu-版权所有 
 */

package com.df.tja.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.df.framework.base.controller.BaseController;

/**
 * <p>YieldSchemeController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月21日 下午3:08:24</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/yield/scheme")
public class YieldSchemeController extends BaseController {

    /**
     * 
     * <p>描述 : 施工图产值策划列表  </p>
     *
     * @return
     * @throws RuntimeException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() throws RuntimeException {
        return "/tjad/oc/yieldsch/yield_scheme_list";
    }

    /**
     * 
     * <p>描述 : 施工图产值策划编辑</p>
     *
     * @param proId
     * @return
     */
    @RequestMapping(value = "/edit/{proId}", method = RequestMethod.GET)
    public String toedit(@PathVariable("proId") String proId) throws RuntimeException {

        return "/tjad/oc/yieldsch/yield_scheme_edit";
    }
}
