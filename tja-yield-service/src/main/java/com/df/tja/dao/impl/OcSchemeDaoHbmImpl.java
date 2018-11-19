package com.df.tja.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcSchemeDao;
import com.df.tja.domain.OcScheme;

/**
 * <p>OcSchemeDaoHbmImpl </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月12日 上午11:59:24</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Repository
public class OcSchemeDaoHbmImpl extends BaseDaoHbmImpl implements IOcSchemeDao {

    @Override
    public OcScheme queryByProId(String proId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT ID as id, PRO_WBS as proWbs    ");
        sql.append("  FROM OC_SCHEME_TM                     ");
        sql.append("  WHERE DEL_FLAG = 0 AND PRO_ID = ?     ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0,proId );
        query.setResultTransformer(Transformers.aliasToBean(OcScheme.class));
        return (OcScheme) query.uniqueResult();
    }

}
