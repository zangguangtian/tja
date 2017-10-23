/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * WfYieldSettleServiceImpl.java
 * 
 * 2017年10月19日-上午10:14:52
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.activiti.domain.ProcessArgs;
import com.df.activiti.service.IProcessService;
import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.hr.domain.cust.CustStaff;
import com.df.project.dao.IProjectDao;
import com.df.project.domain.cust.CustProject;
import com.df.tja.dao.IWfYieldSettleDao;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.OcPermitYield;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;
import com.df.tja.service.IWfYieldSettleService;

/**
 * <p>WfYieldSettleServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月19日 上午10:14:52</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service("wfYieldSettleService")
public class WfYieldSettleServiceImpl extends BaseServiceImpl implements IWfYieldSettleService {

    @Autowired
    private IProjectDao projectDao;

    @Autowired
    private IWfYieldSettleDao wfYieldSettleDao;

    @Autowired
    private IProcessService processService;

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#queryYieldSettle(java.util.Map, java.util.Map)
     */
    @Override
    public void queryYieldSettle(Map<String, Object> inParams, Map<String, Object> outParams) throws RuntimeException {
        String proId = (String) inParams.get("proId");
        String periodId = (String) inParams.get("periodId");
        String syId = (String) inParams.get("syId");
        String id = (String) inParams.get("id");
        String editType = (String) inParams.get("editType");

        try {
            if ("0".equals(id)) {
                //新建
                CustProject project = projectDao.selectProInfoById(proId);
                outParams.put("project", project);

                OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, periodId);
                outParams.put("periodManage", periodManage);
                outParams.put("permitId", syId);

                if ("1000".equals(editType)) {
                    //年度产值结算；


                } else if ("2000".equals(editType)) {
                    // 年度产值结算特批
                    OcPermitYield ocPermitYield = queryByPrimaryKey(OcPermitYield.class, syId);
                    project.setYield(ocPermitYield.getPermitYield());
                    List<CustStaff> staffs = wfYieldSettleDao.selectMajorBudgetStaff(proId,
                        ocPermitYield.getMajorCode());
                    Set<CustStaff> staffSets = new HashSet<CustStaff>();
                    staffSets.addAll(staffs);
                    outParams.put("staffs", staffSets);
                    if (staffs != null && staffs.size() > 0) {
                        CustStaff custStaff = staffs.get(0);
                        if (StringUtils.isNotBlank(custStaff.getMajorName())) {
                            outParams.put("majorName", custStaff.getMajorName());
                            outParams.put("majorCode", custStaff.getMajorCode());
                        }
                    }
                }


            } else {
                //修改 
                WfYieldSettle yieldSettle = wfYieldSettleDao.selectWfYieldSettleById(id);
                outParams.put("yieldSettle", yieldSettle);
                CustProject project = projectDao.selectProInfoById(yieldSettle.getProId());
                project.setYield(yieldSettle.getYearYield());
                outParams.put("project", project);
                outParams.put("permitId", yieldSettle.getPermitId());
                OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, yieldSettle.getPeriodId());
                outParams.put("periodManage", periodManage);
                if ("1000".equals(editType)) {
                    //年度产值结算；

                } else if ("2000".equals(editType)) {
                    // 年度产值结算特批
                    List<WfYieldMajorRoleAllot> permitYields = wfYieldSettleDao.selectMajorRoleAllotByWfId(yieldSettle
                        .getId());
                    outParams.put("majorRoleAllots", permitYields);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#addOrModifyYieldSettle(com.df.tja.domain.WfYieldSettle, 
     * com.df.activiti.domain.ProcessArgs, com.df.tja.domain.cust.WfYieldSettleModel)
     */
    @Override
    public void addOrModifyYieldSettle(WfYieldSettle yieldSettle, ProcessArgs processArgs,
                                       WfYieldSettleModel settleModel) throws RuntimeException {

        try {
            if ("1000".equals(yieldSettle.getWfCategory().trim())) {
                //年度产值结算 - 普通流程


            } else if ("2000".equals(yieldSettle.getWfCategory().trim())) {
                //年度产值结算 - 特批流程
                if (StringUtils.isNotBlank(yieldSettle.getId())) {
                    //修改
                    modify(WfYieldSettle.class, yieldSettle);
                    List<WfYieldMajorRoleAllot> roleAllots = settleModel.getMajorRoleAllots();
                    if (roleAllots != null && roleAllots.size() > 0) {
                        for (WfYieldMajorRoleAllot roleAllot : roleAllots) {
                            if (StringUtils.isNotBlank(roleAllot.getId())) {
                                modify(WfYieldMajorRoleAllot.class, roleAllot);
                            } else {
                                if (roleAllot.getStaffYield().compareTo(BigDecimal.ZERO) > 0) {
                                    roleAllot.setWfId(yieldSettle.getId());
                                    addEntity(WfYieldMajorRoleAllot.class, roleAllot);
                                }
                            }
                        }
                    }

                } else {
                    //添加
                    addEntity(WfYieldSettle.class, yieldSettle);
                    List<WfYieldMajorRoleAllot> roleAllots = settleModel.getMajorRoleAllots();
                    if (roleAllots != null && roleAllots.size() > 0) {
                        for (WfYieldMajorRoleAllot roleAllot : roleAllots) {
                            if (roleAllot.getStaffYield().compareTo(BigDecimal.ZERO) > 0) {
                                roleAllot.setWfId(yieldSettle.getId());
                                addEntity(WfYieldMajorRoleAllot.class, roleAllot);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#queryYieldSettleForView(java.util.Map, java.lang.String)
     */
    @Override
    public void queryYieldSettleForView(Map<String, Object> modelMap, String id) throws RuntimeException {

        WfYieldSettle yieldSettle = wfYieldSettleDao.selectWfYieldSettleById(id);
        CustProject project = projectDao.selectProInfoById(yieldSettle.getProId());
        modelMap.put("project", project);
        modelMap.put("yieldSettle", yieldSettle);

        if ("1000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 普通流程

        } else if ("2000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 特批流程
            OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, yieldSettle.getPeriodId());
            modelMap.put("periodManage", periodManage);
            // 年度产值结算特批
            List<WfYieldMajorRoleAllot> permitYields = wfYieldSettleDao.selectMajorRoleAllotByWfId(yieldSettle.getId());
            modelMap.put("majorRoleAllots", permitYields);
        }
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#approveWfYieldSettle(com.df.tja.domain.cust.WfYieldSettleModel, 
     * java.lang.Integer)
     */
    @Override
    public void approveWfYieldSettle(WfYieldSettle yieldSettle, WfYieldSettleModel settleModel, Integer view)
        throws RuntimeException,
        LogicalException {

        if ("1000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 普通流程

        } else if ("2000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 特批流程
            processService.approveWf(yieldSettle, null);
        }

    }

}
