package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
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
        User user = findUserById(userId);
        return findSellerByUser(user);
    }

    public UserBuyer findBuyerByUser(User user) {
        Optional<UserBuyer> userBuyer = userBuyerRepository.findByUser(user);
        if (userBuyer.isAbsent()) {
            throw new RuntimeException("Usuario no registrado como comprador. ID [" + user.getUid() + "]");
        }
        return userBuyer.get();
    }

    public UserSeller findSellerByUser(User user) {
        Optional<UserSeller> userSeller = userSellerRepository.findByUser(user);
        if (userSeller.isAbsent()) {
            throw new RuntimeException("Usuario no registrado como vendedor. ID [" + user.getUid() + "]");
        }
        return userSeller.get();
    }

    public User findUserById(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return getUserOrThrow(userOptional, "No existe el usuario con id: [" + userId + "]");
    }

    public boolean existsUser(User user) {
        List<User> users = userRepository.getAll();
        return users.stream().anyMatch(userPersited -> userPersited.same(user));
    }

    private User getUserOrThrow(Optional<User> userOptional, String message) {
        if (userOptional.isAbsent()) {
            throw new RuntimeException(message);
        }
        return userOptional.get();
    }

    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return getUserOrThrow(userOptional, "Usuario o contraseña incorrectos.");
    }

    public boolean isSeller(User user) {
        Optional<UserSeller> userSeller = userSellerRepository.findByUser(user);
        return !userSeller.isAbsent();
    }

    public boolean isBuyer(User user) {
        Optional<UserBuyer> userBuyer = userBuyerRepository.findByUser(user);
        return !userBuyer.isAbsent();
    }
}
