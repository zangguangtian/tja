package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysROLE.java
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
 * <p>SysRole </p>
 * 
 * <p>描述：系统资源表 </p>
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
@Table(name = "SYS_ROLE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRole extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -5969596747593603697L;

    /** 属性：角色代码 */
    private java.lang.String roleCode;

    /** 属性：角色名称 */
    private java.lang.String roleName;

    /** 属性：角色类型。1000：全部；2000：流程角色；3000：业务角色 */
    private java.lang.String roleType;

    /** 属性：排序号 */
    private Integer roleSort;

    /** 属性：角色父ID。备用字段，以防止出现角色继承的需求 */
    private java.lang.String parentId;

    /** 属性：备注 */
    private java.lang.String description;

    /** 属性：是否删除。1：是；0：否 */
    private Boolean delFlag;

    /**
     * <p> 属性：roleCode的Getter方法. </p>
     * 
     * @return 返回角色代码属性的值
     */
    @Column(name = "ROLE_CODE")
    public java.lang.String getRoleCode() {
        return roleCode;
    }

    /**
     * <p> 属性角色代码的Setter方法. </p>
     * 
     * @param roleCode 为属性roleCode设置的值
     */
    public void setRoleCode(java.lang.String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * <p> 属性：roleName的Getter方法. </p>
     * 
     * @return 返回角色名称属性的值
     */
    @Column(name = "ROLE_NAME")
    public java.lang.String getRoleName() {
        return roleName;
    }

    /**
     * <p> 属性角色名称的Setter方法. </p>
     * 
     * @param roleName 为属性roleName设置的值
     */
    public void setRoleName(java.lang.String roleName) {
        this.roleName = roleName;
    }

    /**
     * <p> 属性：roleType的Getter方法. </p>
     * 
     * @return 返回角色类型属性的值
     */
    @Column(name = "ROLE_TYPE")
    public java.lang.String getRoleType() {
        return roleType;
    }

    /**
     * <p> 属性角色类型的Setter方法. </p>
     * 
     * @param roleType 为属性roleType设置的值
     */
    public void setRoleType(java.lang.String roleType) {
        this.roleType = roleType;
    }

    /**
     * <p> 属性：roleSort的Getter方法. </p>
     * 
     * @return 返回排序号属性的值
     */
    @Column(name = "ROLE_SORT")
    public Integer getRoleSort() {
        return roleSort;
    }

    /**
     * <p> 属性排序号的Setter方法. </p>
     * 
     * @param roleSort 为属性roleSort设置的值
     */
    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    /**
     * <p> 属性：parentId的Getter方法. </p>
     * 
     * @return 返回角色父ID属性的值
     */
    @Column(name = "PARENT_ID")
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * <p> 属性角色父ID的Setter方法. </p>
     * 
     * @param parentId 为属性parentId设置的值
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p> 属性：description的Getter方法. </p>
     * 
     * @return 返回备注属性的值
     */
    @Column(name = "DESCRIPTION")
    public java.lang.String getDescription() {
        return description;
    }

    /**
     * <p> 属性备注的Setter方法. </p>
     * 
     * @param description 为属性description设置的值
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
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
        SysRole obj1 = (SysRole) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}