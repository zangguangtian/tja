/**
 * 项目名称:tja-web
 *
 * com.df.tja.controller
 *
 * DashBoard.java
 * 
 * 2017年9月12日-下午6:25:42
 *
 * 2017 TabZhu-版权所有 
 */

package com.df.tja.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.df.activiti.domain.cust.CustSysMessage;
import com.df.activiti.domain.cust.CustTask;
import com.df.activiti.service.IProcessApproveService;
import com.df.framework.base.controller.BaseController;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.util.HttpUtil;
import com.df.tja.service.IWfWeekFillService;

/**
 * <p>DashBoardController</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月12日 下午6:25:42</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashBoardController extends BaseController {

    @Autowired
    private IProcessApproveService processApproveService;

    @Autowired
    private IWfWeekFillService weekFillService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) throws RuntimeException {
        //待审事务列表
        Pagination approvePage = new Pagination(1, 5);
        List<CustTask> tasks = processApproveService.queryApprovingTask(HttpUtil.getUser().getId(), approvePage);
        model.addAttribute("tasks", tasks);
        model.addAttribute("approvePage", approvePage);

        //待阅事务列表
        Pagination readPage = new Pagination(1, 5);
        List<CustSysMessage> messes = processApproveService.queryReadingTask(HttpUtil.getUser().getId(), readPage);
        model.addAttribute("messes", messes);
        model.addAttribute("readPage", readPage);

        //项目周报列表
        String userId = HttpUtil.getUser().getId();
        model.addAttribute("weeks", weekFillService.queryWeekList(userId));
        model.addAttribute("weeksCount", weekFillService.queryWeekListCount(userId));

        return "/tjad/dashboard/dashboard";
    }
}
