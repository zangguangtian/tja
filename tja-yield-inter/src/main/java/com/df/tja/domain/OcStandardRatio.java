package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcStandardRATIO.java
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
 * <p>OcStandardRatio </p>
 * 
 * <p>描述：基准专业比例表 </p>
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
@Table(name = "OC_STANDARD_RATIO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcStandardRatio extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -4387532603568735498L;

    /** 属性：基准ID */
    private java.lang.String stardandId;

    /** 属性：专业代码 */
    private java.lang.String majorCode;

    /** 属性：专业比例 */
    private BigDecimal majorRatio;

    /**
     * <p> 属性：stardandId的Getter方法. </p>
     * 
     * @return 返回基准ID属性的值
     */
    @Column(name = "STARDAND_ID")
    public java.lang.String getStardandId() {
        return stardandId;
    }

    /**
     * <p> 属性基准ID的Setter方法. </p>
     * 
     * @param stardandId 为属性stardandId设置的值
     */
    public void setStardandId(java.lang.String stardandId) {
        this.stardandId = stardandId;
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
     * <p> 属性：majorRatio的Getter方法. </p>
     * 
     * @return 返回专业比例属性的值
     */
    @Column(name = "MAJOR_RATIO")
    public BigDecimal getMajorRatio() {
        return majorRatio;
    }

    /**
     * <p> 属性专业比例的Setter方法. </p>
     * 
     * @param majorRatio 为属性majorRatio设置的值
     */
    public void setMajorRatio(BigDecimal majorRatio) {
        this.majorRatio = majorRatio;
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
        OcStandardRatio obj1 = (OcStandardRatio) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
