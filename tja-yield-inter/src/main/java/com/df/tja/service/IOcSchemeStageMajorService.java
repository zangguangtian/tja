package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.tja.domain.cust.OcSchemeStageMajor;

import java.util.List;

/**
 * <p>IOcSchemeStageMajorService </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-12 14:39</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcSchemeStageMajorService extends IBaseService {

    List<OcSchemeStageMajor> queryFullList(String proId);

}
