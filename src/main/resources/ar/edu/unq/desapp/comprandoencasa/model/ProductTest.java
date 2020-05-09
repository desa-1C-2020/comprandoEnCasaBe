package ar.edu.unq.desapp.comprandoencasa.model;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProductTest {

	@Test
	public void whenCreateAProductWithAllTheInfo_theProductIsCreated() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		assertTrue(p instanceof Product);
	}
	
	@Test
	public void whenModifyingAProductsId_theProductIsModified() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		p.setId(2);
		assertTrue(p.getId() == 2);
	}

	@Test
	public void whenModifyingAProductsName_theProductIsModified() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		p.setName("Arvejas");
		assertTrue(p.getName() == "Arvejas");
	}
	
	@Test
	public void whenModifyingAProductsBrand_theProductIsModified() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		p.setBrand("Molto");
		assertTrue(p.getBrand() == "Molto");
	}
	
	@Test
	public void whenModifyingAProductsStock_theProductIsModified() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		p.setStock(19);
		assertTrue(p.getStock() == 19);
	}
	
	@Test
	public void whenModifyingAProductsPrice_theProductIsModified() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		p.setPrice(59.99);
		assertTrue(p.getPrice() == 59.99);
	}
	
	@Test
	public void whenModifyingAProductsUrl_theProductIsModified() {
		Product p = new Product(1, "Fideos", "Marolio", 20, 50, "mockURL");
		p.setImageUrl("www.mock.com");
		assertTrue(p.getImageUrl() == "www.mock.com");
	}
}