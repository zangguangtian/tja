package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.hibernate.persistence.Param;
import com.df.tja.dao.IOcScheduleFillDao;
import com.df.tja.domain.cust.OcCurrweekSchedule;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>OcScheduleFillDaoImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-27 17:22</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Repository
public class OcScheduleFillDaoImpl extends BaseDaoHbmImpl implements IOcScheduleFillDao{

    @Override
    public List<OcCurrweekSchedule> selectSimleByProId(String proId,Pagination pagination,Integer state) {
        List<OcCurrweekSchedule> ocCurrweekScheduleList = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT OSD.PRO_ID AS proId,PP.PRO_NAME AS proName,VSC.CONFIG_NAME AS configName,VHS.NAME AS staffName,     ");
        sql.append(" VHS.ORG_NAME AS orgName,VOC.PREV_SCHEDULE AS prevSchedule,VOC.CURR_SCHEDULE AS currSchedule,               ");
        sql.append(" VOC.CURR_SCHEDULE-VOC.PREV_SCHEDULE AS weekShare,VOC.SCHEDULE_STATUS AS scheduleStatus,                    ");
        sql.append(" (SELECT COUNT(OST.PRO_ID) FROM OC_SCHEME_DIVISOR_TM OST LEFT JOIN PM_PROJECT_TM PPT                        ");
        sql.append(" ON OST.PRO_ID = PPT.ID WHERE PPT.PRE_PRO_ID = PP.PRE_PRO_ID) AS mergeCount                                 ");

        StringBuilder sqlFW = new StringBuilder(100);
        sqlFW.append(" FROM OC_SCHEME_DIVISOR_TM OSD                                                                              ");
        sqlFW.append(" LEFT JOIN V_OC_CURRWEEK_SCHEDULE VOC ON OSD.ID = VOC.DIVISOR_ID                                            ");
        sqlFW.append(" LEFT JOIN V_SYS_CONFIG VSC ON OSD.STAFF_ROLE = VSC.CONFIG_CODE                                             ");
        sqlFW.append(" LEFT JOIN V_HR_STAFF_ALL VHS ON OSD.STAFF_ID = VHS.ID                                                      ");
        sqlFW.append(" LEFT JOIN PM_PROJECT_TM PP ON OSD.PRO_ID = PP.ID                                                           ");
        sqlFW.append(" WHERE EXISTS (                                                                                             ");
        sqlFW.append(" SELECT 'X' FROM OC_SCHEME_TM OS WHERE OS.ID = OSD.SCHEME_ID AND OS.PRO_WBS = 'OC.PROJECT.WBS.SIMPLE')      ");
        if(state == 1) {
            sqlFW.append(" AND PP.PRE_PRO_ID = ?                                                                              ");
        }else{
            sqlFW.append(" AND PP.ID = ?                                                                              ");
        }
        sql.append(sqlFW.toString());
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setResultTransformer(Transformers.aliasToBean(OcCurrweekSchedule.class));
        List<Param> params = new ArrayList<Param>(0);
        params.add(new Param(proId, StandardBasicTypes.STRING));
        addQueryParams(query, params, pagination);

        ocCurrweekScheduleList = query.list();
        if (pagination != null) {
            sqlFW.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            addQueryParams(query, params, null);

            int totalCount = new Integer(query.uniqueResult().toString());
            pagination.setTotalCount(totalCount);
            if (ocCurrweekScheduleList != null && ocCurrweekScheduleList.size() > 0) {
                pagination.setCurrentPageSize(ocCurrweekScheduleList.size());
            }
        }
        return ocCurrweekScheduleList;
    }

