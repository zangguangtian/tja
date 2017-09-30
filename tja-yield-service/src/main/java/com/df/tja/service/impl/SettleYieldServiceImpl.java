/**
 * 项目名称:df-pro-service
 *
 * com.df.project.service.impl
 *
 * SettleYieldServiceImpl.java
 * 
 * 2017年9月18日-下午3:53:46
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.framework.util.ExcelHelper;
import com.df.framework.util.FileUtil;
import com.df.framework.util.LoggerUtil;
import com.df.project.domain.Project;
import com.df.tja.dao.IOcSettleYieldDao;
import com.df.tja.domain.OcSettleYield;
import com.df.tja.domain.OcSettleYieldImp;
import com.df.tja.domain.cust.OcSettleYieldMore;
import com.df.tja.service.ISettleYieldService;

/**
 * <p>SettleYieldServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 下午3:53:46</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */
@Service("settleYieldService")
public class SettleYieldServiceImpl extends BaseServiceImpl implements ISettleYieldService {

    @Autowired
    IOcSettleYieldDao ocSettleYieldDao;

    @Override
    public OcSettleYieldMore querySettleYield(String id) throws RuntimeException {
        return ocSettleYieldDao.querySettleYield(id);
    }

    @Override
    public String createOrModifySettleYield(OcSettleYield ocSettleYield) throws RuntimeException {
        String id = ocSettleYield.getId();
        try {
            this.modify(OcSettleYield.class, ocSettleYield);
        } catch (Exception e) {
            LoggerUtil.error(SettleYieldServiceImpl.class, e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return id;
    }

    /** 
     * @see com.df.tja.service.ISettleYieldService#createImpSettleYield
     * (org.springframework.web.multipart.MultipartFile, java.lang.String, java.util.Map)
     */
    @Override
    public void createImpSettleYield(MultipartFile attach, String period, Map<String, Object> results)
        throws RuntimeException {

        try {
            InputStream inputStream = attach.getInputStream();
            Sheet sheet;
            HSSFWorkbook hworkBook;
            XSSFWorkbook xworkBook;
            String format = FileUtil.getFileExtName(attach.getOriginalFilename());
            if (format.equals("xls")) {
                hworkBook = new HSSFWorkbook(new BufferedInputStream(inputStream));
                sheet = hworkBook.getSheetAt(0);
            } else if ("xlsx".equals(format)) {
                xworkBook = new XSSFWorkbook(new BufferedInputStream(inputStream));
                sheet = xworkBook.getSheetAt(0);
            } else {
                throw new LogicalException("无效的excel文件");
            }
            assert sheet != null;
            int maxRowIx = sheet.getLastRowNum();

            Date date = new Date(); // 一次导入的统一导入时间
            StringBuffer log = new StringBuffer("");

            int validRecord = 0;
            for (int rowIx = 1; rowIx <= maxRowIx; rowIx++) {
                boolean flag = true;
                Row row = sheet.getRow(rowIx);
                if (row == null) {
                    continue;
                }

                //项目编号在项目信息表中是否存在（异常信息：项目不存在）；
                Cell cell = row.getCell(0);
                String proCode = ExcelHelper.getCellFormatValue(cell);
                Project entity = new Project();
                entity.setProCode(proCode);
                List<Project> list = queryByCondition(Project.class, entity);
                if (list.size() == 0) {
                    // 该项目不存在
                    log.append("项目不存在\n");
                    flag = false;
                }

                //预估产值是否为>0的有效数值（异常信息：预估产值无效）；
                cell = row.getCell(2);
                String estimateYield = ExcelHelper.getCellFormatValue(cell);
                if (StringUtils.isBlank(estimateYield)) {
                    estimateYield = "0";
                }
                if (!(new BigDecimal(estimateYield).compareTo(BigDecimal.ZERO) > 0)) {
                    log.append("预估产值无效\n");
                    flag = false;
                }

                //可结算产值是否为>0的有效数值（异常信息：预估产值无效）；
                cell = row.getCell(3);
                String settleYield = ExcelHelper.getCellFormatValue(cell);
                if (StringUtils.isBlank(settleYield)) {
                    settleYield = "0";
                }
                if (!(new BigDecimal(settleYield).compareTo(BigDecimal.ZERO) > 0)) {
                    log.append("可结算产值无效\n");
                    flag = false;
                }

                if (flag) {
                    validRecord++; //有效记录数
                }

                OcSettleYieldImp settleYieldImp = new OcSettleYieldImp();
                settleYieldImp.setErrorInfo(log.toString());
                settleYieldImp.setCreateDate(date);
                settleYieldImp.setEstimateYield(new BigDecimal(estimateYield));
                settleYieldImp.setModifyDate(date);
                settleYieldImp.setPeriodId(period);
                settleYieldImp.setSettleYield(new BigDecimal(settleYield));
                if (list.get(0) != null) {
                    settleYieldImp.setProId(list.get(0).getId());
                }

                ocSettleYieldDao.insert(OcSettleYieldImp.class, settleYieldImp);
            }

            if (validRecord == maxRowIx - 1) {
                //本次导入无误  将临时表数据 插入到 正式表
                ocSettleYieldDao.insertMegerSettleYield(date);
            }

        } catch (LogicalException ex) {

        } catch (Exception e) {

        }

    }

}
