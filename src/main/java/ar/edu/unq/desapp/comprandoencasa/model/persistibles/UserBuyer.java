package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_buyer")
public class UserBuyer extends PersistibleSupport {
    @OneToOne
    private User user;

    public UserBuyer() {}

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
