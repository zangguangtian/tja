package com.df.tja.service.impl;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.util.LoggerUtil;
import com.df.tja.dao.IOcSchemeDao;
import com.df.tja.domain.OcScheme;
import com.df.tja.service.IOcSchemeService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>OcSchemeServiceImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-12 12:11</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Service("ocSchemeService")
public class OcSchemeServiceImpl extends BaseServiceImpl implements IOcSchemeService {

    @Autowired
    IOcSchemeDao ocSchemeDao;

    @Override
    public OcScheme queryByProId(String proId) {
        return ocSchemeDao.queryByProId(proId);
    }

    @Override
    public void modifyOcScheme(OcScheme ocScheme) {
        try {
            OcScheme ocScheme1 = queryByPrimaryKey(OcScheme.class,ocScheme.getId());
            ocScheme1.setProWbs(ocScheme.getProWbs());
            modify(OcScheme.class,ocScheme1);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public OcScheme addOcScheme(String proId) {
        try {
            OcScheme ocScheme = new OcScheme();
            ocScheme.setProId(proId);
            ocScheme.setProWbs("OC.PROJECT.WBS.SIMPLE");
            ocScheme.setDelFlag(false);
            addEntity(OcScheme.class,ocScheme);
            return ocScheme;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
