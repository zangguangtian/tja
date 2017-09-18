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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：分类代码 */
    private java.lang.String categoryCode;

    /** 属性：类型代码 */
    private java.lang.String typeCode;

    /** 属性：类型名称 */
    private java.lang.String typeName;

    /** 属性：土建基准单价 */
    private Double unitPrice;

    /** 属性：备注 */
    private java.lang.String remark;

    /** 属性：登记人 */
    private java.lang.String creator;

    /** 属性：登记时间 */
    private java.util.Date createDate;

    /** 属性：修改人 */
    private java.lang.String modifier;

    /** 属性：修改时间 */
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
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * <p> 属性土建基准单价的Setter方法. </p>
     * 
     * @param unitPrice 为属性unitPrice设置的值
     */
    public void setUnitPrice(Double unitPrice) {
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
        OcStandardPrice obj1 = (OcStandardPrice) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}