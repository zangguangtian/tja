package com.df.tja.service.impl;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.tja.dao.IOcSchemeDao;
import com.df.tja.dao.IOcSchemeStageMajorDao;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import com.df.tja.service.IOcSchemeStageMajorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>OcSchemeStageMajorServiceImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-12 14:40</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public class OcSchemeStageMajorServiceImpl extends BaseServiceImpl implements IOcSchemeStageMajorService {

    @Autowired
    IOcSchemeStageMajorDao ocSchemeStageMajorDao;

    @Override
    public List<OcSchemeStageMajor> queryFullList(String proId) {
        return ocSchemeStageMajorDao.queryFullList(proId);
    }
}
