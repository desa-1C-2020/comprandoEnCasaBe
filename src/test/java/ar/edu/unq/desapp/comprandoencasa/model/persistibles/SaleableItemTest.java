package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SaleableItemTest {

    @Test
    public void whenWantCheckIfIsSameProductWithSameProduct_thenReturnsTrue() {
        Product product = new Product("un nombre de producto", "una marca", "www.imagenes.com/imagen");
        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);

        boolean sameProduct = saleableItem.sameProduct(product);

        assertThat(sameProduct, is(true));
    }

    @Test
    public void whenWantCheckIfIsSameProductWithDifferentProduct_thenReturnsFalse() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        Product otherPoduct = new Product("otro nombre de producto", "otra marca", "otra url");

        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);

        boolean sameProduct = saleableItem.sameProduct(otherPoduct);

        assertThat(sameProduct, is(false));
    }

    @Test
    public void whenWantUpdateWithOtherSaleableItem_thenReplaceAllValues() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);
        Product productToUpdate = new Product("un nuevo nombre de producto", "una nueva marca", "www.imagenes.com/imagenNueva");
        productToUpdate.setId(product.getId());
        SaleableItem saleableItemToUpdate = new SaleableItem(30, 10.00, productToUpdate);

        saleableItem.updateWith(saleableItemToUpdate);

        assertThat(saleableItem.sameProduct(productToUpdate), is(true));
        assertThat(saleableItem.getPrice(), is(saleableItemToUpdate.getPrice()));
        assertThat(saleableItem.getStock(), is(saleableItemToUpdate.getStock()));
    }

    @Test
    public void whenWantCheckIfContainValueInProductNameOrBrandButIsDifferent_thenReturnsFalse() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);

        boolean sameProduct = saleableItem.containsInProductName("otro nombre");

        assertThat(sameProduct, is(false));
    }

    @Test
    public void whenWantCheckIfContainValueInProductNameOrBrandAndHasUpperCaseValueSimilar_thenReturnsFalse() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);

        boolean sameProduct = saleableItem.containsInProductName("UN NOMBRE");

        assertThat(sameProduct, is(true));
    }

    @Test
    public void whenDecrementStock_thenTheNewStockIsDecrement() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        SaleableItem saleableItem = new SaleableItem(10, 50.00, product);

        saleableItem.decrementStockIn(7);

        assertThat(saleableItem.getStock(), is(3));
    }
}