package com.df.tja.dao;

import com.df.framework.base.dao.IBaseDao;
import com.df.tja.domain.cust.OcSchemeDivisorModel;
import org.apache.poi.hwpf.model.FFData;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>IOcSchemeDivisorDao </p>
 *
 * <p>描述： </p>
 *
 * <p>备注：</p>
 *
 * <p>2018年11月12日 上午11:43:15</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public interface IOcSchemeDivisorDao extends IBaseDao{

    /**
     * <p>描述 : 简化模式</p>
     *
     * @param
     * @return
     */
    List<OcSchemeDivisorModel> querySimpleList(String proId);

    /**
     * <p>描述 : 查询比例</p>
     *
     * @param
     * @return
     */
    BigDecimal queryRatioSum(String proId,String parentId);
}