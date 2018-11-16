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

    /**
     * <p>描述 :根据项目ID查询策划 </p>
     *
     * @param proId
     * @return OcScheme
     */
    OcScheme queryByProId(String proId);

    /**
     * <p>描述 :策划WBS更新 </p>
     *
     * @param
     * @return
     */
    void modifyOcScheme(OcScheme ocScheme);

    /**
     * <p>描述 :添加项目WBS </p>
     *
     * @param
     * @return
     */
    OcScheme addOcScheme(String proId);
}
