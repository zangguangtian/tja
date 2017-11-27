/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItProPhasesInfo.java
 * 
 * 2017年10月10日-下午2:47:05
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain;

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
 * <p>ItProPhasesInfo </p>
 * 
 * <p>描述：项目阶段接口表 </p>
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
@Table(name = "IT_PRO_PHASES_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
@IgnoreWriteLog(isIgnore = true)
public class ItProPhasesInfo extends SuperDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 4100295709578472812L;

    /** 属性：阶段名称 */
    private java.lang.String name;

    /** 属性：阶段父ID */
    private java.lang.String prjPhaseId;

    /** 属性：关联项目ID */
    private java.lang.String itemId;

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
     * <p> 属性：name的Getter方法. </p>
     * 
     * @return 返回阶段名称属性的值
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return name;
    }

    /**
     * <p> 属性阶段名称的Setter方法. </p>
     * 
     * @param name 为属性name设置的值
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * <p> 属性：prjPhaseId的Getter方法. </p>
     * 
     * @return 返回阶段父ID属性的值
     */
    @Column(name = "PRJ_PHASE_ID")
    public java.lang.String getPrjPhaseId() {
        return prjPhaseId;
    }

    /**
     * <p> 属性阶段父ID的Setter方法. </p>
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
        ItProPhasesInfo obj1 = (ItProPhasesInfo) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
