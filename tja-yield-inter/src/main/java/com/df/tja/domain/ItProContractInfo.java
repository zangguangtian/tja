package com.df.tja.domain;

/**
* 项目名称:北京易兰
*
* ItProContractINFO.java
*
* 2017年9月18日 9:29:02
*
* 2016 上海一勤信息技术有限公司-版权所有 
*/

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.df.framework.base.domain.BaseDomain;

/**
 * <p>ItProContractInfo </p>
 * 
 * <p>描述：项目合同信息接口表 </p>
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
@Table(name = "IT_PRO_CONTRACT_INFO")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ItProContractInfo extends BaseDomain {
    /**
      * 属性： serialVersionUID 
      */
    private static final long serialVersionUID = 5323011672758446380L;

    /** 属性：合同类型。1000：项目合同；2000：项目分包合同 */
    private java.lang.String contractType;

    /** 属性：合同编号 */
    private java.lang.String contractCode;

    /** 属性：合同名称 */
    private java.lang.String contractName;

    /** 属性：所属项目合同额 */
    private BigDecimal contractMoney;

    /** 属性：关联项目ID */
    private java.lang.String itemId;

    /**
     * <p> 属性：contractType的Getter方法. </p>
     * 
     * @return 返回合同类型属性的值
     */
    @Column(name = "CONTRACT_TYPE")
    public java.lang.String getContractType() {
        return contractType;
    }

    /**
     * <p> 属性合同类型的Setter方法. </p>
     * 
     * @param contractType 为属性contractType设置的值
     */
    public void setContractType(java.lang.String contractType) {
        this.contractType = contractType;
    }

    /**
     * <p> 属性：contractCode的Getter方法. </p>
     * 
     * @return 返回合同编号属性的值
     */
    @Column(name = "CONTRACT_CODE")
    public java.lang.String getContractCode() {
        return contractCode;
    }

    /**
     * <p> 属性合同编号的Setter方法. </p>
     * 
     * @param contractCode 为属性contractCode设置的值
     */
    public void setContractCode(java.lang.String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * <p> 属性：contractName的Getter方法. </p>
     * 
     * @return 返回合同名称属性的值
     */
    @Column(name = "CONTRACT_NAME")
    public java.lang.String getContractName() {
        return contractName;
    }

    /**
     * <p> 属性合同名称的Setter方法. </p>
     * 
     * @param contractName 为属性contractName设置的值
     */
    public void setContractName(java.lang.String contractName) {
        this.contractName = contractName;
    }

    /**
     * <p> 属性：contractMoney的Getter方法. </p>
     * 
     * @return 返回所属项目合同额属性的值
     */
    @Column(name = "CONTRACT_MONEY")
    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    /**
     * <p> 属性所属项目合同额的Setter方法. </p>
     * 
     * @param contractMoney 为属性contractMoney设置的值
     */
    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    /**
     * <p> 属性：itemId的Getter方法. </p>
     * 
     * @return 返回关联项目ID属性的值
     */
    @Column(name = "ITEM_ID")
    public java.lang.String getItemId() {
        return itemId;
    }

    /**
     * <p> 属性关联项目ID的Setter方法. </p>
     * 
     * @param itemId 为属性itemId设置的值
     */
    public void setItemId(java.lang.String itemId) {
        this.itemId = itemId;
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
        ItProContractInfo obj1 = (ItProContractInfo) o;
        return new EqualsBuilder().append(getId(), obj1.getId()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
    }
}