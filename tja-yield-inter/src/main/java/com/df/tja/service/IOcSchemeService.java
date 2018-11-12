package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.tja.domain.OcScheme;
import org.apache.poi.ss.formula.functions.T;

/**
 * <p>IOcSchemeService </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018-11-12 12:09</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcSchemeService extends IBaseService {

    OcScheme queryByProId(String proId);

}
