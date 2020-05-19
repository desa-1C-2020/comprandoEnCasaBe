package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
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

    @Override
    public void removeById(String shoppingListToDeleteId) {
        Optional<ShoppingList> optionalShoppingList = getShoppingListById(shoppingListToDeleteId);
        if (optionalShoppingList.isPresent()) {
            repo.remove(optionalShoppingList.get());
        }
    }

    @Override
    public ShoppingList getById(String shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = getShoppingListById(shoppingListId);
        if (optionalShoppingList.isAbsent()) {
            throw new RuntimeException("No existe la lista de compras con id: [" + shoppingListId + "].");
        }
        return optionalShoppingList.get();
    }

    private Optional<ShoppingList> getShoppingListById(String shoppingListToDeleteId) {
        java.util.Optional<ShoppingList> found = repo
            .stream()
            .filter(shoppingList -> shoppingList.sameId(shoppingListToDeleteId))
            .findFirst();
        return Optional.create(found);
    }
}
