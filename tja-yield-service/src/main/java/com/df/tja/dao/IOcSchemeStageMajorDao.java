package com.df.tja.dao;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.cust.OcSchemeStageMajor;

import java.util.List;

/**
 * <p>IOcSchemeStageMajorDao </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月12日 上午11:50:45</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcSchemeStageMajorDao extends IBaseDao {

    /**
     * <p>描述 :完整模式 </p>
     *
     * @param
     * @return
     */
    List<OcSchemeStageMajor> queryFullList(String proId);

}
