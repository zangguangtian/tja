package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfUserAuditLangTM.java
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
 * <p>WfUserAuditLangTm </p>
 * 
 * <p>描述：个人审批语表 </p>
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
@Table(name = "WF_USER_AUDIT_LANG_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfUserAuditLangTm extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 8416690368283229446L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：用户ID。SYS_USER.ID */
    private java.lang.String userId;

    /** 属性：审批语描述 */
    private java.lang.String auditDesc;

    /** 属性：显示顺序。排序值越小越靠前 */
    private Integer sort;

    /** 属性：是否默认。1：是；0：否 */
    private Boolean defaultFlag;

    /** 属性：是否删除。1：是；0：否 */
    private Boolean delFlag;

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
     * <p> 属性：userId的Getter方法. </p>
     * 
     * @return 返回用户ID属性的值
     */
    @Column(name = "USER_ID")
    public java.lang.String getUserId() {
        return userId;
    }

    /**
     * <p> 属性用户ID的Setter方法. </p>
     * 
     * @param userId 为属性userId设置的值
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    /**
     * <p> 属性：auditDesc的Getter方法. </p>
     * 
     * @return 返回审批语描述属性的值
     */
    @Column(name = "AUDIT_DESC")
    public java.lang.String getAuditDesc() {
        return auditDesc;
    }

    /**
     * <p> 属性审批语描述的Setter方法. </p>
     * 
     * @param auditDesc 为属性auditDesc设置的值
     */
    public void setAuditDesc(java.lang.String auditDesc) {
        this.auditDesc = auditDesc;
    }

    /**
     * <p> 属性：sort的Getter方法. </p>
     * 
     * @return 返回显示顺序属性的值
     */
    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }

    /**
     * <p> 属性显示顺序的Setter方法. </p>
     * 
     * @param sort 为属性sort设置的值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * <p> 属性：defaultFlag的Getter方法. </p>
     * 
     * @return 返回是否默认属性的值
     */
    @Column(name = "DEFAULT_FLAG")
    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    /**
     * <p> 属性是否默认的Setter方法. </p>
     * 
     * @param defaultFlag 为属性defaultFlag设置的值
     */
    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
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
        WfUserAuditLangTm obj1 = (WfUserAuditLangTm) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}