package com.df.tja.service.impl;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.dao.IOcStepFillDao;
import com.df.tja.domain.cust.OcStepFillMore;
import com.df.tja.service.IOcStepFillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>OcStepFillServiceImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-23 15:13</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Service
public class OcStepFillServiceImpl extends BaseServiceImpl implements IOcStepFillService {

    @Autowired
    private IOcStepFillDao ocStepFillDao;

    @Override
    public List<OcStepFillMore> queryByPreProId(String preProId,Pagination pagination,Integer state) {
        try{
            return ocStepFillDao.selectByPreProId(preProId,pagination,state);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

}
