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
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.ObjectUtils;
import com.df.framework.util.StringUtil;
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
import com.df.tja.service.IWfPlanSchemeService;
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
    private IYmConfigService ymConfigService;

    @Autowired
    private IWfPlanSchemeService wfPlanSchemeService;

    public String createYieldScheme(String proId) throws RuntimeException {
        String id = null;
        try {
            OcYieldScheme entity = new OcYieldScheme();
            entity.setProId(proId);
            entity.setRelationId(StringUtil.getUUID());
            //查询本项目的方案产值
            BigDecimal planYield = new BigDecimal(0);//wfPlanSchemeService.queryPlanYieldByProId(proId);
            if (planYield == null) {
                planYield = new BigDecimal(0);
            }
            entity.setSchemeAmount(planYield);
            id = addEntity(OcYieldScheme.class, entity);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return id;
    }

    public CustOcYieldScheme queryOcYieldSchemeById(String id) throws RuntimeException {
        CustOcYieldScheme custOcYieldScheme = null;
        try {
            custOcYieldScheme = ocYieldSchemeDao.selectOcYieldSchemeById(id);

            BigDecimal ddStageParam = ymConfigService.queryDDStageParam();
            custOcYieldScheme.setDdStageParam(ddStageParam);

            BigDecimal ccoStageParam = ymConfigService.queryCCOStageParam();
            custOcYieldScheme.setCcoStageParam(ccoStageParam);

            BigDecimal cctStageParam = ymConfigService.queryCCTStageParam();
            custOcYieldScheme.setCctStageParam(cctStageParam);
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

    public Map<String, CustOcYieldStageMajor> queryOcYieldStageMajorsBySchemeId(String id) throws RuntimeException {
        Map<String, CustOcYieldStageMajor> stageMajorMap = null;
        try {
            OcYieldStageMajor entity = new OcYieldStageMajor();
            entity.setSchemeId(id);
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
            //专业列表
            List<CustOcYieldMajor> majors = ocYieldSchemeDao.selectOcYieldMajorsBySchemeId(schemeId);
            if (majors != null && !majors.isEmpty()) {
                CustOcYieldMajor custOcYieldMajor = null;
                OcYieldMajorRatio ocYieldMajorRatio = null;
                List<OcYieldMajorRatio> majorRatios = null;
                custYieldMajors = new ArrayList<CustOcYieldMajor>(0);
                Map<String, OcYieldMajorRatio> majorMap = null;
                for (CustOcYieldMajor yieldMajor : majors) {
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

    public void createYieldSchemeRatio(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            List<String> majorIds = new ArrayList<String>(0);

            //先删除所有的专业比例
            OcYieldMajorRatio entity = new OcYieldMajorRatio();
            entity.setSchemeId(custOcYieldScheme.getId());
            ocYieldSchemeDao.deleteByObject(OcYieldMajorRatio.class, entity);

            List<CustOcYieldMajor> yieldMajors = custOcYieldScheme.getYieldMajors();
            if (yieldMajors != null && !yieldMajors.isEmpty()) {
                OcYieldMajor ocYieldMajor = null;
                BigDecimal rebate = ymConfigService.queryOcRebateParam();
                for (CustOcYieldMajor custYieldMajor : yieldMajors) {
                    //如果已经记录在数据库中
                    if (StringUtil.isNotBlank(custYieldMajor.getId())) {
                        ocYieldMajor = ocYieldSchemeDao.selectByPrimaryKey(OcYieldMajor.class, custYieldMajor.getId());
                        ObjectUtils.copyProperties(custYieldMajor, ocYieldMajor,
                            new String[] {"schemeId", "creator", "createDate"});
                        //土建基准产值
                        ocYieldMajor.setStandardYield(custYieldMajor.getStandardYield());
                        //各专业产值
                        ocYieldMajor.setMajorYield(
                            ArithmeticUtil.round(ArithmeticUtil.mul(custYieldMajor.getStandardYield(), rebate), 2));

                        //施工图产值专业
                        ocYieldSchemeDao.update(OcYieldMajor.class, ocYieldMajor);
                    } else {
                        ocYieldMajor = new OcYieldMajor();
                        ObjectUtils.copyProperties(custYieldMajor, ocYieldMajor);
                        ocYieldMajor.setSchemeId(custOcYieldScheme.getId());
                        ocYieldMajor.setStandardYield(custYieldMajor.getStandardPrice());
                        ocYieldMajor.setMajorYield(
                            ArithmeticUtil.round(ArithmeticUtil.mul(custYieldMajor.getStandardYield(), rebate), 2));
                        ocYieldSchemeDao.insert(OcYieldMajor.class, ocYieldMajor);
                    }
                    //记录存在记录的ID
                    majorIds.add(ocYieldMajor.getId());

                    //处理专业比例
                    createOcYieldMajorRatio(ocYieldMajor, custOcYieldScheme.getProId(), custYieldMajor.getRatioJson());
                }
            }

            //删除已经删掉的专业比例
            ocYieldSchemeDao.deleteMajors(custOcYieldScheme.getId(), majorIds);

            //保存专业扣减
            createOcYieldMajorDuty(custOcYieldScheme, "RATIO");

            //调用存储过程修改其他几张关联表
            ocYieldSchemeDao.calOtherYield(custOcYieldScheme.getId(), "RATIO");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createYieldSchemeCivil(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            OcYieldScheme ocYieldScheme = ocYieldSchemeDao.selectByPrimaryKey(OcYieldScheme.class,
                custOcYieldScheme.getId());
            if (ocYieldScheme != null) {
                ObjectUtils.batchCopyProperties(custOcYieldScheme, ocYieldScheme,
                    new String[] {"contractAmount", "pkgAmount", "schemeAmount", "rebateAmount"});
                ocYieldScheme.setTotalAmount(custOcYieldScheme.getTotalAmount());
                BigDecimal rebateParam = ymConfigService.queryOcRebateParam();
                ocYieldScheme.setMajorAmount(
                    ArithmeticUtil.round(ArithmeticUtil.mul(rebateParam, ocYieldScheme.getTotalAmount()), 2));
                /*ocYieldScheme.setPrincipalYield(custOcYieldScheme.getPrincipalYield());
                ocYieldScheme.setPmYield(custOcYieldScheme.getPmYield());
                ocYieldScheme.setSecretYield(custOcYieldScheme.getSecretYield());*/
                ocYieldSchemeDao.update(OcYieldScheme.class, ocYieldScheme);

                //插入各专业的产值记录
                createOcYieldMajorDuty(custOcYieldScheme, "civil");

                //调用存储过程修改其他几张关联表
                ocYieldSchemeDao.calOtherYield(custOcYieldScheme.getId(), "CIVIL");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createYieldSchemeStage(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            List<CustOcYieldStageMajor> yieldStageMajors = custOcYieldScheme.getYieldStageMajors();
            if (yieldStageMajors != null && !yieldStageMajors.isEmpty()) {
                OcYieldStageMajor entity = new OcYieldStageMajor();
                entity.setSchemeId(custOcYieldScheme.getId());
                ocYieldSchemeDao.deleteByObject(OcYieldStageMajor.class, entity);

                OcYieldStageMajor ocYieldStageMajorRatio = null;
                OcYieldStageMajor ocYieldStageMajorYield = null;
                for (CustOcYieldStageMajor custYieldStageMajor : yieldStageMajors) {
                    ocYieldStageMajorRatio = new OcYieldStageMajor();
                    ObjectUtils.copyProperties(custYieldStageMajor, ocYieldStageMajorRatio);
                    ocYieldStageMajorRatio.setSchemeId(custOcYieldScheme.getId());
                    ocYieldStageMajorRatio.setCategory("1000");
                    addEntity(OcYieldStageMajor.class, ocYieldStageMajorRatio);

                    ocYieldStageMajorYield = new OcYieldStageMajor();
                    ocYieldStageMajorYield.setSchemeId(custOcYieldScheme.getId());
                    ocYieldStageMajorYield.setCategory("2000");
                    ocYieldStageMajorYield.setMajorCode(custYieldStageMajor.getMajorCode());
                    //下面几个产值通过存储过程计算处理
                    ocYieldStageMajorYield.setPreliminary(new BigDecimal(0));
                    ocYieldStageMajorYield.setDrawing(new BigDecimal(0));
                    ocYieldStageMajorYield.setSubTotal(new BigDecimal(0));
                    ocYieldStageMajorYield.setCoordination(new BigDecimal(0));
                    ocYieldStageMajorYield.setCap(new BigDecimal(0));
                    ocYieldStageMajorYield.setCheck(new BigDecimal(0));
                    addEntity(OcYieldStageMajor.class, ocYieldStageMajorYield);
                }

                //调用存储过程修改其他几张关联表
                ocYieldSchemeDao.calOtherYield(custOcYieldScheme.getId(), "STAGE");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createYieldSchemeProject(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            OcYieldScheme ocYieldScheme = ocYieldSchemeDao.selectByPrimaryKey(OcYieldScheme.class,
                custOcYieldScheme.getId());
            if (ocYieldScheme != null) {
                //用于下面的项目负责人、项目经理、项目秘书的产值计算
                custOcYieldScheme.setContractAmount(ocYieldScheme.getContractAmount());
                custOcYieldScheme.setPkgAmount(ocYieldScheme.getPkgAmount());
                custOcYieldScheme.setSchemeAmount(ocYieldScheme.getSchemeAmount());
                custOcYieldScheme.setRebateAmount(ocYieldScheme.getRebateAmount());

                ocYieldScheme.setPrincipalRate(custOcYieldScheme.getPrincipalRate());
                ocYieldScheme.setPrincipalYield(custOcYieldScheme.getPrincipalYield());
                ocYieldScheme.setPmRate(custOcYieldScheme.getPmRate());
                ocYieldScheme.setPmYield(custOcYieldScheme.getPmYield());
                ocYieldScheme.setSecretRate(custOcYieldScheme.getSecretRate());
                ocYieldScheme.setSecretYield(custOcYieldScheme.getSecretYield());
                ocYieldSchemeDao.update(OcYieldScheme.class, ocYieldScheme);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createYieldSchemePrincipal(CustOcYieldScheme custOcYieldScheme) throws RuntimeException {
        try {
            OcYieldScheme ocYieldScheme = ocYieldSchemeDao.selectByPrimaryKey(OcYieldScheme.class,
                custOcYieldScheme.getId());
            if (ocYieldScheme != null) {
                ocYieldScheme.setPrincipalId(custOcYieldScheme.getPrincipalId());
                ocYieldScheme.setRemark(custOcYieldScheme.getRemark());
                ocYieldSchemeDao.update(OcYieldScheme.class, ocYieldScheme);

                //修改各专业的负责人
                createOcYieldMajorDuty(custOcYieldScheme, "principal");
            }
        } catch (Exception ex) {
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
    private void createOcYieldMajorRatio(OcYieldMajor yieldMajor, String proId,
                                         String ratioJson) throws RuntimeException {
        try {
            JSONObject ratioJsonObj = new JSONObject(ratioJson);
            Set<String> majorCodes = ratioJsonObj.keySet();
            if (majorCodes != null && !majorCodes.isEmpty()) {
                String majorRateVal = null;
                OcYieldMajorRatio majorRatio = null;
                BigDecimal temp = new BigDecimal(0);
                BigDecimal majorYield = new BigDecimal(0);
                BigDecimal sYield = yieldMajor.getMajorYield();
                if (sYield == null) {
                    sYield = new BigDecimal(0);
                }
                for (String majorCode : majorCodes) {
                    majorRatio = new OcYieldMajorRatio();
                    majorRatio.setSchemeId(yieldMajor.getSchemeId());
                    majorRatio.setProId(proId);
                    majorRatio.setMajorId(yieldMajor.getId());
                    majorRatio.setMajorCode(majorCode);

                    majorRateVal = "0";
                    if (ratioJsonObj.has(majorCode) && StringUtil.isNotBlank(ratioJsonObj.getString(majorCode))) {
                        majorRateVal = ratioJsonObj.getString(majorCode);
                    }
                    //比例(%)
                    majorRatio.setMajorRate(new BigDecimal(majorRateVal));

                    //比例*各专业产值/100
                    majorYield = ArithmeticUtil.div(ArithmeticUtil.mul(majorRatio.getMajorRate(), sYield),
                        new BigDecimal(100), 2);
                    if (ArithmeticUtil.add(temp, majorYield).compareTo(sYield) < 0) {
                        temp = ArithmeticUtil.add(temp, majorYield);
                    } else {
                        majorYield = ArithmeticUtil.sub(sYield, temp);
                    }
                    majorRatio.setMajorYield(majorYield);
                    addEntity(OcYieldMajorRatio.class, majorRatio);
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
      * @param opType civil:土建产值保存调用；principal:各专业部门负责人会签保存时调用
      * @throws RuntimeException
      */

    private void createOcYieldMajorDuty(CustOcYieldScheme custOcYieldScheme, String opType) throws RuntimeException {
        try {
            List<CustOcYieldMajorDuty> yieldMajorDuties = custOcYieldScheme.getYieldMajorDuties();
            if (yieldMajorDuties != null && !yieldMajorDuties.isEmpty()) {
                List<OcYieldMajorDuty> duties = null;
                OcYieldMajorDuty majorDuty = null;
                OcYieldMajorDuty majorDutySearch = new OcYieldMajorDuty();
                majorDutySearch.setSchemeId(custOcYieldScheme.getId());
                for (CustOcYieldMajorDuty custMajorDuty : yieldMajorDuties) {
                    majorDutySearch.setMajorCode(custMajorDuty.getMajorCode());
                    duties = ocYieldSchemeDao.selectBySQLCondition(OcYieldMajorDuty.class, majorDutySearch, null);
                    if (duties == null || duties.isEmpty()) {
                        //插入
                        majorDuty = new OcYieldMajorDuty();
                        majorDuty.setSchemeId(custOcYieldScheme.getId());
                        majorDuty.setMajorCode(custMajorDuty.getMajorCode());
                        majorDuty.setMinusYield(custMajorDuty.getMinusYield());
                        majorDuty.setMajorYield(new BigDecimal(0)); //通过存储过程计算
                        majorDuty.setPrincipalId(custMajorDuty.getPrincipalId());
                        addEntity(OcYieldMajorDuty.class, majorDuty);
                    } else {
                        if ("RATIO".equalsIgnoreCase(opType)) { //只有专业比例保存时才会去修改各专业的专业扣减
                            majorDuty = duties.get(0);
                            majorDuty.setMinusYield(custMajorDuty.getMinusYield());
                            ocYieldSchemeDao.update(OcYieldMajorDuty.class, majorDuty);
                        } else if ("principal".equalsIgnoreCase(opType)) { //只有各专业部门负责人会签保存时才会去修改各专业的负责人
                            majorDuty = duties.get(0);
                            majorDuty.setPrincipalId(custMajorDuty.getPrincipalId());
                            ocYieldSchemeDao.update(OcYieldMajorDuty.class, majorDuty);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

    protected <T> void copyOtherProperties(Class<T> entityClass, Object source, Object target) throws RuntimeException {
        try {
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();

            String methodName = null;
            if (stacks != null && stacks.length > 15) {
                methodName = stacks[15].getMethodName();
            }
            //当方法名不为空时
            if (StringUtil.isNotEmpty(methodName)) {
                //当调用方法是baseSave时
                if ("baseSave".equals(methodName)) {
                    ObjectUtils.batchCopyProperties(source, target, "landArea");
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
