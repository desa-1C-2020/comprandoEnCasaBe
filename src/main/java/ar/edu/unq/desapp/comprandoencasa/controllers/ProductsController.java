package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceWithFoundProductsTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.security.CurrentUser;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.service.ProductFinderService;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinderService;
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
    private ProductFinderService productFinder;

    @Autowired
    private UserFinderService userFinder;

    @GetMapping("find")
    public List<CommerceWithFoundProductsTO> findByName(@CurrentUser UserPrincipal userPrincipal,
                                                      @RequestParam("maxDistance") String maxDistanceMeters,
                                                      @RequestParam("productToFind") String productName) {
        User user = userFinder.findUserById(userPrincipal.getId());
        return productFinder.findByNameInRangeForUser(productName, maxDistanceMeters, user.getAddress());
    }

    @GetMapping("by-name")
    public List<CommerceWithFoundProductsTO> find(@RequestParam("productToFind") String productName) {
        return productFinder.productsByname(productName);
    }
}

