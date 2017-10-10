package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>ItemWbs complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ItemWbs"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrjPhases" type="{http://tempuri.org/}ArrayOfWbsPrjPhase" minOccurs="0"/&gt;
 *         &lt;element name="SubEntrys" type="{http://tempuri.org/}ArrayOfWbsSubEntry" minOccurs="0"/&gt;
 *         &lt;element name="Majors" type="{http://tempuri.org/}ArrayOfWbsMajor" minOccurs="0"/&gt;
 *         &lt;element name="Users" type="{http://tempuri.org/}ArrayOfWbsUser" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemWbs", propOrder = {"prjPhases", "subEntrys", "majors", "users"})
public class ItemWbs {

    @XmlElement(name = "PrjPhases")
    protected ArrayOfWbsPrjPhase prjPhases;
    @XmlElement(name = "SubEntrys")
    protected ArrayOfWbsSubEntry subEntrys;
    @XmlElement(name = "Majors")
    protected ArrayOfWbsMajor majors;
    @XmlElement(name = "Users")
    protected ArrayOfWbsUser users;

    /**
     * 获取prjPhases属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWbsPrjPhase }
     *     
     */
    public ArrayOfWbsPrjPhase getPrjPhases() {
        return prjPhases;
    }

    /**
     * 设置prjPhases属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWbsPrjPhase }
     *     
     */
    public void setPrjPhases(ArrayOfWbsPrjPhase value) {
        this.prjPhases = value;
    }

    /**
     * 获取subEntrys属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWbsSubEntry }
     *     
     */
    public ArrayOfWbsSubEntry getSubEntrys() {
        return subEntrys;
    }

    /**
     * 设置subEntrys属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWbsSubEntry }
     *     
     */
    public void setSubEntrys(ArrayOfWbsSubEntry value) {
        this.subEntrys = value;
    }

    /**
     * 获取majors属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWbsMajor }
     *     
     */
    public ArrayOfWbsMajor getMajors() {
        return majors;
    }

    /**
     * 设置majors属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWbsMajor }
     *     
     */
    public void setMajors(ArrayOfWbsMajor value) {
        this.majors = value;
    }

    /**
     * 获取users属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfWbsUser }
     *     
     */
    public ArrayOfWbsUser getUsers() {
        return users;
    }

    /**
     * 设置users属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfWbsUser }
     *     
     */
    public void setUsers(ArrayOfWbsUser value) {
        this.users = value;
    }

}
