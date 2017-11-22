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
}
