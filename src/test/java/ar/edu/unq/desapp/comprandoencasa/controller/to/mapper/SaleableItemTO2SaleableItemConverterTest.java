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

public class SaleableItemTO2SaleableItemConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private SaleableItemTO saleableItemTO;

    @Before
    public void setUp() {
        saleableItemTO = new SaleableItemTO();
        saleableItemTO.setProductId(123L);
        saleableItemTO.setName("nombre");
        saleableItemTO.setBrand("marca");
        saleableItemTO.setImageUrl("url");
        saleableItemTO.setStock(10);
        saleableItemTO.setPrice(Double.MAX_VALUE);
    }

    @Test
    public void convertingFromSaleableItemTO2Product_mapsAllFields() {
        SaleableItem saleableItem = objectConverter.convertTo(SaleableItem.class, this.saleableItemTO);
        Product product = saleableItem.getProduct();
        assertThat(saleableItem.getPrice(), is(saleableItemTO.getPrice()));
        assertThat(saleableItem.getStock(), is(saleableItemTO.getStock()));
        assertThat(product.getName(), is(this.saleableItemTO.getName()));
        assertThat(product.getBrand(), is(this.saleableItemTO.getBrand()));
        assertThat(product.getImageUrl(), is(this.saleableItemTO.getImageUrl()));
        assertThat(product.getId(), is(this.saleableItemTO.getProductId()));
    }
}
