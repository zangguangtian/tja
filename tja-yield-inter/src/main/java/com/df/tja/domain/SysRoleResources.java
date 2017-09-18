package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysRoleRESOURCES.java
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
 * <p>SysRoleResources </p>
 * 
 * <p>描述：系统角色资源表 </p>
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
@Table(name = "SYS_ROLE_RESOURCES")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRoleResources extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -5305605430541615179L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：角色ID */
    private java.lang.String roleId;

    /** 属性：资源ID */
    private java.lang.String resId;

    /** 属性：登记人 */
    private java.lang.String creator;

    /** 属性：登记时间 */
    private java.util.Date createDate;

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
     * <p> 属性：roleId的Getter方法. </p>
     * 
     * @return 返回角色ID属性的值
     */
    @Column(name = "ROLE_ID")
    public java.lang.String getRoleId() {
        return roleId;
    }

    /**
     * <p> 属性角色ID的Setter方法. </p>
     * 
     * @param roleId 为属性roleId设置的值
     */
    public void setRoleId(java.lang.String roleId) {
        this.roleId = roleId;
    }

    /**
     * <p> 属性：resId的Getter方法. </p>
     * 
     * @return 返回资源ID属性的值
     */
    @Column(name = "RES_ID")
    public java.lang.String getResId() {
        return resId;
    }

    /**
     * <p> 属性资源ID的Setter方法. </p>
     * 
     * @param resId 为属性resId设置的值
     */
    public void setResId(java.lang.String resId) {
        this.resId = resId;
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
        SysRoleResources obj1 = (SysRoleResources) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}