/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * CustYieldSettle.java
 * 
 * 2017年11月9日-上午11:36:36
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import com.df.tja.domain.WfYieldSettle;

/**
 * <p>CustYieldSettle</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月9日 上午11:36:36</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public class CustYieldSettle extends WfYieldSettle {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 5384003591916543149L;

    /** 属性：项目编号 */
    private java.lang.String proCode;

    /** 属性：项目名称 */
    private java.lang.String proName;

    private java.lang.String syId;

    private java.lang.String settleCode;

    public java.lang.String getProCode() {
        return proCode;
    }

    public void setProCode(java.lang.String proCode) {
        this.proCode = proCode;
    }

    public java.lang.String getProName() {
        return proName;
    }

    public void setProName(java.lang.String proName) {
        this.proName = proName;
    }

    public java.lang.String getSyId() {
        return syId;
    }

    public void setSyId(java.lang.String syId) {
        this.syId = syId;
    }

    public java.lang.String getSettleCode() {
        return settleCode;
    }

    public void setSettleCode(java.lang.String settleCode) {
        this.settleCode = settleCode;
    }
}
