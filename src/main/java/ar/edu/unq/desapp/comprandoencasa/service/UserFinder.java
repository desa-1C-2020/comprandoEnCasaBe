package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBuyer;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserBuyerRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

import java.util.List;

public class UserFinder {
    private UserRepository userRepository;
    private UserSellerRepository userSellerRepository;
    private UserBuyerRepository userBuyerRepository;

    public UserFinder(UserRepository userRepository, UserSellerRepository userSellerRepository,
                      UserBuyerRepository userBuyerRepository) {
        this.userRepository = userRepository;
        this.userSellerRepository = userSellerRepository;
        this.userBuyerRepository = userBuyerRepository;
    }

    public UserSeller findSellerByUserId(String userId) {
        UserBasic userBasic = findUserById(userId);
        return findSellerByUser(userBasic);
    }

    public UserBuyer findBuyerByUser(UserBasic userBasic) {
        Optional<UserBuyer> userBuyer = userBuyerRepository.findByUser(userBasic);
        if (userBuyer.isAbsent()) {
            throw new RuntimeException("Usuario no registrado como comprador. ID [" + userBasic.getUid() + "]");
        }
        return userBuyer.get();
    }

    public UserSeller findSellerByUser(UserBasic userBasic) {
        Optional<UserSeller> userSeller = userSellerRepository.findByUser(userBasic);
        if (userSeller.isAbsent()) {
            throw new RuntimeException("Usuario no registrado como vendedor. ID [" + userBasic.getUid() + "]");
        }
        return userSeller.get();
    }

    public UserBasic findUserById(String userId) {
        Optional<UserBasic> userOptional = userRepository.findById(userId);
        return getUserOrThrow(userOptional, "No existe el usuario con id: [" + userId + "]");
    }

    public boolean existsUser(UserBasic userBasic) {
        List<UserBasic> userBasics = userRepository.getAll();
        return userBasics.stream().anyMatch(userPersited -> userPersited.same(userBasic));
    }

    private UserBasic getUserOrThrow(Optional<UserBasic> userOptional, String message) {
        if (userOptional.isAbsent()) {
            throw new RuntimeException(message);
        }
        return userOptional.get();
    }

    public UserBasic findByEmail(String email) {
        Optional<UserBasic> userOptional = userRepository.findByEmail(email);
        return getUserOrThrow(userOptional, "Usuario o contraseña incorrectos.");
    }

    public boolean isSeller(UserBasic userBasic) {
        Optional<UserSeller> userSeller = userSellerRepository.findByUser(userBasic);
        return !userSeller.isAbsent();
    }

    public boolean isBuyer(UserBasic userBasic) {
        Optional<UserBuyer> userBuyer = userBuyerRepository.findByUser(userBasic);
        return !userBuyer.isAbsent();
    }
}
