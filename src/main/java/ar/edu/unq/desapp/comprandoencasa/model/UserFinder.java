package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserSellerRepository;

import java.util.List;

public class UserFinder {
    private UserRepository userRepository;
    private UserSellerRepository userSellerRepository;

    public UserFinder(UserRepository userRepository, UserSellerRepository userSellerRepository) {
        this.userRepository = userRepository;
        this.userSellerRepository = userSellerRepository;
    }

    public UserSeller findSellerById(String userId) {
        User user = findUserById(userId);
        Optional<UserSeller> userSeller = userSellerRepository.findByUser(user);
        if (userSeller.isAbsent()) {
            throw new RuntimeException("Usuario no registrado como vendedor. ID [" + userId + "]");
        }
        return userSeller.get();
    }

    public User findUserById(String userId) {
        Optional<User> userOptional = userRepository.findBy(userId);
        return getUserOrThrow(userId, userOptional);
    }

    public boolean existsUser(User user) {
        List<User> users = userRepository.getAll();
        return users.stream().anyMatch(userPersited -> userPersited.same(user));
    }

    private User getUserOrThrow(String userId, Optional<User> userOptional) {
        if (userOptional.isAbsent()) {
            throw new RuntimeException("No existe el usuario con id: [" + userId + "]");
        }
        return userOptional.get();
    }
}
