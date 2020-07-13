package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.TakeAwayTO;
import ar.edu.unq.desapp.comprandoencasa.security.CurrentUser;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = PurchaseController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    public static final String basePath = "/purchase";

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping(value = "takeAway")
    public LocalDateTime getTakeAwayOptions(@RequestBody TakeAwayTO takeAwayTO) {
        return purchaseService.getTakeAwayOptionFor(takeAwayTO.getCommercesId(), takeAwayTO.getSuggestedDay());
    }

    @GetMapping(value = "delivery")
    public LocalDateTime getDeliveryOptions(@CurrentUser UserPrincipal userPrincipal) {
        return purchaseService.getDeliveryOption(userPrincipal.getId());
    }
    }
}