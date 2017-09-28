/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * PermitYieldServiceImpl.java
 * 
 * 2017年9月27日-下午4:45:10
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.dao.IPermitYieldDao;
import com.df.tja.domain.OcPermitYield;
import com.df.tja.service.IPermitYieldService;

/**
 * <p>PermitYieldServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月27日 下午4:45:10</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service
public class PermitYieldServiceImpl extends BaseServiceImpl implements IPermitYieldService {

    @Autowired
    private IPermitYieldDao permitYieldDao;

    /** 
     * @see com.df.tja.service.IPermitYieldService#queryPermitYield(com.df.tja.domain.OcPermitYield, com.df.framework.hibernate.persistence.Pagination)
     */
    @Override
    public List<OcPermitYield> queryPermitYield(OcPermitYield ocPermitYield, Pagination page) throws RuntimeException {
        List<OcPermitYield> yields = null;
        try {
            yields = permitYieldDao.selectPermitYield(ocPermitYield, page);
            if (yields != null && !yields.isEmpty()) {
                int number = 0;
                for (OcPermitYield permitYield : yields) {
                    permitYield.setNumber((page.getPageNo() - 1) * page.getRowsPerPage() + (++number));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return yields;
    }

    /** 
     * @see com.df.tja.service.IPermitYieldService#createOrModifyPermitYield(com.df.tja.domain.OcPermitYield)
     */
    @Override
    public void createOrModifyPermitYield(OcPermitYield ocPermitYield) throws RuntimeException {
        try {
            if (StringUtils.isNotBlank(ocPermitYield.getId())) {
                //修改
                modify(OcPermitYield.class, ocPermitYield);
            } else {
                //添加
                if (StringUtils.isNotBlank(ocPermitYield.getMajorCode())
                    && StringUtils.isNotBlank(ocPermitYield.getProId())
                    && StringUtils.isNotBlank(ocPermitYield.getPeriodId()) && ocPermitYield.getPermitYield() != null) {
                    addEntity(OcPermitYield.class, ocPermitYield);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
