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
 *         &lt;element name="UserObjectResult" type="{http://tempuri.org/}User" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"userObjectResult"})
@XmlRootElement(name = "UserObjectResponse")
public class UserObjectResponse {

    @XmlElement(name = "UserObjectResult")
    protected User userObjectResult;

    /**
     * 获取userObjectResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserObjectResult() {
        return userObjectResult;
    }

    /**
     * 设置userObjectResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserObjectResult(User value) {
        this.userObjectResult = value;
    }

}
