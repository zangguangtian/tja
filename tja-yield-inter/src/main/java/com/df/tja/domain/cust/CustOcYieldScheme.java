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

    private List<CustOcYieldMajor> yieldMajors;
}
