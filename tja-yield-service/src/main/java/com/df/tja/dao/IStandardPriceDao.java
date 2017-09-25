/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IStandardPriceDao.java
 * 
 * 2017年9月23日-下午3:47:14
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcStandardPrice;

/**
 * <p>IStandardPriceDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月23日 下午3:47:14</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IStandardPriceDao {

    /**
     * <p>描述 : </p>
     *
     * @param ocStandardPrice
     * @param page
     * @return
     */
    List<OcStandardPrice> selectStandardPrices(OcStandardPrice ocStandardPrice, Pagination page) throws Exception;

}
