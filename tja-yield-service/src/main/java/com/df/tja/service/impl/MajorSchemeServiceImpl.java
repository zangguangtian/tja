package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.log.LoggerProxy;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.HttpUtil;
import com.df.tja.dao.IOcMajorSchemeDao;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.CustSchemeMajorNode;
import com.df.tja.domain.cust.OcSchemeMajorTask;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import com.df.tja.service.IMajorSchemeService;

@Service
public class MajorSchemeServiceImpl extends BaseServiceImpl implements IMajorSchemeService {

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private IOcMajorSchemeDao majorSchemeDao;

    @Override
    public OcSchemeStageMajor queryOcSchemeStageMajorById(String majorId) {
        return majorSchemeDao.selectOcSchemeStageMajorById(majorId);
    }

    public List<OcSchemeMajorTask> queryMajorTaskById(String majorId) {
        return majorSchemeDao.selectMajorTaskById(majorId);
    }

    public void createSchemeMajorNode(CustSchemeMajorNode majorNode) throws LogicalException {
        try {
            Integer divisorSort = null;
            OcSchemeDivisor ocSchemeDivisor = null;
            if ("s".equals(majorNode.getNodeCategory())) { //子项
                divisorSort = majorNode.getSubSort();
                ocSchemeDivisor = majorSchemeDao.selectByPrimaryKey(OcSchemeDivisor.class, majorNode.getMajorId());
            } else if ("t".equals(majorNode.getNodeCategory())) { //节点
                divisorSort = majorNode.getTaskSort();
                ocSchemeDivisor = majorSchemeDao.selectByPrimaryKey(OcSchemeDivisor.class, majorNode.getSubId());
            } else if ("u".equals(majorNode.getNodeCategory())) { //员工
                ocSchemeDivisor = majorSchemeDao.selectByPrimaryKey(OcSchemeDivisor.class, majorNode.getTaskId());
            }
            if (ocSchemeDivisor == null) {
                throw new LogicalException("未选择节点!");
            }
            if (!"u".equals(majorNode.getNodeCategory())) {
                OcSchemeDivisor schemeDivisor = new OcSchemeDivisor();
                schemeDivisor.setSchemeId(ocSchemeDivisor.getSchemeId());
                schemeDivisor.setProId(ocSchemeDivisor.getProId());
                schemeDivisor.setParentId(ocSchemeDivisor.getId());
                schemeDivisor.setDivisorName(majorNode.getDivisorName());
                schemeDivisor.setSchemeRatio(majorNode.getSchemeRatio());
                schemeDivisor.setDivisorGrade(ocSchemeDivisor.getDivisorGrade() + 1);
                schemeDivisor.setDivisorSort(divisorSort);
                majorSchemeDao.insert(OcSchemeDivisor.class, schemeDivisor);

                schemeDivisor.setTreePath(ocSchemeDivisor.getTreePath() + schemeDivisor.getId() + "@");

                BigDecimal schemeRatio = majorSchemeDao.selectTotalSchemeRatioByPid(ocSchemeDivisor.getId());
                schemeRatio = ArithmeticUtil.add(schemeRatio, majorNode.getSchemeRatio());
                if (schemeRatio.compareTo(new BigDecimal(100)) > 0) {
                    LogicalException ratioException = null;
                    if ("s".equals(majorNode.getNodeCategory())) {
                        SysConfig sysconfig = sysConfigService.querySysConfigByCode(ocSchemeDivisor.getDivisorName());

                        ratioException = new LogicalException(
                            sysconfig.getConfigName() + "专业下的子项策划比例之和不能超过100%!");
                    } else {
                        ratioException = new LogicalException(
                            ocSchemeDivisor.getDivisorName() + "子项下的任务策划比例之和不能超过100%!");
                    }
                    throw ratioException;
                }
            } else {
                List<OcSchemeDivisor> userDivisors = majorNode.getUserDivisors();
                if (userDivisors != null && !userDivisors.isEmpty()) {
                    int i = 0;
                    String userId = HttpUtil.getUser().getId();
                    Date createDate = new Date();

                    BigDecimal schemeRatio = majorSchemeDao.selectTotalSchemeRatioByPid(ocSchemeDivisor.getId());
                    for (OcSchemeDivisor divisor : userDivisors) {
                        divisor.setSchemeId(ocSchemeDivisor.getSchemeId());
                        divisor.setProId(ocSchemeDivisor.getProId());
                        divisor.setParentId(ocSchemeDivisor.getId());
                        divisor.setDivisorSort(majorNode.getUserSort() + i);
                        divisor.setDivisorGrade(ocSchemeDivisor.getDivisorGrade() + 1);
                        divisor.setCreator(userId);
                        divisor.setCreateDate(createDate);
                        divisor.setModifier(userId);
                        divisor.setModifyDate(createDate);
                        i++;

                        schemeRatio = ArithmeticUtil.add(schemeRatio, divisor.getSchemeRatio());
                    }

                    if (schemeRatio.compareTo(new BigDecimal(100)) > 0) {
                        throw new LogicalException(ocSchemeDivisor.getDivisorName() + "任务下的人员策划比例之和不能超过100%!");
                    }

                    //批量插入
                    majorSchemeDao.batchInsert(OcSchemeDivisor.class, userDivisors);

                    //批量更新tree_path
                    majorSchemeDao.updateMajorTreePath(ocSchemeDivisor.getId(), majorNode.getUserSort());
                }
            }
        } catch (LogicalException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void modifySchemeMajorByBatch(List<OcSchemeDivisor> divisors) throws LogicalException {
        try {
            if (divisors == null || divisors.isEmpty()) {
                throw new LogicalException("没有需要修改的专业策划比例!");
            }

            BigDecimal totalRatio = null;
            Map<String, BigDecimal> childRatios = new HashMap<String, BigDecimal>(0);
            for (OcSchemeDivisor divisor : divisors) {
                totalRatio = new BigDecimal(0);
                totalRatio = ArithmeticUtil.add(totalRatio, divisor.getSchemeRatio());
                if (childRatios.containsKey(divisor.getParentId())) {
                    totalRatio = ArithmeticUtil.add(childRatios.get(divisor.getParentId()), totalRatio);
                }
                childRatios.put(divisor.getParentId(), totalRatio);

                divisor.setParentId(null);//不修改parentId
                modify(OcSchemeDivisor.class, divisor);
            }
            
            String id = null;
            boolean flag = false;
            for (String key : childRatios.keySet()) {
                if (new BigDecimal(100).compareTo(childRatios.get(key)) < 0) {
                    flag = true;
                    id = key;
                    break;
                }
            }
            if (flag) {
                OcSchemeDivisor divisor = queryByPrimaryKey(OcSchemeDivisor.class, id);
                throw new LogicalException(divisor.getDivisorName() + "子列表的比例之和大于100%!");
            }
        } catch (LogicalException ex) {
            throw ex;
        } catch (Exception ex) {
            LoggerProxy.error(null, ex);
            throw new RuntimeException(ex);
        }
    }
}
