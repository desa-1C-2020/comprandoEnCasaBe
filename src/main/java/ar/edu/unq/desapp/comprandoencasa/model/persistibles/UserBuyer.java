package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public class UserBuyer {
    private User user;

    public UserBuyer(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean sameUser(User user) {
        return this.user.same(user);
    }
}
