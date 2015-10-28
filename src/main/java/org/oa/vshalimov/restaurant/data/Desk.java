package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "desk")
@XmlRootElement
public class Desk {

    @Id
    @Column(name = "deskId")
    private int deskId;

    @Column(name = "deskName")
    private String deskName;

    @Column(name = "deskMaxPersons")
    private int deskMaxPersons;

    public Desk() {
    }

    @XmlElement
    public int getDeskId() {
        return deskId;
    }

    @XmlElement
    public String getDeskName() {
        return deskName;
    }

    @XmlElement
    public int getDeskMaxPersons() {
        return deskMaxPersons;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public void setDeskMaxPersons(int deskMaxPersons) {
        this.deskMaxPersons = deskMaxPersons;
    }
}