package com.df.tja.domain.cust;

import com.df.framework.base.domain.SuperDomain;
import com.df.tja.domain.OcSchemeDivisor;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>OcSchemeMode </p>
 * <p>
 * <p>描述： </p>
 * <p>
 * <p>备注：</p>
 * <p>
 * <p>2018-11-13 14:15</p>
 *
 * @author deng.jiayan
 * @version 1.0.0
 */
public class OcSchemeMode extends SuperDomain {

    /**
     *  serialVersionUID
     */
    private static final long serialVersionUID = -732254118468207843L;

    /** 属性：项目ID */
    private String pid;

    /** 属性：項目扩展ID */
    private String peid;

    /** 属性：属性：是否方案产值。1：是；0：否 */
    private Boolean schemeFlag;

    /** 属性：产值额 */
    private BigDecimal schemeAmount;

    /** 属性：项目策划ID */
    private String sid;

    /** 属性：项目WBS */
    private String proWbs;

    /** 属性：简化模式 */
    private List<OcSchemeDivisor> ocSchemeDivisors;

    /** 属性：完整模式 */
    private List<OcSchemeStageMajor> ocSchemeStageMajors;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPeid() {
        return peid;
    }

    public void setPeid(String peid) {
        this.peid = peid;
    }

    public Boolean getSchemeFlag() {
        return schemeFlag;
    }

    public void setSchemeFlag(Boolean schemeFlag) {
        this.schemeFlag = schemeFlag;
    }

    public BigDecimal getSchemeAmount() {
        return schemeAmount;
    }

    public void setSchemeAmount(BigDecimal schemeAmount) {
        this.schemeAmount = schemeAmount;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getProWbs() {
        return proWbs;
    }

    public void setProWbs(String proWbs) {
        this.proWbs = proWbs;
    }

    public List<OcSchemeDivisor> getOcSchemeDivisors() {
        return ocSchemeDivisors;
    }

    public void setOcSchemeDivisors(List<OcSchemeDivisor> ocSchemeDivisors) {
        this.ocSchemeDivisors = ocSchemeDivisors;
    }

    public List<OcSchemeStageMajor> getOcSchemeStageMajors() {
        return ocSchemeStageMajors;
    }

    public void setOcSchemeStageMajors(List<OcSchemeStageMajor> ocSchemeStageMajors) {
        this.ocSchemeStageMajors = ocSchemeStageMajors;
    }
}
