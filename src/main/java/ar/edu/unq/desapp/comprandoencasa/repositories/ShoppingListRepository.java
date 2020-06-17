package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;

import java.util.List;

public interface ShoppingListRepository {
    void save(ShoppingList shoppingList);

    List<ShoppingList> getAllByUser(UserBasic userBasic);

    void removeById(String shoppingListToDeleteId);
}
