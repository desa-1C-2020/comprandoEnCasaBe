package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListCreatorTest {

    private ShoppingListCreator creator;

    @Mock
    private UserFinder userFinder;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private CommerceRepository commerceRepository;

    @Captor
    private ArgumentCaptor<ShoppingList> shoppingListCaptor;

    @Before
    public void setUp() {
        creator = new ShoppingListCreator(userFinder, shoppingListRepository, commerceRepository);
    }

    @Test
    public void whenCreatesAShoppingListWithValidValus_thenSavesTheCreatedShoppingList() {
        ShoppingListTo shoppingListTo = getShoppingListTo(123L, 1234L);
        simulatesRightOperationForCommerceAndCommerceRepository();

        ShoppingList shoppingList = creator.createAndSave(shoppingListTo, Long.MAX_VALUE);

        assertThat(shoppingList.getTotal(), is(shoppingListTo.getTotal()));
        verify(shoppingListRepository, times(1)).save(shoppingListCaptor.capture());
        assertThat(shoppingListCaptor.getValue(), is(shoppingList));
    }

    @Test
    public void whenWantCreateAShoppingListWithACommerceThatNotExists_thenFailsWithExceptionAndNotSaveTheShoppingList() {
        Long commerceId = 123L;
        ShoppingListTo shoppingListTo = getShoppingListTo(commerceId, 1234L);
        when(commerceRepository.getById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> creator.createAndSave(shoppingListTo, Long.MAX_VALUE))
            .withMessage("No existe el comercio con id: [" + commerceId + "]. No se puede crear la lista de compras");

        verify(commerceRepository, times(1)).getById(commerceId);
        verify(shoppingListRepository, never()).save(any());
    }

    @Test
    public void whenWantCreateAShoppingListWithAProductThatNotExistsInCommerce_thenFailsWithExceptionAndNotSaveTheShoppingList() {
        Long commerceId = 123L;
        Long productId = 1234L;
        ShoppingListTo shoppingListTo = getShoppingListTo(commerceId, productId);
        Commerce anyCommerce = new Commerce("name", null, null, null, null, null);
        when(commerceRepository.getById(anyLong())).thenReturn(Optional.of(anyCommerce));

        String errorMessage = "No existe el producto con id: [" + productId + "] en el comercio [" +
            anyCommerce.getName() + "]. No se puede crear la lista de compras";
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> creator.createAndSave(shoppingListTo, Long.MAX_VALUE))
            .withMessage(errorMessage);

        verify(shoppingListRepository, never()).save(any());
    }

    @Test
    public void whenWantGetAllListsForUser_thenGetsTheListForTheUser() {
        User user = User.create("aName", "aSurname", "anEmail@email.com", "password", null);
        user.setId(Long.MAX_VALUE);
        ShoppingList shoppingList = createShoppingList(user);
        List<ShoppingList> shoppingLists = new ArrayList<>();
        shoppingLists.add(shoppingList);
        when(shoppingListRepository.getAllByUser(user)).thenReturn(shoppingLists);
        when(userFinder.findUserById(anyLong())).thenReturn(user);

        List<ShoppingList> recoveredShoppingLists = creator.recreateAllListsForUserWithId(user.getId());

        verify(shoppingListRepository, times(1)).getAllByUser(user);
        verify(userFinder, times(1)).findUserById(anyLong());
        assertThat(recoveredShoppingLists.size(), is(1));
        assertThat(recoveredShoppingLists.get(0), is(shoppingList));
    }

    private ShoppingList createShoppingList(User user) {
        List<ItemsByCommerce> itemsByCommerces = new ArrayList<>();
        return new ShoppingList(user, itemsByCommerces, BigDecimal.TEN, new Date());
    }

    private ShoppingListTo getShoppingListTo(Long commerceId, Long productId) {
        List<ItemByCommerceTo> itemsByCommerce = getItemByCommerceTos(commerceId, productId);

        ShoppingListTo shoppingListTo = new ShoppingListTo();
        shoppingListTo.setTotal(BigDecimal.TEN);
        shoppingListTo.setCreationDateTime(new Date());
        shoppingListTo.setItemByCommerceTo(itemsByCommerce);
        return shoppingListTo;
    }

    private List<ItemByCommerceTo> getItemByCommerceTos(Long commerceId, Long productId) {
        List<ShoppingListItemTO> items = getAShoppingListItemTo(productId);
        List<ItemByCommerceTo> itemsByCommerce = new ArrayList<>();
        ItemByCommerceTo itemByCommerceTo = new ItemByCommerceTo();
        itemByCommerceTo.setCommerceId(commerceId);
        itemByCommerceTo.setItems(items);
        itemsByCommerce.add(itemByCommerceTo);
        return itemsByCommerce;
    }

    private List<ShoppingListItemTO> getAShoppingListItemTo(Long unProductID) {
        List<ShoppingListItemTO> shoppingListItemTOS = new ArrayList<>();
        ShoppingListItemTO itemTo = new ShoppingListItemTO();
        itemTo.setPrice(BigDecimal.ONE);
        itemTo.setProductId(unProductID);
        itemTo.setQuantity(10);
        shoppingListItemTOS.add(itemTo);
        return shoppingListItemTOS;
    }

    private void simulatesRightOperationForCommerceAndCommerceRepository() {
        Commerce anyCommerce = mock(Commerce.class);
        when(anyCommerce.containsProductWithId(anyLong())).thenReturn(true);
        Product product = new Product("a Product", null, null);
        when(anyCommerce.getProductById(anyLong())).thenReturn(product);
        when(commerceRepository.getById(anyLong())).thenReturn(Optional.of(anyCommerce));
    }
}
