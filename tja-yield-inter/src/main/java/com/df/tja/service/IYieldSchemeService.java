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

import java.util.List;
import java.util.Map;

import com.df.framework.base.service.IBaseService;
import com.df.tja.domain.cust.CustOcYieldMajor;
import com.df.tja.domain.cust.CustOcYieldMajorDuty;
import com.df.tja.domain.cust.CustOcYieldScheme;
import com.df.tja.domain.cust.CustOcYieldStageMajor;

/**
 * @author TabZhu
 *
 */
public interface IYieldSchemeService extends IBaseService {

    /**
     * 
     * <p>描述 : </p>
     * @para proId
     *
     * @return
     * @throws RuntimeException
     */
    String createYieldScheme(String proId) throws RuntimeException;

    /**
      * 按ID查询施工图产值策划
      * @param id
      * @return
      * @throws RuntimeException
      */

    CustOcYieldScheme queryOcYieldSchemeById(String id) throws RuntimeException;

    /**
      * 查询各专业部门产值及负责人
      * @param schemeId
      * @return
      * @throws RuntimeException
      */

    Map<String, CustOcYieldMajorDuty> queryOcYieldMajorDutiesBySchemeId(String schemeId) throws RuntimeException;

    /**
      * 查询各专业的阶段产值
      * @param schemeId
      * @return
      * @throws RuntimeException
      */

    Map<String, CustOcYieldStageMajor> queryOcYieldStageMajorsBySchemeId(String schemeId) throws RuntimeException;

    /**
      * 通过策划ID查询专业列表
      * @param schemeId
      * @return
      * @throws RuntimeException
      */

    List<CustOcYieldMajor> queryOcYieldMajors(String schemeId) throws RuntimeException;

    /**
      * 查询所有的基准单价及专业比例，如果存在策划，则以当前记录的基准单价及专业比例为准
      * @param schemeId
      * @return
      * @throws RuntimeException
      */
    List<CustOcYieldMajor> queryMajorAllPrices(String schemeId) throws RuntimeException;

    /**
     * 
     * <p>描述 : 保存施工图产值策划的专业比例</p>
     *
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    void createYieldSchemeRatio(CustOcYieldScheme custOcYieldScheme) throws RuntimeException;

    /**
     * 
     * <p>描述 : 保存施工图产值策划的土建产值</p>
     *
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    void createYieldSchemeCivil(CustOcYieldScheme custOcYieldScheme) throws RuntimeException;

    /**
     * 
     * <p>描述 : 保存施工图产值策划的各专业产值</p>
     *
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    void createYieldSchemeStage(CustOcYieldScheme custOcYieldScheme) throws RuntimeException;

    /**
     * 
     * <p>描述 : 保存施工图产值策划的项目管理产值</p>
     *
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    void createYieldSchemeProject(CustOcYieldScheme custOcYieldScheme) throws RuntimeException;

    /**
     * 
     * <p>描述 : 保存施工图产值策划的各专业部门负责人会签</p>
     *
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    void createYieldSchemePrincipal(CustOcYieldScheme custOcYieldScheme) throws RuntimeException;
}
