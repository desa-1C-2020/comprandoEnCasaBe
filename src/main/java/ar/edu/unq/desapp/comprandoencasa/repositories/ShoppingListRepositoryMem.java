package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingListRepositoryMem implements ShoppingListRepository {

    private List<ShoppingList> repo;

    public ShoppingListRepositoryMem() {
        this.repo = new ArrayList<>();
    }

    @Override
    public void save(ShoppingList shoppingList) {
        repo.add(shoppingList);
    }

    @Override
    public List<ShoppingList> getAllByUser(User user) {
        return repo
            .stream()
            .filter(shoppingList -> shoppingList.itIsFrom(user))
            .collect(Collectors.toList());
    }
}
