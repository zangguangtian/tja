package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfYieldMajorRoleRATE.java
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
 * <p>WfYieldMajorRoleRate </p>
 * 
 * <p>描述：年度产值专业角色结算比例表 </p>
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
@Table(name = "WF_YIELD_MAJOR_ROLE_RATE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfYieldMajorRoleRate extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 1L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：流程ID */
    private java.lang.String wfId;

    /** 属性：专业代码。参考系统配置表 */
    private java.lang.String majorCode;

    /** 属性：角色代码。参考系统配置表 */
    private java.lang.String roleCode;

    /** 属性：角色排序号 */
    private Integer roleSort;

    /** 属性：比例 */
    private Double allotRate;

    /** 属性：专业结算比例ID。WF_YIELD_MAJOR_RATE.ID */
    private java.lang.String majorRateId;

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
     * <p> 属性：wfId的Getter方法. </p>
     * 
     * @return 返回流程ID属性的值
     */
    @Column(name = "WF_ID")
    public java.lang.String getWfId() {
        return wfId;
    }

    /**
     * <p> 属性流程ID的Setter方法. </p>
     * 
     * @param wfId 为属性wfId设置的值
     */
    public void setWfId(java.lang.String wfId) {
        this.wfId = wfId;
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
     * <p> 属性：roleSort的Getter方法. </p>
     * 
     * @return 返回角色排序号属性的值
     */
    @Column(name = "ROLE_SORT")
    public Integer getRoleSort() {
        return roleSort;
    }

    /**
     * <p> 属性角色排序号的Setter方法. </p>
     * 
     * @param roleSort 为属性roleSort设置的值
     */
    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    /**
     * <p> 属性：allotRate的Getter方法. </p>
     * 
     * @return 返回比例属性的值
     */
    @Column(name = "ALLOT_RATE")
    public Double getAllotRate() {
        return allotRate;
    }

    /**
     * <p> 属性比例的Setter方法. </p>
     * 
     * @param allotRate 为属性allotRate设置的值
     */
    public void setAllotRate(Double allotRate) {
        this.allotRate = allotRate;
    }

    /**
     * <p> 属性：majorRateId的Getter方法. </p>
     * 
     * @return 返回专业结算比例ID属性的值
     */
    @Column(name = "MAJOR_RATE_ID")
    public java.lang.String getMajorRateId() {
        return majorRateId;
    }

    /**
     * <p> 属性专业结算比例ID的Setter方法. </p>
     * 
     * @param majorRateId 为属性majorRateId设置的值
     */
    public void setMajorRateId(java.lang.String majorRateId) {
        this.majorRateId = majorRateId;
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
        WfYieldMajorRoleRate obj1 = (WfYieldMajorRoleRate) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}