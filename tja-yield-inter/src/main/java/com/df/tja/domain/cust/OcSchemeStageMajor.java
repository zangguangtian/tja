package com.df.tja.domain.cust;

import com.df.framework.base.domain.SuperDomain;

/**
 * 
 * <p>OcSchemeStageMajor</p>
 * 
 * <p>描述：项目策划的阶段专业信息</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月12日 上午11:08:15</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 *
 */
public class OcSchemeStageMajor extends SuperDomain {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 2093278580624799478L;

    /** 属性：策划ID*/
    private String schemeId;

    /** 属性：项目WBS*/
    private String proWbs;

    /** 属性：项目ID*/
    private String proId;

    /** 属性：项目代码*/
    private String proCode;

    /** 属性：项目免*/
    private String proName;

    /** 属性：策划阶段ID*/
    private String schemeStageId;

    /** 属性：策划阶段名称*/
    private String schemeStageName;

    /** 属性：策划阶段比例*/
    private String schemeStageRatio;

    /** 属性：策划阶段下面的专业数量 */
    private String schemeStageCount;

    /** 属性：策划专业ID*/
    private String schemeMajorId;

    /** 属性：策划专业名称*/
    private String schemeMajorName;

    /** 属性：策划专业比例*/
    private String schemeMajorRatio;

    /**
     * <p> 属性schemeId的Getter方法. </p>
     * 
     * @return 返回schemeId属性的值
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * <p> 属性schemeId的Setter方法. </p>
     * 
     * @param schemeId 为属性schemeId设置的值
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    /**
     * <p> 属性proWbs的Getter方法. </p>
     * 
     * @return 返回proWbs属性的值
     */
    public String getProWbs() {
        return proWbs;
    }

    /**
     * <p> 属性proWbs的Setter方法. </p>
     * 
     * @param proWbs 为属性proWbs设置的值
     */
    public void setProWbs(String proWbs) {
        this.proWbs = proWbs;
    }

    /**
     * <p> 属性proId的Getter方法. </p>
     * 
     * @return 返回proId属性的值
     */
    public String getProId() {
        return proId;
    }

    /**
     * <p> 属性proId的Setter方法. </p>
     * 
     * @param proId 为属性proId设置的值
     */
    public void setProId(String proId) {
        this.proId = proId;
    }

    /**
     * <p> 属性proCode的Getter方法. </p>
     * 
     * @return 返回proCode属性的值
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * <p> 属性proCode的Setter方法. </p>
     * 
     * @param proCode 为属性proCode设置的值
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * <p> 属性proName的Getter方法. </p>
     * 
     * @return 返回proName属性的值
     */
    public String getProName() {
        return proName;
    }

    /**
     * <p> 属性proName的Setter方法. </p>
     * 
     * @param proName 为属性proName设置的值
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * <p> 属性schemeStageId的Getter方法. </p>
     * 
     * @return 返回schemeStageId属性的值
     */
    public String getSchemeStageId() {
        return schemeStageId;
    }

    /**
     * <p> 属性schemeStageId的Setter方法. </p>
     * 
     * @param schemeStageId 为属性schemeStageId设置的值
     */
    public void setSchemeStageId(String schemeStageId) {
        this.schemeStageId = schemeStageId;
    }

    /**
     * <p> 属性schemeStageName的Getter方法. </p>
     * 
     * @return 返回schemeStageName属性的值
     */
    public String getSchemeStageName() {
        return schemeStageName;
    }

    /**
     * <p> 属性schemeStageName的Setter方法. </p>
     * 
     * @param schemeStageName 为属性schemeStageName设置的值
     */
    public void setSchemeStageName(String schemeStageName) {
        this.schemeStageName = schemeStageName;
    }

    /**
     * <p> 属性schemeStageRatio的Getter方法. </p>
     * 
     * @return 返回schemeStageRatio属性的值
     */
    public String getSchemeStageRatio() {
        return schemeStageRatio;
    }

    /**
     * <p> 属性schemeStageRatio的Setter方法. </p>
     * 
     * @param schemeStageRatio 为属性schemeStageRatio设置的值
     */
    public void setSchemeStageRatio(String schemeStageRatio) {
        this.schemeStageRatio = schemeStageRatio;
    }

    /**
     * <p> 属性schemeStageCount的Getter方法. </p>
     * 
     * @return 返回schemeStageCount属性的值
     */
    public String getSchemeStageCount() {
        return schemeStageCount;
    }

    /**
     * <p> 属性schemeStageCount的Setter方法. </p>
     * 
     * @param schemeStageCount 为属性schemeStageCount设置的值
     */
    public void setSchemeStageCount(String schemeStageCount) {
        this.schemeStageCount = schemeStageCount;
    }

    /**
     * <p> 属性schemeMajorId的Getter方法. </p>
     * 
     * @return 返回schemeMajorId属性的值
     */
    public String getSchemeMajorId() {
        return schemeMajorId;
    }

    /**
     * <p> 属性schemeMajorId的Setter方法. </p>
     * 
     * @param schemeMajorId 为属性schemeMajorId设置的值
     */
    public void setSchemeMajorId(String schemeMajorId) {
        this.schemeMajorId = schemeMajorId;
    }

    /**
     * <p> 属性schemeMajorName的Getter方法. </p>
     * 
     * @return 返回schemeMajorName属性的值
     */
    public String getSchemeMajorName() {
        return schemeMajorName;
    }

    /**
     * <p> 属性schemeMajorName的Setter方法. </p>
     * 
     * @param schemeMajorName 为属性schemeMajorName设置的值
     */
    public void setSchemeMajorName(String schemeMajorName) {
        this.schemeMajorName = schemeMajorName;
    }

    /**
     * <p> 属性schemeMajorRatio的Getter方法. </p>
     * 
     * @return 返回schemeMajorRatio属性的值
     */
    public String getSchemeMajorRatio() {
        return schemeMajorRatio;
    }

    /**
     * <p> 属性schemeMajorRatio的Setter方法. </p>
     * 
     * @param schemeMajorRatio 为属性schemeMajorRatio设置的值
     */
    public void setSchemeMajorRatio(String schemeMajorRatio) {
        this.schemeMajorRatio = schemeMajorRatio;
    }

}
