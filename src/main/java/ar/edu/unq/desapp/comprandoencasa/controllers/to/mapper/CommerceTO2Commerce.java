package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AddressTO2Address.class, PaymentMethodTO2PaymentMethod.class})
public interface CommerceTO2Commerce extends MapperFunction<CommerceTO, Commerce> {
    @Mappings({
        @Mapping(target = Commerce.id_FIELD, ignore = true),
        @Mapping(target = Commerce.saleableItems_FIELD, ignore = true),
        @Mapping(target = Commerce.persistenceVersion_FIELD, ignore = true)
    })
    @Override
    Commerce apply(CommerceTO commerceTO);
}