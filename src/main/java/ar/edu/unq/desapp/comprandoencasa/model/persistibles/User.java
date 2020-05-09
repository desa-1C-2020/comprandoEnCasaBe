package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.optionals.Optional;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

public class User {
    private final String name;
    private final String surname;
    private final String uid;
    private String email;
    private UserRol rol;
    private List<Commerce> commerces;

    private User(String name, String surname, String email, UserRol rol) {
        this.rol = rol;
        validateEmailIsWellFormed(email);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.commerces = new ArrayList<>();
        this.uid = randomUUID().toString();
    }

    public static User createWithoutCommerce(String name, String surname, String email, UserRol rol) {
        return new User(name, surname, email, rol);
    }

    public static User createWithCommerce(String name, String surname, String email, UserRol rol, Commerce commerce) {
        User user = new User(name, surname, email, rol);
        user.addCommerce(commerce);
        return user;
    }

    public static void validateEmailIsWellFormed(String email) {
        if (email == null || !email.contains("@")) {
            throw new RuntimeException("El getEmail '" + email + "' no es válido.");
        }
    }

    public boolean same(User userToFind) {
        return emailRegistered(userToFind);
    }

    private boolean emailRegistered(User userToFind) {
        return email == userToFind.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Optional<Commerce> getCommerce() {
        return Optional.ofNullable(commerces.get(0));
    }

    public void addCommerce(Commerce commerce) {
        commerces.add(commerce);
    }

    public boolean isSeller() {
        return rol.isSeller();
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
        Optional<Commerce> commerceOptional = getCommerce();
        if (commerceOptional.isAbsent()) {
            throw new RuntimeException("No posee un comercio registrado. No se puede verificar si existe el producto.");
        }
        Commerce commerce = commerceOptional.get();
        return commerce.containsProduct(product);
    }

    public boolean sameId(String userId) {
        return uid.equals(userId);
    }

    public void removeFromCommerce(Product product) {
        if (containsProductInCommerce(product)) {
            Commerce existingcommerce = getCommerce().get();
            existingcommerce.removeProduct(product);
        }
    }

    public String getUid() {
        return uid;
    }
}