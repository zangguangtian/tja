/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * CustOcYieldScheme.java
 * 
 * 2017年10月24日-上午10:23:13
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain.cust;

import java.util.List;

import com.df.tja.domain.OcYieldScheme;

/**
 * <p>CustOcYieldScheme</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月24日 上午10:23:13</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public class CustOcYieldScheme extends OcYieldScheme {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -8625746507653337130L;

    /** 属性：专业比例集合 */
    private List<CustOcYieldMajor> yieldMajors;

    /** 属性：专业负责人集合 */
    private List<CustOcYieldMajorDuty> yieldMajorDuties;

    /** 属性：阶段专业产值集合 */
    private List<CustOcYieldStageMajor> yieldStageMajors;

    /**
     * @return yieldMajors
     */
    public List<CustOcYieldMajor> getYieldMajors() {
        return yieldMajors;
    }

    /**
     * @param yieldMajors 要设置的 yieldMajors
     */
    public void setYieldMajors(List<CustOcYieldMajor> yieldMajors) {
        this.yieldMajors = yieldMajors;
    }

    /**
     * @return yieldMajorDuties
     */
    public List<CustOcYieldMajorDuty> getYieldMajorDuties() {
        return yieldMajorDuties;
    }

    /**
     * @param yieldMajorDuties 要设置的 yieldMajorDuties
     */
    public void setYieldMajorDuties(List<CustOcYieldMajorDuty> yieldMajorDuties) {
        this.yieldMajorDuties = yieldMajorDuties;
    }

    /**
     * @return yieldStageMajors
     */
    public List<CustOcYieldStageMajor> getYieldStageMajors() {
        return yieldStageMajors;
    }

    /**
     * @param yieldStageMajors 要设置的 yieldStageMajors
     */
    public void setYieldStageMajors(List<CustOcYieldStageMajor> yieldStageMajors) {
        this.yieldStageMajors = yieldStageMajors;
    }

}
