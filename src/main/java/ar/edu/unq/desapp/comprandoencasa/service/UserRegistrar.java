package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

public class UserRegistrar {
    private UserFinder userFinder;
    private UserRepository userRepository;
    private UserBuyerRepository buyerRepository;
    private UserSellerRepository userSellerRepository;
    private ObjectConverter converter;

    public UserRegistrar(UserFinder userFinder, UserRepository userRepository,
                         UserBuyerRepository buyerRepository, UserSellerRepository userSellerRepository,
                         ObjectConverter objectConverter) {
        this.userFinder = userFinder;
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.userSellerRepository = userSellerRepository;
        converter = objectConverter;
    }

    public UserBuyer registerNewUser(RegisterUserTO registerUserTO) {
        UserBasic newUserBasic = converter.convertTo(UserBasic.class, registerUserTO);
        UserBasic userBasic = registerUser(newUserBasic);
        return registerBuyer(userBasic);
    }

    public UserSeller registerSellerCommerce(SellerTO sellerTo) {
        UserBasic userBasic = userFinder.findUserById(sellerTo.getUserId());
        return registerSeller(userBasic, sellerTo);
    }

    private UserBuyer registerBuyer(UserBasic userBasic) {
        UserBuyer userBuyer = new UserBuyer(userBasic);
        buyerRepository.save(userBuyer);
        return userBuyer;
    }

    private UserBasic registerUser(UserBasic userBasic) {
        if (userFinder.existsUser(userBasic)) {
            String errorMessage = "No se puede registrar debido a que existe en el sistema un usuario con el email: [" + userBasic.getEmail() + "].";
            throw new RuntimeException(errorMessage);
        }
        userRepository.addUser(userBasic);
        return userBasic;
    }

    private UserSeller registerSeller(UserBasic userBasic, SellerTO sellerTo) {
        Commerce commerce = converter.convertTo(Commerce.class, sellerTo);
        UserSeller userSeller = new UserSeller(userBasic, commerce);
        userSellerRepository.save(userSeller);
        return userSeller;
    }
}