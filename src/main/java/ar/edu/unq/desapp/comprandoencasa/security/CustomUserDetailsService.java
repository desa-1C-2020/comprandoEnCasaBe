package ar.edu.unq.desapp.comprandoencasa.security;


import ar.edu.unq.desapp.comprandoencasa.exception.ResourceNotFoundException;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFinderService userFinder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + email)
            );
        boolean isSeller = userFinder.isSeller(user);
        boolean isBuyer = userFinder.isBuyer(user);
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        if (isBuyer) {
            roles.add("ROLE_BUYER");
        }
        if (isSeller) {
            roles.add("ROLE_SELLER");
        }
        return UserPrincipal.create(user, roles);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}