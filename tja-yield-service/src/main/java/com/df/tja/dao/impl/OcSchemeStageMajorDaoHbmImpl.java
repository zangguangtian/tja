package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcSchemeStageMajorDao;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * <p>OcSchemeStageMajorDaoHbmImpl </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-12 14:21</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public class OcSchemeStageMajorDaoHbmImpl extends BaseDaoHbmImpl implements IOcSchemeStageMajorDao {

    @Override
    public List<OcSchemeStageMajor> queryFullList(String proId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT SCHEME_STAGE_ID,SCHEME_STAGE_NAME,     ");
        sql.append("      SCHEME_STAGE_RATIO,STAGE_MAJOR_COUNT,     ");
        sql.append("      SCHEME_MAJOR_ID,SCHEME_MAJOR_CODE,        ");
        sql.append("      SCHEME_MAJOR_NAME,SCHEME_MAJOR_RATIO      ");
        sql.append("  FROM                                          ");
        sql.append("      V_OC_SCHEME_STAGE_MAJOR                   ");
        sql.append("  WHERE                                         ");
        sql.append("      PRO_ID = ?                                ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setResultTransformer(Transformers.aliasToBean(OcSchemeStageMajor.class));
        return query.list();
    }

}
