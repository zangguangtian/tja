package com.df.tja.domain;
/**
 * 项目名称:tja-model
 *
 * OcSchemeTM.java
 *
 * 2018年11月9日 15:08:57
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

import com.df.framework.base.domain.BaseDomain;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * <p>OcScheme </p>
 *
 * <p>描述：项目策划主表 </p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月9日 15:08:57</p>
 *
 * @author dengjiayan
 *
 * @version 1.0.0
 *
 */

@Entity
@Table(name = "OC_SCHEME_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcScheme extends BaseDomain {

    /** serialVersionUID */
    private static final long serialVersionUID = -5685537038123536920L;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：项目WBS。参考系统配置表 */
    private java.lang.String proWbs;

    /** 属性：备注 */
    private java.lang.String remark;

    /** 属性：是否删除。1：是；0：否 */
    private Boolean delFlag;

    /**
     * <p> 属性：proId的Getter方法. </p>
     *
     * @return 返回项目ID属性的值
     */
    @Column(name="PRO_ID")
    public java.lang.String getProId()
    {
        return proId;
    }

    /**
     * <p> 属性项目ID的Setter方法. </p>
     *
     * @param proId 为属性proId设置的值
     */
    public void setProId(java.lang.String proId)
    {
        this.proId = proId;
    }

    /**
     * <p> 属性：proWbs的Getter方法. </p>
     *
     * @return 返回项目WBS属性的值
     */
    @Column(name="PRO_WBS")
    public java.lang.String getProWbs()
    {
        return proWbs;
    }

    /**
     * <p> 属性项目WBS的Setter方法. </p>
     *
     * @param proWbs 为属性proWbs设置的值
     */
    public void setProWbs(java.lang.String proWbs)
    {
        this.proWbs = proWbs;
    }

    /**
     * <p> 属性：remark的Getter方法. </p>
     *
     * @return 返回备注属性的值
     */
    @Column(name="REMARK")
    public java.lang.String getRemark()
    {
        return remark;
    }

    /**
     * <p> 属性备注的Setter方法. </p>
     *
     * @param remark 为属性remark设置的值
     */
    public void setRemark(java.lang.String remark)
    {
        this.remark = remark;
    }

    /**
     * <p> 属性：delFlag的Getter方法. </p>
     *
     * @return 返回是否删除属性的值
     */
    @Column(name="DEL_FLAG")
    public Boolean getDelFlag()
    {
        return delFlag;
    }

    /**
     * <p> 属性是否删除的Setter方法. </p>
     *
     * @param delFlag 为属性delFlag设置的值
     */
    public void setDelFlag(Boolean delFlag)
    {
        this.delFlag = delFlag;
    }

    /**
     * <p> 属性：creator的Getter方法. </p>
     *
     * @return 返回登记人属性的值
     */
    @Column(name="CREATOR")
    public java.lang.String getCreator()
    {
        return creator;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     *
     * @return 返回登记时间属性的值
     */
    @Column(name="CREATE_DATE")
    public java.util.Date getCreateDate()
    {
        return createDate;
    }

    /**
     * <p> 属性：modifier的Getter方法. </p>
     *
     * @return 返回修改人属性的值
     */
    @Column(name="MODIFIER")
    public java.lang.String getModifier()
    {
        return modifier;
    }

    /**
     * <p> 属性：modifyDate的Getter方法. </p>
     *
     * @return 返回修改时间属性的值
     */
    @Column(name="MODIFY_DATE")
    public java.util.Date getModifyDate()
    {
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
        OcScheme obj1 = (OcScheme) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}