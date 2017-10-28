package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcYieldMAJOR.java
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
 * <p>OcYieldMajor </p>
 * 
 * <p>描述：施工图产值专业表 </p>
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
@Table(name = "OC_YIELD_MAJOR")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcYieldMajor extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -8772919869689696046L;

    /** 属性：策划ID */
    private java.lang.String schemeId;

    /** 属性：类型名称 */
    private java.lang.String name;

    /** 属性：基准单价ID */
    private java.lang.String priceId;

    /** 属性：建筑面积 */
    private BigDecimal buildArea;

    /** 属性：土建基准单价 */
    private BigDecimal standardPrice;

    /** 属性：土建基准产值 */
    private BigDecimal standardYield;

    /** 属性：各专业产值 */
    private BigDecimal majorYield;

    /**
     * <p> 属性：schemeId的Getter方法. </p>
     * 
     * @return 返回策划ID属性的值
     */
    @Column(name = "SCHEME_ID")
    public java.lang.String getSchemeId() {
        return schemeId;
    }

    /**
     * <p> 属性策划ID的Setter方法. </p>
     * 
     * @param schemeId 为属性schemeId设置的值
     */
    public void setSchemeId(java.lang.String schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * <p> 属性：name的Getter方法. </p>
     * 
     * @return 返回类型名称属性的值
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return name;
    }

    /**
     * <p> 属性类型名称的Setter方法. </p>
     * 
     * @param name 为属性name设置的值
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * <p> 属性：priceId的Getter方法. </p>
     * 
     * @return 返回基准单价ID属性的值
     */
    @Column(name = "PRICE_ID")
    public java.lang.String getPriceId() {
        return priceId;
    }

    /**
     * <p> 属性基准单价ID的Setter方法. </p>
     * 
     * @param priceId 为属性priceId设置的值
     */
    public void setPriceId(java.lang.String priceId) {
        this.priceId = priceId;
    }

    /**
     * <p> 属性：buildArea的Getter方法. </p>
     * 
     * @return 返回建筑面积属性的值
     */
    @Column(name = "BUILD_AREA")
    public BigDecimal getBuildArea() {
        return buildArea;
    }

    /**
     * <p> 属性建筑面积的Setter方法. </p>
     * 
     * @param buildArea 为属性buildArea设置的值
     */
    public void setBuildArea(BigDecimal buildArea) {
        this.buildArea = buildArea;
    }

    /**
     * <p> 属性：standardPrice的Getter方法. </p>
     * 
     * @return 返回土建基准单价属性的值
     */
    @Column(name = "STANDARD_PRICE")
    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    /**
     * <p> 属性土建基准单价的Setter方法. </p>
     * 
     * @param standardPrice 为属性standardPrice设置的值
     */
    public void setStandardPrice(BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    /**
     * <p> 属性：standardYield的Getter方法. </p>
     * 
     * @return 返回土建基准产值属性的值
     */
    @Column(name = "STANDARD_YIELD")
    public BigDecimal getStandardYield() {
        return standardYield;
    }

    /**
     * <p> 属性土建基准产值的Setter方法. </p>
     * 
     * @param standardYield 为属性standardYield设置的值
     */
    public void setStandardYield(BigDecimal standardYield) {
        this.standardYield = standardYield;
    }

    /**
     * <p> 属性：majorYield的Getter方法. </p>
     * 
     * @return 返回各专业产值属性的值
     */
    @Column(name = "MAJOR_YIELD")
    public BigDecimal getMajorYield() {
        return majorYield;
    }

    /**
     * <p> 属性各专业产值的Setter方法. </p>
     * 
     * @param majorYield 为属性majorYield设置的值
     */
    public void setMajorYield(BigDecimal majorYield) {
        this.majorYield = majorYield;
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
        OcYieldMajor obj1 = (OcYieldMajor) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
