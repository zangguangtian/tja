package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfFlowMAIN.java
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
 * <p>WfFlowMain </p>
 * 
 * <p>描述：流程主表。所有流程的共同属性拆分到此表，此表的主键策略为手工赋值且与流程业务表具有相同的ID。 </p>
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
@Table(name = "WF_FLOW_MAIN")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfFlowMain extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -7728108676184072719L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：流水号 */
    private java.lang.String seqNo;

    /** 属性：流程说明 */
    private java.lang.String wfDesc;

    /** 属性：流程审核状态。0：未提交；1：审核中；2：审核通过；3：审核退回；4：申请人撤回；9：流程废弃； */
    private java.lang.String auditStatus;

    /** 属性：流程实例ID */
    private java.lang.String procId;

    /** 属性：流程实例KEY */
    private java.lang.String procKey;

    /** 属性：提交人 */
    private java.lang.String submiter;

    /** 属性：提交时间 */
    private java.util.Date submitDate;

    /** 属性：审核完成时间 */
    private java.util.Date completedDate;

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
     * <p> 属性：seqNo的Getter方法. </p>
     * 
     * @return 返回流水号属性的值
     */
    @Column(name = "SEQ_NO")
    public java.lang.String getSeqNo() {
        return seqNo;
    }

    /**
     * <p> 属性流水号的Setter方法. </p>
     * 
     * @param seqNo 为属性seqNo设置的值
     */
    public void setSeqNo(java.lang.String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * <p> 属性：wfDesc的Getter方法. </p>
     * 
     * @return 返回流程说明属性的值
     */
    @Column(name = "WF_DESC")
    public java.lang.String getWfDesc() {
        return wfDesc;
    }

    /**
     * <p> 属性流程说明的Setter方法. </p>
     * 
     * @param wfDesc 为属性wfDesc设置的值
     */
    public void setWfDesc(java.lang.String wfDesc) {
        this.wfDesc = wfDesc;
    }

    /**
     * <p> 属性：auditStatus的Getter方法. </p>
     * 
     * @return 返回审核状态属性的值
     */
    @Column(name = "AUDIT_STATUS")
    public java.lang.String getAuditStatus() {
        return auditStatus;
    }

    /**
     * <p> 属性审核状态的Setter方法. </p>
     * 
     * @param auditStatus 为属性auditStatus设置的值
     */
    public void setAuditStatus(java.lang.String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * <p> 属性：procId的Getter方法. </p>
     * 
     * @return 返回流程ID属性的值
     */
    @Column(name = "PROC_ID")
    public java.lang.String getProcId() {
        return procId;
    }

    /**
     * <p> 属性流程ID的Setter方法. </p>
     * 
     * @param procId 为属性procId设置的值
     */
    public void setProcId(java.lang.String procId) {
        this.procId = procId;
    }

    /**
     * <p> 属性：procKey的Getter方法. </p>
     * 
     * @return 返回流程KEY属性的值
     */
    @Column(name = "PROC_KEY")
    public java.lang.String getProcKey() {
        return procKey;
    }

    /**
     * <p> 属性流程KEY的Setter方法. </p>
     * 
     * @param procKey 为属性procKey设置的值
     */
    public void setProcKey(java.lang.String procKey) {
        this.procKey = procKey;
    }

    /**
     * <p> 属性：submiter的Getter方法. </p>
     * 
     * @return 返回提交人属性的值
     */
    @Column(name = "SUBMITER")
    public java.lang.String getSubmiter() {
        return submiter;
    }

    /**
     * <p> 属性提交人的Setter方法. </p>
     * 
     * @param submiter 为属性submiter设置的值
     */
    public void setSubmiter(java.lang.String submiter) {
        this.submiter = submiter;
    }

    /**
     * <p> 属性：submitDate的Getter方法. </p>
     * 
     * @return 返回提交时间属性的值
     */
    @Column(name = "SUBMIT_DATE")
    public java.util.Date getSubmitDate() {
        return submitDate;
    }

    /**
     * <p> 属性提交时间的Setter方法. </p>
     * 
     * @param submitDate 为属性submitDate设置的值
     */
    public void setSubmitDate(java.util.Date submitDate) {
        this.submitDate = submitDate;
    }

    /**
     * <p> 属性：completedDate的Getter方法. </p>
     * 
     * @return 返回审核完成时间属性的值
     */
    @Column(name = "COMPLETED_DATE")
    public java.util.Date getCompletedDate() {
        return completedDate;
    }

    /**
     * <p> 属性审核完成时间的Setter方法. </p>
     * 
     * @param completedDate 为属性completedDate设置的值
     */
    public void setCompletedDate(java.util.Date completedDate) {
        this.completedDate = completedDate;
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
        WfFlowMain obj1 = (WfFlowMain) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}