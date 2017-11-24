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
     * 
     * @return 产值计算系数，取值小于1
     */
    BigDecimal queryOcRebateParam();

    /**
     * 
     * <p>描述 : 初设施工图阶段比例</p>
     *
     * @return
     */
    BigDecimal queryDDStageParam();

    /**
     * 
     * <p>描述 : 施工配合阶段1比例</p>
     *
     * @return
     */
    BigDecimal queryCCOStageParam();

    /**
     * 
     * <p>描述 : 施工配合阶段2比例</p>
     *
     * @return
     */
    BigDecimal queryCCTStageParam();

}
