package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SellerTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AddressTO2Address.class, PaymentMethodTO2PaymentMethod.class})
public interface SellerTO2Commerce extends MapperFunction<SellerTO, Commerce> {
    @Mappings({
        @Mapping(source = SellerTO.commerceName_FIELD, target = Commerce.name_FIELD),
        @Mapping(source = SellerTO.commerceAddress_FIELD, target = Commerce.address_FIELD),
        @Mapping(target = Commerce.id_FIELD, ignore = true),
        @Mapping(target = Commerce.saleableItems_FIELD, ignore = true),
        @Mapping(target = Commerce.persistenceVersion_FIELD, ignore = true)
    })
    @Override
    Commerce apply(SellerTO sellerTO);
}