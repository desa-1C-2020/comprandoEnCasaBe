package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.model.AuthProvider;
import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "basic_user")
public class User extends PersistibleSupport {
    private String name;
    private String surname;
    @Column(nullable = false)
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    private String providerId;
    @Column(nullable = false)
    private Boolean emailVerified = false;
    private String imageUrl;

    public User() {
    }

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

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}