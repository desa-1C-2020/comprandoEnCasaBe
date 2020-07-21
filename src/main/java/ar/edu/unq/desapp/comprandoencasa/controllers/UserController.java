package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.security.CurrentUser;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.service.UserLogerService;
import ar.edu.unq.desapp.comprandoencasa.service.UserRegistrarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    public static final String basePath = "/user";
    public static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRegistrarService userRegistrar;
    @Autowired
    private UserLogerService userLoger;

    @PostMapping("buyer/update")
    public UserBuyer updateBuyer(@CurrentUser UserPrincipal userPrincipal, @RequestBody AddressTO addressTO) {
        UserBuyer userBuyer = userRegistrar.updateBuyer(userPrincipal.getId(), addressTO);
        logger.info("Se creo el usuario comprador con id: [" + userBuyer.getUser().getId() + "]");
        logger.info("Recibo como buyer: [" + addressTO + "]");
        return userBuyer;
    }

    @PostMapping("seller/update")
    public UserSeller updateSeller(@CurrentUser UserPrincipal userPrincipal, @RequestBody CommerceTO commerceTo) {
        UserSeller userSeller = userRegistrar.updateSeller(userPrincipal.getId(), commerceTo);
        logger.info("Recibo como seller: [" + commerceTo + "]");
        logger.info("Se creo el usuario vendedor con id: [" + userSeller.getUser().getId() + "]");
        return userSeller;
    }

    @GetMapping("me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity me(@CurrentUser UserPrincipal userPrincipal) {
        UserLoginTO user = userLoger.logIn(userPrincipal.getId());
        return ResponseEntity.ok().body(user);
    }
}

