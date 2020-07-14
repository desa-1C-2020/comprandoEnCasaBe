package ar.edu.unq.desapp.comprandoencasa.repositories.spring;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSellerRepositoryJpa extends JpaRepository<UserSeller, Long> {
    UserSeller findByUser(User user);

    UserSeller findByCommerce(Commerce commerce);
}
