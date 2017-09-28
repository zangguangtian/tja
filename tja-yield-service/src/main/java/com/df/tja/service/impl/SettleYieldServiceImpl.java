/**
 * 项目名称:df-pro-service
 *
 * com.df.project.service.impl
 *
 * SettleYieldServiceImpl.java
 * 
 * 2017年9月18日-下午3:53:46
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.util.LoggerUtil;
import com.df.tja.dao.IOcSettleYieldDao;
import com.df.tja.domain.OcSettleYield;
import com.df.tja.domain.cust.OcSettleYieldMore;
import com.df.tja.service.ISettleYieldService;

/**
 * <p>SettleYieldServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:53:46</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Service("settleYieldService")
public class SettleYieldServiceImpl extends BaseServiceImpl implements ISettleYieldService {

    @Autowired
    IOcSettleYieldDao ocSettleYieldDao;

    @Override
    public OcSettleYieldMore querySettleYield(String id) throws RuntimeException {
        return ocSettleYieldDao.querySettleYield(id);
    }

    @Override
    public String createOrModifySettleYield(OcSettleYield ocSettleYield) throws RuntimeException {
        String id = ocSettleYield.getId();
        try {
            this.modify(OcSettleYield.class, ocSettleYield);
        } catch (Exception e) {
            LoggerUtil.error(SettleYieldServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return id;
    }

}
