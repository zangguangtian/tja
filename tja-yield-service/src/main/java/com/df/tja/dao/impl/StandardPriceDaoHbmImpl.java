/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * StandardPriceDaoHbmImpl.java
 * 
 * 2017年9月23日-下午3:48:20
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.df.framework.base.dao.impl.BaseDaoHbmImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.dao.IStandardPriceDao;
import com.df.tja.domain.OcStandardPrice;

/**
 * <p>StandardPriceDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月23日 下午3:48:20</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class StandardPriceDaoHbmImpl extends BaseDaoHbmImpl implements IStandardPriceDao {

    /** 
     * @see com.df.tja.dao.IStandardPriceDao#selectStandardPrices
     * (com.df.tja.domain.OcStandardPrice, com.df.framework.hibernate.persistence.Pagination)
     */
    @Override
    public List<OcStandardPrice> selectStandardPrices(OcStandardPrice ocStandardPrice, Pagination pagination) {
        List<OcStandardPrice> list = new ArrayList<OcStandardPrice>();
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlFW = new StringBuilder();
        sql.append(" SELECT OS.ID As id, OS.CATEGORY_CODE AS categoryCode,OS.TYPE_CODE As typeCode, ");
        sql.append(" OS.TYPE_NAME AS typeName,OS.UNIT_PRICE AS unitPrice,OS.REMARK AS remark        ");
        sqlFW.append(" FROM OC_STANDARD_PRICE OS WHERE 1=1                                          ");
        List<String> param = new ArrayList<String>();
        //分类
        if (ocStandardPrice != null && StringUtils.isNotBlank(ocStandardPrice.getCategoryCode())) {
            sqlFW.append(" AND OS.CATEGORY_CODE like ? ");
            param.add(ocStandardPrice.getCategoryCode());
        }

        //类型
        if (ocStandardPrice != null && StringUtils.isNotBlank(ocStandardPrice.getTypeCode())) {
            sqlFW.append(" AND OS.TYPE_CODE like ? ");
            param.add(ocStandardPrice.getTypeCode());
        }

        //类型名称
        if (ocStandardPrice != null && StringUtils.isNotBlank(ocStandardPrice.getTypeName())) {
            sqlFW.append(" AND OS.TYPE_NAME like ? ");
            param.add(ocStandardPrice.getTypeName());
        }
        sql.append(sqlFW);
        sql.append("  ORDER BY OS.CATEGORY_CODE,OS.TYPE_CODE");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(OcStandardPrice.class));
        if (pagination != null) {
            query.setFirstResult(pagination.getStartPosition());
            query.setMaxResults(pagination.getRowsPerPage());
        }
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                query.setString(i, "%" + param.get(i) + "%");
            }
        }
        list = query.list();
        if (pagination != null) {
            sqlFW.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            if (param != null && param.size() > 0) {
                for (int i = 0; i < param.size(); i++) {
                    query.setString(i, "%" + param.get(i) + "%");
                }
            }
            int totalCount = new Integer(query.uniqueResult().toString());
            pagination.setTotalCount(totalCount);
            if (list != null && list.size() > 0) {
                pagination.setCurrentPageSize(list.size());
            }
        }
        return list;
    }

}
