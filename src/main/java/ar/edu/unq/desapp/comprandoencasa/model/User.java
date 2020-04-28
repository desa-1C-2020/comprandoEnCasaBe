package ar.edu.unq.desapp.comprandoencasa.model;

public class User {
    private final String name;
    private final String surname;
    private String email;

    public User(String name, String surname, String email) {
        validateEmailIsWellFormed(email);
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public static void validateEmailIsWellFormed(String email) {
        if (email == null || !email.contains("@")){
            throw new RuntimeException("El email '" + email + "' no es válido.");
        }
    }

    public boolean same(User userToFind) {
        return emailRegistered(userToFind) ;
    }

    private boolean emailRegistered(User userToFind) {
        return email == userToFind.email();
    }

    public String email() {
        return email;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }
}