package org.oa.vshalimov.restaurant;

import org.oa.vshalimov.restaurant.dao.FacadeRepository;
import org.oa.vshalimov.restaurant.data.Employee;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FacadeRepository facade = FacadeRepository.getInstance();
        List<Employee> employees = facade.getEmployeeRepository().loadAll();
        for (Employee employee: employees) {
            System.out.println(employee.toString());
        }
    }

}