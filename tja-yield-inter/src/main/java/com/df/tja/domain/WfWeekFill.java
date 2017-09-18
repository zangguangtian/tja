package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* WfWeekFILL.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
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
 * <p>WfWeekFill </p>
 * 
 * <p>描述：项目周报上报流程表 </p>
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
@Table(name = "WF_WEEK_FILL")
@DynamicInsert(true)
@DynamicUpdate(true)
public class WfWeekFill extends BaseDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -7735861966361198213L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：期间ID */
    private java.lang.String periodId;

    /** 属性：项目ID */
    private java.lang.String proId;

    /** 属性：实际合同额 */
    private BigDecimal contractAmount;

    /** 属性：分包扣减 */
    private BigDecimal pkgAmount;

    /** 属性：方案扣减 */
    private BigDecimal schemeAmount;

    /** 属性：其他扣减 */
    private BigDecimal rebateAmount;

    /** 属性：项目所处状态代码。参考系统配置表 */
    private java.lang.String itemStatus;

    /** 属性：项目所处阶段代码。参考系统配置表 */
    private java.lang.String phaseCode;

    /** 属性：对应阶段启动时间 */
    private java.util.Date phaseStart;

    /** 属性：持续时间，填报的当前时间-对应阶段启动时间的天数 */
    private Integer duration;

    /** 属性：当周进度 */
    private Double weekProgress;

    /** 属性：当周产值 */
    private Double weekYield;

    /** 属性：当周工作及进展情况 */
    private String weekEvolve;

    /** 属性：下阶段工作计划 */
    private String workPlan;

    /** 属性：备案情况 */
    private String filing;

    /** 属性：备案评定。参考系统配置表 */
    private java.lang.String filingEstimate;

    /** 属性：合同收费。参考系统配置表 */
    private java.lang.String feeEstimate;

    /** 属性：运营评定。参考系统配置表 */
    private java.lang.String operationEstimate;

    /** 属性：登记人（申请人） */
    private java.lang.String creator;

    /** 属性：登记时间（申请时间） */
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
     * <p> 属性：periodId的Getter方法. </p>
     * 
     * @return 返回期间ID属性的值
     */
    @Column(name = "PERIOD_ID")
    public java.lang.String getPeriodId() {
        return periodId;
    }

    /**
     * <p> 属性期间ID的Setter方法. </p>
     * 
     * @param periodId 为属性periodId设置的值
     */
    public void setPeriodId(java.lang.String periodId) {
        this.periodId = periodId;
    }

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
     * <p> 属性：contractAmount的Getter方法. </p>
     * 
     * @return 返回实际合同额属性的值
     */
    @Column(name = "CONTRACT_AMOUNT")
    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * <p> 属性实际合同额的Setter方法. </p>
     * 
     * @param contractAmount 为属性contractAmount设置的值
     */
    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * <p> 属性：pkgAmount的Getter方法. </p>
     * 
     * @return 返回分包扣减属性的值
     */
    @Column(name = "PKG_AMOUNT")
    public BigDecimal getPkgAmount() {
        return pkgAmount;
    }

    /**
     * <p> 属性分包扣减的Setter方法. </p>
     * 
     * @param pkgAmount 为属性pkgAmount设置的值
     */
    public void setPkgAmount(BigDecimal pkgAmount) {
        this.pkgAmount = pkgAmount;
    }

    /**
     * <p> 属性：schemeAmount的Getter方法. </p>
     * 
     * @return 返回方案扣减属性的值
     */
    @Column(name = "SCHEME_AMOUNT")
    public BigDecimal getSchemeAmount() {
        return schemeAmount;
    }

    /**
     * <p> 属性方案扣减的Setter方法. </p>
     * 
     * @param schemeAmount 为属性schemeAmount设置的值
     */
    public void setSchemeAmount(BigDecimal schemeAmount) {
        this.schemeAmount = schemeAmount;
    }

    /**
     * <p> 属性：rebateAmount的Getter方法. </p>
     * 
     * @return 返回其他扣减属性的值
     */
    @Column(name = "REBATE_AMOUNT")
    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    /**
     * <p> 属性其他扣减的Setter方法. </p>
     * 
     * @param rebateAmount 为属性rebateAmount设置的值
     */
    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    /**
     * <p> 属性：itemStatus的Getter方法. </p>
     * 
     * @return 返回项目所处状态属性的值
     */
    @Column(name = "ITEM_STATUS")
    public java.lang.String getItemStatus() {
        return itemStatus;
    }

    /**
     * <p> 属性项目所处状态的Setter方法. </p>
     * 
     * @param itemStatus 为属性itemStatus设置的值
     */
    public void setItemStatus(java.lang.String itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * <p> 属性：phaseCode的Getter方法. </p>
     * 
     * @return 返回项目所处阶段属性的值
     */
    @Column(name = "PHASE_CODE")
    public java.lang.String getPhaseCode() {
        return phaseCode;
    }

    /**
     * <p> 属性项目所处阶段的Setter方法. </p>
     * 
     * @param phaseCode 为属性phaseCode设置的值
     */
    public void setPhaseCode(java.lang.String phaseCode) {
        this.phaseCode = phaseCode;
    }

    /**
     * <p> 属性：phaseStart的Getter方法. </p>
     * 
     * @return 返回对应阶段启动时间属性的值
     */
    @Column(name = "PHASE_START")
    public java.util.Date getPhaseStart() {
        return phaseStart;
    }

    /**
     * <p> 属性对应阶段启动时间的Setter方法. </p>
     * 
     * @param phaseStart 为属性phaseStart设置的值
     */
    public void setPhaseStart(java.util.Date phaseStart) {
        this.phaseStart = phaseStart;
    }

    /**
     * <p> 属性：duration的Getter方法. </p>
     * 
     * @return 返回持续时间属性的值
     */
    @Column(name = "DURATION")
    public Integer getDuration() {
        return duration;
    }

    /**
     * <p> 属性持续时间的Setter方法. </p>
     * 
     * @param duration 为属性duration设置的值
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * <p> 属性：weekProgress的Getter方法. </p>
     * 
     * @return 返回当周进度属性的值
     */
    @Column(name = "WEEK_PROGRESS")
    public Double getWeekProgress() {
        return weekProgress;
    }

    /**
     * <p> 属性当周进度的Setter方法. </p>
     * 
     * @param weekProgress 为属性weekProgress设置的值
     */
    public void setWeekProgress(Double weekProgress) {
        this.weekProgress = weekProgress;
    }

    /**
     * <p> 属性：weekYield的Getter方法. </p>
     * 
     * @return 返回当周产值属性的值
     */
    @Column(name = "WEEK_YIELD")
    public Double getWeekYield() {
        return weekYield;
    }

    /**
     * <p> 属性当周产值的Setter方法. </p>
     * 
     * @param weekYield 为属性weekYield设置的值
     */
    public void setWeekYield(Double weekYield) {
        this.weekYield = weekYield;
    }

    /**
     * <p> 属性：weekEvolve的Getter方法. </p>
     * 
     * @return 返回当周工作及进展情况属性的值
     */
    @Column(name = "WEEK_EVOLVE")
    public String getWeekEvolve() {
        return weekEvolve;
    }

    /**
     * <p> 属性当周工作及进展情况的Setter方法. </p>
     * 
     * @param weekEvolve 为属性weekEvolve设置的值
     */
    public void setWeekEvolve(String weekEvolve) {
        this.weekEvolve = weekEvolve;
    }

    /**
     * <p> 属性：workPlan的Getter方法. </p>
     * 
     * @return 返回下阶段工作计划属性的值
     */
    @Column(name = "WORK_PLAN")
    public String getWorkPlan() {
        return workPlan;
    }

    /**
     * <p> 属性下阶段工作计划的Setter方法. </p>
     * 
     * @param workPlan 为属性workPlan设置的值
     */
    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
    }

    /**
     * <p> 属性：filing的Getter方法. </p>
     * 
     * @return 返回备案情况属性的值
     */
    @Column(name = "FILING")
    public String getFiling() {
        return filing;
    }

    /**
     * <p> 属性备案情况的Setter方法. </p>
     * 
     * @param filing 为属性filing设置的值
     */
    public void setFiling(String filing) {
        this.filing = filing;
    }

    /**
     * <p> 属性：filingEstimate的Getter方法. </p>
     * 
     * @return 返回备案评定属性的值
     */
    @Column(name = "FILING_ESTIMATE")
    public java.lang.String getFilingEstimate() {
        return filingEstimate;
    }

    /**
     * <p> 属性备案评定的Setter方法. </p>
     * 
     * @param filingEstimate 为属性filingEstimate设置的值
     */
    public void setFilingEstimate(java.lang.String filingEstimate) {
        this.filingEstimate = filingEstimate;
    }

    /**
     * <p> 属性：feeEstimate的Getter方法. </p>
     * 
     * @return 返回合同收费属性的值
     */
    @Column(name = "FEE_ESTIMATE")
    public java.lang.String getFeeEstimate() {
        return feeEstimate;
    }

    /**
     * <p> 属性合同收费的Setter方法. </p>
     * 
     * @param feeEstimate 为属性feeEstimate设置的值
     */
    public void setFeeEstimate(java.lang.String feeEstimate) {
        this.feeEstimate = feeEstimate;
    }

    /**
     * <p> 属性：operationEstimate的Getter方法. </p>
     * 
     * @return 返回运营评定属性的值
     */
    @Column(name = "OPERATION_ESTIMATE")
    public java.lang.String getOperationEstimate() {
        return operationEstimate;
    }

    /**
     * <p> 属性运营评定的Setter方法. </p>
     * 
     * @param operationEstimate 为属性operationEstimate设置的值
     */
    public void setOperationEstimate(java.lang.String operationEstimate) {
        this.operationEstimate = operationEstimate;
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
        WfWeekFill obj1 = (WfWeekFill) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}