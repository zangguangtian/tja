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
        String param = sysConfigService.querySysConfigByCode("OC.REBATE.PARAM").getConfigValue();
        if (StringUtil.isBlank(param)) {
            param = "0";
        }
        return new BigDecimal(param);
    }
}
