package com.df.tja.domain.cust;

import java.math.BigDecimal;
import java.util.List;

import com.df.framework.base.domain.SuperDomain;
import com.df.tja.domain.OcSchemeDivisor;

public class CustSchemeMajorNode extends SuperDomain {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = -2566751601093380828L;

    /** 属性：专业ID*/
    private String majorId;

    /** 属性：子项ID*/
    private String subId;

    /** 属性：任务ID*/
    private String taskId;

    /** 属性：节点类型*/
    private String nodeCategory;

    /** 属性：子项排序号*/
    private Integer subSort;

    /** 属性：任务排序号*/
    private Integer taskSort;

    /** 属性：用户排序号*/
    private Integer userSort;

    /** 属性：节点因子名称*/
    private String divisorName;

    /** 属性：策划比例*/
    private BigDecimal schemeRatio;

    /** 添加用户因子*/
    private List<OcSchemeDivisor> userDivisors;

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
     * <p> 属性taskId的Getter方法. </p>
     * 
     * @return 返回taskId属性的值
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * <p> 属性taskId的Setter方法. </p>
     * 
     * @param taskId 为属性taskId设置的值
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * <p> 属性nodeCategory的Getter方法. </p>
     * 
     * @return 返回nodeCategory属性的值
     */
    public String getNodeCategory() {
        return nodeCategory;
    }

    /**
     * <p> 属性nodeCategory的Setter方法. </p>
     * 
     * @param nodeCategory 为属性nodeCategory设置的值
     */
    public void setNodeCategory(String nodeCategory) {
        this.nodeCategory = nodeCategory;
    }

    /**
     * <p> 属性subSort的Getter方法. </p>
     * 
     * @return 返回subSort属性的值
     */
    public Integer getSubSort() {
        return subSort;
    }

    /**
     * <p> 属性subSort的Setter方法. </p>
     * 
     * @param subSort 为属性subSort设置的值
     */
    public void setSubSort(Integer subSort) {
        this.subSort = subSort;
    }

    /**
     * <p> 属性taskSort的Getter方法. </p>
     * 
     * @return 返回taskSort属性的值
     */
    public Integer getTaskSort() {
        return taskSort;
    }

    /**
     * <p> 属性taskSort的Setter方法. </p>
     * 
     * @param taskSort 为属性taskSort设置的值
     */
    public void setTaskSort(Integer taskSort) {
        this.taskSort = taskSort;
    }

    /**
     * <p> 属性userSort的Getter方法. </p>
     * 
     * @return 返回userSort属性的值
     */
    public Integer getUserSort() {
        return userSort;
    }

    /**
     * <p> 属性userSort的Setter方法. </p>
     * 
     * @param userSort 为属性userSort设置的值
     */
    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
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

    /**
     * <p> 属性userDivisors的Getter方法. </p>
     * 
     * @return 返回userDivisors属性的值
     */
    public List<OcSchemeDivisor> getUserDivisors() {
        return userDivisors;
    }

    /**
     * <p> 属性userDivisors的Setter方法. </p>
     * 
     * @param userDivisors 为属性userDivisors设置的值
     */
    public void setUserDivisors(List<OcSchemeDivisor> userDivisors) {
        this.userDivisors = userDivisors;
    }

}
