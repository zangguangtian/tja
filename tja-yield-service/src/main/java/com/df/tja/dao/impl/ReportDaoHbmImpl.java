/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * ReportDaoHbmImpl.java
 * 
 * 2017年11月21日-下午4:44:57
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.project.domain.cust.CustProject;
import com.df.tja.dao.IReportDao;
import com.df.tja.domain.cust.YieldDeptDetial;

/**
 * <p>ReportDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月21日 下午4:44:57</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class ReportDaoHbmImpl extends BaseDaoHbmImpl implements IReportDao {

    /** 
     * @see com.df.tja.dao.IReportDao#
     * selectProByperiod(java.lang.String, com.df.framework.hibernate.persistence.Pagination)
     */
    @Override
    public List<CustProject> selectProByperiod(String periodId, Pagination page) {
        List<CustProject> custProjects = new ArrayList<CustProject>();
        StringBuilder sqlFW = new StringBuilder();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT YS.PRO_ID AS id,                               ");
        sql.append(" PM.PRO_CODE AS proCode, PM.PRO_NAME AS proName       ");

        sqlFW.append(" FROM WF_YIELD_SETTLE YS                             ");
        sqlFW.append(" INNER JOIN PM_PROJECT_TM PM ON YS.PRO_ID = PM.ID    ");
        if (StringUtils.isNotBlank(periodId)) {
            sqlFW.append(" WHERE YS.PERIOD_ID  = ?                         ");
        }
        sql.append(sqlFW.toString());
        sql.append(" GROUP BY YS.PRO_ID,PM.PRO_CODE,PM.PRO_NAME          ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(CustProject.class));
        if (StringUtils.isNotBlank(periodId)) {
            query.setString(0, periodId);
        }
        if (page != null) {
            query.setFirstResult(page.getStartPosition());
            query.setMaxResults(page.getRowsPerPage());
        }

        custProjects = query.list();
        if (page != null) {
            sqlFW.insert(0, "select COUNT(DISTINCT(PRO_ID)) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            if (StringUtils.isNotBlank(periodId)) {
                query.setString(0, periodId);
            }
            int totalCount = new Integer(query.uniqueResult().toString());
            page.setTotalCount(totalCount);
            if (custProjects != null && custProjects.size() > 0) {
                page.setCurrentPageSize(custProjects.size());
            }
        }
        return custProjects;
    }

    /** 
     * @see com.df.tja.dao.IReportDao#selectStaffYield(java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public List<YieldDeptDetial> selectStaffYield(String orgId, String periodId, List<String> proIds) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT YS.PRO_ID AS proId, SY.STAFF_ID AS staffId, SY.STAFF_YIELD AS staffYield,     ");
        sql.append(" VH.NAME AS name FROM (                                                               ");
        sql.append("     SELECT PA.WF_ID, PA.STAFF_ID, PA.STAFF_YIELD                                     ");
        sql.append("     FROM WF_YIELD_PRINCIPAL_ALLOT PA                                                 ");
        sql.append("     WHERE EXISTS(                                                                    ");
        sql.append("         SELECT 'X' FROM WF_YIELD_SETTLE YS                                           ");
        sql.append("         WHERE PA.WF_ID = YS.ID AND YS.PRO_ID IN (:proIds)                            ");
        if (StringUtils.isNotBlank(periodId)) {
            sql.append("        AND YS.PERIOD_ID = :periodId                                              ");
        }
        sql.append("     )                                                                                ");
        sql.append("     UNION                                                                            ");
        sql.append("     SELECT RA.WF_ID, RA.STAFF_ID, RA.STAFF_YIELD                                     ");
        sql.append("     FROM WF_YIELD_MAJOR_ROLE_ALLOT RA                                                ");
        sql.append("     WHERE EXISTS(                                                                    ");
        sql.append("         SELECT 'X' FROM WF_YIELD_SETTLE YS                                           ");
        sql.append("         WHERE RA.WF_ID = YS.ID AND YS.PRO_ID IN (:proIds)                            ");
        if (StringUtils.isNotBlank(periodId)) {
            sql.append("        AND YS.PERIOD_ID = :periodId                                              ");
        }
        sql.append("     )                                                                                ");
        sql.append(" ) SY INNER JOIN WF_YIELD_SETTLE YS ON SY.WF_ID = YS.ID                               ");
        sql.append(" INNER JOIN V_HR_STAFF_ALL VH ON VH.ID = SY.STAFF_ID                                  ");

        if (StringUtils.isNotBlank(orgId)) {
            sql.append(" WHERE EXISTS(                                                                        ");
            sql.append("     SELECT 'X' FROM HR_ORG_TM HT                                                     ");
            sql.append("     WHERE VH.ORG_ID = HT.ID AND HT.ID = :orgId                                       ");
            sql.append(" )                                                                                    ");
        }

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("proIds", proIds);
        query.setResultTransformer(Transformers.aliasToBean(YieldDeptDetial.class));
        if (StringUtils.isNotBlank(orgId)) {
            query.setString("orgId", orgId);
        }
        if (StringUtils.isNotBlank(periodId)) {
            query.setString("periodId", periodId);
        }
        return query.list();
    }

}
