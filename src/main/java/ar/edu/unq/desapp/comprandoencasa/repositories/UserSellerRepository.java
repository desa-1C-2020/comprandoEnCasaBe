package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;

public interface UserSellerRepository {
    void save(UserSeller userSeller);

    Optional<UserSeller> findByUser(UserBasic userBasic);

    void update(UserSeller seller);
}
