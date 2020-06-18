package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.service.UserLoger;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    public static final String basePath = "/account";
    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRegistrar userRegistrar;

    @Autowired
    private UserLoger userLoger;

    @PostMapping("buyer")
    public UserBuyer registerBuyer(@RequestBody RegisterUserTO registerUserTO) {
        UserBuyer userBuyer = userRegistrar.registerNewUser(registerUserTO);
        //Aca mappearlo a un to para quitar por ejemplo el password en este caso.
        logger.info("Se creo el usuario comprador con id: [" + userBuyer.getUser().getId() + "]");
        return userBuyer;
    }

    @PostMapping("seller")
    public UserSeller registerSeller(@RequestBody SellerTO sellerTo) {
        UserSeller userSeller = userRegistrar.registerSellerCommerce(sellerTo);
        //Aca mappearlo a un to para quitar por ejemplo el password en este caso.
        logger.info("Se creo el usuario vendedor con id: [" + userSeller.getUser().getId() + "]");
        return userSeller;
    }

    @PostMapping("login/buyer")
    public UserBuyer loginBuyer(@RequestBody UserLoginTo userLoginTo) {
        return userLoger.logInBuyer(userLoginTo);
    }

    @PostMapping("login/seller")
    public UserSeller loginSeller(@RequestBody UserLoginTo userLoginTo) {
        return userLoger.logInSeller(userLoginTo);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserLoginTo userLoginTo) {
        return ResponseEntity.ok().body(userLoger.logIn(userLoginTo));
    }
}

