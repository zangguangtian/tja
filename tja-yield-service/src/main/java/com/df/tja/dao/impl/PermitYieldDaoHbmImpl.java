/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao.impl
 *
 * PermitYieldDaoHbmImpl.java
 * 
 * 2017年9月27日-下午4:49:06
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
import com.df.tja.dao.IPermitYieldDao;
import com.df.tja.domain.OcPermitYield;

/**
 * <p>PermitYieldDaoHbmImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月27日 下午4:49:06</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Repository
public class PermitYieldDaoHbmImpl extends BaseDaoHbmImpl implements IPermitYieldDao {

    /** 
     * @see com.df.tja.dao.IPermitYieldDao#selectPermitYield(com.df.tja.domain.OcPermitYield, com.df.framework.hibernate.persistence.Pagination)
     */
    @Override
    public List<OcPermitYield> selectPermitYield(OcPermitYield ocPermitYield, Pagination pagination) {
        List<OcPermitYield> list = new ArrayList<OcPermitYield>();
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlFW = new StringBuilder();

        sql.append(" SELECT OP.ID AS id,OP.MAJOR_CODE AS majorCode,OP.PERIOD_ID AS periodId,OP.PRO_ID      ");
        sql.append(" AS proId,OP.PERMIT_YIELD AS permitYield,OP.REMARK AS remark,PJ.PRO_CODE AS proCode,   ");
        sql.append(" PJ.PRO_NAME AS proName,OM.PERIOD_NAME AS periodName,SC.CONFIG_NAME AS majorName       ");

        sqlFW.append(" FROM OC_PERMIT_YIELD OP                                                             ");
        sqlFW.append(" LEFT JOIN SYS_CONFIG_TM SC ON OP.MAJOR_CODE = SC.CONFIG_CODE                        ");
        sqlFW.append(" LEFT JOIN PM_PROJECT_TM PJ ON OP.PRO_ID = PJ.ID                                     ");
        sqlFW.append(" LEFT JOIN OC_PERIOD_MANAGE OM ON OM.ID = OP.PERIOD_ID WHERE 1=1                     ");

        List<String> param = new ArrayList<String>();
        //期间
        if (ocPermitYield != null && StringUtils.isNotBlank(ocPermitYield.getPeriodName())) {
            sqlFW.append(" AND OM.PERIOD_NAME LIKE '%'+?+'%' ");
            param.add(ocPermitYield.getPeriodName());
        }

        //项目编号
        if (ocPermitYield != null && StringUtils.isNotBlank(ocPermitYield.getProCode())) {
            sqlFW.append(" AND PJ.PRO_CODE LIKE '%'+?+'%' ");
            param.add(ocPermitYield.getProCode());
        }

        //项目名称
        if (ocPermitYield != null && StringUtils.isNotBlank(ocPermitYield.getProName())) {
            sqlFW.append(" AND PJ.PRO_NAME LIKE '%'+?+'%' ");
            param.add(ocPermitYield.getProName());
        }

        //专业
        if (ocPermitYield != null && StringUtils.isNotBlank(ocPermitYield.getMajorName())) {
            sqlFW.append(" AND SC.CONFIG_NAME LIKE '%'+?+'%' ");
            param.add(ocPermitYield.getMajorName());
        }

        //备注
        if (ocPermitYield != null && StringUtils.isNotBlank(ocPermitYield.getRemark())) {
            sqlFW.append(" AND OP.REMARK LIKE '%'+?+'%' ");
            param.add(ocPermitYield.getRemark());
        }

        sql.append(sqlFW);
        sql.append("  ORDER BY OP.MAJOR_CODE ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(OcPermitYield.class));
        if (pagination != null) {
            query.setFirstResult(pagination.getStartPosition());
            query.setMaxResults(pagination.getRowsPerPage());
        }
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                query.setString(i, param.get(i));
            }
        }
        list = query.list();
        if (pagination != null) {
            sqlFW.insert(0, "select count(*) as count ");
            query = getCurrentSession().createSQLQuery(sqlFW.toString());
            if (param != null && param.size() > 0) {
                for (int i = 0; i < param.size(); i++) {
                    query.setString(i, param.get(i));
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
