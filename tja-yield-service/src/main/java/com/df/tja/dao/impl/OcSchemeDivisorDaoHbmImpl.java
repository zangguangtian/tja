package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IOcSchemeDivisorDao;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.OcSchemeDivisorModel;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.dsig.Transform;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>OcSchemeDivisorDaoHbmImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-12 20:04</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Repository
public class OcSchemeDivisorDaoHbmImpl extends BaseDaoHbmImpl implements IOcSchemeDivisorDao {
    @Override
    public List<OcSchemeDivisorModel> querySimpleList(String proId) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT SD.STAFF_ROLE as staffRole,SC.CONFIG_NAME as configName,SD.STAFF_ID as staffId,    ");
        sql.append("      VHS.NAME AS staffName,VHS.ORG_NAME as orgName,SD.SCHEME_RATIO AS schemeRatio,         ");
        sql.append("      SD.REMARK AS remark,SD.SCHEME_ID as schemeId,SD.ID AS id                              ");
        sql.append("  FROM                                                                                      ");
        sql.append("      OC_SCHEME_DIVISOR_TM SD                                                               ");
        sql.append("  LEFT JOIN SYS_CONFIG_TM SC ON SD.STAFF_ROLE = SC.CONFIG_CODE                              ");
        sql.append("  LEFT JOIN V_HR_STAFF_ALL VHS ON SD.STAFF_ID = VHS.ID                                      ");
        sql.append("  WHERE SD.PRO_ID = ?                                                                       ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setResultTransformer(Transformers.aliasToBean(OcSchemeDivisorModel.class));
        return query.list();
    }

    @Override
    public BigDecimal queryRatioSum(String proId, String parentId) {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT ISNULL(SUM(SCHEME_RATIO), 0)             ");
        sql.append("FROM OC_SCHEME_DIVISOR_TM WHERE PRO_ID = ?      ");
        if(parentId == null){
            sql.append("AND PARENT_ID IS NULL                       ");
        }else{
            sql.append("AND PARENT_ID = ?                           ");
        }
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setString(0, proId);
        if(parentId != null){
            sqlQuery.setString(1,parentId);
        }
        return (BigDecimal) sqlQuery.uniqueResult();
    }

    @Override
    public List<OcSchemeDivisor> selectStageMajor(String proId, String parentId) {
        StringBuffer sql = new StringBuffer("");
        sql.append(" SELECT OSD.ID AS id,ISNULL(SC.CONFIG_NAME, OSD.DIVISOR_NAME) AS divisorName    ");
        sql.append(" FROM OC_SCHEME_DIVISOR_TM OSD                                                  ");
        sql.append(" LEFT JOIN SYS_CONFIG_TM SC ON OSD.DIVISOR_NAME = SC.CONFIG_CODE                ");
        sql.append(" WHERE PRO_ID = ?                                                               ");
        if(StringUtil.isNotBlank(parentId)){
            sql.append(" AND OSD.PARENT_ID = '"+parentId+"'                                         ");
        }else{
            sql.append(" AND OSD.PARENT_ID IS NULL                                                  ");
        }
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setString(0,proId);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(OcSchemeDivisor.class));
        return sqlQuery.list();
    }
}
