package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfYearMonthFILL.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>WfYearMonthFill </p>
 * 
 * <p>描述：1项目年月报上报流程表。 </p>
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
@Table(name = "WF_YEAR_MONTH_FILL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfYearMonthFill extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -2818248388935908933L;

    /** 属性：分类。1000：月报专业进度；2000：年报专业进度； */
    private java.lang.String pgCategory;

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

    /** 属性：项目所处状态 */
    private java.lang.String itemStatus;

    /** 属性：施工进度描述 */
    private String progressExplain;

    /** 属性：备注 */
    private String remark;

    /**
     * <p> 属性：pgCategory的Getter方法. </p>
     * 
     * @return 返回分类属性的值
     */
    @Column(name = "PG_CATEGORY")
    public java.lang.String getPgCategory() {
        return pgCategory;
    }

    /**
     * <p> 属性分类的Setter方法. </p>
     * 
     * @param pgCategory 为属性pgCategory设置的值
     */
    public void setPgCategory(java.lang.String pgCategory) {
        this.pgCategory = pgCategory;
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
     * <p> 属性：progressExplain的Getter方法. </p>
     * 
     * @return 返回施工进度描述属性的值
     */
    @Column(name = "PROGRESS_EXPLAIN")
    public String getProgressExplain() {
        return progressExplain;
    }

    /**
     * <p> 属性施工进度描述的Setter方法. </p>
     * 
     * @param progressExplain 为属性progressExplain设置的值
     */
    public void setProgressExplain(String progressExplain) {
        this.progressExplain = progressExplain;
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
        WfYearMonthFill obj1 = (WfYearMonthFill) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
