/**
 * 项目名称:df-pro-service
 *
 * com.df.project.service.impl
 *
 * WfYearMonthFillServiceImpl.java
 * 
 * 2017年9月18日-下午3:53:46
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.activiti.domain.ProcessArgs;
import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.StringUtil;
import com.df.project.dao.IProMajorRoleRateDao;
import com.df.project.domain.ProMajorRoleRate;
import com.df.project.domain.Project;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.dao.IWfYearMonthFillDao;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.WfMajorProgressRecord;
import com.df.tja.domain.WfYearMonthFill;
import com.df.tja.domain.cust.WfYearMonthFillMore;
import com.df.tja.service.IWfYearMonthFillService;

/**
 * <p>WfYearMonthFillServiceImpl</p>
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
@Service("wfYearMonthFillService")
public class WfYearMonthFillServiceImpl extends BaseServiceImpl implements IWfYearMonthFillService {

    @Autowired
    private IWfYearMonthFillDao wfYearMonthFillDao;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IProMajorRoleRateDao proMajorRoleRateDao;

    @Override
    public WfYearMonthFillMore queryWfYearMonthFill(String id, String proId, String periodId) throws RuntimeException {
        WfYearMonthFillMore yearMonthFillMore = new WfYearMonthFillMore();
        WfYearMonthFill yearMonthFill = null;
        List<WfMajorProgressRecord> majorProgressList = new ArrayList<WfMajorProgressRecord>(0);
        try {
            if (!"0".equals(id)) {
                yearMonthFill = wfYearMonthFillDao.queryWfYearMonthFill(id);
                proId = yearMonthFill.getProId();
                periodId = yearMonthFill.getPeriodId();

                // 专业完成进度
                WfMajorProgressRecord mprSerch = new WfMajorProgressRecord();
                mprSerch.setWfId(id);
                majorProgressList = this.queryByCondition(WfMajorProgressRecord.class, mprSerch);
            } else {
                List<ProMajorRoleRate> majorRoleRateList = proMajorRoleRateDao.queryProMajorRoleRate(proId, "PM.MAJOR");
                if (majorRoleRateList != null && !majorRoleRateList.isEmpty()) {
                    for (ProMajorRoleRate o : majorRoleRateList) {
                        WfMajorProgressRecord mpr = new WfMajorProgressRecord();
                        mpr.setMajorCode(o.getAllotCode());
                        mpr.setAlloteRate(o.getAllotRate());
                        mpr.setMajorName(o.getAllotName());
                        majorProgressList.add(mpr);
                    }
                }
            }
            yearMonthFillMore.setProId(proId);
            yearMonthFillMore.setPeriodId(periodId);
            yearMonthFillMore.setMajorProgressList(majorProgressList);

            CustProject project = projectService.queryByProId(proId);
            BeanUtils.copyProperties(project, yearMonthFillMore, "id");
            yearMonthFillMore.setItemStatus(project.getProStatus());

            OcPeriodManage period = this.queryByPrimaryKey(OcPeriodManage.class, periodId);
            yearMonthFillMore.setPeriodName(period.getPeriodName());

            if (yearMonthFill != null) {
                BeanUtils.copyProperties(yearMonthFill, yearMonthFillMore);
            }
        } catch (Exception e) {
            LoggerUtil.error(WfYearMonthFillServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return yearMonthFillMore;
    }

    @Override
    public String addOrModifyWfYearMonthFill(WfYearMonthFill ymFill, ProcessArgs processArgs,
                                             List<WfMajorProgressRecord> majorProgressList) throws RuntimeException,
        LogicalException {
        String id = ymFill.getId();
        try {
            if (StringUtil.isBlank(id)) {
                id = this.addEntity(WfYearMonthFill.class, ymFill);
            } else {
                this.modify(WfYearMonthFill.class, ymFill);
            }

            // 专业完成进度
            if (majorProgressList != null && !majorProgressList.isEmpty()) {
                for (WfMajorProgressRecord mpr : majorProgressList) {
                    mpr.setWfId(id);
                    if (StringUtil.isBlank(mpr.getId())) {
                        this.addEntity(WfMajorProgressRecord.class, mpr);
                    } else {
                        this.modify(WfMajorProgressRecord.class, mpr);
                    }
                }
            }

            // 项目状态
            Project project = new Project();
            project.setId(ymFill.getProId());
            project.setProStatus(ymFill.getItemStatus());
            this.modify(Project.class, project);
        } catch (Exception e) {
            LoggerUtil.error(WfYearMonthFillServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public List<WfYearMonthFillMore> queryMonthList(String userId) throws RuntimeException {
        return wfYearMonthFillDao.queryMonthList(userId);
    }

    @Override
    public int queryMonthListCount(String userId) throws RuntimeException {
        return wfYearMonthFillDao.queryMonthListCount(userId);
    }

}
