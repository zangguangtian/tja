/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * YieldDeptDetial.java
 * 
 * 2017年11月21日-下午5:14:08
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>YieldDeptDetial</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月21日 下午5:14:08</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public class YieldDeptDetial implements Serializable {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 4037901226703665038L;

    private String proId;

    private String staffId;

    private String name;

    private BigDecimal staffYield;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStaffYield() {
        return staffYield;
    }

    public void setStaffYield(BigDecimal staffYield) {
        this.staffYield = staffYield;
    }
}
