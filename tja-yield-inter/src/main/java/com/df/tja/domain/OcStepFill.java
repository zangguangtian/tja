/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain
 *
 * OcStepFill.java
 * 
 * 2018年11月20日-下午2:29:01
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
 * <p>OcStepFill </p>
 * 
 * <p>描述：项目进展填报表 </p>
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
@Table(name = "OC_STEP_FILL_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcStepFill extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -1827087338700483212L;

    /** 属性：项目ID。冗余字段 */
    private java.lang.String proId;

    /** 属性：进度主表ID。表示针对哪周进行填报 */
    private java.lang.String scheduleId;

    /** 属性：策划主表ID。冗余字段 */
    private java.lang.String schemeId;

    /** 属性：策划因子ID。表示针对谁进行填报 */
    private java.lang.String divisorId;

    /** 属性：因子状态。参考系统配置表（主要指分项、专业状态） */
    private java.lang.String divisorStatus;

    /** 属性：进展状态。参考系统配置表（主要为正常、超前、滞后） */
    private java.lang.String stepStatus;

    /** 属性：工作内容及进展情况 */
    private java.lang.String workContent;

    /** 属性：下阶段工作计划 */
    private java.lang.String workPlan;

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
    * <p> 属性：divisorStatus的Getter方法. </p>
    * 
    * @return 返回因子状态属性的值
    */
    @Column(name = "DIVISOR_STATUS")
    public java.lang.String getDivisorStatus() {
        return divisorStatus;
    }

    /**
    * <p> 属性因子状态的Setter方法. </p>
    * 
    * @param divisorStatus 为属性divisorStatus设置的值
    */
    public void setDivisorStatus(java.lang.String divisorStatus) {
        this.divisorStatus = divisorStatus;
    }

    /**
    * <p> 属性：stepStatus的Getter方法. </p>
    * 
    * @return 返回进展状态属性的值
    */
    @Column(name = "STEP_STATUS")
    public java.lang.String getStepStatus() {
        return stepStatus;
    }

    /**
    * <p> 属性进展状态的Setter方法. </p>
    * 
    * @param stepStatus 为属性stepStatus设置的值
    */
    public void setStepStatus(java.lang.String stepStatus) {
        this.stepStatus = stepStatus;
    }

    /**
    * <p> 属性：workContent的Getter方法. </p>
    * 
    * @return 返回工作内容及进展情况属性的值
    */
    @Column(name = "WORK_CONTENT")
    public java.lang.String getWorkContent() {
        return workContent;
    }

    /**
    * <p> 属性工作内容及进展情况的Setter方法. </p>
    * 
    * @param workContent 为属性workContent设置的值
    */
    public void setWorkContent(java.lang.String workContent) {
        this.workContent = workContent;
    }

    /**
    * <p> 属性：workPlan的Getter方法. </p>
    * 
    * @return 返回下阶段工作计划属性的值
    */
    @Column(name = "WORK_PLAN")
    public java.lang.String getWorkPlan() {
        return workPlan;
    }

    /**
    * <p> 属性下阶段工作计划的Setter方法. </p>
    * 
    * @param workPlan 为属性workPlan设置的值
    */
    public void setWorkPlan(java.lang.String workPlan) {
        this.workPlan = workPlan;
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
        OcStepFill obj1 = (OcStepFill) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}