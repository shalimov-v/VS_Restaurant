package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dishtype")
@XmlRootElement
public class DishType {

    @Id
    @Column(name = "dishTypeId")
    private int dishTypeId;

    @Column(name = "dishTypeName")
    private String dishTypeName;

    public DishType() {
    }

    @XmlElement
    public int getDishTypeId() {
        return dishTypeId;
    }

    @XmlElement
    public String getDishTypeName() {
        return dishTypeName;
    }

    public void setDishTypeId(int dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public void setDishTypeName(String dishTypeName) {
        this.dishTypeName = dishTypeName;
    }

}
