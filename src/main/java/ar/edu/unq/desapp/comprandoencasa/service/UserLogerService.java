package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;

public class UserLogerService {
    private UserFinderService userFinder;

    public UserLogerService(UserFinderService userFinder) {
        this.userFinder = userFinder;
    }

    public UserLoginTO logIn(Long userPrincipalId) {
        User user = userFinder.findUserById(userPrincipalId);

        if (userFinder.isSeller(user)) {
            UserSeller rol = userFinder.findSellerByUser(user);
            Boolean missingAddress = rol.getCommerce().getAddress() == null;
            return new UserLoginTO(user, rol, missingAddress);
        } else if (userFinder.isBuyer(user)) {
            UserBuyer rol = userFinder.findBuyerByUser(user);
            Boolean missingAddress = user.getAddress() == null;
            return new UserLoginTO(user, rol, missingAddress);
        }
        return new UserLoginTO(user, null, true);
    }
}