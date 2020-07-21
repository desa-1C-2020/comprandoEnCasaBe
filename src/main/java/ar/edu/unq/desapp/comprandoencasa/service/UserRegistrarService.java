package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

public class UserRegistrarService {
    private UserFinderService userFinder;
    private UserRepository userRepository;
    private UserBuyerRepository buyerRepository;
    private UserSellerRepository userSellerRepository;
    private ObjectConverter converter;

    public UserRegistrarService(UserFinderService userFinder, UserRepository userRepository,
                         UserBuyerRepository buyerRepository, UserSellerRepository userSellerRepository,
                         ObjectConverter objectConverter) {
        this.userFinder = userFinder;
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
        this.userSellerRepository = userSellerRepository;
        converter = objectConverter;
    }

    public UserSeller updateSeller(Long userId, CommerceTO commerceTo) {
        User user = userFinder.findUserById(userId);
        return registerSellerCommerce(user, commerceTo);
    }

    public UserBuyer updateBuyer(Long userId, AddressTO addressTO) {
        User user = userFinder.findUserById(userId);
        Address address = converter.convertTo(Address.class, addressTO);
        user.setAddress(address);
        userRepository.addUser(user);
        return registerBuyer(user);
    }

    private UserBuyer registerBuyer(User user) {
        UserBuyer userBuyer = new UserBuyer(user);
        buyerRepository.save(userBuyer);
        return userBuyer;
    }

    private UserSeller registerSellerCommerce(User user, CommerceTO commerceTo) {
        Commerce commerce = converter.convertTo(Commerce.class, commerceTo);
        UserSeller userSeller = new UserSeller(user, commerce);
        userSellerRepository.save(userSeller);
        return userSeller;
    }
}