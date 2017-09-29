/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * StandardPriceServiceImpl.java
 * 
 * 2017年9月23日-下午2:33:48
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
import com.df.framework.util.StringUtil;
import com.df.tja.constant.TjaConstant;
import com.df.tja.dao.IStandardPriceDao;
import com.df.tja.domain.OcStandardPrice;
import com.df.tja.domain.OcStandardRatio;
import com.df.tja.domain.cust.OcJsGridModel;
import com.df.tja.service.IStandardPriceService;

/**
 * <p>StandardPriceServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月23日 下午2:33:48</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service
public class StandardPriceServiceImpl extends BaseServiceImpl implements IStandardPriceService {

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IStandardPriceDao standardPriceDao;

    /** 
     * @see com.df.tja.service.IStandardPriceService#queryStandardPriceInfo(java.util.Map)
     */
    @Override
    public void queryStandardPriceInfo(Map<String, Object> outparms) throws RuntimeException {
        List<OcJsGridModel> gridModels = new ArrayList<OcJsGridModel>();
        List<SysConfig> configs = configService.querySysConfigsByParentCode(TjaConstant.SysCode.PM_MAJOR_PARENT_CODE);

        if (configs != null && configs.size() > 0) {
            for (SysConfig sysConfig : configs) {
                OcJsGridModel gridModel = new OcJsGridModel();
                gridModel.setCss("checkTotal jsgrid-form-control");
                gridModel.setName(sysConfig.getConfigCode().replaceAll("\\.", "_"));
                gridModel.setTitle(sysConfig.getConfigName());
                gridModel.setType("number");
                gridModel.setValidate("checkTotal");
                gridModel.setWidth("10%");
                gridModel.setFiltering(false);
                gridModels.add(gridModel);
            }
        }
        JSONArray gridModel = new JSONArray(gridModels);
        String gridModelString = gridModel.toString().substring(1, gridModel.toString().length() - 1);
        outparms.put("gridModel", gridModelString);
    }

    /** 
     * @see com.df.tja.service.IStandardPriceService#queryStandardPrices
     * (com.df.tja.domain.OcStandardPrice, com.df.framework.hibernate.persistence.Pagination)
     */
    @Override
    public List<OcStandardPrice> queryStandardPrices(OcStandardPrice ocStandardPrice, Pagination page)
        throws RuntimeException {
        List<OcStandardPrice> prices = null;
        try {
            prices = standardPriceDao.selectStandardPrices(ocStandardPrice, page);
            if (prices != null && !prices.isEmpty()) {
                int number = 0;
                for (OcStandardPrice standardPrice : prices) {
                    OcStandardRatio ocStandardRatio = new OcStandardRatio();
                    ocStandardRatio.setStardandId(standardPrice.getId());
                    List<OcStandardRatio> list = queryByCondition(OcStandardRatio.class, ocStandardRatio);
                    String code = "";
                    String value = "";
                    for (OcStandardRatio ratio : list) {
                        code = code + "," + ratio.getMajorCode().replaceAll("\\.", "_");
                        value = value + "," + ratio.getMajorRatio();

                    }
                    if (StringUtils.isNotBlank(code)) {
                        standardPrice.setCodes(code.substring(1));
                        standardPrice.setValues(value.substring(1));
                    }
                    standardPrice.setNumber((page.getPageNo() - 1) * page.getRowsPerPage() + (++number));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prices;
    }

    /** 
     * @see com.df.tja.service.IStandardPriceService#createOrModifyStandardPrice(com.df.tja.domain.OcStandardPrice)
     */
    @Override
    public void createOrModifyStandardPrice(OcStandardPrice ocStandardPrice) throws RuntimeException, LogicalException {
        try {
            if (StringUtil.isNotBlank(ocStandardPrice.getId())) {
                modify(OcStandardPrice.class, ocStandardPrice);
                String keyValue = ocStandardPrice.getKeyValue();
                if (StringUtils.isNotBlank(keyValue) && keyValue.contains("&")) {
                    String[] codeValues = keyValue.split("&");

                    if (codeValues != null && codeValues.length > 0) {
                        //先删除
                        OcStandardRatio ocStandardRatio = new OcStandardRatio();
                        ocStandardRatio.setStardandId(ocStandardPrice.getId());
                        deleteByObject(OcStandardRatio.class, ocStandardRatio);

                        //再添加
                        for (String codeValue : codeValues) {
                            if (StringUtils.isNotBlank(codeValue) && codeValue.contains("=")) {
                                String[] code = codeValue.split("=");

                                ocStandardRatio = new OcStandardRatio();
                                ocStandardRatio.setMajorCode(code[0]);
                                ocStandardRatio.setMajorRatio(new BigDecimal(code[1]));
                                ocStandardRatio.setStardandId(ocStandardPrice.getId());
                                addEntity(OcStandardRatio.class, ocStandardRatio);

                            } else {
                                //若没有添加  抛出 异常 回滚删除的  数据
                                throw new LogicalException("数据有误!");
                            }
                        }

                    }

                }
            } else {
                if (StringUtil.isNotBlank(ocStandardPrice.getCategoryCode())
                    || StringUtil.isNotBlank(ocStandardPrice.getTypeCode())) {
                    addEntity(OcStandardPrice.class, ocStandardPrice);

                    String keyValue = ocStandardPrice.getKeyValue();
                    if (StringUtils.isNotBlank(keyValue) && keyValue.contains("&")) {
                        String[] codeValues = keyValue.split("&");

                        if (codeValues != null && codeValues.length > 0) {

                            for (String codeValue : codeValues) {
                                if (StringUtils.isNotBlank(codeValue) && codeValue.contains("=")) {
                                    String[] code = codeValue.split("=");
                                    OcStandardRatio ocStandardRatio = new OcStandardRatio();
                                    ocStandardRatio.setMajorCode(code[0]);
                                    ocStandardRatio.setMajorRatio(new BigDecimal(code[1]));
                                    ocStandardRatio.setStardandId(ocStandardPrice.getId());
                                    addEntity(OcStandardRatio.class, ocStandardRatio);
                                }
                            }
                        }

                    }
                }
            }
        } catch (LogicalException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        

    }

    /** 
     * @see com.df.tja.service.IStandardPriceService#deleteStandardPrice(com.df.tja.domain.OcStandardPrice)
     */
    @Override
    public void deleteStandardPrice(OcStandardPrice ocStandardPrice) throws RuntimeException {
        try {
            if (ocStandardPrice != null && StringUtils.isNotBlank(ocStandardPrice.getId())) {
                //删除主表
                deleteByPrimaryKey(OcStandardPrice.class, ocStandardPrice.getId());
                //删除子表
                OcStandardRatio entity = new OcStandardRatio();
                entity.setStardandId(ocStandardPrice.getId());
                deleteByObject(OcStandardRatio.class, entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
