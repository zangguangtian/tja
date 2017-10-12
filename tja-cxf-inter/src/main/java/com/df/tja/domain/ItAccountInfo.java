/**
 * 项目名称:tja-cxf-inter
 *
 * com.df.tja.domain
 *
 * ItAccountInfo.java
 * 
 * 2017年10月10日-下午2:18:13
 *
 * 2017 上海一勤信息技术有限公司-版权所有
 */

package com.df.tja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.df.framework.base.domain.SuperDomain;

/**
 * <p>ItAccountInfo </p>
 * 
 * <p>描述：账号信息接口表 </p>
 *
 * <p>备注：</p>
 * 
 * <p>2017年10月10日 12:06:54</p>
 *
 * @author TabZhu
 * 
 * @version 1.0.0
 * 
 */

@Entity
@Table(name = "IT_ACCOUNT_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ItAccountInfo extends SuperDomain {
    /**
     * 属性： serialVersionUID 
     */
    private static final long serialVersionUID = 7011748517802758743L;

    /** 属性：账号 */
    private java.lang.String accountName;

    /**
     * <p> 属性：id的Getter方法. </p>
     * 
     * @return 返回主键ID属性的值
     */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "ID", unique = true, nullable = false)
    public java.lang.String getId() {
        return id;
    }

    /**
     * <p> 属性：accountName的Getter方法. </p>
     * 
     * @return 返回账号属性的值
     */
    @Column(name = "ACCOUNT_NAME")
    public java.lang.String getAccountName() {
        return accountName;
    }

    /**
     * <p> 属性账号的Setter方法. </p>
     * 
     * @param accountName 为属性accountName设置的值
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }

    /**
     * <p> 属性：createDate的Getter方法. </p>
     * 
     * @return 返回同步时间属性的值
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
        ItAccountInfo obj1 = (ItAccountInfo) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}
