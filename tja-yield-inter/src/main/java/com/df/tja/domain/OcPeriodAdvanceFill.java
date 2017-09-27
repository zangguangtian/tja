package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* OcPeriodAdvanceFILL.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

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
 * <p>OcPeriodAdvanceFill </p>
 * 
 * <p>描述：期间提前上报表 </p>
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
@Table(name = "OC_PERIOD_ADVANCE_FILL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcPeriodAdvanceFill extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -2574940836683916637L;

    /** 属性：期间ID */
    private java.lang.String periodId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：备注 */
    private String remark;

    /** ex：期间 */
    private String periodName;

    /** ex：项目编号 */
    private String proCode;

    /** ex：项目名称 */
    private String proName;

    /** ex：序号 */
    private Integer number;

    /** ex：页码 */
    private Integer pageIndex;

    /** ex：页面大小 */
    private Integer pageSize;

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

    /**
     * <p> 属性periodName的Getter方法. </p>
     * 
     * @return 返回periodName属性的值
     */
    @Transient
    public String getPeriodName() {
        return periodName;
    }

    /**
     * <p> 属性periodName的Setter方法. </p>
     * 
     * @param periodName 为属性periodName设置的值
     */
    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    /**
     * <p> 属性proCode的Getter方法. </p>
     * 
     * @return 返回proCode属性的值
     */
    @Transient
    public String getProCode() {
        return proCode;
    }

    /**
     * <p> 属性proCode的Setter方法. </p>
     * 
     * @param proCode 为属性proCode设置的值
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p> 属性proName的Getter方法. </p>
     * 
     * @return 返回proName属性的值
     */
    @Transient
    public String getProName() {
        return proName;
    }

    /**
     * <p> 属性proName的Setter方法. </p>
     * 
     * @param proName 为属性proName设置的值
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * <p> 属性number的Getter方法. </p>
     * 
     * @return 返回number属性的值
     */
    @Transient
    public Integer getNumber() {
        return number;
    }

    /**
     * <p> 属性number的Setter方法. </p>
     * 
     * @param number 为属性number设置的值
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * <p> 属性pageIndex的Getter方法. </p>
     * 
     * @return 返回pageIndex属性的值
     */
    @Transient
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * <p> 属性pageIndex的Setter方法. </p>
     * 
     * @param pageIndex 为属性pageIndex设置的值
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * <p> 属性pageSize的Getter方法. </p>
     * 
     * @return 返回pageSize属性的值
     */
    @Transient
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * <p> 属性pageSize的Setter方法. </p>
     * 
     * @param pageSize 为属性pageSize设置的值
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
        OcPeriodAdvanceFill obj1 = (OcPeriodAdvanceFill) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
