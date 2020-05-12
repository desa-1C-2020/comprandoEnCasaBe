package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BuyerController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BuyerController {

    public static final String basePath = "/buyer";

    @Autowired
    private ShoppingListCreator shoppingListCreator;

    @PostMapping(value = "shoppingList")
    public ResponseEntity addShoppingList(@RequestBody ShoppingListTo shoppingListTo) {
        shoppingListCreator.from(shoppingListTo);
        return (ResponseEntity) ResponseEntity.ok();
    }
}