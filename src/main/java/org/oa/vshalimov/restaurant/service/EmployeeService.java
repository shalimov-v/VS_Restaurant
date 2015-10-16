package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.dao.FacadeRepository;
import org.oa.vshalimov.restaurant.data.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Employee> loadAll() {
        return facade.getEmployeeRepository().loadAll();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Employee findById(@PathParam("id") String id) {
        return facade.getEmployeeRepository().findById(Integer.parseInt(id));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(Employee employee) {
        if (facade.getEmployeeRepository().create(employee)) {
            return Response.ok(employee, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response update(Employee employee) {
        if (facade.getEmployeeRepository().update(employee)) {
            return Response.ok(employee, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response delete(Employee employee) {
        if (facade.getEmployeeRepository().delete(employee)) {
            return Response.ok(employee, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

}