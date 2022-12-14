/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * YieldSettleAdjustServiceImpl.java
 * 
 * 2017年11月6日-下午3:24:26
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.util.ArithmeticUtil;
import com.df.project.dao.IProjectDao;
import com.df.project.domain.cust.CustProject;
import com.df.tja.constant.TjaConstant;
import com.df.tja.dao.IWfYieldSettleDao;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldMajorRoleRate;
import com.df.tja.domain.WfYieldPrincipalAllot;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;
import com.df.tja.domain.cust.YieldSettleMajorModel;
import com.df.tja.service.IYieldSettleAdjustService;

/**
 * <p>YieldSettleAdjustServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月6日 下午3:24:26</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service("yieldSettleAdjustService")
public class YieldSettleAdjustServiceImpl extends BaseServiceImpl implements IYieldSettleAdjustService {

    @Autowired
    private IProjectDao projectDao;

    @Autowired
    private IWfYieldSettleDao wfYieldSettleDao;

    /** 
     * @see com.df.tja.service.IYieldSettleAdjustService#
     * queryYieldSettle(java.util.Map, com.df.tja.domain.WfYieldSettle)
     */
    @Override
    public void queryYieldSettle(Map<String, Object> modelMap, WfYieldSettle yieldSettle) throws RuntimeException {
        CustProject project = projectDao.selectProInfoById(yieldSettle.getProId());
        OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, yieldSettle.getPeriodId());
        modelMap.put("periodManage", periodManage);
        modelMap.put("project", project);
        modelMap.put("yieldSettle", yieldSettle);
        modelMap.put("categoryLeader", TjaConstant.SysCode.STAFF_CATEGORY_LEADER);
        modelMap.put("categoryPm", TjaConstant.SysCode.STAFF_CATEGORY_PM);
        if ("1000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 普通流程
            //获取项目负责人下 的 人员 
            List<WfYieldPrincipalAllot> allotsLeaders = wfYieldSettleDao.selectPrincipalAllot(yieldSettle.getId(),
                TjaConstant.SysCode.STAFF_CATEGORY_LEADER);
            modelMap.put("proLeaders", allotsLeaders);
            //获取项目经理下 的 人员 
            List<WfYieldPrincipalAllot> allotsPms = wfYieldSettleDao.selectPrincipalAllot(yieldSettle.getId(),
                TjaConstant.SysCode.STAFF_CATEGORY_PM);
            modelMap.put("proManagers", allotsPms);
            //当年专业结算比例
            List<WfYieldMajorRate> majorRates = wfYieldSettleDao.selectMajorRate(yieldSettle.getId());
            modelMap.put("majorRates", majorRates);
            List<YieldSettleMajorModel> majorModels = new ArrayList<YieldSettleMajorModel>();
            addMajorAndAllotEdit(yieldSettle, majorRates, majorModels);
            modelMap.put("majorModels", majorModels);
        } else if ("2000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 特批流程
            // 年度产值结算特批
            List<WfYieldMajorRoleAllot> permitYields = wfYieldSettleDao.selectMajorRoleAllotByWfId(yieldSettle.getId());
            modelMap.put("majorRoleAllots", permitYields);
        }

    }

    private void addMajorAndAllotEdit(WfYieldSettle yieldSettle, List<WfYieldMajorRate> majors,
                                      List<YieldSettleMajorModel> majorModels) {
        if (majors != null && majors.size() > 0) {
            for (WfYieldMajorRate major : majors) {
                YieldSettleMajorModel settleMajor = new YieldSettleMajorModel();
                if (StringUtils.isNotBlank(major.getMajorCode())) {
                    settleMajor.setMajorCode(major.getMajorCode());
                    settleMajor.setMajorName(major.getMajorName());

                    //年度产值专业角色人员
                    List<WfYieldMajorRoleAllot> majorRoleAllots = wfYieldSettleDao.selectMajorRoleAllot(
                        yieldSettle.getId(), major.getMajorCode());

                    //各专业负责人审批   年度产值专业角色结算比例
                    List<WfYieldMajorRoleRate> majorRoleRates = wfYieldSettleDao.selectMajorRoleRate(
                        yieldSettle.getId(), major.getMajorCode());

                    WfYieldMajorRate majorRate = new WfYieldMajorRate();
                    majorRate.setWfId(yieldSettle.getId());
                    majorRate.setMajorCode(major.getMajorCode());
                    List<WfYieldMajorRate> list = queryByCondition(WfYieldMajorRate.class, majorRate);
                    if (list != null && list.size() > 0) {
                        majorRate = list.get(0);
                        settleMajor.setMajorAllotRate(majorRate.getSettleRate());
                    }

                    settleMajor.setMajorRoleAllots(majorRoleAllots);
                    settleMajor.setMajorRoleRates(majorRoleRates);
                }
                majorModels.add(settleMajor);
            }
        }
    }

    /** 
     * @see com.df.tja.service.IYieldSettleAdjustService#
     * createOrEditYieldSettle(com.df.tja.domain.cust.WfYieldSettleModel)
     */
    @Override
    public void createOrEditYieldSettle(WfYieldSettleModel settleModel) throws RuntimeException, LogicalException {
        try {
            WfYieldSettle yieldSettle = settleModel.getWfYieldSettle();
            modify(WfYieldSettle.class, yieldSettle);
            if ("1000".equals(yieldSettle.getWfCategory().trim())) { //普通流程 调整
                addOrEditOrdinSettleAdjust(settleModel, yieldSettle);
            } else if ("2000".equals(yieldSettle.getWfCategory().trim())) { //特批流程 调整
                if (StringUtils.isNotBlank(yieldSettle.getId())) { //修改
                    modify(WfYieldSettle.class, yieldSettle);
                    List<WfYieldMajorRoleAllot> roleAllots = settleModel.getMajorRoleAllots();
                    if (roleAllots != null && roleAllots.size() > 0) {
                        for (WfYieldMajorRoleAllot roleAllot : roleAllots) {
                            if (StringUtils.isNotBlank(roleAllot.getId())) {
                                modify(WfYieldMajorRoleAllot.class, roleAllot);
                            } else {
                                roleAllot.setWfId(yieldSettle.getId());
                                addEntity(WfYieldMajorRoleAllot.class, roleAllot);
                            }
                        }
                    }
                } else { //添加
                    addEntity(WfYieldSettle.class, yieldSettle);
                    List<WfYieldMajorRoleAllot> roleAllots = settleModel.getMajorRoleAllots();
                    if (roleAllots != null && roleAllots.size() > 0) {
                        for (WfYieldMajorRoleAllot roleAllot : roleAllots) {
                            roleAllot.setWfId(yieldSettle.getId());
                            addEntity(WfYieldMajorRoleAllot.class, roleAllot);
                        }
                    }

                }
            }
        } catch (LogicalException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>描述 : </p>
     *
     * @param settleModel
     * @param yieldSettle
     * @throws LogicalException
     */
    private void addOrEditOrdinSettleAdjust(WfYieldSettleModel settleModel, WfYieldSettle yieldSettle)
        throws LogicalException {
        //检验 年度产值专业角色结算比例 年度产值专业角色人员工作量 是否为 100% 并保存修改
        addOrCheckRate(settleModel, yieldSettle);

        //专业结算比例
        List<WfYieldMajorRate> majorRates = settleModel.getMajorRates();
        for (WfYieldMajorRate wfYieldMajorRate : majorRates) {
            if (StringUtils.isNotBlank(wfYieldMajorRate.getId())) { //修改
                modify(WfYieldMajorRate.class, wfYieldMajorRate);
            }
        }

        List<WfYieldPrincipalAllot> principalAllots = settleModel.getPrincipalAllots();
        if (principalAllots != null && principalAllots.size() > 0) {
            for (WfYieldPrincipalAllot principalAllot : principalAllots) {
                principalAllot.setWfId(yieldSettle.getId());
                if (StringUtils.isNotBlank(principalAllot.getId())) {
                    modify(WfYieldPrincipalAllot.class, principalAllot);
                } else {
                    addEntity(WfYieldPrincipalAllot.class, principalAllot);
                }
            }
        }
    }

    /**
     * <p>描述 : </p>
     *
     * @param settleModel
     * @param yieldSettle
     * @throws LogicalException
     */
    private void addOrCheckRate(WfYieldSettleModel settleModel, WfYieldSettle yieldSettle) throws LogicalException {
        List<WfYieldMajorRoleRate> roleRates = settleModel.getMajorRoleRates(); //各专业负责人审批   年度产值专业角色结算比例
        List<WfYieldMajorRoleAllot> majorRoleAllots = settleModel.getMajorRoleAllots(); //年度产值专业角色人员
        BigDecimal totalAllotRate = new BigDecimal(0);
        String roleNames = null;
        Map<String, BigDecimal> cheackRate = new HashMap<String, BigDecimal>();
        Map<String, String> marjorRoleMap = new HashMap<String, String>();
        if (roleRates != null && roleRates.size() > 0) {
            for (WfYieldMajorRoleRate majorRoleRate : roleRates) {
                majorRoleRate.setWfId(yieldSettle.getId());
                if (StringUtils.isNoneBlank(majorRoleRate.getId())) {
                    modify(WfYieldMajorRoleRate.class, majorRoleRate);
                } else {
                    addEntity(WfYieldMajorRoleRate.class, majorRoleRate);
                }
                //累加计算 检查 比例：校对人+审核人+设计人/制图人=100%；
                String marjor = majorRoleRate.getMajorName();
                if (cheackRate.containsKey(marjor)) {
                    BigDecimal allotRate = cheackRate.get(marjor);
                    totalAllotRate = ArithmeticUtil.add(allotRate, majorRoleRate.getAllotRate());
                } else {
                    totalAllotRate = majorRoleRate.getAllotRate();
                }
                cheackRate.put(marjor, totalAllotRate);
                if (marjorRoleMap.containsKey(marjor)) {
                    String roleName = marjorRoleMap.get(marjor);
                    roleNames = roleName + "+" + majorRoleRate.getRoleName();
                } else {
                    roleNames = majorRoleRate.getRoleName();
                }
                marjorRoleMap.put(marjor, roleNames);
            }
            if (!cheackRate.isEmpty()) {
                for (String marjorRoleKey : cheackRate.keySet()) {
                    BigDecimal totalMarjorRoleAllotRate = cheackRate.get(marjorRoleKey);
                    if ((totalMarjorRoleAllotRate.compareTo(new BigDecimal(100))) != 0) {
                        throw new LogicalException(marjorRoleKey + marjorRoleMap.get(marjorRoleKey) + "=100%");
                    }
                }
            }
        }
        if (majorRoleAllots != null && majorRoleAllots.size() > 0) {
            totalAllotRate = new BigDecimal(0);
            cheackRate = new HashMap<String, BigDecimal>();
            marjorRoleMap = new HashMap<String, String>();
            for (WfYieldMajorRoleAllot wfYieldMajorRoleAllot : majorRoleAllots) {
                wfYieldMajorRoleAllot.setWfId(yieldSettle.getId());
                if (StringUtils.isNoneBlank(wfYieldMajorRoleAllot.getId())) {
                    modify(WfYieldMajorRoleAllot.class, wfYieldMajorRoleAllot);
                } else {
                    addEntity(WfYieldMajorRoleAllot.class, wfYieldMajorRoleAllot);
                }
                String marjor = wfYieldMajorRoleAllot.getMajorCode() + wfYieldMajorRoleAllot.getRoleCode();
                if (cheackRate.containsKey(marjor)) {
                    BigDecimal allotRate = cheackRate.get(marjor);
                    totalAllotRate = ArithmeticUtil.add(allotRate, wfYieldMajorRoleAllot.getStaffRate());
                } else {
                    totalAllotRate = wfYieldMajorRoleAllot.getStaffRate();
                }
                cheackRate.put(marjor, totalAllotRate);
                if (!marjorRoleMap.containsKey(marjor)) {
                    roleNames = wfYieldMajorRoleAllot.getMajorName() + wfYieldMajorRoleAllot.getRoleName();
                    marjorRoleMap.put(marjor, roleNames);
                }
            }
            if (!cheackRate.isEmpty()) {
                for (String marjorRoleKey : cheackRate.keySet()) {
                    BigDecimal totalMarjorRoleAllotRate = cheackRate.get(marjorRoleKey);
                    if ((totalMarjorRoleAllotRate.compareTo(new BigDecimal(100))) != 0) {
                        throw new LogicalException(marjorRoleMap.get(marjorRoleKey) + "的工作量(%)合计必须达到100");
                    }
                }
            }
        }
    }
}
