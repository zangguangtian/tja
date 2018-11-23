/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao.impl
 *
 * OcSettleYieldDaoHbmImpl.java
 * 
 * 2017年9月18日-下午3:57:38
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.util.DateUtil;
import com.df.tja.dao.IOcSettleYieldDao;
import com.df.tja.domain.OcSettleYield;

/**
 * <p>OcSettleYieldDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:57:38</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Repository("ocSettleYieldDao")
public class OcSettleYieldDaoHbmImpl extends BaseDaoHbmImpl implements IOcSettleYieldDao {

    @Override
    public OcSettleYield querySettleYield(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                                   ");
        sql.append("      /*主键 */                                             ");
        sql.append("      t1.ID AS id,                                         ");
        sql.append("      /*项目id */                                         ");
        sql.append("      t1.PRO_ID AS proId,                                  ");
        sql.append("      /*预估 产值(¥)*/                                      ");
        sql.append("      t1.ESTIMATE_YIELD AS estimateYield,                  ");
        sql.append("      /*可结算 产值(¥)*/                                    ");
        sql.append("      t1.SETTLE_YIELD AS settleYield,                      ");
        sql.append("      /*更新人 */                                           ");
        sql.append("      t2.REAL_NAME AS modifier,                            ");
        sql.append("      /*更新时间 */                                         ");
        sql.append("      t1.MODIFY_DATE AS modifyDate                         ");
        sql.append("  FROM                                                     ");
        sql.append("      OC_SETTLE_YIELD t1                                   ");
        sql.append("  LEFT JOIN SYS_USER t2 ON t1.MODIFIER = t2.ID             ");
        sql.append("  WHERE                                                    ");
        sql.append("      t1.ID = ?                                            ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, id);
        query.setResultTransformer(Transformers.aliasToBean(OcSettleYield.class));
        return (OcSettleYield) query.uniqueResult();
    }

    /** 
     * @see com.df.tja.dao.IOcSettleYieldDao#insertMegerSettleYield(java.util.Date)
     */
    @Override
    public void insertMegerSettleYield(Date date) {
        flushSession();
        StringBuilder sql = new StringBuilder();
        sql.append(" MERGE INTO OC_SETTLE_YIELD AS T USING                                 ");
        sql.append(" OC_SETTLE_YIELD_IMP AS S ON T.PRO_ID = S.PRO_ID                       ");
        sql.append(" AND T.PERIOD_ID = S.PERIOD_ID                                         ");
        sql.append(" WHEN MATCHED AND CONVERT(varchar(100), S.CREATE_DATE, 20) = :date     ");
        sql.append(" THEN UPDATE SET T.CREATE_DATE = S.CREATE_DATE,                        ");
        sql.append("  T.CREATOR = S.CREATOR,                                               ");
        sql.append("  T.ESTIMATE_YIELD = S.ESTIMATE_YIELD,                                 ");
        sql.append("  T.MODIFIER = S.CREATOR,                                              ");
        sql.append("  T.MODIFY_DATE = S.CREATE_DATE,                                       ");
        sql.append("  T.PERIOD_ID = S.PERIOD_ID,                                           ");
        sql.append("  T.PRO_ID = S.PRO_ID                                                  ");
        sql.append(" WHEN NOT MATCHED AND CONVERT(varchar(100), S.CREATE_DATE, 20) = :date ");
        sql.append(" THEN INSERT VALUES                                                    ");
        sql.append("     (                                                                 ");
        sql.append("         S.ID,                                                         ");
        sql.append("         S.PERIOD_ID,                                                  ");
        sql.append("         S.PRO_ID,                                                     ");
        sql.append("         S.ESTIMATE_YIELD,                                             ");
        sql.append("         S.SETTLE_YIELD,                                               ");
        sql.append("         S.CREATOR,                                                    ");
        sql.append("         S.CREATE_DATE,                                                ");
        sql.append("         S.CREATOR,                                                    ");
        sql.append("         S.CREATE_DATE                                                 ");
        sql.append("     );                                                                ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString("date", DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
        query.executeUpdate();
    }

}
