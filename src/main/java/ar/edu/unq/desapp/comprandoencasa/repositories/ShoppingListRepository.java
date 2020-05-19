package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.util.List;

public interface ShoppingListRepository {
    void save(ShoppingList shoppingList);

    List<ShoppingList> getAllByUser(User user);

    void removeById(String shoppingListToDeleteId);

    ShoppingList getById(String shoppingListId);
}
