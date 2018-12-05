package com.df.tja.service.impl;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.util.ArithmeticUtil;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IOcSchemeDivisorDao;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.OcSchemeDivisorModel;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import com.df.tja.service.IOcSchemeDivisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>OcSchemeDivisorServiceImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-12 20:14</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Service
public class OcSchemeDivisorServiceImpl extends BaseServiceImpl implements IOcSchemeDivisorService {

    @Autowired
    private IOcSchemeDivisorDao ocSchemeDivisorDao;

    @Override
    public List<OcSchemeDivisorModel> querySimpleList(String proId) {
        return ocSchemeDivisorDao.querySimpleList(proId);
    }

    @Override
    public void addNode(OcSchemeDivisor ocSchemeDivisor) throws LogicalException {
        try {
            if(ocSchemeDivisor!=null){
                String treePath = null;
                if(StringUtil.isNotBlank(ocSchemeDivisor.getParentId())){
                    //查询比例
                    BigDecimal ratioSum = ocSchemeDivisorDao.queryRatioSum(ocSchemeDivisor.getProId(),ocSchemeDivisor.getParentId());
                    BigDecimal sum = ArithmeticUtil.add(ratioSum, ocSchemeDivisor.getSchemeRatio());
                    if(sum.compareTo(new BigDecimal(100)) > 0){
                        throw new LogicalException("专业比例之和不能超过100%!");
                    }

                    OcSchemeDivisor parent = queryByPrimaryKey(OcSchemeDivisor.class,ocSchemeDivisor.getParentId());
                    treePath = parent.getTreePath();
                    ocSchemeDivisor.setDivisorGrade(2);
                }else{
                    //查询比例
                    BigDecimal ratioSum = ocSchemeDivisorDao.queryRatioSum(ocSchemeDivisor.getProId(),null);
                    BigDecimal sum = ArithmeticUtil.add(ratioSum, ocSchemeDivisor.getSchemeRatio());
                    if(sum.compareTo(new BigDecimal(100)) > 0){
                        throw new LogicalException("阶段比例之和不能超过100%!");
                    }
                    treePath = ocSchemeDivisor.getProId()+"@";
                    ocSchemeDivisor.setDivisorGrade(1);
                }
                String id = addEntity(OcSchemeDivisor.class,ocSchemeDivisor);
                ocSchemeDivisor.setTreePath(treePath+id+"@");
            }
        }catch (LogicalException ex) {
            throw ex;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyRatio(List<OcSchemeStageMajor> ocSchemeStageMajors) throws LogicalException{
        try{
            //专业比例
            String key = "1";
            BigDecimal sum = new BigDecimal(0);
            for (OcSchemeStageMajor ocSchemeStageMajor: ocSchemeStageMajors) {
                if(ocSchemeStageMajor.getSchemeMajorRatio() != null) {
                    if (key.equals("1")) {
                        key = ocSchemeStageMajor.getSchemeStageId();
                    }
                    if (ocSchemeStageMajor.getSchemeStageId().equals(key)) {
                        sum = ArithmeticUtil.add(sum, ocSchemeStageMajor.getSchemeMajorRatio());
                        if (sum.compareTo(new BigDecimal(100)) > 0) {
                            throw new LogicalException("专业比例之和不能超过100%!");
                        }
                    } else {
                        key = ocSchemeStageMajor.getSchemeStageId();
                        sum = new BigDecimal(0);
                    }
                }
            }

            for (int i = 0;i < ocSchemeStageMajors.size();i++) {
                if(StringUtil.isNotBlank(ocSchemeStageMajors.get(i).getSchemeMajorId())) {
                    OcSchemeDivisor major = queryByPrimaryKey(OcSchemeDivisor.class, ocSchemeStageMajors.get(i).getSchemeMajorId());
                    major.setSchemeRatio(ocSchemeStageMajors.get(i).getSchemeMajorRatio());
                    modify(OcSchemeDivisor.class, major);
                }
                if(i>0){
                    if(ocSchemeStageMajors.get(i).getSchemeStageId().equals(ocSchemeStageMajors.get(i-1).getSchemeStageId())){
                        continue;
                    }
                }
                OcSchemeDivisor stage = queryByPrimaryKey(OcSchemeDivisor.class,ocSchemeStageMajors.get(i).getSchemeStageId());
                stage.setSchemeRatio(ocSchemeStageMajors.get(i).getSchemeStageRatio());
                modify(OcSchemeDivisor.class,stage);
            }
        }catch (LogicalException ex) {
            throw ex;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(OcSchemeDivisor ocSchemeDivisor) throws LogicalException {
        try{
            if(ocSchemeDivisor != null){
                //查询比例
                BigDecimal ratioSum = ocSchemeDivisorDao.queryRatioSum(ocSchemeDivisor.getProId(),null);
                BigDecimal sum = ArithmeticUtil.add(ratioSum, ocSchemeDivisor.getSchemeRatio());
                if(sum.compareTo(new BigDecimal(100)) > 0){
                    throw new LogicalException("人员比例之和不能超过100%!");
                }

                ocSchemeDivisor.setDivisorGrade(5);
                addEntity(OcSchemeDivisor.class,ocSchemeDivisor);
            }
        }catch (LogicalException ex) {
            throw ex;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifySimple(List<OcSchemeDivisor> ocSchemeDivisors) {
        try{
            if(ocSchemeDivisors != null){
                for (OcSchemeDivisor ocSchemeDivisor:ocSchemeDivisors) {
                    if(StringUtil.isNotBlank(ocSchemeDivisor.getId())){
                        OcSchemeDivisor ocSchemeDivisor1 = (OcSchemeDivisor)queryByPrimaryKey(OcSchemeDivisor.class,ocSchemeDivisor.getId());
                        ocSchemeDivisor1.setSchemeRatio(ocSchemeDivisor.getSchemeRatio());
                        ocSchemeDivisor1.setRemark(ocSchemeDivisor.getRemark());
                        modify(OcSchemeDivisor.class,ocSchemeDivisor1);
                    }
                }
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OcSchemeDivisor> queryStageMajor(String proId, String parentId) throws LogicalException {
        List<OcSchemeDivisor> ocSchemeDivisors = new ArrayList<>();
        try{
            if(StringUtil.isNotBlank(parentId)){
                ocSchemeDivisors = ocSchemeDivisorDao.selectStageMajor(proId,parentId);
                if(ocSchemeDivisors == null || ocSchemeDivisors.size() < 1){
                    throw new LogicalException("没有专业信息！");
                }
            }else{
                ocSchemeDivisors = ocSchemeDivisorDao.selectStageMajor(proId,parentId);
                if(ocSchemeDivisors == null || ocSchemeDivisors.size() < 1){
                    throw new LogicalException("没有阶段信息！");
                }
            }
        }catch (LogicalException e) {
            e.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ocSchemeDivisors;
    }
}
