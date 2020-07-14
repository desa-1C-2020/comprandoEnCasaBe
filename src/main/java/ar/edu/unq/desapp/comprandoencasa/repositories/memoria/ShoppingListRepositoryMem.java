package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void removeById(Long shoppingListToDeleteId) {
        Optional<ShoppingList> optionalShoppingList = repo
            .stream()
            .filter(shoppingList -> shoppingList.sameId(shoppingListToDeleteId))
            .findFirst();
        optionalShoppingList.ifPresent(shoppingList -> repo.remove(shoppingList));
    }
}
