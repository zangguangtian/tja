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
import java.util.ArrayList;
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
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.HttpUtil;
import com.df.hr.domain.Staff;
import com.df.hr.domain.cust.CustStaff;
import com.df.project.dao.IProjectDao;
import com.df.project.domain.ProBudgetStaff;
import com.df.project.domain.ProMajorRoleRate;
import com.df.project.domain.Project;
import com.df.project.domain.ProjectExtend;
import com.df.project.domain.cust.CustProject;
import com.df.tja.constant.TjaConstant;
import com.df.tja.dao.IWfYieldSettleDao;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.OcPermitYield;
import com.df.tja.domain.OcSettleYield;
import com.df.tja.domain.WfYieldMajorRate;
import com.df.tja.domain.WfYieldMajorRoleAllot;
import com.df.tja.domain.WfYieldMajorRoleRate;
import com.df.tja.domain.WfYieldPrincipalAllot;
import com.df.tja.domain.WfYieldSettle;
import com.df.tja.domain.cust.CustYieldSettle;
import com.df.tja.domain.cust.WfYieldSettleModel;
import com.df.tja.domain.cust.YieldSettleMajorModel;
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
        outParams.put("categoryLeader", TjaConstant.SysCode.STAFF_CATEGORY_LEADER);
        outParams.put("categoryPm", TjaConstant.SysCode.STAFF_CATEGORY_PM);
        try {
            if ("0".equals(id)) { //新建
                getYieldSettleInfo(outParams, proId, periodId, syId, editType);
            } else { //修改 
                WfYieldSettle yieldSettle = wfYieldSettleDao.selectWfYieldSettleById(id);
                outParams.put("yieldSettle", yieldSettle);
                CustProject project = projectDao.selectProInfoById(yieldSettle.getProId());
                project.setYield(yieldSettle.getYearYield());
                outParams.put("project", project);
                outParams.put("permitId", yieldSettle.getPermitId());
                OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, yieldSettle.getPeriodId());
                outParams.put("periodManage", periodManage);
                if ("1000".equals(editType)) { //年度产值结算；
                    //获取项目负责人下 的 人员 
                    List<WfYieldPrincipalAllot> allotsLeaders = wfYieldSettleDao.selectPrincipalAllot(
                        yieldSettle.getId(), TjaConstant.SysCode.STAFF_CATEGORY_LEADER);
                    outParams.put("proLeaders", allotsLeaders);
                    //获取项目经理下 的 人员 
                    List<WfYieldPrincipalAllot> allotsPms = wfYieldSettleDao.selectPrincipalAllot(yieldSettle.getId(),
                        TjaConstant.SysCode.STAFF_CATEGORY_PM);
                    outParams.put("proManagers", allotsPms);
                    //当年专业结算比例
                    List<WfYieldMajorRate> majorRates = wfYieldSettleDao.selectMajorRate(yieldSettle.getId());
                    outParams.put("majorRates", majorRates);
                } else if ("2000".equals(editType)) { // 年度产值结算特批
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
     * <p>描述 : </p>
     *
     * @param outParams
     * @param proId
     * @param periodId
     * @param syId
     * @param editType
     */
    private void getYieldSettleInfo(Map<String, Object> outParams, String proId, String periodId, String syId,
                                    String editType) {
        CustProject project = projectDao.selectProInfoById(proId);
        OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, periodId);
        outParams.put("periodManage", periodManage);
        outParams.put("permitId", syId);
        if ("1000".equals(editType)) { //年度产值结算；
            OcSettleYield ocSettleYield = queryByPrimaryKey(OcSettleYield.class, syId);
            project.setYield(ocSettleYield.getSettleYield());
            //获取 项目负责人 和 项目经理 比例
            ProjectExtend projectExtend = queryByPrimaryKey(ProjectExtend.class, proId);
            outParams.put("projectExtend", projectExtend);
            //获取项目负责人下 的 人员 
            List<CustStaff> leaders = wfYieldSettleDao.selectBudgetStaffByRole(proId,
                TjaConstant.SysCode.STAFF_CATEGORY_LEADER);
            outParams.put("leaders", leaders);
            //获取项目经理下 的 人员 
            List<CustStaff> pms = wfYieldSettleDao.selectBudgetStaffByRole(proId,
                TjaConstant.SysCode.STAFF_CATEGORY_PM);
            outParams.put("proPms", pms);
            //当年专业结算比例
            List<SysConfig> configs = wfYieldSettleDao.selectMajorByProId(proId);
            outParams.put("configs", configs);
            //历年已结算产值
            WfYieldSettle settle = wfYieldSettleDao.selectHisYearYield();
            outParams.put("hisyearYield", settle.getYearYield());
        } else if ("2000".equals(editType)) { // 年度产值结算特批
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
        outParams.put("project", project);
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#addOrModifyYieldSettle(com.df.tja.domain.WfYieldSettle, 
     * com.df.activiti.domain.ProcessArgs, com.df.tja.domain.cust.WfYieldSettleModel)
     */
    @Override
    public void addOrModifyYieldSettle(WfYieldSettle yieldSettle, ProcessArgs processArgs,
                                       WfYieldSettleModel settleModel) throws RuntimeException {
        try {
            if ("1000".equals(yieldSettle.getWfCategory().trim())) { //年度产值结算 - 普通流程
                if (StringUtils.isNotBlank(yieldSettle.getId())) {
                    modifyYieldSettle(yieldSettle, settleModel);
                } else {
                    addYieldSettle(yieldSettle, settleModel);
                }
            } else if ("2000".equals(yieldSettle.getWfCategory().trim())) { //年度产值结算 - 特批流程
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>描述 : </p>
     *
     * @param yieldSettle
     * @param settleModel
     */
    private void addYieldSettle(WfYieldSettle yieldSettle, WfYieldSettleModel settleModel) {
        //添加
        addEntity(WfYieldSettle.class, yieldSettle);
        //项目负责人 项目经理 人员
        List<WfYieldPrincipalAllot> principalAllots = settleModel.getPrincipalAllots();
        if (principalAllots != null && principalAllots.size() > 0) {
            for (WfYieldPrincipalAllot allot : principalAllots) {
                //添加
                allot.setWfId(yieldSettle.getId());
                addEntity(WfYieldPrincipalAllot.class, allot);
            }
        }
        //当年专业结算比例
        List<WfYieldMajorRate> majorRates = settleModel.getMajorRates();
        if (majorRates != null && majorRates.size() > 0) {
            for (WfYieldMajorRate majorRate : majorRates) {
                majorRate.setWfId(yieldSettle.getId());
                addEntity(WfYieldMajorRate.class, majorRate);
            }
        }
    }

    /**
     * <p>描述 : </p>
     *
     * @param yieldSettle
     * @param settleModel
     */
    private void modifyYieldSettle(WfYieldSettle yieldSettle, WfYieldSettleModel settleModel) {
        //修改
        modify(WfYieldSettle.class, yieldSettle);
        //项目负责人 项目经理 人员
        List<WfYieldPrincipalAllot> principalAllots = settleModel.getPrincipalAllots();
        if (principalAllots != null && principalAllots.size() > 0) {
            for (WfYieldPrincipalAllot allot : principalAllots) {
                if (StringUtils.isNotBlank(allot.getId())) {
                    //修改
                    modify(WfYieldPrincipalAllot.class, allot);
                } else {
                    //添加
                    allot.setWfId(yieldSettle.getId());
                    addEntity(WfYieldPrincipalAllot.class, allot);
                }

            }

        }

        //当年专业结算比例
        List<WfYieldMajorRate> majorRates = settleModel.getMajorRates();
        if (majorRates != null && majorRates.size() > 0) {
            for (WfYieldMajorRate majorRate : majorRates) {
                if (StringUtils.isNotBlank(majorRate.getId())) {
                    modify(WfYieldMajorRate.class, majorRate);
                } else {
                    majorRate.setWfId(yieldSettle.getId());
                    addEntity(WfYieldMajorRate.class, majorRate);
                }
            }
        }
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#queryYieldSettleForView(java.util.Map, java.lang.String)
     */
    @Override
    public void queryYieldSettleForView(Map<String, Object> modelMap, String id, Integer view) throws RuntimeException {
        WfYieldSettle yieldSettle = wfYieldSettleDao.selectWfYieldSettleById(id);
        CustProject project = projectDao.selectProInfoById(yieldSettle.getProId());
        OcPeriodManage periodManage = queryByPrimaryKey(OcPeriodManage.class, yieldSettle.getPeriodId());
        modelMap.put("periodManage", periodManage);
        modelMap.put("project", project);
        modelMap.put("yieldSettle", yieldSettle);
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
            //各专业负责人审批
            if (view == 2) {
                Map<String, Object> variables = processService.getVariables(yieldSettle.getProcId(), HttpUtil.getUser()
                    .getId());

                Map<String, List<String>> userMajorMap = (Map<String, List<String>>) variables.get("userMajorMap");
                List<String> majors = userMajorMap.get(HttpUtil.getUser().getId());

                addMajorAndAllotEdit(yieldSettle, majors, majorModels);
                YieldSettleMajorModel yieldSettleMajorModel = majorModels.get(0);
                if (yieldSettleMajorModel == null) {
                    majorModels.clear();
                    addMajorAndSatffAllot(majors, yieldSettle, majorModels);
                } else if (yieldSettleMajorModel.getMajorRoleRates() == null
                           || yieldSettleMajorModel.getMajorRoleRates().size() <= 0) {
                    majorModels.clear();
                    addMajorAndSatffAllot(majors, yieldSettle, majorModels);
                }

            } else if (view > 2) {
                Map<String, Object> variables = processService.getHistoricVariables(yieldSettle.getProcId());
                Map<String, List<String>> userMajorMap = (Map<String, List<String>>) variables.get("userMajorMap");
                //看到所有的专业
                List<String> allMajors = new ArrayList<String>();
                for (Map.Entry<String, List<String>> entry : userMajorMap.entrySet()) {
                    allMajors.addAll(entry.getValue());
                }
                addMajorAndAllotEdit(yieldSettle, allMajors, majorModels);
            }
            modelMap.put("majorModels", majorModels);
        } else if ("2000".equals(yieldSettle.getWfCategory().trim())) {
            //年度产值结算 - 特批流程
            // 年度产值结算特批
            List<WfYieldMajorRoleAllot> permitYields = wfYieldSettleDao.selectMajorRoleAllotByWfId(yieldSettle.getId());
            modelMap.put("majorRoleAllots", permitYields);
        }
    }

    /**
     * <p>描述 : </p>
     *
     * @param yieldSettle
     * @param majors
     * @param majorModels
     */
    private void addMajorAndAllotEdit(WfYieldSettle yieldSettle, List<String> majors,
                                      List<YieldSettleMajorModel> majorModels) {
        if (majors != null && majors.size() > 0) {
            for (String major : majors) {
                YieldSettleMajorModel settleMajor = new YieldSettleMajorModel();
                if (major.contains("-")) {
                    String[] codeName = major.split("-");
                    if (codeName.length > 1) {
                        settleMajor.setMajorCode(codeName[0]);
                        settleMajor.setMajorName(codeName[1]);

                        //年度产值专业角色人员
                        List<WfYieldMajorRoleAllot> majorRoleAllots = wfYieldSettleDao.selectMajorRoleAllot(
                            yieldSettle.getId(), codeName[0]);

                        //各专业负责人审批   年度产值专业角色结算比例
                        List<WfYieldMajorRoleRate> majorRoleRates = wfYieldSettleDao.selectMajorRoleRate(
                            yieldSettle.getId(), codeName[0]);

                        WfYieldMajorRate majorRate = new WfYieldMajorRate();
                        majorRate.setWfId(yieldSettle.getId());
                        majorRate.setMajorCode(codeName[0]);
                        List<WfYieldMajorRate> list = queryByCondition(WfYieldMajorRate.class, majorRate);
                        if (list != null && list.size() > 0) {
                            majorRate = list.get(0);
                            settleMajor.setMajorAllotRate(majorRate.getSettleRate());
                        }

                        settleMajor.setMajorRoleAllots(majorRoleAllots);
                        settleMajor.setMajorRoleRates(majorRoleRates);
                    }
                }
                majorModels.add(settleMajor);
            }
        }
    }

    private void addMajorAndSatffAllot(List<String> majors, WfYieldSettle yieldSettle,
                                       List<YieldSettleMajorModel> majorModels) {
        if (majors != null && majors.size() > 0) {
            for (String major : majors) {
                YieldSettleMajorModel settleMajor = new YieldSettleMajorModel();
                if (major.contains("-")) {
                    String[] codeName = major.split("-");
                    if (codeName.length > 1) {
                        settleMajor.setMajorCode(codeName[0]);
                        settleMajor.setMajorName(codeName[1]);
                        //当年本专业结算比例
                        WfYieldMajorRate majorRate = new WfYieldMajorRate();
                        majorRate.setWfId(yieldSettle.getId());
                        majorRate.setMajorCode(codeName[0]);
                        List<WfYieldMajorRate> list = queryByCondition(WfYieldMajorRate.class, majorRate);
                        if (list != null && list.size() > 0) {
                            majorRate = list.get(0);
                            settleMajor.setMajorAllotRate(majorRate.getSettleRate());
                        }

                        List<WfYieldMajorRoleRate> wfYieldMajorRoleRates = new ArrayList<WfYieldMajorRoleRate>();
                        //年度产值专业角色结算比例
                        ProMajorRoleRate majorRoleRate = new ProMajorRoleRate();
                        majorRoleRate.setProId(yieldSettle.getProId());
                        majorRoleRate.setAllotCategory("1000");
                        List<ProMajorRoleRate> roleRates = queryByCondition(ProMajorRoleRate.class, majorRoleRate);
                        if (roleRates != null && roleRates.size() > 0) {
                            for (ProMajorRoleRate proMajorRoleRate : roleRates) {
                                WfYieldMajorRoleRate yieldMajorRoleRate = new WfYieldMajorRoleRate();
                                yieldMajorRoleRate.setAllotRate(proMajorRoleRate.getAllotRate());
                                yieldMajorRoleRate.setMajorCode(codeName[0]);
                                yieldMajorRoleRate.setRoleCode(proMajorRoleRate.getAllotCode());
                                yieldMajorRoleRate.setMajorRateId(majorRate.getId());
                                SysConfig entity = new SysConfig();
                                entity.setConfigCode(proMajorRoleRate.getAllotCode());
                                List<SysConfig> sysConfigs = queryByCondition(SysConfig.class, entity);
                                if (sysConfigs != null && sysConfigs.size() > 0) {
                                    SysConfig sysConfig = sysConfigs.get(0);
                                    yieldMajorRoleRate.setRoleName(sysConfig.getConfigName());
                                }

                                wfYieldMajorRoleRates.add(yieldMajorRoleRate);
                            }
                        }
                        settleMajor.setMajorRoleRates(wfYieldMajorRoleRates);
                        //年度产值专业角色人员
                        List<WfYieldMajorRoleAllot> majorRoleAllots = new ArrayList<WfYieldMajorRoleAllot>();
                        ProBudgetStaff budgetStaff = new ProBudgetStaff();
                        budgetStaff.setProId(yieldSettle.getProId());
                        budgetStaff.setMajorCode(codeName[0]);
                        List<ProBudgetStaff> staffs = queryByCondition(ProBudgetStaff.class, budgetStaff);
                        if (staffs != null && staffs.size() > 0) {
                            for (ProBudgetStaff proBudgetStaff : staffs) {
                                WfYieldMajorRoleAllot majorRoleAllot = new WfYieldMajorRoleAllot();
                                majorRoleAllot.setMajorCode(proBudgetStaff.getMajorCode());
                                majorRoleAllot.setRoleCode(proBudgetStaff.getInvolvedRole());
                                majorRoleAllot.setStaffId(proBudgetStaff.getStaffId());
                                if (StringUtils.isNotBlank(proBudgetStaff.getStaffId())) {
                                    Staff staff = queryByPrimaryKey(Staff.class, proBudgetStaff.getStaffId());
                                    majorRoleAllot.setStaffName(staff != null ? staff.getName() : null);
                                }
                                majorRoleAllots.add(majorRoleAllot);
                            }
                        }
                        settleMajor.setMajorRoleAllots(majorRoleAllots);
                    }
                    majorModels.add(settleMajor);
                }
            }
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
            if (view == 2) {
                Integer tabs = settleModel.getTabs();
                //各专业负责人审批   年度产值专业角色结算比例
                List<WfYieldMajorRoleRate> roleRates = settleModel.getMajorRoleRates();

                //年度产值专业角色人员
                List<WfYieldMajorRoleAllot> majorRoleAllots = settleModel.getMajorRoleAllots();

                BigDecimal totalAllotRate = new BigDecimal(0);
                if (roleRates != null && roleRates.size() > 0) {
                    for (WfYieldMajorRoleRate majorRoleRate : roleRates) {
                        majorRoleRate.setWfId(yieldSettle.getId());
                        if (StringUtils.isNoneBlank(majorRoleRate.getId())) {
                            //修改
                            modify(WfYieldMajorRoleRate.class, majorRoleRate);

                        } else {
                            //添加
                            addEntity(WfYieldMajorRoleRate.class, majorRoleRate);
                        }

                        //累加计算 检查 比例：校对人+审核人+设计人/制图人=100%；
                        totalAllotRate = ArithmeticUtil.add(totalAllotRate, majorRoleRate.getAllotRate());
                    }
                }
                BigDecimal total = ArithmeticUtil.mul(new BigDecimal(tabs), new BigDecimal(100));
                
                if ((total.compareTo(totalAllotRate)) != 0) {
                    throw new LogicalException("校对人+审核人+设计人/制图人=100%");
                }
                
                BigDecimal totalRoleAllot = new BigDecimal(0);
                if (majorRoleAllots != null && majorRoleAllots.size() > 0) {
                    for (WfYieldMajorRoleAllot wfYieldMajorRoleAllot : majorRoleAllots) {
                        wfYieldMajorRoleAllot.setWfId(yieldSettle.getId());
                        if (StringUtils.isNoneBlank(wfYieldMajorRoleAllot.getId())) {
                            //修改
                            modify(WfYieldMajorRoleAllot.class, wfYieldMajorRoleAllot);

                        } else {
                            //添加
                            addEntity(WfYieldMajorRoleAllot.class, wfYieldMajorRoleAllot);
                        }
                        totalRoleAllot = ArithmeticUtil.add(totalRoleAllot, wfYieldMajorRoleAllot.getStaffRate());
                    }
                }

                BigDecimal total2 = ArithmeticUtil.mul(ArithmeticUtil.mul(new BigDecimal(tabs), new BigDecimal(100)),
                    new BigDecimal(roleRates.size()));
                if ((total2.compareTo(totalRoleAllot)) != 0) {
                    throw new LogicalException("工作量(%)合计必须达到100");
                }
            }
        }
        processService.approveWf(yieldSettle, null);

    }

    public void modifyProStutas(String id) throws RuntimeException {
        WfYieldSettle yieldSettle = queryByPrimaryKey(WfYieldSettle.class, id);
        Project project = new Project();
        project.setId(yieldSettle.getProId());
        project.setProStatus(yieldSettle.getItemStatus());
        modify(Project.class, project);
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#queryYieldSettleList(java.lang.String)
     */
    @Override
    public List<CustYieldSettle> queryYieldSettleList(String userId) throws RuntimeException {
        try {
            return wfYieldSettleDao.selectYieldSettleList(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 
     * @see com.df.tja.service.IWfYieldSettleService#queryYieldSettleListCount(java.lang.String)
     */
    @Override
    public int queryYieldSettleListCount(String userId) throws RuntimeException {
        try {
            return wfYieldSettleDao.selectYieldSettleListCount(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
