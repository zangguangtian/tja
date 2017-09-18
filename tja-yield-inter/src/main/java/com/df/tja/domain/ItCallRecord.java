package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* ItCallRECORD.java
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
 * <p>ItCallRecord </p>
 * 
 * <p>描述：接口调用记录表 </p>
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
@Table(name = "IT_CALL_RECORD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ItCallRecord extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 1L;

    /** 属性：接口方法 */
    private java.lang.String inMethod;

    /** 属性：接口请求参数 */
    private java.lang.String reqArgs;

    /** 属性：接口请求时间 */
    private java.util.Date reqDate;

    /** 属性：接口响应报文 */
    private String resText;

    /** 属性：接口响应时间 */
    private java.util.Date resDate;

    /**
     * <p> 属性：inMethod的Getter方法. </p>
     * 
     * @return 返回接口方法属性的值
     */
    @Column(name = "IN_METHOD")
    public java.lang.String getInMethod() {
        return inMethod;
    }

    /**
     * <p> 属性接口方法的Setter方法. </p>
     * 
     * @param inMethod 为属性inMethod设置的值
     */
    public void setInMethod(java.lang.String inMethod) {
        this.inMethod = inMethod;
    }

    /**
     * <p> 属性：reqArgs的Getter方法. </p>
     * 
     * @return 返回接口请求参数属性的值
     */
    @Column(name = "REQ_ARGS")
    public java.lang.String getReqArgs() {
        return reqArgs;
    }

    /**
     * <p> 属性接口请求参数的Setter方法. </p>
     * 
     * @param reqArgs 为属性reqArgs设置的值
     */
    public void setReqArgs(java.lang.String reqArgs) {
        this.reqArgs = reqArgs;
    }

    /**
     * <p> 属性：reqDate的Getter方法. </p>
     * 
     * @return 返回接口请求时间属性的值
     */
    @Column(name = "REQ_DATE")
    public java.util.Date getReqDate() {
        return reqDate;
    }

    /**
     * <p> 属性接口请求时间的Setter方法. </p>
     * 
     * @param reqDate 为属性reqDate设置的值
     */
    public void setReqDate(java.util.Date reqDate) {
        this.reqDate = reqDate;
    }

    /**
     * <p> 属性：resText的Getter方法. </p>
     * 
     * @return 返回接口响应报文属性的值
     */
    @Column(name = "RES_TEXT")
    public String getResText() {
        return resText;
    }

    /**
     * <p> 属性接口响应报文的Setter方法. </p>
     * 
     * @param resText 为属性resText设置的值
     */
    public void setResText(String resText) {
        this.resText = resText;
    }

    /**
     * <p> 属性：resDate的Getter方法. </p>
     * 
     * @return 返回接口响应时间属性的值
     */
    @Column(name = "RES_DATE")
    public java.util.Date getResDate() {
        return resDate;
    }

    /**
     * <p> 属性接口响应时间的Setter方法. </p>
     * 
     * @param resDate 为属性resDate设置的值
     */
    public void setResDate(java.util.Date resDate) {
        this.resDate = resDate;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回创建时间属性的值
     */
    @Column(name = "CREATE_DATE")
    public java.util.Date getCreateDate() {
        return createDate;
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
        ItCallRecord obj1 = (ItCallRecord) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}