package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.tja.domain.cust.OcStepFillMore;

import java.util.List;

/**
 * <p>IOcStepFillService </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-23 15:11</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcStepFillService extends IBaseService{

    List<OcStepFillMore> queryByPreProId(String preProId);

}
