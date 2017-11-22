/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao.impl
 *
 * OcPeriodManageDaoHbmImpl.java
 * 
 * 2017年9月18日-下午3:57:38
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IOcPeriodManageDao;
import com.df.tja.domain.OcPeriodManage;

/**
 * <p>OcPeriodManageDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:57:38</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Repository("ocPeriodManageDao")
public class OcPeriodManageDaoHbmImpl extends BaseDaoHbmImpl implements IOcPeriodManageDao {

    @Override
    public OcPeriodManage queryPeriod(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("  SELECT                                               ");
        sql.append("      t1.ID AS id,                                     ");
        sql.append("      t1.TYPE_CODE AS typeCode,                        ");
        sql.append("      t1.PERIOD_NAME AS periodName,                    ");
        sql.append("      t1.RANGE_START AS rangeStart,                    ");
        sql.append("      t1.RANGE_END AS rangeEnd,                        ");
        sql.append("      t1.EXPLAIN AS explain,                           ");
        sql.append("      t1.STATUS_CODE AS statusCode,                    ");
        sql.append("      t1.START_DATE AS startDate,                      ");
        sql.append("      t1.END_DATE AS endDate,                          ");
        sql.append("      t1.REMARK AS remark,                             ");
        sql.append("      t1.CREATOR AS creator,                           ");
        sql.append("      t1.CREATE_DATE AS createDate,                    ");
        sql.append("      t1.MODIFIER AS modifier,                         ");
        sql.append("      t1.MODIFY_DATE AS modifyDate,                    ");
        sql.append("      t2.REAL_NAME AS creatorName,                     ");
        sql.append("      t3.REAL_NAME AS modifierName                     ");
        sql.append("  FROM                                                 ");
        sql.append("      OC_PERIOD_MANAGE t1                              ");
        sql.append("  LEFT JOIN SYS_USER t2 ON t1.CREATOR = t2.ID          ");
        sql.append("  LEFT JOIN SYS_USER t3 ON t1.MODIFIER = t3.ID         ");
        sql.append("  WHERE t1.ID = ?                                      ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, id);
        query.setResultTransformer(Transformers.aliasToBean(OcPeriodManage.class));
        return (OcPeriodManage) query.uniqueResult();
    }

    /** 
     * @see com.df.tja.dao.IOcPeriodManageDao#selectSettlePeriodForRp()
     */
    @Override
    public List<OcPeriodManage> selectSettlePeriodForRp() {
        List<OcPeriodManage> periodManages = new ArrayList<OcPeriodManage>();
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ID AS id, PERIOD_NAME AS periodName      ");
        sql.append(" FROM OC_PERIOD_MANAGE                           ");
        sql.append(" WHERE TYPE_CODE = 'OC.PERIOD.TYPE.SETTLE'       ");
        sql.append(" AND CAST(GETDATE() AS DATE) >= START_DATE       ");
        sql.append(" ORDER BY RANGE_START DESC                       ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(OcPeriodManage.class));
        periodManages = query.list();
        return periodManages;
    }

}
