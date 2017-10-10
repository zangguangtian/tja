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
 *         &lt;element name="ItemWbsObjectResult" type="{http://tempuri.org/}ItemWbs" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"itemWbsObjectResult"})
@XmlRootElement(name = "ItemWbsObjectResponse")
public class ItemWbsObjectResponse {

    @XmlElement(name = "ItemWbsObjectResult")
    protected ItemWbs itemWbsObjectResult;

    /**
     * 获取itemWbsObjectResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ItemWbs }
     *     
     */
    public ItemWbs getItemWbsObjectResult() {
        return itemWbsObjectResult;
    }

    /**
     * 设置itemWbsObjectResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ItemWbs }
     *     
     */
    public void setItemWbsObjectResult(ItemWbs value) {
        this.itemWbsObjectResult = value;
    }

}
