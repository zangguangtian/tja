/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * YieldSchemeServiceImpl.java
 * 
 * 2017年10月28日-下午2:37:46
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.ObjectUtils;
import com.df.framework.util.StringUtil;
import com.df.project.domain.ProMajorRoleRate;
import com.df.tja.dao.IOcYieldSchemeDao;
import com.df.tja.domain.OcYieldMajor;
import com.df.tja.domain.OcYieldMajorDuty;
import com.df.tja.domain.OcYieldMajorRatio;
import com.df.tja.domain.OcYieldScheme;
import com.df.tja.domain.OcYieldStageMajor;
import com.df.tja.domain.cust.CustOcYieldMajor;
import com.df.tja.domain.cust.CustOcYieldMajorDuty;
import com.df.tja.domain.cust.CustOcYieldScheme;
import com.df.tja.domain.cust.CustOcYieldStageMajor;
import com.df.tja.service.IStandardPriceService;
import com.df.tja.service.IYieldSchemeService;
import com.df.tja.service.IYmConfigService;

/**
 * @author TabZhu
 *
 */
@Service
public class YieldSchemeServiceImpl extends BaseServiceImpl implements IYieldSchemeService {

    @Autowired
    private IOcYieldSchemeDao ocYieldSchemeDao;

    @Autowired
    private IStandardPriceService standardPriceService;

    @Autowired
    private IYmConfigService ymConfigService;

    @Autowired
    private ISysConfigService sysConfigService;

