/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja
 *
 * IDataSyncDao.java
 * 
 * 2017年10月10日-下午3:02:21
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.dao;

import com.df.framework.base.dao.IBaseDao;

/**
 * <p>IDataSyncDao</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月10日 下午3:02:21</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

public interface IDataSyncDao extends IBaseDao {

    /**
     * 
     * <p>描述 : 调用存储过程将接口数据写入业务表中 </p>
     *
     * @param dataType
     * @return
     */
    void writeBackSyncData(String dataType);

}
