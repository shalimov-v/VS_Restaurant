package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Entity
@Table (name = "menu")
@XmlRootElement
public class Menu {

    @Id
    @Column(name = "menuId")
    private int menuId;

    @ManyToOne
    @JoinColumn(name = "menuDishId")
    private Dish menuDish;

    @Column(name = "menuPrice")
    private BigDecimal menuPrice;

    public Menu() {
    }

    @XmlElement
    public int getMenuId() {
        return menuId;
    }

    @XmlElement
    public Dish getMenuDish() {
        return menuDish;
    }

    @XmlElement
    public BigDecimal getMenuPrice() {
        return menuPrice;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setMenuDish(Dish dish) {
        this.menuDish = dish;
    }

    public void setMenuPrice(BigDecimal menuPrice) {
        this.menuPrice = menuPrice;
    }

}
