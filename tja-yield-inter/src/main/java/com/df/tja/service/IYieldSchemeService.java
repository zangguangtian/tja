/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IYieldSchemeService.java
 * 
 * 2017年10月28日-下午2:31:56
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.tja.domain.cust.CustOcYieldScheme;

/**
 * @author TabZhu
 *
 */
public interface IYieldSchemeService extends IBaseService {

    /**
     * 插入或修改施工图产值策划
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    void createOrModifyYieldScheme(CustOcYieldScheme custOcYieldScheme) throws RuntimeException;
}