    @Override
    public List<OcCurrweekSchedule> selectFullByProId(String proId,Pagination pagination,Integer state) {
        List<OcCurrweekSchedule> ocCurrweekScheduleList = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT OSS.PRO_ID AS proId,OSS.PRO_NAME AS proName,OSS.SCHEME_STAGE_NAME AS schemeStageName,       ");
        sql.append(" (SELECT COUNT(*) FROM V_OC_SCHEME_STAGE_MAJOR OST LEFT JOIN V_OC_SCHEME_MAJOR_TASK OSA             ");
        sql.append(" ON OST.SCHEME_MAJOR_ID = OSA.MAJOR_ID WHERE OST.PRO_ID = OSS.PRO_ID) AS mergeCount,                ");
        sql.append(" OSS.SCHEME_MAJOR_NAME AS schemeMajorName,OSM.SUB_NAME AS subName,OSM.TASK_NAME AS taskName,        ");
        sql.append(" (SELECT COUNT(*) FROM V_OC_SCHEME_STAGE_MAJOR OST                                                  ");
        sql.append(" LEFT JOIN V_OC_SCHEME_MAJOR_TASK OSA ON OST.SCHEME_MAJOR_ID = OSA.MAJOR_ID                         ");
        sql.append(" WHERE OST.SCHEME_STAGE_NAME = OSS.SCHEME_STAGE_NAME AND OST.PRO_ID = OSS.PRO_ID) AS stageCount,    ");
        sql.append(" (SELECT COUNT(*) FROM V_OC_SCHEME_STAGE_MAJOR OST LEFT JOIN V_OC_SCHEME_MAJOR_TASK OSA             ");
        sql.append(" ON OST.SCHEME_MAJOR_ID = OSA.MAJOR_ID WHERE OST.SCHEME_MAJOR_NAME = OSS.SCHEME_MAJOR_NAME          ");
        sql.append(" AND OST.SCHEME_STAGE_NAME = OSS.SCHEME_STAGE_NAME) AS majorCount,                                  ");
        sql.append(" OSM.USER_ROLE_NAME AS configName,OSM.STAFF_NAME AS staffName,OSM.ORG_NAME AS orgName,              ");
        sql.append(" VOC.PREV_SCHEDULE AS prevSchedule,VOC.CURR_SCHEDULE AS currSchedule,                               ");
        sql.append(" VOC.CURR_SCHEDULE-VOC.PREV_SCHEDULE AS weekShare,VOC.SCHEDULE_STATUS AS scheduleStatus             ");
        StringBuilder sqlFW = new StringBuilder(100);
        sqlFW.append(" FROM V_OC_SCHEME_STAGE_MAJOR OSS                                                                   ");
        sqlFW.append(" LEFT JOIN V_OC_SCHEME_MAJOR_TASK OSM ON OSS.SCHEME_MAJOR_ID = OSM.MAJOR_ID                         ");
        sqlFW.append(" LEFT JOIN V_OC_CURRWEEK_SCHEDULE VOC ON OSM.USER_ID = VOC.DIVISOR_ID                               ");
        sqlFW.append(" LEFT JOIN PM_PROJECT_TM PP ON OSS.PRO_ID = PP.ID                                                   ");
        sqlFW.append(" WHERE OSS.PRO_WBS = 'OC.PROJECT.WBS.FULL'                                                          ");
        if(state == 1) {
            sqlFW.append(" AND PP.PRE_PRO_ID = ?                                                                              ");
        }else{
            sqlFW.append(" AND PP.ID = ?                                                                              ");
        }
        sql.append(sqlFW.toString());
        sql.append(" ORDER BY OSS.PRO_ID,OSS.SCHEME_STAGE_NAME,OSS.SCHEME_MAJOR_NAME,OSM.SUB_NAME                       ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setResultTransformer(Transformers.aliasToBean(OcCurrweekSchedule.class));
        List<Param> params = new ArrayList<Param>(0);
        params.add(new Param(proId, StandardBasicTypes.STRING));
        addQueryParams(query, params, pagination);
        ocCurrweekScheduleList = query.list();
        if (pagination != null) {
            sqlFW.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            addQueryParams(query, params, null);

            int totalCount = new Integer(query.uniqueResult().toString());
            pagination.setTotalCount(totalCount);
            if (ocCurrweekScheduleList != null && ocCurrweekScheduleList.size() > 0) {
                pagination.setCurrentPageSize(ocCurrweekScheduleList.size());
            }
        }
        return ocCurrweekScheduleList;
    }
}
