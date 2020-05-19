package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ShoppingList2ShoppingListTO.class})
public interface ListOfShoppingList2ListOfShoppingListTO extends MapperFunction<List<ShoppingList>, List<ShoppingListTo>> {
}
