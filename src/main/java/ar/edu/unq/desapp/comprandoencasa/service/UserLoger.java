package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;

public class UserLoger {
    private UserFinder userFinder;

    public UserLoger(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    public Object logIn(UserLoginTo userLoginTo) {
        User user = userFinder.findByEmail(userLoginTo.getEmail());
        if (!user.samePassword(userLoginTo.getPassword())) {
            return throwWrongUserOrPassError();
        }

        Object userFound = null;
        if (userFinder.isSeller(user)) {
            userFound = userFinder.findSellerByUser(user);
        } else if (userFinder.isBuyer(user)) {
            userFound = userFinder.findBuyerByUser(user);
        } else {
            throwWrongUserOrPassError();
        }
        return userFound;
    }

    public UserBuyer logInBuyer(UserLoginTo userLoginTo) {
        User user = userFinder.findByEmail(userLoginTo.getEmail());
        if (user.samePassword(userLoginTo.getPassword())) {
            throwWrongUserOrPassError();
        }
        return userFinder.findBuyerByUser(user);
    }

    public UserSeller logInSeller(UserLoginTo userLoginTo) {
        User user = userFinder.findByEmail(userLoginTo.getEmail());
        if (user.samePassword(userLoginTo.getPassword())) {
            throwWrongUserOrPassError();
        }
        return userFinder.findSellerByUser(user);
    }

    private RuntimeException throwWrongUserOrPassError() {
        throw new RuntimeException("Usuario o contraseña incorrectos.");
    }
}
