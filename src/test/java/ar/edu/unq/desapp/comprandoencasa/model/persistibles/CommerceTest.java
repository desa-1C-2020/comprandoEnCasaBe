package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommerceTest {

    @Test
    public void whenCheckIfContainsProduct_returnsTrue() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(product);

        boolean containsProduct = kiosco.containsProduct(product);

        assertThat(containsProduct, is(true));
    }

    @Test
    public void whenCheckIfContainsProduct_returnsFalse() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(null);

        boolean containsProduct = kiosco.containsProduct(product);

        assertThat(containsProduct, is(false));
    }

    @Test
    public void whenCheckIfContainsProductById_returnsTrue() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(product);

        boolean containsProductWithId = kiosco.containsProductWithId(product.getId());

        assertThat(containsProductWithId, is(true));
    }

    @Test
    public void whenCheckIfContainsProductById_returnsFalse() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(null);

        boolean containsProductWithId = kiosco.containsProductWithId(product.getId());

        assertThat(containsProductWithId, is(false));
    }

    @Test
    public void whenWantRemovesAnExistingProduct_thenTheCommerceNotContainsTheRemovedProduct() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(product);

        kiosco.removeProduct(product);

        assertThat(kiosco.containsProduct(product), is(false));
    }

    @Test
    public void whenWantUpdateAnExistingProductWithNewValues_thenTheCommerceContainsTheProductWithValueChanges() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Product productToUpdate = new Product("Lays 2", "Lays", 30, 50.00, "www.imagenes.com/lays2");
        Commerce kiosco = createCommerceWith(product);

        kiosco.updateProduct(productToUpdate);

        assertThat(kiosco.containsProduct(productToUpdate), is(true));
    }

    @Test
    public void whenWantRemovesAnExistingProductBy_thenTheCommerceNotContainsTheRemovedProduct() {
        Product product = new Product("Lays", "Lays", 1, 50.00, "www.imagenes.com/lays");
        Commerce kiosco = createCommerceWith(product);

        kiosco.removeProductById(product.getId());

        assertThat(kiosco.containsProduct(product), is(false));
    }


    private Commerce createCommerceWith(Product product) {
        List<String> paymentMethods = new ArrayList<>();
        List<String> horarios = new ArrayList<>();
        paymentMethods.add("Efectivo");
        horarios.add("Lunes a viernes de 10 a 18hs");
        Commerce kiosco = new Commerce("un nombre de comercio", "Kiosco", "Roque Sáenz Peña 284, Bernal, Buenos Aires", paymentMethods, horarios, "3km");

        if (product != null) {
            kiosco.addProduct(product);
        }
        return kiosco;
    }
}