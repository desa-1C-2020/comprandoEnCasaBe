package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import static java.util.UUID.randomUUID;

public class User {
    private String uid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Address address;

    public User() {
    }

    private User(String name, String surname, String email, String password, Address address) {
        validateEmailIsWellFormed(email);
        this.uid = randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public static User create(String name, String surname, String email, String password, Address address) {
        return new User(name, surname, email, password, address);
    }

    public static void validateEmailIsWellFormed(String email) {
        if (email == null || !email.contains("@")) {
            throw new RuntimeException("El email: [" + email + "] no es válido.");
        }
    }

    public boolean same(User userToFind) {
        return sameEmailRegistered(userToFind);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean sameId(String userId) {
        return uid.equals(userId);
    }

    private boolean sameEmailRegistered(User userToFind) {
        return email == userToFind.getEmail();
    }

    public String getUid() {
        return uid;
    }
}