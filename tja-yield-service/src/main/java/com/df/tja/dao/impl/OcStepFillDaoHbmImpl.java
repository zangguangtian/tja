package com.df.tja.dao.impl;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcStepFillDao;
import com.df.tja.domain.cust.OcStepFillMore;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<OcStepFillMore> queryByPreProId(String preProId) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT SF.ID AS id,SF.PRO_ID AS proId,PP.PRO_NAME AS proName,SCF.CONFIG_NAME AS proStatusName,    ");
        sql.append("  (SELECT COUNT(PRO_NAME) FROM OC_STEP_FILL_TM SFT LEFT JOIN PM_PROJECT_TM PPT ON SFT.PRO_ID = PPT.ID WHERE PPT.PRE_PRO_ID = PP.PRE_PRO_ID) AS mergeCount,   ");
        sql.append("  SD.WEEK_START AS weekStart,SD.WEEK_END AS weekEnd,SF.STEP_STATUS AS stepStatus,                   ");
        sql.append("  SF.WORK_CONTENT AS workContent,SF.WORK_PLAN AS workPlan,SF.REMARK AS remark,                      ");
        sql.append("  SF.MODIFIER AS modifier,SF.MODIFY_DATE AS modifyDate                                              ");
        sql.append("  FROM OC_STEP_FILL_TM SF                                                                           ");
        sql.append("  LEFT JOIN OC_SCHEDULE_TM SD ON SF.SCHEDULE_ID = SD.ID                                             ");
        sql.append("  LEFT JOIN PM_PROJECT_TM PP ON SF.PRO_ID = PP.ID                                                   ");
        sql.append("  LEFT JOIN SYS_CONFIG_TM SCF ON PP.PRO_STATUS = SCF.CONFIG_CODE                                    ");
        sql.append("  WHERE PP.PRE_PRO_ID = ?                                                                           ");
        sql.append("  ORDER BY SD.PRO_ID                                                                                ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0,preProId );
        query.setResultTransformer(Transformers.aliasToBean(OcStepFillMore.class));
        return query.list();
    }

}
