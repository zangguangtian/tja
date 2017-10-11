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
import com.df.tja.domain.cust.OcSettleYieldMore;

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
    public OcSettleYieldMore querySettleYield(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                                   ");
        sql.append("      /*主键 */                                             ");
        sql.append("      t1.ID AS id,                                         ");
        sql.append("      /*项目编号 */                                         ");
        sql.append("      t2.PRO_CODE AS proCode,                              ");
        sql.append("      /*项目名称 */                                         ");
        sql.append("      t2.PRO_NAME AS proName,                              ");
        sql.append("      /*合同编号 */                                         ");
        sql.append("      t4.ITEM_NO AS itemNo,                                ");
        sql.append("      /*项目类型 */                                         ");
        sql.append("      t8.FIELD_VAL AS proCategory,                         ");
        sql.append("      /*合同额 */                                           ");
        sql.append("      t2.CONTRACT_AMOUNT AS contractAmount,                ");
        sql.append("      /*分包额 */                                           ");
        sql.append("      t2.PKG_AMOUNT AS pkgAmount,                          ");
        sql.append("      /*项目负责人 */                                       ");
        sql.append("      t5.STAFF_NAME AS proFzrName,                         ");
        sql.append("      /*项目经理 */                                         ");
        sql.append("      t6.STAFF_NAME AS proJlName,                          ");
        sql.append("      /*预估 产值(¥)*/                                      ");
        sql.append("      t1.ESTIMATE_YIELD AS estimateYield,                  ");
        sql.append("      /*可结算 产值(¥)*/                                    ");
        sql.append("      t1.SETTLE_YIELD AS settleYield,                      ");
        sql.append("      /*更新人 */                                           ");
        sql.append("      t7.REAL_NAME AS modifier,                            ");
        sql.append("      /*更新时间 */                                         ");
        sql.append("      t1.MODIFY_DATE AS modifyDate                         ");
        sql.append("  FROM                                                     ");
        sql.append("      OC_SETTLE_YIELD t1                                   ");
        sql.append("  LEFT JOIN PM_PROJECT_TM t2 ON t1.PRO_ID = t2.ID          ");
        sql.append("  LEFT JOIN PM_CONTRACT_PRO_TM t3 ON t2.ID = t3.PRO_ID     ");
        sql.append("  LEFT JOIN PM_CONTRACT_TM t4 ON t3.CONTRACT_ID = t4.ID    ");
        sql.append("  LEFT JOIN V_PM_MANAGER_TEAM t5 ON t2.ID = t5.PRO_ID      ");
        sql.append("    AND t5.TEAM_ROLE = 'PM.TEAM.ROLE.LEADER'               ");
        sql.append("  LEFT JOIN V_PM_MANAGER_TEAM t6 ON t2.ID = t6.PRO_ID      ");
        sql.append("    AND t6.TEAM_ROLE = 'PM.TEAM.ROLE.PM'                   ");
        sql.append("  LEFT JOIN SYS_USER t7 ON t1.MODIFIER = t7.ID             ");
        sql.append("  LEFT JOIN PM_CHECKBOX_TM t8 ON t2.ID = t8.REF_ID         ");
        sql.append("    AND t8.REF_OBJ = '01'                                  ");
        sql.append("    AND t8.FIELD_OWNER = '0102'                            ");
        sql.append("  WHERE                                                    ");
        sql.append("      t1.ID = ?                                            ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, id);
        query.setResultTransformer(Transformers.aliasToBean(OcSettleYieldMore.class));
        return (OcSettleYieldMore) query.uniqueResult();
    }

    /** 
     * @see com.df.tja.dao.IOcSettleYieldDao#insertMegerSettleYield(java.util.Date)
     */
    @Override
    public void insertMegerSettleYield(Date date) {
        StringBuilder sql = new StringBuilder();
        sql.append(" MERGE INTO OC_SETTLE_YIELD AS T USING                              ");
        sql.append(" OC_SETTLE_YIELD_IMP AS S ON T.PRO_ID = S.PRO_ID                    ");
        sql.append(" AND T.PERIOD_ID = S.PERIOD_ID                                      ");
        sql.append(" AND T.CREATE_DATE = S.CREATE_DATE                                  ");
        sql.append(" WHEN MATCHED THEN UPDATE SET                                       ");
        sql.append("  T.CREATE_DATE = S.CREATE_DATE,                                    ");
        sql.append("  T.CREATOR = S.CREATOR,                                            ");
        sql.append("  T.ESTIMATE_YIELD = S.ESTIMATE_YIELD,                              ");
        sql.append("  T.MODIFIER = S.CREATOR,                                           ");
        sql.append("  T.MODIFY_DATE = S.CREATE_DATE,                                    ");
        sql.append("  T.PERIOD_ID = S.PERIOD_ID,                                        ");
        sql.append("  T.PRO_ID = S.PRO_ID                                               ");
        sql.append(" WHEN NOT MATCHED AND CONVERT(varchar(100), S.CREATE_DATE, 20) = ?  ");
        sql.append(" THEN INSERT VALUES                                                 ");
        sql.append("     (                                                              ");
        sql.append("         S.ID,                                                      ");
        sql.append("         S.PERIOD_ID,                                               ");
        sql.append("         S.PRO_ID,                                                  ");
        sql.append("         S.ESTIMATE_YIELD,                                          ");
        sql.append("         S.SETTLE_YIELD,                                            ");
        sql.append("         S.CREATOR,                                                 ");
        sql.append("         S.CREATE_DATE,                                             ");
        sql.append("         S.CREATOR,                                                 ");
        sql.append("         S.CREATE_DATE                                              ");
        sql.append("     );                                                             ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
        query.executeUpdate();
    }

}
