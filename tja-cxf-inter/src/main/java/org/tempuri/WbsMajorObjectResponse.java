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
 *         &lt;element name="WbsMajorObjectResult" type="{http://tempuri.org/}WbsMajor" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"wbsMajorObjectResult"})
@XmlRootElement(name = "WbsMajorObjectResponse")
public class WbsMajorObjectResponse {

    @XmlElement(name = "WbsMajorObjectResult")
    protected WbsMajor wbsMajorObjectResult;

    /**
     * 获取wbsMajorObjectResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WbsMajor }
     *     
     */
    public WbsMajor getWbsMajorObjectResult() {
        return wbsMajorObjectResult;
    }

    /**
     * 设置wbsMajorObjectResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WbsMajor }
     *     
     */
    public void setWbsMajorObjectResult(WbsMajor value) {
        this.wbsMajorObjectResult = value;
    }

}
