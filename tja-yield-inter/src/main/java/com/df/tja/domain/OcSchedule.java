/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain
 *
 * OcSchedule.java
 * 
 * 2018年11月20日-下午2:27:01
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>OcSchedule </p>
 * 
 * <p>描述：项目进度主表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月20日 14:26:16</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "OC_SCHEDULE_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcSchedule extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -8322933630103815792L;

    /** 属性：项目ID。冗余字段 */
    private java.lang.String proId;

    /** 属性：策划主表ID。冗余字段 */
    private java.lang.String schemeId;

    /** 属性：周开始日期 */
    private java.util.Date weekStart;

    /** 属性：周结束日期 */
    private java.util.Date weekEnd;

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
    * <p> 属性：weekStart的Getter方法. </p>
    * 
    * @return 返回周开始日期属性的值
    */
    @Column(name = "WEEK_START")
    public java.util.Date getWeekStart() {
        return weekStart;
    }

    /**
    * <p> 属性周开始日期的Setter方法. </p>
    * 
    * @param weekStart 为属性weekStart设置的值
    */
    public void setWeekStart(java.util.Date weekStart) {
        this.weekStart = weekStart;
    }

    /**
    * <p> 属性：weekEnd的Getter方法. </p>
    * 
    * @return 返回周结束日期属性的值
    */
    @Column(name = "WEEK_END")
    public java.util.Date getWeekEnd() {
        return weekEnd;
    }

    /**
    * <p> 属性周结束日期的Setter方法. </p>
    * 
    * @param weekEnd 为属性weekEnd设置的值
    */
    public void setWeekEnd(java.util.Date weekEnd) {
        this.weekEnd = weekEnd;
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
        OcSchedule obj1 = (OcSchedule) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
