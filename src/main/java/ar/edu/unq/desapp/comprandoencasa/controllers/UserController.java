package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String basePath = "/account";

    @Autowired
    private UserRegistrar userRegistrar;

    @PostMapping("buyer")
    public UserBuyer registerBuyer(@RequestBody RegisterUserTO registerUserTO) {
        return userRegistrar.registerNewUser(registerUserTO);
    }

    @PostMapping("seller")
    public UserSeller registerSeller(@RequestBody SellerTO sellerTo) {
        return userRegistrar.registerSellerCommerce(sellerTo);
    }
}

