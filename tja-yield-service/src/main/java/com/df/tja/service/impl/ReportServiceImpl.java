/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * ReportServiceImpl.java
 * 
 * 2017年11月21日-下午4:24:53
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.ExcelHelper;
import com.df.project.domain.cust.CustProject;
import com.df.tja.dao.IOcPeriodManageDao;
import com.df.tja.dao.IReportDao;
import com.df.tja.domain.OcPeriodManage;
import com.df.tja.domain.cust.YieldDeptDetial;
import com.df.tja.service.IReportService;

/**
 * <p>ReportServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月21日 下午4:24:53</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service("reportService")
public class ReportServiceImpl extends BaseServiceImpl implements IReportService {

    @Autowired
    private IReportDao reportDao;

    @Autowired
    private IOcPeriodManageDao ocPeriodManageDao;

    /** 
     * @see com.df.tja.service.IReportService#
     * queryYieldDeptDetial(com.df.framework.hibernate.persistence.Pagination, java.util.Map)
     */
    @Override
    public void queryYieldDeptDetial(Pagination page, Map<String, Object> param) throws RuntimeException {
        String orgId = (String) param.get("orgId");
        String periodId = (String) param.get("periodId");
        String orgName = (String) param.get("orgName");

        //期间
        List<OcPeriodManage> periodSelect = ocPeriodManageDao.selectSettlePeriodForRp();
        param.put("periodSelects", periodSelect);
        param.put("periodId", periodId);
        param.put("orgId", orgId);
        param.put("orgName", orgName);

        if (StringUtils.isNoneBlank(orgId)) {
            //查询项目
            List<CustProject> custProjects = reportDao.selectProByperiod(periodId, page);
            List<String> proIds = new ArrayList<String>();
            for (CustProject custProject : custProjects) {
                proIds.add(custProject.getId());
            }
            if (proIds != null && proIds.size() > 0) {
                //项目团队 人员和产值
                List<YieldDeptDetial> deptDetials = reportDao.selectStaffYield(orgId, periodId, proIds);
                //项目 员工 产值 map
                Map<String, Map<String, BigDecimal>> proStaffYields = new HashMap<String, Map<String, BigDecimal>>();

                if (deptDetials != null && deptDetials.size() > 0) {
                    //员工id和姓名  map
                    TreeMap<String, String> staffs = new TreeMap<String, String>();
                    for (YieldDeptDetial deptDetial : deptDetials) {
                        if (deptDetial != null) {
                            staffs.put(deptDetial.getStaffId(), deptDetial.getName());
                            Map<String, BigDecimal> staffYields = null;
                            if (proStaffYields.containsKey(deptDetial.getProId())) {
                                //map中包含项目 取出 项目的 员工 和 产值
                                staffYields = proStaffYields.get(deptDetial.getProId());
                            } else {
                                //map中没有包含项目 新加一个 员工 和 产值的map
                                staffYields = new HashMap<String, BigDecimal>();
                            }
                            staffYields.put(deptDetial.getStaffId(), deptDetial.getStaffYield());
                            proStaffYields.put(deptDetial.getProId(), staffYields);
                        }
                    }
                    //把员工产值 设置到项目中
                    for (CustProject project : custProjects) {
                        Map<String, BigDecimal> staffYields = proStaffYields.get(project.getId());
                        if (staffYields != null && staffYields.size() > 0) {
                            project.setStaffYields(staffYields);
                        }
                    }
                    param.put("staffs", staffs);
                    param.put("custProjects", custProjects);
                }
            }
        }
    }

    /** 
     * @see com.df.tja.service.IReportService#exportDeptDetial(java.util.Map, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    public HSSFWorkbook createExportDeptDetial(Map<String, Object> param, Pagination page)
        throws RuntimeException {
        List<Map<String, String>> sheetContents = new ArrayList<Map<String, String>>();
        Map<String, String> params = new LinkedHashMap<String, String>();
        Map<String, String> content = null;
        this.queryYieldDeptDetial(page, param);

        TreeMap<String, String> staffs = (TreeMap<String, String>) param.get("staffs");

        List<CustProject> custProjects = (List<CustProject>) param.get("custProjects");

        params.put("sheetName", "部门结算产值明细");
        params.put("index", "序号");
        params.put("proCode", "项目编号");
        params.put("proName", "项目名称");

        if (staffs != null && staffs.size() > 0) {
            for (Map.Entry<String, String> entry : staffs.entrySet()) {
                params.put(entry.getKey(), entry.getValue());
            }
            params.put("total", "合计");
            if (custProjects != null && custProjects.size() > 0) {
                for (int i = 0; i < custProjects.size(); i++) {
                    BigDecimal total = new BigDecimal(0);
                    CustProject custProject = custProjects.get(i);
                    content = new HashMap<String, String>();
                    content.put("index", i + 1 + "");
                    content.put("proCode", custProject.getProCode());
                    content.put("proName", custProject.getProName());
                    Map<String, BigDecimal> staffYields = custProject.getStaffYields();
                    for (Map.Entry<String, String> entry : staffs.entrySet()) {
                        BigDecimal yield = null;
                        if (staffYields != null && staffYields.size() > 0) {
                            yield = staffYields.get(entry.getKey()) == null ? new BigDecimal(0) : staffYields.get(entry
                                .getKey());
                        } else {
                            yield = new BigDecimal(0);
                        }
                        content.put(entry.getKey(), yield.toString());
                        total = ArithmeticUtil.add(total, yield);
                    }
                    content.put("total", total.toString());
                    sheetContents.add(content);
                }
            }
        }

        HSSFWorkbook workbook = ExcelHelper.exportExcel(sheetContents, params);
        return workbook;
    }
}
