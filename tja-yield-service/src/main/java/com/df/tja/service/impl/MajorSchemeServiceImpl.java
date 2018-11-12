package com.df.tja.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.tja.dao.IOcMajorSchemeDao;
import com.df.tja.domain.cust.OcSchemeMajorTask;
import com.df.tja.domain.cust.OcSchemeStageMajor;
import com.df.tja.service.IMajorSchemeService;

@Service
public class MajorSchemeServiceImpl extends BaseServiceImpl implements IMajorSchemeService {

    @Autowired
    private IOcMajorSchemeDao majorSchemeDao;

    @Override
    public OcSchemeStageMajor queryOcSchemeStageMajorById(String majorId) {
        return majorSchemeDao.selectOcSchemeStageMajorById(majorId);
    }

    public List<OcSchemeMajorTask> queryMajorTaskById(String majorId) {
        return majorSchemeDao.selectMajorTaskById(majorId);
    }

}
