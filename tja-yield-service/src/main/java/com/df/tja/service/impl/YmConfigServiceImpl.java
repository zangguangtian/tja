/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * SysConfigServiceImpl.java
 * 
 * 2017年10月14日-下午4:08:10
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.StringUtil;
import com.df.tja.service.IYmConfigService;

/**
 * <p>SysConfigServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月14日 下午4:08:10</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

@Service("ymConfigService")
public class YmConfigServiceImpl extends BaseServiceImpl implements IYmConfigService {

    @Autowired
    private ISysConfigService sysConfigService;

    @Override
    public BigDecimal queryOcRebateParam() {
        SysConfig sysConfig = sysConfigService.querySysConfigByCode("OC.REBATE.PARAM");
        if (sysConfig == null || StringUtil.isBlank(sysConfig.getConfigValue())) {
            return new BigDecimal("0");
        }
        return new BigDecimal(sysConfig.getConfigValue());
    }

    @Override
    public BigDecimal queryDDStageParam() {
        SysConfig sysConfig = sysConfigService.querySysConfigByCode("OC.DDSTAGE.PARAM");
        if (sysConfig == null || StringUtil.isBlank(sysConfig.getConfigValue())) {
            return new BigDecimal("0");
        }
        return new BigDecimal(sysConfig.getConfigValue());
    }

    @Override
    public BigDecimal queryCCOStageParam() {
        SysConfig sysConfig = sysConfigService.querySysConfigByCode("OC.CCOSTAGE.PARAM");
        if (sysConfig == null || StringUtil.isBlank(sysConfig.getConfigValue())) {
            return new BigDecimal("0");
        }
        return new BigDecimal(sysConfig.getConfigValue());
    }

    @Override
    public BigDecimal queryCCTStageParam() {
        SysConfig sysConfig = sysConfigService.querySysConfigByCode("OC.CCTSTAGE.PARAM");
        if (sysConfig == null || StringUtil.isBlank(sysConfig.getConfigValue())) {
            return new BigDecimal("0");
        }
        return new BigDecimal(sysConfig.getConfigValue());
    }

}
