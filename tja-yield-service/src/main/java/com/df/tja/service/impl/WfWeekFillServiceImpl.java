/**
 * 项目名称:df-pro-service
 *
 * com.df.project.service.impl
 *
 * WfWeekFillServiceImpl.java
 * 
 * 2017年9月18日-下午3:53:46
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.activiti.domain.ProcessArgs;
import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.StringUtil;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.dao.IWfWeekFillDao;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.WfWeekFill;
import com.df.tja.domain.cust.WfWeekFillMore;
import com.df.tja.service.IWfWeekFillService;

/**
 * <p>WfWeekFillServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:53:46</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Service("wfWeekFillService")
public class WfWeekFillServiceImpl extends BaseServiceImpl implements IWfWeekFillService {

    @Autowired
    IWfWeekFillDao wfWeekFillDao;

    @Autowired
    IProjectService projectService;

    @Override
    public WfWeekFillMore queryWfWeekFill(String id, String proId, String periodId) throws RuntimeException {
        WfWeekFillMore wfWeekFillMore = new WfWeekFillMore();
        WfWeekFill weekFill = null;
        try {
            if (!"0".equals(id)) {
                weekFill = wfWeekFillDao.queryWfWeekFill(id);
                proId = weekFill.getProId();
                periodId = weekFill.getPeriodId();
            }
            wfWeekFillMore.setProId(proId);
            wfWeekFillMore.setPeriodId(periodId);

            CustProject projectMore = projectService.queryByProId(proId);
            BeanUtils.copyProperties(projectMore, wfWeekFillMore, "id");

            OcPeriodManage period = this.queryByPrimaryKey(OcPeriodManage.class, periodId);
            wfWeekFillMore.setPeriodName(period.getPeriodName());
            wfWeekFillMore.setRangeStart(period.getRangeStart());
            wfWeekFillMore.setRangeEnd(period.getRangeEnd());

            if (weekFill != null) {
                BeanUtils.copyProperties(weekFill, wfWeekFillMore);
            }
        } catch (Exception e) {
            LoggerUtil.error(WfWeekFillServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return wfWeekFillMore;
    }

    @Override
    public String addOrModifyWfWeekFill(WfWeekFill wfWeekFill, ProcessArgs processArgs) throws RuntimeException,
        LogicalException {
        String id = wfWeekFill.getId();
        try {
            if (StringUtil.isBlank(id)) {
                id = this.addEntity(WfWeekFill.class, wfWeekFill);
            } else {
                this.modify(WfWeekFill.class, wfWeekFill);
            }
        } catch (Exception e) {
            LoggerUtil.error(WfWeekFillServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public List<WfWeekFillMore> queryWeekList(String userId) throws RuntimeException {
        return wfWeekFillDao.queryWeekList(userId);
    }

    @Override
    public int queryWeekListCount(String userId) throws RuntimeException {
        return wfWeekFillDao.queryWeekListCount(userId);
    }

}
