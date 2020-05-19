package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ListOfItemsByCommerce2ListOfItemsByCommerceTO.class})
public interface ShoppingList2ShoppingListTO extends MapperFunction<ShoppingList, ShoppingListTo> {

    @Mapping(source = ShoppingList.itemsByCommerce_FIELD, target = ShoppingListTo.itemByCommerceTo_FIELD)
    @Override
    ShoppingListTo apply(ShoppingList shoppingList);
}
