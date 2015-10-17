package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.Dish;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dishes")
public class DishService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Dish> loadAll() {
        return facade.getDishRepository().loadAll();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Dish findById(@PathParam("id") String id) {
        return facade.getDishRepository().findById(Integer.parseInt(id));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(Dish dish) {
        if (facade.getDishRepository().create(dish)) {
            return Response.ok(dish, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response update(Dish dish) {
        if (facade.getDishRepository().update(dish)) {
            return Response.ok(dish, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response delete(Dish dish) {
        if (facade.getDishRepository().delete(dish)) {
            return Response.ok(dish, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

}