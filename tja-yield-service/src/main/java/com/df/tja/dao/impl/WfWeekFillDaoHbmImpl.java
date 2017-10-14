/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao.impl
 *
 * WfWeekFillDaoHbmImpl.java
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
import com.df.tja.dao.IWfWeekFillDao;
import com.df.tja.domain.WfWeekFill;
import com.df.tja.domain.cust.WfWeekFillMore;

/**
 * <p>WfWeekFillDaoHbmImpl</p>
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
@Repository("wfWeekFillDao")
public class WfWeekFillDaoHbmImpl extends BaseDaoHbmImpl implements IWfWeekFillDao {

    @Override
    public WfWeekFill queryWfWeekFill(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT                                                 ");
        sql.append("     t1.ID AS id,                                       ");
        sql.append("     t1.PERIOD_ID AS periodId,                          ");
        sql.append("     t1.PRO_ID AS proId,                                ");
        sql.append("     t1.CONTRACT_AMOUNT AS contractAmount,              ");
        sql.append("     t1.PKG_AMOUNT AS pkgAmount,                        ");
        sql.append("     t1.SCHEME_AMOUNT AS schemeAmount,                  ");
        sql.append("     t1.REBATE_AMOUNT AS rebateAmount,                  ");
        sql.append("     t1.ITEM_STATUS AS itemStatus,                      ");
        sql.append("     t1.PHASE_CODE AS phaseCode,                        ");
        sql.append("     t1.PHASE_START AS phaseStart,                      ");
        sql.append("     t1.DURATION AS duration,                           ");
        sql.append("     t1.WEEK_PROGRESS AS weekProgress,                  ");
        sql.append("     t1.WEEK_YIELD AS weekYield,                        ");
        sql.append("     t1.WEEK_EVOLVE AS weekEvolve,                      ");
        sql.append("     t1.WORK_PLAN AS workPlan,                          ");
        sql.append("     t1.FILING AS filing,                               ");
        sql.append("     t1.FILING_ESTIMATE AS filingEstimate,              ");
        sql.append("     t1.FEE_ESTIMATE AS feeEstimate,                    ");
        sql.append("     t1.OPERATION_ESTIMATE AS operationEstimate,        ");
        sql.append("     t1.CREATE_DATE AS createDate,                      ");
        sql.append("     t2.AUDIT_STATUS AS auditStatus,                    ");
        sql.append("     t2.PROC_ID AS procId,                              ");
        sql.append("     t2.SEQ_NO AS seqNo,                                ");
        sql.append("     t3.REAL_NAME AS creator                            ");
        sql.append(" FROM                                                   ");
        sql.append("     WF_WEEK_FILL t1                                    ");
        sql.append(" INNER JOIN WF_FLOW_MAIN t2 ON t1.ID = t2.ID            ");
        sql.append(" LEFT JOIN SYS_USER t3 ON t1.CREATOR = t3.ID            ");
        sql.append(" WHERE                                                  ");
        sql.append("     t1.ID = ?                                          ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, id);
        query.setResultTransformer(Transformers.aliasToBean(WfWeekFill.class));
        return (WfWeekFill) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<WfWeekFillMore> queryWeekList(String userId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT TOP 5                                              ");
        sql.append("      t2.ID AS proId,                                       ");
        sql.append("      t2.PRO_CODE AS proCode,                               ");
        sql.append("      t2.PRO_NAME AS proName,                               ");
        sql.append("      t3.ID AS periodId                                     ");
        sql.append("  FROM                                                      ");
        sql.append("      OC_PERIOD_ADVANCE_FILL t1                             ");
        sql.append("  INNER JOIN PM_PROJECT_TM t2 ON t1.PRO_ID = t2.ID          ");
        sql.append("  INNER JOIN OC_PERIOD_MANAGE t3 ON t1.PERIOD_ID = t3.ID    ");
        sql.append("  LEFT JOIN PM_PRO_TEAM_TM t4 ON t2.ID = t4.PRO_ID          ");
        sql.append("  AND t4.MAIN_FLAG = 1                                      ");
        sql.append("  AND t4.TEAM_ROLE = 'PM.TEAM.ROLE.LEADER'                  ");
        sql.append("  LEFT JOIN PM_PRO_TEAM_TM t5 ON t2.ID = t5.PRO_ID          ");
        sql.append("  AND t5.MAIN_FLAG = 1                                      ");
        sql.append("  AND t5.TEAM_ROLE = 'PM.TEAM.ROLE.PM'                      ");
        sql.append("  WHERE                                                     ");
        sql.append("      t3.START_DATE < GETDATE()                             ");
        sql.append("  AND t3.END_DATE > GETDATE()                               ");
        sql.append("  AND t3.STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'               ");
        sql.append("  AND t2.PRO_STATUS = 'PM.STATUS.SGT'                       ");
        sql.append("  AND (                                                     ");
        sql.append("      t4.STAFF_ID = (select STAFF_ID from SYS_USER where ID = ?)    ");
        sql.append("      OR t5.STAFF_ID = (select STAFF_ID from SYS_USER where ID = ?) ");
        sql.append("  )                                                         ");
        sql.append("  AND NOT EXISTS (                                          ");
        sql.append("      SELECT                                                ");
        sql.append("          1                                                 ");
        sql.append("      FROM                                                  ");
        sql.append("          WF_WEEK_FILL t6                                   ");
        sql.append("      WHERE                                                 ");
        sql.append("          t6.PRO_ID = t2.ID                                 ");
        sql.append("      AND t6.PERIOD_ID = t3.ID                              ");
        sql.append("  )                                                         ");
        sql.append("  ORDER BY                                                  ");
        sql.append("      t2.PRO_CODE ASC                                       ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, userId);
        query.setParameter(1, userId);
        query.setResultTransformer(Transformers.aliasToBean(WfWeekFillMore.class));
        return query.list();
    }

    @Override
    public int queryWeekListCount(String userId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                                               ");
        sql.append("      COUNT (*) AS weeksCount                                          ");
        sql.append("  FROM                                                                 ");
        sql.append("      (                                                                ");
        sql.append("          SELECT                                                       ");
        sql.append("              t2.ID AS proId,                                          ");
        sql.append("              t2.PRO_CODE AS proCode,                                  ");
        sql.append("              t2.PRO_NAME AS proName,                                  ");
        sql.append("              t3.ID AS periodId                                        ");
        sql.append("          FROM                                                         ");
        sql.append("              OC_PERIOD_ADVANCE_FILL t1                                ");
        sql.append("          INNER JOIN PM_PROJECT_TM t2 ON t1.PRO_ID = t2.ID             ");
        sql.append("          INNER JOIN OC_PERIOD_MANAGE t3 ON t1.PERIOD_ID = t3.ID       ");
        sql.append("          LEFT JOIN PM_PRO_TEAM_TM t4 ON t2.ID = t4.PRO_ID             ");
        sql.append("          AND t4.MAIN_FLAG = 1                                         ");
        sql.append("          AND t4.TEAM_ROLE = 'PM.TEAM.ROLE.LEADER'                     ");
        sql.append("          LEFT JOIN PM_PRO_TEAM_TM t5 ON t2.ID = t5.PRO_ID             ");
        sql.append("          AND t5.MAIN_FLAG = 1                                         ");
        sql.append("          AND t5.TEAM_ROLE = 'PM.TEAM.ROLE.PM'                         ");
        sql.append("          WHERE                                                        ");
        sql.append("              t3.START_DATE < GETDATE()                                ");
        sql.append("          AND t3.END_DATE > GETDATE()                                  ");
        sql.append("          AND t3.STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'                  ");
        sql.append("          AND t2.PRO_STATUS = 'PM.STATUS.SGT'                          ");
        sql.append("          AND (                                                        ");
        sql.append("              t4.STAFF_ID = (select STAFF_ID from SYS_USER where ID = ?)    ");
        sql.append("              OR t5.STAFF_ID = (select STAFF_ID from SYS_USER where ID = ?) ");
        sql.append("          )                                                            ");
        sql.append("          AND NOT EXISTS (                                             ");
        sql.append("              SELECT                                                   ");
        sql.append("                  1                                                    ");
        sql.append("              FROM                                                     ");
        sql.append("                  WF_WEEK_FILL t6                                      ");
        sql.append("              WHERE                                                    ");
        sql.append("                  t6.PRO_ID = t2.ID                                    ");
        sql.append("              AND t6.PERIOD_ID = t3.ID                                 ");
        sql.append("          )                                                            ");
        sql.append("      ) t                                                              ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, userId);
        query.setParameter(1, userId);
        return (int) query.uniqueResult();
    }

}
