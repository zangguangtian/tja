package com.df.tja.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
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
            } else {
                List<OcSchemeDivisor> userDivisors = majorNode.getUserDivisors();
                if (userDivisors != null && !userDivisors.isEmpty()) {
                    int i = 1;
                    String userId = HttpUtil.getUser().getId();
                    Date createDate = new Date();
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
}
