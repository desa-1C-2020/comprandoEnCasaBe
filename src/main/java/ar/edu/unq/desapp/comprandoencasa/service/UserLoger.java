package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.UserLoginTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;

public class UserLoger {
    private UserFinder userFinder;

    public UserLoger(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    public Object logIn(UserLoginTo userLoginTo) {
        UserBasic userBasic = userFinder.findByEmail(userLoginTo.getEmail());
        if (!userBasic.samePassword(userLoginTo.getPassword())) {
            return throwWrongUserOrPassError();
        }

        Object userFound = null;
        if (userFinder.isSeller(userBasic)) {
            userFound = userFinder.findSellerByUser(userBasic);
        } else if (userFinder.isBuyer(userBasic)) {
            userFound = userFinder.findBuyerByUser(userBasic);
        } else {
            throwWrongUserOrPassError();
        }
        return userFound;
    }

    public UserBuyer logInBuyer(UserLoginTo userLoginTo) {
        UserBasic userBasic = userFinder.findByEmail(userLoginTo.getEmail());
        if (userBasic.samePassword(userLoginTo.getPassword())) {
            throwWrongUserOrPassError();
        }
        return userFinder.findBuyerByUser(userBasic);
    }

    public UserSeller logInSeller(UserLoginTo userLoginTo) {
        UserBasic userBasic = userFinder.findByEmail(userLoginTo.getEmail());
        if (userBasic.samePassword(userLoginTo.getPassword())) {
            throwWrongUserOrPassError();
        }
        return userFinder.findSellerByUser(userBasic);
    }

    private RuntimeException throwWrongUserOrPassError() {
        throw new RuntimeException("Usuario o contraseña incorrectos.");
    }
}
