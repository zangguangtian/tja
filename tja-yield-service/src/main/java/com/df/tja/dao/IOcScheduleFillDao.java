package com.df.tja.dao;

import com.df.framework.base.dao.IBaseDao;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.cust.OcCurrweekSchedule;
import org.apache.poi.hwpf.model.FFData;

import java.util.List;

/**
 * <p>IOcScheduleFillDao </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-27 17:20</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcScheduleFillDao extends IBaseDao {

    /**
     * <p>描述 : 主项进展页面简化模式</p>
     *
     * @param proId
     * @return
     */
    List<OcCurrweekSchedule> selectSimleByProId(String proId,Pagination pagination,Integer state);

    /**
     * <p>描述 : 主项进展页面完整模式</p>
     *
     * @param proId
     * @return
     */
    List<OcCurrweekSchedule> selectFullByProId(String proId,Pagination pagination,Integer state);

}
