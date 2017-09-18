package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysMENU.java
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
 * <p>SysMenu </p>
 * 
 * <p>描述：系统菜单 </p>
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
@Table(name = "SYS_MENU")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysMenu extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 930307767825489190L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：菜单类型。1000：目录；2000：地址；3000：按钮 */
    private java.lang.String menuType;

    /** 属性：菜单名称 */
    private java.lang.String name;

    /** 属性：菜单资源权限代码 */
    private java.lang.String resKey;

    /** 属性：菜单层级 */
    private Integer menuLevel;

    /** 属性：菜单排序号。排序值越小越靠前 */
    private Integer sort;

    /** 属性：是否显示图标。1：显示；0：不显示 */
    private Boolean iconFlag;

    /** 属性：显示图标的CLASS */
    private java.lang.String iconClass;

    /** 属性：URL类型。1000：内部地址；2000：外部地址； */
    private java.lang.String urlType;

    /** 属性：菜单URL */
    private java.lang.String menuUrl;

    /** 属性：打开方式。self：当前页；blank：新页面 */
    private java.lang.String openType;

    /** 属性：菜单描述 */
    private java.lang.String menuDesc;

    /** 属性：是否可用。1：可用；0：不可用 */
    private Boolean enabledFlag;

    /** 属性：父菜单ID */
    private java.lang.String parentId;

    /** 属性：树ID之间用@分隔 */
    private java.lang.String treePath;

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
     * <p> 属性：menuType的Getter方法. </p>
     * 
     * @return 返回菜单类型属性的值
     */
    @Column(name = "MENU_TYPE")
    public java.lang.String getMenuType() {
        return menuType;
    }

    /**
     * <p> 属性菜单类型的Setter方法. </p>
     * 
     * @param menuType 为属性menuType设置的值
     */
    public void setMenuType(java.lang.String menuType) {
        this.menuType = menuType;
    }

    /**
     * <p> 属性：name的Getter方法. </p>
     * 
     * @return 返回菜单名称属性的值
     */
    @Column(name = "NAME")
    public java.lang.String getName() {
        return name;
    }

    /**
     * <p> 属性菜单名称的Setter方法. </p>
     * 
     * @param name 为属性name设置的值
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * <p> 属性：resKey的Getter方法. </p>
     * 
     * @return 返回权限代码属性的值
     */
    @Column(name = "RES_KEY")
    public java.lang.String getResKey() {
        return resKey;
    }

    /**
     * <p> 属性权限代码的Setter方法. </p>
     * 
     * @param resKey 为属性resKey设置的值
     */
    public void setResKey(java.lang.String resKey) {
        this.resKey = resKey;
    }

    /**
     * <p> 属性：menuLevel的Getter方法. </p>
     * 
     * @return 返回菜单层级属性的值
     */
    @Column(name = "MENU_LEVEL")
    public Integer getMenuLevel() {
        return menuLevel;
    }

    /**
     * <p> 属性菜单层级的Setter方法. </p>
     * 
     * @param menuLevel 为属性menuLevel设置的值
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * <p> 属性：sort的Getter方法. </p>
     * 
     * @return 返回菜单排序号属性的值
     */
    @Column(name = "SORT")
    public Integer getSort() {
        return sort;
    }

    /**
     * <p> 属性菜单排序号的Setter方法. </p>
     * 
     * @param sort 为属性sort设置的值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * <p> 属性：iconFlag的Getter方法. </p>
     * 
     * @return 返回是否显示图标属性的值
     */
    @Column(name = "ICON_FLAG")
    public Boolean getIconFlag() {
        return iconFlag;
    }

    /**
     * <p> 属性是否显示图标的Setter方法. </p>
     * 
     * @param iconFlag 为属性iconFlag设置的值
     */
    public void setIconFlag(Boolean iconFlag) {
        this.iconFlag = iconFlag;
    }

    /**
     * <p> 属性：iconClass的Getter方法. </p>
     * 
     * @return 返回显示图标属性的值
     */
    @Column(name = "ICON_CLASS")
    public java.lang.String getIconClass() {
        return iconClass;
    }

    /**
     * <p> 属性显示图标的Setter方法. </p>
     * 
     * @param iconClass 为属性iconClass设置的值
     */
    public void setIconClass(java.lang.String iconClass) {
        this.iconClass = iconClass;
    }

    /**
     * <p> 属性：urlType的Getter方法. </p>
     * 
     * @return 返回URL类型属性的值
     */
    @Column(name = "URL_TYPE")
    public java.lang.String getUrlType() {
        return urlType;
    }

    /**
     * <p> 属性URL类型的Setter方法. </p>
     * 
     * @param urlType 为属性urlType设置的值
     */
    public void setUrlType(java.lang.String urlType) {
        this.urlType = urlType;
    }

    /**
     * <p> 属性：menuUrl的Getter方法. </p>
     * 
     * @return 返回菜单URL属性的值
     */
    @Column(name = "MENU_URL")
    public java.lang.String getMenuUrl() {
        return menuUrl;
    }

    /**
     * <p> 属性菜单URL的Setter方法. </p>
     * 
     * @param menuUrl 为属性menuUrl设置的值
     */
    public void setMenuUrl(java.lang.String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * <p> 属性：openType的Getter方法. </p>
     * 
     * @return 返回打开方式属性的值
     */
    @Column(name = "OPEN_TYPE")
    public java.lang.String getOpenType() {
        return openType;
    }

    /**
     * <p> 属性打开方式的Setter方法. </p>
     * 
     * @param openType 为属性openType设置的值
     */
    public void setOpenType(java.lang.String openType) {
        this.openType = openType;
    }

    /**
     * <p> 属性：menuDesc的Getter方法. </p>
     * 
     * @return 返回菜单描述属性的值
     */
    @Column(name = "MENU_DESC")
    public java.lang.String getMenuDesc() {
        return menuDesc;
    }

    /**
     * <p> 属性菜单描述的Setter方法. </p>
     * 
     * @param menuDesc 为属性menuDesc设置的值
     */
    public void setMenuDesc(java.lang.String menuDesc) {
        this.menuDesc = menuDesc;
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
     * <p> 属性：parentId的Getter方法. </p>
     * 
     * @return 返回父菜单ID属性的值
     */
    @Column(name = "PARENT_ID")
    public java.lang.String getParentId() {
        return parentId;
    }

    /**
     * <p> 属性父菜单ID的Setter方法. </p>
     * 
     * @param parentId 为属性parentId设置的值
     */
    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    /**
     * <p> 属性：treePath的Getter方法. </p>
     * 
     * @return 返回节点的所有父ID属性的值
     */
    @Column(name = "TREE_PATH")
    public java.lang.String getTreePath() {
        return treePath;
    }

    /**
     * <p> 属性节点的所有父ID的Setter方法. </p>
     * 
     * @param treePath 为属性treePath设置的值
     */
    public void setTreePath(java.lang.String treePath) {
        this.treePath = treePath;
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
        SysMenu obj1 = (SysMenu) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}