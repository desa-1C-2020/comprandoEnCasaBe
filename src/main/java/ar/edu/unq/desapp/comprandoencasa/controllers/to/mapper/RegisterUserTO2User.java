package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class RegisterUserTO2User implements MapperFunction<RegisterUserTO, User> {
    @Autowired
    private AddressTO2Address addressTO2Address;

    @Override
    public User apply(RegisterUserTO registerUserTO) {
        if (registerUserTO == null) {
            return null;
        }

        Address address = addressTO2Address.apply(registerUserTO.getAddress());
        return User.create(registerUserTO.getName(), registerUserTO.getSurname(), registerUserTO.getEmail(),
            registerUserTO.getPassword(), address);
    }
}