package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcYieldStageMAJOR.java
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
 * <p>OcYieldStageMajor </p>
 * 
 * <p>描述：施工图产值阶段专业表 </p>
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
@Table(name = "OC_YIELD_STAGE_MAJOR")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcYieldStageMajor extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -1110767655956864262L;

    /** 属性：策划ID */
    private java.lang.String schemeId;

    /** 属性：分为。1000：比例；2000：产值 */
    private java.lang.String category;

    /** 属性：专业代码。参考系统配置表 */
    private java.lang.String majorCode;

    /** 属性：初设阶段产值/比例 */
    private BigDecimal preliminary;

    /** 属性：施工图阶段产值/比例 */
    private BigDecimal drawing;

    /** 属性：小计产值/比例 */
    private BigDecimal subTotal;

    /** 属性：施工配合产值/比例 */
    private BigDecimal coordination;

    /** 属性：施工配合-封顶产值/比例 */
    private BigDecimal cap;

    /** 属性：产值产值/比例 */
    private BigDecimal check;

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
     * <p> 属性：category的Getter方法. </p>
     * 
     * @return 返回分类属性的值
     */
    @Column(name = "CATEGORY")
    public java.lang.String getCategory() {
        return category;
    }

    /**
     * <p> 属性分类的Setter方法. </p>
     * 
     * @param category 为属性category设置的值
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
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
     * <p> 属性：preliminary的Getter方法. </p>
     * 
     * @return 返回初设阶段属性的值
     */
    @Column(name = "PRELIMINARY")
    public BigDecimal getPreliminary() {
        return preliminary;
    }

    /**
     * <p> 属性初设阶段的Setter方法. </p>
     * 
     * @param preliminary 为属性preliminary设置的值
     */
    public void setPreliminary(BigDecimal preliminary) {
        this.preliminary = preliminary;
    }

    /**
     * <p> 属性：drawing的Getter方法. </p>
     * 
     * @return 返回施工图阶段属性的值
     */
    @Column(name = "DRAWING")
    public BigDecimal getDrawing() {
        return drawing;
    }

    /**
     * <p> 属性施工图阶段的Setter方法. </p>
     * 
     * @param drawing 为属性drawing设置的值
     */
    public void setDrawing(BigDecimal drawing) {
        this.drawing = drawing;
    }

    /**
     * <p> 属性：subTotal的Getter方法. </p>
     * 
     * @return 返回小计属性的值
     */
    @Column(name = "SUB_TOTAL")
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * <p> 属性小计的Setter方法. </p>
     * 
     * @param subTotal 为属性subTotal设置的值
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * <p> 属性：coordination的Getter方法. </p>
     * 
     * @return 返回施工配合属性的值
     */
    @Column(name = "COORDINATION")
    public BigDecimal getCoordination() {
        return coordination;
    }

    /**
     * <p> 属性施工配合的Setter方法. </p>
     * 
     * @param coordination 为属性coordination设置的值
     */
    public void setCoordination(BigDecimal coordination) {
        this.coordination = coordination;
    }

    /**
     * <p> 属性：cap的Getter方法. </p>
     * 
     * @return 返回施工配合-封顶属性的值
     */
    @Column(name = "CAP")
    public BigDecimal getCap() {
        return cap;
    }

    /**
     * <p> 属性施工配合-封顶的Setter方法. </p>
     * 
     * @param cap 为属性cap设置的值
     */
    public void setCap(BigDecimal cap) {
        this.cap = cap;
    }

    /**
     * <p> 属性：check的Getter方法. </p>
     * 
     * @return 返回施工配合-验收属性的值
     */
    @Column(name = "CHECK")
    public BigDecimal getCheck() {
        return check;
    }

    /**
     * <p> 属性施工配合-验收的Setter方法. </p>
     * 
     * @param check 为属性check设置的值
     */
    public void setCheck(BigDecimal check) {
        this.check = check;
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
        OcYieldStageMajor obj1 = (OcYieldStageMajor) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
