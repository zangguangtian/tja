/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * WfYieldSettleDaoHbmImpl.java
 * 
 * 2017年10月19日-下午2:34:45
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.hr.domain.Staff;
import com.df.hr.domain.cust.CustStaff;
import com.df.tja.dao.IWfYieldSettleDao;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldSettle;

/**
 * <p>WfYieldSettleDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月19日 下午2:34:45</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Repository("wfYieldSettleDao")
public class WfYieldSettleDaoHbmImpl extends BaseDaoHbmImpl implements IWfYieldSettleDao {

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectMajorBudgetStaff(java.lang.String, java.lang.String)
     */
    @Override
    public List<CustStaff> selectMajorBudgetStaff(String proId, String majorCode) {
        List<Staff> staffs = new ArrayList<Staff>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT PBS.STAFF_ID AS id, HS.NAME AS name,PBS.MAJOR_CODE AS majorCode, ");
        sql.append(" SC.CONFIG_NAME AS  majorName FROM PM_BUDGET_STAFF_TM PBS                ");
        sql.append(" LEFT JOIN HR_STAFF_TM HS ON PBS.STAFF_ID = HS.ID                        ");
        sql.append(" LEFT JOIN SYS_CONFIG_TM SC ON PBS.MAJOR_CODE = SC.CONFIG_CODE           ");
        sql.append(" WHERE PBS.PRO_ID = ? AND PBS.MAJOR_CODE = ?                             ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setParameter(1, majorCode);
        query.setResultTransformer(Transformers.aliasToBean(CustStaff.class));
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectPermitYieldByWfId(java.lang.String)
     */
    @Override
    public List<WfYieldMajorRoleAllot> selectMajorRoleAllotByWfId(String id) {
        List<WfYieldMajorRoleAllot> roleAllots = new ArrayList<WfYieldMajorRoleAllot>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT                                                     ");
        sql.append(" YM.ID AS id,YM.WF_ID AS wfId,YM.CATEGORY AS category,      ");
        sql.append(" YM.MAJOR_ROLE_ID AS majorRoleId,YM.ROLE_CODE AS roleCode,  ");
        sql.append(" YM.MAJOR_CODE AS majorCode,YM.STAFF_ID AS staffId,         ");
        sql.append(" YM.STAFF_RATE AS staffRate,YM.STAFF_SORT AS staffSort,     ");
        sql.append(" YM.STAFF_YIELD AS staffYield,HS.NAME AS staffName          ");
        sql.append(" FROM                                                       ");
        sql.append("     WF_YIELD_MAJOR_ROLE_ALLOT YM                           ");
        sql.append(" LEFT JOIN HR_STAFF_TM HS ON YM.STAFF_ID = HS.ID            ");
        sql.append(" WHERE YM.WF_ID = ?                                         ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldMajorRoleAllot.class));
        query.setString(0, id);
        roleAllots = query.list();
        return roleAllots;
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectWfYieldSettleById(java.lang.String)
     */
    @Override
    public WfYieldSettle selectWfYieldSettleById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT YS.ID AS id, YS.WF_CATEGORY AS wfCategory, YS.PERIOD_ID AS periodId,       ");
        sql.append(" YS.PRO_ID AS proId, YS.CONTRACT_AMOUNT AS contractAmount,                         ");
        sql.append(" YS.PKG_AMOUNT AS pkgAmount, YS.SCHEME_AMOUNT AS schemeAmount,                     ");
        sql.append(" YS.REBATE_AMOUNT AS rebateAmount, YS.YEAR_YIELD AS yearYield,                     ");
        sql.append(" YS.HISYEAR_YIELD AS hisyearYield, YS.ITEM_STATUS AS itemStatus,                   ");
        sql.append(" YS.PRINCIPAL_RATE AS principalRate, YS.PM_RATE AS pmRate,                         ");
        sql.append(" YS.PERMIT_ID AS permitId, YS.REMARK AS remark,YS.CREATOR AS  creator,             ");
        sql.append(" YS.CREATE_DATE AS createDate, SU.REAL_NAME AS creatorName,                        ");
        sql.append(" FM.SEQ_NO AS seqNo,FM.WF_DESC AS wfDesc, FM.AUDIT_STATUS AS auditStatus,          ");
        sql.append(" FM.PROC_ID AS procId  FROM  WF_YIELD_SETTLE YS                                    ");
        sql.append(" LEFT JOIN WF_FLOW_MAIN FM ON YS.ID = FM.ID                                        ");
        sql.append(" LEFT JOIN SYS_USER SU ON SU.ID = YS.CREATOR WHERE YS.ID = ?                       ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldSettle.class));
        query.setString(0, id);
        List<WfYieldSettle> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
