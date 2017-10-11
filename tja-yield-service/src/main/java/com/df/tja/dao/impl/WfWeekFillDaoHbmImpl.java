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

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IWfWeekFillDao;
import com.df.tja.domain.WfWeekFill;

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
    public WfWeekFill queryWfWeekFill(String proId, String periodId) {
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
        sql.append("     PRO_ID = ?                                         ");
        sql.append(" AND PERIOD_ID = ?                                      ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setParameter(1, periodId);
        query.setResultTransformer(Transformers.aliasToBean(WfWeekFill.class));
        return (WfWeekFill) query.uniqueResult();
    }

}
