package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcPeriodAdvanceFILL.java
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
 * <p>OcPeriodAdvanceFill </p>
 * 
 * <p>描述：期间提前上报表 </p>
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
@Table(name = "OC_PERIOD_ADVANCE_FILL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcPeriodAdvanceFill extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -2574940836683916637L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：期间ID */
    private java.lang.String periodId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：备注 */
    private String remark;

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
        OcPeriodAdvanceFill obj1 = (OcPeriodAdvanceFill) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}