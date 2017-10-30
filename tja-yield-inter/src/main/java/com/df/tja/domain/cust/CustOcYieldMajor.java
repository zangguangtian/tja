/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * CustOcYieldMajor.java
 * 
 * 2017年10月24日-上午10:24:19
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain.cust;

import java.util.Map;

import com.df.tja.domain.OcYieldMajor;
import com.df.tja.domain.OcYieldMajorRatio;

/**
 * <p>CustOcYieldMajor</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月24日 上午10:24:19</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public class CustOcYieldMajor extends OcYieldMajor {

    private static final long serialVersionUID = 5224723686322921079L;

    /** 专业比例集合,key为CustOcYieldMajor+OcYieldMajorRatio.majorCode*/
    private Map<String, OcYieldMajorRatio> majorMap;

    /**
     * @return majorMap
     */
    public Map<String, OcYieldMajorRatio> getMajorMap() {
        return majorMap;
    }

    /**
     * @param majorMap 要设置的 majorMap
     */
    public void setMajorMap(Map<String, OcYieldMajorRatio> majorMap) {
        this.majorMap = majorMap;
    }

}
