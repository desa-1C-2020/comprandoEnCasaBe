package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.DayWithRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.service.DeliveryOptions;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = PurchaseController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    public static final String basePath = "/purchase";

    @Autowired
    private ShoppingListCreator shoppingListCreator;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeliveryOptions deliveryOptions;

    @PostMapping(value = "delivery/options")
    public List<DayWithRange> getDeliveryOptions(@RequestParam String shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.getById(shoppingListId);
        List<DayWithRange> daysWithRange = deliveryOptions.optionsFor(shoppingList);
        return daysWithRange;
    }
}