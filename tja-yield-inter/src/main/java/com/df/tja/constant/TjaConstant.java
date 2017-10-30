/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.constant
 *
 * TjaConstant.java
 * 
 * 2017年9月28日-上午10:36:58
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.constant;

/**
 * <p>TjaConstant</p>
 * 
 * <p>描述：TJA项目常量类</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月28日 上午10:36:58</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public abstract class TjaConstant {

    /**
     * 
     * <p>SysCode</p>
     * 
     * <p>描述：</p>
     *
     * <p>备注：</p>
     * 
     * <p>2017年9月28日 上午10:39:43</p>
     *
     * @author wang.changjiu
     * 
     * @version 1.0.0
     *
     */
    public static class SysCode {

        public static final String PM_MAJOR_PARENT_CODE = "PM.MAJOR";

        /**
         * 项目负责人角色代码
         */
        public static final String STAFF_CATEGORY_LEADER = "PM.TEAM.ROLE.LEADER";

        /**
         * 项目经理角色代码
         */
        public static final String STAFF_CATEGORY_PM = "PM.TEAM.ROLE.PM";
    }

    /**
     * 
     * <p>FlowTaskRole</p>
     * 
     * <p>描述：</p>
     *
     * <p>备注：</p>
     * 
     * <p>2017年9月26日 下午4:49:23</p>
     *
     * @author wang.changjiu
     * 
     * @version 1.0.0
     *
     */
    public static class FlowTaskRole {
        /**运营部角色代码 */
        public static final String YUNYING = "ROLE_YunYingApprove";

    }

    public static class WriteBackOpType {

        /**
         * 运营相关回写类型
         */
        public static final String[] OCWRITEBACKTYPE = {"方案产值策划"};

    }

    /**
     *业务回写函数  
     */
    public static class WriteBackFunc {

        /**
         * 流程回写函数
         */
        public static final String WF_FUNC = "usp_WF_WriteBack";

    }

}
