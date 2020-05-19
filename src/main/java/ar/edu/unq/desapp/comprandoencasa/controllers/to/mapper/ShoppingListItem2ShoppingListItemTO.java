package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingListItem2ShoppingListItemTO extends MapperFunction<ShoppingListItem, ShoppingListItemTO> {
    @Override
    ShoppingListItemTO apply(ShoppingListItem shoppingListItem);
}
