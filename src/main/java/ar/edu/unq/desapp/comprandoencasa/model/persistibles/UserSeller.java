package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_seller")
public class UserSeller extends PersistibleSupport {
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Commerce commerce;

    public UserSeller(User user, Commerce commerce) {
        this.user = user;
        this.commerce = commerce;
    }

    public Commerce getCommerceOrThrow() {
        Optional<Commerce> commerceOptional = getCommerce();
        if (commerceOptional.isAbsent()) {
            throw new RuntimeException("No posee un comercio registrado.");
        }
        return commerceOptional.get();
    }

    public User getUser() {
        return user;
    }

    public boolean sameUser(User user) {
        return this.user.same(user);
    }

    private Optional<Commerce> getCommerce() {
        return Optional.ofNullable(commerce);
    }
}
