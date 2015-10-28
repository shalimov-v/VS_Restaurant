package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "discount")
@XmlRootElement
public class Discount {

    @Id
    @Column(name = "discountId")
    private int discountId;

    @Column(name = "discountName")
    private String discountName;

    @Column(name = "discountValue")
    private int discountValue;

    public Discount() {
    }

    @XmlElement
    public int getDiscountId() {
        return discountId;
    }

    @XmlElement
    public String getDiscountName() {
        return discountName;
    }

    @XmlElement
    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

}