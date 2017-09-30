/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao.impl
 *
 * WfWeekFillDaoHbmImpl.java
 * 
 * 2017年9月18日-下午3:57:38
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.tja.dao.IWfWeekFillDao;
import com.df.tja.domain.WfWeekFill;

/**
 * <p>WfWeekFillDaoHbmImpl</p>
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
@Repository("wfWeekFillDao")
public class WfWeekFillDaoHbmImpl extends BaseDaoHbmImpl implements IWfWeekFillDao {

    @Override
    public WfWeekFill queryWfWeekFill(String proId, String periodId) {
        StringBuilder sql = new StringBuilder();

        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter(0, proId);
        query.setParameter(1, periodId);
        query.setResultTransformer(Transformers.aliasToBean(WfWeekFill.class));
        return (WfWeekFill) query.uniqueResult();
    }

}
