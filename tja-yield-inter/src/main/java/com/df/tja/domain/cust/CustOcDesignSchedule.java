/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * CustOcDesignSchedule.java
 * 
 * 2018年11月20日-下午2:36:53
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.domain.cust;

import java.math.BigDecimal;

/**
 * <p>CustOcDesignSchedule</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月20日 下午2:36:53</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public class CustOcDesignSchedule {

    /** 属性：策划ID*/
    private String schemeId;
    
    /** 属性：专业ID*/
    private String majorId;
    
    /** 属性：专业名称*/
    private String majorName;

    /** 属性：子项ID*/
    private String subId;

    /** 属性：子项名称*/
    private String subName;

    /** 属性：任务ID*/
    private String taskId;

    /** 属性：任务名称*/
    private String taskName;

    /** 属性：用户因子ID*/
    private String userId;

    /** 属性：用户角色名*/
    private String userRoleName;

    /** 属性：员工ID*/
    private String staffId;

    /** 属性：员工ID*/
    private String staffName;

    /** 属性：组织名称*/
    private String orgName;

    /** 属性：上周进度*/
    private BigDecimal preSchedule;

    /** 属性：本周进度*/
    private BigDecimal currSchedule;

    /** 属性：进度状态*/
    private String scheduleStatus;

    /** 属性：备注*/
    private String remark;

    /**
     * <p> 属性schemeId的Getter方法. </p>
     * 
     * @return 返回schemeId属性的值
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * <p> 属性schemeId的Setter方法. </p>
     * 
     * @param schemeId 为属性schemeId设置的值
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * <p> 属性majorId的Getter方法. </p>
     * 
     * @return 返回majorId属性的值
     */
    public String getMajorId() {
        return majorId;
    }

    /**
     * <p> 属性majorId的Setter方法. </p>
     * 
     * @param majorId 为属性majorId设置的值
     */
    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    /**
     * <p> 属性majorName的Getter方法. </p>
     * 
     * @return 返回majorName属性的值
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * <p> 属性majorName的Setter方法. </p>
     * 
     * @param majorName 为属性majorName设置的值
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    /**
     * <p> 属性subId的Getter方法. </p>
     * 
     * @return 返回subId属性的值
     */
    public String getSubId() {
        return subId;
    }

    /**
     * <p> 属性subId的Setter方法. </p>
     * 
     * @param subId 为属性subId设置的值
     */
    public void setSubId(String subId) {
        this.subId = subId;
    }

    /**
     * <p> 属性subName的Getter方法. </p>
     * 
     * @return 返回subName属性的值
     */
    public String getSubName() {
        return subName;
    }

    /**
     * <p> 属性subName的Setter方法. </p>
     * 
     * @param subName 为属性subName设置的值
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * <p> 属性taskId的Getter方法. </p>
     * 
     * @return 返回taskId属性的值
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * <p> 属性taskId的Setter方法. </p>
     * 
     * @param taskId 为属性taskId设置的值
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * <p> 属性taskName的Getter方法. </p>
     * 
     * @return 返回taskName属性的值
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * <p> 属性taskName的Setter方法. </p>
     * 
     * @param taskName 为属性taskName设置的值
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * <p> 属性userId的Getter方法. </p>
     * 
     * @return 返回userId属性的值
     */
    public String getUserId() {
        return userId;
    }

    /**
     * <p> 属性userId的Setter方法. </p>
     * 
     * @param userId 为属性userId设置的值
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * <p> 属性userRoleName的Getter方法. </p>
     * 
     * @return 返回userRoleName属性的值
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * <p> 属性userRoleName的Setter方法. </p>
     * 
     * @param userRoleName 为属性userRoleName设置的值
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    /**
     * <p> 属性staffId的Getter方法. </p>
     * 
     * @return 返回staffId属性的值
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * <p> 属性staffId的Setter方法. </p>
     * 
     * @param staffId 为属性staffId设置的值
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * <p> 属性staffName的Getter方法. </p>
     * 
     * @return 返回staffName属性的值
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * <p> 属性staffName的Setter方法. </p>
     * 
     * @param staffName 为属性staffName设置的值
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * <p> 属性orgName的Getter方法. </p>
     * 
     * @return 返回orgName属性的值
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * <p> 属性orgName的Setter方法. </p>
     * 
     * @param orgName 为属性orgName设置的值
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * <p> 属性preSchedule的Getter方法. </p>
     * 
     * @return 返回preSchedule属性的值
     */
    public BigDecimal getPreSchedule() {
        return preSchedule;
    }

    /**
     * <p> 属性preSchedule的Setter方法. </p>
     * 
     * @param preSchedule 为属性preSchedule设置的值
     */
    public void setPreSchedule(BigDecimal preSchedule) {
        this.preSchedule = preSchedule;
    }

    /**
     * <p> 属性currSchedule的Getter方法. </p>
     * 
     * @return 返回currSchedule属性的值
     */
    public BigDecimal getCurrSchedule() {
        return currSchedule;
    }

    /**
     * <p> 属性currSchedule的Setter方法. </p>
     * 
     * @param currSchedule 为属性currSchedule设置的值
     */
    public void setCurrSchedule(BigDecimal currSchedule) {
        this.currSchedule = currSchedule;
    }

    /**
     * <p> 属性scheduleStatus的Getter方法. </p>
     * 
     * @return 返回scheduleStatus属性的值
     */
    public String getScheduleStatus() {
        return scheduleStatus;
    }

    /**
     * <p> 属性scheduleStatus的Setter方法. </p>
     * 
     * @param scheduleStatus 为属性scheduleStatus设置的值
     */
    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    /**
     * <p> 属性remark的Getter方法. </p>
     * 
     * @return 返回remark属性的值
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p> 属性remark的Setter方法. </p>
     * 
     * @param remark 为属性remark设置的值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
