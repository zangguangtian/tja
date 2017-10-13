/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.service
 *
 * IDataSyncService.java
 * 
 * 2017年10月10日-上午11:53:17
 *
 * 2017 TabZhu-版权所有 
 */

package com.df.tja.service;

import java.util.Date;

import com.df.framework.base.service.IBaseService;

/**
 * <p>IDataSyncService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月10日 上午11:53:17</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public interface IDataSyncService extends IBaseService {

    /**
     * 
     * <p>描述 : 调用同步接口 </p>
     * <p>备注 : 分开原因是：不让调用和处理保持处于一个事务中 </p>
     *
     * @param itName 接口方法名
     * @param itArgs 接口参数
     * @return 如果调用失败，则返回null
     * @throws RuntimeException
     */
    String createAndQuerySyncData(String itName, String... itArgs) throws RuntimeException;

    /**
     * 
     * <p>描述 :同步部门信息 </p>
     *
     * @param value 接口返回值
     * @throws RuntimeException
     */
    void syncDepts(String value) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步人员信息 </p>
     *
     * @param value 接口返回值
     *
     * @throws RuntimeException
     */
    void syncEmployees(String value) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步账号信息 </p>
     *
     * @param value 接口返回值
     *
     * @throws RuntimeException
     */
    void syncAccounts(String value) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步所有项目信息</p>
     *
     * @param value
     * @throws RuntimeException
     */
    void syncItems(String value) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步项目合同信息</p>
     *
     * @param value
     * @throws RuntimeException
     */
    void syncContractOfItem(String value) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步项目分包合同信息</p>
     *
     * @param value
     * @throws RuntimeException
     */
    void syncEpibolyContractOfItem(String value) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步项目工时成本</p>
     *
     * @param value
     * @throws RuntimeException
     */
    void syncCostOfItem(String value, String itemId, Date createDate) throws RuntimeException;

    /**
     * 
     * <p>描述 : 同步项目策划信息</p>
     *
     * @param value
     * @throws RuntimeException
     */
    void syncItemWbsInfo(String value, String itemId) throws RuntimeException;
}
