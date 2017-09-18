package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysConfigTM.java
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
 * <p>SysConfigTm </p>
 * 
 * <p>描述：系统配置表 </p>
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
@Table(name = "SYS_CONFIG_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysConfigTm extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -4952427783897949590L;

    /** 属性：配置ID */
    private java.lang.String id;

    /** 属性：树根ID */
    private java.lang.String rootId;

    /** 属性：父配置ID */
    private java.lang.String parentId;

    /** 属性：配置代码 */
    private java.lang.String configCode;

    /** 属性：配置名称 */
    private java.lang.String configName;

    /** 属性：配置类型。1000：系统配置； */
    private java.lang.String configType;

    /** 属性：配置节点层级 */
    private Integer configLevel;

    /** 属性：排序 */
    private Integer configSort;

    /** 属性：配置项取值 */
    private java.lang.String configValue;

    /** 属性：配置描述 */
    private java.lang.String configDesc;

    /** 属性：是否可用。1：可用；0：不可用 */
    private Boolean enabledFlag;

    /** 属性：是否系统字典（备用字段）。1：是；0：否 */
    private Boolean systemFlag;

    /** 属性：登记人 */
    private java.lang.String creator;

    /** 属性：登记时间 */
    private java.util.Date createDate;

    /** 属性：修改人 */
    private java.lang.String modifier;

    /** 属性：修改时间 */
    private java.util.Date modifyDate;

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
     * <p> 属性：rootId的Getter方法. </p>
     * 
     * @return 返回树根ID属性的值
     */
    @Column(name = "ROOT_ID")
    public java.lang.String getRootId() {
        return rootId;
    }

    /**
     * <p> 属性树根ID的Setter方法. </p>
     * 
     * @param rootId 为属性rootId设置的值
     */
    public void setRootId(java.lang.String rootId) {
        this.rootId = rootId;
    }

    /**
     * <p> 属性：parentId的Getter方法. </p>
     * 
     * @return 返回父配置ID属性的值
     */
    @Column(name = "PARENT_ID")
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * <p> 属性父配置ID的Setter方法. </p>
     * 
     * @param parentId 为属性parentId设置的值
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p> 属性：configCode的Getter方法. </p>
     * 
     * @return 返回配置代码属性的值
     */
    @Column(name = "CONFIG_CODE")
    public java.lang.String getConfigCode() {
        return configCode;
    }

    /**
     * <p> 属性配置代码的Setter方法. </p>
     * 
     * @param configCode 为属性configCode设置的值
     */
    public void setConfigCode(java.lang.String configCode) {
        this.configCode = configCode;
    }

    /**
     * <p> 属性：configName的Getter方法. </p>
     * 
     * @return 返回配置名称属性的值
     */
    @Column(name = "CONFIG_NAME")
    public java.lang.String getConfigName() {
        return configName;
    }

    /**
     * <p> 属性配置名称的Setter方法. </p>
     * 
     * @param configName 为属性configName设置的值
     */
    public void setConfigName(java.lang.String configName) {
        this.configName = configName;
    }

    /**
     * <p> 属性：configType的Getter方法. </p>
     * 
     * @return 返回配置类型属性的值
     */
    @Column(name = "CONFIG_TYPE")
    public java.lang.String getConfigType() {
        return configType;
    }

    /**
     * <p> 属性配置类型的Setter方法. </p>
     * 
     * @param configType 为属性configType设置的值
     */
    public void setConfigType(java.lang.String configType) {
        this.configType = configType;
    }

    /**
     * <p> 属性：configLevel的Getter方法. </p>
     * 
     * @return 返回配置节点层级属性的值
     */
    @Column(name = "CONFIG_LEVEL")
    public Integer getConfigLevel() {
        return configLevel;
    }

    /**
     * <p> 属性配置节点层级的Setter方法. </p>
     * 
     * @param configLevel 为属性configLevel设置的值
     */
    public void setConfigLevel(Integer configLevel) {
        this.configLevel = configLevel;
    }

    /**
     * <p> 属性：configSort的Getter方法. </p>
     * 
     * @return 返回排序属性的值
     */
    @Column(name = "CONFIG_SORT")
    public Integer getConfigSort() {
        return configSort;
    }

    /**
     * <p> 属性排序的Setter方法. </p>
     * 
     * @param configSort 为属性configSort设置的值
     */
    public void setConfigSort(Integer configSort) {
        this.configSort = configSort;
    }

    /**
     * <p> 属性：configValue的Getter方法. </p>
     * 
     * @return 返回配置项取舍属性的值
     */
    @Column(name = "CONFIG_VALUE")
    public java.lang.String getConfigValue() {
        return configValue;
    }

    /**
     * <p> 属性配置项取舍的Setter方法. </p>
     * 
     * @param configValue 为属性configValue设置的值
     */
    public void setConfigValue(java.lang.String configValue) {
        this.configValue = configValue;
    }

    /**
     * <p> 属性：configDesc的Getter方法. </p>
     * 
     * @return 返回配置描述属性的值
     */
    @Column(name = "CONFIG_DESC")
    public java.lang.String getConfigDesc() {
        return configDesc;
    }

    /**
     * <p> 属性配置描述的Setter方法. </p>
     * 
     * @param configDesc 为属性configDesc设置的值
     */
    public void setConfigDesc(java.lang.String configDesc) {
        this.configDesc = configDesc;
    }

    /**
     * <p> 属性：enabledFlag的Getter方法. </p>
     * 
     * @return 返回是否可用属性的值
     */
    @Column(name = "ENABLED_FLAG")
    public Boolean getEnabledFlag() {
        return enabledFlag;
    }

    /**
     * <p> 属性是否可用的Setter方法. </p>
     * 
     * @param enabledFlag 为属性enabledFlag设置的值
     */
    public void setEnabledFlag(Boolean enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    /**
     * <p> 属性：systemFlag的Getter方法. </p>
     * 
     * @return 返回是否系统配置属性的值
     */
    @Column(name = "SYSTEM_FLAG")
    public Boolean getSystemFlag() {
        return systemFlag;
    }

    /**
     * <p> 属性是否系统配置的Setter方法. </p>
     * 
     * @param systemFlag 为属性systemFlag设置的值
     */
    public void setSystemFlag(Boolean systemFlag) {
        this.systemFlag = systemFlag;
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
     * <p> 属性修改人的Setter方法. </p>
     * 
     * @param modifier 为属性modifier设置的值
     */
    public void setModifier(java.lang.String modifier) {
        this.modifier = modifier;
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
     * <p> 属性修改时间的Setter方法. </p>
     * 
     * @param modifyDate 为属性modifyDate设置的值
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
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
        SysConfigTm obj1 = (SysConfigTm) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}