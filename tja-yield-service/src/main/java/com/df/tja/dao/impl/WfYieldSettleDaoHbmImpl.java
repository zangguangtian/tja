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
import com.df.hr.domain.cust.CustStaff;
import com.df.tja.dao.IWfYieldSettleDao;
import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldMajorRoleRate;
import com.df.tja.domain.WfYieldPrincipalAllot;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.CustYieldSettle;

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
     * @see com.df.tja.dao.IWfYieldSettleDao#selectBudgetStaffByRole(java.lang.String, java.lang.String)
     */
    @Override
    public List<CustStaff> selectBudgetStaffByRole(String proId, String roleCode) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT PBS.STAFF_ID AS id, HS.NAME AS name         ");
        sql.append("  FROM PM_BUDGET_STAFF_TM PBS                       ");
        sql.append(" LEFT JOIN HR_STAFF_TM HS ON PBS.STAFF_ID = HS.ID   ");
        sql.append(" WHERE PBS.PRO_ID = ? AND PBS.INVOLVED_ROLE = ?     ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setParameter(1, roleCode);
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

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectMajorByProId(java.lang.String)
     */
    @Override
    public List<WfYieldMajorRate> selectMajorByProId(String proId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT PBS.MAJOR_CODE AS majorCode,                    ");
        sql.append(" SC.CONFIG_NAME AS majorName,V.REF_MAJOR_RATE AS settleRate      ");
        sql.append(" FROM PM_BUDGET_STAFF_TM PBS                                     ");
        sql.append(" LEFT JOIN SYS_CONFIG_TM SC ON PBS.MAJOR_CODE = SC.CONFIG_CODE   ");
        sql.append(" LEFT JOIN V_PM_REF_MAJOR_RATE V ON PBS.PRO_ID = V.PRO_ID        ");
        sql.append(" AND PBS.MAJOR_CODE = V.CONFIG_CODE                              ");
        sql.append(" WHERE PBS.PRO_ID = ?                                            ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldMajorRate.class));
        query.setString(0, proId);
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectPrincipalAllot(java.lang.String, java.lang.String)
     */
    @Override
    public List<WfYieldPrincipalAllot> selectPrincipalAllot(String id, String staffCategory) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT                                                                        ");
        sql.append(" YP.ID AS id,YP.WF_ID AS wfId,YP.STAFF_CATEGORY AS staffCategory,              ");
        sql.append(" YP.STAFF_ID AS staffId,YP.STAFF_SORT AS staffSort,YP.STAFF_RATE AS staffRate, ");
        sql.append(" YP.STAFF_YIELD AS staffYield,HS.NAME AS staffName                             ");
        sql.append(" FROM WF_YIELD_PRINCIPAL_ALLOT YP                                              ");
        sql.append(" LEFT JOIN HR_STAFF_TM HS ON YP.STAFF_ID = HS.ID                               ");
        sql.append(" WHERE YP.WF_ID = ? AND YP.STAFF_CATEGORY = ?                                     ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldPrincipalAllot.class));
        query.setString(0, id);
        query.setString(1, staffCategory);
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectMajorRate(java.lang.String)
     */
    @Override
    public List<WfYieldMajorRate> selectMajorRate(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT                                                                                 ");
        sql.append(" YM.ID AS id,YM.WF_ID AS wfId,YM.MAJOR_CODE AS majorCode,                               ");
        sql.append(" YM.MAJOR_SORT AS majorSort,YM.SETTLE_RATE AS settleRate,                               ");
        sql.append(" SC.CONFIG_NAME AS majorName                                                            ");
        sql.append(" FROM WF_YIELD_MAJOR_RATE YM                                                            ");
        sql.append(" LEFT JOIN SYS_CONFIG_TM SC ON YM.MAJOR_CODE = SC.CONFIG_CODE WHERE YM.WF_ID = ?        ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldMajorRate.class));
        query.setString(0, id);
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectMajorRoleRate(java.lang.String, java.lang.String)
     */
    @Override
    public List<WfYieldMajorRoleRate> selectMajorRoleRate(String wfId, String majorCode) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT                                                           ");
        sql.append(" MR.ID AS id,                                                     ");
        sql.append(" MR.WF_ID AS wfId,                                                ");
        sql.append(" MR.MAJOR_CODE AS majorCode,                                      ");
        sql.append(" MR.ROLE_CODE AS roleCode,                                        ");
        sql.append(" MR.ROLE_SORT AS roleSort,                                        ");
        sql.append(" MR.ALLOT_RATE AS allotRate,                                      ");
        sql.append(" MR.MAJOR_RATE_ID AS majorRateId,                                 ");
        sql.append(" MR.CREATE_DATE AS createDate,                                    ");
        sql.append(" SC.CONFIG_NAME AS roleName                                       ");
        sql.append(" FROM                                                             ");
        sql.append("   WF_YIELD_MAJOR_ROLE_RATE MR                                    ");
        sql.append(" LEFT JOIN SYS_CONFIG_TM SC ON MR.ROLE_CODE = SC.CONFIG_CODE      ");
        sql.append(" WHERE MR.WF_ID = ? AND MR.MAJOR_CODE = ?                         ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldMajorRoleRate.class));
        query.setString(0, wfId);
        query.setString(1, majorCode);
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectMajorRoleAllot(java.lang.String, java.lang.String)
     */
    @Override
    public List<WfYieldMajorRoleAllot> selectMajorRoleAllot(String wfId, String majorCode) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT                                                            ");
        sql.append(" MR.ID AS id,                                                      ");
        sql.append(" MR.WF_ID AS wfId,                                                 ");
        sql.append(" MR.CATEGORY AS category,                                          ");
        sql.append(" MR.MAJOR_ROLE_ID AS majorRoleId,                                  ");
        sql.append(" MR.ROLE_CODE AS roleCode,                                         ");
        sql.append(" MR.MAJOR_CODE AS majorCode,                                       ");
        sql.append(" MR.STAFF_ID AS staffId,                                           ");
        sql.append(" MR.STAFF_SORT AS staffSort,                                       ");
        sql.append(" MR.STAFF_RATE AS staffRate,                                       ");
        sql.append(" MR.STAFF_YIELD AS staffYield,                                     ");
        sql.append(" HS.NAME AS staffName                                              ");
        sql.append(" FROM                                                              ");
        sql.append("   WF_YIELD_MAJOR_ROLE_ALLOT MR                                    ");
        sql.append(" LEFT JOIN HR_STAFF_TM HS ON MR.STAFF_ID = HS.ID                   ");
        sql.append(" WHERE MR.WF_ID = ? AND MR.MAJOR_CODE = ? AND MR.CATEGORY = 1000   ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldMajorRoleAllot.class));
        query.setString(0, wfId);
        query.setString(1, majorCode);
        return query.list();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectHisYearYield()
     */
    @Override
    public WfYieldSettle selectHisYearYield() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT                                                              ");
        sql.append(" ISNULL(SUM(YS.YEAR_YIELD), 0) AS yearYield                          ");
        sql.append(" FROM                                                                ");
        sql.append(" WF_YIELD_SETTLE YS                                                  ");
        sql.append(" LEFT JOIN WF_FLOW_MAIN FM ON YS.ID = FM.ID                          ");
        sql.append(" WHERE YS.WF_CATEGORY = '1000'                                       ");
        sql.append(" AND Datename(year,YS.CREATE_DATE) != Datename(year,GetDate())       ");
        sql.append(" AND FM.AUDIT_STATUS = 2                                             ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(WfYieldSettle.class));
        return (WfYieldSettle) query.uniqueResult();
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectYieldSettleList(java.lang.String)
     */
    @Override
    public List<CustYieldSettle> selectYieldSettleList(String userId) {
        StringBuilder sql = new StringBuilder();
        List<CustYieldSettle> settles = new ArrayList<CustYieldSettle>();
        sql.append("       SELECT                                                    ");
        sql.append("       TOP (5) WF.ID AS id,                                      ");
        sql.append("       PT.ID AS proId,                                           ");
        sql.append("       OPM.ID AS periodId,                                       ");
        sql.append("       SY.ID AS syId,                                            ");
        sql.append("       PT.PRO_CODE AS proCode,                                   ");
        sql.append("       PT.PRO_NAME AS proName,                                   ");
        sql.append("       SY.SETTLE_CODE AS settleCode                              ");
        sql.append("   FROM                                                          ");
        sql.append("       (SELECT ID, PERIOD_ID,                                    ");
        sql.append("               PRO_ID,'1000' AS SETTLE_CODE,                     ");
        sql.append("               '' AS MAJOR_CODE                                  ");
        sql.append("           FROM OC_SETTLE_YIELD                                  ");
        sql.append("           WHERE SETTLE_YIELD > 0                                ");
        sql.append("           UNION ALL SELECT PY.ID,                               ");
        sql.append("           PERIOD_ID,PRO_ID,'2000' AS SETTLE_CODE,               ");
        sql.append("           PY.MAJOR_CODE                                         ");
        sql.append("           FROM OC_PERMIT_YIELD PY                               ");
        sql.append("           WHERE PERMIT_YIELD > 0 ) SY                           ");
        sql.append("   INNER JOIN (                                                  ");
        sql.append("       SELECT ID FROM OC_PERIOD_MANAGE OPM                       ");
        sql.append("       WHERE TYPE_CODE = 'OC.PERIOD.TYPE.SETTLE'                 ");
        sql.append("       AND STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'                  ");
        sql.append("       AND START_DATE <= CONVERT (DATE, GETDATE())               ");
        sql.append("       AND CONVERT (DATE, GETDATE()) <= END_DATE                 ");
        sql.append("   ) OPM ON SY.PERIOD_ID = OPM.ID                                ");
        sql.append("   INNER JOIN PM_PROJECT_TM PT ON SY.PRO_ID = PT.ID              ");
        sql.append("   LEFT JOIN WF_YIELD_SETTLE WF ON WF.PRO_ID = PT.ID             ");
        sql.append("   AND WF.PERIOD_ID = OPM.ID                                     ");
        sql.append("   LEFT JOIN WF_FLOW_MAIN WFM ON WF.ID = WFM.ID                  ");
        sql.append("   AND ISNULL(WFM.AUDIT_STATUS, 0) = 0                           ");
        sql.append("   WHERE EXISTS (SELECT 'X' FROM V_PM_MANAGER_TEAM PPT           ");
        sql.append("           INNER JOIN V_HR_STAFF_ALL HS ON PPT.STAFF_ID = HS.ID  ");
        sql.append("           WHERE PPT.PRO_ID = PT.ID AND HS.U_ID = ?              ");
        sql.append("           AND PPT.TEAM_ROLE IN ('PM.TEAM.ROLE.PM',              ");
        sql.append("               'PM.TEAM.ROLE.LEADER')                            ");
        sql.append("           AND PPT.MAIN_FLAG = 1                                 ");
        sql.append("       ) OR EXISTS (SELECT 'X' FROM                              ");
        sql.append("           PM_BUDGET_STAFF_TM PBS                                ");
        sql.append("       INNER JOIN V_HR_STAFF_ALL HS ON PBS.STAFF_ID = HS.ID      ");
        sql.append("       WHERE PBS.PRO_ID = PT.ID                                  ");
        sql.append("       AND PBS.MAJOR_CODE = SY.MAJOR_CODE                        ");
        sql.append("       AND SY.SETTLE_CODE = '2000'                               ");
        sql.append("       AND PBS.INVOLVED_ROLE = 'PrjMajorLeader'                  ");
        sql.append("       AND HS.U_ID = ?                                           ");
        sql.append("       AND PBS.MAIN_FLAG = 1                                     ");
        sql.append("   )                                                             ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(CustYieldSettle.class));
        query.setString(0, userId);
        query.setString(1, userId);
        settles = query.list();
        return settles;
    }

    /** 
     * @see com.df.tja.dao.IWfYieldSettleDao#selectYieldSettleListCount(java.lang.String)
     */
    @Override
    public int selectYieldSettleListCount(String userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(*) FROM (                                                   ");
        sql.append("     SELECT                                                               ");
        sql.append("       SY.ID                                                              ");
        sql.append("     FROM                                                                 ");
        sql.append("         (SELECT ID, PERIOD_ID,PRO_ID,'1000' AS SETTLE_CODE,              ");
        sql.append("             '' AS MAJOR_CODE FROM OC_SETTLE_YIELD                        ");
        sql.append("             WHERE SETTLE_YIELD > 0                                       ");
        sql.append("             UNION ALL SELECT PY.ID,                                      ");
        sql.append("             PERIOD_ID,PRO_ID,'2000' AS SETTLE_CODE,                      ");
        sql.append("             PY.MAJOR_CODE FROM OC_PERMIT_YIELD PY                        ");
        sql.append("             WHERE PERMIT_YIELD > 0 ) SY                                  ");
        sql.append("     INNER JOIN (                                                         ");
        sql.append("         SELECT ID FROM OC_PERIOD_MANAGE OPM                              ");
        sql.append("         WHERE TYPE_CODE = 'OC.PERIOD.TYPE.SETTLE'                        ");
        sql.append("         AND STATUS_CODE = 'OC.PERIOD.STATUS.JXZ'                         ");
        sql.append("         AND START_DATE <= CONVERT (DATE, GETDATE())                      ");
        sql.append("         AND CONVERT (DATE, GETDATE()) <= END_DATE                        ");
        sql.append("     ) OPM ON SY.PERIOD_ID = OPM.ID                                       ");
        sql.append("     INNER JOIN PM_PROJECT_TM PT ON SY.PRO_ID = PT.ID                     ");
        sql.append("     LEFT JOIN WF_YIELD_SETTLE WF ON WF.PRO_ID = PT.ID                    ");
        sql.append("     AND WF.PERIOD_ID = OPM.ID                                            ");
        sql.append("     LEFT JOIN WF_FLOW_MAIN WFM ON WF.ID = WFM.ID                         ");
        sql.append("     AND ISNULL(WFM.AUDIT_STATUS, 0) = 0                                  ");
        sql.append("         WHERE EXISTS (SELECT 'X' FROM V_PM_MANAGER_TEAM PPT              ");
        sql.append("                 INNER JOIN V_HR_STAFF_ALL HS ON PPT.STAFF_ID = HS.ID     ");
        sql.append("                 WHERE PPT.PRO_ID = PT.ID AND HS.U_ID = ?                 ");
        sql.append("                 AND PPT.TEAM_ROLE IN ('PM.TEAM.ROLE.PM',                 ");
        sql.append("                     'PM.TEAM.ROLE.LEADER')                               ");
        sql.append("                 AND PPT.MAIN_FLAG = 1                                    ");
        sql.append("             ) OR EXISTS (SELECT 'X' FROM                                 ");
        sql.append("                 PM_BUDGET_STAFF_TM PBS                                   ");
        sql.append("             INNER JOIN V_HR_STAFF_ALL HS ON PBS.STAFF_ID = HS.ID         ");
        sql.append("             WHERE PBS.PRO_ID = PT.ID                                     ");
        sql.append("             AND PBS.MAJOR_CODE = SY.MAJOR_CODE                           ");
        sql.append("             AND SY.SETTLE_CODE = '2000'                                  ");
        sql.append("             AND PBS.INVOLVED_ROLE = 'PrjMajorLeader'                     ");
        sql.append("             AND HS.U_ID = ?                                              ");
        sql.append("             AND PBS.MAIN_FLAG = 1                                        ");
        sql.append("         )                                                                ");
        sql.append("     ) R                                                                  ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, userId);
        query.setString(1, userId);
        return (int) query.uniqueResult();
    }

}
