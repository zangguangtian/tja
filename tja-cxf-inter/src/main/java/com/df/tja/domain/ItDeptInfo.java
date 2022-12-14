/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItDeptInfo.java
 * 
 * 2017年10月10日-下午1:58:55
 *
 * 2017 TabZhu-版权所有 
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
 * <p>ItDeptInfo </p>
 * 
 * <p>描述：部门信息接口表 </p>
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
@Table(name = "IT_DEPT_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
@IgnoreWriteLog(isIgnore = true)
public class ItDeptInfo extends SuperDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -8813093257468977001L;

    /** 属性：上级部门ID */
    private java.lang.String parentId;

    /** 属性：部门全ID */
    private java.lang.String idPath;

    /** 属性：部门名称 */
    private java.lang.String name;

    /** 属性：排序序号 */
    private Integer sortIndex;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "ID", unique = true, nullable = false)
    public java.lang.String getId() {
        return id;
    }

    /**
     * <p> 属性：parentId的Getter方法. </p>
     * 
     * @return 返回上级部门ID属性的值
     */
    @Column(name = "PARENT_ID")
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * <p> 属性上级部门ID的Setter方法. </p>
     * 
     * @param parentId 为属性parentId设置的值
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p> 属性：idPath的Getter方法. </p>
     * 
     * @return 返回部门全ID属性的值
     */
    @Column(name = "ID_PATH")
    public java.lang.String getIdPath() {
        return idPath;
    }

    /**
     * <p> 属性部门全ID的Setter方法. </p>
     * 
     * @param idPath 为属性idPath设置的值
     */
    public void setIdPath(java.lang.String idPath) {
        this.idPath = idPath;
    }

    /**
     * <p> 属性：name的Getter方法. </p>
     * 
     * @return 返回部门名称属性的值
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return name;
    }

    /**
     * <p> 属性部门名称的Setter方法. </p>
     * 
     * @param name 为属性name设置的值
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * <p> 属性：sortIndex的Getter方法. </p>
     * 
     * @return 返回排序序号属性的值
     */
    @Column(name = "SORT_INDEX")
    public Integer getSortIndex() {
        return sortIndex;
    }

    /**
     * <p> 属性排序序号的Setter方法. </p>
     * 
     * @param sortIndex 为属性sortIndex设置的值
     */
    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
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
        ItDeptInfo obj1 = (ItDeptInfo) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
