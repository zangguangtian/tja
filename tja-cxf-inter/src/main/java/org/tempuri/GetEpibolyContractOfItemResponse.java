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
 *         &lt;element name="GetEpibolyContractOfItemResult" type="{http://tempuri.org/}Result" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"getEpibolyContractOfItemResult"})
@XmlRootElement(name = "GetEpibolyContractOfItemResponse")
public class GetEpibolyContractOfItemResponse {

    @XmlElement(name = "GetEpibolyContractOfItemResult")
    protected Result getEpibolyContractOfItemResult;

    /**
     * 获取getEpibolyContractOfItemResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetEpibolyContractOfItemResult() {
        return getEpibolyContractOfItemResult;
    }

    /**
     * 设置getEpibolyContractOfItemResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetEpibolyContractOfItemResult(Result value) {
        this.getEpibolyContractOfItemResult = value;
    }

}
