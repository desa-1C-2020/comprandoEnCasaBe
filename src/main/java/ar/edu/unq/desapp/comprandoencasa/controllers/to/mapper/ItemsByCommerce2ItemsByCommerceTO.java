package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ListOfShoppingListItem2ListOfShoppingListItemTO.class})
public interface ItemsByCommerce2ItemsByCommerceTO extends MapperFunction<ItemsByCommerce, ItemByCommerceTO> {
}
