package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.optionals.Optional;

import java.util.List;

public class UserSeller {
    private User user;
    private UserRol userRol;
    private Commerce commerce;

    public UserSeller(User user, UserRol userRol, Commerce commerce) {
        this.user = user;
        this.userRol = userRol;
        this.commerce = commerce;
    }

    public Optional<Commerce> getCommerce() {
        return Optional.ofNullable(commerce);
    }

    public void addProductToCommerce(Product product) {
        Optional<Commerce> commerceOptional = getCommerce();
        if (commerceOptional.isAbsent()) {
            throw new RuntimeException("No posee un comercio registrado. No se puede agregar un producto.");
        }
        Commerce commerce = commerceOptional.get();
        commerce.addProduct(product);
    }

    public boolean containsProductInCommerce(Product product) {
        return getCommerceToFindProduct().containsProduct(product);
    }

    public boolean containsProductInCommerceId(String productId) {
        return getCommerceToFindProduct().containsProductWithId(productId);
    }

    private Commerce getCommerceToFindProduct() {
        Optional<Commerce> commerceOptional = getCommerce();
        if (commerceOptional.isAbsent()) {
            throw new RuntimeException("No posee un comercio registrado. No se puede verificar si existe el producto.");
        }
        return commerceOptional.get();
    }

    public void removeFromCommerce(String productId) {
        if (containsProductInCommerceId(productId)) {
            Commerce existingcommerce = getCommerce().get();
            existingcommerce.removeProductById(productId);
        }
    }

    public void updateProduct(Product product) {
        if (containsProductInCommerce(product)) {
            Commerce existingcommerce = getCommerce().get();
            existingcommerce.updateProduct(product);
        }
    }

    public boolean isSeller() {
        return userRol.isSeller();
    }

    public List<Product> getProducts() {
        return getCommerceToFindProduct().getProducts();
    }

    public User getUser() {
        return user;
    }

    public boolean sameUser(User user) {
        return user.same(user);
    }
}
