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
 *         &lt;element name="WbsPrjPhaseObjectResult" type="{http://tempuri.org/}WbsPrjPhase" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"wbsPrjPhaseObjectResult"})
@XmlRootElement(name = "WbsPrjPhaseObjectResponse")
public class WbsPrjPhaseObjectResponse {

    @XmlElement(name = "WbsPrjPhaseObjectResult")
    protected WbsPrjPhase wbsPrjPhaseObjectResult;

    /**
     * 获取wbsPrjPhaseObjectResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WbsPrjPhase }
     *     
     */
    public WbsPrjPhase getWbsPrjPhaseObjectResult() {
        return wbsPrjPhaseObjectResult;
    }

    /**
     * 设置wbsPrjPhaseObjectResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WbsPrjPhase }
     *     
     */
    public void setWbsPrjPhaseObjectResult(WbsPrjPhase value) {
        this.wbsPrjPhaseObjectResult = value;
    }

}
