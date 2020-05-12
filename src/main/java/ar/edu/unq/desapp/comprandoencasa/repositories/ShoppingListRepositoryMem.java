package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListRepositoryMem implements ShoppingListRepository {

    private List<ShoppingList> repo;

    public ShoppingListRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public void save(ShoppingList shoppingList) {
        repo.add(shoppingList);
    }
}
