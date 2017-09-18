package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* ItStaffINFO.java
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
 * <p>ItStaffInfo </p>
 * 
 * <p>描述：人员信息接口表 </p>
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
@Table(name = "IT_STAFF_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ItStaffInfo extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 7579630312712464532L;

    /** 属性：账号 */
    private java.lang.String workNo;

    /** 属性：姓名 */
    private java.lang.String name;

    /** 属性：姓名拼音 */
    private java.lang.String pinYin;

    /** 属性：部门ID */
    private java.lang.String deptId;

    /** 属性：入职时间 */
    private java.util.Date entryDate;

    /** 属性：在职状态。F：在职；T：离职 */
    private java.lang.String status;

    /** 属性：电子邮箱 */
    private java.lang.String email;

    /** 属性：账号ID */
    private java.lang.String accountId;

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
     * <p> 属性：name的Getter方法. </p>
     * 
     * @return 返回姓名属性的值
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return name;
    }

    /**
     * <p> 属性姓名的Setter方法. </p>
     * 
     * @param name 为属性name设置的值
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * <p> 属性：pinYin的Getter方法. </p>
     * 
     * @return 返回姓名拼音属性的值
     */
    @Column(name = "PIN_YIN")
    public java.lang.String getPinYin() {
        return pinYin;
    }

    /**
     * <p> 属性姓名拼音的Setter方法. </p>
     * 
     * @param pinYin 为属性pinYin设置的值
     */
    public void setPinYin(java.lang.String pinYin) {
        this.pinYin = pinYin;
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

    /**
     * <p> 属性：entryDate的Getter方法. </p>
     * 
     * @return 返回入职时间属性的值
     */
    @Column(name = "ENTRY_DATE")
    public java.util.Date getEntryDate() {
        return entryDate;
    }

    /**
     * <p> 属性入职时间的Setter方法. </p>
     * 
     * @param entryDate 为属性entryDate设置的值
     */
    public void setEntryDate(java.util.Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * <p> 属性：status的Getter方法. </p>
     * 
     * @return 返回在职状态属性的值
     */
    @Column(name = "STATUS")
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * <p> 属性在职状态的Setter方法. </p>
     * 
     * @param status 为属性status设置的值
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    /**
     * <p> 属性：email的Getter方法. </p>
     * 
     * @return 返回电子邮箱属性的值
     */
    @Column(name = "EMAIL")
    public java.lang.String getEmail() {
        return email;
    }

    /**
     * <p> 属性电子邮箱的Setter方法. </p>
     * 
     * @param email 为属性email设置的值
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

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
        ItStaffInfo obj1 = (ItStaffInfo) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}