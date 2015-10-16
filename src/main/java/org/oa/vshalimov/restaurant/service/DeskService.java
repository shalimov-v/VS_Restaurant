package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.oa.vshalimov.restaurant.data.Desk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/desks")
public class DeskService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Desk> loadAll() {
        return facade.getDeskRepository().loadAll();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Desk findById(@PathParam("id") String id) {
        return facade.getDeskRepository().findById(Integer.parseInt(id));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(Desk table) {
        if (facade.getDeskRepository().create(table)) {
            return Response.ok(table, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response update(Desk desk) {
        if (facade.getDeskRepository().update(desk)) {
            return Response.ok(desk, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response delete(Desk desk) {
        if (facade.getDeskRepository().delete(desk)) {
            return Response.ok(desk, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(304).build();
        }
    }

}
