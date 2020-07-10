package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceWithFoundProducts;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.security.CurrentUser;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.service.ProductFinder;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ProductsController.basePath)
public class ProductsController {

    public static final String basePath = "/products";
    @Autowired
    private ProductFinder productFinder;

    @Autowired
    private UserFinder userFinder;

    @GetMapping("find")
    public List<CommerceWithFoundProducts> findByName(@CurrentUser UserPrincipal userPrincipal,
                                                      @RequestParam("maxDistance") String maxDistanceMeters,
                                                      @RequestParam("productToFind") String productToFind) {
        User user = userFinder.findUserById(userPrincipal.getId());
        return productFinder.findByNameInRangeForUser(productToFind, maxDistanceMeters, user.getAddress());
    }
}

