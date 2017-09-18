package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfYieldMajorRoleALLOT.java
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
 * <p>WfYieldMajorRoleAllot </p>
 * 
 * <p>描述：年度产值专业角色人员分配表(包括特批的分配记录) </p>
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
@Table(name = "WF_YIELD_MAJOR_ROLE_ALLOT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfYieldMajorRoleAllot extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 1L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：流程ID */
    private java.lang.String wfId;

    /** 属性：分类。1000：非特批产值分配；2000：特批产值分配。 */
    private java.lang.String category;

    /** 属性：专业角色ID。WF_YIELD_MAJOR_ROLE_RATE.ID，当分类为2000时，为null */
    private java.lang.String majorRoleId;

    /** 属性：角色代码。参考系统配置表(冗余字段)，当分类为2000时，为null */
    private java.lang.String roleCode;

    /** 属性：专业代码。参考系统配置表 */
    private java.lang.String majorCode;

    /** 属性：人员ID */
    private java.lang.String staffId;

    /** 属性：排序号 */
    private Integer staffSort;

    /** 属性：工作量比例 */
    private Double staffRate;

    /** 属性：产值 */
    private Double staffYield;

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
     * <p> 属性：category的Getter方法. </p>
     * 
     * @return 返回分类属性的值
     */
    @Column(name = "CATEGORY")
    public java.lang.String getCategory() {
        return category;
    }

    /**
     * <p> 属性分类的Setter方法. </p>
     * 
     * @param category 为属性category设置的值
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }

    /**
     * <p> 属性：majorRoleId的Getter方法. </p>
     * 
     * @return 返回专业角色ID属性的值
     */
    @Column(name = "MAJOR_ROLE_ID")
    public java.lang.String getMajorRoleId() {
        return majorRoleId;
    }

    /**
     * <p> 属性专业角色ID的Setter方法. </p>
     * 
     * @param majorRoleId 为属性majorRoleId设置的值
     */
    public void setMajorRoleId(java.lang.String majorRoleId) {
        this.majorRoleId = majorRoleId;
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
     * <p> 属性：staffId的Getter方法. </p>
     * 
     * @return 返回人员ID属性的值
     */
    @Column(name = "STAFF_ID")
    public java.lang.String getStaffId() {
        return staffId;
    }

    /**
     * <p> 属性人员ID的Setter方法. </p>
     * 
     * @param staffId 为属性staffId设置的值
     */
    public void setStaffId(java.lang.String staffId) {
        this.staffId = staffId;
    }

    /**
     * <p> 属性：staffSort的Getter方法. </p>
     * 
     * @return 返回排序号属性的值
     */
    @Column(name = "STAFF_SORT")
    public Integer getStaffSort() {
        return staffSort;
    }

    /**
     * <p> 属性排序号的Setter方法. </p>
     * 
     * @param staffSort 为属性staffSort设置的值
     */
    public void setStaffSort(Integer staffSort) {
        this.staffSort = staffSort;
    }

    /**
     * <p> 属性：staffRate的Getter方法. </p>
     * 
     * @return 返回工作量比例属性的值
     */
    @Column(name = "STAFF_RATE")
    public Double getStaffRate() {
        return staffRate;
    }

    /**
     * <p> 属性工作量比例的Setter方法. </p>
     * 
     * @param staffRate 为属性staffRate设置的值
     */
    public void setStaffRate(Double staffRate) {
        this.staffRate = staffRate;
    }

    /**
     * <p> 属性：staffYield的Getter方法. </p>
     * 
     * @return 返回产值属性的值
     */
    @Column(name = "STAFF_YIELD")
    public Double getStaffYield() {
        return staffYield;
    }

    /**
     * <p> 属性产值的Setter方法. </p>
     * 
     * @param staffYield 为属性staffYield设置的值
     */
    public void setStaffYield(Double staffYield) {
        this.staffYield = staffYield;
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
        WfYieldMajorRoleAllot obj1 = (WfYieldMajorRoleAllot) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}