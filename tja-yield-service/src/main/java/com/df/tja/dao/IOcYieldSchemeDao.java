/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IOcYieldSchemeDao.java
 * 
 * 2017年10月28日-下午2:59:45
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.cust.CustOcYieldMajorDuty;
import com.df.tja.domain.cust.CustOcYieldScheme;

/**
 * @author TabZhu
 *
 */
public interface IOcYieldSchemeDao extends IBaseDao {

    /**
     * 按ID查询施工图产值策划信息
     * @param id
     * @return
     */
    CustOcYieldScheme selectOcYieldSchemeById(String id);

    List<CustOcYieldMajorDuty> selectOcYieldMajorDutiesBySchemeId(String schemeId);
}
