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

import java.math.BigDecimal;
import java.util.Map;

import com.df.framework.util.ArithmeticUtil;
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

    /** 属性：类型名称 */
    private java.lang.String typeCode;

    /** 属性：类型名称 */
    private java.lang.String typeName;

    /** 属性：专业比例 */
    private java.lang.String ratioJson;

    /** 专业比例集合,key为CustOcYieldMajor+OcYieldMajorRatio.majorCode*/
    private Map<String, OcYieldMajorRatio> majorMap;

    /**
     * @return typeCode
     */
    public java.lang.String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode 要设置的 typeCode
     */
    public void setTypeCode(java.lang.String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @return typeName
     */
    public java.lang.String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName 要设置的 typeName
     */
    public void setTypeName(java.lang.String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return ratioJson
     */
    public java.lang.String getRatioJson() {
        return ratioJson;
    }

    /**
     * @param ratioJson 要设置的 ratioJson
     */
    public void setRatioJson(java.lang.String ratioJson) {
        this.ratioJson = ratioJson;
    }

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

    /**
     * 
     * 计算土建基准产值
     */
    public BigDecimal getStandardYield() {
        BigDecimal standardYield = new BigDecimal(0);
        if (getStandardPrice() != null && getBuildArea() != null) {
            standardYield = ArithmeticUtil.multMul(getStandardPrice(), getBuildArea());
            standardYield = ArithmeticUtil.round(standardYield, 2);
        }
        return standardYield;
    }
}
