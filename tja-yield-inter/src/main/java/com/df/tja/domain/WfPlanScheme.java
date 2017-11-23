package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfPlanSCHEME.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.activiti.domain.WfBaseDomain;
import com.df.framework.annotation.Comment;

/**
 * <p>WfPlanScheme </p>
 * 
 * <p>描述：项目方案产值策划流程表 </p>
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
@Table(name = "WF_PLAN_SCHEME")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfPlanScheme extends WfBaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -4049750483896033751L;

    /** 属性：项目ID */
    @Comment(name = "项目ID")
    private java.lang.String proId;

    /** 属性：项目级别。参考系统配置值 */
    @Comment(name = "项目级别")
    private java.lang.String itemGrade;

    /** 属性：设计启动时间 */
    @Comment(name = "设计启动时间")
    private java.util.Date designStart;

    /** 属性：设计完成时间 */
    @Comment(name = "设计完成时间")
    private java.util.Date designCompleted;

    /** 属性：方案产值 */
    @Comment(name = "方案产值")
    private BigDecimal schemeYield;

    /** 属性：承接部门 */
    @Comment(name = "承接部门")
    private java.lang.String receptDeptId;

    /** 属性：绩效类型 1000：中标产值；2000：入围产值*/
    @Comment(name = "绩效类型")
    private java.lang.String perfType;

    /** 属性：绩效产值*/
    @Comment(name = "绩效产值")
    private BigDecimal perfYield;

    /** 属性：概况 */
    @Comment(name = "概况")
    private String schemeOverview;

    /** 属性：备注 */
    @Comment(name = "备注")
    private String remark;

    /** 属性：绩效结算年度 */
    @Comment(name = "绩效结算年度")
    private String perfPeriod;

    /** 属性：部门名称 */
    private String orgName;

    private String perfPeriodName;

    private List<WfShemeTeam> shemeTeams;

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
     * <p> 属性：itemGrade的Getter方法. </p>
     * 
     * @return 返回项目级别属性的值
     */
    @Column(name = "ITEM_GRADE")
    public java.lang.String getItemGrade() {
        return itemGrade;
    }

    /**
     * <p> 属性项目级别的Setter方法. </p>
     * 
     * @param itemGrade 为属性itemGrade设置的值
     */
    public void setItemGrade(java.lang.String itemGrade) {
        this.itemGrade = itemGrade;
    }

    /**
     * <p> 属性：designStart的Getter方法. </p>
     * 
     * @return 返回设计启动时间属性的值
     */
    @Column(name = "DESIGN_START")
    public java.util.Date getDesignStart() {
        return designStart;
    }

    /**
     * <p> 属性设计启动时间的Setter方法. </p>
     * 
     * @param designStart 为属性designStart设置的值
     */
    public void setDesignStart(java.util.Date designStart) {
        this.designStart = designStart;
    }

    /**
     * <p> 属性：designCompleted的Getter方法. </p>
     * 
     * @return 返回设计完成时间属性的值
     */
    @Column(name = "DESIGN_COMPLETED")
    public java.util.Date getDesignCompleted() {
        return designCompleted;
    }

    /**
     * <p> 属性设计完成时间的Setter方法. </p>
     * 
     * @param designCompleted 为属性designCompleted设置的值
     */
    public void setDesignCompleted(java.util.Date designCompleted) {
        this.designCompleted = designCompleted;
    }

    /**
     * <p> 属性：schemeYield的Getter方法. </p>
     * 
     * @return 返回方案产值属性的值
     */
    @Column(name = "SCHEME_YIELD")
    public BigDecimal getSchemeYield() {
        return schemeYield;
    }

    /**
     * <p> 属性方案产值的Setter方法. </p>
     * 
     * @param schemeYield 为属性schemeYield设置的值
     */
    public void setSchemeYield(BigDecimal schemeYield) {
        this.schemeYield = schemeYield;
    }

    /**
     * <p> 属性：receptDeptId的Getter方法. </p>
     * 
     * @return 返回承接部门属性的值
     */
    @Column(name = "RECEPT_DEPT_ID")
    public java.lang.String getReceptDeptId() {
        return receptDeptId;
    }

    /**
     * <p> 属性承接部门的Setter方法. </p>
     * 
     * @param receptDeptId 为属性receptDeptId设置的值
     */
    public void setReceptDeptId(java.lang.String receptDeptId) {
        this.receptDeptId = receptDeptId;
    }

    /**
     * <p> 属性：schemeOverview的Getter方法. </p>
     * 
     * @return 返回概况属性的值
     */
    @Column(name = "SCHEME_OVERVIEW")
    public String getSchemeOverview() {
        return schemeOverview;
    }

    /**
     * <p> 属性概况的Setter方法. </p>
     * 
     * @param schemeOverview 为属性schemeOverview设置的值
     */
    public void setSchemeOverview(String schemeOverview) {
        this.schemeOverview = schemeOverview;
    }

    @Column(name = "PERF_TYPE")
    public java.lang.String getPerfType() {
        return perfType;
    }

    public void setPerfType(java.lang.String perfType) {
        this.perfType = perfType;
    }

    @Column(name = "PERF_YIELD")
    public BigDecimal getPerfYield() {
        return perfYield;
    }

    public void setPerfYield(BigDecimal perfYield) {
        this.perfYield = perfYield;
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

    @Column(name = "PERF_PERIOD")
    public String getPerfPeriod() {
        return perfPeriod;
    }

    public void setPerfPeriod(String perfPeriod) {
        this.perfPeriod = perfPeriod;
    }

    @Transient
    public String getPerfPeriodName() {
        return perfPeriodName;
    }

    public void setPerfPeriodName(String perfPeriodName) {
        this.perfPeriodName = perfPeriodName;
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

    @Transient
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * <p> 属性shemeTeams的Getter方法. </p>
     * 
     * @return 返回shemeTeams属性的值
     */
    @Transient
    public List<WfShemeTeam> getShemeTeams() {
        return shemeTeams;
    }

    /**
     * <p> 属性shemeTeams的Setter方法. </p>
     * 
     * @param shemeTeams 为属性shemeTeams设置的值
     */
    public void setShemeTeams(List<WfShemeTeam> shemeTeams) {
        this.shemeTeams = shemeTeams;
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
        WfPlanScheme obj1 = (WfPlanScheme) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
