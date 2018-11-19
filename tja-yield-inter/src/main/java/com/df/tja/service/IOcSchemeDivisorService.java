package com.df.tja.service;

import com.df.framework.base.service.IBaseService;
import com.df.framework.exception.LogicalException;
import com.df.tja.domain.OcSchemeDivisor;
import com.df.tja.domain.cust.OcSchemeDivisorModel;
import com.df.tja.domain.cust.OcSchemeStageMajor;

import java.util.List;

/**
 * <p>IOcSchemeDivisorService </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-12 20:03</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcSchemeDivisorService extends IBaseService {

    List<OcSchemeDivisorModel> querySimpleList(String proId);

    void addNode(OcSchemeDivisor ocSchemeDivisor) throws LogicalException;

    void modifyRatio(List<OcSchemeStageMajor> ocSchemeStageMajors)throws LogicalException;

    void addUser(OcSchemeDivisor ocSchemeDivisor) throws LogicalException;

    void modifySimple(List<OcSchemeDivisor> ocSchemeDivisors);

}
