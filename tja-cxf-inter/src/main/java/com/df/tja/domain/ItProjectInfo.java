/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItProjectInfo.java
 * 
 * 2017年10月10日-下午2:20:06
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain;

import java.math.BigDecimal;

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

import com.df.framework.annotation.IgnoreWriteLog;
import com.df.framework.base.domain.SuperDomain;

/**
 * <p>ItProjectInfo </p>
 * 
 * <p>描述：项目信息表 </p>
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
@Table(name = "IT_PROJECT_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
@IgnoreWriteLog(isIgnore = true)
public class ItProjectInfo extends SuperDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 7159721120604890480L;

    /** 属性：项目编号 */
    private java.lang.String itemCode;

    /** 属性：项目名称 */
    private java.lang.String itemName;

    /** 属性：项目类型。如果获取的为名称，那么需要转为代码 */
    private java.lang.String itemStyle;

    /** 属性：负责部门ID */
    private java.lang.String dutyDeptId;

    /** 属性：负责部门名称 */
    private java.lang.String dutyDeptName;

    /** 属性：项目负责人ID */
    private java.lang.String principalId;

    /** 属性：项目负责人名称 */
    private java.lang.String principalName;

    /** 属性：项目经理ID */
    private java.lang.String prjmanagerId;

    /** 属性：项目经理名称 */
    private java.lang.String prjmanagerName;

    /** 属性：项目工时成本 */
    private BigDecimal workCost;

    /** 属性：工时成本同步时间 */
    private java.util.Date wcCreateDate;

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
     * <p> 属性主键ID的Setter方法. </p>
     * 
     * @param id 为属性id设置的值
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * <p> 属性：itemCode的Getter方法. </p>
     * 
     * @return 返回项目编号属性的值
     */
    @Column(name = "ITEM_CODE")
    public java.lang.String getItemCode() {
        return itemCode;
    }

    /**
     * <p> 属性项目编号的Setter方法. </p>
     * 
     * @param itemCode 为属性itemCode设置的值
     */
    public void setItemCode(java.lang.String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * <p> 属性：itemName的Getter方法. </p>
     * 
     * @return 返回项目名称属性的值
     */
    @Column(name = "ITEM_NAME")
    public java.lang.String getItemName() {
        return itemName;
    }

    /**
     * <p> 属性项目名称的Setter方法. </p>
     * 
     * @param itemName 为属性itemName设置的值
     */
    public void setItemName(java.lang.String itemName) {
        this.itemName = itemName;
    }

    /**
     * <p> 属性：itemStyle的Getter方法. </p>
     * 
     * @return 返回项目类型属性的值
     */
    @Column(name = "ITEM_STYLE")
    public java.lang.String getItemStyle() {
        return itemStyle;
    }

    /**
     * <p> 属性项目类型的Setter方法. </p>
     * 
     * @param itemStyle 为属性itemStyle设置的值
     */
    public void setItemStyle(java.lang.String itemStyle) {
        this.itemStyle = itemStyle;
    }

    /**
     * <p> 属性：dutyDeptId的Getter方法. </p>
     * 
     * @return 返回负责部门ID属性的值
     */
    @Column(name = "DUTY_DEPT_ID")
    public java.lang.String getDutyDeptId() {
        return dutyDeptId;
    }

    /**
     * <p> 属性负责部门ID的Setter方法. </p>
     * 
     * @param dutyDeptId 为属性dutyDeptId设置的值
     */
    public void setDutyDeptId(java.lang.String dutyDeptId) {
        this.dutyDeptId = dutyDeptId;
    }

    /**
     * <p> 属性：dutyDeptName的Getter方法. </p>
     * 
     * @return 返回负责部门名称属性的值
     */
    @Column(name = "DUTY_DEPT_NAME")
    public java.lang.String getDutyDeptName() {
        return dutyDeptName;
    }

    /**
     * <p> 属性负责部门名称的Setter方法. </p>
     * 
     * @param dutyDeptName 为属性dutyDeptName设置的值
     */
    public void setDutyDeptName(java.lang.String dutyDeptName) {
        this.dutyDeptName = dutyDeptName;
    }

    /**
     * <p> 属性：principalId的Getter方法. </p>
     * 
     * @return 返回项目负责人ID属性的值
     */
    @Column(name = "PRINCIPAL_ID")
    public java.lang.String getPrincipalId() {
        return principalId;
    }

    /**
     * <p> 属性项目负责人ID的Setter方法. </p>
     * 
     * @param principalId 为属性principalId设置的值
     */
    public void setPrincipalId(java.lang.String principalId) {
        this.principalId = principalId;
    }

    /**
     * <p> 属性：principalName的Getter方法. </p>
     * 
     * @return 返回项目负责人名称属性的值
     */
    @Column(name = "PRINCIPAL_NAME")
    public java.lang.String getPrincipalName() {
        return principalName;
    }

    /**
     * <p> 属性项目负责人名称的Setter方法. </p>
     * 
     * @param principalName 为属性principalName设置的值
     */
    public void setPrincipalName(java.lang.String principalName) {
        this.principalName = principalName;
    }

    /**
     * <p> 属性：prjmanagerId的Getter方法. </p>
     * 
     * @return 返回项目经理ID属性的值
     */
    @Column(name = "PRJMANAGER_ID")
    public java.lang.String getPrjmanagerId() {
        return prjmanagerId;
    }

    /**
     * <p> 属性项目经理ID的Setter方法. </p>
     * 
     * @param prjmanagerId 为属性prjmanagerId设置的值
     */
    public void setPrjmanagerId(java.lang.String prjmanagerId) {
        this.prjmanagerId = prjmanagerId;
    }

    /**
     * <p> 属性：prjmanagerName的Getter方法. </p>
     * 
     * @return 返回项目经理名称属性的值
     */
    @Column(name = "PRJMANAGER_NAME")
    public java.lang.String getPrjmanagerName() {
        return prjmanagerName;
    }

    /**
     * <p> 属性项目经理名称的Setter方法. </p>
     * 
     * @param prjmanagerName 为属性prjmanagerName设置的值
     */
    public void setPrjmanagerName(java.lang.String prjmanagerName) {
        this.prjmanagerName = prjmanagerName;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回项目同步时间属性的值
     */
    @Column(name = "CREATE_DATE")
    public java.util.Date getCreateDate() {
        return createDate;
    }

    /**
     * <p> 属性：workCost的Getter方法. </p>
     * 
     * @return 返回项目工时成本属性的值
     */
    @Column(name = "WORK_COST")
    public BigDecimal getWorkCost() {
        return workCost;
    }

    /**
     * <p> 属性项目工时成本的Setter方法. </p>
     * 
     * @param workCost 为属性workCost设置的值
     */
    public void setWorkCost(BigDecimal workCost) {
        this.workCost = workCost;
    }

    /**
     * <p> 属性：wcCreateDate的Getter方法. </p>
     * 
     * @return 返回工时成本同步时间属性的值
     */
    @Column(name = "WC_CREATE_DATE")
    public java.util.Date getWcCreateDate() {
        return wcCreateDate;
    }

    /**
     * <p> 属性工时成本同步时间的Setter方法. </p>
     * 
     * @param wcCreateDate 为属性wcCreateDate设置的值
     */
    public void setWcCreateDate(java.util.Date wcCreateDate) {
        this.wcCreateDate = wcCreateDate;
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
        ItProjectInfo obj1 = (ItProjectInfo) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
