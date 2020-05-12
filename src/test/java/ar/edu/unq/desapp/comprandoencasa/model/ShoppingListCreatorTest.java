package ar.edu.unq.desapp.comprandoencasa.model;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ItemByCommerceTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListItemTo;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.ShoppingListTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.ShoppingListRepository;
import ar.edu.unq.desapp.comprandoencasa.service.ShoppingListCreator;
import ar.edu.unq.desapp.comprandoencasa.service.UserFinder;
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
import static org.mockito.ArgumentMatchers.anyString;
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
        ShoppingListTo shoppingListTo = getShoppingListTo("unComercio", "unProductId");
        simulatesRightOperationForCommerceAndCommerceRepository();

        ShoppingList shoppingList = creator.from(shoppingListTo);

        assertThat(shoppingList.getTotal(), is(shoppingListTo.getTotal()));
        verify(shoppingListRepository, times(1)).save(shoppingListCaptor.capture());
        assertThat(shoppingListCaptor.getValue(), is(shoppingList));
    }

    @Test
    public void cuandoGuadaUnaListaDeComprasConUnComercioQueNoExiste_FallaLaCreacionDeLaLista() {
        String commerceId = "unComercio";
        ShoppingListTo shoppingListTo = getShoppingListTo(commerceId, "unProductId");
        when(commerceRepository.getById(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> creator.from(shoppingListTo))
            .withMessage("No existe el comercio con id: [" + commerceId + "]. No se puede crear la lista de compras");

        verify(commerceRepository, times(1)).getById(commerceId);
        verify(shoppingListRepository, never()).save(any());
    }

    @Test
    public void cuandoGuadaUnaListaDeComprasConUnProductoQueExisteEnUnComercio_FallaLaCreacionDeLaLista() {
        String commerceId = "unComercio";
        String productId = "unProductId";
        ShoppingListTo shoppingListTo = getShoppingListTo(commerceId, productId);
        Commerce anyCommerce = new Commerce("name", null, null, null, null, null);
        when(commerceRepository.getById(anyString())).thenReturn(Optional.of(anyCommerce));

        String errorMessage = "No existe el producto con id: [" + productId + "] en el comercio [" +
            anyCommerce.getName() + "]. No se puede crear la lista de compras";
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> creator.from(shoppingListTo))
            .withMessage(errorMessage);

        verify(shoppingListRepository, never()).save(any());
    }

    private ShoppingListTo getShoppingListTo(String commerceId, String productId) {
        List<ItemByCommerceTo> itemsByCommerce = getItemByCommerceTos(commerceId, productId);

        ShoppingListTo shoppingListTo = new ShoppingListTo();
        shoppingListTo.setUserId("unId");
        shoppingListTo.setTotal(BigDecimal.TEN);
        shoppingListTo.setCreationDateTime(new Date());
        shoppingListTo.setItemByCommerceTo(itemsByCommerce);
        return shoppingListTo;
    }

    private List<ItemByCommerceTo> getItemByCommerceTos(String commerceId, String productId) {
        List<ShoppingListItemTo> items = getAShoppingListItemTo(productId);
        List<ItemByCommerceTo> itemsByCommerce = new ArrayList<>();
        ItemByCommerceTo itemByCommerceTo = new ItemByCommerceTo();
        itemByCommerceTo.setCommerceId(commerceId);
        itemByCommerceTo.setItems(items);
        itemsByCommerce.add(itemByCommerceTo);
        return itemsByCommerce;
    }

    private List<ShoppingListItemTo> getAShoppingListItemTo(String unProductID) {
        List<ShoppingListItemTo> shoppingListItemTos = new ArrayList<>();
        ShoppingListItemTo itemTo = new ShoppingListItemTo();
        itemTo.setPrice(BigDecimal.ONE);
        itemTo.setProductId(unProductID);
        itemTo.setQuantity(10);
        shoppingListItemTos.add(itemTo);
        return shoppingListItemTos;
    }

    private void simulatesRightOperationForCommerceAndCommerceRepository() {
        Commerce anyCommerce = mock(Commerce.class);
        when(anyCommerce.containsProductWithId(anyString())).thenReturn(true);
        Product product = new Product("a Product", null, Integer.MIN_VALUE, Double.MIN_VALUE, null);
        when(anyCommerce.getProductById(anyString())).thenReturn(product);
        when(commerceRepository.getById(anyString())).thenReturn(Optional.of(anyCommerce));
    }
}
