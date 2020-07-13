package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import com.google.maps.model.LatLng;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommerceTest {

    @Test
    public void whenCheckIfContainsProduct_returnsTrue() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        boolean containsProduct = kiosco.containsProduct(product);

        assertThat(containsProduct, is(true));
    }

    @Test
    public void whenCheckIfContainsProduct_returnsFalse() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(null, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        boolean containsProduct = kiosco.containsProduct(product);

        assertThat(containsProduct, is(false));
    }

    @Test
    public void whenCheckIfContainsProductById_returnsTrue() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        product.setId(1L);
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        boolean containsProductWithId = kiosco.containsProductWithId(product.getId());

        assertThat(containsProductWithId, is(true));
    }

    @Test
    public void whenCheckIfContainsProductById_returnsFalse() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(null, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        boolean containsProductWithId = kiosco.containsProductWithId(product.getId());

        assertThat(containsProductWithId, is(false));
    }

    @Test
    public void whenWantRemovesAnExistingProduct_thenTheCommerceNotContainsTheRemovedProduct() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        kiosco.removeProduct(product);

        assertThat(kiosco.containsProduct(product), is(false));
    }

    @Test
    public void whenWantUpdateAnExistingProductWithNewValues_thenTheCommerceContainsTheProductWithValueChanges() {
        Product product = new Product("un nombre de producto", "una marca", "www.imagenes.com/imagen");
        product.setId(1L);
        Product productToUpdate = new Product("un nuevo nombre de producto", "una nueva marca", "www.imagenes.com/imagenNueva");
        productToUpdate.setId(1L);
        productToUpdate.setId(product.getId());
        SaleableItem saleableItemToUpdate = new SaleableItem(30, 10.00, productToUpdate);
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        kiosco.updateSaleableItem(saleableItemToUpdate);

        assertThat(kiosco.containsProduct(productToUpdate), is(true));
    }

    @Test
    public void whenWantUpdateAnNonExistingProductWithNewValues_thenTheCommerceThrowsAnException() {
        Product product = new Product("un nombre de producto", "una marca", "www.imagenes.com/imagen");
        Product productToUpdate = new Product("un nuevo nombre de producto", "una nueva marca", "www.imagenes.com/imagenNueva");
        productToUpdate.setId(product.getId());
        SaleableItem saleableItemToUpdate = new SaleableItem(30, 10.00, productToUpdate);
        Commerce kiosco = createCommerceWith(null, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> kiosco.updateSaleableItem(saleableItemToUpdate))
            .withMessage("No se puede actualizar. No existe el producto con id: [" + saleableItemToUpdate.getProductId() + "]");
    }

    @Test
    public void whenWantRemovesAnExistingProductBy_thenTheCommerceNotContainsTheRemovedProduct() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        product.setId(1L);
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        kiosco.removeSaleableItemByProductId(product.getId());

        assertThat(kiosco.containsProduct(product), is(false));
    }

    @Test
    public void whenWantGetProductByIdAndExists_theReturnsTheCorrectProduct() {
        Product product = new Product("Lays", "Lays", "www.imagenes.com/lays");
        product.setId(1L);
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        Product foundProduct = kiosco.getProductById(product.getId());

        assertThat(foundProduct, is(product));
    }

    @Test
    public void whenWantCheckIfCOntainsProductByNameWithSameProductName_thenReturnsTrue() {
        String nameToFind = "same name";
        Product product = new Product(nameToFind, "una marca", "www.imagenes.com/imagen");
        Commerce kiosco = createCommerceWith(product, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        boolean containsProductByName = kiosco.containsProductByName(nameToFind);

        assertThat(containsProductByName, is(true));
    }

    @Test
    public void whenWantCheckIfCOntainsProductByNameButTheCommerceHasNoProducts_thenReturnsFalse() {
        String nameToFind = "same name";
        Commerce kiosco = createCommerceWith(null, new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12))));

        boolean containsProductByName = kiosco.containsProductByName(nameToFind);

        assertThat(containsProductByName, is(false));
    }

    @Test
    public void whenWantCheckIsOpenInGivenDateInRange_thenReturnTrue() {
        DayOfWeekWithTimeRange dayOfWeekWithTimeRange = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        Commerce kiosco = createCommerceWith(null, dayOfWeekWithTimeRange);
        LocalDateTime mondayInRange = LocalDateTime.of(2020, 07, 13, 8, 10);

        boolean openIn = kiosco.isOpenIn(mondayInRange);

        assertThat(openIn, is(true));
    }

    @Test
    public void whenWantCheckIsOpenInGivenDateThatIsNotInRange_thenReturnFalse() {
        DayOfWeekWithTimeRange dayOfWeekWithTimeRange = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        Commerce kiosco = createCommerceWith(null, dayOfWeekWithTimeRange);
        LocalDateTime mondayNotInRange = LocalDateTime.of(2020, 07, 13, 18, 10);

        boolean openIn = kiosco.isOpenIn(mondayNotInRange);

        assertThat(openIn, is(false));
    }

    @Test
    public void whenWantCheckIsOpenInGivenDateThatIsNotInRangeAndItsDifferentDay_thenReturnFalse() {
        DayOfWeekWithTimeRange dayOfWeekWithTimeRange = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        Commerce kiosco = createCommerceWith(null, dayOfWeekWithTimeRange);
        LocalDateTime sundayNotInRange = LocalDateTime.of(2020, 07, 12, 18, 10);

        boolean openIn = kiosco.isOpenIn(sundayNotInRange);

        assertThat(openIn, is(false));
    }

    @Test
    public void whenWantCheckIsOpenInGivenDateThatIsInRangeAndItsDifferentDay_thenReturnFalse() {
        DayOfWeekWithTimeRange dayOfWeekWithTimeRange = new DayOfWeekWithTimeRange(DayOfWeek.MONDAY,
            Collections.singletonList(new TimeRange(8, 12)));
        Commerce kiosco = createCommerceWith(null, dayOfWeekWithTimeRange);
        LocalDateTime sundayInRange = LocalDateTime.of(2020, 07, 12, 8, 10);

        boolean openIn = kiosco.isOpenIn(sundayInRange);

        assertThat(openIn, is(false));
    }

    private Commerce createCommerceWith(Product product, DayOfWeekWithTimeRange horarios) {
        Efectivo efectivo = new Efectivo("pesos");
        List<Efectivo> paymentMethods = new ArrayList<>();
        paymentMethods.add(efectivo);
        Address kioscoAddress = Address.create("Roque Sáenz Peña 284, Bernal, Buenos Aires", new LatLng(-34.7066345, -58.2819718));
        Commerce kiosco = new Commerce("un nombre de comercio", "Kiosco", kioscoAddress, paymentMethods,
            Collections.singletonList(horarios), "3km");
        SaleableItem saleableItem = new SaleableItem(1, 50.00, product);

        if (product != null) {
            kiosco.addSaleableItem(saleableItem);
        }
        return kiosco;
    }
}