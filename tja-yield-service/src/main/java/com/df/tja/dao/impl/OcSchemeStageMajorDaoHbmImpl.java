package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcSchemeStageMajorDao;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

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
@Repository
public class OcSchemeStageMajorDaoHbmImpl extends BaseDaoHbmImpl implements IOcSchemeStageMajorDao {

    @Override
    public List<OcSchemeStageMajor> queryFullList(String proId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT SCHEME_STAGE_ID as schemeStageId,SCHEME_STAGE_NAME as schemeStageName,     ");
        sql.append("      SCHEME_STAGE_RATIO as schemeStageRatio,STAGE_MAJOR_COUNT as schemeStageCount,     ");
        sql.append("      SCHEME_MAJOR_ID as schemeMajorId,SCHEME_MAJOR_CODE as schemeMajorCode,        ");
        sql.append("      SCHEME_MAJOR_NAME as schemeMajorName,SCHEME_MAJOR_RATIO as schemeMajorRatio      ");
        sql.append("  FROM                                          ");
        sql.append("      V_OC_SCHEME_STAGE_MAJOR                   ");
        sql.append("  WHERE                                         ");
        sql.append("      PRO_ID = ?                                ");
        sql.append("  ORDER BY SCHEME_STAGE_ID                      ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setResultTransformer(Transformers.aliasToBean(OcSchemeStageMajor.class));
        return query.list();
    }

}
