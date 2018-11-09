package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcYieldMajorDUTY.java
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
 * <p>OcYieldMajorDuty </p>
 * 
 * <p>描述：施工图产值专业责任人表 </p>
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
@Table(name = "OC_YIELD_MAJOR_DUTY")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcYieldMajorDuty extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -7789593741118585697L;

    /** 属性：策划ID */
    private java.lang.String schemeId;

    /** 属性：专业代码。参考系统配置表 */
    private java.lang.String majorCode;

    /** 属性：专业扣减 */
    private BigDecimal minusYield;

    /** 属性：专业产值 */
    private BigDecimal majorYield;

    /** 属性：专业负责人ID */
    private java.lang.String principalId;

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
     * <p> 属性minusYield的Getter方法. </p>
     * 
     * @return 返回minusYield属性的值
     */
    @Column(name = "MINUS_YIELD")
    public BigDecimal getMinusYield() {
        return minusYield;
    }

    /**
     * <p> 属性minusYield的Setter方法. </p>
     * 
     * @param minusYield 为属性minusYield设置的值
     */
    public void setMinusYield(BigDecimal minusYield) {
        this.minusYield = minusYield;
    }

    /**
     * <p> 属性：majorYield的Getter方法. </p>
     * 
     * @return 返回专业产值属性的值
     */
    @Column(name = "MAJOR_YIELD")
    public BigDecimal getMajorYield() {
        return majorYield;
    }

    /**
     * <p> 属性专业产值的Setter方法. </p>
     * 
     * @param majorYield 为属性majorYield设置的值
     */
    public void setMajorYield(BigDecimal majorYield) {
        this.majorYield = majorYield;
    }

    /**
     * <p> 属性：principalId的Getter方法. </p>
     * 
     * @return 返回专业负责人属性的值
     */
    @Column(name = "PRINCIPAL_ID")
    public java.lang.String getPrincipalId() {
        return principalId;
    }

    /**
     * <p> 属性专业负责人的Setter方法. </p>
     * 
     * @param principalId 为属性principalId设置的值
     */
    public void setPrincipalId(java.lang.String principalId) {
        this.principalId = principalId;
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
        OcYieldMajorDuty obj1 = (OcYieldMajorDuty) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
