package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.DishType;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dishTypes")
public class DishTypeService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<DishType> loadAll() {
        return facade.getDishTypeRepository().loadAll();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public DishType findById(@PathParam("id") String id) {
        return facade.getDishTypeRepository().findById(Integer.parseInt(id));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(DishType dishType) {
        if (facade.getDishTypeRepository().create(dishType)) {
            return Response.ok(dishType, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response update(DishType dishType) {
        if (facade.getDishTypeRepository().update(dishType)) {
            return Response.ok(dishType, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response delete(DishType dishType) {
        if (facade.getDishTypeRepository().delete(dishType)) {
            return Response.ok(dishType, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

}
