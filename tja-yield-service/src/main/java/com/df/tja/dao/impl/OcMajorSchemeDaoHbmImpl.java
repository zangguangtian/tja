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
        sql.append("SELECT T.SCHEME_ID AS schemeId, T.PRO_ID AS proId, T.SUB_ID AS subId,                ");
        sql.append("  T.SUB_NAME AS subName, T.SUB_RATIO AS subRatio,T.SUB_CHILD_COUNT AS subChildCount, ");
        sql.append("  ISNULL((SELECT COUNT(SUB_ID) FROM V_OC_SCHEME_MAJOR_TASK ST GROUP BY SUB_ID        ");
        sql.append("    HAVING ST.SUB_ID = T.SUB_ID), 1) AS subTaskCount, T.TASK_ID AS taskId,           ");
        sql.append("  T.TASK_NAME AS taskName, T.TASK_RATIO AS taskRatio, T.TASK_USER_COUNT AS           ");
        sql.append("  taskUserCount, T.USER_ID AS userId, T.USER_ROLE_CODE AS userRoleCode,              ");
        sql.append("  T.USER_ROLE_NAME AS userRoleName, T.STAFF_ID AS staffId, T.STAFF_NAME AS           ");
        sql.append("  staffName, T.ORG_NAME AS orgName, T.USER_RATIO AS userRatio, T.REMARK AS remark    ");
        sql.append("FROM V_OC_SCHEME_MAJOR_TASK T                                                        ");
        sql.append("WHERE T.MAJOR_ID = ? ORDER BY T.SUB_SORT, T.TASK_SORT, T.USER_ROLE_CODE, T.USER_SORT ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setString(0, majorId);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(OcSchemeMajorTask.class));
        return sqlQuery.list();
    }

    public void updateMajorTreePath(String parentId, Integer sort) {
        StringBuffer sql = new StringBuffer("");

        sql.append("UPDATE A SET A.TREE_PATH = B.TREE_PATH + A.ID + '@'           ");
        sql.append("FROM OC_SCHEME_DIVISOR_TM A, OC_SCHEME_DIVISOR_TM B           ");
        sql.append("WHERE A.PARENT_ID = B.ID AND A.DIVISOR_SORT > ? AND B.ID = ?  ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setInteger(0, sort);
        sqlQuery.setString(1, parentId);
        sqlQuery.executeUpdate();
    }
}
