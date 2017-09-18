/**
 * 项目名称:tja-model
 *
 * com.df.framework.base.domain
 *
 * BaseDomain.java
 * 
 * 2015年10月13日-下午10:24:31
 *
 * 2015 上海一勤信息技术有限公司-版权所有 
 */
package com.df.framework.base.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>BaseDomain</p>
 * 
 * <p>描述：所有Domain类的基类</p>
 *
 * <p>备注：</p>
 * 
 * <p>2015年10月13日 下午10:24:31</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */
@MappedSuperclass
public abstract class BaseDomain implements Serializable {
    private static final long serialVersionUID = 8342158001266215214L;

    /** id,作为一个对象的主键*/
    protected String id;

    /** 创建用户 */
    protected String creator;

    /** 创建时间 */
    protected Date createDate;

    /** 修改用户 */
    protected String modifier;

    /** 创建时间 */
    protected Date modifyDate;

    /** orderBy属性，供查询用 */
    private String orderBy;

    /** 附加条件属性，供查询用 */
    private String otherCondition;

    /** 附加条件属性值，供查询用*/
    private List<Object> otherConditions = new ArrayList<Object>(0);

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Transient
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Transient
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Transient
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Transient
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Transient
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Transient
    public String getOtherCondition() {
        return otherCondition;
    }

    public void setOtherCondition(String otherCondition) {
        this.otherCondition = otherCondition;
    }

    @Transient
    public List<Object> getOtherConditions() {
        return otherConditions;
    }

    public void setOtherConditions(List<Object> otherConditions) {
        this.otherConditions = otherConditions;
    }

    public void addOtherConditions(Object oc) {
        if (this.otherConditions == null) {
            this.otherConditions = new ArrayList<Object>(0);
        }
        this.otherConditions.add(oc);
    }
}
