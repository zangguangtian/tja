package com.df.tja.domain.cust;

import java.math.BigDecimal;

import com.df.framework.base.domain.SuperDomain;

/**
 * 
 * <p>OcSchemeStageMajor</p>
 * 
 * <p>描述：专业策划的任务信息</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月12日 上午11:08:15</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 *
 */
public class OcSchemeMajorTask extends SuperDomain {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 2093278580624799478L;

    /** 属性：策划ID*/
    private String schemeId;

    /** 属性：项目ID*/
    private String proId;

    /** 属性：专业ID*/
    private String majorId;

    /** 属性：子项ID*/
    private String subId;

    /** 属性：子项名称*/
    private String subName;

    /** 属性：子项比例*/
    private BigDecimal subRatio;

    /** 属性：子项下任务数量*/
    private Integer subChildCount;

    /** 属性：子项下任务及人员数量*/
    private Integer subTaskCount;

    /** 属性：任务ID*/
    private String taskId;

    /** 属性：任务名称*/
    private String taskName;

    /** 属性：任务比例*/
    private BigDecimal taskRatio;

    /** 属性：任务下用户数量*/
    private Integer taskUserCount;

    /** 属性：用户ID */
    private String userId;

    /** 属性：用户角色代码*/
    private String userRoleCode;

    /** 属性：用户角色名称*/
    private String userRoleName;

    /** 属性：员工ID*/
    private String staffId;

    /** 属性：员工名称*/
    private String staffName;

    /** 属性：机构名称*/
    private String orgName;

    /** 属性：用户比例*/
    private BigDecimal userRatio;

    /** 属性：备注 */
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
     * <p> 属性proId的Getter方法. </p>
     * 
     * @return 返回proId属性的值
     */
    public String getProId() {
        return proId;
    }

    /**
     * <p> 属性proId的Setter方法. </p>
     * 
     * @param proId 为属性proId设置的值
     */
    public void setProId(String proId) {
        this.proId = proId;
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
     * <p> 属性subRatio的Getter方法. </p>
     * 
     * @return 返回subRatio属性的值
     */
    public BigDecimal getSubRatio() {
        return subRatio;
    }

    /**
     * <p> 属性subRatio的Setter方法. </p>
     * 
     * @param subRatio 为属性subRatio设置的值
     */
    public void setSubRatio(BigDecimal subRatio) {
        this.subRatio = subRatio;
    }

    /**
     * <p> 属性subChildCount的Getter方法. </p>
     * 
     * @return 返回subChildCount属性的值
     */
    public Integer getSubChildCount() {
        return subChildCount;
    }

    /**
     * <p> 属性subChildCount的Setter方法. </p>
     * 
     * @param subChildCount 为属性subChildCount设置的值
     */
    public void setSubChildCount(Integer subChildCount) {
        this.subChildCount = subChildCount;
    }

    /**
     * <p> 属性subTaskCount的Getter方法. </p>
     * 
     * @return 返回subTaskCount属性的值
     */
    public Integer getSubTaskCount() {
        return subTaskCount;
    }

    /**
     * <p> 属性subTaskCount的Setter方法. </p>
     * 
     * @param subTaskCount 为属性subTaskCount设置的值
     */
    public void setSubTaskCount(Integer subTaskCount) {
        this.subTaskCount = subTaskCount;
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
     * <p> 属性taskRatio的Getter方法. </p>
     * 
     * @return 返回taskRatio属性的值
     */
    public BigDecimal getTaskRatio() {
        return taskRatio;
    }

    /**
     * <p> 属性taskRatio的Setter方法. </p>
     * 
     * @param taskRatio 为属性taskRatio设置的值
     */
    public void setTaskRatio(BigDecimal taskRatio) {
        this.taskRatio = taskRatio;
    }

    /**
     * <p> 属性taskUserCount的Getter方法. </p>
     * 
     * @return 返回taskUserCount属性的值
     */
    public Integer getTaskUserCount() {
        return taskUserCount;
    }

    /**
     * <p> 属性taskUserCount的Setter方法. </p>
     * 
     * @param taskUserCount 为属性taskUserCount设置的值
     */
    public void setTaskUserCount(Integer taskUserCount) {
        this.taskUserCount = taskUserCount;
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
     * <p> 属性userRoleCode的Getter方法. </p>
     * 
     * @return 返回userRoleCode属性的值
     */
    public String getUserRoleCode() {
        return userRoleCode;
    }

    /**
     * <p> 属性userRoleCode的Setter方法. </p>
     * 
     * @param userRoleCode 为属性userRoleCode设置的值
     */
    public void setUserRoleCode(String userRoleCode) {
        this.userRoleCode = userRoleCode;
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
     * <p> 属性userRatio的Getter方法. </p>
     * 
     * @return 返回userRatio属性的值
     */
    public BigDecimal getUserRatio() {
        return userRatio;
    }

    /**
     * <p> 属性userRatio的Setter方法. </p>
     * 
     * @param userRatio 为属性userRatio设置的值
     */
    public void setUserRatio(BigDecimal userRatio) {
        this.userRatio = userRatio;
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
