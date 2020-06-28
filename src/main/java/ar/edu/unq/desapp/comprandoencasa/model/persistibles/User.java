package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "basic_user")
public class User extends PersistibleSupport {
    private String name;
    private String surname;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public User() {}

    private User(String name, String surname, String email, String password, Address address) {
        validateEmailIsWellFormed(email);
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
        return sameEmailRegistered(userToFind.getEmail());
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

    public boolean sameId(Long userId) {
        return getId().equals(userId);
    }

    public boolean sameEmailRegistered(String emailToFind) {
        return email.equals(emailToFind);
    }


    public boolean samePassword(String password) {
        return this.password.equals(password);
    }
}