/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IReportDao.java
 * 
 * 2017年11月21日-下午4:44:06
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.project.domain.cust.CustProject;
import com.df.tja.domain.cust.YieldDeptDetial;

/**
 * <p>IReportDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月21日 下午4:44:06</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IReportDao extends IBaseDao {

    /**
     * <p>描述 : </p>
     *
     * @param periodId
     * @param page
     * @return
     */
    List<CustProject> selectProByperiod(String periodId, Pagination page);

    /**
     * <p>描述 : </p>
     *
     * @param orgId
     * @param periodId
     * @param proIds
     * @return
     */
    List<YieldDeptDetial> selectStaffYield(String orgId, String periodId, List<String> proIds);

}
