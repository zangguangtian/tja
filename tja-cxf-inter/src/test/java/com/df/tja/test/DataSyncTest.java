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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@ContextConfiguration(locations = { "classpath:/spring.xml" })
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
}
