package com.df.tja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
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
            }

            if (ocSchemeDivisor == null) {
                throw new LogicalException("未选择节点!");
            }
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
        } catch (LogicalException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
