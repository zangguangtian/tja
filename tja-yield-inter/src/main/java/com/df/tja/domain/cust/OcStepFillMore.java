package com.df.tja.domain.cust;

import com.df.tja.domain.OcScheduleFill;
import com.df.tja.domain.OcStepFill;

import java.util.Date;

/**
 * <p>OcStepFillMore </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-23 14:25</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public class OcStepFillMore extends OcStepFill{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8340562432488817496L;

    /** 属性：分项名称 */
    private String proName;

    /** 属性：分项状态 */
    private String proStatusName;

    /** 属性：开始时间 */
    private java.util.Date weekStart;

    /** 属性：结束时间 */
    private java.util.Date weekEnd;

    /** 属性：合并数 */
    private Integer mergeCount;

    /**
     * <p> 属性：mergeCount的Getter方法. </p>
     *
     * @return 返回合并数属性的值
     */
    public Integer getMergeCount() {
        return mergeCount;
    }

    /**
    * <p> 属性合并数的Setter方法. </p>
    *
    * @param mergeCount 为属性mergeCount设置的值
    */
    public void setMergeCount(Integer mergeCount) {
        this.mergeCount = mergeCount;
    }

    /**
     * <p> 属性：proName的Getter方法. </p>
     *
     * @return 返回分项名称属性的值
     */
    public String getProName() {
        return proName;
    }

    /**
     * <p> 属性分项名称的Setter方法. </p>
     *
     * @param proName 为属性proName设置的值
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * <p> 属性：proStatusName的Getter方法. </p>
     *
     * @return 返回分项状态属性的值
     */
    public String getProStatusName() {
        return proStatusName;
    }

    /**
     * <p> 属性分项状态的Setter方法. </p>
     *
     * @param proStatusName 为属性proStatusName设置的值
     */
    public void setProStatusName(String proStatusName) {
        this.proStatusName = proStatusName;
    }

    /**
     * <p> 属性：weekStart的Getter方法. </p>
     *
     * @return 返回开始时间属性的值
     */
    public Date getWeekStart() {
        return weekStart;
    }

    /**
     * <p> 属性开始时间的Setter方法. </p>
     *
     * @param weekStart 为属性weekStart设置的值
     */
    public void setWeekStart(Date weekStart) {
        this.weekStart = weekStart;
    }

    /**
     * <p> 属性：weekEnd的Getter方法. </p>
     *
     * @return 返回结束时间属性的值
     */
    public Date getWeekEnd() {
        return weekEnd;
    }

    /**
    * <p> 属性结束时间的Setter方法. </p>
    *
    * @param weekEnd 为属性weekEnd设置的值
    */
    public void setWeekEnd(Date weekEnd) {
        this.weekEnd = weekEnd;
    }
}
