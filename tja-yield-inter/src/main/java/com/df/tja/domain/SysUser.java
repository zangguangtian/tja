package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysUSER.java
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
 * <p>SysUser </p>
 * 
 * <p>描述：系统用户表 </p>
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
@Table(name = "SYS_USER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUser extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -7084432801912181378L;

    /** 属性：工号。与HR_STAFF_TM.JOB_NUM一致 */
    private java.lang.String appid;

    /** 属性：用户名 */
    private java.lang.String username;

    /** 属性：姓名 */
    private java.lang.String realName;

    /** 属性：员工ID，冗余字段。与HR_STAFF_TM.ID关联 */
    private java.lang.String staffId;

    /** 属性： */
    private java.lang.String password;

    /** 属性：查询密码 */
    private java.lang.String queryPassword;

    /** 属性：状态。Y：激活；N：禁用 */
    private java.lang.String enabled;

    /** 属性：备注 */
    private java.lang.String remark;

    /** 属性：签名图片 */
    private java.lang.Object signImage;

    /** 属性：DB_AUTH,  NO_AUTH  ,IN_AUTH */
    private java.lang.String authenticationType;

    /** 属性：是否删除。1：是；0：否 */
    private Boolean delFlag;

    /**
     * <p> 属性：appid的Getter方法. </p>
     * 
     * @return 返回工号属性的值
     */
    @Column(name = "APPID")
    public java.lang.String getAppid() {
        return appid;
    }

    /**
     * <p> 属性工号的Setter方法. </p>
     * 
     * @param appid 为属性appid设置的值
     */
    public void setAppid(java.lang.String appid) {
        this.appid = appid;
    }

    /**
     * <p> 属性：username的Getter方法. </p>
     * 
     * @return 返回用户名属性的值
     */
    @Column(name = "USERNAME")
    public java.lang.String getUsername() {
        return username;
    }

    /**
     * <p> 属性用户名的Setter方法. </p>
     * 
     * @param username 为属性username设置的值
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    /**
     * <p> 属性：realName的Getter方法. </p>
     * 
     * @return 返回姓名属性的值
     */
    @Column(name = "REAL_NAME")
    public java.lang.String getRealName() {
        return realName;
    }

    /**
     * <p> 属性姓名的Setter方法. </p>
     * 
     * @param realName 为属性realName设置的值
     */
    public void setRealName(java.lang.String realName) {
        this.realName = realName;
    }

    /**
     * <p> 属性：staffId的Getter方法. </p>
     * 
     * @return 返回员工ID属性的值
     */
    @Column(name = "STAFF_ID")
    public java.lang.String getStaffId() {
        return staffId;
    }

    /**
     * <p> 属性员工ID的Setter方法. </p>
     * 
     * @param staffId 为属性staffId设置的值
     */
    public void setStaffId(java.lang.String staffId) {
        this.staffId = staffId;
    }

    /**
     * <p> 属性：password的Getter方法. </p>
     * 
     * @return 返回密码属性的值
     */
    @Column(name = "PASSWORD")
    public java.lang.String getPassword() {
        return password;
    }

    /**
     * <p> 属性密码的Setter方法. </p>
     * 
     * @param password 为属性password设置的值
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    /**
     * <p> 属性：queryPassword的Getter方法. </p>
     * 
     * @return 返回查询密码属性的值
     */
    @Column(name = "QUERY_PASSWORD")
    public java.lang.String getQueryPassword() {
        return queryPassword;
    }

    /**
     * <p> 属性查询密码的Setter方法. </p>
     * 
     * @param queryPassword 为属性queryPassword设置的值
     */
    public void setQueryPassword(java.lang.String queryPassword) {
        this.queryPassword = queryPassword;
    }

    /**
     * <p> 属性：enabled的Getter方法. </p>
     * 
     * @return 返回状态属性的值
     */
    @Column(name = "ENABLED")
    public java.lang.String getEnabled() {
        return enabled;
    }

    /**
     * <p> 属性状态的Setter方法. </p>
     * 
     * @param enabled 为属性enabled设置的值
     */
    public void setEnabled(java.lang.String enabled) {
        this.enabled = enabled;
    }

    /**
     * <p> 属性：remark的Getter方法. </p>
     * 
     * @return 返回备注属性的值
     */
    @Column(name = "REMARK")
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * <p> 属性备注的Setter方法. </p>
     * 
     * @param remark 为属性remark设置的值
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * <p> 属性：signImage的Getter方法. </p>
     * 
     * @return 返回签名图片属性的值
     */
    @Column(name = "SIGN_IMAGE")
    public java.lang.Object getSignImage() {
        return signImage;
    }

    /**
     * <p> 属性签名图片的Setter方法. </p>
     * 
     * @param signImage 为属性signImage设置的值
     */
    public void setSignImage(java.lang.Object signImage) {
        this.signImage = signImage;
    }

    /**
     * <p> 属性：authenticationType的Getter方法. </p>
     * 
     * @return 返回验证类型属性的值
     */
    @Column(name = "AUTHENTICATION_TYPE")
    public java.lang.String getAuthenticationType() {
        return authenticationType;
    }

    /**
     * <p> 属性验证类型的Setter方法. </p>
     * 
     * @param authenticationType 为属性authenticationType设置的值
     */
    public void setAuthenticationType(java.lang.String authenticationType) {
        this.authenticationType = authenticationType;
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
        SysUser obj1 = (SysUser) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}