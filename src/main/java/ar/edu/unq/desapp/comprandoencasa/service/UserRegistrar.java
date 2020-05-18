package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

public class UserRegistrar {
    private UserFinder userFinder;
    private UserRepository userRepository;
    private ObjectMapper mapper;
    private UserBuyerRepository buyerRepository;
    private UserSellerRepository userSellerRepository;

    public UserRegistrar(UserFinder userFinder, ObjectMapper mapper, UserRepository userRepository,
                         UserBuyerRepository buyerRepository, UserSellerRepository userSellerRepository) {
        this.userFinder = userFinder;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.buyerRepository = buyerRepository;
        this.userSellerRepository = userSellerRepository;
    }

    public UserBuyer registerNewUser(RegisterUserTO registerUserTO) {
        User newUser = mapper.mapToToUser(registerUserTO);
        User user = registerUser(newUser);
        return registerBuyer(user);
    }

    public UserSeller registerSellerCommerce(SellerTO sellerTo) {
        User user = userFinder.findUserById(sellerTo.getUserId());
        return registerSeller(user, sellerTo);
    }

    private UserBuyer registerBuyer(User user) {
        UserBuyer userBuyer = new UserBuyer(user);
        buyerRepository.save(userBuyer);
        return userBuyer;
    }

    private User registerUser(User user) {
        if (userFinder.existsUser(user)) {
            String errorMessage = "No se puede registrar debido a que existe en el sistema un usuario con el email: [" + user.getEmail() + "].";
            throw new RuntimeException(errorMessage);
        }
        userRepository.addUser(user);
        return user;
    }

    private UserSeller registerSeller(User user, SellerTO sellerTo) {
        Commerce commerce = mapper.mapToCommerce(sellerTo);
        UserSeller userSeller = new UserSeller(user, commerce);
        userSellerRepository.save(userSeller);
        return userSeller;
    }
}