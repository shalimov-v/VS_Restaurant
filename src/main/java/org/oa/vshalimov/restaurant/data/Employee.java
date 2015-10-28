package org.oa.vshalimov.restaurant.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "employee")
@XmlRootElement
public class Employee {

    @Id
    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "employeeFirstName")
    private String employeeFirstName;

    @Column(name = "employeeLastName")
    private String employeeLastName;

    public Employee() {
    }

    @XmlElement
    public int getEmployeeId() {
        return employeeId;
    }

    @XmlElement
    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    @XmlElement
    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

}
