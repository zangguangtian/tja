package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfShemeTEAM.java
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
 * <p>WfShemeTeam </p>
 * 
 * <p>描述：项目方案产值设计团队表 </p>
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
@Table(name = "WF_SHEME_TEAM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfShemeTeam extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -2677964730008798640L;

    /** 属性：流程ID */
    private java.lang.String wfId;

    /** 属性：人员信息ID */
    private java.lang.String staffId;

    /** 属性：人员排序号 */
    private Integer staffSort;

    /** 属性：对应比例 */
    private BigDecimal refRate;

    /** 属性：对应产值 */
    private BigDecimal refYield;

    /** 属性：人员名字 */
    private java.lang.String staffName;

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
     * <p> 属性：staffId的Getter方法. </p>
     * 
     * @return 返回人员信息ID属性的值
     */
    @Column(name = "STAFF_ID")
    public java.lang.String getStaffId() {
        return staffId;
    }

    /**
     * <p> 属性人员信息ID的Setter方法. </p>
     * 
     * @param staffId 为属性staffId设置的值
     */
    public void setStaffId(java.lang.String staffId) {
        this.staffId = staffId;
    }

    /**
     * <p> 属性：staffSort的Getter方法. </p>
     * 
     * @return 返回人员排序号属性的值
     */
    @Column(name = "STAFF_SORT")
    public Integer getStaffSort() {
        return staffSort;
    }

    /**
     * <p> 属性人员排序号的Setter方法. </p>
     * 
     * @param staffSort 为属性staffSort设置的值
     */
    public void setStaffSort(Integer staffSort) {
        this.staffSort = staffSort;
    }

    /**
     * <p> 属性：refRate的Getter方法. </p>
     * 
     * @return 返回对应比例属性的值
     */
    @Column(name = "REF_RATE")
    public BigDecimal getRefRate() {
        return refRate;
    }

    /**
     * <p> 属性对应比例的Setter方法. </p>
     * 
     * @param refRate 为属性refRate设置的值
     */
    public void setRefRate(BigDecimal refRate) {
        this.refRate = refRate;
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

    @Transient
    public java.lang.String getStaffName() {
        return staffName;
    }

    public void setStaffName(java.lang.String staffName) {
        this.staffName = staffName;
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
        WfShemeTeam obj1 = (WfShemeTeam) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
