/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IWfPlanSchemeDao.java
 * 
 * 2017年9月22日-下午2:08:15
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.tja.domain.WfPlanScheme;
import com.df.tja.domain.WfShemeTeam;

/**
 * <p>IWfPlanSchemeDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月22日 下午2:08:15</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IWfPlanSchemeDao {

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    List<WfShemeTeam> selectWfShemeTeamsByWfId(String id) throws Exception;

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    WfPlanScheme selectWfPlanSchemeById(String id) throws Exception;

}
