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
        sql.append("      r1.allotCode AS majorCode,                                                ");
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
    public List<WfYearMonthFillMore> queryYmList(String userId, String category) {
        StringBuilder sql = new StringBuilder();
        String typeCode = null;
        if ("1000".equals(category)) {
            typeCode = "OC.PERIOD.TYPE.MONTH";
        }
        if ("2000".equals(category)) {
            typeCode = "OC.PERIOD.TYPE.YEAR";
        }

        sql.append("  SELECT TOP 5                                              ");
        sql.append("      ISNULL(WF.ID, 0) AS id,                               ");
        sql.append("      PT.ID AS proId,                                       ");
        sql.append("      PT.PRO_CODE AS proCode,                               ");
        sql.append("      PT.PRO_NAME AS proName,                               ");
        sql.append("      OPM.ID AS periodId                                    ");
        sql.append("  FROM                                                      ");
        sql.append("      (                                                     ");
        sql.append("          SELECT                                            ");
        sql.append("              *                                             ");
        sql.append("          FROM                                              ");
        sql.append("              OC_PERIOD_MANAGE OPM                          ");
        sql.append("          WHERE                                             ");
        sql.append("              TYPE_CODE = ?                                 ");
        sql.append("          AND STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'          ");
        sql.append("          AND START_DATE <= GETDATE()                       ");
        sql.append("          AND GETDATE() <= END_DATE                         ");
        sql.append("      ) OPM                                                 ");
        sql.append("  INNER JOIN PM_PROJECT_TM PT ON 1 = 1                      ");
        sql.append("  LEFT JOIN WF_YEAR_MONTH_FILL WF ON WF.PRO_ID = PT.ID      ");
        sql.append("  AND OPM.ID = WF.PERIOD_ID                                 ");
        sql.append("  AND WF.PG_CATEGORY = ?                                    ");
        sql.append("  LEFT JOIN WF_FLOW_MAIN WFM ON WF.ID = WFM.ID              ");
        sql.append("  WHERE                                                     ");
        sql.append("      1 = 1 /*AND PT.PRO_STATUS = 'PM.STATUS.SGT'*/         ");
        sql.append("  AND EXISTS (                                              ");
        sql.append("      SELECT                                                ");
        sql.append("          'X'                                               ");
        sql.append("      FROM                                                  ");
        sql.append("          V_PM_MANAGER_TEAM PPT                             ");
        sql.append("      INNER JOIN V_HR_STAFF_ALL HS ON PPT.STAFF_ID = HS.ID  ");
        sql.append("      WHERE                                                 ");
        sql.append("          PPT.PRO_ID = PT.ID                                ");
        sql.append("      AND HS.U_ID = ?                                       ");
        sql.append("  )                                                         ");
        sql.append("  AND ISNULL(WFM.AUDIT_STATUS, 0) = 0                       ");
        sql.append("  ORDER BY                                                  ");
        sql.append("      PT.PRO_CODE ASC                                       ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, typeCode);
        query.setParameter(1, category);
        query.setParameter(2, userId);
        query.setResultTransformer(Transformers.aliasToBean(WfYearMonthFillMore.class));
        return query.list();
    }

    @Override
    public int queryYmListCount(String userId, String category) {
        StringBuilder sql = new StringBuilder();
        String typeCode = null;
        if ("1000".equals(category)) {
            typeCode = "OC.PERIOD.TYPE.MONTH";
        }
        if ("2000".equals(category)) {
            typeCode = "OC.PERIOD.TYPE.YEAR";
        }

        sql.append(" SELECT                                                             ");
        sql.append("     COUNT (*) AS ymCount                                           ");
        sql.append(" FROM                                                               ");
        sql.append("     (                                                              ");
        sql.append("         SELECT                                                     ");
        sql.append("             ISNULL(WF.ID, 0) AS id,                                ");
        sql.append("             PT.ID AS proId,                                        ");
        sql.append("             PT.PRO_CODE AS proCode,                                ");
        sql.append("             PT.PRO_NAME AS proName,                                ");
        sql.append("             OPM.ID AS periodId                                     ");
        sql.append("         FROM                                                       ");
        sql.append("             (                                                      ");
        sql.append("                 SELECT                                             ");
        sql.append("                     *                                              ");
        sql.append("                 FROM                                               ");
        sql.append("                     OC_PERIOD_MANAGE OPM                           ");
        sql.append("                 WHERE                                              ");
        sql.append("                     TYPE_CODE = ?                                  ");
        sql.append("                 AND STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'           ");
        sql.append("                 AND START_DATE <= GETDATE()                        ");
        sql.append("                 AND GETDATE() <= END_DATE                          ");
        sql.append("             ) OPM                                                  ");
        sql.append("         INNER JOIN PM_PROJECT_TM PT ON 1 = 1                       ");
        sql.append("         LEFT JOIN WF_YEAR_MONTH_FILL WF ON WF.PRO_ID = PT.ID       ");
        sql.append("         AND OPM.ID = WF.PERIOD_ID                                  ");
        sql.append("         AND WF.PG_CATEGORY = ?                                     ");
        sql.append("         LEFT JOIN WF_FLOW_MAIN WFM ON WF.ID = WFM.ID               ");
        sql.append("         WHERE                                                      ");
        sql.append("             1 = 1 /*AND PT.PRO_STATUS = 'PM.STATUS.SGT'*/          ");
        sql.append("         AND EXISTS (                                               ");
        sql.append("             SELECT                                                 ");
        sql.append("                 'X'                                                ");
        sql.append("             FROM                                                   ");
        sql.append("                 V_PM_MANAGER_TEAM PPT                              ");
        sql.append("             INNER JOIN V_HR_STAFF_ALL HS ON PPT.STAFF_ID = HS.ID   ");
        sql.append("             WHERE                                                  ");
        sql.append("                 PPT.PRO_ID = PT.ID                                 ");
        sql.append("             AND HS.U_ID = ?                                        ");
        sql.append("         )                                                          ");
        sql.append("         AND ISNULL(WFM.AUDIT_STATUS, 0) = 0                        ");
        sql.append("     ) r                                                            ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, typeCode);
        query.setParameter(1, category);
        query.setParameter(2, userId);
        return (int) query.uniqueResult();
    }

}
