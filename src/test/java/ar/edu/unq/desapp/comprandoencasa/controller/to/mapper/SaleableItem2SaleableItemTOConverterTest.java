package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SaleableItem2SaleableItemTOConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private SaleableItem saleableItem;

    @Before
    public void setUp() {
        Product product = new Product("Pure de tomate de la huerta 530grs", "De la huerta baggio", "http://url.com");
        saleableItem = new SaleableItem(4, 70.00, product);
    }


    @Test
    public void convertingFromSaleableItem2SaleableItemTO_mapsAllFields() {
        SaleableItemTO saleableItemTO = objectConverter.convertTo(SaleableItemTO.class, this.saleableItem);
        Product product = saleableItem.getProduct();
        assertThat(saleableItemTO.getPrice(), is(saleableItem.getPrice()));
        assertThat(saleableItemTO.getStock(), is(saleableItem.getStock()));
        assertThat(saleableItemTO.getName(), is(product.getName()));
        assertThat(saleableItemTO.getBrand(), is(product.getBrand()));
        assertThat(saleableItemTO.getImageUrl(), is(product.getImageUrl()));
        assertThat(saleableItemTO.getProductId(), is(product.getId()));
    }
}
