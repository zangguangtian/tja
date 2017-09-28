/**
 * 项目名称:df-pro-service
 *
 * com.df.project.service.impl
 *
 * PeriodServiceImpl.java
 * 
 * 2017年9月18日-下午3:53:46
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IOcPeriodAdvanceFillDao;
import com.df.tja.dao.IOcPeriodManageDao;
import com.df.tja.domain.OcPeriodAdvanceFill;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.service.IPeriodService;

/**
 * <p>PeriodServiceImpl</p>
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
@Service("periodService")
public class PeriodServiceImpl extends BaseServiceImpl implements IPeriodService {

    @Autowired
    IOcPeriodManageDao ocPeriodManageDao;

    @Autowired
    IOcPeriodAdvanceFillDao ocPeriodAdvanceFillDao;

    @Override
    public OcPeriodManage queryPeriod(String id) throws RuntimeException {
        return ocPeriodManageDao.queryPeriod(id);
    }

    @Override
    public String createOrModifyPeriod(OcPeriodManage ocPeriodManage) throws RuntimeException, LogicalException {
        String id = ocPeriodManage.getId();
        try {
            // “类型”+“期间”在已保存的记录中不得重复，如果重复，提示“该类型的期间已经存在”
            OcPeriodManage opm = new OcPeriodManage();
            opm.setTypeCode(ocPeriodManage.getTypeCode());
            opm.setPeriodName(ocPeriodManage.getPeriodName());
            List<OcPeriodManage> opmCheck = this.queryByCondition(OcPeriodManage.class, opm);
            if (opmCheck != null && !opmCheck.isEmpty() && !opmCheck.get(0).getId().equals(id)) {
                throw new LogicalException("该类型的期间已经存在");
            }

            if (StringUtil.isBlank(id)) {
                ocPeriodManage.setId(StringUtil.getUUID());
                id = this.addEntity(OcPeriodManage.class, ocPeriodManage);
            } else {
                this.modify(OcPeriodManage.class, ocPeriodManage);
            }
        } catch (LogicalException e) {
            throw e;
        } catch (Exception e) {
            LoggerUtil.error(PeriodServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public List<OcPeriodAdvanceFill> queryAdvanceFillList(OcPeriodAdvanceFill advanceFill, Pagination pagination)
        throws RuntimeException {
        List<OcPeriodAdvanceFill> advanceFillList = null;
        try {
            advanceFillList = ocPeriodAdvanceFillDao.queryAdvanceFillList(advanceFill, pagination);

            if (advanceFillList != null && !advanceFillList.isEmpty() && pagination != null) {
                int number = 0;
                for (OcPeriodAdvanceFill fill : advanceFillList) {
                    fill.setNumber((pagination.getPageNo() - 1) * pagination.getRowsPerPage() + (++number));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return advanceFillList;
    }

}
