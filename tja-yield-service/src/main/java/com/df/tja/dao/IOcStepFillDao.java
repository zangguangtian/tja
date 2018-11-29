package com.df.tja.dao;

import com.df.framework.base.dao.IBaseDao;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.tja.domain.cust.OcStepFillMore;

import java.util.List;

/**
 * <p>IOcStepFillDao </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-23 14:49</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcStepFillDao extends IBaseDao {

    List<OcStepFillMore> selectByPreProId(String preProId,Pagination pagination,Integer state);

}
