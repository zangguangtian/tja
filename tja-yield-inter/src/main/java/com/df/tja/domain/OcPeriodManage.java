package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcPeriodMANAGE.java
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
 * <p>OcPeriodManage </p>
 * 
 * <p>描述：期间管理表 </p>
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
@Table(name = "OC_PERIOD_MANAGE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcPeriodManage extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 2616839561639324789L;

    /** 属性：类型代码。参考系统配置表 */
    private java.lang.String typeCode;

    /** 属性：期间名称 */
    private java.lang.String periodName;

    /** 属性：期间范围起 */
    private java.util.Date rangeStart;

    /** 属性：期间范围止 */
    private java.util.Date rangeEnd;

    /** 属性：说明 */
    private java.lang.String explain;

    /** 属性：状态 */
    private java.lang.String statusCode;

    /** 属性：开始日期 */
    private java.util.Date startDate;

    /** 属性：结束日期 */
    private java.util.Date endDate;

    /** 属性：备注 */
    private String remark;

    /**
     * <p> 属性：typeCode的Getter方法. </p>
     * 
     * @return 返回类型代码属性的值
     */
    @Column(name = "TYPE_CODE")
    public java.lang.String getTypeCode() {
        return typeCode;
    }

    /**
     * <p> 属性类型代码的Setter方法. </p>
     * 
     * @param typeCode 为属性typeCode设置的值
     */
    public void setTypeCode(java.lang.String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * <p> 属性：periodName的Getter方法. </p>
     * 
     * @return 返回期间名称属性的值
     */
    @Column(name = "PERIOD_NAME")
    public java.lang.String getPeriodName() {
        return periodName;
    }

    /**
     * <p> 属性期间名称的Setter方法. </p>
     * 
     * @param periodName 为属性periodName设置的值
     */
    public void setPeriodName(java.lang.String periodName) {
        this.periodName = periodName;
    }

    /**
     * <p> 属性：rangeStart的Getter方法. </p>
     * 
     * @return 返回期间范围起属性的值
     */
    @Column(name = "RANGE_START")
    public java.util.Date getRangeStart() {
        return rangeStart;
    }

    /**
     * <p> 属性期间范围起的Setter方法. </p>
     * 
     * @param rangeStart 为属性rangeStart设置的值
     */
    public void setRangeStart(java.util.Date rangeStart) {
        this.rangeStart = rangeStart;
    }

    /**
     * <p> 属性：rangeEnd的Getter方法. </p>
     * 
     * @return 返回期间范围止属性的值
     */
    @Column(name = "RANGE_END")
    public java.util.Date getRangeEnd() {
        return rangeEnd;
    }

    /**
     * <p> 属性期间范围止的Setter方法. </p>
     * 
     * @param rangeEnd 为属性rangeEnd设置的值
     */
    public void setRangeEnd(java.util.Date rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    /**
     * <p> 属性：explain的Getter方法. </p>
     * 
     * @return 返回说明属性的值
     */
    @Column(name = "EXPLAIN")
    public java.lang.String getExplain() {
        return explain;
    }

    /**
     * <p> 属性说明的Setter方法. </p>
     * 
     * @param explain 为属性explain设置的值
     */
    public void setExplain(java.lang.String explain) {
        this.explain = explain;
    }

    /**
     * <p> 属性：statusCode的Getter方法. </p>
     * 
     * @return 返回状态代码属性的值
     */
    @Column(name = "STATUS_CODE")
    public java.lang.String getStatusCode() {
        return statusCode;
    }

    /**
     * <p> 属性状态代码的Setter方法. </p>
     * 
     * @param statusCode 为属性statusCode设置的值
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * <p> 属性：startDate的Getter方法. </p>
     * 
     * @return 返回开始日期属性的值
     */
    @Column(name = "START_DATE")
    public java.util.Date getStartDate() {
        return startDate;
    }

    /**
     * <p> 属性开始日期的Setter方法. </p>
     * 
     * @param startDate 为属性startDate设置的值
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    /**
     * <p> 属性：endDate的Getter方法. </p>
     * 
     * @return 返回结束日期属性的值
     */
    @Column(name = "END_DATE")
    public java.util.Date getEndDate() {
        return endDate;
    }

    /**
     * <p> 属性结束日期的Setter方法. </p>
     * 
     * @param endDate 为属性endDate设置的值
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    /**
     * <p> 属性：remark的Getter方法. </p>
     * 
     * @return 返回备注属性的值
     */
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    /**
     * <p> 属性备注的Setter方法. </p>
     * 
     * @param remark 为属性remark设置的值
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
        OcPeriodManage obj1 = (OcPeriodManage) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
