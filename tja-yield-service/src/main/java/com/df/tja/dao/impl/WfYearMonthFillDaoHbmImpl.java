/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao.impl
 *
 * WfYearMonthFillDaoHbmImpl.java
 * 
 * 2017年9月18日-下午3:57:38
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IWfYearMonthFillDao;
import com.df.tja.domain.WfMajorProgressRecord;
import com.df.tja.domain.WfYearMonthFill;
import com.df.tja.domain.cust.WfYearMonthFillMore;

/**
 * <p>WfYearMonthFillDaoHbmImpl</p>
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
@Repository("wfYearMonthFillDao")
public class WfYearMonthFillDaoHbmImpl extends BaseDaoHbmImpl implements IWfYearMonthFillDao {

    @Override
    public WfYearMonthFill queryWfYearMonthFill(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                        ");
        sql.append("      t1.ID AS id,                              ");
        sql.append("      t1.PG_CATEGORY AS pgCategory,             ");
        sql.append("      t1.PERIOD_ID AS periodId,                 ");
        sql.append("      t1.PRO_ID AS proId,                       ");
        sql.append("      t1.CONTRACT_AMOUNT AS contractAmount,     ");
        sql.append("      t1.PKG_AMOUNT AS pkgAmount,               ");
        sql.append("      t1.SCHEME_AMOUNT AS schemeAmount,         ");
        sql.append("      t1.REBATE_AMOUNT AS rebateAmount,         ");
        sql.append("      t1.ITEM_STATUS AS itemStatus,             ");
        sql.append("      t1.PROGRESS_EXPLAIN AS progressExplain,   ");
        sql.append("      t1.REMARK AS remark,                      ");
        sql.append("      t1.CREATE_DATE AS createDate,             ");
        sql.append("      t2.AUDIT_STATUS AS auditStatus,           ");
        sql.append("      t2.PROC_ID AS procId,                     ");
        sql.append("      t2.SEQ_NO AS seqNo,                       ");
        sql.append("      t3.REAL_NAME AS creator                   ");
        sql.append("  FROM                                          ");
        sql.append("      WF_YEAR_MONTH_FILL t1                     ");
        sql.append("  INNER JOIN WF_FLOW_MAIN t2 ON t1.ID = t2.ID   ");
        sql.append("  LEFT JOIN SYS_USER t3 ON t1.CREATOR = t3.ID   ");
        sql.append("  WHERE                                         ");
        sql.append("      t1.ID = ?                                 ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, id);
        query.setResultTransformer(Transformers.aliasToBean(WfYearMonthFill.class));
        return (WfYearMonthFill) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WfMajorProgressRecord> queryMajorProgress(String proId, String category) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                                                         ");
        sql.append("      r2.MAJOR_CODE AS majorCode,                                                ");
        sql.append("      r1.allotName AS majorName,                                                 ");
        sql.append("      r1.alloteRate,                                                              ");
        sql.append("      r2.accRate,                                                                ");
        sql.append("      r2.accYield                                                                ");
        sql.append("  FROM                                                                           ");
        sql.append("      (                                                                          ");
        sql.append("          SELECT                                                                 ");
        sql.append("              SC.CONFIG_CODE AS allotCode,                                       ");
        sql.append("              SC.CONFIG_NAME AS allotName,                                       ");
        sql.append("              ISNULL(PR.ALLOT_RATE, 0) AS alloteRate,                             ");
        sql.append("              SC.CONFIG_SORT                                                     ");
        sql.append("          FROM                                                                   ");
        sql.append("              V_SYS_CONFIG SC                                                    ");
        sql.append("          LEFT JOIN PM_PRO_MAJOR_ROLE_RATE PR ON SC.CONFIG_CODE = PR.ALLOT_CODE  ");
        sql.append("          AND PR.PRO_ID = ?                                                      ");
        sql.append("          WHERE                                                                  ");
        sql.append("              SC.F_CODE = 'PM.MAJOR'                                             ");
        sql.append("      ) r1                                                                       ");
        sql.append("  LEFT JOIN (                                                                    ");
        sql.append("      SELECT                                                                     ");
        sql.append("          t1.MAJOR_CODE,                                                         ");
        sql.append("          SUM (t1.COMPLETE_RATE) AS accRate,                                     ");
        sql.append("          SUM (t1.REF_YIELD) AS accYield                                         ");
        sql.append("      FROM                                                                       ");
        sql.append("          WF_MAJOR_PROGRESS_RECORD t1                                            ");
        sql.append("      INNER JOIN WF_YEAR_MONTH_FILL t2 ON t1.WF_ID = t2.ID                       ");
        sql.append("      INNER JOIN WF_FLOW_MAIN t3 ON t2.ID = t3.ID                                ");
        sql.append("      WHERE                                                                      ");
        sql.append("          t2.PRO_ID = ?                                                          ");
        sql.append("      AND t2.PG_CATEGORY = ?                                                     ");
        sql.append("      AND t3.AUDIT_STATUS = '2'                                                  ");
        sql.append("      GROUP BY                                                                   ");
        sql.append("          t1.MAJOR_CODE                                                          ");
        sql.append("  ) r2 ON r1.allotCode = r2.MAJOR_CODE                                           ");
        sql.append("  ORDER BY                                                                       ");
        sql.append("      r1.CONFIG_SORT                                                             ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setParameter(1, proId);
        query.setParameter(2, category);
        query.setResultTransformer(Transformers.aliasToBean(WfMajorProgressRecord.class));
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WfYearMonthFillMore> queryMonthList(String userId) {
        StringBuilder sql = new StringBuilder();

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, userId);
        query.setResultTransformer(Transformers.aliasToBean(WfYearMonthFillMore.class));
        return query.list();
    }

    @Override
    public int queryMonthListCount(String userId) {
        StringBuilder sql = new StringBuilder();

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, userId);
        return (int) query.uniqueResult();
    }

}