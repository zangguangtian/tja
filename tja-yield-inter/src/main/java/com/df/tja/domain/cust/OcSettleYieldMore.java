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
    private String itemNo;
    /** 项目类型 */
    private String proCategory;
    /** 合同额 */
    private BigDecimal contractAmount;
    /** 分包额 */
    private BigDecimal pkgAmount;
    /** 项目负责人 */
    private String proFzrName;
    /** 项目经理 */
    private String proJlName;

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
     * <p> 属性itemNo的Getter方法. </p>
     * 
     * @return 返回itemNo属性的值
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * <p> 属性itemNo的Setter方法. </p>
     * 
     * @param itemNo 为属性itemNo设置的值
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * <p> 属性proCategory的Getter方法. </p>
     * 
     * @return 返回proCategory属性的值
     */
    public String getProCategory() {
        return proCategory;
    }

    /**
     * <p> 属性proCategory的Setter方法. </p>
     * 
     * @param proCategory 为属性proCategory设置的值
     */
    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
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
     * <p> 属性proFzrName的Getter方法. </p>
     * 
     * @return 返回proFzrName属性的值
     */
    public String getProFzrName() {
        return proFzrName;
    }

    /**
     * <p> 属性proFzrName的Setter方法. </p>
     * 
     * @param proFzrName 为属性proFzrName设置的值
     */
    public void setProFzrName(String proFzrName) {
        this.proFzrName = proFzrName;
    }

    /**
     * <p> 属性proJlName的Getter方法. </p>
     * 
     * @return 返回proJlName属性的值
     */
    public String getProJlName() {
        return proJlName;
    }

    /**
     * <p> 属性proJlName的Setter方法. </p>
     * 
     * @param proJlName 为属性proJlName设置的值
     */
    public void setProJlName(String proJlName) {
        this.proJlName = proJlName;
    }

}
