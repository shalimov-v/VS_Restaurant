package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dish")
@XmlRootElement
public class Dish {

    @Id
    @Column(name = "dishId")
    private int dishId;

    @Column(name = "dishName")
    private String dishName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "dishTypeId")
    private DishType dishType;

    @Column(name = "dishDescription")
    private String dishDescription;

    public Dish() {
    }

    @XmlElement
    public int getDishId() {
        return dishId;
    }

    @XmlElement
    public String getDishName() {
        return dishName;
    }

    @XmlElement
    public DishType getDishType() {
        return dishType;
    }

    @XmlElement
    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

}
