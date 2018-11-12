package com.df.tja.dao;


import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.OcScheme;

/**
 * <p>IOcSchemeDao </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月12日 上午11:58:25</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcSchemeDao extends IBaseDao {

    OcScheme queryByProId(String proId);

}
