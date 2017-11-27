/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItDeptLeader.java
 * 
 * 2017年10月17日-上午9:39:44
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
 * <p>ItDeptLeader </p>
 * 
 * <p>描述：部门负责人接口表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月17日 9:39:15</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "IT_DEPT_LEADER")
@DynamicInsert(true)
@DynamicUpdate(true)
@IgnoreWriteLog(isIgnore = true)
public class ItDeptLeader extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -8819172670850507920L;

    /** 属性：账号ID */
    private java.lang.String accountId;

    /** 属性：账号 */
    private java.lang.String accountName;

    /** 属性：工号 */
    private java.lang.String workNo;

    /** 属性：姓名 */
    private java.lang.String userName;

    /** 属性：部门ID */
    private java.lang.String deptId;

    /**
     * <p> 属性：accountId的Getter方法. </p>
     * 
     * @return 返回账号ID属性的值
     */
    @Column(name = "ACCOUNT_ID")
    public java.lang.String getAccountId() {
        return accountId;
    }

    /**
     * <p> 属性账号ID的Setter方法. </p>
     * 
     * @param accountId 为属性accountId设置的值
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }

    /**
     * <p> 属性：accountName的Getter方法. </p>
     * 
     * @return 返回账号属性的值
     */
    @Column(name = "ACCOUNT_NAME")
    public java.lang.String getAccountName() {
        return accountName;
    }

    /**
     * <p> 属性账号的Setter方法. </p>
     * 
     * @param accountName 为属性accountName设置的值
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }

    /**
     * <p> 属性：workNo的Getter方法. </p>
     * 
     * @return 返回工号属性的值
     */
    @Column(name = "WORK_NO")
    public java.lang.String getWorkNo() {
        return workNo;
    }

    /**
     * <p> 属性工号的Setter方法. </p>
     * 
     * @param workNo 为属性workNo设置的值
     */
    public void setWorkNo(java.lang.String workNo) {
        this.workNo = workNo;
    }

    /**
     * <p> 属性：userName的Getter方法. </p>
     * 
     * @return 返回姓名属性的值
     */
    @Column(name = "USER_NAME")
    public java.lang.String getUserName() {
        return userName;
    }

    /**
     * <p> 属性姓名的Setter方法. </p>
     * 
     * @param userName 为属性userName设置的值
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    /**
     * <p> 属性：deptId的Getter方法. </p>
     * 
     * @return 返回部门ID属性的值
     */
    @Column(name = "DEPT_ID")
    public java.lang.String getDeptId() {
        return deptId;
    }

    /**
     * <p> 属性部门ID的Setter方法. </p>
     * 
     * @param deptId 为属性deptId设置的值
     */
    public void setDeptId(java.lang.String deptId) {
        this.deptId = deptId;
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
        ItDeptLeader obj1 = (ItDeptLeader) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
