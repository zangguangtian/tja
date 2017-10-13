/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItProPhasesMajor.java
 * 
 * 2017年10月10日-下午2:48:15
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>ItProPhasesMajor </p>
 * 
 * <p>描述：项目阶段专业接口表 </p>
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
@Table(name = "IT_PRO_PHASES_MAJOR")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ItProPhasesMajor extends BaseDomain {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 979792651478296543L;

    /** 属性：专业代码 */
    private java.lang.String majorCode;

    /** 属性：专业名称 */
    private java.lang.String majorName;

    /** 属性：关联阶段ID */
    private java.lang.String prjPhaseId;

    /** 属性：关联项目ID */
    private java.lang.String itemId;

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
     * <p> 属性：majorName的Getter方法. </p>
     * 
     * @return 返回专业名称属性的值
     */
    @Column(name = "MAJOR_NAME")
    public java.lang.String getMajorName() {
        return majorName;
    }

    /**
     * <p> 属性专业名称的Setter方法. </p>
     * 
     * @param majorName 为属性majorName设置的值
     */
    public void setMajorName(java.lang.String majorName) {
        this.majorName = majorName;
    }

    /**
     * <p> 属性：prjPhaseId的Getter方法. </p>
     * 
     * @return 返回关联阶段ID属性的值
     */
    @Column(name = "PRJ_PHASE_ID")
    public java.lang.String getPrjPhaseId() {
        return prjPhaseId;
    }

    /**
     * <p> 属性关联阶段ID的Setter方法. </p>
     * 
     * @param prjPhaseId 为属性prjPhaseId设置的值
     */
    public void setPrjPhaseId(java.lang.String prjPhaseId) {
        this.prjPhaseId = prjPhaseId;
    }

    /**
     * <p> 属性：itemId的Getter方法. </p>
     * 
     * @return 返回关联项目ID属性的值
     */
    @Column(name = "ITEM_ID")
    public java.lang.String getItemId() {
        return itemId;
    }

    /**
     * <p> 属性关联项目ID的Setter方法. </p>
     * 
     * @param itemId 为属性itemId设置的值
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回同步时间属性的值
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
        ItProPhasesMajor obj1 = (ItProPhasesMajor) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
