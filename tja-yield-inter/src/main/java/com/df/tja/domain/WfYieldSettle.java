package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfYieldSETTLE.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.activiti.domain.WfBaseDomain;

/**
 * <p>WfYieldSettle </p>
 * 
 * <p>描述：项目年度产值结算流程表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 9:29:02</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "WF_YIELD_SETTLE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfYieldSettle extends WfBaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -7947951915646943585L;

    /** 属性：分类。1000：年度产值结算；2000：年度产值结算特批 */
    private java.lang.String wfCategory;

    /** 属性：期间ID */
    private java.lang.String periodId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：实际合同额 */
    private BigDecimal contractAmount;

    /** 属性：分包扣减 */
    private BigDecimal pkgAmount;

    /** 属性：方案扣减 */
    private BigDecimal schemeAmount;

    /** 属性：其他扣减 */
    private BigDecimal rebateAmount;

    /** 属性：当年可结算产值（共用当年特批产值） */
    private BigDecimal yearYield;

    /** 属性：历年已结算产值 */
    private BigDecimal hisyearYield;

    /** 属性：项目所处状态 */
    private java.lang.String itemStatus;

    /** 属性：项目负责人结算比例 */
    private BigDecimal principalRate;

    /** 属性：项目经理结算比例 */
    private BigDecimal pmRate;

    /** 属性：项目秘书结算比例 */
    private BigDecimal secretRate;

    /** 属性：记录由哪个特批发起的流程 */
    private java.lang.String permitId;

    /** 属性：备注 */
    private String remark;

    private String creatorName;
    
    /**
     * <p> 属性：wfCategory的Getter方法. </p>
     * 
     * @return 返回分类属性的值
     */
    @Column(name = "WF_CATEGORY")
    public java.lang.String getWfCategory() {
        return wfCategory;
    }

    /**
     * <p> 属性分类的Setter方法. </p>
     * 
     * @param wfCategory 为属性wfCategory设置的值
     */
    public void setWfCategory(java.lang.String wfCategory) {
        this.wfCategory = wfCategory;
    }

    /**
     * <p> 属性：periodId的Getter方法. </p>
     * 
     * @return 返回期间ID属性的值
     */
    @Column(name = "PERIOD_ID")
    public java.lang.String getPeriodId() {
        return periodId;
    }

    /**
     * <p> 属性期间ID的Setter方法. </p>
     * 
     * @param periodId 为属性periodId设置的值
     */
    public void setPeriodId(java.lang.String periodId) {
        this.periodId = periodId;
    }

    /**
     * <p> 属性：proId的Getter方法. </p>
     * 
     * @return 返回项目ID属性的值
     */
    @Column(name = "PRO_ID")
    public java.lang.String getProId() {
        return proId;
    }

    /**
     * <p> 属性项目ID的Setter方法. </p>
     * 
     * @param proId 为属性proId设置的值
     */
    public void setProId(java.lang.String proId) {
        this.proId = proId;
    }

    /**
     * <p> 属性：contractAmount的Getter方法. </p>
     * 
     * @return 返回实际合同额属性的值
     */
    @Column(name = "CONTRACT_AMOUNT")
    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * <p> 属性实际合同额的Setter方法. </p>
     * 
     * @param contractAmount 为属性contractAmount设置的值
     */
    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * <p> 属性：pkgAmount的Getter方法. </p>
     * 
     * @return 返回分包扣减属性的值
     */
    @Column(name = "PKG_AMOUNT")
    public BigDecimal getPkgAmount() {
        return pkgAmount;
    }

    /**
     * <p> 属性分包扣减的Setter方法. </p>
     * 
     * @param pkgAmount 为属性pkgAmount设置的值
     */
    public void setPkgAmount(BigDecimal pkgAmount) {
        this.pkgAmount = pkgAmount;
    }

    /**
     * <p> 属性：schemeAmount的Getter方法. </p>
     * 
     * @return 返回方案扣减属性的值
     */
    @Column(name = "SCHEME_AMOUNT")
    public BigDecimal getSchemeAmount() {
        return schemeAmount;
    }

    /**
     * <p> 属性方案扣减的Setter方法. </p>
     * 
     * @param schemeAmount 为属性schemeAmount设置的值
     */
    public void setSchemeAmount(BigDecimal schemeAmount) {
        this.schemeAmount = schemeAmount;
    }

    /**
     * <p> 属性：rebateAmount的Getter方法. </p>
     * 
     * @return 返回其他扣减属性的值
     */
    @Column(name = "REBATE_AMOUNT")
    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    /**
     * <p> 属性其他扣减的Setter方法. </p>
     * 
     * @param rebateAmount 为属性rebateAmount设置的值
     */
    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    /**
     * <p> 属性：yearYield的Getter方法. </p>
     * 
     * @return 返回当年可结算产值属性的值
     */
    @Column(name = "YEAR_YIELD")
    public BigDecimal getYearYield() {
        return yearYield;
    }

    /**
     * <p> 属性当年可结算产值的Setter方法. </p>
     * 
     * @param yearYield 为属性yearYield设置的值
     */
    public void setYearYield(BigDecimal yearYield) {
        this.yearYield = yearYield;
    }

    /**
     * <p> 属性：hisyearYield的Getter方法. </p>
     * 
     * @return 返回历年已结算产值属性的值
     */
    @Column(name = "HISYEAR_YIELD")
    public BigDecimal getHisyearYield() {
        return hisyearYield;
    }

    /**
     * <p> 属性历年已结算产值的Setter方法. </p>
     * 
     * @param hisyearYield 为属性hisyearYield设置的值
     */
    public void setHisyearYield(BigDecimal hisyearYield) {
        this.hisyearYield = hisyearYield;
    }

    /**
     * <p> 属性：itemStatus的Getter方法. </p>
     * 
     * @return 返回项目所处状态属性的值
     */
    @Column(name = "ITEM_STATUS")
    public java.lang.String getItemStatus() {
        return itemStatus;
    }

    /**
     * <p> 属性项目所处状态的Setter方法. </p>
     * 
     * @param itemStatus 为属性itemStatus设置的值
     */
    public void setItemStatus(java.lang.String itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * <p> 属性：principalRate的Getter方法. </p>
     * 
     * @return 返回项目负责人结算比例属性的值
     */
    @Column(name = "PRINCIPAL_RATE")
    public BigDecimal getPrincipalRate() {
        return principalRate;
    }

    /**
     * <p> 属性项目负责人结算比例的Setter方法. </p>
     * 
     * @param principalRate 为属性principalRate设置的值
     */
    public void setPrincipalRate(BigDecimal principalRate) {
        this.principalRate = principalRate;
    }

    /**
     * <p> 属性：pmRate的Getter方法. </p>
     * 
     * @return 返回项目经理结算比例属性的值
     */
    @Column(name = "PM_RATE")
    public BigDecimal getPmRate() {
        return pmRate;
    }

    /**
     * <p> 属性项目经理结算比例的Setter方法. </p>
     * 
     * @param pmRate 为属性pmRate设置的值
     */
    public void setPmRate(BigDecimal pmRate) {
        this.pmRate = pmRate;
    }

    @Column(name = "SECRET_RATE")
    public BigDecimal getSecretRate() {
        return secretRate;
    }

    public void setSecretRate(BigDecimal secretRate) {
        this.secretRate = secretRate;
    }

    /**
     * <p> 属性：permitId的Getter方法. </p>
     * 
     * @return 返回特批ID属性的值
     */
    @Column(name = "PERMIT_ID")
    public java.lang.String getPermitId() {
        return permitId;
    }

    /**
     * <p> 属性特批ID的Setter方法. </p>
     * 
     * @param permitId 为属性permitId设置的值
     */
    public void setPermitId(java.lang.String permitId) {
        this.permitId = permitId;
    }

    /**
     * <p> 属性：remark的Getter方法. </p>
     * 
     * @return 返回备注属性的值
     */
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    /**
     * <p> 属性备注的Setter方法. </p>
     * 
     * @param remark 为属性remark设置的值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p> 属性：creator的Getter方法. </p>
     * 
     * @return 返回登记人属性的值
     */
    @Column(name = "CREATOR")
    public java.lang.String getCreator() {
        return creator;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回登记时间属性的值
     */
    @Column(name = "CREATE_DATE")
    public java.util.Date getCreateDate() {
        return createDate;
    }

    /**
     * <p> 属性：modifier的Getter方法. </p>
     * 
     * @return 返回修改人属性的值
     */
    @Column(name = "MODIFIER")
    public java.lang.String getModifier() {
        return modifier;
    }

    /**
     * <p> 属性：modifyDate的Getter方法. </p>
     * 
     * @return 返回修改时间属性的值
     */
    @Column(name = "MODIFY_DATE")
    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    @Transient
    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        WfYieldSettle obj1 = (WfYieldSettle) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
