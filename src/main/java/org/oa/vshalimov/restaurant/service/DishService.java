package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.Dish;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("dishes")
public class DishService {

    @Autowired
    FacadeRepository facade;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<Dish> loadAll() {
        return facade.getDishRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Dish findById(@PathVariable("id") String id) {
        return facade.getDishRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.POST)
    public @ResponseBody Dish create(@RequestBody Dish dish) {
        if (facade.getDishRepository().create(dish)) {
            return dish;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.PUT)
    public @ResponseBody Dish update(@RequestBody Dish dish) {
        if (facade.getDishRepository().update(dish)) {
            return dish;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.DELETE)
    public @ResponseBody Dish delete(@RequestBody Dish dish) {
        if (facade.getDishRepository().delete(dish)) {
            return dish;
        } else {
            return null;
        }
    }

}