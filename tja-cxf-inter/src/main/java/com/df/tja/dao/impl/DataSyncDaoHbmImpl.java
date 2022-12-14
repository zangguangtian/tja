/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.dao
 *
 * DataSyncDaoHbmImpl.java
 * 
 * 2017年10月10日-下午3:03:21
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IDataSyncDao;

/**
 * <p>DataSyncDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月10日 下午3:03:21</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

@Repository("dataSyncDao")
public class DataSyncDaoHbmImpl extends BaseDaoHbmImpl implements IDataSyncDao {

    public void writeBackSyncData(final String dataType) {
        //在存储过程调用前先flush。flush只是将Hibernate缓存的数据保存到数据库中，但事务还没有提交
        flushSession();

        getCurrentSession().doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement cstmt = null;
                try {
                    cstmt = connection.prepareCall("{call ups_IT_DATA_SYNC(?)}");
                    cstmt.setString(1, dataType);
                    cstmt.execute();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        });
    }

    public void updateCostOfItem(BigDecimal value, String itemId) {
        String sql = "update it_project_info set work_cost = ?, wc_create_date = ? where id = ?";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setBigDecimal(0, value);
        query.setDate(1, new Date());
        query.setString(2, itemId);
        query.executeUpdate();
    }
}
