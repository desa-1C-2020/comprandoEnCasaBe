package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import ar.edu.unq.desapp.comprandoencasa.service.UserSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = BuyerController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BuyerController {

    public static final String basePath = "/buyer";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserSellerService userSellerService;

    @Autowired
    private ShoppingListCreator shoppingListCreator;

    @PostMapping(value = "shoppingList")
    public ResponseEntity addShoppingList(@RequestParam String userId, @RequestBody ShoppingListTo shoppingListTo) {
        shoppingListCreator.from(shoppingListTo);
        return (ResponseEntity) ResponseEntity.ok();
    }
}

