package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserBasic;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class RegisterUserTO2User implements MapperFunction<RegisterUserTO, UserBasic> {
    @Autowired
    private AddressTO2Address addressTO2Address;

    @Override
    public UserBasic apply(RegisterUserTO registerUserTO) {
        if (registerUserTO == null) {
            return null;
        }

        Address address = addressTO2Address.apply(registerUserTO.getAddress());
        return UserBasic.create(registerUserTO.getName(), registerUserTO.getSurname(), registerUserTO.getEmail(),
            registerUserTO.getPassword(), address);
    }
}