package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String surname;
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
    }

    public static User createWithoutCommerce(String name, String surname, String email, UserRol rol){
        return new User(name, surname, email, rol);
    }

    public static User createWithCommerce(String name, String surname, String email, UserRol rol, Commerce commerce){
        User user = new User(name, surname, email, rol);
        user.addCommerce(commerce);
        return user;
    }

    public static void validateEmailIsWellFormed(String email) {
        if (email == null || !email.contains("@")){
            throw new RuntimeException("El getEmail '" + email + "' no es válido.");
        }
    }

    public boolean same(User userToFind) {
        return emailRegistered(userToFind) ;
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

    public UserRol getRol() {
        return rol;
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
}