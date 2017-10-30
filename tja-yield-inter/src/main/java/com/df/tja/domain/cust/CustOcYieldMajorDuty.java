/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * CustOcYieldMajorDuty.java
 * 
 * 2017年10月27日-上午10:09:22
 *
 * 2017 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.domain.cust;

import com.df.tja.domain.OcYieldMajorDuty;

/**
 * @author TabZhu
 *
 */
public class CustOcYieldMajorDuty extends OcYieldMajorDuty {

    private static final long serialVersionUID = -3821728354067519048L;

    /** 属性：专业负责人姓名 */
    private java.lang.String principalName;

    /**
     * @return principalName
     */
    public java.lang.String getPrincipalName() {
        return principalName;
    }

    /**
     * @param principalName 要设置的 principalName
     */
    public void setPrincipalName(java.lang.String principalName) {
        this.principalName = principalName;
    }

}
