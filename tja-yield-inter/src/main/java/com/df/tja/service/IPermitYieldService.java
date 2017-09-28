/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IPermitYieldService.java
 * 
 * 2017年9月27日-下午4:40:18
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.List;

import com.df.framework.base.service.IBaseService;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.OcPermitYield;

/**
 * <p>IPermitYieldService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月27日 下午4:40:18</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IPermitYieldService extends IBaseService {

    /**
     * <p>描述 : </p>
     *
     * @param ocPermitYield
     * @param page
     * @return
     */
    List<OcPermitYield> queryPermitYield(OcPermitYield ocPermitYield, Pagination page) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param ocPermitYield
     */
    void createOrModifyPermitYield(OcPermitYield ocPermitYield) throws RuntimeException;

}
