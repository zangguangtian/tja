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

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.log.LoggerProxy;
import com.df.tja.dao.IOcDesignScheduleDao;
import com.df.tja.domain.OcScheduleFill;
import com.df.tja.domain.cust.CustOcDesignSchedule;
import com.df.tja.service.IOcDesignScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class OcDesignScheduleServiceImpl extends BaseServiceImpl implements IOcDesignScheduleService {

    @Autowired
    private IOcDesignScheduleDao designScheduleDao;

    @Override
    public List<CustOcDesignSchedule> queryDesignSchedulesById(String phaseId) {
        return designScheduleDao.selectDesignSchedulesById(phaseId);
    }

    @Override
    public void createDesignSchedules(CustOcDesignSchedule designSchedule) {
        try {
            String scheduleId = designSchedule.getScheduleId();
            //先删除
            OcScheduleFill entity = new OcScheduleFill();
            entity.setScheduleId(scheduleId);
            designScheduleDao.deleteByObject(OcScheduleFill.class, entity);
            
            //再插入
            List<CustOcDesignSchedule> schedules = designSchedule.getSchedules();
            if(schedules != null && !schedules.isEmpty()) {
                OcScheduleFill fill = null;
                for(CustOcDesignSchedule schedule : schedules) {
                    fill = new OcScheduleFill();
                    fill.setProId(designSchedule.getProId());
                    fill.setScheduleId(designSchedule.getScheduleId());
                    fill.setSchemeId(designSchedule.getSchemeId());
                    fill.setDivisorId(schedule.getUserId());
                    fill.setCurrSchedule(schedule.getCurrSchedule());
                    fill.setScheduleStatus(schedule.getScheduleStatus());
                    fill.setRemark(schedule.getRemark());
                    addEntity(OcScheduleFill.class, fill);
                }
            }
            //更新上周进度
            mergeDesignPreSchedule(scheduleId);
        }catch(Exception ex) {
            LoggerProxy.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mergeDesignPreSchedule(String scheduleId) {
        designScheduleDao.updateDesignPreSchedule(scheduleId);
    }

}
