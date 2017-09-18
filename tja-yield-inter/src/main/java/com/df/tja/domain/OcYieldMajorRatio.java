package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcYieldMajorRATIO.java
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
 * <p>OcYieldMajorRatio </p>
 * 
 * <p>描述：项目产值专业比例表 </p>
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
@Table(name = "OC_YIELD_MAJOR_RATIO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcYieldMajorRatio extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 4348519681025218835L;

    /** 属性：策划ID */
    private java.lang.String schemeId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：项目产值专业ID */
    private java.lang.String majorId;

    /** 属性：专业代码。参考系统配置表 */
    private java.lang.String majorCode;

    /** 属性：比例 */
    private BigDecimal majorRate;

    /** 属性：产值 */
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
     * <p> 属性：majorId的Getter方法. </p>
     * 
     * @return 返回项目产值专业ID属性的值
     */
    @Column(name = "MAJOR_ID")
    public java.lang.String getMajorId() {
        return majorId;
    }

    /**
     * <p> 属性项目产值专业ID的Setter方法. </p>
     * 
     * @param majorId 为属性majorId设置的值
     */
    public void setMajorId(java.lang.String majorId) {
        this.majorId = majorId;
    }

    /**
     * <p> 属性：majorCode的Getter方法. </p>
     * 
     * @return 返回专业代码属性的值
     */
    @Column(name = "MAJOR_CODE")
    public java.lang.String getMajorCode() {
        return majorCode;
    }

    /**
     * <p> 属性专业代码的Setter方法. </p>
     * 
     * @param majorCode 为属性majorCode设置的值
     */
    public void setMajorCode(java.lang.String majorCode) {
        this.majorCode = majorCode;
    }

    /**
     * <p> 属性：majorRate的Getter方法. </p>
     * 
     * @return 返回比例属性的值
     */
    @Column(name = "MAJOR_RATE")
    public BigDecimal getMajorRate() {
        return majorRate;
    }

    /**
     * <p> 属性比例的Setter方法. </p>
     * 
     * @param majorRate 为属性majorRate设置的值
     */
    public void setMajorRate(BigDecimal majorRate) {
        this.majorRate = majorRate;
    }

    /**
     * <p> 属性：majorYield的Getter方法. </p>
     * 
     * @return 返回产值属性的值
     */
    @Column(name = "MAJOR_YIELD")
    public BigDecimal getMajorYield() {
        return majorYield;
    }

    /**
     * <p> 属性产值的Setter方法. </p>
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
        OcYieldMajorRatio obj1 = (OcYieldMajorRatio) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}