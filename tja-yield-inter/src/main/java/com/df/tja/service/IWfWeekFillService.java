/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * WfWeekFillService.java
 * 
 * 2017年9月18日-下午3:43:18
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.List;

import com.df.activiti.domain.ProcessArgs;
import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.tja.domain.WfWeekFill;
import com.df.tja.domain.cust.WfWeekFillMore;

/**
 * <p>IWfWeekFillService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:43:18</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public interface IWfWeekFillService extends IBaseService {

    /**
     * <p>描述 :查询 周报详情 </p>
     * 
     * @param id 
     * @return 
     * */
    WfWeekFillMore queryWfWeekFill(String id, String proId, String periodId) throws RuntimeException;

    /**
     * <p>描述 :保存/修改 周报 </p>
     * 
     * @param wfWeekFill
     * @return id
     * */
    String addOrModifyWfWeekFill(WfWeekFill wfWeekFill, ProcessArgs processArgs) throws RuntimeException,
        LogicalException;

    /**
     * <p>描述 :首页-周报 </p>
     * 
     * @param 
     * @return list
     * */
    List<WfWeekFillMore> queryWeekList(String userId) throws RuntimeException;

    /**
     * 
     * <p>描述 : </p>
     *
     * @param 
     * @return 当前用户相关的周报总数
     */
    int queryWeekListCount(String userId) throws RuntimeException;

}
