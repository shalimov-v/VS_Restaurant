package org.oa.vshalimov.restaurant.service;

import org.oa.vshalimov.restaurant.data.Discount;
import org.oa.vshalimov.restaurant.repository.FacadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("discounts")
public class DiscountService {

    @Autowired
    private FacadeRepository facade;


    @RequestMapping(produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    List<Discount> loadAll() {
        return facade.getDiscountRepository().loadAll();
    }

    @RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody Discount findById(@PathVariable("id") String id) {
        return facade.getDiscountRepository().findById(Integer.parseInt(id));
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.POST)
    public @ResponseBody Discount create(@RequestBody Discount discount) {
        if (facade.getDiscountRepository().create(discount)) {
            return discount;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.PUT)
    public @ResponseBody Discount update(@RequestBody Discount discount) {
        if (facade.getDiscountRepository().update(discount)) {
            return discount;
        } else {
            return null;
        }
    }

    @RequestMapping(produces = "application/json", consumes = "application/json" ,method = RequestMethod.DELETE)
    public @ResponseBody Discount delete(@RequestBody Discount discount) {
        if (facade.getDiscountRepository().delete(discount)) {
            return discount;
        } else {
            return null;
        }
    }
}
