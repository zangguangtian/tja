package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcStandardPRICE.java
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

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>OcStandardPrice </p>
 * 
 * <p>描述：基准单价表 </p>
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
@Table(name = "OC_STANDARD_PRICE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcStandardPrice extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 3591781964009023113L;

    /** 属性：分类代码 */
    private java.lang.String categoryCode;

    /** 属性：类型代码 */
    private java.lang.String typeCode;

    /** 属性：类型名称 */
    private java.lang.String typeName;

    /** 属性：土建基准单价 */
    private BigDecimal unitPrice;

    /** 属性：备注 */
    private java.lang.String remark;

    private java.lang.String codes; //xxx-xxx

    private java.lang.String values; //xxx-xxx

    private java.lang.String keyValue;

    private java.lang.Integer pageIndex;

    private java.lang.Integer pageSize;

    /** 属性：序号 */
    private Integer number;

    /**
     * <p> 属性：categoryCode的Getter方法. </p>
     * 
     * @return 返回分类代码属性的值
     */
    @Column(name = "CATEGORY_CODE")
    public java.lang.String getCategoryCode() {
        return categoryCode;
    }

    /**
     * <p> 属性分类代码的Setter方法. </p>
     * 
     * @param categoryCode 为属性categoryCode设置的值
     */
    public void setCategoryCode(java.lang.String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * <p> 属性：typeCode的Getter方法. </p>
     * 
     * @return 返回类型代码属性的值
     */
    @Column(name = "TYPE_CODE")
    public java.lang.String getTypeCode() {
        return typeCode;
    }

    /**
     * <p> 属性类型代码的Setter方法. </p>
     * 
     * @param typeCode 为属性typeCode设置的值
     */
    public void setTypeCode(java.lang.String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * <p> 属性：typeName的Getter方法. </p>
     * 
     * @return 返回类型名称属性的值
     */
    @Column(name = "TYPE_NAME")
    public java.lang.String getTypeName() {
        return typeName;
    }

    /**
     * <p> 属性类型名称的Setter方法. </p>
     * 
     * @param typeName 为属性typeName设置的值
     */
    public void setTypeName(java.lang.String typeName) {
        this.typeName = typeName;
    }

    /**
     * <p> 属性：unitPrice的Getter方法. </p>
     * 
     * @return 返回土建基准单价属性的值
     */
    @Column(name = "UNIT_PRICE")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * <p> 属性土建基准单价的Setter方法. </p>
     * 
     * @param unitPrice 为属性unitPrice设置的值
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * <p> 属性：remark的Getter方法. </p>
     * 
     * @return 返回备注属性的值
     */
    @Column(name = "REMARK")
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p> 属性备注的Setter方法. </p>
     * 
     * @param remark 为属性remark设置的值
     */
    public void setRemark(java.lang.String remark) {
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
    public java.lang.String getCodes() {
        return codes;
    }

    public void setCodes(java.lang.String codes) {
        this.codes = codes;
    }

    @Transient
    public java.lang.String getValues() {
        return values;
    }

    public void setValues(java.lang.String values) {
        this.values = values;
    }

    @Transient
    public java.lang.String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(java.lang.String keyValue) {
        this.keyValue = keyValue;
    }

    @Transient
    public java.lang.Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(java.lang.Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Transient
    public java.lang.Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(java.lang.Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Transient
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        OcStandardPrice obj1 = (OcStandardPrice) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
