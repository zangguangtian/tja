/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * OcSettleYieldMore.java
 * 
 * 2017年9月27日-下午4:53:56
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.math.BigDecimal;

import com.df.tja.domain.OcSettleYield;

/** *
 * <p>OcSettleYieldMore</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月27日 下午4:53:56</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public class OcSettleYieldMore extends OcSettleYield {

    private static final long serialVersionUID = 1L;

    /** 项目编号 */
    private String proCode;
    /** 项目名称 */
    private String proName;
    /** 合同编号 */
    private String contractCode;
    /** 项目类型 */
    private String proType;
    /** 合同额 */
    private BigDecimal contractAmount;
    /** 分包额 */
    private BigDecimal pkgAmount;
    /** 项目负责人 */
    private String pmLeaders;
    /** 项目经理 */
    private String pManagers;

    /**
     * <p> 属性proCode的Getter方法. </p>
     * 
     * @return 返回proCode属性的值
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p> 属性proCode的Setter方法. </p>
     * 
     * @param proCode 为属性proCode设置的值
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p> 属性proName的Getter方法. </p>
     * 
     * @return 返回proName属性的值
     */
    public String getProName() {
        return proName;
    }

    /**
     * <p> 属性proName的Setter方法. </p>
     * 
     * @param proName 为属性proName设置的值
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * <p> 属性contractCode的Getter方法. </p>
     * 
     * @return 返回contractCode属性的值
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * <p> 属性contractCode的Setter方法. </p>
     * 
     * @param contractCode 为属性contractCode设置的值
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * <p> 属性proType的Getter方法. </p>
     * 
     * @return 返回proType属性的值
     */
    public String getProType() {
        return proType;
    }

    /**
     * <p> 属性proType的Setter方法. </p>
     * 
     * @param proType 为属性proType设置的值
     */
    public void setProType(String proType) {
        this.proType = proType;
    }

    /**
     * <p> 属性contractAmount的Getter方法. </p>
     * 
     * @return 返回contractAmount属性的值
     */
    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * <p> 属性contractAmount的Setter方法. </p>
     * 
     * @param contractAmount 为属性contractAmount设置的值
     */
    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * <p> 属性pkgAmount的Getter方法. </p>
     * 
     * @return 返回pkgAmount属性的值
     */
    public BigDecimal getPkgAmount() {
        return pkgAmount;
    }

    /**
     * <p> 属性pkgAmount的Setter方法. </p>
     * 
     * @param pkgAmount 为属性pkgAmount设置的值
     */
    public void setPkgAmount(BigDecimal pkgAmount) {
        this.pkgAmount = pkgAmount;
    }

    /**
     * <p> 属性pmLeaders的Getter方法. </p>
     * 
     * @return 返回pmLeaders属性的值
     */
    public String getPmLeaders() {
        return pmLeaders;
    }

    /**
     * <p> 属性pmLeaders的Setter方法. </p>
     * 
     * @param pmLeaders 为属性pmLeaders设置的值
     */
    public void setPmLeaders(String pmLeaders) {
        this.pmLeaders = pmLeaders;
    }

    /**
     * <p> 属性pManagers的Getter方法. </p>
     * 
     * @return 返回pManagers属性的值
     */
    public String getpManagers() {
        return pManagers;
    }

    /**
     * <p> 属性pManagers的Setter方法. </p>
     * 
     * @param pManagers 为属性pManagers设置的值
     */
    public void setpManagers(String pManagers) {
        this.pManagers = pManagers;
    }

}
