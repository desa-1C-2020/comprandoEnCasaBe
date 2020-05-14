package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    @Test
    public void whenWantUpdateProductWithNewValues_thenTheProductUpdatesCorrectly() {
        Product product = new Product("un nombre de producto", "una marca", "www.imagenes.com/imagen");
        Product productToUpdate = new Product("un nuevo nombre de producto", "una nueva marca", "www.imagenes.com/imagenNueva");

        Product updatedProduct = product.updateWith(productToUpdate);

        assertThat(updatedProduct.getName(), is(productToUpdate.getName()));
        assertThat(updatedProduct.getBrand(), is(productToUpdate.getBrand()));
        assertThat(updatedProduct.getImageUrl(), is(productToUpdate.getImageUrl()));
    }

    @Test
    public void whenCheckIfAProductIsSameWithAnotherWithSameNameAndBrand_thenReturnsTrue() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        Product otherPoduct = new Product("un nombre de producto", "una marca", "otro url");

        boolean isSameProduct = product.sameProduct(otherPoduct);

        assertThat(isSameProduct, is(true));
    }

    @Test
    public void whenCheckIfAProductIsSameWithAnotherWithDifferentName_thenReturnsfalse() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        Product otherPoduct = new Product("otro nombre de producto", "una marca", "otro url");

        boolean isSameProduct = product.sameProduct(otherPoduct);

        assertThat(isSameProduct, is(false));
    }

    @Test
    public void whenCheckIfAProductIsSameWithAnotherWithDifferentBrand_thenReturnsfalse() {
        Product product = new Product("un nombre de producto", "una marca", "una url");
        Product otherPoduct = new Product("un nombre de producto", "otra marca", "otro url");

        boolean isSameProduct = product.sameProduct(otherPoduct);

        assertThat(isSameProduct, is(false));
    }
}