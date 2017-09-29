/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * WfPlanSchemeDaoHbmImpl.java
 * 
 * 2017年9月22日-下午2:08:50
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IWfPlanSchemeDao;
import com.df.tja.domain.WfPlanScheme;
import com.df.tja.domain.WfShemeTeam;

/**
 * <p>WfPlanSchemeDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月22日 下午2:08:50</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class WfPlanSchemeDaoHbmImpl extends BaseDaoHbmImpl implements IWfPlanSchemeDao {

    /** 
     * @see com.df.tja.dao.IWfPlanSchemeDao#selectWfShemeTeamsByWfId(java.lang.String)
     */
    @Override
    public List<WfShemeTeam> selectWfShemeTeamsByWfId(String id) {
        StringBuilder sql = new StringBuilder("");
        sql.append(" SELECT WS.ID AS id,WS.WF_ID AS wfId,WS.STAFF_ID AS staffId,WS.REF_RATE AS refRate,  ");
        sql.append(" WS.REF_YIELD AS refYield,WS.STAFF_SORT AS staffSort,HS.NAME As staffName            ");
        sql.append(" FROM WF_SHEME_TEAM WS LEFT JOIN HR_STAFF_TM HS ON WS.STAFF_ID = HS.ID               ");
        sql.append(" WHERE WS.WF_ID = ?                                                                  ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfShemeTeam.class));
        query.setString(0, id);
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfPlanSchemeDao#selectWfPlanSchemeById(java.lang.String)
     */
    @Override
    public WfPlanScheme selectWfPlanSchemeById(String id) {
        StringBuilder sql = new StringBuilder("");
        sql.append("SELECT WP.ID AS id, WP.PRO_ID AS proId, WP.ITEM_GRADE AS itemGrade,        ");
        sql.append("  WP.DESIGN_START AS designStart, WP.DESIGN_COMPLETED AS designCompleted,  ");
        sql.append("  WP.SCHEME_YIELD AS schemeYield, WP.RECEPT_DEPT_ID As receptDeptId,       ");
        sql.append("  HO.ORG_NAME AS orgName, WP.SCHEME_OVERVIEW AS schemeOverview, FM.PROC_ID ");
        sql.append("  AS procId, FM.AUDIT_STATUS AS auditStatus, FM.SEQ_NO AS seqNo,           ");
        sql.append("  WP.CREATOR AS creator, WP.REMARK As remark                               ");
        sql.append("FROM WF_PLAN_SCHEME WP                                                     ");
        sql.append("INNER JOIN WF_FLOW_MAIN FM ON WP.ID = FM.ID                                ");
        sql.append("LEFT JOIN HR_ORG_TM HO ON WP.RECEPT_DEPT_ID = HO.ID                        ");
        sql.append("WHERE WP.ID = ?                                                            ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfPlanScheme.class));
        query.setString(0, id);
        return (WfPlanScheme) query.uniqueResult();
    }

}
