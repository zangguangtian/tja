/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * OcDesignScheduleServiceImpl.java
 * 
 * 2018年11月20日-下午2:47:37
 *
 * 2018 上海一勤信息技术有限公司-版权所有 
 */

package com.df.tja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.tja.dao.IOcDesignScheduleDao;
import com.df.tja.domain.cust.CustOcDesignSchedule;
import com.df.tja.service.IOcDesignScheduleService;

/**
 * <p>OcDesignScheduleServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2018年11月20日 下午2:47:37</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Service
public class OcDesignScheduleServiceImpl implements IOcDesignScheduleService {

    @Autowired
    private IOcDesignScheduleDao designScheduleDao;
    
    public List<CustOcDesignSchedule> queryDesignSchedulesById(String phaseId) {
        return designScheduleDao.selectDesignSchedulesById(phaseId);
    }

}
