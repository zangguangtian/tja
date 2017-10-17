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
    }

    public static class WriteStoreProce {

        public static final String USP_SYS_SCHEDULED_TASK_01 = "usp_SYS_Scheduled_Task_01";

        public static final String USP_SYS_SCHEDULED_TASK_02 = "usp_SYS_Scheduled_Task_02";

        public static final String USP_SYS_SCHEDULED_TASK_03 = "usp_SYS_Scheduled_Task_03";

        public static final String USP_SYS_DB_FULLBACKUP = "usp_SYS_DB_FullBackup";

        public static final String USP_SYS_DB_DIFFBACKUP = "usp_SYS_DB_DiffBackup";

        public static final String USP_SYS_DATA_UPDATE = "usp_SYS_Data_Update";

        public static final String USP_SYS_DATA_CORRECT = "usp_SYS_Data_Correct";

        public static final String USP_SYS_DATA_ALERT = "usp_SYS_Data_Alert";
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
