package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfYieldMajorRATE.java
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
 * <p>WfYieldMajorRate </p>
 * 
 * <p>描述：项目年度产值专业结算比例 </p>
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
@Table(name = "WF_YIELD_MAJOR_RATE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfYieldMajorRate extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -1526929232432816299L;

    /** 属性：流程ID */
    private java.lang.String wfId;

    /** 属性：专业代码 */
    private java.lang.String majorCode;

    /** 属性：排序号 */
    private Integer majorSort;

    /** 属性：结算比例 */
    private BigDecimal settleRate;

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
     * <p> 属性：majorSort的Getter方法. </p>
     * 
     * @return 返回排序号属性的值
     */
    @Column(name = "MAJOR_SORT")
    public Integer getMajorSort() {
        return majorSort;
    }

    /**
     * <p> 属性排序号的Setter方法. </p>
     * 
     * @param majorSort 为属性majorSort设置的值
     */
    public void setMajorSort(Integer majorSort) {
        this.majorSort = majorSort;
    }

    /**
     * <p> 属性：settleRate的Getter方法. </p>
     * 
     * @return 返回结算比例属性的值
     */
    @Column(name = "SETTLE_RATE")
    public BigDecimal getSettleRate() {
        return settleRate;
    }

    /**
     * <p> 属性结算比例的Setter方法. </p>
     * 
     * @param settleRate 为属性settleRate设置的值
     */
    public void setSettleRate(BigDecimal settleRate) {
        this.settleRate = settleRate;
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
        WfYieldMajorRate obj1 = (WfYieldMajorRate) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}