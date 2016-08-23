
package com.elong.nb.agent.ProductForPartnerServiceContract;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfBedInfoEntity complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBedInfoEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BedInfoEntity" type="{http://schemas.datacontract.org/2004/07/com.eLong.Hotel.Product.Entities.Model}BedInfoEntity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBedInfoEntity", namespace = "http://schemas.datacontract.org/2004/07/com.eLong.Hotel.Product.Entities.Model", propOrder = {
    "bedInfoEntity"
})
public class ArrayOfBedInfoEntity {

    @XmlElement(name = "BedInfoEntity", nillable = true)
    protected List<BedInfoEntity> bedInfoEntity;

    /**
     * Gets the value of the bedInfoEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bedInfoEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBedInfoEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BedInfoEntity }
     * 
     * 
     */
    public List<BedInfoEntity> getBedInfoEntity() {
        if (bedInfoEntity == null) {
            bedInfoEntity = new ArrayList<BedInfoEntity>();
        }
        return this.bedInfoEntity;
    }

}
