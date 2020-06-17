package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;

public interface UserBuyerRepository {
    void save(UserBuyer userBuyer);

    Optional<UserBuyer> findByUser(UserBasic userBasic);
}
