package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.DishType;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("dishTypes")
public class DishTypeService {

    @Autowired
    private FacadeRepository facade;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<DishType> loadAll() {
        return facade.getDishTypeRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody DishType findById(@PathVariable("id") String id) {
        return facade.getDishTypeRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.POST)
    public @ResponseBody DishType create(@RequestBody DishType dishType) {
        if (facade.getDishTypeRepository().create(dishType)) {
            return dishType;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.PUT)
    public @ResponseBody DishType update(@RequestBody DishType dishType) {
        if (facade.getDishTypeRepository().update(dishType)) {
            return dishType;
        } else {
            return dishType;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.DELETE)
    public @ResponseBody DishType delete(@RequestBody DishType dishType) {
        if (facade.getDishTypeRepository().delete(dishType)) {
            return dishType;
        } else {
            return null;
        }
    }

}
