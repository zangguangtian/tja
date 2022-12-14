/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.dao
 *
 * IWfYieldSettleDao.java
 * 
 * 2017年10月19日-下午2:09:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.dao;

import java.util.List;

import com.df.framework.base.dao.IBaseDao;
import com.df.hr.domain.cust.CustStaff;
import com.df.tja.domain.OcSettleYield;
import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldMajorRoleRate;
import com.df.tja.domain.WfYieldPrincipalAllot;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.CustYieldSettle;

/**
 * <p>IWfYieldSettleDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月19日 下午2:09:52</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IWfYieldSettleDao extends IBaseDao {

    /**
     * <p>描述 : </p>
     *
     * @param proId
     * @param majorCode
     * @return
     */
    List<CustStaff> selectMajorBudgetStaff(String proId, String majorCode);

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    List<WfYieldMajorRoleAllot> selectMajorRoleAllotByWfId(String id);

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    WfYieldSettle selectWfYieldSettleById(String id);

    /**
     * 
     * <p>描述 : </p>
     *
     * @param proId
     * @param roleCode
     * @return
     */
    List<CustStaff> selectBudgetStaffByRole(String proId, String roleCode);

    /**
     * <p>描述 : </p>
     *
     * @param proId
     * @return
     */
    List<WfYieldMajorRate> selectMajorByProId(String proId);

    /**
     * 
     * <p>描述 : </p>
     *
     * @param id
     * @param staffCategory
     * @return
     */
    List<WfYieldPrincipalAllot> selectPrincipalAllot(String id, String staffCategory);

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @return
     */
    List<WfYieldMajorRate> selectMajorRate(String id);

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @param majorCode
     * @return
     */
    List<WfYieldMajorRoleRate> selectMajorRoleRate(String wfId, String majorCode);

    /**
     * <p>描述 : </p>
     *
     * @param id
     * @param majorCode
     * @return
     */
    List<WfYieldMajorRoleAllot> selectMajorRoleAllot(String wfId, String majorCode);

    /**
     * <p>描述 : 获取历年已经结算产值</p>
     *
     * @return
     */
    WfYieldSettle selectHisYearYield();

    /**
     * <p>描述 : </p>
     *
     * @param userId
     * @return
     */
    List<CustYieldSettle> selectYieldSettleList(String userId);

    /**
     * <p>描述 : </p>
     *
     * @param userId
     * @return
     */
    int selectYieldSettleListCount(String userId);

    /**
     * <p>描述 : </p>
     *
     * @param proId
     * @param periodId
     * @return
     */
    OcSettleYield selectSettleYieldByProIdAndPeriodId(String proId, String periodId);

}
