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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.df.framework.base.domain.SuperDomain;

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
public class ItProPhasesUser extends SuperDomain {
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

    /** 属性：专业 */
    private java.lang.String major;

    /** 属性：角色 */
    private java.lang.String role;

    /**
     * <p> 属性：id的Getter方法. </p>
     * 
     * @return 返回主键ID属性的值
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "ID", unique = true, nullable = false)
    public java.lang.String getId() {
        return id;
    }

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
     * <p> 属性itemId的Getter方法. </p>
     * 
     * @return 返回itemId属性的值
     */
    @Column(name = "ITEM_ID")
    public java.lang.String getItemId() {
        return itemId;
    }

    /**
     * <p> 属性itemId的Setter方法. </p>
     * 
     * @param itemId 为属性itemId设置的值
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
    }

    /**
     * <p> 属性：major的Getter方法. </p>
     * 
     * @return 返回专业属性的值
     */
    @Column(name = "MAJOR")
    public java.lang.String getMajor() {
        return major;
    }

    /**
     * <p> 属性专业的Setter方法. </p>
     * 
     * @param major 为属性major设置的值
     */
    public void setMajor(java.lang.String major) {
        this.major = major;
    }

    /**
     * <p> 属性：role的Getter方法. </p>
     * 
     * @return 返回角色属性的值
     */
    @Column(name = "ROLE")
    public java.lang.String getRole() {
        return role;
    }

    /**
     * <p> 属性角色的Setter方法. </p>
     * 
     * @param role 为属性role设置的值
     */
    public void setRole(java.lang.String role) {
        this.role = role;
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
