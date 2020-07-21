package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemsByCommerce2ItemsByCommerceTO.class})
public interface ListOfItemsByCommerce2ListOfItemsByCommerceTO extends MapperFunction<List<ItemsByCommerce>,
    List<ItemByCommerceTO>> {
}
