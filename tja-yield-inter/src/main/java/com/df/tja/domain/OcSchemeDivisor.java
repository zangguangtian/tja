package com.df.tja.domain;
/**
 * 项目名称:tja-model
 *
 * OcSchemeDivisorTM.java
 *
 * 2018年11月9日 15:08:57
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>OcSchemeDivisor </p>
 *
 * <p>描述：项目策划因子表（包括阶段、专业、子项、任务、人员角色） </p>
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
@Table(name = "OC_SCHEME_DIVISOR_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OcSchemeDivisor extends BaseDomain {

    /** serialVersionUID */
    private static final long serialVersionUID = 6392863215596959679L;

    /** 属性：项目策划ID */
    private java.lang.String schemeId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：因子父ID */
    private java.lang.String parentId;

    /** 属性：因子名称，，只有DIVISOR_GRADE为5时，不存在 */
    private java.lang.String divisorName;

    /** 属性：项目角色代码。参考系统配置表，只有DIVISOR_GRADE为5时，才存在 */
    private java.lang.String staffRole;

    /** 属性：员工ID。HR_STAFF_TM.ID，只有DIVISOR_GRADE为5时，才存在 */
    private java.lang.String staffId;

    /** 属性：策划比例 */
    private BigDecimal schemeRatio;

    /** 属性：因子负责人(主要指专业负责人) */
    private java.lang.String divisorDirector;

    /** 属性：因子级别。1：阶段；2：专业；3：子项；4：任务；5：人员角色； */
    private Integer divisorSort;

    /** 属性：因子级别。1：阶段；2：专业；3：子项；4：任务；5：人员角色； */
    private Integer divisorGrade;

    /** 属性：因子ID树。项目ID@阶段ID@专业ID@子项ID@任务ID@人员角色ID */
    private java.lang.String treePath;

    /**
     * <p> 属性：schemeId的Getter方法. </p>
     *
     * @return 返回项目策划ID属性的值
     */
    @Column(name="SCHEME_ID")
    public java.lang.String getSchemeId()
    {
        return schemeId;
    }

    /**
     * <p> 属性项目策划ID的Setter方法. </p>
     *
     * @param schemeId 为属性schemeId设置的值
     */
    public void setSchemeId(java.lang.String schemeId)
    {
        this.schemeId = schemeId;
    }

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
     * <p> 属性：parentId的Getter方法. </p>
     *
     * @return 返回因子父ID属性的值
     */
    @Column(name="PARENT_ID")
    public java.lang.String getParentId()
    {
        return parentId;
    }

    /**
     * <p> 属性因子父ID的Setter方法. </p>
     *
     * @param parentId 为属性parentId设置的值
     */
    public void setParentId(java.lang.String parentId)
    {
        this.parentId = parentId;
    }

    /**
     * <p> 属性：divisorName的Getter方法. </p>
     *
     * @return 返回因子名称属性的值
     */
    @Column(name="DIVISOR_NAME")
    public java.lang.String getDivisorName()
    {
        return divisorName;
    }

    /**
     * <p> 属性因子名称的Setter方法. </p>
     *
     * @param divisorName 为属性divisorName设置的值
     */
    public void setDivisorName(java.lang.String divisorName)
    {
        this.divisorName = divisorName;
    }

    /**
     * <p> 属性：staffRole的Getter方法. </p>
     *
     * @return 返回项目角色代码属性的值
     */
    @Column(name="STAFF_ROLE")
    public java.lang.String getStaffRole()
    {
        return staffRole;
    }

    /**
     * <p> 属性项目角色代码的Setter方法. </p>
     *
     * @param staffRole 为属性staffRole设置的值
     */
    public void setStaffRole(java.lang.String staffRole)
    {
        this.staffRole = staffRole;
    }

    /**
     * <p> 属性：staffId的Getter方法. </p>
     *
     * @return 返回员工ID属性的值
     */
    @Column(name="STAFF_ID")
    public java.lang.String getStaffId()
    {
        return staffId;
    }

    /**
     * <p> 属性员工ID的Setter方法. </p>
     *
     * @param staffId 为属性staffId设置的值
     */
    public void setStaffId(java.lang.String staffId)
    {
        this.staffId = staffId;
    }

    /**
     * <p> 属性：schemeRatio的Getter方法. </p>
     *
     * @return 返回策划比例属性的值
     */
    @Column(name="SCHEME_RATIO")
    public BigDecimal getSchemeRatio()
    {
        return schemeRatio;
    }

    /**
     * <p> 属性策划比例的Setter方法. </p>
     *
     * @param schemeRatio 为属性schemeRatio设置的值
     */
    public void setSchemeRatio(BigDecimal schemeRatio)
    {
        this.schemeRatio = schemeRatio;
    }

    /**
     * <p> 属性：divisorDirector的Getter方法. </p>
     *
     * @return 返回因子负责人属性的值
     */
    @Column(name="DIVISOR_DIRECTOR")
    public java.lang.String getDivisorDirector()
    {
        return divisorDirector;
    }

    /**
     * <p> 属性因子负责人的Setter方法. </p>
     *
     * @param divisorDirector 为属性divisorDirector设置的值
     */
    public void setDivisorDirector(java.lang.String divisorDirector)
    {
        this.divisorDirector = divisorDirector;
    }

    /**
     * <p> 属性divisorSort的Getter方法. </p>
     * 
     * @return 返回divisorSort属性的值
     */
    @Column(name = "DIVISOR_SORT")
    public Integer getDivisorSort() {
        return divisorSort;
    }

    /**
     * <p> 属性divisorSort的Setter方法. </p>
     * 
     * @param divisorSort 为属性divisorSort设置的值
     */
    public void setDivisorSort(Integer divisorSort) {
        this.divisorSort = divisorSort;
    }

    /**
     * <p> 属性：divisorGrade的Getter方法. </p>
     *
     * @return 返回因子级别属性的值
     */
    @Column(name="DIVISOR_GRADE")
    public Integer getDivisorGrade()
    {
        return divisorGrade;
    }

    /**
     * <p> 属性因子级别的Setter方法. </p>
     *
     * @param divisorGrade 为属性divisorGrade设置的值
     */
    public void setDivisorGrade(Integer divisorGrade)
    {
        this.divisorGrade = divisorGrade;
    }

    /**
     * <p> 属性：treePath的Getter方法. </p>
     *
     * @return 返回因子ID树属性的值
     */
    @Column(name="TREE_PATH")
    public java.lang.String getTreePath()
    {
        return treePath;
    }

    /**
     * <p> 属性因子ID树的Setter方法. </p>
     *
     * @param treePath 为属性treePath设置的值
     */
    public void setTreePath(java.lang.String treePath)
    {
        this.treePath = treePath;
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
        OcSchemeDivisor obj1 = (OcSchemeDivisor) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
