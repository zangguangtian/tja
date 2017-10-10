package org.tempuri;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>ArrayOfWbsPrjPhase complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfWbsPrjPhase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *       &lt;element name="WbsPrjPhase" type="{http://tempuri.org/}WbsPrjPhase" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfWbsPrjPhase", propOrder = {"wbsPrjPhase"})
public class ArrayOfWbsPrjPhase {

    @XmlElement(name = "WbsPrjPhase", nillable = true)
    protected List<WbsPrjPhase> wbsPrjPhase;

    /**
     * Gets the value of the wbsPrjPhase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wbsPrjPhase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWbsPrjPhase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WbsPrjPhase }
     * 
     * 
     */
    public List<WbsPrjPhase> getWbsPrjPhase() {
        if (wbsPrjPhase == null) {
            wbsPrjPhase = new ArrayList<WbsPrjPhase>();
        }
        return this.wbsPrjPhase;
    }

}
