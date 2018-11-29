package com.df.tja.domain.cust;

import com.df.framework.base.domain.SuperDomain;
import org.apache.fop.fonts.base14.ZapfDingbats;

import java.math.BigDecimal;

/**
 * <p>OcCurrweekSchedule </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-27 17:07</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public class OcCurrweekSchedule extends SuperDomain {

    /** 属性：serialVersionUID */
    private static final long serialVersionUID = 2640394568019457338L;

    /** 属性：项目ID */
    private String proId;

    /** 属性：项目名称 */
    private String proName;

    /** 属性：阶段 */
    private String schemeStageName;

    /** 属性：专业 */
    private String schemeMajorName;

    /** 属性：子项 */
    private String subName;

    /** 属性：任务 */
    private String taskName;

    /** 属性：项目角色 */
    private String configName;

    /** 属性：姓名 */
    private String staffName;

    /** 属性：任职部门 */
    private String orgName;

    /** 属性：上周进度 */
    private BigDecimal prevSchedule;

    /** 属性：本周进度 */
    private BigDecimal currSchedule;

    /** 属性：本周占比 */
    private BigDecimal weekShare;

    /** 属性：进度状态 */
    private String scheduleStatus;

    /** 属性：分项合并数 */
    private Integer mergeCount;

    /** 属性：阶段合并数 */
    private Integer stageCount;

    /** 属性：专业合并 */
    private Integer majorCount;

    public Integer getMajorCount() {
        return majorCount;
    }

    public void setMajorCount(Integer majorCount) {
        this.majorCount = majorCount;
    }

    public Integer getStageCount() {
        return stageCount;
    }

    public void setStageCount(Integer stageCount) {
        this.stageCount = stageCount;
    }

    public String getSchemeStageName() {
        return schemeStageName;
    }

    public void setSchemeStageName(String schemeStageName) {
        this.schemeStageName = schemeStageName;
    }

    public String getSchemeMajorName() {
        return schemeMajorName;
    }

    public void setSchemeMajorName(String schemeMajorName) {
        this.schemeMajorName = schemeMajorName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getMergeCount() {
        return mergeCount;
    }

    public void setMergeCount(Integer mergeCount) {
        this.mergeCount = mergeCount;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public BigDecimal getPrevSchedule() {
        return prevSchedule;
    }

    public void setPrevSchedule(BigDecimal prevSchedule) {
        this.prevSchedule = prevSchedule;
    }

    public BigDecimal getCurrSchedule() {
        return currSchedule;
    }

    public void setCurrSchedule(BigDecimal currSchedule) {
        this.currSchedule = currSchedule;
    }

    public BigDecimal getWeekShare() {
        return weekShare;
    }

    public void setWeekShare(BigDecimal weekShare) {
        this.weekShare = weekShare;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
