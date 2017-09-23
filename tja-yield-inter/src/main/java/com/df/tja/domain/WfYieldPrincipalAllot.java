package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfYieldPrincipalALLOT.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>WfYieldPrincipalAllot </p>
 * 
 * <p>描述：年度产值负责人项目经理人员分配表 </p>
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
@Table(name = "WF_YIELD_PRINCIPAL_ALLOT")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfYieldPrincipalAllot extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -3471598614767430920L;

    /** 属性：流程ID */
    private java.lang.String wfId;

    /** 属性：人员分类。参考项目经理、项目负责人角色代码 */
    private java.lang.String staffCategory;

    /** 属性：人员ID。员工表ID */
    private java.lang.String staffId;

    /** 属性：排序号 */
    private Integer staffSort;

    /** 属性：工作量比例 */
    private BigDecimal staffRate;

    /** 属性：产值 */
    private BigDecimal staffYield;

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
     * <p> 属性：staffCategory的Getter方法. </p>
     * 
     * @return 返回人员分类属性的值
     */
    @Column(name = "STAFF_CATEGORY")
    public java.lang.String getStaffCategory() {
        return staffCategory;
    }

    /**
     * <p> 属性人员分类的Setter方法. </p>
     * 
     * @param staffCategory 为属性staffCategory设置的值
     */
    public void setStaffCategory(java.lang.String staffCategory) {
        this.staffCategory = staffCategory;
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
    public BigDecimal getStaffRate() {
        return staffRate;
    }

    /**
     * <p> 属性工作量比例的Setter方法. </p>
     * 
     * @param staffRate 为属性staffRate设置的值
     */
    public void setStaffRate(BigDecimal staffRate) {
        this.staffRate = staffRate;
    }

    /**
     * <p> 属性：staffYield的Getter方法. </p>
     * 
     * @return 返回产值属性的值
     */
    @Column(name = "STAFF_YIELD")
    public BigDecimal getStaffYield() {
        return staffYield;
    }

    /**
     * <p> 属性产值的Setter方法. </p>
     * 
     * @param staffYield 为属性staffYield设置的值
     */
    public void setStaffYield(BigDecimal staffYield) {
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
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回登记时间属性的值
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
        WfYieldPrincipalAllot obj1 = (WfYieldPrincipalAllot) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
