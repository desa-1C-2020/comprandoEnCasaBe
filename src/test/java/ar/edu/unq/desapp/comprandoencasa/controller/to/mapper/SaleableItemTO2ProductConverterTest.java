package ar.edu.unq.desapp.comprandoencasa.controller.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SaleableItemTO2ProductConverterTest extends SpringIntegrationTest {

    @Autowired
    private ObjectConverter objectConverter;

    private SaleableItemTO saleableItemTO;

    @Before
    public void setUp() {
        saleableItemTO = new SaleableItemTO();
        saleableItemTO.setProductId("un id");
        saleableItemTO.setName("nombre");
        saleableItemTO.setBrand("marca");
        saleableItemTO.setImageUrl("url");
    }

    @Test
    public void convertingFromSaleableItemTO2Product_mapsAllFields() {
        Product product = objectConverter.convertTo(Product.class, saleableItemTO);

        assertThat(product.getName(), is(saleableItemTO.getName()));
        assertThat(product.getBrand(), is(saleableItemTO.getBrand()));
        assertThat(product.getImageUrl(), is(saleableItemTO.getImageUrl()));
        assertThat(product.getId(), is(saleableItemTO.getProductId()));
    }
}
