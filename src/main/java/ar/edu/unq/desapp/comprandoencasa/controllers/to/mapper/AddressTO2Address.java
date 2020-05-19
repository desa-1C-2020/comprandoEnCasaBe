package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import com.google.maps.model.LatLng;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AddressTO2Address implements MapperFunction<AddressTO, Address> {

    @Override
    public Address apply(AddressTO addressTO) {
        if (addressTO == null) {
            return null;
        }

        LatLng latLng = new LatLng(addressTO.getLatitud(), addressTO.getLongitud());

        return Address.create(addressTO.getStreet(), latLng);
    }
}
