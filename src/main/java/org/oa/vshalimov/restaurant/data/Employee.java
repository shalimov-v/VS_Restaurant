package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "employee")
@XmlRootElement(name = "Employee")
public class Employee {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    public Employee() {
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
