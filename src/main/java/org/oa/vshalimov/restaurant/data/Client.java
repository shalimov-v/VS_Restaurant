package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "client")
@XmlRootElement
public class Client {

    @Id
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "clientFirstName")
    private String clientFirstName;

    @Column(name = "clientLastName")
    private String clientLastName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "clientDiscountId")
    private Discount clientDiscount;

    public Client() {
    }

    @XmlElement
    public int getClientId() {
        return clientId;
    }

    @XmlElement
    public String getClientFirstName() {
        return clientFirstName;
    }

    @XmlElement
    public String getClientLastName() {
        return clientLastName;
    }

    @XmlElement
    public Discount getClientDiscount() {
        return clientDiscount;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setClientDiscount(Discount clientDiscount) {
        this.clientDiscount = clientDiscount;
    }

}