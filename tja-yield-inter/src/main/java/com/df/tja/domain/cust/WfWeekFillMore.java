/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * WfWeekFillMore.java
 * 
 * 2017年9月29日-下午2:26:45
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.util.Date;

import com.df.tja.domain.WfWeekFill;

/** *
 * <p>WfWeekFillMore</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月29日 下午2:26:45</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public class WfWeekFillMore extends WfWeekFill {

    private static final long serialVersionUID = 1L;

    /** 项目编号 */
    private String proCode;
    /** 项目名称 */
    private String proName;
    /** 项目类型 */
    private String proType;
    /** 项目级别 */
    private String proGrade;
    /** 所处状态 */
    private String proStatus;
    /** 项目负责人 名称 */
    private String pmLeaders;
    /** 项目经理 名称 */
    private String pManagers;
    /** 期间名称 */
    private String periodName;
    /** 期间范围起 */
    private Date rangeStart;
    /** 期间范围止 */
    private Date rangeEnd;

    /**
     * <p> 属性proCode的Getter方法. </p>
     * 
     * @return 返回proCode属性的值
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p> 属性proCode的Setter方法. </p>
     * 
     * @param proCode 为属性proCode设置的值
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p> 属性proName的Getter方法. </p>
     * 
     * @return 返回proName属性的值
     */
    public String getProName() {
        return proName;
    }

    /**
     * <p> 属性proName的Setter方法. </p>
     * 
     * @param proName 为属性proName设置的值
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * <p> 属性proType的Getter方法. </p>
     * 
     * @return 返回proType属性的值
     */
    public String getProType() {
        return proType;
    }

    /**
     * <p> 属性proType的Setter方法. </p>
     * 
     * @param proType 为属性proType设置的值
     */
    public void setProType(String proType) {
        this.proType = proType;
    }

    /**
     * <p> 属性proGrade的Getter方法. </p>
     * 
     * @return 返回proGrade属性的值
     */
    public String getProGrade() {
        return proGrade;
    }

    /**
     * <p> 属性proGrade的Setter方法. </p>
     * 
     * @param proGrade 为属性proGrade设置的值
     */
    public void setProGrade(String proGrade) {
        this.proGrade = proGrade;
    }

    /**
     * <p> 属性proStatus的Getter方法. </p>
     * 
     * @return 返回proStatus属性的值
     */
    public String getProStatus() {
        return proStatus;
    }

    /**
     * <p> 属性proStatus的Setter方法. </p>
     * 
     * @param proStatus 为属性proStatus设置的值
     */
    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    /**
     * <p> 属性pmLeaders的Getter方法. </p>
     * 
     * @return 返回pmLeaders属性的值
     */
    public String getPmLeaders() {
        return pmLeaders;
    }

    /**
     * <p> 属性pmLeaders的Setter方法. </p>
     * 
     * @param pmLeaders 为属性pmLeaders设置的值
     */
    public void setPmLeaders(String pmLeaders) {
        this.pmLeaders = pmLeaders;
    }

    /**
     * <p> 属性pManagers的Getter方法. </p>
     * 
     * @return 返回pManagers属性的值
     */
    public String getpManagers() {
        return pManagers;
    }

    /**
     * <p> 属性pManagers的Setter方法. </p>
     * 
     * @param pManagers 为属性pManagers设置的值
     */
    public void setpManagers(String pManagers) {
        this.pManagers = pManagers;
    }

    /**
     * <p> 属性periodName的Getter方法. </p>
     * 
     * @return 返回periodName属性的值
     */
    public String getPeriodName() {
        return periodName;
    }

    /**
     * <p> 属性periodName的Setter方法. </p>
     * 
     * @param periodName 为属性periodName设置的值
     */
    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    /**
     * <p> 属性rangeStart的Getter方法. </p>
     * 
     * @return 返回rangeStart属性的值
     */
    public Date getRangeStart() {
        return rangeStart;
    }

    /**
     * <p> 属性rangeStart的Setter方法. </p>
     * 
     * @param rangeStart 为属性rangeStart设置的值
     */
    public void setRangeStart(Date rangeStart) {
        this.rangeStart = rangeStart;
    }

    /**
     * <p> 属性rangeEnd的Getter方法. </p>
     * 
     * @return 返回rangeEnd属性的值
     */
    public Date getRangeEnd() {
        return rangeEnd;
    }

    /**
     * <p> 属性rangeEnd的Setter方法. </p>
     * 
     * @param rangeEnd 为属性rangeEnd设置的值
     */
    public void setRangeEnd(Date rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

}
