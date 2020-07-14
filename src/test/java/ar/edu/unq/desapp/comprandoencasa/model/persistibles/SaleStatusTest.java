package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleStatus.CANCELED;
import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleStatus.PAID_OUT;
import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleStatus.PENDING;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SaleStatusTest {
    @Test
    public void pendingStatus() {
        String type = "PENDING";

        SaleStatus saleStatus = SaleStatus.valueOf(type);

        assertThat(saleStatus, is(PENDING));
    }

    @Test
    public void paidOutStatus() {
        String type = "PAID_OUT";

        SaleStatus saleStatus = SaleStatus.valueOf(type);

        assertThat(saleStatus, is(PAID_OUT));
    }

    @Test
    public void canceledStatus() {
        String type = "CANCELED";

        SaleStatus saleStatus = SaleStatus.valueOf(type);

        assertThat(saleStatus, is(CANCELED));
    }

    @Test
    public void whenPendingStatusAffectRegister_thenDecreaseStock() {
        int initialStock = 10;
        int stockVariation = 3;
        int expectedStock = 7;
        assertForStatusTheStock(PENDING, initialStock, stockVariation, expectedStock);
    }

    @Test
    public void whenPaidOutStatusAffectRegister_thenDoNothing() {
        int initialStock = 15;
        int stockVariation = 10;
        int expectedStock = 15;
        assertForStatusTheStock(PAID_OUT, initialStock, stockVariation, expectedStock);
    }

    @Test
    public void whenCanceledStatusAffectRegister_thenRestoreStock() {
        int initialStock = 20;
        int stockVariation = 4;
        int expectedStock = 24;
        assertForStatusTheStock(CANCELED, initialStock, stockVariation, expectedStock);
    }

    private void assertForStatusTheStock(SaleStatus saleStatus, int initialStock, int stockVariation, int expectedStock) {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        product.setId(1L);
        Commerce commerce = createCommerceWith(product, initialStock);
        ShoppingListItem shoppingListItem = new ShoppingListItem(product, stockVariation, BigDecimal.valueOf(50.00));
        List<ShoppingListItem> items = Collections.singletonList(shoppingListItem);

        saleStatus.affectStock(commerce, items);

        SaleableItem saleableItem = commerce.getSaleableItemByProduct(product).get();
        assertThat(saleableItem.getStock(), is(expectedStock));
    }

    private Commerce createCommerceWith(Product product, int initialStock) {
        Commerce commerce = new Commerce("un nombre de comercio", "Kiosco", null, Collections.singletonList(PaymentMethod.CASH),
            Collections.emptyList(), "3km");
        SaleableItem saleableItem = new SaleableItem(initialStock, 50.00, product);
        if (product != null) {
            commerce.addSaleableItem(saleableItem);
        }
        return commerce;
    }
}