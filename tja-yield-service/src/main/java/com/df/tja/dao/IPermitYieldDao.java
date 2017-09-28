/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IPermitYieldDao.java
 * 
 * 2017年9月27日-下午4:47:21
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcPermitYield;

/**
 * <p>IPermitYieldDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月27日 下午4:47:21</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IPermitYieldDao extends IBaseDao {

    /**
     * <p>描述 : </p>
     *
     * @param ocPermitYield
     * @param page
     * @return
     */
    List<OcPermitYield> selectPermitYield(OcPermitYield ocPermitYield, Pagination page);

}
