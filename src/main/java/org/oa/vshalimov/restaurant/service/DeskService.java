package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.oa.vshalimov.restaurant.data.Desk;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("desks")
public class DeskService {

    FacadeRepository facade = FacadeRepository.getInstance();

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<Desk> loadAll() {
        return facade.getDeskRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Desk findById(@PathVariable("id") String id) {
        return facade.getDeskRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.POST)
    public @ResponseBody Desk create(@RequestBody Desk desk) {
        if (facade.getDeskRepository().create(desk)) {
            return desk;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.PUT)
    public @ResponseBody Desk update(@RequestBody Desk desk) {
        if (facade.getDeskRepository().update(desk)) {
            return desk;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.DELETE)
    public @ResponseBody Desk delete(@RequestBody Desk desk) {
        if (facade.getDeskRepository().delete(desk)) {
            return desk;
        } else {
            return null;
        }
    }

}