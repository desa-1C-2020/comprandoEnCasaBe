package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ProductController.basePath)
public class ProductController {

    public static final String basePath = "/products";

    @Autowired
    private ProductService productService;
    
    @PostMapping("/saveProduct")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveNewProduct(@RequestParam("shopID") String shopID,
    						   @RequestParam("name") String name,
                               @RequestParam("brand") String brand,
                               @RequestParam("stock") String stock,
                               @RequestParam("price") String price,
                               @RequestParam("imageUrl") String imageUrl) {
    	int productStock = Integer.parseInt(stock);
    	double productPrice = Integer.parseInt(price);
    	Product product = new Product(name, brand, productStock, productPrice, imageUrl);
    	int id = Integer.parseInt(shopID);
    	this.productService.saveProduct(id, product);
    } 
    
    @DeleteMapping("/deleteProduct")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteExistingProduct(@RequestParam("shopID") String shopID,
    								  @RequestParam("productID") String productID) {
    	int idShop = Integer.parseInt(shopID);
    	int idProduct = Integer.parseInt(productID);
    	this.productService.deleteProduct(idShop, idProduct);
    } 
    
    @PostMapping("/modifyProduct")
    @ResponseStatus(value = HttpStatus.OK)
    public void modifyExistingProduct(@RequestParam("shopID") String shopID,
    								  @RequestParam("product") Product product) {
    	int idShop = Integer.parseInt(shopID);
    	this.productService.modifyProduct(idShop, product);
    } 
  
}