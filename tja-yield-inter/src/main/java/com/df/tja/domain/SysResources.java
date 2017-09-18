package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysRESOURCES.java
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
 * <p>SysResources </p>
 * 
 * <p>描述：系统资源表 </p>
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
@Table(name = "SYS_RESOURCES")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysResources extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 3594204839288658530L;

    /** 属性：父资源ID */
    private java.lang.String parentId;

    /** 属性：资源树ID。此资源的所有父节点用@符号拼接 */
    private java.lang.String treePath;

    /** 属性：资源编码 */
    private java.lang.String resCode;

    /** 属性：资源名称 */
    private java.lang.String resName;

    /** 属性：资源层级 */
    private Integer resLevel;

    /** 属性：资源类型。1000：目录；2000：地址；3000：按钮 */
    private java.lang.String resType;

    /** 属性：匹配模式 */
    private java.lang.String pattern;

    /** 属性：号码越大优先过滤 */
    private Integer resSort;

    /** 属性：说明 */
    private java.lang.String explain;

    /** 属性：是否可用。1：可用；0：不可用 */
    private Boolean isEnabled;

    /**
     * <p> 属性：parentId的Getter方法. </p>
     * 
     * @return 返回父资源ID属性的值
     */
    @Column(name = "PARENT_ID")
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * <p> 属性父资源ID的Setter方法. </p>
     * 
     * @param parentId 为属性parentId设置的值
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p> 属性：treePath的Getter方法. </p>
     * 
     * @return 返回树资源ID属性的值
     */
    @Column(name = "TREE_PATH")
    public java.lang.String getTreePath() {
        return treePath;
    }

    /**
     * <p> 属性树资源ID的Setter方法. </p>
     * 
     * @param treePath 为属性treePath设置的值
     */
    public void setTreePath(java.lang.String treePath) {
        this.treePath = treePath;
    }

    /**
     * <p> 属性：resCode的Getter方法. </p>
     * 
     * @return 返回资源编码属性的值
     */
    @Column(name = "RES_CODE")
    public java.lang.String getResCode() {
        return resCode;
    }

    /**
     * <p> 属性资源编码的Setter方法. </p>
     * 
     * @param resCode 为属性resCode设置的值
     */
    public void setResCode(java.lang.String resCode) {
        this.resCode = resCode;
    }

    /**
     * <p> 属性：resName的Getter方法. </p>
     * 
     * @return 返回资源名称属性的值
     */
    @Column(name = "RES_NAME")
    public java.lang.String getResName() {
        return resName;
    }

    /**
     * <p> 属性资源名称的Setter方法. </p>
     * 
     * @param resName 为属性resName设置的值
     */
    public void setResName(java.lang.String resName) {
        this.resName = resName;
    }

    /**
     * <p> 属性：resLevel的Getter方法. </p>
     * 
     * @return 返回资源层级属性的值
     */
    @Column(name = "RES_LEVEL")
    public Integer getResLevel() {
        return resLevel;
    }

    /**
     * <p> 属性资源层级的Setter方法. </p>
     * 
     * @param resLevel 为属性resLevel设置的值
     */
    public void setResLevel(Integer resLevel) {
        this.resLevel = resLevel;
    }

    /**
     * <p> 属性：resType的Getter方法. </p>
     * 
     * @return 返回资源类型属性的值
     */
    @Column(name = "RES_TYPE")
    public java.lang.String getResType() {
        return resType;
    }

    /**
     * <p> 属性资源类型的Setter方法. </p>
     * 
     * @param resType 为属性resType设置的值
     */
    public void setResType(java.lang.String resType) {
        this.resType = resType;
    }

    /**
     * <p> 属性：pattern的Getter方法. </p>
     * 
     * @return 返回匹配模式属性的值
     */
    @Column(name = "PATTERN")
    public java.lang.String getPattern() {
        return pattern;
    }

    /**
     * <p> 属性匹配模式的Setter方法. </p>
     * 
     * @param pattern 为属性pattern设置的值
     */
    public void setPattern(java.lang.String pattern) {
        this.pattern = pattern;
    }

    /**
     * <p> 属性：resSort的Getter方法. </p>
     * 
     * @return 返回排序号属性的值
     */
    @Column(name = "RES_SORT")
    public Integer getResSort() {
        return resSort;
    }

    /**
     * <p> 属性排序号的Setter方法. </p>
     * 
     * @param resSort 为属性resSort设置的值
     */
    public void setResSort(Integer resSort) {
        this.resSort = resSort;
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
     * <p> 属性：isEnabled的Getter方法. </p>
     * 
     * @return 返回是否可用属性的值
     */
    @Column(name = "IS_ENABLED")
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * <p> 属性是否可用的Setter方法. </p>
     * 
     * @param isEnabled 为属性isEnabled设置的值
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
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
        SysResources obj1 = (SysResources) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}