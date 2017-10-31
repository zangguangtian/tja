/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * WfYieldSettleModel.java
 * 
 * 2017年10月19日-下午4:01:04
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.io.Serializable;
import java.util.List;

import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldMajorRoleRate;
import com.df.tja.domain.WfYieldPrincipalAllot;
import com.df.tja.domain.WfYieldSettle;

/**
 * <p>WfYieldSettleModel</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月19日 下午4:01:04</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public class WfYieldSettleModel implements Serializable {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 1796661955804585114L;

    private WfYieldSettle wfYieldSettle;

    private List<WfYieldMajorRoleAllot> majorRoleAllots;

    private List<WfYieldMajorRate> majorRates;

    private List<WfYieldMajorRoleRate> majorRoleRates;

    private List<WfYieldPrincipalAllot> principalAllots;

    private Integer tabs;

    public WfYieldSettle getWfYieldSettle() {
        return wfYieldSettle;
    }

    public void setWfYieldSettle(WfYieldSettle wfYieldSettle) {
        this.wfYieldSettle = wfYieldSettle;
    }

    public List<WfYieldMajorRoleAllot> getMajorRoleAllots() {
        return majorRoleAllots;
    }

    public void setMajorRoleAllots(List<WfYieldMajorRoleAllot> majorRoleAllots) {
        this.majorRoleAllots = majorRoleAllots;
    }

    public List<WfYieldMajorRate> getMajorRates() {
        return majorRates;
    }

    public void setMajorRates(List<WfYieldMajorRate> majorRates) {
        this.majorRates = majorRates;
    }

    public List<WfYieldMajorRoleRate> getMajorRoleRates() {
        return majorRoleRates;
    }

    public void setMajorRoleRates(List<WfYieldMajorRoleRate> majorRoleRates) {
        this.majorRoleRates = majorRoleRates;
    }

    public List<WfYieldPrincipalAllot> getPrincipalAllots() {
        return principalAllots;
    }

    public void setPrincipalAllots(List<WfYieldPrincipalAllot> principalAllots) {
        this.principalAllots = principalAllots;
    }

    public Integer getTabs() {
        return tabs;
    }

    public void setTabs(Integer tabs) {
        this.tabs = tabs;
    }
}
