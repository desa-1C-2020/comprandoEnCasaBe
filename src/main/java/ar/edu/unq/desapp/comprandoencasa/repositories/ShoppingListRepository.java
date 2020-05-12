package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;

public interface ShoppingListRepository {
    void save(ShoppingList shoppingList);
}
