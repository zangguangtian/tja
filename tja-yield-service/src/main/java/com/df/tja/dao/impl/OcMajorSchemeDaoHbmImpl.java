package com.df.tja.dao.impl;


import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcMajorSchemeDao;
import com.df.tja.domain.cust.OcSchemeMajorTask;
import com.df.tja.domain.cust.OcSchemeStageMajor;

@Repository
public class OcMajorSchemeDaoHbmImpl extends BaseDaoHbmImpl implements IOcMajorSchemeDao {

    @Override
    public OcSchemeStageMajor selectOcSchemeStageMajorById(String majorId) {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT T.WBS_NAME AS wbsName, T.SCHEME_STAGE_NAME AS schemeStageName,       ");
        sql.append(" T.SCHEME_MAJOR_NAME AS schemeMajorName, T.SCHEME_MAJOR_ID AS schemeMajorId ");
        sql.append(" FROM V_OC_SCHEME_STAGE_MAJOR T WHERE T.SCHEME_MAJOR_ID = ?                 ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setString(0, majorId);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(OcSchemeStageMajor.class));
        return (OcSchemeStageMajor) sqlQuery.uniqueResult();
    }

    public List<OcSchemeMajorTask> selectMajorTaskById(String majorId) {
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT T.SCHEME_ID AS schemeId, T.PRO_ID AS proId, T.SUB_ID AS subId,             ");
        sql.append("  T.SUB_NAME AS subName, T.SUB_RATIO AS subRatio,                                 ");
        sql.append("  ISNULL((SELECT COUNT(SUB_ID) FROM V_OC_SCHEME_MAJOR_TASK ST GROUP BY SUB_ID     ");
        sql.append("    HAVING ST.SUB_ID = T.SUB_ID), 1) AS subTaskCount, T.TASK_ID AS taskId,        ");
        sql.append("  T.TASK_NAME AS taskName, T.TASK_RATIO AS taskRatio, T.TASK_USER_COUNT AS        ");
        sql.append("  taskUserCount, T.USER_ID AS userId, T.USER_ROLE_CODE AS userRoleCode,           ");
        sql.append("  T.USER_ROLE_NAME AS userRoleName, T.STAFF_ID AS staffId, T.STAFF_NAME AS        ");
        sql.append("  staffName, T.ORG_NAME AS orgName, T.USER_RATIO AS userRatio, T.REMARK AS remark ");
        sql.append("FROM V_OC_SCHEME_MAJOR_TASK T                                                     ");
        sql.append("WHERE T.MAJOR_ID = ?                                                              ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setString(0, majorId);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(OcSchemeMajorTask.class));
        return sqlQuery.list();
    }
}
