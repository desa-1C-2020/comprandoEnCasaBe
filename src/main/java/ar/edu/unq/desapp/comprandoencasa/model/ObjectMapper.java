package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.AddressTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ProductTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.RegisterUserTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import com.google.maps.model.LatLng;

public class ObjectMapper {
    public User mapToToUser(RegisterUserTO userto) {
        return User.create(userto.getName(), userto.getSurname(), userto.getEmail());
    }

    public Commerce mapToCommerce(SellerTo sellerTo) {
        AddressTo addressTo = sellerTo.getCommerceAddress();
        Address address = mapToAddress(addressTo);
        return new Commerce(sellerTo.getCommerceName(), sellerTo.getCommerceBusinessSector(),
            address, sellerTo.getPaymentMethods(), sellerTo.getDaysAndHoursOpen(), sellerTo.getArrivalRange());
    }

    private Address mapToAddress(AddressTo addressTo) {
        LatLng latLng = new LatLng(addressTo.getLatitud(), addressTo.getLongitud());
        return new Address(addressTo.getStreet(), latLng);
    }

    public Product mapToProduct(ProductTo productTo) {
        return new Product(productTo.getName(), productTo.getBrand(), productTo.getStock(), productTo.getPrice(),
            productTo.getImageUrl());
    }
}
