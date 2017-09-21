/**
 * 项目名称:tja-yield-inter
 *
 * com.df.tja.domain.cust
 *
 * WfPlanSchemeModel.java
 * 
 * 2017年9月20日-下午4:06:51
 *
 * 2017 上海一勤-版权所有 
 */

package com.df.tja.domain.cust;

import java.io.Serializable;
import java.util.List;

import com.df.tja.domain.WfPlanScheme;
import com.df.tja.domain.WfShemeTeam;

/**
 * <p>WfPlanSchemeModel</p>
 * 
 * <p>描述：</p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月20日 下午4:06:51</p>
 *
 * @author wang.changjiu
 * 
 * @version 1.0.0
 * 
 */

public class WfPlanSchemeModel implements Serializable {

    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 7929312988231785784L;

    private WfPlanScheme planScheme;

    private List<WfShemeTeam> shemeTeams;

    public WfPlanScheme getPlanScheme() {
        return planScheme;
    }

    public void setPlanScheme(WfPlanScheme planScheme) {
        this.planScheme = planScheme;
    }

    public List<WfShemeTeam> getShemeTeams() {
        return shemeTeams;
    }

    public void setShemeTeams(List<WfShemeTeam> shemeTeams) {
        this.shemeTeams = shemeTeams;
    }
}
