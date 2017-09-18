package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcSettleYieldIMP.java
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
 * <p>OcSettleYieldImp </p>
 * 
 * <p>描述：可结算产值导入表 </p>
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
@Table(name = "OC_SETTLE_YIELD_IMP")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcSettleYieldImp extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -3445901514795946482L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：期间ID */
    private java.lang.String periodId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：预估产值 */
    private Double estimateYield;

    /** 属性：可结算产值 */
    private Double settleYield;

    /** 属性：异常信息 */
    private java.lang.String errorInfo;

    /** 属性：登记人（导入人） */
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
     * <p> 属性：periodId的Getter方法. </p>
     * 
     * @return 返回期间ID属性的值
     */
    @Column(name = "PERIOD_ID")
    public java.lang.String getPeriodId() {
        return periodId;
    }

    /**
     * <p> 属性期间ID的Setter方法. </p>
     * 
     * @param periodId 为属性periodId设置的值
     */
    public void setPeriodId(java.lang.String periodId) {
        this.periodId = periodId;
    }

    /**
     * <p> 属性：proId的Getter方法. </p>
     * 
     * @return 返回项目ID属性的值
     */
    @Column(name = "PRO_ID")
    public java.lang.String getProId() {
        return proId;
    }

    /**
     * <p> 属性项目ID的Setter方法. </p>
     * 
     * @param proId 为属性proId设置的值
     */
    public void setProId(java.lang.String proId) {
        this.proId = proId;
    }

    /**
     * <p> 属性：estimateYield的Getter方法. </p>
     * 
     * @return 返回预估产值属性的值
     */
    @Column(name = "ESTIMATE_YIELD")
    public Double getEstimateYield() {
        return estimateYield;
    }

    /**
     * <p> 属性预估产值的Setter方法. </p>
     * 
     * @param estimateYield 为属性estimateYield设置的值
     */
    public void setEstimateYield(Double estimateYield) {
        this.estimateYield = estimateYield;
    }

    /**
     * <p> 属性：settleYield的Getter方法. </p>
     * 
     * @return 返回可结算产值属性的值
     */
    @Column(name = "SETTLE_YIELD")
    public Double getSettleYield() {
        return settleYield;
    }

    /**
     * <p> 属性可结算产值的Setter方法. </p>
     * 
     * @param settleYield 为属性settleYield设置的值
     */
    public void setSettleYield(Double settleYield) {
        this.settleYield = settleYield;
    }

    /**
     * <p> 属性：errorInfo的Getter方法. </p>
     * 
     * @return 返回异常信息属性的值
     */
    @Column(name = "ERROR_INFO")
    public java.lang.String getErrorInfo() {
        return errorInfo;
    }

    /**
     * <p> 属性异常信息的Setter方法. </p>
     * 
     * @param errorInfo 为属性errorInfo设置的值
     */
    public void setErrorInfo(java.lang.String errorInfo) {
        this.errorInfo = errorInfo;
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
        OcSettleYieldImp obj1 = (OcSettleYieldImp) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}