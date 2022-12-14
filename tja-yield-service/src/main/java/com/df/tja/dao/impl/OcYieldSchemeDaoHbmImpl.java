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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IOcYieldSchemeDao;
import com.df.tja.domain.OcYieldMajorRatio;
import com.df.tja.domain.cust.CustOcYieldMajor;
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
        sql.append("  ys.PM_RATE as pmRate, ys.PM_YIELD as pmYield, ys.SECRET_RATE as secretRate,    ");
        sql.append("  ys.SECRET_YIELD as secretYield, ys.PRINCIPAL_ID as principalId, hs.name as     ");
        sql.append("  principalName, ys.REMARK as remark, ys.RELATION_ID as relationId               ");
        sql.append("from oc_yield_scheme ys                                                          ");
        sql.append("left join hr_staff_tm hs on ys.PRINCIPAL_ID = hs.id                              ");
        sql.append("where ys.id = ?                                                                  ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, id);
        query.setResultTransformer(Transformers.aliasToBean(CustOcYieldScheme.class));
        return (CustOcYieldScheme) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<CustOcYieldMajor> selectOcYieldMajorsBySchemeId(String schemeId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ym.id as id, ym.scheme_id as schemeId, ym.name as name,        ");
        sql.append("  ym.price_id as priceId, sp.type_code as typeCode, ym.build_area     ");
        sql.append("  as buildArea, ym.standard_price as standardPrice, ym.standard_yield ");
        sql.append("  as standardYield, ym.major_yield as majorYield                      ");
        sql.append("from oc_yield_major ym                                                ");
        sql.append("left join oc_standard_price sp on ym.price_id = sp.id                 ");
        sql.append("where ym.scheme_id = ?                                                ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, schemeId);
        query.setResultTransformer(Transformers.aliasToBean(CustOcYieldMajor.class));
        return query.list();
    }

    public List<CustOcYieldMajorDuty> selectOcYieldMajorDutiesBySchemeId(String schemeId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ymd.id as id, ymd.scheme_id as schemeId, ymd.major_code as majorCode,  ");
        sql.append("  ymd.MAJOR_YIELD as majorYield, ymd.MINUS_YIELD as minusYield,               ");
        sql.append("  ymd.PRINCIPAL_ID as principalId, hs.name as principalName                   ");
        sql.append("FROM OC_YIELD_MAJOR_DUTY ymd                                                  ");
        sql.append("left join v_hr_staff_all hs on ymd.PRINCIPAL_ID = hs.id                       ");
        sql.append("where ymd.scheme_id = ?                                                       ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, schemeId);
        query.setResultTransformer(Transformers.aliasToBean(CustOcYieldMajorDuty.class));
        return query.list();
    }

    public List<CustOcYieldMajor> selectMajorPrices(String schemeId) {
        StringBuffer sql = new StringBuffer("");
        sql.append("select sp.id as priceId, ym.id as id, sp.type_code as typeCode, ");
        sql.append("  isnull(ym.standard_price, sp.unit_price) as standardPrice,    ");
        sql.append("  sp.type_name as typeName                                      ");
        sql.append("from oc_standard_price sp                                       ");
        sql.append("left join oc_yield_major ym on sp.id = ym.price_id              ");
        if (!"0".equals(StringUtil.defaultIfBlank(schemeId, "0"))) {
            sql.append(" and ym.scheme_id = ?                                       ");
        } else {
            sql.append(" and ym.scheme_id is null                                   ");
        }

        sql.append("order by sp.type_code                                           ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        if (!"0".equals(StringUtil.defaultIfBlank(schemeId, "0"))) {
            query.setString(0, schemeId);
        }
        query.setResultTransformer(Transformers.aliasToBean(CustOcYieldMajor.class));
        return query.list();
    }

    public List<OcYieldMajorRatio> selectMajorRatios(String priceId, String majorId) {
        StringBuffer sql = new StringBuffer("");

        sql.append("select sr.major_code as majorCode, isnull(r.major_rate, sr.major_ratio) as majorRate  ");
        sql.append(" from oc_standard_ratio sr                                                            ");
        sql.append(" left join(                                                                           ");
        sql.append("   select ym.id, ym.price_id, mr.major_code, mr.major_rate                            ");
        sql.append("   from oc_yield_major ym                                                             ");
        sql.append("   left join oc_yield_major_ratio mr on mr.major_id = ym.id                           ");
        sql.append("   where 1=1                                                                          ");
        if (StringUtil.isNotBlank(majorId)) {
            sql.append("  and ym.id = :majorId                                                            ");
        } else {
            sql.append("  and ym.id is null                                                               ");
        }
        sql.append(" ) r on sr.stardand_id = r.price_id and sr.major_code = r.major_code                  ");
        sql.append(" where sr.stardand_id = :priceId                                                      ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(OcYieldMajorRatio.class));
        if (StringUtil.isNotBlank(majorId)) {
            query.setString("majorId", majorId);
        }
        query.setString("priceId", priceId);
        return query.list();
    }

    public void deleteMajors(String schemeId, List<String> majorIds) {
        StringBuffer sql = new StringBuffer("delete from oc_yield_major where scheme_id = :schemeId");
        if (majorIds != null && !majorIds.isEmpty()) {
            sql.append(" and id not in(:majorIds)");
        }

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString("schemeId", schemeId);
        if (majorIds != null && !majorIds.isEmpty()) {
            query.setParameterList("majorIds", majorIds);
        }
        query.executeUpdate();
    }

    public void calOtherYield(final String schemeId, final String opType) {
        //刷新缓存
        flushSession();
        getCurrentSession().doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement cstmt;
                try {
                    cstmt = connection.prepareCall("{call usp_YIELD_SCHEME_CAL(?, ?)}");
                    cstmt.setString(1, schemeId);
                    cstmt.setString(2, opType);
                    cstmt.execute();
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        });
    }

}
