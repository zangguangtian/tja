/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.service
 *
 * IReportService.java
 * 
 * 2017年11月21日-下午4:23:46
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.df.framework.base.service.IBaseService;
import com.df.framework.hibernate.persistence.Pagination;

/**
 * <p>IReportService</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年11月21日 下午4:23:46</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public interface IReportService extends IBaseService {

    /**
     * <p>描述 : </p>
     *
     * @param page
     * @param param
     */
    void queryYieldDeptDetial(Pagination page, Map<String, Object> param) throws RuntimeException;

    /**
     * <p>描述 : </p>
     *
     * @param param
     * @param response
     */
    HSSFWorkbook createExportDeptDetial(Map<String, Object> param, Pagination page) throws RuntimeException;

}
