package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import static java.util.UUID.randomUUID;

public class User {
    private final String uid;
    private final String name;
    private final String surname;
    private String email;

    private User(String name, String surname, String email) {
        validateEmailIsWellFormed(email);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.uid = randomUUID().toString();
    }

    public static User create(String name, String surname, String email) {
        return new User(name, surname, email);
    }

    public static void validateEmailIsWellFormed(String email) {
        if (email == null || !email.contains("@")) {
            throw new RuntimeException("El email: [" + email + "] no es válido.");
        }
    }

    public boolean same(User userToFind) {
        return emailRegistered(userToFind);
    }

    private boolean emailRegistered(User userToFind) {
        return email == userToFind.getEmail();
    }

    public String getUid() {
        return uid;
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

    public boolean sameId(String userId) {
        return uid.equals(userId);
    }
}