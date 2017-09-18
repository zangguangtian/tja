package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfFlowManageTM.java
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
 * <p>WfFlowManageTm </p>
 * 
 * <p>描述：流程管理表(每个流程有且仅有一条记录) </p>
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
@Table(name = "WF_FLOW_MANAGE_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfFlowManageTm extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -1907715089251825107L;

    /** 属性：流程分组代码。 */
    private java.lang.String groupCode;

    /** 属性：业务流程KEY，主要用于流程委托；一个业务流程KEY可以对应多个流程定义KEY，如付款申请，支出凭单等； */
    private java.lang.String bizKey;

    /** 属性：流程定义KEY */
    private java.lang.String wfKey;

    /** 属性：流程定义名称 */
    private java.lang.String wfName;

    /** 属性：流程主表对应的实体类名 */
    private java.lang.String entityClass;

    /** 属性：访问类名。流程运行时审批访问地址反射的类名 */
    private java.lang.String wfClass;

    /** 属性：访问方法名。流程运行时审批访问地址反射的方法名 */
    private java.lang.String wfMethod;

    /** 属性：完成类名。流程审批通过调用的反射类名 */
    private java.lang.String completeClass;

    /** 属性：完成方法名。流程审批通过调用的反射方法名 */
    private java.lang.String completeMethod;

    /** 属性：流程编辑URL。 */
    private java.lang.String editUrl;

    /** 属性：流程编辑URL后面的TYPE */
    private java.lang.String editType;

    /** 属性：是否多视图。1：是；0：否；一个流程分节点填写表单数据的情况才称多视图 */
    private Boolean multFlag;

    /** 属性：是否与项目相关。1：是；0：否。如果流程节点中有需要项目合伙人参与，则认为是与项目相关 */
    private Boolean proFlag;

    /** 属性：排序号。 */
    private Integer sort;

    /** 属性：是否删除。1：是；0：否 */
    private Boolean delFlag;

    /** 属性：是否抄送发起人。1：是；0：否 */
    private Boolean ccFlag;

    /**
     * <p> 属性：groupCode的Getter方法. </p>
     * 
     * @return 返回流程分组代码属性的值
     */
    @Column(name = "GROUP_CODE")
    public java.lang.String getGroupCode() {
        return groupCode;
    }

    /**
     * <p> 属性流程分组代码的Setter方法. </p>
     * 
     * @param groupCode 为属性groupCode设置的值
     */
    public void setGroupCode(java.lang.String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * <p> 属性：bizKey的Getter方法. </p>
     * 
     * @return 返回业务流程KEY属性的值
     */
    @Column(name = "BIZ_KEY")
    public java.lang.String getBizKey() {
        return bizKey;
    }

    /**
     * <p> 属性业务流程KEY的Setter方法. </p>
     * 
     * @param bizKey 为属性bizKey设置的值
     */
    public void setBizKey(java.lang.String bizKey) {
        this.bizKey = bizKey;
    }

    /**
     * <p> 属性：wfKey的Getter方法. </p>
     * 
     * @return 返回流程定义KEY属性的值
     */
    @Column(name = "WF_KEY")
    public java.lang.String getWfKey() {
        return wfKey;
    }

    /**
     * <p> 属性流程定义KEY的Setter方法. </p>
     * 
     * @param wfKey 为属性wfKey设置的值
     */
    public void setWfKey(java.lang.String wfKey) {
        this.wfKey = wfKey;
    }

    /**
     * <p> 属性：wfName的Getter方法. </p>
     * 
     * @return 返回流程定义名称属性的值
     */
    @Column(name = "WF_NAME")
    public java.lang.String getWfName() {
        return wfName;
    }

    /**
     * <p> 属性流程定义名称的Setter方法. </p>
     * 
     * @param wfName 为属性wfName设置的值
     */
    public void setWfName(java.lang.String wfName) {
        this.wfName = wfName;
    }

    /**
     * <p> 属性：entityClass的Getter方法. </p>
     * 
     * @return 返回流程实体类名属性的值
     */
    @Column(name = "ENTITY_CLASS")
    public java.lang.String getEntityClass() {
        return entityClass;
    }

    /**
     * <p> 属性流程实体类名的Setter方法. </p>
     * 
     * @param entityClass 为属性entityClass设置的值
     */
    public void setEntityClass(java.lang.String entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * <p> 属性：wfClass的Getter方法. </p>
     * 
     * @return 返回访问类名属性的值
     */
    @Column(name = "WF_CLASS")
    public java.lang.String getWfClass() {
        return wfClass;
    }

    /**
     * <p> 属性访问类名的Setter方法. </p>
     * 
     * @param wfClass 为属性wfClass设置的值
     */
    public void setWfClass(java.lang.String wfClass) {
        this.wfClass = wfClass;
    }

    /**
     * <p> 属性：wfMethod的Getter方法. </p>
     * 
     * @return 返回访问方法名属性的值
     */
    @Column(name = "WF_METHOD")
    public java.lang.String getWfMethod() {
        return wfMethod;
    }

    /**
     * <p> 属性访问方法名的Setter方法. </p>
     * 
     * @param wfMethod 为属性wfMethod设置的值
     */
    public void setWfMethod(java.lang.String wfMethod) {
        this.wfMethod = wfMethod;
    }

    /**
     * <p> 属性：completeClass的Getter方法. </p>
     * 
     * @return 返回完成类名属性的值
     */
    @Column(name = "COMPLETE_CLASS")
    public java.lang.String getCompleteClass() {
        return completeClass;
    }

    /**
     * <p> 属性完成类名的Setter方法. </p>
     * 
     * @param completeClass 为属性completeClass设置的值
     */
    public void setCompleteClass(java.lang.String completeClass) {
        this.completeClass = completeClass;
    }

    /**
     * <p> 属性：completeMethod的Getter方法. </p>
     * 
     * @return 返回完成方法名属性的值
     */
    @Column(name = "COMPLETE_METHOD")
    public java.lang.String getCompleteMethod() {
        return completeMethod;
    }

    /**
     * <p> 属性完成方法名的Setter方法. </p>
     * 
     * @param completeMethod 为属性completeMethod设置的值
     */
    public void setCompleteMethod(java.lang.String completeMethod) {
        this.completeMethod = completeMethod;
    }

    /**
     * <p> 属性：editUrl的Getter方法. </p>
     * 
     * @return 返回流程编辑URL属性的值
     */
    @Column(name = "EDIT_URL")
    public java.lang.String getEditUrl() {
        return editUrl;
    }

    /**
     * <p> 属性流程编辑URL的Setter方法. </p>
     * 
     * @param editUrl 为属性editUrl设置的值
     */
    public void setEditUrl(java.lang.String editUrl) {
        this.editUrl = editUrl;
    }

    /**
     * <p> 属性：editType的Getter方法. </p>
     * 
     * @return 返回流程编辑URL分类属性的值
     */
    @Column(name = "EDIT_TYPE")
    public java.lang.String getEditType() {
        return editType;
    }

    /**
     * <p> 属性流程编辑URL分类的Setter方法. </p>
     * 
     * @param editType 为属性editType设置的值
     */
    public void setEditType(java.lang.String editType) {
        this.editType = editType;
    }

    /**
     * <p> 属性：multFlag的Getter方法. </p>
     * 
     * @return 返回是否多视图属性的值
     */
    @Column(name = "MULT_FLAG")
    public Boolean getMultFlag() {
        return multFlag;
    }

    /**
     * <p> 属性是否多视图的Setter方法. </p>
     * 
     * @param multFlag 为属性multFlag设置的值
     */
    public void setMultFlag(Boolean multFlag) {
        this.multFlag = multFlag;
    }

    /**
     * <p> 属性：proFlag的Getter方法. </p>
     * 
     * @return 返回是否与项目相关属性的值
     */
    @Column(name = "PRO_FLAG")
    public Boolean getProFlag() {
        return proFlag;
    }

    /**
     * <p> 属性是否与项目相关的Setter方法. </p>
     * 
     * @param proFlag 为属性proFlag设置的值
     */
    public void setProFlag(Boolean proFlag) {
        this.proFlag = proFlag;
    }

    /**
     * <p> 属性：sort的Getter方法. </p>
     * 
     * @return 返回排序号属性的值
     */
    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }

    /**
     * <p> 属性排序号的Setter方法. </p>
     * 
     * @param sort 为属性sort设置的值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * <p> 属性：delFlag的Getter方法. </p>
     * 
     * @return 返回是否删除属性的值
     */
    @Column(name = "DEL_FLAG")
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * <p> 属性是否删除的Setter方法. </p>
     * 
     * @param delFlag 为属性delFlag设置的值
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * <p> 属性：ccFlag的Getter方法. </p>
     * 
     * @return 返回是否抄送发起人属性的值
     */
    @Column(name = "CC_FLAG")
    public Boolean getCcFlag() {
        return ccFlag;
    }

    /**
     * <p> 属性是否抄送发起人的Setter方法. </p>
     * 
     * @param ccFlag 为属性ccFlag设置的值
     */
    public void setCcFlag(Boolean ccFlag) {
        this.ccFlag = ccFlag;
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
        WfFlowManageTm obj1 = (WfFlowManageTm) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}