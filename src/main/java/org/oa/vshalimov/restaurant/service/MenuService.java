package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.Menu;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("menus")
public class MenuService {

    @Autowired
    private FacadeRepository facade;

    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<Menu> loadAll() {
        return facade.getMenuRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Menu findById(@PathVariable("id") String id) {
        return facade.getMenuRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(value = "/type/{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<Menu> findByDishTypeId(@PathVariable("id") String id) {
        return facade.getMenuRepository().findByDishType(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    public @ResponseBody Menu create(@RequestBody Menu menu) {
        if (facade.getMenuRepository().create(menu)) {
            return menu;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json", method = RequestMethod.PUT)
    public @ResponseBody Menu update(@RequestBody Menu menu) {
        if (facade.getMenuRepository().update(menu)) {
            return menu;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json", method = RequestMethod.DELETE)
    public @ResponseBody Menu delete(@RequestBody Menu menu) {
        if (facade.getMenuRepository().delete(menu)) {
            return menu;
        } else {
            return null;
        }
    }

}