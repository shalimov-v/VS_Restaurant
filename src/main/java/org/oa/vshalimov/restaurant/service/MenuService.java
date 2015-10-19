package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.Menu;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/menus")
public class MenuService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Menu> loadAll() {
        return facade.getMenuRepository().loadAll();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Menu findById(@PathParam("id") String id) {
        return facade.getMenuRepository().findById(Integer.parseInt(id));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(Menu menu) {
        if (facade.getMenuRepository().create(menu)) {
            return Response.ok(menu, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response update(Menu menu) {
        if (facade.getMenuRepository().update(menu)) {
            return Response.ok(menu, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response delete(Menu menu) {
        if (facade.getMenuRepository().delete(menu)) {
            return Response.ok(menu, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

}