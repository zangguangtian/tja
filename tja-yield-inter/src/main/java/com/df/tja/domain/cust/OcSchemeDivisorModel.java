package com.df.tja.domain.cust;

import com.df.tja.domain.OcSchemeDivisor;

/**
 * <p>OcSchemeDivisorModel </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-12 19:54</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public class OcSchemeDivisorModel extends OcSchemeDivisor {

    /**
     *  serialVersionUID
     */
    private static final long serialVersionUID = 7170268593657640411L;

    /**
     * 项目角色
     */
    private String configName;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 任职部门
     */
    private String orgName;

    /**
     * <p> 属性：configName的Getter方法. </p>
     *
     * @return 返回项目角色属性的值
     */
    public String getConfigName() {
        return configName;
    }

    /**
    * <p> 属性项目角色的Setter方法. </p>
    *
    * @param configName 为属性configName设置的值
    */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * <p> 属性：staffName的Getter方法. </p>
     *
     * @return 返回姓名属性的值
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * <p> 属性姓名的Setter方法. </p>
     *
     * @param staffName 为属性staffName设置的值
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * <p> 属性：orgName的Getter方法. </p>
     *
     * @return 返回任职部门属性的值
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * <p> 属性任职部门的Setter方法. </p>
     *
     * @param orgName 为属性orgName设置的值
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
