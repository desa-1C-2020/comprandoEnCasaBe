package ar.edu.unq.desapp.comprandoencasa.security.oauth2;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.exception.OAuth2AuthenticationProcessingException;
import ar.edu.unq.desapp.comprandoencasa.model.AuthProvider;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.UserRepository;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.security.oauth2.user.OAuth2UserInfo;
import ar.edu.unq.desapp.comprandoencasa.security.oauth2.user.OAuth2UserInfoFactory;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserFinder userFinder;
    @Autowired
    UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                    user.getProvider() + " account. Please use your " + user.getProvider() +
                    " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }


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
        return UserPrincipal.create(user, roles, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setSurname(oAuth2UserInfo.getSurname());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.addUser(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.addUser(existingUser);
    }

}
