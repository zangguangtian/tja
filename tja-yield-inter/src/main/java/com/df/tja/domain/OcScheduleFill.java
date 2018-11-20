/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain
 *
 * OcScheduleFill.java
 * 
 * 2018年11月20日-下午2:30:43
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.domain;

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
 * <p>OcScheduleFill </p>
 * 
 * <p>描述：项目进度填报表 </p>
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
@Table(name = "OC_SCHEDULE_FILL_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcScheduleFill extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 6308603432354506387L;

    /** 属性：项目ID。冗余字段 */
    private java.lang.String proId;

    /** 属性：进度主表ID。表示针对哪周进行填报 */
    private java.lang.String scheduleId;

    /** 属性：策划主表ID。冗余字段 */
    private java.lang.String schemeId;

    /** 属性：策划因子ID。表示针对谁进行填报 */
    private java.lang.String divisorId;

    /** 属性：上周进度 */
    private BigDecimal prevSchedule;

    /** 属性：本周进度 */
    private BigDecimal currSchedule;

    /** 属性：进度状态。参考系统配置表 */
    private java.lang.String scheduleStatus;

    /** 属性：备注 */
    private java.lang.String remark;

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
    * <p> 属性：scheduleId的Getter方法. </p>
    * 
    * @return 返回进度主表ID属性的值
    */
    @Column(name = "SCHEDULE_ID")
    public java.lang.String getScheduleId() {
        return scheduleId;
    }

    /**
    * <p> 属性进度主表ID的Setter方法. </p>
    * 
    * @param scheduleId 为属性scheduleId设置的值
    */
    public void setScheduleId(java.lang.String scheduleId) {
        this.scheduleId = scheduleId;
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
    * <p> 属性：divisorId的Getter方法. </p>
    * 
    * @return 返回策划因子ID属性的值
    */
    @Column(name = "DIVISOR_ID")
    public java.lang.String getDivisorId() {
        return divisorId;
    }

    /**
    * <p> 属性策划因子ID的Setter方法. </p>
    * 
    * @param divisorId 为属性divisorId设置的值
    */
    public void setDivisorId(java.lang.String divisorId) {
        this.divisorId = divisorId;
    }

    /**
    * <p> 属性：prevSchedule的Getter方法. </p>
    * 
    * @return 返回上周进度属性的值
    */
    @Column(name = "PREV_SCHEDULE")
    public BigDecimal getPrevSchedule() {
        return prevSchedule;
    }

    /**
    * <p> 属性上周进度的Setter方法. </p>
    * 
    * @param prevSchedule 为属性prevSchedule设置的值
    */
    public void setPrevSchedule(BigDecimal prevSchedule) {
        this.prevSchedule = prevSchedule;
    }

    /**
    * <p> 属性：currSchedule的Getter方法. </p>
    * 
    * @return 返回本周进度属性的值
    */
    @Column(name = "CURR_SCHEDULE")
    public BigDecimal getCurrSchedule() {
        return currSchedule;
    }

    /**
    * <p> 属性本周进度的Setter方法. </p>
    * 
    * @param currSchedule 为属性currSchedule设置的值
    */
    public void setCurrSchedule(BigDecimal currSchedule) {
        this.currSchedule = currSchedule;
    }

    /**
    * <p> 属性：scheduleStatus的Getter方法. </p>
    * 
    * @return 返回进度状态属性的值
    */
    @Column(name = "SCHEDULE_STATUS")
    public java.lang.String getScheduleStatus() {
        return scheduleStatus;
    }

    /**
    * <p> 属性进度状态的Setter方法. </p>
    * 
    * @param scheduleStatus 为属性scheduleStatus设置的值
    */
    public void setScheduleStatus(java.lang.String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
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
        OcScheduleFill obj1 = (OcScheduleFill) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}