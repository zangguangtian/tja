/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * YieldSettleMajorModel.java
 * 
 * 2017年10月26日-上午10:56:19
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldMajorRoleRate;

/**
 * <p>YieldSettleMajorModel</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月26日 上午10:56:19</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public class YieldSettleMajorModel implements Serializable {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 6726438926074780927L;

    private java.lang.String majorCode;

    private java.lang.String majorName;

    /**项目上的专业分配比例*/
    private BigDecimal majorAllotRate;

    //各专业负责人审批   年度产值专业角色结算比例
    private List<WfYieldMajorRoleRate> majorRoleRates;

    //年度产值专业角色人员
    private List<WfYieldMajorRoleAllot> majorRoleAllots;

    public java.lang.String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(java.lang.String majorCode) {
        this.majorCode = majorCode;
    }

    public BigDecimal getMajorAllotRate() {
        return majorAllotRate;
    }

    public void setMajorAllotRate(BigDecimal majorAllotRate) {
        this.majorAllotRate = majorAllotRate;
    }

    public java.lang.String getMajorName() {
        return majorName;
    }

    public void setMajorName(java.lang.String majorName) {
        this.majorName = majorName;
    }

    public List<WfYieldMajorRoleRate> getMajorRoleRates() {
        return majorRoleRates;
    }

    public void setMajorRoleRates(List<WfYieldMajorRoleRate> majorRoleRates) {
        this.majorRoleRates = majorRoleRates;
    }

    public List<WfYieldMajorRoleAllot> getMajorRoleAllots() {
        return majorRoleAllots;
    }

    public void setMajorRoleAllots(List<WfYieldMajorRoleAllot> majorRoleAllots) {
        this.majorRoleAllots = majorRoleAllots;
    }
}
