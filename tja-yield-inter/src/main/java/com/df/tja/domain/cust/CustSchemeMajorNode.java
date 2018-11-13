package com.df.tja.domain.cust;

import java.math.BigDecimal;

import com.df.framework.base.domain.SuperDomain;

public class CustSchemeMajorNode extends SuperDomain {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -2566751601093380828L;

    /** 属性：专业ID*/
    private String majorId;

    /** 属性：子项ID*/
    private String subId;

    /** 属性：节点类型*/
    private String nodeType;

    /** 属性：节点因子名称*/
    private String divisorName;

    /** 属性：策划比例*/
    private BigDecimal schemeRatio;

    /**
     * <p> 属性majorId的Getter方法. </p>
     * 
     * @return 返回majorId属性的值
     */
    public String getMajorId() {
        return majorId;
    }

    /**
     * <p> 属性majorId的Setter方法. </p>
     * 
     * @param majorId 为属性majorId设置的值
     */
    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    /**
     * <p> 属性subId的Getter方法. </p>
     * 
     * @return 返回subId属性的值
     */
    public String getSubId() {
        return subId;
    }

    /**
     * <p> 属性subId的Setter方法. </p>
     * 
     * @param subId 为属性subId设置的值
     */
    public void setSubId(String subId) {
        this.subId = subId;
    }

    /**
     * <p> 属性nodeType的Getter方法. </p>
     * 
     * @return 返回nodeType属性的值
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * <p> 属性nodeType的Setter方法. </p>
     * 
     * @param nodeType 为属性nodeType设置的值
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * <p> 属性divisorName的Getter方法. </p>
     * 
     * @return 返回divisorName属性的值
     */
    public String getDivisorName() {
        return divisorName;
    }

    /**
     * <p> 属性divisorName的Setter方法. </p>
     * 
     * @param divisorName 为属性divisorName设置的值
     */
    public void setDivisorName(String divisorName) {
        this.divisorName = divisorName;
    }

    /**
     * <p> 属性schemeRatio的Getter方法. </p>
     * 
     * @return 返回schemeRatio属性的值
     */
    public BigDecimal getSchemeRatio() {
        return schemeRatio;
    }

    /**
     * <p> 属性schemeRatio的Setter方法. </p>
     * 
     * @param schemeRatio 为属性schemeRatio设置的值
     */
    public void setSchemeRatio(BigDecimal schemeRatio) {
        this.schemeRatio = schemeRatio;
    }

}
