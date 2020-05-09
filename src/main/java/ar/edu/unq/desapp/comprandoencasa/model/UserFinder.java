package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;

public class UserFinder {
    private UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findSeller(Long userId) {
        User user = find(userId);
        if (!user.isSeller()) {
            throw new RuntimeException("Usuario usuario con id: [" + userId + "], no habilitado para vender.");
        }
        return user;
    }

    private User find(Long userId) {
        Optional<User> userOptional = userRepository.findBy(userId);
        if (userOptional.isAbsent()) {
            throw new RuntimeException("No existe el usuario con id: [" + userId + "]");
        }
        return userOptional.get();
    }
}
