package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;

public class UserRegistrar {
    private UserFinder userFinder;
    private UserRepository userRepository;

    public UserRegistrar(UserFinder userFinder, UserRepository userRepository) {
        this.userFinder = userFinder;
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if (userFinder.existsUser(user)) {
            String errorMessage = "No se puede registrar debido a que existe en el sistema un usuario con el email: [" + user.getEmail() + "].";
            throw new RuntimeException(errorMessage);
        }
        userRepository.addUser(user);
    }

}