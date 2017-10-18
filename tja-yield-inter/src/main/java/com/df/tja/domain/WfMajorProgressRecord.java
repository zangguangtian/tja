package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfMajorProgressRECORD.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>WfMajorProgressRecord </p>
 * 
 * <p>描述：项目年月上报流程专业完成进度表 </p>
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
@Table(name = "WF_MAJOR_PROGRESS_RECORD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfMajorProgressRecord extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 1L;

    /** 属性：流程ID */
    private java.lang.String wfId;

    /** 属性：专业代码 */
    private java.lang.String majorCode;

    /** 属性：专业排序号 */
    private Integer majorSort;

    /** 属性：当年完成进度比例 */
    private BigDecimal completeRate;

    /** 属性：专业分配比例 */
    private BigDecimal alloteRate;

    /** 属性：对应产值 */
    private BigDecimal refYield;

    /** 属性：累计进度比例 */
    private BigDecimal accRate;

    /** 属性：累计产值 */
    private BigDecimal accYield;

    /** ex：专业名称 */
    private String majorName;

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
     * @return 返回专业排序号属性的值
     */
    @Column(name = "MAJOR_SORT")
    public Integer getMajorSort() {
        return majorSort;
    }

    /**
     * <p> 属性专业排序号的Setter方法. </p>
     * 
     * @param majorSort 为属性majorSort设置的值
     */
    public void setMajorSort(Integer majorSort) {
        this.majorSort = majorSort;
    }

    /**
     * <p> 属性：completeRate的Getter方法. </p>
     * 
     * @return 返回当年/月完成进度比例属性的值
     */
    @Column(name = "COMPLETE_RATE")
    public BigDecimal getCompleteRate() {
        return completeRate;
    }

    /**
     * <p> 属性当年/月完成进度比例的Setter方法. </p>
     * 
     * @param completeRate 为属性completeRate设置的值
     */
    public void setCompleteRate(BigDecimal completeRate) {
        this.completeRate = completeRate;
    }

    /**
     * <p> 属性：alloteRate的Getter方法. </p>
     * 
     * @return 返回专业分配比例属性的值
     */
    @Column(name = "ALLOTE_RATE")
    public BigDecimal getAlloteRate() {
        return alloteRate;
    }

    /**
     * <p> 属性专业分配比例的Setter方法. </p>
     * 
     * @param alloteRate 为属性alloteRate设置的值
     */
    public void setAlloteRate(BigDecimal alloteRate) {
        this.alloteRate = alloteRate;
    }

    /**
     * <p> 属性：refYield的Getter方法. </p>
     * 
     * @return 返回对应产值属性的值
     */
    @Column(name = "REF_YIELD")
    public BigDecimal getRefYield() {
        return refYield;
    }

    /**
     * <p> 属性对应产值的Setter方法. </p>
     * 
     * @param refYield 为属性refYield设置的值
     */
    public void setRefYield(BigDecimal refYield) {
        this.refYield = refYield;
    }

    /**
     * <p> 属性：accRate的Getter方法. </p>
     * 
     * @return 返回累计进度比例属性的值
     */
    @Column(name = "ACC_RATE")
    public BigDecimal getAccRate() {
        return accRate;
    }

    /**
     * <p> 属性累计进度比例的Setter方法. </p>
     * 
     * @param accRate 为属性accRate设置的值
     */
    public void setAccRate(BigDecimal accRate) {
        this.accRate = accRate;
    }

    /**
     * <p> 属性：accYield的Getter方法. </p>
     * 
     * @return 返回累计产值属性的值
     */
    @Column(name = "ACC_YIELD")
    public BigDecimal getAccYield() {
        return accYield;
    }

    /**
     * <p> 属性累计产值的Setter方法. </p>
     * 
     * @param accYield 为属性accYield设置的值
     */
    public void setAccYield(BigDecimal accYield) {
        this.accYield = accYield;
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

    /**
     * <p> 属性majorName的Getter方法. </p>
     * 
     * @return 返回majorName属性的值
     */
    @Transient
    public String getMajorName() {
        return majorName;
    }

    /**
     * <p> 属性majorName的Setter方法. </p>
     * 
     * @param majorName 为属性majorName设置的值
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
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
        WfMajorProgressRecord obj1 = (WfMajorProgressRecord) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
