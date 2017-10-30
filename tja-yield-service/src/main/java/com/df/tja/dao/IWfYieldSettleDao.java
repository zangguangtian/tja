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
import com.df.framework.sys.domain.SysConfig;
import com.df.hr.domain.cust.CustStaff;
import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldPrincipalAllot;
import com.df.tja.domain.WfYieldSettle;

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
    List<SysConfig> selectMajorByProId(String proId);

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

}