    public void createOrModifyYieldScheme(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            OcYieldScheme ocYieldScheme = null;
            BigDecimal rebate = ymConfigService.queryOcRebateParam();

            //计算土建总产值
            BigDecimal totalAmount = custOcYieldScheme.getTotalAmount();
            //计算各专业产值
            BigDecimal majorAmount = ArithmeticUtil.round(ArithmeticUtil.mul(totalAmount, rebate), 2);
            if ("0".equals(StringUtil.defaultIfBlank(custOcYieldScheme.getId(), "0"))) { //插入
                ocYieldScheme = new OcYieldScheme();
                ObjectUtils.copyProperties(custOcYieldScheme, ocYieldScheme);

                ocYieldScheme.setTotalAmount(totalAmount);
                ocYieldScheme.setMajorAmount(majorAmount);
                //项目负责人(产值)
                ocYieldScheme.setPrincipalYield(
                    ArithmeticUtil.div(ArithmeticUtil.multMul(ocYieldScheme.getPrincipalRate(), totalAmount, rebate),
                        new BigDecimal(100), 2));
                //项目经理(产值)
                ocYieldScheme.setPmYield(ArithmeticUtil.div(
                    ArithmeticUtil.multMul(ocYieldScheme.getPmRate(), totalAmount, rebate), new BigDecimal(100), 2));
                addEntity(OcYieldScheme.class, ocYieldScheme);
            } else { //修改
                //删除施工图产值阶段专业
                OcYieldStageMajor ocYieldStageMajor = new OcYieldStageMajor();
                ocYieldStageMajor.setSchemeId(custOcYieldScheme.getId());
                ocYieldSchemeDao.deleteByObject(OcYieldStageMajor.class, ocYieldStageMajor);

                //删除施工图产值专业责任人
                OcYieldMajorDuty ocYieldMajorDuty = new OcYieldMajorDuty();
                ocYieldMajorDuty.setSchemeId(custOcYieldScheme.getId());
                ocYieldSchemeDao.deleteByObject(OcYieldMajorDuty.class, ocYieldMajorDuty);

                //修改施工图产值策划
                ocYieldScheme = ocYieldSchemeDao.selectByPrimaryKey(OcYieldScheme.class, custOcYieldScheme.getId());
                BeanUtils.copyProperties(custOcYieldScheme, ocYieldScheme,
                    new String[] {"proId", "schemeAmount", "creator", "createDate"});
                ocYieldScheme.setTotalAmount(totalAmount);
                ocYieldScheme.setMajorAmount(majorAmount);
                //项目负责人(产值)
                ocYieldScheme.setPrincipalYield(
                    ArithmeticUtil.div(ArithmeticUtil.multMul(ocYieldScheme.getPrincipalRate(), totalAmount, rebate),
                        new BigDecimal(100), 2));
                //项目经理(产值)
                ocYieldScheme.setPmYield(ArithmeticUtil.div(
                    ArithmeticUtil.multMul(ocYieldScheme.getPmRate(), totalAmount, rebate), new BigDecimal(100), 2));
                ocYieldSchemeDao.update(OcYieldScheme.class, ocYieldScheme);
            }

            //添加施工图产值专业
            Map<String, BigDecimal> majorWLHJ = new HashMap<String, BigDecimal>(0); //专业院内合计
            custOcYieldScheme.setId(ocYieldScheme.getId());
            createOcYieldMajor(custOcYieldScheme, rebate, majorWLHJ);

            //添加施工图产值阶段专业
            createOcYieldStageMajor(custOcYieldScheme);

            //添加施工图产值阶段专业
            BigDecimal majorTotalAmount = ArithmeticUtil.sub(majorAmount, ocYieldScheme.getPrincipalYield());
            majorTotalAmount = ArithmeticUtil.sub(majorTotalAmount, ocYieldScheme.getPmYield());
            createOcYieldMajorDuty(majorTotalAmount, custOcYieldScheme, majorWLHJ);

            //为项目添加专业分配比例
            createProMajorRoleRate(custOcYieldScheme.getProId(), majorWLHJ);
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    public CustOcYieldScheme queryOcYieldSchemeById(String id) throws RuntimeException {
        CustOcYieldScheme custOcYieldScheme = null;
        try {
            custOcYieldScheme = ocYieldSchemeDao.selectOcYieldSchemeById(id);
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
        return custOcYieldScheme;
    }

    public Map<String, CustOcYieldMajorDuty> queryOcYieldMajorDutiesBySchemeId(String sId) throws RuntimeException {
        Map<String, CustOcYieldMajorDuty> majorDutyMap = null;
        try {
            List<CustOcYieldMajorDuty> duties = ocYieldSchemeDao.selectOcYieldMajorDutiesBySchemeId(sId);
            if (duties != null && !duties.isEmpty()) {
                majorDutyMap = new HashMap<String, CustOcYieldMajorDuty>(0);
                for (CustOcYieldMajorDuty duty : duties) {
                    majorDutyMap.put(duty.getMajorCode(), duty);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
        return majorDutyMap;
    }

    public Map<String, CustOcYieldStageMajor> queryOcYieldStageMajorsBySchemeId(String schemeId)
        throws RuntimeException {
        Map<String, CustOcYieldStageMajor> stageMajorMap = null;
        try {
            OcYieldStageMajor entity = new OcYieldStageMajor();
            entity.setSchemeId(schemeId);
            List<OcYieldStageMajor> stageMajors = ocYieldSchemeDao.selectByHQLCondition(OcYieldStageMajor.class, entity,
                null);

            if (stageMajors != null && !stageMajors.isEmpty()) {
                stageMajorMap = new HashMap<String, CustOcYieldStageMajor>(0);
                CustOcYieldStageMajor custStageMajor = null;
                for (OcYieldStageMajor stageMajor : stageMajors) {
                    custStageMajor = new CustOcYieldStageMajor();
                    ObjectUtils.copyProperties(stageMajor, custStageMajor);
                    stageMajorMap.put(stageMajor.getCategory() + "." + stageMajor.getMajorCode(), custStageMajor);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
        return stageMajorMap;
    }

    public List<CustOcYieldMajor> queryOcYieldMajors(String schemeId) throws RuntimeException {
        List<CustOcYieldMajor> custYieldMajors = null;
        try {
            OcYieldMajor ocYieldMajor = new OcYieldMajor();
            ocYieldMajor.setSchemeId(schemeId);
            //专业列表
            List<OcYieldMajor> majors = ocYieldSchemeDao.selectBySQLCondition(OcYieldMajor.class, ocYieldMajor, null);
            if (majors != null && !majors.isEmpty()) {
                CustOcYieldMajor custOcYieldMajor = null;
                OcYieldMajorRatio ocYieldMajorRatio = null;
                List<OcYieldMajorRatio> majorRatios = null;
                custYieldMajors = new ArrayList<CustOcYieldMajor>(0);
                Map<String, OcYieldMajorRatio> majorMap = null;
                for (OcYieldMajor yieldMajor : majors) {
                    custOcYieldMajor = new CustOcYieldMajor();
                    ObjectUtils.copyProperties(yieldMajor, custOcYieldMajor);

                    /**查询专业的各比例和产值*/
                    ocYieldMajorRatio = new OcYieldMajorRatio();
                    ocYieldMajorRatio.setSchemeId(schemeId);
                    ocYieldMajorRatio.setMajorId(yieldMajor.getId());
                    ocYieldMajorRatio.setOrderBy("majorCode asc");
                    majorRatios = ocYieldSchemeDao.selectByHQLCondition(OcYieldMajorRatio.class, ocYieldMajorRatio,
                        null);

                    majorMap = new HashMap<String, OcYieldMajorRatio>(0);
                    if (majorRatios != null && !majorRatios.isEmpty()) {
                        for (OcYieldMajorRatio majorRatio : majorRatios) {
                            majorMap.put(yieldMajor.getId() + "." + majorRatio.getMajorCode(), majorRatio);
                        }
                    }
                    custOcYieldMajor.setMajorMap(majorMap);

                    custYieldMajors.add(custOcYieldMajor);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
        return custYieldMajors;
    }

    public List<CustOcYieldMajor> queryMajorAllPrices(String schemeId) throws RuntimeException {
        List<CustOcYieldMajor> prices = null;
        try {
            prices = ocYieldSchemeDao.selectMajorPrices(schemeId);
            if (prices != null && !prices.isEmpty()) {
                JSONObject jsonObject = null;
                Map<String, BigDecimal> ratioMap = new HashMap<String, BigDecimal>(0);
                for (CustOcYieldMajor yieldMajor : prices) {
                    ratioMap.clear();

                    List<OcYieldMajorRatio> ratios = ocYieldSchemeDao.selectMajorRatios(yieldMajor.getPriceId(),
                        yieldMajor.getId());
                    if (ratios != null && !ratios.isEmpty()) {
                        for (OcYieldMajorRatio ratio : ratios) {
                            ratioMap.put(ratio.getMajorCode(), ratio.getMajorRate());
                        }
                    }
                    jsonObject = new JSONObject(ratioMap);
                    yieldMajor.setRatioJson(jsonObject.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prices;
    }

    /**
     * 添加施工图产值专业
     * @param custOcYieldScheme
     * @param rebate
     * @param majorWLHJ 专业院内合计
     * @throws RuntimeException
     */
    private void createOcYieldMajor(CustOcYieldScheme custOcYieldScheme, BigDecimal rebate,
                                    Map<String, BigDecimal> majorWLTotal)
        throws RuntimeException {
        try {
            List<CustOcYieldMajor> yieldMajors = custOcYieldScheme.getYieldMajors();
            if (yieldMajors != null && !yieldMajors.isEmpty()) {
                CustOcYieldMajor ocStandardPrice = null;
                JSONObject majorRatioJson = null;
                OcYieldMajor ocYieldMajor = null;
                String schemeId = custOcYieldScheme.getId();
                Map<String, BigDecimal> majorYieldTotal = new HashMap<String, BigDecimal>(0); //按专业存储每个专业的总产值
                for (CustOcYieldMajor custYieldMajor : yieldMajors) {
                    //获取单价
                    ocStandardPrice = queryStandardPriceById(custYieldMajor.getPriceId(), custYieldMajor.getId());

                    if (StringUtil.isNotBlank(custYieldMajor.getId())) {
                        ocYieldMajor = ocYieldSchemeDao.selectByPrimaryKey(OcYieldMajor.class, custYieldMajor.getId());
                        ObjectUtils.copyProperties(custYieldMajor, ocYieldMajor,
                            new String[] {"schemeId", "creator", "createDate"});
                        ocYieldMajor.setStandardPrice(ocStandardPrice.getStandardPrice());
                        //土建基准产值
                        ocYieldMajor.setStandardYield(ArithmeticUtil.round(
                            ArithmeticUtil.mul(ocYieldMajor.getBuildArea(), ocStandardPrice.getStandardPrice()), 2));
                        //各专业产值
                        ocYieldMajor.setMajorYield(
                            ArithmeticUtil.round(ArithmeticUtil.mul(ocYieldMajor.getStandardYield(), rebate), 2));

                        //施工图产值专业
                        ocYieldSchemeDao.update(OcYieldMajor.class, ocYieldMajor);
                    } else {
                        ocYieldMajor = new OcYieldMajor();
                        ObjectUtils.copyProperties(custYieldMajor, ocYieldMajor);
                        ocYieldMajor.setSchemeId(schemeId);
                        ocYieldMajor.setStandardPrice(ocStandardPrice.getStandardPrice());
                        //土建基准产值
                        ocYieldMajor.setStandardYield(ArithmeticUtil.round(
                            ArithmeticUtil.mul(ocYieldMajor.getBuildArea(), ocStandardPrice.getStandardPrice()), 2));
                        //各专业产值
                        ocYieldMajor.setMajorYield(
                            ArithmeticUtil.round(ArithmeticUtil.mul(ocYieldMajor.getStandardYield(), rebate), 2));
                        //施工图产值专业
                        ocYieldSchemeDao.insert(OcYieldMajor.class, ocYieldMajor);
                    }

                    //施工图产值专业比例
                    majorRatioJson = new JSONObject(ocStandardPrice.getRatioJson());
                    createOcYieldMajorRatio(ocYieldMajor, custOcYieldScheme.getProId(), majorRatioJson,
                        majorYieldTotal);
                }

                //计算院内合计
                if (!majorYieldTotal.isEmpty()) {
                    //计算所有专业的总产值
                    BigDecimal wlYieldTotal = new BigDecimal(0);
                    for (BigDecimal majorYield : majorYieldTotal.values()) {
                        wlYieldTotal = ArithmeticUtil.add(wlYieldTotal, majorYield);
                    }
                    //计算院内合计
                    BigDecimal total = new BigDecimal(0);
                    BigDecimal tempYield = new BigDecimal(0);
                    for (String majorCode : majorYieldTotal.keySet()) {
                        tempYield = ArithmeticUtil.div(
                            ArithmeticUtil.mul(majorYieldTotal.get(majorCode), new BigDecimal(100)), wlYieldTotal, 2);
                        if (new BigDecimal(100).compareTo(ArithmeticUtil.add(total, tempYield)) > 0) {
                            majorWLTotal.put(majorCode, tempYield);
                            total = ArithmeticUtil.add(total, tempYield);
                        } else {
                            majorWLTotal.put(majorCode,
                                ArithmeticUtil.round(ArithmeticUtil.sub(new BigDecimal(100), total), 2));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * 施工图产值专业比例
     * @param ocYieldMajor
     * @param proId
     * @param majorRatioJson
     * @param majorYieldTotal
     * @throws RuntimeException
     */
    private void createOcYieldMajorRatio(OcYieldMajor ocYieldMajor, String proId, JSONObject majorRatioJson,
                                         Map<String, BigDecimal> majorYieldTotal)
        throws RuntimeException {
        try {
            //不能放在调用queryStandardPriceById方法前执行删除操作
            //删除施工图产值专业比例
            OcYieldMajorRatio ocYieldMajorRatio = new OcYieldMajorRatio();
            ocYieldMajorRatio.setMajorId(ocYieldMajor.getId());
            ocYieldSchemeDao.deleteByObject(OcYieldMajorRatio.class, ocYieldMajorRatio);

            List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode("PM.MAJOR");
            if (majors != null && !majors.isEmpty()) {
                OcYieldMajorRatio majorRatio = null;
                String majorRateVal = "0";
                //院内所有专业产值之和
                BigDecimal tempYield = new BigDecimal(0);
                for (SysConfig major : majors) {
                    majorRatio = new OcYieldMajorRatio();
                    majorRatio.setSchemeId(ocYieldMajor.getSchemeId());
                    majorRatio.setProId(proId);
                    majorRatio.setMajorId(ocYieldMajor.getId());
                    majorRatio.setMajorCode(major.getConfigCode());
                    if (StringUtil.isNotBlank(majorRatioJson.getString(major.getConfigCode()))) {
                        majorRateVal = majorRatioJson.getString(major.getConfigCode());
                    }
                    //比例(%)
                    majorRatio.setMajorRate(new BigDecimal(majorRateVal));
                    //比例*各专业产值/100
                    majorRatio.setMajorYield(
                        ArithmeticUtil.div(ArithmeticUtil.mul(majorRatio.getMajorRate(), ocYieldMajor.getMajorYield()),
                            new BigDecimal(100), 2));
                    addEntity(OcYieldMajorRatio.class, majorRatio);

                    tempYield = new BigDecimal(0);
                    if (majorYieldTotal.containsKey(major.getConfigCode())) {
                        tempYield = majorYieldTotal.get(major.getConfigCode());
                        tempYield = ArithmeticUtil.add(tempYield, majorRatio.getMajorYield());
                        majorYieldTotal.put(major.getConfigCode(), tempYield);
                    } else {
                        majorYieldTotal.put(major.getConfigCode(), majorRatio.getMajorYield());
                    }
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * 施工图产值阶段专业
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    private void createOcYieldStageMajor(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            List<CustOcYieldStageMajor> yieldStageMajors = custOcYieldScheme.getYieldStageMajors();
            if (yieldStageMajors != null && !yieldStageMajors.isEmpty()) {
                OcYieldStageMajor ocYieldStageMajor = null;
                String schemeId = custOcYieldScheme.getId();
                for (CustOcYieldStageMajor custYieldStageMajor : yieldStageMajors) {
                    ocYieldStageMajor = new OcYieldStageMajor();
                    ObjectUtils.copyProperties(custYieldStageMajor, ocYieldStageMajor);
                    ocYieldStageMajor.setSchemeId(schemeId);
                    //施工图产值专业
                    addEntity(OcYieldStageMajor.class, ocYieldStageMajor);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * 施工图产值专业责任人
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    private void createOcYieldMajorDuty(BigDecimal majorTotalAmount, CustOcYieldScheme custOcYieldScheme,
                                        Map<String, BigDecimal> majorWLHJ)
        throws RuntimeException {
        try {
            List<CustOcYieldMajorDuty> yieldMajorDuties = custOcYieldScheme.getYieldMajorDuties();
            if (yieldMajorDuties != null && !yieldMajorDuties.isEmpty()) {
                CustOcYieldMajorDuty ocYieldMajorDuty = null;
                String schemeId = custOcYieldScheme.getId();
                BigDecimal majorWLYield = null;
                for (CustOcYieldMajorDuty custYieldMajorDuty : yieldMajorDuties) {
                    ocYieldMajorDuty = new CustOcYieldMajorDuty();
                    ObjectUtils.copyProperties(custYieldMajorDuty, ocYieldMajorDuty);
                    ocYieldMajorDuty.setSchemeId(schemeId);

                    majorWLYield = majorWLHJ.get(ocYieldMajorDuty.getMajorCode());
                    majorWLYield = ArithmeticUtil.mul(majorTotalAmount, majorWLYield);
                    majorWLYield = ArithmeticUtil.div(majorWLYield, new BigDecimal(100), 2);
                    ocYieldMajorDuty.setMajorYield(majorWLYield);
                    //施工图产值专业
                    addEntity(OcYieldMajorDuty.class, ocYieldMajorDuty);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * 项目专业角色比例
     * @param custOcYieldScheme
     * @throws RuntimeException
     */
    private void createProMajorRoleRate(String proId, Map<String, BigDecimal> majorWLHJ) throws RuntimeException {
        try {
            if (majorWLHJ != null && !majorWLHJ.isEmpty()) {
                ProMajorRoleRate entity = null;
                List<ProMajorRoleRate> majorRoleRates = null;
                for (String majorCode : majorWLHJ.keySet()) {
                    entity = new ProMajorRoleRate();
                    entity.setProId(proId);
                    entity.setAllotCategory("2000");
                    entity.setAllotCode(majorCode);
                    majorRoleRates = queryByCondition(ProMajorRoleRate.class, entity);
                    if (majorRoleRates != null && !majorRoleRates.isEmpty()) {
                        entity = majorRoleRates.get(0);
                        entity.setAllotRate(majorWLHJ.get(majorCode));
                        modify(ProMajorRoleRate.class, entity);
                    } else {
                        entity.setAllotRate(majorWLHJ.get(majorCode));
                        addEntity(ProMajorRoleRate.class, entity);
                    }
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    private CustOcYieldMajor queryStandardPriceById(String priceId, String majorId) throws RuntimeException {
        CustOcYieldMajor price = null;
        JSONObject jsonObject = null;
        try {
            price = new CustOcYieldMajor();

            BigDecimal standardPrice = ocYieldSchemeDao.selectMajorPrice(priceId, majorId);
            if (standardPrice == null) {
                standardPrice = new BigDecimal(0);
            }
            price.setStandardPrice(standardPrice);

            Map<String, BigDecimal> ratioMap = new HashMap<String, BigDecimal>(0);
            List<OcYieldMajorRatio> ratios = ocYieldSchemeDao.selectMajorRatios(priceId, majorId);
            if (ratios != null && !ratios.isEmpty()) {
                for (OcYieldMajorRatio ratio : ratios) {
                    ratioMap.put(ratio.getMajorCode(), ratio.getMajorRate());
                }
            }
            jsonObject = new JSONObject(ratioMap);
            price.setRatioJson(jsonObject.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return price;
    }

}
