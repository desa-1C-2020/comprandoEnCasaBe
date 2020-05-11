package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.UserFinder;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

import java.util.List;

public class UserService {
    private final UserFinder userFinder;
    private final UserSellerRepository repository;

    public UserService(UserFinder userFinder, UserSellerRepository userRepository) {
        this.userFinder = userFinder;
        this.repository = userRepository;
    }

    public List<Product> addProductByUserId(Product product, String userId) {
        UserSeller seller = userFinder.findSellerById(userId);
        seller.addProductToCommerce(product);
        repository.update(seller);
        return seller.getProducts();
    }

    public List<Product> removeProductByUserId(String productId, String userId) {
        UserSeller seller = userFinder.findSellerById(userId);
        seller.removeFromCommerce(productId);
        repository.update(seller);
        return seller.getProducts();
    }

    public List<Product> updateProductById(Product product, String userId) {
        UserSeller seller = userFinder.findSellerById(userId);
        seller.updateProduct(product);
        repository.update(seller);
        return seller.getProducts();
    }
}
