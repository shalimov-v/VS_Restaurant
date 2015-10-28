package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.Client;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("clients")
public class ClientService {

    @Autowired
    private FacadeRepository facade;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    List<Client> loadAll() {
        return facade.getClientRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Client findById(@PathVariable("id") String id) {
        return facade.getClientRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.POST)
    public @ResponseBody Client create(@RequestBody Client client) {
        if (facade.getClientRepository().create(client)) {
            return client;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.PUT)
    public @ResponseBody Client update(@RequestBody Client client) {
        if (facade.getClientRepository().update(client)) {
            return client;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.DELETE)
    public @ResponseBody Client delete(@RequestBody Client client) {
        if (facade.getClientRepository().delete(client)) {
            return client;
        } else {
            return null;
        }
    }

}
