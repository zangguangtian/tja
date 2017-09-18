package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcYieldSCHEME.java
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
 * <p>OcYieldScheme </p>
 * 
 * <p>描述：施工图产值策划表 </p>
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
@Table(name = "OC_YIELD_SCHEME")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcYieldScheme extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 2017044264418217129L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：策划编号 */
    private java.lang.String schemeNo;

    /** 属性：项目ID */
    private java.lang.String itemId;

    /** 属性：用地面积 */
    private BigDecimal landArea;

    /** 属性：策划依据 */
    private java.lang.String schemeBasis;

    /** 属性：实际合同额 */
    private BigDecimal contractAmount;

    /** 属性：分包扣减 */
    private BigDecimal pkgAmount;

    /** 属性：方案扣减 */
    private BigDecimal schemeAmount;

    /** 属性：其他扣减 */
    private BigDecimal rebateAmount;

    /** 属性：土建总产值 */
    private BigDecimal totalAmount;

    /** 属性：各专业产值 */
    private BigDecimal majorAmount;

    /** 属性：项目负责人产值比例 */
    private BigDecimal principalRate;

    /** 属性：项目负责人产值 */
    private BigDecimal principalYield;

    /** 属性：项目经理产值比例 */
    private BigDecimal pmRate;

    /** 属性：项目经理产值 */
    private BigDecimal pmYield;

    /** 属性：设计负责人。记录人员信息ID */
    private java.lang.String principalId;

    /** 属性：备注 */
    private String remark;

    /** 属性：登记人 */
    private java.lang.String creator;

    /** 属性：登记时间 */
    private java.util.Date createDate;

    /** 属性：修改人 */
    private java.lang.String modifier;

    /** 属性：修改时间（更新日期） */
    private java.util.Date modifyDate;

    /**
     * <p> 属性：id的Getter方法. </p>
     * 
     * @return 返回主键ID属性的值
     */
    @Column(name = "ID")
    public java.lang.String getId() {
        return id;
    }

    /**
     * <p> 属性主键ID的Setter方法. </p>
     * 
     * @param id 为属性id设置的值
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * <p> 属性：schemeNo的Getter方法. </p>
     * 
     * @return 返回策划编号属性的值
     */
    @Column(name = "SCHEME_NO")
    public java.lang.String getSchemeNo() {
        return schemeNo;
    }

    /**
     * <p> 属性策划编号的Setter方法. </p>
     * 
     * @param schemeNo 为属性schemeNo设置的值
     */
    public void setSchemeNo(java.lang.String schemeNo) {
        this.schemeNo = schemeNo;
    }

    /**
     * <p> 属性：itemId的Getter方法. </p>
     * 
     * @return 返回项目ID属性的值
     */
    @Column(name = "ITEM_ID")
    public java.lang.String getItemId() {
        return itemId;
    }

    /**
     * <p> 属性项目ID的Setter方法. </p>
     * 
     * @param itemId 为属性itemId设置的值
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
    }

    /**
     * <p> 属性：landArea的Getter方法. </p>
     * 
     * @return 返回用地面积属性的值
     */
    @Column(name = "LAND_AREA")
    public BigDecimal getLandArea() {
        return landArea;
    }

    /**
     * <p> 属性用地面积的Setter方法. </p>
     * 
     * @param landArea 为属性landArea设置的值
     */
    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    /**
     * <p> 属性：schemeBasis的Getter方法. </p>
     * 
     * @return 返回策划依据属性的值
     */
    @Column(name = "SCHEME_BASIS")
    public java.lang.String getSchemeBasis() {
        return schemeBasis;
    }

    /**
     * <p> 属性策划依据的Setter方法. </p>
     * 
     * @param schemeBasis 为属性schemeBasis设置的值
     */
    public void setSchemeBasis(java.lang.String schemeBasis) {
        this.schemeBasis = schemeBasis;
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
     * <p> 属性：totalAmount的Getter方法. </p>
     * 
     * @return 返回土建总产值属性的值
     */
    @Column(name = "TOTAL_AMOUNT")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * <p> 属性土建总产值的Setter方法. </p>
     * 
     * @param totalAmount 为属性totalAmount设置的值
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * <p> 属性：majorAmount的Getter方法. </p>
     * 
     * @return 返回各专业产值属性的值
     */
    @Column(name = "MAJOR_AMOUNT")
    public BigDecimal getMajorAmount() {
        return majorAmount;
    }

    /**
     * <p> 属性各专业产值的Setter方法. </p>
     * 
     * @param majorAmount 为属性majorAmount设置的值
     */
    public void setMajorAmount(BigDecimal majorAmount) {
        this.majorAmount = majorAmount;
    }

    /**
     * <p> 属性：principalRate的Getter方法. </p>
     * 
     * @return 返回项目负责人产值比例属性的值
     */
    @Column(name = "PRINCIPAL_RATE")
    public BigDecimal getPrincipalRate() {
        return principalRate;
    }

    /**
     * <p> 属性项目负责人产值比例的Setter方法. </p>
     * 
     * @param principalRate 为属性principalRate设置的值
     */
    public void setPrincipalRate(BigDecimal principalRate) {
        this.principalRate = principalRate;
    }

    /**
     * <p> 属性：principalYield的Getter方法. </p>
     * 
     * @return 返回项目负责人产值属性的值
     */
    @Column(name = "PRINCIPAL_YIELD")
    public BigDecimal getPrincipalYield() {
        return principalYield;
    }

    /**
     * <p> 属性项目负责人产值的Setter方法. </p>
     * 
     * @param principalYield 为属性principalYield设置的值
     */
    public void setPrincipalYield(BigDecimal principalYield) {
        this.principalYield = principalYield;
    }

    /**
     * <p> 属性：pmRate的Getter方法. </p>
     * 
     * @return 返回项目经理产值比例属性的值
     */
    @Column(name = "PM_RATE")
    public BigDecimal getPmRate() {
        return pmRate;
    }

    /**
     * <p> 属性项目经理产值比例的Setter方法. </p>
     * 
     * @param pmRate 为属性pmRate设置的值
     */
    public void setPmRate(BigDecimal pmRate) {
        this.pmRate = pmRate;
    }

    /**
     * <p> 属性：pmYield的Getter方法. </p>
     * 
     * @return 返回项目经理产值属性的值
     */
    @Column(name = "PM_YIELD")
    public BigDecimal getPmYield() {
        return pmYield;
    }

    /**
     * <p> 属性项目经理产值的Setter方法. </p>
     * 
     * @param pmYield 为属性pmYield设置的值
     */
    public void setPmYield(BigDecimal pmYield) {
        this.pmYield = pmYield;
    }

    /**
     * <p> 属性：principalId的Getter方法. </p>
     * 
     * @return 返回设计负责人属性的值
     */
    @Column(name = "PRINCIPAL_ID")
    public java.lang.String getPrincipalId() {
        return principalId;
    }

    /**
     * <p> 属性设计负责人的Setter方法. </p>
     * 
     * @param principalId 为属性principalId设置的值
     */
    public void setPrincipalId(java.lang.String principalId) {
        this.principalId = principalId;
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
     * <p> 属性登记人的Setter方法. </p>
     * 
     * @param creator 为属性creator设置的值
     */
    public void setCreator(java.lang.String creator) {
        this.creator = creator;
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
     * <p> 属性登记时间的Setter方法. </p>
     * 
     * @param createDate 为属性createDate设置的值
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
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
     * <p> 属性修改人的Setter方法. </p>
     * 
     * @param modifier 为属性modifier设置的值
     */
    public void setModifier(java.lang.String modifier) {
        this.modifier = modifier;
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

    /**
     * <p> 属性修改时间的Setter方法. </p>
     * 
     * @param modifyDate 为属性modifyDate设置的值
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
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
        OcYieldScheme obj1 = (OcYieldScheme) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}