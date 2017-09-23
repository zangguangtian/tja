/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IWfPlanSchemeService.java
 * 
 * 2017年9月22日-下午2:01:19
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.Map;

import com.df.activiti.domain.ProcessArgs;
import com.df.tja.domain.WfPlanScheme;
import com.df.tja.domain.cust.WfPlanSchemeModel;

/**
 * <p>IWfPlanSchemeService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月22日 下午2:01:19</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IWfPlanSchemeService {

    /**
     * <p>描述 : </p>
     *
     * @param planScheme
     * @param processArgs
     * @param planSchemeModel
     */
    void addOrModifyPlanScheme(WfPlanScheme planScheme, ProcessArgs processArgs, WfPlanSchemeModel planSchemeModel)
        throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    void queryPlanSchemeById(Map<String, Object> attributes, String id) throws RuntimeException;

}