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

        sql.append("  SELECT TOP 5                                                ");
        sql.append("      ISNULL(WF.ID, 0) AS id,                                 ");
        sql.append("      PT.ID AS proId,                                         ");
        sql.append("      PT.PRO_CODE AS proCode,                                 ");
        sql.append("      PT.PRO_NAME AS proName,                                 ");
        sql.append("      OPM.ID AS periodId                                      ");
        sql.append("  FROM                                                        ");
        sql.append("      (                                                       ");
        sql.append("          SELECT                                              ");
        sql.append("              *                                               ");
        sql.append("          FROM                                                ");
        sql.append("              OC_PERIOD_MANAGE OPM                            ");
        sql.append("          WHERE                                               ");
        sql.append("              TYPE_CODE = 'OC.PERIOD.TYPE.WEEK'               ");
        sql.append("          AND STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'            ");
        sql.append("          AND START_DATE <= GETDATE()                         ");
        sql.append("          AND GETDATE() <= END_DATE                           ");
        sql.append("      ) OPM,                                                  ");
        sql.append("      PM_PROJECT_TM PT                                        ");
        sql.append("  LEFT JOIN WF_WEEK_FILL WF ON WF.PRO_ID = PT.ID              ");
        sql.append("  LEFT JOIN WF_FLOW_MAIN WFM ON WF.ID = WFM.ID                ");
        sql.append("  WHERE                                                       ");
        sql.append("      PT.PRO_STATUS = 'PM.STATUS.SGT'                         ");
        sql.append("  AND EXISTS (                                                ");
        sql.append("      SELECT                                                  ");
        sql.append("          'X'                                                 ");
        sql.append("      FROM                                                    ");
        sql.append("          V_PM_MANAGER_TEAM PPT                               ");
        sql.append("      INNER JOIN V_HR_STAFF_ALL HS ON PPT.STAFF_ID = HS.ID    ");
        sql.append("      WHERE                                                   ");
        sql.append("          PPT.PRO_ID = PT.ID                                  ");
        sql.append("      AND HS.U_ID = ?                                         ");
        sql.append("  )                                                           ");
        sql.append("  AND ISNULL(WFM.AUDIT_STATUS, 0) = 0                         ");
        sql.append("  ORDER BY                                                    ");
        sql.append("      PT.PRO_CODE ASC                                         ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, userId);
        query.setResultTransformer(Transformers.aliasToBean(WfWeekFillMore.class));
        return query.list();
    }

    @Override
    public int queryWeekListCount(String userId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                                              ");
        sql.append("      COUNT (*) AS weeksCount                                         ");
        sql.append("  FROM                                                                ");
        sql.append("      (                                                               ");
        sql.append("          SELECT                                                      ");
        sql.append("              ISNULL(WF.ID, 0) AS id,                                 ");
        sql.append("              PT.ID AS proId,                                         ");
        sql.append("              PT.PRO_CODE AS proCode,                                 ");
        sql.append("              PT.PRO_NAME AS proName,                                 ");
        sql.append("              OPM.ID AS periodId                                      ");
        sql.append("          FROM                                                        ");
        sql.append("              (                                                       ");
        sql.append("                  SELECT                                              ");
        sql.append("                      *                                               ");
        sql.append("                  FROM                                                ");
        sql.append("                      OC_PERIOD_MANAGE OPM                            ");
        sql.append("                  WHERE                                               ");
        sql.append("                      TYPE_CODE = 'OC.PERIOD.TYPE.WEEK'               ");
        sql.append("                  AND STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'            ");
        sql.append("                  AND START_DATE <= GETDATE()                         ");
        sql.append("                  AND GETDATE() <= END_DATE                           ");
        sql.append("              ) OPM,                                                  ");
        sql.append("              PM_PROJECT_TM PT                                        ");
        sql.append("          LEFT JOIN WF_WEEK_FILL WF ON WF.PRO_ID = PT.ID              ");
        sql.append("          LEFT JOIN WF_FLOW_MAIN WFM ON WF.ID = WFM.ID                ");
        sql.append("          WHERE                                                       ");
        sql.append("              PT.PRO_STATUS = 'PM.STATUS.SGT'                         ");
        sql.append("          AND EXISTS (                                                ");
        sql.append("              SELECT                                                  ");
        sql.append("                  'X'                                                 ");
        sql.append("              FROM                                                    ");
        sql.append("                  V_PM_MANAGER_TEAM PPT                               ");
        sql.append("              INNER JOIN V_HR_STAFF_ALL HS ON PPT.STAFF_ID = HS.ID    ");
        sql.append("              WHERE                                                   ");
        sql.append("                  PPT.PRO_ID = PT.ID                                  ");
        sql.append("              AND HS.U_ID = ?                                         ");
        sql.append("          )                                                           ");
        sql.append("          AND ISNULL(WFM.AUDIT_STATUS, 0) = 0                         ");
        sql.append("      ) t                                                             ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, userId);
        return (int) query.uniqueResult();
    }

}
