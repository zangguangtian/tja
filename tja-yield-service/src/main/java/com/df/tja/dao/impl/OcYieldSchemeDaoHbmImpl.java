/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * OcYieldSchemeDaoHbmImpl.java
 * 
 * 2017年10月28日-下午3:01:23
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcYieldSchemeDao;
import com.df.tja.domain.cust.CustOcYieldMajorDuty;
import com.df.tja.domain.cust.CustOcYieldScheme;

/**
 * @author TabZhu
 *
 */
@Repository
public class OcYieldSchemeDaoHbmImpl extends BaseDaoHbmImpl implements IOcYieldSchemeDao {

    public CustOcYieldScheme selectOcYieldSchemeById(String id) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ys.id as id, ys.scheme_no as schemeNo, ys.last_update as lastUpdate,      ");
        sql.append("  ys.PRO_ID as proId, ys.LAND_AREA as landArea, ys.SCHEME_BASIS as schemeBasis,  ");
        sql.append("  ys.CONTRACT_AMOUNT as contractAmount, ys.PKG_AMOUNT as pkgAmount,              ");
        sql.append("  ys.SCHEME_AMOUNT as schemeAmount, ys.REBATE_AMOUNT as rebateAmount,            ");
        sql.append("  ys.TOTAL_AMOUNT as totalAmount, ys.MAJOR_AMOUNT as majorAmount,                ");
        sql.append("  ys.PRINCIPAL_RATE as principalRate, ys.PRINCIPAL_YIELD as principalYield,      ");
        sql.append("  ys.PM_RATE as pmRate, ys.PM_YIELD as pmYield, ys.PRINCIPAL_ID as principalId,  ");
        sql.append("  hs.name as principalName, ys.REMARK as remark                                  ");
        sql.append("from oc_yield_scheme ys                                                          ");
        sql.append("left join hr_staff_tm hs on ys.PRINCIPAL_ID = hs.id                              ");
        sql.append("where ys.id = ?                                                                  ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, id);
        query.setResultTransformer(Transformers.aliasToBean(CustOcYieldScheme.class));
        return (CustOcYieldScheme) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<CustOcYieldMajorDuty> selectOcYieldMajorDutiesBySchemeId(String schemeId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ymd.id as id, ymd.scheme_id as schemeId, ymd.major_code as majorCode,  ");
        sql.append("  ymd.MAJOR_YIELD as majorYield, ymd.PRINCIPAL_ID as principalId,             ");
        sql.append("  hs.name as principalName                                                    ");
        sql.append("FROM OC_YIELD_MAJOR_DUTY ymd                                                  ");
        sql.append("left join hr_staff_tm hs on ymd.PRINCIPAL_ID = hs.id                          ");
        sql.append("where ymd.scheme_id = ?                                                       ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, schemeId);
        query.setResultTransformer(Transformers.aliasToBean(CustOcYieldMajorDuty.class));
        return query.list();
    }

}
