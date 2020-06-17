package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import static java.util.UUID.randomUUID;

@Entity
@Table(name = "basic_user")
public class UserBasic extends PersistibleSupport {
    @Transient
    private String uid;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public UserBasic() {
    }

    private UserBasic(String name, String surname, String email, String password, Address address) {
        validateEmailIsWellFormed(email);
        this.uid = randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public static UserBasic create(String name, String surname, String email, String password, Address address) {
        return new UserBasic(name, surname, email, password, address);
    }

    public static void validateEmailIsWellFormed(String email) {
        if (email == null || !email.contains("@")) {
            throw new RuntimeException("El email: [" + email + "] no es válido.");
        }
    }

    public boolean same(UserBasic userBasicToFind) {
        return sameEmailRegistered(userBasicToFind.getEmail());
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

    public boolean sameEmailRegistered(String emailToFind) {
        return email.equals(emailToFind);
    }

    public String getUid() {
        return uid;
    }

    public boolean samePassword(String password) {
        return this.password.equals(password);
    }
}