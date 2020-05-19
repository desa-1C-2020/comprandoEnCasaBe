package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShoppingListItem2ShoppingListItemTO.class})
public interface ListOfShoppingListItem2ListOfShoppingListItemTO extends MapperFunction<List<ShoppingListItem>,
    List<ShoppingListItemTO>> {
}
