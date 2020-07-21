package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceWithFoundProductsTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DayOfWeekWithTimeRange;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.TimeRange;
import ar.edu.unq.desapp.meta.SpringIntegrationTest;
import com.google.maps.model.LatLng;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductFinderTest extends SpringIntegrationTest {

    private ProductFinderService productFinder;

    @Mock
    private CommerceFinderService commerceFinder;

    @Mock
    private GoogleConnector googleConnector;

    @Autowired
    private ObjectConverter converter;

    @Before
    public void setUp() {
        productFinder = new ProductFinderService(commerceFinder, googleConnector, converter);
    }

    @Test
    public void whenFindByNameInRangeForUserAndNoExistsAnyOneCommerceWithThisProduct_thenReturnsAnEmptyList() {
        when(commerceFinder.findAllInsideRange(any(), any())).thenReturn(Collections.emptyList());
        LatLng latLngFrom = new LatLng(123, 123);
        Address userAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", latLngFrom);
        String maxDistance = "10";
        String productToFind = "un nombre";

        List<CommerceWithFoundProductsTO> commerceWithFoundProducts = productFinder.findByNameInRangeForUser(productToFind, maxDistance, userAddress);

        assertThat(commerceWithFoundProducts, empty());
    }

    @Test
    public void whenFindByNameInRangeForUserAndExistsCommerceWithTheSearchedProduct_thenReturnsAnListWithTheCommerceAndProduct() {
        long distance = 10L;
        String maxDistance = "20";
        String productToFind = "un nombre";
        Commerce commerce = simulatesCommerceWithName("un nombre de comercio");
        List<Commerce> commercesSaved = new ArrayList<>();
        commercesSaved.add(commerce);
        when(commerceFinder.findAllInsideRange(any(), any())).thenReturn(commercesSaved);
        when(googleConnector.distanceInMetersBetweenTwoLatLng(any(), any())).thenReturn(Optional.of(distance));
        LatLng latLngFrom = new LatLng(123, 123);
        Address userAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", latLngFrom);

        List<CommerceWithFoundProductsTO> commerceWithFoundProducts = productFinder.findByNameInRangeForUser(productToFind, maxDistance, userAddress);

        CommerceWithFoundProductsTO commerceWithFoundProduct = commerceWithFoundProducts.get(0);
        assertThat(commerceWithFoundProduct.getCommerceName(), is("un nombre de comercio"));
        assertThat(commerceWithFoundProduct.getDistance(), is(distance));
    }

    private Commerce simulatesCommerceWithName(String commerceName) {
        Product product = new Product("un nombre", "una marca", "www.imagenes.com/imagen");
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(PaymentMethod.CASH);
        DayOfWeekWithTimeRange horarios = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        Address kioscoAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));
        Commerce kiosco = new Commerce(commerceName, "Kiosco", kioscoAddress, paymentMethods,
            Collections.singletonList(horarios), "3km");
        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);

        if (product != null) {
            kiosco.addSaleableItem(saleableItem);
        }
        return kiosco;
    }
}
