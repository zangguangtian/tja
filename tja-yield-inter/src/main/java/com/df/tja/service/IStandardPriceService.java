/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IStandardPriceService.java
 * 
 * 2017年9月23日-下午12:21:39
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.List;
import java.util.Map;

import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcStandardPrice;

/**
 * <p>IStandardPriceService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月23日 下午12:21:39</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IStandardPriceService extends IBaseService {

    /**
     * <p>描述 : </p>
     *
     * @param outparms
     */
    void queryStandardPriceInfo(Map<String, Object> outparms) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param ocStandardPrice
     * @param page
     * @return
     */
    List<OcStandardPrice> queryStandardPrices(OcStandardPrice ocStandardPrice, Pagination page) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param ocStandardPrice
     */
    void createOrModifyStandardPrice(OcStandardPrice ocStandardPrice) throws RuntimeException, LogicalException;

    /**
     * <p>描述 : </p>
     *
     * @param ocStandardPrice
     */
    void deleteStandardPrice(OcStandardPrice ocStandardPrice) throws RuntimeException;

}
