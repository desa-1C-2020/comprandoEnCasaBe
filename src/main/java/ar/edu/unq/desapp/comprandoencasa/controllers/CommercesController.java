package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.security.CurrentUser;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.service.CommerceFinder;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CommercesController.basePath)
public class CommercesController {
    public static final String basePath = "/commerces";

    @Autowired
    private CommerceFinder commerceFinder;

    @Autowired
    private UserFinder userFinder;

    @GetMapping("findInRange")
    public List<Commerce> getAllWithinGivenDistance(@CurrentUser UserPrincipal userPrincipal,
                                                    @RequestParam("maxDistance") String maxDistanceMeters) {

        User user = userFinder.findUserById(userPrincipal.getId());
        return commerceFinder.findAllInsideRange(maxDistanceMeters, user.getAddress().getLatLng());
    }
}

