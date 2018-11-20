/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IOcDesignScheduleService.java
 * 
 * 2018年11月20日-下午2:44:42
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.service;

import java.util.List;

import com.df.tja.domain.cust.CustOcDesignSchedule;

/**
 * <p>IOcDesignScheduleService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月20日 下午2:44:42</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public interface IOcDesignScheduleService {

    /**
     * 
     * <p>描述 : 通过专业ID，查询完整模式的进展填报列表</p>
     *
     * @param phaseId
     * @return
     */
    List<CustOcDesignSchedule> queryDesignSchedulesById(String phaseId);
}
