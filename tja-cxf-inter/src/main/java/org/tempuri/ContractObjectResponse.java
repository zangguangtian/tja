package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContractObjectResult" type="{http://tempuri.org/}Contract" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"contractObjectResult"})
@XmlRootElement(name = "ContractObjectResponse")
public class ContractObjectResponse {

    @XmlElement(name = "ContractObjectResult")
    protected Contract contractObjectResult;

    /**
     * 获取contractObjectResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContractObjectResult() {
        return contractObjectResult;
    }

    /**
     * 设置contractObjectResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContractObjectResult(Contract value) {
        this.contractObjectResult = value;
    }

}
