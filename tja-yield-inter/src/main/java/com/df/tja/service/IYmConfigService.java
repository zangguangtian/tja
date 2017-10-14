/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IYmConfigService.java
 * 
 * 2017年10月14日-下午4:03:35
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.math.BigDecimal;

import com.df.framework.base.service.IBaseService;

/**
 * <p>IYmConfigService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月14日 下午4:03:35</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public interface IYmConfigService extends IBaseService {

    /**
     * 产值计算系数
     * */
    BigDecimal queryOcRebateParam();

}
