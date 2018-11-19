package com.df.tja.service.impl;

import com.df.framework.base.service.impl.BaseServiceImpl;
import com.df.framework.exception.LogicalException;
import com.df.project.domain.ProjectExtend;
import com.df.project.service.IProjectService;
import com.df.tja.domain.OcScheme;
import com.df.tja.domain.cust.OcSchemeMode;
import com.df.tja.service.IOcSchemeDivisorService;
import com.df.tja.service.IOcSchemeService;
import com.df.tja.service.IProjectPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>ProjectPlanningServiceImpl </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-19 10:31</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
@Service
public class ProjectPlanningServiceImpl extends BaseServiceImpl implements IProjectPlanningService {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IOcSchemeService ocSchemeService;

    @Autowired
    private IOcSchemeDivisorService ocSchemeDivisorService;

    @Override
    public void submitProjectPlanning(OcSchemeMode ocSchemeMode) throws LogicalException {
        try{
            /** 项目扩展 */
            ProjectExtend projectExtend = new ProjectExtend();
            projectExtend.setSchemeFlag(ocSchemeMode.getSchemeFlag());
            projectExtend.setId(ocSchemeMode.getPeid());
            projectExtend.setSchemeAmount(ocSchemeMode.getSchemeAmount());
            projectService.modifyProjectExtend(projectExtend);

            /** 项目WBS */
            OcScheme ocScheme = new OcScheme();
            ocScheme.setId(ocSchemeMode.getSid());
            ocScheme.setProWbs(ocSchemeMode.getProWbs());
            ocSchemeService.modifyOcScheme(ocScheme);

            /** 完整模式 */
            if(ocSchemeMode.getOcSchemeStageMajors() != null && ocSchemeMode.getOcSchemeStageMajors().size() > 0){
                ocSchemeDivisorService.modifyRatio(ocSchemeMode.getOcSchemeStageMajors());
            }

            /** 简化模式 */
            if(ocSchemeMode.getOcSchemeDivisors() != null && ocSchemeMode.getOcSchemeDivisors().size() > 0){
                ocSchemeDivisorService.modifySimple(ocSchemeMode.getOcSchemeDivisors());
            }
        }catch (LogicalException ex) {
            throw ex;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
