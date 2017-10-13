/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.test
 *
 * DataSyncTest.java
 * 
 * 2017年10月11日-下午4:02:02
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.df.tja.domain.ItProContractInfo;
import com.df.tja.domain.ItProPhasesInfo;
import com.df.tja.domain.ItProPhasesMajor;
import com.df.tja.domain.ItProPhasesUser;
import com.df.tja.domain.ItProjectInfo;
import com.df.tja.service.IDataSyncService;

/**
 * <p>DataSyncTest</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月11日 下午4:02:02</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
//使用junit4进行测试  
@ContextConfiguration(locations = {"classpath:/spring.xml"})
//加载配置文件 
public class DataSyncTest {

    @Autowired
    private IDataSyncService dataSyncService;

    @Test
    public void testSyncDept() {
        String value = dataSyncService.createAndQuerySyncData("getDepts", "");
        dataSyncService.syncDepts(value);
    }

    @Test
    public void testSyncEmployee() {
        String value = dataSyncService.createAndQuerySyncData("getEmployees", "");
        dataSyncService.syncEmployees(value);
    }

    @Test
    public void testSyncAccounts() {
        String value = dataSyncService.createAndQuerySyncData("getAccounts", "");
        dataSyncService.syncAccounts(value);
    }

    @Test
    public void testSyncItems() {
        String value = dataSyncService.createAndQuerySyncData("getItems", "");
        dataSyncService.syncItems(value);
    }

    @Test
    public void testSyncContractOfItem() {
        List<ItProjectInfo> projects = dataSyncService.queryByCondition(ItProjectInfo.class, null);
        if (projects != null && !projects.isEmpty()) {
            //同步前先将接口表中的数据清空
            ItProContractInfo delObj = new ItProContractInfo();
            delObj.setContractType("1000");
            dataSyncService.deleteByObject(ItProContractInfo.class, delObj);
            for (ItProjectInfo projectInfo : projects) {
                String value = dataSyncService.createAndQuerySyncData("getContractOfItem", projectInfo.getId());
                dataSyncService.syncContractOfItem(value);
            }
        }
    }

    @Test
    public void testSyncEpibolyContractOfItem() {
        List<ItProjectInfo> projects = dataSyncService.queryByCondition(ItProjectInfo.class, null);
        if (projects != null && !projects.isEmpty()) {
            //同步前先将接口表中的数据清空
            ItProContractInfo delObj = new ItProContractInfo();
            delObj.setContractType("2000");
            dataSyncService.deleteByObject(ItProContractInfo.class, delObj);
            for (ItProjectInfo projectInfo : projects) {
                String value = dataSyncService.createAndQuerySyncData("getEpibolyContractOfItem", projectInfo.getId());
                dataSyncService.syncEpibolyContractOfItem(value);
            }
        }
    }

    @Test
    public void testSyncCostOfItem() {
        List<ItProjectInfo> projects = dataSyncService.queryByCondition(ItProjectInfo.class, null);
        if (projects != null && !projects.isEmpty()) {
            for (ItProjectInfo projectInfo : projects) {
                String value = dataSyncService.createAndQuerySyncData("getCostOfItem", projectInfo.getId());
                dataSyncService.syncCostOfItem(value, projectInfo.getId(), projectInfo.getCreateDate());
            }
        }
    }

    @Test
    public void testSyncItemWbsInfo() {
        List<ItProjectInfo> projects = dataSyncService.queryByCondition(ItProjectInfo.class, null);
        if (projects != null && !projects.isEmpty()) {
            //同步前先将接口表中的数据清空
            dataSyncService.deleteByObject(ItProPhasesInfo.class, null);
            dataSyncService.deleteByObject(ItProPhasesMajor.class, null);
            dataSyncService.deleteByObject(ItProPhasesUser.class, null);

            for (ItProjectInfo projectInfo : projects) {
                String value = dataSyncService.createAndQuerySyncData("getItemWbsInfo", projectInfo.getId());
                dataSyncService.syncItemWbsInfo(value, projectInfo.getId());
            }
        }
    }

}
