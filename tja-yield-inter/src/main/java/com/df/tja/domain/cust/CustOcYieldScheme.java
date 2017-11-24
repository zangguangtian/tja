/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * CustOcYieldScheme.java
 * 
 * 2017年10月24日-上午10:23:13
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain.cust;

import java.math.BigDecimal;
import java.util.List;

import com.df.framework.util.ArithmeticUtil;
import com.df.tja.domain.OcYieldScheme;

/**
 * <p>CustOcYieldScheme</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月24日 上午10:23:13</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public class CustOcYieldScheme extends OcYieldScheme {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -8625746507653337130L;

    /** 属性： 设计负责人*/
    private java.lang.String principalName;

    /** 属性：初设施工图阶段比例 */
    private java.math.BigDecimal ddStageParam;

    /** 属性：施工配合阶段1比例 */
    private java.math.BigDecimal ccoStageParam;

    /** 属性：施工配合阶段2比例 */
    private java.math.BigDecimal cctStageParam;

    /** 属性：专业比例集合 */
    private List<CustOcYieldMajor> yieldMajors;

    /** 属性：专业负责人集合 */
    private List<CustOcYieldMajorDuty> yieldMajorDuties;

    /** 属性：阶段专业产值集合 */
    private List<CustOcYieldStageMajor> yieldStageMajors;

    /**
     * @return principalName
     */
    public java.lang.String getPrincipalName() {
        return principalName;
    }

    /**
     * @param principalName 要设置的 principalName
     */
    public void setPrincipalName(java.lang.String principalName) {
        this.principalName = principalName;
    }

    /**
     * <p> 属性ddStageParam的Getter方法. </p>
     * 
     * @return 返回ddStageParam属性的值
     */
    public java.math.BigDecimal getDdStageParam() {
        return ddStageParam;
    }

    /**
     * <p> 属性ddStageParam的Setter方法. </p>
     * 
     * @param ddStageParam 为属性ddStageParam设置的值
     */
    public void setDdStageParam(java.math.BigDecimal ddStageParam) {
        this.ddStageParam = ddStageParam;
    }

    /**
     * <p> 属性ccoStageParam的Getter方法. </p>
     * 
     * @return 返回ccoStageParam属性的值
     */
    public java.math.BigDecimal getCcoStageParam() {
        return ccoStageParam;
    }

    /**
     * <p> 属性ccoStageParam的Setter方法. </p>
     * 
     * @param ccoStageParam 为属性ccoStageParam设置的值
     */
    public void setCcoStageParam(java.math.BigDecimal ccoStageParam) {
        this.ccoStageParam = ccoStageParam;
    }

    /**
     * <p> 属性cctStageParam的Getter方法. </p>
     * 
     * @return 返回cctStageParam属性的值
     */
    public java.math.BigDecimal getCctStageParam() {
        return cctStageParam;
    }

    /**
     * <p> 属性cctStageParam的Setter方法. </p>
     * 
     * @param cctStageParam 为属性cctStageParam设置的值
     */
    public void setCctStageParam(java.math.BigDecimal cctStageParam) {
        this.cctStageParam = cctStageParam;
    }

    /**
     * @return yieldMajors
     */
    public List<CustOcYieldMajor> getYieldMajors() {
        return yieldMajors;
    }

    /**
     * @param yieldMajors 要设置的 yieldMajors
     */
    public void setYieldMajors(List<CustOcYieldMajor> yieldMajors) {
        this.yieldMajors = yieldMajors;
    }

    /**
     * @return yieldMajorDuties
     */
    public List<CustOcYieldMajorDuty> getYieldMajorDuties() {
        return yieldMajorDuties;
    }

    /**
     * @param yieldMajorDuties 要设置的 yieldMajorDuties
     */
    public void setYieldMajorDuties(List<CustOcYieldMajorDuty> yieldMajorDuties) {
        this.yieldMajorDuties = yieldMajorDuties;
    }

    /**
     * @return yieldStageMajors
     */
    public List<CustOcYieldStageMajor> getYieldStageMajors() {
        return yieldStageMajors;
    }

    /**
     * @param yieldStageMajors 要设置的 yieldStageMajors
     */
    public void setYieldStageMajors(List<CustOcYieldStageMajor> yieldStageMajors) {
        this.yieldStageMajors = yieldStageMajors;
    }

    public BigDecimal getPkgAmount() {
        BigDecimal pkgAmount = super.getPkgAmount();
        if (pkgAmount == null) {
            pkgAmount = new BigDecimal(0);
        }
        return pkgAmount;
    }

    public BigDecimal getSchemeAmount() {
        BigDecimal schemeAmount = super.getSchemeAmount();
        if (schemeAmount == null) {
            schemeAmount = new BigDecimal(0);
        }
        return super.getSchemeAmount();
    }

    public BigDecimal getRebateAmount() {
        BigDecimal rebateAmount = super.getRebateAmount();
        if (rebateAmount == null) {
            rebateAmount = new BigDecimal(0);
        }
        return super.getRebateAmount();
    }

    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = ArithmeticUtil.sub(getContractAmount(), getPkgAmount());
        totalAmount = ArithmeticUtil.sub(totalAmount, getSchemeAmount());
        totalAmount = ArithmeticUtil.sub(totalAmount, getRebateAmount());
        return totalAmount;
    }

    public BigDecimal getPrincipalYield() {
        BigDecimal principalYield = new BigDecimal(0);
        principalYield = ArithmeticUtil.div(ArithmeticUtil.mul(getMajorAmount(), getPrincipalRate()),
            new BigDecimal(100), 2);
        return principalYield;
    }

    public BigDecimal getPmYield() {
        BigDecimal pmYield = new BigDecimal(0);
        pmYield = ArithmeticUtil.div(ArithmeticUtil.mul(getMajorAmount(), getPmRate()), new BigDecimal(100), 2);
        return pmYield;
    }

    public BigDecimal getSecretYield() {
        BigDecimal secretYield = new BigDecimal(0);
        secretYield = ArithmeticUtil.div(ArithmeticUtil.mul(getMajorAmount(), getSecretRate()), new BigDecimal(100), 2);
        return secretYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人初设施工图阶段</p>
     *
     * @return
     */
    public BigDecimal getPrincipalDDStageYield() {
        BigDecimal principalDDStageYield = ArithmeticUtil
            .round(ArithmeticUtil.mul(getPrincipalYield(), getDdStageParam()), 2);
        return principalDDStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人施工配合阶段1</p>
     *
     * @return
     */
    public BigDecimal getPrincipalCCOStageYield() {
        BigDecimal principalCCOStageYield = ArithmeticUtil.round(ArithmeticUtil.multMul(getPrincipalYield(),
            ArithmeticUtil.sub(new BigDecimal(1), getDdStageParam()), getCcoStageParam()), 4);
        return principalCCOStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人施工配合阶段2</p>
     *
     * @return
     */
    public BigDecimal getPrincipalCCTStageYield() {
        BigDecimal principalCCTStageYield = ArithmeticUtil.round(ArithmeticUtil.multMul(getPrincipalYield(),
            ArithmeticUtil.sub(new BigDecimal(1), getDdStageParam()), getCctStageParam()), 4);
        return principalCCTStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人初设施工图阶段</p>
     *
     * @return
     */
    public BigDecimal getPmDDStageYield() {
        BigDecimal principalDDStageYield = ArithmeticUtil.round(ArithmeticUtil.mul(getPmYield(), getDdStageParam()), 2);
        return principalDDStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人施工配合阶段1</p>
     *
     * @return
     */
    public BigDecimal getPmCCOStageYield() {
        BigDecimal principalCCOStageYield = ArithmeticUtil.round(ArithmeticUtil.multMul(getPmYield(),
            ArithmeticUtil.sub(new BigDecimal(1), getDdStageParam()), getCcoStageParam()), 4);
        return principalCCOStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人施工配合阶段2</p>
     *
     * @return
     */
    public BigDecimal getPmCCTStageYield() {
        BigDecimal principalCCTStageYield = ArithmeticUtil.round(ArithmeticUtil.multMul(getPmYield(),
            ArithmeticUtil.sub(new BigDecimal(1), getDdStageParam()), getCctStageParam()), 4);
        return principalCCTStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人初设施工图阶段</p>
     *
     * @return
     */
    public BigDecimal getSecretDDStageYield() {
        BigDecimal principalDDStageYield = ArithmeticUtil.round(ArithmeticUtil.mul(getSecretYield(), getDdStageParam()),
            2);
        return principalDDStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人施工配合阶段1</p>
     *
     * @return
     */
    public BigDecimal getSecretCCOStageYield() {
        BigDecimal principalCCOStageYield = ArithmeticUtil.round(ArithmeticUtil.multMul(getSecretYield(),
            ArithmeticUtil.sub(new BigDecimal(1), getDdStageParam()), getCcoStageParam()), 4);
        return principalCCOStageYield;
    }

    /**
     * 
     * <p>描述 : 计算项目负责人施工配合阶段2</p>
     *
     * @return
     */
    public BigDecimal getSecretCCTStageYield() {
        BigDecimal principalCCTStageYield = ArithmeticUtil.round(ArithmeticUtil.multMul(getSecretYield(),
            ArithmeticUtil.sub(new BigDecimal(1), getDdStageParam()), getCctStageParam()), 4);
        return principalCCTStageYield;
    }
}
