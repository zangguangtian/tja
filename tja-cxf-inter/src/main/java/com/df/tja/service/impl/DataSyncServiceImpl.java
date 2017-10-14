/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.service.impl
 *
 * DataSyncServiceImpl.java
 * 
 * 2017年10月10日-上午11:55:27
 *
 * 2017 TabZhu-版权所有 
 */

package com.df.tja.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tempuri.Result;
import org.tempuri.Service1Soap;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.util.DateUtil;
import com.df.framework.util.LoggerUtil;
import com.df.framework.util.StringUtil;
import com.df.tja.dao.IDataSyncDao;
import com.df.tja.domain.ItAccountInfo;
import com.df.tja.domain.ItCallRecord;
import com.df.tja.domain.ItDeptInfo;
import com.df.tja.domain.ItProContractInfo;
import com.df.tja.domain.ItProPhasesInfo;
import com.df.tja.domain.ItProPhasesMajor;
import com.df.tja.domain.ItProPhasesUser;
import com.df.tja.domain.ItProjectInfo;
import com.df.tja.domain.ItStaffInfo;
import com.df.tja.service.IDataSyncService;

/**
 * <p>DataSyncServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月10日 上午11:55:27</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@Service
public class DataSyncServiceImpl extends BaseServiceImpl implements IDataSyncService {

    @Autowired
    private Service1Soap tjaWsClient;

    @Autowired
    private IDataSyncDao dataSyncDao;

    public String createAndQuerySyncData(String itName, String... itArgs) throws RuntimeException {
        String value = null;
        try {
            Result result = null;
            ItCallRecord itCallRecord = new ItCallRecord();
            itCallRecord.setInMethod(itName);
            //此方法只能是放在调用前
            itCallRecord.setReqDate(new Date());
            switch (itName) {
                case "getDepts":
                    result = tjaWsClient.getDepts();
                    break;
                case "getEmployees":
                    result = tjaWsClient.getEmployees();
                    break;
                case "getAccounts":
                    result = tjaWsClient.getAccounts();
                    break;
                case "getItems":
                    result = tjaWsClient.getItems();
                    break;
                case "getContractOfItem":
                    itCallRecord.setReqArgs(itArgs[0]);
                    result = tjaWsClient.getContractOfItem(itArgs[0]);
                    break;
                case "getEpibolyContractOfItem":
                    itCallRecord.setReqArgs(itArgs[0]);
                    result = tjaWsClient.getEpibolyContractOfItem(itArgs[0]);
                    break;
                case "getCostOfItem":
                    itCallRecord.setReqArgs(itArgs[0]);
                    result = tjaWsClient.getCostOfItem(itArgs[0]);
                    break;
                case "getItemWbsInfo":
                    itCallRecord.setReqArgs(itArgs[0]);
                    result = tjaWsClient.getItemWbsInfo(itArgs[0]);
                    break;
                default:
                    break;
            }
            //此方法只能是放在调用后
            itCallRecord.setResDate(new Date());
            if (result != null) {
                if (result.isIsSucc()) {
                    value = result.getValue();
                }
                itCallRecord.setResText(result.toString());
            }
            itCallRecord.setCreateDate(new Date());
            dataSyncDao.insert(ItCallRecord.class, itCallRecord);
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
        return value;
    }

    public void syncDepts(String value) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                //同步前先将接口表中的数据清空
                dataSyncDao.deleteByObject(ItDeptInfo.class, null);

                ItDeptInfo itDeptInfo = null;
                JSONObject jsonObj = null;
                Date syncDate = new Date();
                List<ItDeptInfo> depts = new ArrayList<ItDeptInfo>(0);
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itDeptInfo = new ItDeptInfo();
                    itDeptInfo.setId(jsonObj.getString("Id"));
                    itDeptInfo.setParentId(jsonObj.getString("ParentId"));
                    itDeptInfo.setIdPath(jsonObj.getString("IdPath"));
                    itDeptInfo.setName(jsonObj.getString("Name"));
                    itDeptInfo.setSortIndex(jsonObj.getInt("SortIndex"));
                    itDeptInfo.setCreateDate(syncDate);
                    depts.add(itDeptInfo);
                }
                dataSyncDao.batchInsert(ItDeptInfo.class, depts);
                dataSyncDao.writeBackSyncData("syncDept");
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncEmployees(String value) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                //同步前先将接口表中的数据清空
                dataSyncDao.deleteByObject(ItStaffInfo.class, null);

                ItStaffInfo itStaffInfo = null;
                JSONObject jsonObj = null;
                Date syncDate = new Date();
                List<ItStaffInfo> staffs = new ArrayList<ItStaffInfo>(0);
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itStaffInfo = new ItStaffInfo();
                    itStaffInfo.setId(jsonObj.getString("Id"));
                    itStaffInfo.setWorkNo(jsonObj.getString("WorkNo"));
                    itStaffInfo.setName(jsonObj.getString("Name"));
                    itStaffInfo.setPinYin(jsonObj.getString("Pinyin"));
                    itStaffInfo.setDeptId(jsonObj.getString("DeptId"));
                    itStaffInfo.setEntryDate(DateUtil.valueOfStandard(jsonObj.getString("InCompanyDate")));
                    itStaffInfo.setStatus(jsonObj.getString("IsDimission"));
                    itStaffInfo.setEmail(jsonObj.getString("EMail"));
                    itStaffInfo.setAccountId(jsonObj.getString("AccountId"));
                    itStaffInfo.setCreateDate(syncDate);
                    staffs.add(itStaffInfo);
                }
                dataSyncDao.batchInsert(ItStaffInfo.class, staffs);
                dataSyncDao.writeBackSyncData("syncEmployee");
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncAccounts(String value) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                //同步前先将接口表中的数据清空
                dataSyncDao.deleteByObject(ItAccountInfo.class, null);

                ItAccountInfo itAccountInfo = null;
                JSONObject jsonObj = null;
                Date syncDate = new Date();
                List<ItAccountInfo> accounts = new ArrayList<ItAccountInfo>(0);
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itAccountInfo = new ItAccountInfo();
                    itAccountInfo.setId(jsonObj.getString("Id"));
                    itAccountInfo.setAccountName(jsonObj.getString("AccountName"));
                    itAccountInfo.setWorkNo(jsonObj.getString("WorkNo"));
                    itAccountInfo.setUserName(jsonObj.getString("UserName"));
                    itAccountInfo.setCreateDate(syncDate);
                    accounts.add(itAccountInfo);
                }
                dataSyncDao.batchInsert(ItAccountInfo.class, accounts);
                dataSyncDao.writeBackSyncData("syncAccount");
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncItems(String value) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                //同步前先将接口表中的数据清空
                dataSyncDao.deleteByObject(ItProjectInfo.class, null);

                ItProjectInfo itProjectInfo = null;
                JSONObject jsonObj = null;
                Date syncDate = new Date();
                List<ItProjectInfo> projects = new ArrayList<ItProjectInfo>(0);
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProjectInfo = new ItProjectInfo();
                    itProjectInfo.setId(jsonObj.getString("Id"));
                    itProjectInfo.setItemCode(jsonObj.getString("ItemCode"));
                    itProjectInfo.setItemName(jsonObj.getString("ItemName"));
                    itProjectInfo.setItemStyle(jsonObj.getString("ItemStyle"));
                    itProjectInfo.setDutyDeptId(jsonObj.getString("DutyDeptId"));
                    itProjectInfo.setDutyDeptName(jsonObj.getString("DutyDeptName"));
                    itProjectInfo.setPrincipalId(jsonObj.getString("PrincipalId"));
                    itProjectInfo.setPrincipalName(jsonObj.getString("PrincipalName"));
                    itProjectInfo.setPrjmanagerId(jsonObj.getString("PrjManagerId"));
                    itProjectInfo.setPrjmanagerName(jsonObj.getString("PrjManagerName"));
                    itProjectInfo.setCreateDate(syncDate);
                    projects.add(itProjectInfo);
                }
                dataSyncDao.batchInsert(ItProjectInfo.class, projects);
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncContractOfItem(String value) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                ItProContractInfo itProContractInfo = null;
                JSONObject jsonObj = null;
                Date syncDate = new Date();
                List<ItProContractInfo> list = new ArrayList<ItProContractInfo>(0);
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProContractInfo = new ItProContractInfo();
                    itProContractInfo.setContractId(jsonObj.getString("Id"));
                    itProContractInfo.setContractType("1000");
                    itProContractInfo.setContractCode(jsonObj.getString("ContractCode"));
                    itProContractInfo.setContractName(jsonObj.getString("ContractName"));
                    itProContractInfo.setContractMoney(BigDecimal.valueOf(jsonObj.getDouble("ContractMoney")));
                    itProContractInfo.setItemId(jsonObj.getString("ItemId"));
                    itProContractInfo.setCreateDate(syncDate);
                    list.add(itProContractInfo);
                }
                dataSyncDao.batchInsert(ItProContractInfo.class, list);
                dataSyncDao.writeBackSyncData("syncContractOfItem");
            }
        } catch (Exception ex) {
            System.out.println(value);
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncEpibolyContractOfItem(String value) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                ItProContractInfo itProContractInfo = null;
                JSONObject jsonObj = null;
                Date syncDate = new Date();
                List<ItProContractInfo> list = new ArrayList<ItProContractInfo>(0);
                JSONArray jsonArray = new JSONArray(value);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProContractInfo = new ItProContractInfo();
                    itProContractInfo.setContractId(jsonObj.getString("Id"));
                    itProContractInfo.setContractType("2000");
                    itProContractInfo.setContractCode(jsonObj.getString("ContractCode"));
                    itProContractInfo.setContractName(jsonObj.getString("ContractName"));
                    itProContractInfo.setContractMoney(BigDecimal.valueOf(jsonObj.getDouble("ContractMoney")));
                    itProContractInfo.setItemId(jsonObj.getString("ItemId"));
                    itProContractInfo.setCreateDate(syncDate);
                    list.add(itProContractInfo);
                }
                dataSyncDao.batchInsert(ItProContractInfo.class, list);
                dataSyncDao.writeBackSyncData("syncEpibolyContractOfItem");
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncCostOfItem(String value, String itemId, Date createDate) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                dataSyncDao.updateCostOfItem(new BigDecimal(value), itemId);
                dataSyncDao.writeBackSyncData("syncItem");
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public void syncItemWbsInfo(String value, String itemId) throws RuntimeException {
        try {
            if (StringUtil.isNotBlank(value)) {
                ItProPhasesInfo itProPhasesInfo = null;
                ItProPhasesMajor itProPhasesMajor = null;
                ItProPhasesUser itProPhasesUser = null;
                Date syncDate = new Date();
                JSONObject jsonObj = null;
                JSONObject jsonObject = new JSONObject(value);

                JSONArray jsonArray = jsonObject.getJSONArray("PrjPhases");
                List<ItProPhasesInfo> prjPhases = new ArrayList<ItProPhasesInfo>(0);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProPhasesInfo = new ItProPhasesInfo();
                    itProPhasesInfo.setId(jsonObj.getString("Id"));
                    itProPhasesInfo.setName(jsonObj.getString("Name"));
                    itProPhasesInfo.setItemId(itemId);
                    itProPhasesInfo.setCreateDate(syncDate);
                    prjPhases.add(itProPhasesInfo);
                }
                dataSyncDao.batchInsert(ItProPhasesInfo.class, prjPhases);

                jsonArray = jsonObject.getJSONArray("SubEntrys");
                List<ItProPhasesInfo> subEntrys = new ArrayList<ItProPhasesInfo>(0);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProPhasesInfo = new ItProPhasesInfo();
                    itProPhasesInfo.setId(jsonObj.getString("Id"));
                    itProPhasesInfo.setName(jsonObj.getString("Name"));
                    itProPhasesInfo.setPrjPhaseId(jsonObj.getString("PrjPhaseId"));
                    itProPhasesInfo.setItemId(itemId);
                    itProPhasesInfo.setCreateDate(syncDate);
                    subEntrys.add(itProPhasesInfo);
                }
                dataSyncDao.batchInsert(ItProPhasesInfo.class, subEntrys);

                jsonArray = jsonObject.getJSONArray("Majors");
                List<ItProPhasesMajor> majors = new ArrayList<ItProPhasesMajor>(0);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProPhasesMajor = new ItProPhasesMajor();
                    itProPhasesMajor.setMajorCode(jsonObj.getString("MajorCode"));
                    itProPhasesMajor.setMajorName(jsonObj.getString("MajorName"));
                    itProPhasesMajor.setPrjPhaseId(jsonObj.getString("PrjPhaseId"));
                    itProPhasesMajor.setItemId(itemId);
                    itProPhasesMajor.setCreateDate(syncDate);
                    majors.add(itProPhasesMajor);
                }
                dataSyncDao.batchInsert(ItProPhasesMajor.class, majors);

                jsonArray = jsonObject.getJSONArray("Users");
                List<ItProPhasesUser> users = new ArrayList<ItProPhasesUser>(0);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObj = (JSONObject) jsonArray.get(i);
                    itProPhasesUser = new ItProPhasesUser();
                    itProPhasesUser.setUserId(jsonObj.getString("Id"));
                    itProPhasesUser.setName(jsonObj.getString("Name"));
                    itProPhasesUser.setPrjPhaseId(jsonObj.getString("PrjPhaseId"));
                    itProPhasesUser.setSubEntryId(jsonObj.getString("SubEntryId"));
                    itProPhasesUser.setItemId(itemId);
                    itProPhasesUser.setMajorCode(jsonObj.getString("MajorCode"));
                    itProPhasesUser.setMajorName(jsonObj.getString("MajorName"));
                    itProPhasesUser.setRoleKey(jsonObj.getString("RoleKey"));
                    itProPhasesUser.setRoleName(jsonObj.getString("RoleName"));
                    itProPhasesUser.setCreateDate(syncDate);
                    users.add(itProPhasesUser);
                }
                dataSyncDao.batchInsert(ItProPhasesUser.class, users);

                dataSyncDao.writeBackSyncData("syncItemWbsInfo");
            }
        } catch (Exception ex) {
            LoggerUtil.error(DataSyncServiceImpl.class, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
