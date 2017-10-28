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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.ObjectUtils;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IOcYieldSchemeDao;
import com.df.tja.domain.OcStandardPrice;
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

            BigDecimal totalAmount = custOcYieldScheme.getTotalAmount();
            BigDecimal majorAmount = ArithmeticUtil.round(ArithmeticUtil.mul(totalAmount, rebate), 2);
            if ("0".equals(StringUtil.defaultIfBlank(custOcYieldScheme.getId(), "0"))) { //插入
                ocYieldScheme = new OcYieldScheme();
                ObjectUtils.copyProperties(custOcYieldScheme, ocYieldScheme);

                ocYieldScheme.setTotalAmount(totalAmount);
                ocYieldScheme.setMajorAmount(majorAmount);
                ocYieldScheme.setPrincipalYield(
                    ArithmeticUtil.div(ArithmeticUtil.multMul(ocYieldScheme.getPrincipalRate(), totalAmount, rebate),
                        new BigDecimal(100), 2));
                ocYieldScheme.setPmYield(ArithmeticUtil.div(
                    ArithmeticUtil.multMul(ocYieldScheme.getPmRate(), totalAmount, rebate), new BigDecimal(100), 2));
                addEntity(OcYieldScheme.class, ocYieldScheme);
            } else { //修改
                //删除施工图产值专业
                OcYieldMajor ocYieldMajor = new OcYieldMajor();
                ocYieldMajor.setSchemeId(custOcYieldScheme.getId());
                ocYieldSchemeDao.deleteByObject(OcYieldMajor.class, ocYieldMajor);

                //删除施工图产值专业比例
                OcYieldMajorRatio ocYieldMajorRatio = new OcYieldMajorRatio();
                ocYieldMajorRatio.setSchemeId(custOcYieldScheme.getId());
                ocYieldSchemeDao.deleteByObject(OcYieldMajorRatio.class, ocYieldMajorRatio);

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
                ObjectUtils.batchCopyProperties(custOcYieldScheme, ocYieldScheme,
                    new String[] {"proId", "schemeAmount", "creator", "createDate"});
                ocYieldScheme.setTotalAmount(totalAmount);
                ocYieldScheme.setMajorAmount(majorAmount);
                ocYieldScheme.setPrincipalYield(
                    ArithmeticUtil.div(ArithmeticUtil.multMul(ocYieldScheme.getPrincipalRate(), totalAmount, rebate),
                        new BigDecimal(100), 2));
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

        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
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
                OcStandardPrice ocStandardPrice = null;
                JSONObject majorRatioJson = null;
                OcYieldMajor ocYieldMajor = null;
                String schemeId = custOcYieldScheme.getId();
                for (CustOcYieldMajor custYieldMajor : yieldMajors) {
                    ocYieldMajor = new OcYieldMajor();
                    ObjectUtils.copyProperties(custYieldMajor, ocYieldMajor);

                    ocStandardPrice = standardPriceService.queryStandardPriceById(ocYieldMajor.getPriceId());

                    ocYieldMajor.setSchemeId(schemeId);
                    ocYieldMajor.setStandardPrice(ocStandardPrice.getUnitPrice());
                    //土建基准产值
                    ocYieldMajor.setStandardYield(ArithmeticUtil
                        .round(ArithmeticUtil.mul(ocYieldMajor.getBuildArea(), ocStandardPrice.getUnitPrice()), 2));
                    //各专业产值
                    ocYieldMajor.setMajorYield(
                        ArithmeticUtil.round(ArithmeticUtil.mul(ocYieldMajor.getStandardYield(), rebate), 2));
                    //施工图产值专业
                    addEntity(OcYieldMajor.class, ocYieldMajor);

                    //施工图产值专业比例
                    majorRatioJson = new JSONObject(ocStandardPrice.getRatioJson());
                    createOcYieldMajorRatio(ocYieldMajor, custOcYieldScheme.getProId(), majorRatioJson, majorWLTotal);
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
     * @param majorWLTotal
     * @throws RuntimeException
     */
    private void createOcYieldMajorRatio(OcYieldMajor ocYieldMajor, String proId, JSONObject majorRatioJson,
                                         Map<String, BigDecimal> majorWLTotal)
        throws RuntimeException {
        try {
            List<SysConfig> majors = sysConfigService.querySysConfigsByParentCode("PM.MAJOR");
            if (majors != null && !majors.isEmpty()) {
                OcYieldMajorRatio majorRatio = null;
                String majorRateVal = "0";
                //院内所有专业产值之和
                BigDecimal tempYield = new BigDecimal(0);
                BigDecimal wlYieldTotal = new BigDecimal(0);
                //按专业存储总产值
                Map<String, BigDecimal> majorYieldTotal = new HashMap<String, BigDecimal>(0);
                List<OcYieldMajorRatio> ratios = new ArrayList<OcYieldMajorRatio>();
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
                    ratios.add(majorRatio);

                    tempYield = new BigDecimal(0);
                    wlYieldTotal = ArithmeticUtil.add(wlYieldTotal, majorRatio.getMajorYield());
                    if (majorYieldTotal.containsKey(major.getConfigCode())) {
                        tempYield = majorYieldTotal.get(major.getConfigCode());
                        tempYield = ArithmeticUtil.add(tempYield, majorRatio.getMajorYield());
                        majorYieldTotal.put(major.getConfigCode(), tempYield);
                    } else {
                        majorYieldTotal.put(major.getConfigCode(), majorRatio.getMajorYield());
                    }
                }
                //施工图产值专业比例
                ocYieldSchemeDao.batchInsert(OcYieldMajorRatio.class, ratios);

                //计算院内合计
                if (!majorYieldTotal.isEmpty()) {
                    BigDecimal total = new BigDecimal(0);
                    for (String majorCode : majorYieldTotal.keySet()) {
                        tempYield = ArithmeticUtil.div(majorYieldTotal.get(majorCode), wlYieldTotal, 2);
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
     * 施工图产值专业责任人表
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
                    addEntity(CustOcYieldMajorDuty.class, ocYieldMajorDuty);
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(YieldSchemeServiceImpl.class, "", ex);
            throw new RuntimeException(ex);
        }
    }

}
