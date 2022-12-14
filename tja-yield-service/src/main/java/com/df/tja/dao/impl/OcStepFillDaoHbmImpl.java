package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.hibernate.persistence.Param;
import com.df.tja.dao.IOcStepFillDao;
import com.df.tja.domain.cust.OcStepFillMore;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>OcStepFillDaoHbmImpl </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-23 14:52</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Repository
public class OcStepFillDaoHbmImpl extends BaseDaoHbmImpl implements IOcStepFillDao {

    private Query query;

    @Override
    public List<OcStepFillMore> selectByPreProId(String preProId,Pagination pagination,Integer state) {
        List<OcStepFillMore> ocStepFillMoreList = null;
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT SF.ID AS id,SF.PRO_ID AS proId,PP.PRO_NAME AS proName,PP.PRO_STATUS AS proStatus,          ");
        sql.append("  (SELECT COUNT(PRO_NAME) FROM OC_STEP_FILL_TM SFT LEFT JOIN PM_PROJECT_TM PPT                      ");
        sql.append("  ON SFT.PRO_ID = PPT.ID WHERE PPT.PRE_PRO_ID = PP.PRE_PRO_ID) AS mergeCount,                       ");
        sql.append("  SD.WEEK_START AS weekStart,SD.WEEK_END AS weekEnd,SF.STEP_STATUS AS stepStatus,                   ");
        sql.append("  SF.WORK_CONTENT AS workContent,SF.WORK_PLAN AS workPlan,SF.REMARK AS remark,                      ");
        sql.append("  SF.MODIFIER AS modifier,SF.MODIFY_DATE AS modifyDate                                              ");

        StringBuilder sqlFW = new StringBuilder(100);
        sqlFW.append("  FROM OC_STEP_FILL_TM SF                                                                         ");
        sqlFW.append("  LEFT JOIN OC_SCHEDULE_TM SD ON SF.SCHEDULE_ID = SD.ID                                           ");
        sqlFW.append("  LEFT JOIN PM_PROJECT_TM PP ON SF.PRO_ID = PP.ID                                                 ");
        sqlFW.append("  LEFT JOIN SYS_CONFIG_TM SCF ON PP.PRO_STATUS = SCF.CONFIG_CODE                                  ");
        if(state == 1){
            sqlFW.append("  WHERE PP.PRE_PRO_ID = ?                                                                     ");
        }else{
            sqlFW.append("  WHERE PP.ID = ?                                                                             ");
        }
        sqlFW.append("  AND SF.DIVISOR_ID IS NULL                                                                       ");
        sql.append(sqlFW.toString());
        sql.append("  ORDER BY SF.PRO_ID,SD.WEEK_START DESC                                                             ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0,preProId );
        query.setResultTransformer(Transformers.aliasToBean(OcStepFillMore.class));

        List<Param> params = new ArrayList<Param>(0);
        params.add(new Param(preProId, StandardBasicTypes.STRING));
        addQueryParams(query, params, pagination);

        ocStepFillMoreList = query.list();
        if (pagination != null) {
            sqlFW.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            addQueryParams(query, params, null);

            int totalCount = new Integer(query.uniqueResult().toString());
            pagination.setTotalCount(totalCount);
            if (ocStepFillMoreList != null && ocStepFillMoreList.size() > 0) {
                pagination.setCurrentPageSize(ocStepFillMoreList.size());
            }
        }
        return ocStepFillMoreList;
    }

    @Override
    public List<OcStepFillMore> selectProgressByMajor(String proId, String parentId,Pagination pagination) {
        List<OcStepFillMore> ocStepFillMoreList = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT OSF.ID AS id,OSF.DIVISOR_STATUS AS divisorStatus,OS.WEEK_START AS weekStart,    ");
        sql.append(" OS.WEEK_END AS weekEnd,OSF.STEP_STATUS AS stepStatus,OSF.WORK_CONTENT AS workContent,  ");
        sql.append(" OSF.WORK_PLAN AS workPlan,OSF.REMARK AS remark,                                        ");
        sql.append(" OSF.MODIFIER AS modifier,OSF.MODIFY_DATE AS modifyDate                                 ");

        StringBuilder sqlFW = new StringBuilder(50);
        sqlFW.append("  FROM OC_STEP_FILL_TM OSF                                                            ");
        sqlFW.append("  LEFT JOIN OC_SCHEDULE_TM OS ON OSF.SCHEDULE_ID = OS.ID                              ");
        sqlFW.append("  WHERE OSF.PRO_ID = ? AND OSF.DIVISOR_ID = ?                                         ");
        sql.append(sqlFW.toString());
        sql.append("  ORDER BY OSF.ID,OS.WEEK_START DESC                                                    ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setParameter(1, parentId);
        query.setResultTransformer(Transformers.aliasToBean(OcStepFillMore.class));

        List<Param> params = new ArrayList<Param>(0);
        params.add(new Param(proId, StandardBasicTypes.STRING));
        params.add(new Param(parentId, StandardBasicTypes.STRING));
        addQueryParams(query, params, pagination);

        ocStepFillMoreList = query.list();
        if (pagination != null) {
            sqlFW.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            addQueryParams(query, params, null);

            int totalCount = new Integer(query.uniqueResult().toString());
            pagination.setTotalCount(totalCount);
            if (ocStepFillMoreList != null && ocStepFillMoreList.size() > 0) {
                pagination.setCurrentPageSize(ocStepFillMoreList.size());
            }
        }
        return ocStepFillMoreList;
    }

}
