/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * OcDesignScheduleDaoHbmImpl.java
 * 
 * 2018年11月20日-下午2:49:01
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcDesignScheduleDao;
import com.df.tja.domain.cust.CustOcDesignSchedule;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>OcDesignScheduleDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月20日 下午2:49:01</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class OcDesignScheduleDaoHbmImpl extends BaseDaoHbmImpl implements IOcDesignScheduleDao {

    @Override
    public List<CustOcDesignSchedule> selectDesignSchedulesById(String phaseId) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT OSM.SCHEME_ID AS schemeId, OSM.MAJOR_ID AS majorId, OSM.MAJOR_NAME    ");
        sql.append("  AS majorName, OSM.SUB_ID AS subId, OSM.SUB_NAME AS subName, OSM.TASK_ID    ");
        sql.append("  AS taskId, OSM.TASK_NAME AS taskName, OSM.USER_ID AS userId,               ");
        sql.append("  OSM.USER_ROLE_NAME AS userRoleName, OSM.STAFF_ID AS staffId,               ");
        sql.append("  OSM.STAFF_NAME AS staffName, OSM.ORG_NAME AS orgName,                      ");
        sql.append("  ISNULL(PS.CURR_SCHEDULE, 0) AS preSchedule, ISNULL(CS.CURR_SCHEDULE, 0)    ");
        sql.append("  AS currSchedule, CS.SCHEDULE_STATUS AS scheduleStatus, CS.REMARK AS remark ");
        sql.append("FROM V_OC_SCHEME_MAJOR_TASK OSM                                              ");
        sql.append("LEFT JOIN V_OC_CURRWEEK_SCHEDULE CS ON OSM.PRO_ID = CS.PRO_ID                ");
        sql.append("  AND OSM.USER_ID = CS.DIVISOR_ID                                            ");
        sql.append("LEFT JOIN V_OC_PREWEEK_SCHEDULE PS ON OSM.PRO_ID = PS.PRO_ID                 ");
        sql.append("  AND OSM.USER_ID = PS.DIVISOR_ID                                            ");
        sql.append("WHERE OSM.USER_ID IS NOT NULL AND OSM.PHASE_ID = ?                           ");
        sql.append("ORDER BY OSM.MAJOR_SORT, OSM.SUB_SORT, OSM.TASK_SORT, OSM.USER_SORT          ");

        SQLQuery query = this.getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(CustOcDesignSchedule.class));
        query.setString(0, phaseId);
        return query.list();
    }

    @Override
    public void updateDesignPreSchedule(String phaseId) {
        flushSession();
        StringBuffer sql = new StringBuffer("");
        sql.append("UPDATE C SET C.PREV_SCHEDULE = ISNULL(P.CURR_SCHEDULE, 0)         ");
        sql.append("FROM OC_SCHEDULE_FILL_TM C                                        ");
        sql.append("LEFT JOIN OC_SCHEDULE_FILL_TM P ON C.PRO_ID = P.PRO_ID            ");
        sql.append("  AND C.SCHEME_ID = P.SCHEME_ID AND C.DIVISOR_ID = P.DIVISOR_ID   ");
        sql.append("WHERE P.SCHEDULE_ID IN (                                          ");
        sql.append("    SELECT TOP(1) PS.ID                                           ");
        sql.append("    FROM OC_SCHEDULE_TM PS                                        ");
        sql.append("    WHERE EXISTS(                                                 ");
        sql.append("      SELECT 'X' FROM OC_SCHEDULE_TM CS                           ");
        sql.append("      WHERE CS.PRO_ID = PS.PRO_ID AND CS.SCHEME_ID = PS.SCHEME_ID ");
        sql.append("        AND PS.WEEK_END < CS.WEEK_START AND CS.ID = C.SCHEDULE_ID ");
        sql.append("    ) ORDER BY PS.WEEK_START DESC                                 ");
        sql.append("  ) AND C.SCHEDULE_ID = ?                                         ");
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, phaseId);
        query.executeUpdate();
    }
}
