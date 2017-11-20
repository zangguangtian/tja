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
import com.df.tja.domain.OcYieldMajorRatio;
import com.df.tja.domain.cust.CustOcYieldMajor;
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

    /**
     * 按施工图产值策划ID查询所有专业比例
     * @param schemeId
     * @return
     */
    List<CustOcYieldMajor> selectOcYieldMajorsBySchemeId(String schemeId);

    /**
     * 按施工图产值策划ID查询所有专业的产值及负责人
     * @param schemeId
     * @return
     */
    List<CustOcYieldMajorDuty> selectOcYieldMajorDutiesBySchemeId(String schemeId);

    /**
     * 查询施工图专业比例中类型编号下拉列表
     * @param schemeId
     * @return
     */
    List<CustOcYieldMajor> selectMajorPrices(String schemeId);

    /**
     * 查询施工图专业比例中各专业的比例
     * @param priceId
     * @param majorId
     * @return
     */
    List<OcYieldMajorRatio> selectMajorRatios(String priceId, String majorId);

    /**
     * 删除页面上已经删除的专业比例
     * @param schemeId 策划ID
     * @param majorIds 存在的专业比例ID
     */
    void deleteMajors(String schemeId, List<String> majorIds);

    /**
     * 
     * <p>描述 : 根据策划ID，计算土建产值、各专业产值</p>
     *
     * @param schemeId
     * @param opType
     */
    void calOtherYield(String schemeId, String opType);
}
