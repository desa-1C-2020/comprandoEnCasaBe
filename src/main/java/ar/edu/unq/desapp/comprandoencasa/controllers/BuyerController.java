package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = BuyerController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BuyerController {

    public static final String basePath = "/buyer";

    @Autowired
    private ShoppingListCreator shoppingListCreator;

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "shoppingList")
    public ResponseEntity addShoppingList(@RequestBody ShoppingListTo shoppingListTo) {
        shoppingListCreator.createAndSave(shoppingListTo);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @GetMapping(value = "shoppingList")
    public List<ShoppingListTo> getShoppingList(@RequestParam String userId) {
        List<ShoppingList> shoppingList = shoppingListCreator.recreateAllListsForUserWithId(userId);
        return objectMapper.mapToShoppingListsTo(shoppingList);
    }

    @DeleteMapping(value = "shoppingList")
    public ResponseEntity deleteShoppingList(@RequestParam String shoppingListToDeleteId) {
        shoppingListRepository.removeById(shoppingListToDeleteId);
        return (ResponseEntity) ResponseEntity.ok();
    }
}