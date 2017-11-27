/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItProPhasesUser.java
 * 
 * 2017年10月10日-下午2:49:32
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.annotation.IgnoreWriteLog;
import com.df.framework.base.domain.BaseDomain;

/**
 * <p>ItProPhasesUser </p>
 * 
 * <p>描述：项目阶段参与人员表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月10日 12:06:54</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "IT_PRO_PHASES_USER")
@DynamicInsert(true)
@DynamicUpdate(true)
@IgnoreWriteLog(isIgnore = true)
public class ItProPhasesUser extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -7491382007445201522L;

    /** 属性：人员账号ID */
    private java.lang.String userId;

    /** 属性：人员名称 */
    private java.lang.String name;

    /** 属性：关联阶段ID */
    private java.lang.String prjPhaseId;

    /** 属性：关联子阶段ID */
    private java.lang.String subEntryId;

    /** 属性：关联项目ID */
    private java.lang.String itemId;

    /** 属性：专业代码 */
    private java.lang.String majorCode;

    /** 属性：专业名称 */
    private java.lang.String majorName;

    /** 属性：角色代码 */
    private java.lang.String roleKey;

    /** 属性：角色名称 */
    private java.lang.String roleName;

    /**
     * <p> 属性：userId的Getter方法. </p>
     * 
     * @return 返回人员账号ID属性的值
     */
    @Column(name = "USER_ID")
    public java.lang.String getUserId() {
        return userId;
    }

    /**
     * <p> 属性人员账号ID的Setter方法. </p>
     * 
     * @param userId 为属性userId设置的值
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    /**
     * <p> 属性：name的Getter方法. </p>
     * 
     * @return 返回人员名称属性的值
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return name;
    }

    /**
     * <p> 属性人员名称的Setter方法. </p>
     * 
     * @param name 为属性name设置的值
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * <p> 属性：prjPhaseId的Getter方法. </p>
     * 
     * @return 返回关联阶段ID属性的值
     */
    @Column(name = "PRJ_PHASE_ID")
    public java.lang.String getPrjPhaseId() {
        return prjPhaseId;
    }

    /**
     * <p> 属性关联阶段ID的Setter方法. </p>
     * 
     * @param prjPhaseId 为属性prjPhaseId设置的值
     */
    public void setPrjPhaseId(java.lang.String prjPhaseId) {
        this.prjPhaseId = prjPhaseId;
    }

    /**
     * <p> 属性：subEntryId的Getter方法. </p>
     * 
     * @return 返回关联子阶段ID属性的值
     */
    @Column(name = "SUB_ENTRY_ID")
    public java.lang.String getSubEntryId() {
        return subEntryId;
    }

    /**
     * <p> 属性关联子阶段ID的Setter方法. </p>
     * 
     * @param subEntryId 为属性subEntryId设置的值
     */
    public void setSubEntryId(java.lang.String subEntryId) {
        this.subEntryId = subEntryId;
    }

    /**
     * <p> 属性：itemId的Getter方法. </p>
     * 
     * @return 返回关联项目ID属性的值
     */
    @Column(name = "ITEM_ID")
    public java.lang.String getItemId() {
        return itemId;
    }

    /**
     * <p> 属性关联项目ID的Setter方法. </p>
     * 
     * @param itemId 为属性itemId设置的值
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
    }

    /**
     * <p> 属性：majorCode的Getter方法. </p>
     * 
     * @return 返回专业代码属性的值
     */
    @Column(name = "MAJOR_CODE")
    public java.lang.String getMajorCode() {
        return majorCode;
    }

    /**
     * <p> 属性专业代码的Setter方法. </p>
     * 
     * @param majorCode 为属性majorCode设置的值
     */
    public void setMajorCode(java.lang.String majorCode) {
        this.majorCode = majorCode;
    }

    /**
     * <p> 属性：majorName的Getter方法. </p>
     * 
     * @return 返回专业名称属性的值
     */
    @Column(name = "MAJOR_NAME")
    public java.lang.String getMajorName() {
        return majorName;
    }

    /**
     * <p> 属性专业名称的Setter方法. </p>
     * 
     * @param majorName 为属性majorName设置的值
     */
    public void setMajorName(java.lang.String majorName) {
        this.majorName = majorName;
    }

    /**
     * <p> 属性：roleKey的Getter方法. </p>
     * 
     * @return 返回角色代码属性的值
     */
    @Column(name = "ROLE_KEY")
    public java.lang.String getRoleKey() {
        return roleKey;
    }

    /**
     * <p> 属性角色代码的Setter方法. </p>
     * 
     * @param roleKey 为属性roleKey设置的值
     */
    public void setRoleKey(java.lang.String roleKey) {
        this.roleKey = roleKey;
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
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回同步时间属性的值
     */
    @Column(name = "CREATE_DATE")
    public java.util.Date getCreateDate() {
        return createDate;
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
        ItProPhasesUser obj1 = (ItProPhasesUser) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
