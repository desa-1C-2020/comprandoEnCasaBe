package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;

import java.util.List;

public class UserFinder {
    private UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findSeller(String userId) {
        User user = findById(userId);
        if (!user.isSeller()) {
            throw new RuntimeException("Usuario usuario con id: [" + userId + "], no habilitado para vender.");
        }
        return user;
    }

    private User findById(String userId) {
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
