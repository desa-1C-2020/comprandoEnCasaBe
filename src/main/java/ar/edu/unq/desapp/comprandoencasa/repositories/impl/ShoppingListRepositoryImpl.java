package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.ShoppingListRepositoryJpa;

import java.util.List;

public class ShoppingListRepositoryImpl implements ShoppingListRepository {
    private ShoppingListRepositoryJpa repoJpa;

    public static ShoppingListRepository create(ShoppingListRepositoryJpa shoppingListRepositoryJpa) {
        ShoppingListRepositoryImpl shoppingListRepository = new ShoppingListRepositoryImpl();
        shoppingListRepository.repoJpa = shoppingListRepositoryJpa;
        return shoppingListRepository;
    }

    @Override
    public void save(ShoppingList shoppingList) {
        repoJpa.saveAndFlush(shoppingList);
    }

    @Override
    public List<ShoppingList> getAllByUser(User user) {
        return repoJpa.findByUser(user);
    }

    @Override
    public void removeById(Long shoppingListToDeleteId) {
        repoJpa.deleteById(shoppingListToDeleteId);
    }
}
