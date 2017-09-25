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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.hibernate.persistence.Pagination;
import com.df.framework.sys.domain.SysConfig;
import com.df.framework.sys.service.ISysConfigService;
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
        List<SysConfig> configs = configService.querySysConfigsByParentCode("PM.MAJOR");

        if (configs != null && configs.size() > 0) {
            for (SysConfig sysConfig : configs) {
                OcJsGridModel gridModel = new OcJsGridModel(sysConfig.getConfigCode().replaceAll(".", "_"),
                    sysConfig.getConfigName(),
                    "number", "10%");
                gridModels.add(gridModel);
            }
        }
        JSONArray gridModel = new JSONArray(gridModels);
        String gridModelString = gridModel.toString().substring(1, gridModel.toString().length() - 1);
        outparms.put("gridModel", gridModelString);
    }

    /** 
     * @see com.df.tja.service.IStandardPriceService#queryStandardPrices(com.df.tja.domain.OcStandardPrice, com.df.framework.hibernate.persistence.Pagination)
     */
    @Override
    public List<OcStandardPrice> queryStandardPrices(OcStandardPrice ocStandardPrice, Pagination page)
        throws RuntimeException {
        List<OcStandardPrice> prices = null;
        try {
            prices = standardPriceDao.selectStandardPrices(ocStandardPrice, page);
            //prices = queryByCondition(OcStandardPrice.class, ocStandardPrice, page);
            if (prices != null && !prices.isEmpty()) {
                int number = 0;
                for (OcStandardPrice standardPrice : prices) {
                    OcStandardRatio ocStandardRatio = new OcStandardRatio();
                    ocStandardRatio.setStardandId(standardPrice.getId());
                    List<OcStandardRatio> list = queryByCondition(OcStandardRatio.class, ocStandardRatio);
                    String code = "";
                    String value = "";
                    for (OcStandardRatio ratio : list) {
                        code = code + "," + ratio.getMajorCode().replaceAll(".", "_");
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
    public void createOrModifyStandardPrice(OcStandardPrice ocStandardPrice) throws RuntimeException {

    }
}
