package ar.edu.unq.desapp.comprandoencasa.controllers.to.mappers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class Address2AddressTO implements MapperFunction<Address, AddressTO> {
    @Override
    public AddressTO apply(Address address) {
        if (address == null) {
            return null;
        }

        AddressTO addressTO = new AddressTO();
        addressTO.setStreet(address.getStreet());
        addressTO.setLatitud(address.getLatitud());
        addressTO.setLongitud(address.getLongitud());
        return addressTO;
    }
}
