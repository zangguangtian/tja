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
import com.df.framework.sys.domain.SysNotice;
import com.df.framework.sys.service.ISysNoticeService;
import com.df.framework.util.HttpUtil;
import com.df.tja.service.IWfWeekFillService;
import com.df.tja.service.IWfYearMonthFillService;
import com.df.tja.service.IWfYieldSettleService;

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

    @Autowired
    private IWfYearMonthFillService yearMonthFillService;

    @Autowired
    private IWfYieldSettleService wfYieldSettleService;

    @Autowired
    private ISysNoticeService sysNoticeService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) throws RuntimeException {
        String userId = HttpUtil.getUser().getId();

        //公告
        List<SysNotice> sysNoticeList = sysNoticeService.queryHomeSysNoticeList();
        model.addAttribute("sysNoticeList", sysNoticeList);

        //待审事务列表
        Pagination approvePage = new Pagination(1, 5);
        List<CustTask> tasks = processApproveService.queryApprovingTask(userId, approvePage);
        model.addAttribute("tasks", tasks);
        model.addAttribute("approvePage", approvePage);

        //待阅事务列表
        Pagination readPage = new Pagination(1, 5);
        List<CustSysMessage> messes = processApproveService.queryReadingTask(userId, readPage);
        model.addAttribute("messes", messes);
        model.addAttribute("readPage", readPage);

        //项目周报列表
        model.addAttribute("weeks", weekFillService.queryWeekList(userId));
        model.addAttribute("weekCount", weekFillService.queryWeekListCount(userId));

        //项目月报列表
        model.addAttribute("months", yearMonthFillService.queryYmList(userId, "1000"));
        model.addAttribute("monthCount", yearMonthFillService.queryYmListCount(userId, "1000"));

        //项目年报列表
        model.addAttribute("years", yearMonthFillService.queryYmList(userId, "2000"));
        model.addAttribute("yearCount", yearMonthFillService.queryYmListCount(userId, "2000"));

        //年度产值结算
        model.addAttribute("yieldSettles", wfYieldSettleService.queryYieldSettleList(userId));
        model.addAttribute("yieldSettleCount", wfYieldSettleService.queryYieldSettleListCount(userId));

        return "/tjad/dashboard/dashboard";
    }
}
