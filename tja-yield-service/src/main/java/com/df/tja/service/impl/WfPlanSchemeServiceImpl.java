/**
 * 项目名称:tja-yield-service
 *
 * com.df.tja.service.impl
 *
 * WfPlanSchemeServiceImpl.java
 * 
 * 2017年9月22日-下午2:03:35
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.df.activiti.domain.ProcessArgs;
import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.project.domain.cust.CustProject;
import com.df.project.service.IProjectService;
import com.df.tja.constant.TjaConstant;
import com.df.tja.dao.IWfPlanSchemeDao;
import com.df.tja.domain.WfPlanScheme;
import com.df.tja.domain.WfShemeTeam;
import com.df.tja.service.IWfPlanSchemeService;

/**
 * <p>WfPlanSchemeServiceImpl</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月22日 下午2:03:35</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */
@Service
public class WfPlanSchemeServiceImpl extends BaseServiceImpl implements IWfPlanSchemeService {

    @Autowired
    private IWfPlanSchemeDao wfPlanSchemeDao;

    @Autowired
    private IProjectService projectService;

    /** 
     * @see com.df.tja.service.IWfPlanSchemeService#addOrModifyPlanScheme
     * (com.df.tja.domain.WfPlanScheme, com.df.activiti.domain.ProcessArgs, com.df.tja.domain.cust.WfPlanSchemeModel)
     */
    @Override
    public void addOrModifyPlanScheme(WfPlanScheme planScheme, ProcessArgs processArgs) throws RuntimeException {
        try {
            //修改
            if (StringUtils.isNotBlank(planScheme.getId())) {
                modify(WfPlanScheme.class, planScheme);
            } else {
                //添加
                addEntity(WfPlanScheme.class, planScheme);
            }

            List<WfShemeTeam> shemeTeams = planScheme.getShemeTeams();
            if (shemeTeams != null && shemeTeams.size() > 0) {
                for (WfShemeTeam wfShemeTeam : shemeTeams) {
                    wfShemeTeam.setWfId(planScheme.getId());
                    if (StringUtils.isNotBlank(wfShemeTeam.getId())) {
                        //修改
                        modify(WfShemeTeam.class, wfShemeTeam);
                    } else {
                        //添加  
                        addEntity(WfShemeTeam.class, wfShemeTeam);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /** 
     * @see com.df.tja.service.IWfPlanSchemeService#queryPlanSchemeById(java.lang.String)
     */
    @Override
    public void queryPlanSchemeById(Map<String, Object> attributes, String id) throws RuntimeException {
        try {
            WfPlanScheme scheme = wfPlanSchemeDao.selectWfPlanSchemeById(id);
            attributes.put("planScheme", scheme);
            List<WfShemeTeam> shemeTeams = wfPlanSchemeDao.selectWfShemeTeamsByWfId(id);
            attributes.put("shemeTeams", shemeTeams);
            if (StringUtils.isNoneBlank(scheme.getProId())) {
                CustProject custProject = projectService.queryProInfoById(scheme.getProId());
                attributes.put("project", custProject);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeBackPlanScheme(String id) throws RuntimeException {
        try {
            wfPlanSchemeDao.writeBack(id, TjaConstant.WriteBackOpType.OCWRITEBACKTYPE[0],
                TjaConstant.WriteBackFunc.WF_FUNC);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
