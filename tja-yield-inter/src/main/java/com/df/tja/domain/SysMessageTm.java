package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* SysMessageTM.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>SysMessageTm </p>
 * 
 * <p>描述：系统消息表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年9月18日 9:29:02</p>
 *
 * @author tc
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "SYS_MESSAGE_TM")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysMessageTm extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = -137782931588614720L;

    /** 属性：主键ID */
    private java.lang.String id;

    /** 属性：操作人ID */
    private java.lang.String operatorId;

    /** 属性：被通知人ID */
    private java.lang.String userId;

    /** 属性：通知内容 */
    private String content;

    /** 属性：通知时间 */
    private java.util.Date createTime;

    /** 属性：是否已经阅读。1：已经阅读；0：未阅读； */
    private Integer readStatus = 0;

    /** 属性：分类。1000：流程待阅； */
    private java.lang.String messType;

    /** 属性：读取时间 */
    private java.util.Date readTime;

    /** 属性：应用业务对象ID */
    private java.lang.String oppId;

    /**
     * <p> 属性：id的Getter方法. </p>
     * 
     * @return 返回主键ID属性的值
     */
    @Column(name = "ID")
    public java.lang.String getId() {
        return id;
    }

    /**
     * <p> 属性主键ID的Setter方法. </p>
     * 
     * @param id 为属性id设置的值
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /**
     * <p> 属性：operatorId的Getter方法. </p>
     * 
     * @return 返回操作人ID属性的值
     */
    @Column(name = "OPERATOR_ID")
    public java.lang.String getOperatorId() {
        return operatorId;
    }

    /**
     * <p> 属性操作人ID的Setter方法. </p>
     * 
     * @param operatorId 为属性operatorId设置的值
     */
    public void setOperatorId(java.lang.String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * <p> 属性：userId的Getter方法. </p>
     * 
     * @return 返回被通知人ID属性的值
     */
    @Column(name = "USER_ID")
    public java.lang.String getUserId() {
        return userId;
    }

    /**
     * <p> 属性被通知人ID的Setter方法. </p>
     * 
     * @param userId 为属性userId设置的值
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    /**
     * <p> 属性：content的Getter方法. </p>
     * 
     * @return 返回通知内容属性的值
     */
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    /**
     * <p> 属性通知内容的Setter方法. </p>
     * 
     * @param content 为属性content设置的值
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * <p> 属性：createTime的Getter方法. </p>
     * 
     * @return 返回通知时间属性的值
     */
    @Column(name = "CREATE_TIME")
    public java.util.Date getCreateTime() {
        return createTime;
    }

    /**
     * <p> 属性通知时间的Setter方法. </p>
     * 
     * @param createTime 为属性createTime设置的值
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * <p> 属性：readStatus的Getter方法. </p>
     * 
     * @return 返回阅读状态属性的值
     */
    @Column(name = "READ_STATUS")
    public Integer getReadStatus() {
        return readStatus;
    }

    /**
     * <p> 属性阅读状态的Setter方法. </p>
     * 
     * @param readStatus 为属性readStatus设置的值
     */
    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    /**
     * <p> 属性：messType的Getter方法. </p>
     * 
     * @return 返回分类属性的值
     */
    @Column(name = "MESS_TYPE")
    public java.lang.String getMessType() {
        return messType;
    }

    /**
     * <p> 属性分类的Setter方法. </p>
     * 
     * @param messType 为属性messType设置的值
     */
    public void setMessType(java.lang.String messType) {
        this.messType = messType;
    }

    /**
     * <p> 属性：readTime的Getter方法. </p>
     * 
     * @return 返回读取时间属性的值
     */
    @Column(name = "READ_TIME")
    public java.util.Date getReadTime() {
        return readTime;
    }

    /**
     * <p> 属性读取时间的Setter方法. </p>
     * 
     * @param readTime 为属性readTime设置的值
     */
    public void setReadTime(java.util.Date readTime) {
        this.readTime = readTime;
    }

    /**
     * <p> 属性：oppId的Getter方法. </p>
     * 
     * @return 返回应用ID属性的值
     */
    @Column(name = "OPP_ID")
    public java.lang.String getOppId() {
        return oppId;
    }

    /**
     * <p> 属性应用ID的Setter方法. </p>
     * 
     * @param oppId 为属性oppId设置的值
     */
    public void setOppId(java.lang.String oppId) {
        this.oppId = oppId;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        SysMessageTm obj1 = (SysMessageTm) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}