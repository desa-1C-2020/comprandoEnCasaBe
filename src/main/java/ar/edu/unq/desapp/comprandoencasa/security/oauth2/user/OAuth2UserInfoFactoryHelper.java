package ar.edu.unq.desapp.comprandoencasa.security.oauth2.user;


import ar.edu.unq.desapp.comprandoencasa.exception.OAuth2AuthenticationProcessingException;
import ar.edu.unq.desapp.comprandoencasa.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactoryHelper {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
