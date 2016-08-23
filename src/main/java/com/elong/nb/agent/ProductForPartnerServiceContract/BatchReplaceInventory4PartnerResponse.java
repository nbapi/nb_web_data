
package com.elong.nb.agent.ProductForPartnerServiceContract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BatchReplaceInventory4PartnerResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BatchReplaceInventory4PartnerResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/com.eLong.Hotel.Framework}ServiceResponseBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AffectedDateIntervals" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfdateTimedateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchReplaceInventory4PartnerResponse", namespace = "http://schemas.datacontract.org/2004/07/com.eLong.Hotel.Product.Entities.Response", propOrder = {
    "affectedDateIntervals"
})
public class BatchReplaceInventory4PartnerResponse
    extends ServiceResponseBase
{

    @XmlElement(name = "AffectedDateIntervals", nillable = true)
    protected ArrayOfKeyValueOfdateTimedateTime affectedDateIntervals;

    /**
     * 获取affectedDateIntervals属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyValueOfdateTimedateTime }
     *     
     */
    public ArrayOfKeyValueOfdateTimedateTime getAffectedDateIntervals() {
        return affectedDateIntervals;
    }

    /**
     * 设置affectedDateIntervals属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyValueOfdateTimedateTime }
     *     
     */
    public void setAffectedDateIntervals(ArrayOfKeyValueOfdateTimedateTime value) {
        this.affectedDateIntervals = value;
    }

}
