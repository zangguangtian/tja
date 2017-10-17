/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * OcPeriodAdvanceFillDaoHbmImpl.java
 * 
 * 2017年9月25日-下午4:17:40
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.hibernate.persistence.Param;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IOcPeriodAdvanceFillDao;
import com.df.tja.domain.OcPeriodAdvanceFill;

/**
 * <p>OcPeriodAdvanceFillDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月25日 下午4:17:40</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Repository("ocPeriodAdvanceFillDao")
public class OcPeriodAdvanceFillDaoHbmImpl extends BaseDaoHbmImpl implements IOcPeriodAdvanceFillDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<OcPeriodAdvanceFill> queryAdvanceFillList(OcPeriodAdvanceFill advanceFill, Pagination pagination) {
        List<OcPeriodAdvanceFill> list = new ArrayList<OcPeriodAdvanceFill>();
        List<Param> params = new ArrayList<Param>(0);

        StringBuilder sqlWhere = new StringBuilder(100);
        if (StringUtil.isNotBlank(advanceFill.getPeriodName())) {
            sqlWhere.append(" AND t2.PERIOD_NAME like ?");
            params.add(new Param("%" + advanceFill.getPeriodName() + "%", StandardBasicTypes.STRING));
        }
        if (StringUtil.isNotBlank(advanceFill.getProCode())) {
            sqlWhere.append(" AND t3.PRO_CODE like ? ");
            params.add(new Param("%" + advanceFill.getProCode() + "%", StandardBasicTypes.STRING));
        }
        if (StringUtil.isNotBlank(advanceFill.getProName())) {
            sqlWhere.append(" AND t3.PRO_NAME like ?");
            params.add(new Param("%" + advanceFill.getProName() + "%", StandardBasicTypes.STRING));
        }
        if (StringUtil.isNotBlank(advanceFill.getRemark())) {
            sqlWhere.append(" AND t1.REMARK like ? ");
            params.add(new Param("%" + advanceFill.getRemark() + "%", StandardBasicTypes.STRING));
        }

        StringBuilder sqlFrom = new StringBuilder(100);
        sqlFrom.append(" FROM OC_PERIOD_ADVANCE_FILL t1                            ");
        sqlFrom.append(" INNER JOIN OC_PERIOD_MANAGE t2 ON t1.PERIOD_ID = t2.ID    ");
        sqlFrom.append(" INNER JOIN PM_PROJECT_TM t3 ON t1.PRO_ID = t3.ID          ");
        sqlFrom.append(" WHERE 1=1                                                 ");
        sqlFrom.append(sqlWhere);

        StringBuilder sql = new StringBuilder("");
        sql.append(" SELECT                              ");
        sql.append("     t1.ID AS id,                    ");
        sql.append("     t1.PERIOD_ID AS periodId,       ");
        sql.append("     t1.PRO_ID AS proId,             ");
        sql.append("     t1.REMARK AS remark,            ");
        sql.append("     t2.PERIOD_NAME AS periodName,   ");
        sql.append("     t3.PRO_CODE AS proCode,         ");
        sql.append("     t3.PRO_NAME AS proName          ");
        sql.append(sqlFrom.toString());
        sql.append(" ORDER BY t1.MODIFY_DATE desc        ");

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(OcPeriodAdvanceFill.class));
        addQueryParams(query, params, pagination);
        list = query.list();

        if (pagination != null) {
            sqlFrom.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFrom.toString());
            addQueryParams(query, params, null);
            int totalCount = new Integer(query.uniqueResult().toString());
            pagination.setTotalCount(totalCount);
            if (list != null && list.size() > 0) {
                pagination.setCurrentPageSize(list.size());
            }
        }

        return list;
    }
}
