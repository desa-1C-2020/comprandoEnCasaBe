package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ListOfItemsByCommerce2ListOfItemsByCommerceTO.class})
public interface ShoppingList2ShoppingListTO extends MapperFunction<ShoppingList, ShoppingListTO> {

    @Mapping(source = ShoppingList.itemsByCommerce_FIELD, target = ShoppingListTO.itemByCommerceTo_FIELD)
    @Override
    ShoppingListTO apply(ShoppingList shoppingList);
}
