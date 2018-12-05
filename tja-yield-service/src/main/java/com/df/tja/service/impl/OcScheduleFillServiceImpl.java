package com.df.tja.service.impl;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.dao.IOcScheduleFillDao;
import com.df.tja.domain.cust.OcCurrweekSchedule;
import com.df.tja.service.IOcScheduleFillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>OcScheduleFillServiceImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-27 17:36</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Service
public class OcScheduleFillServiceImpl extends BaseServiceImpl implements IOcScheduleFillService {

    @Autowired
    private IOcScheduleFillDao ocScheduleFillDao;

    @Override
    public List<OcCurrweekSchedule> querySimleByProId(String proId,Pagination pagination,Integer state) {
        try{
            return ocScheduleFillDao.selectSimleByProId(proId, pagination,state);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OcCurrweekSchedule> queryFullByProId(String proId,Pagination pagination,Integer state) {
        try{
            return ocScheduleFillDao.selectFullByProId(proId,pagination,state);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OcCurrweekSchedule> queryFullMajor(String proId,String majorId ,Pagination pagination) {
        try{
            return ocScheduleFillDao.selectFullMajor(proId,majorId,pagination);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
