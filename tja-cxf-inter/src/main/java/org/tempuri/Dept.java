package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Dept complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Dept"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SortIndex" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="DeptLeader" type="{http://tempuri.org/}ArrayOfAccount" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dept", propOrder = {"id", "parentId", "idPath", "name", "sortIndex", "deptLeader"})
public class Dept {

    @XmlElement(name = "Id")
    protected String id;
    @XmlElement(name = "ParentId")
    protected String parentId;
    @XmlElement(name = "IdPath")
    protected String idPath;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "SortIndex")
    protected int sortIndex;
    @XmlElement(name = "DeptLeader")
    protected ArrayOfAccount deptLeader;

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取parentId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置parentId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentId(String value) {
        this.parentId = value;
    }

    /**
     * 获取idPath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPath() {
        return idPath;
    }

    /**
     * 设置idPath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPath(String value) {
        this.idPath = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取sortIndex属性的值。
     * 
     */
    public int getSortIndex() {
        return sortIndex;
    }

    /**
     * 设置sortIndex属性的值。
     * 
     */
    public void setSortIndex(int value) {
        this.sortIndex = value;
    }

    /**
     * 获取deptLeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAccount }
     *     
     */
    public ArrayOfAccount getDeptLeader() {
        return deptLeader;
    }

    /**
     * 设置deptLeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAccount }
     *     
     */
    public void setDeptLeader(ArrayOfAccount value) {
        this.deptLeader = value;
    }

}
