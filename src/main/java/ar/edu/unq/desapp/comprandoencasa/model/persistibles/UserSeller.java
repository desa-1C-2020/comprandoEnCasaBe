package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.com.kfgodel.nary.api.optionals.Optional;

public class UserSeller {
    private UserBasic userBasic;
    private Commerce commerce;

    public UserSeller(UserBasic userBasic, Commerce commerce) {
        this.userBasic = userBasic;
        this.commerce = commerce;
    }

    public Commerce getCommerceOrThrow() {
        Optional<Commerce> commerceOptional = getCommerce();
        if (commerceOptional.isAbsent()) {
            throw new RuntimeException("No posee un comercio registrado.");
        }
        return commerceOptional.get();
    }

    public UserBasic getUserBasic() {
        return userBasic;
    }

    public boolean sameUser(UserBasic userBasic) {
        return this.userBasic.same(userBasic);
    }

    private Optional<Commerce> getCommerce() {
        return Optional.ofNullable(commerce);
    }
}
