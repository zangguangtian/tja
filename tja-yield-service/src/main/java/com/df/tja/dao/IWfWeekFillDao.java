/**
 * 项目名称:df-pro-service
 *
 * com.df.project.dao
 *
 * IWfWeekFillDao.java
 * 
 * 2017年9月18日-下午6:12:26
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.WfWeekFill;
import com.df.tja.domain.cust.WfWeekFillMore;

/**
 * <p>IWfWeekFillDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午6:12:26</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

public interface IWfWeekFillDao extends IBaseDao {

    /**
     * 
     * <p>描述 : </p>
     *
     * @param proId
     * @param periodId
     * @return
     */
    WfWeekFill queryWfWeekFill(String id);

    /**
     * 
     * <p>描述 : </p>
     *
     * @param 
     * @return
     */
    List<WfWeekFillMore> queryWeekList(String userId);

    /**
     * 
     * <p>描述 : </p>
     *
     * @param 
     * @return 当前用户相关的周报总数
     */
    int queryWeekListCount(String userId);

}
