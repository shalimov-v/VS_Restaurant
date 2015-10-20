package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.oa.vshalimov.restaurant.data.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employees")
public class EmployeeService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<Employee> loadAll() {
        return facade.getEmployeeRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Employee findById(@PathVariable("id") String id) {
        return facade.getEmployeeRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.POST)
    public @ResponseBody Employee create(@RequestBody Employee employee) {
        if (facade.getEmployeeRepository().create(employee)) {
            return employee;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.PUT)
    public @ResponseBody Employee update(@RequestBody Employee employee) {
        if (facade.getEmployeeRepository().update(employee)) {
            return employee;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.DELETE)
    public @ResponseBody Employee delete(@RequestBody Employee employee) {
        if (facade.getEmployeeRepository().delete(employee)) {
            return employee;
        } else {
            return null;
        }
    }

}