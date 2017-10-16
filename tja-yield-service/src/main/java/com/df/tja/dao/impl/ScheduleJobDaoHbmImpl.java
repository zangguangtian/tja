/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * ScheduleJobDaoHbmImpl.java
 * 
 * 2017年10月16日-下午4:30:11
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IScheduleJobDao;

/**
 * <p>ScheduleJobDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月16日 下午4:30:11</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class ScheduleJobDaoHbmImpl extends BaseDaoHbmImpl implements IScheduleJobDao {

    /** 
     * @see com.df.tja.dao.IScheduleJobDao#updateWriteStoreProce(java.lang.String)
     */
    @Override
    public void updateWriteStoreProce(final String func) {
        String backMsg;
        try {
            backMsg = getCurrentSession().doReturningWork(new ReturningWork<String>() {
                public String execute(Connection connection) throws SQLException {
                    String result = null;
                    CallableStatement cstmt;
                    try {
                        cstmt = connection.prepareCall("{call " + func + "()}");
                        cstmt.execute();
                    } catch (SQLException ex) {
                        throw ex;
                    }
                    return result;
                }
            });
        } catch (Exception e) {
            if (logger.isEnabledFor(Level.ERROR)) {
                logger.error("ScheduleJobDaoHbmImpl dao exec writeBackPm method failure", e);
            }
            throw e;
        }
    }

}
