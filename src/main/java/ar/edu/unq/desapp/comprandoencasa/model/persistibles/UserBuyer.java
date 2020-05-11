package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public class UserBuyer {
    private User user;
    private UserRol rol;

    public UserBuyer(User user, UserRol rol) {
        this.user = user;
        this.rol = rol;
    }

    public User getUser() {
        return user;
    }
}
