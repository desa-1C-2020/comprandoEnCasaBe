package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;

import java.util.List;
import java.util.function.Consumer;

public class UserService {
    private final UserFinder userFinder;
    private final UserRepository repository;

    public UserService(UserFinder userFinder, UserRepository userRepository) {
        this.userFinder = userFinder;
        this.repository = userRepository;
    }

    public List<Product> addProductByUserId(Product product, String userId) {
        return findDoAndUpdateUser(userId, (user) -> user.addProductToCommerce(product));
    }

    public List<Product> removeProductByUserId(String productId, String userId) {
        return findDoAndUpdateUser(userId, (user) -> user.removeFromCommerce(productId));
    }

    public List<Product> updateProductById(Product product, String userId) {
        return findDoAndUpdateUser(userId, (user) -> user.updateProduct(product));
    }

    private List<Product> findDoAndUpdateUser(String userId, Consumer<User> consumer) {
        User user = userFinder.findSeller(userId);
        consumer.accept(user);
        repository.update(user);
        return user.getCommerce().get().getProducts();
    }
}
