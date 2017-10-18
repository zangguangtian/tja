/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * WfMonthFillService.java
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
import com.df.tja.domain.WfMajorProgressRecord;
import com.df.tja.domain.WfYearMonthFill;
import com.df.tja.domain.cust.WfYearMonthFillMore;

/**
 * <p>IWfMonthFillService</p>
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

public interface IWfYearMonthFillService extends IBaseService {

    /**
     * <p>描述 :查询 月/年报详情 </p>
     * 
     * @param id 
     * @return 
     * */
    WfYearMonthFillMore queryWfYearMonthFill(String id, String proId, String periodId) throws RuntimeException;

    /**
     * <p>描述 :保存/修改 月报 </p>
     * 
     * @param ymFill
     * @param processArgs
     * @param majorProgressList
     * @return id
     * */
    String addOrModifyWfYearMonthFill(WfYearMonthFill ymFill, ProcessArgs processArgs,
                                      List<WfMajorProgressRecord> majorProgressList) throws RuntimeException,
        LogicalException;

    /**
     * <p>描述 :首页-月报 </p>
     * 
     * @param 
     * @return list
     * */
    List<WfYearMonthFillMore> queryMonthList(String userId) throws RuntimeException;

    /**
     * 
     * <p>描述 : </p>
     *
     * @param 
     * @return 当前用户相关的月报总数
     */
    int queryMonthListCount(String userId) throws RuntimeException;

}
