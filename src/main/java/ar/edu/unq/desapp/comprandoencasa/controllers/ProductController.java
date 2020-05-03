package ar.edu.unq.desapp.comprandoencasa.controllers;

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

    @PostMapping("/saveProduct")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveNewProduct(@RequestParam("shop") String shopID,
    						   @RequestParam("name") String name,
                               @RequestParam("brand") String marca,
                               @RequestParam("stock") String stock,
                               @RequestParam("price") String price,
                               @RequestParam("imageUrl") String imageUrl) {
    	
    } 
    
    @DeleteMapping("/deleteProduct")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteExistingProduct(@RequestParam("shop") String shopID,
    									  @RequestParam("productID") String productID) {
    	
    } 
    
    
    @PostMapping("/modifyProduct")
    @ResponseStatus(value = HttpStatus.OK)
    public void modifyExistingProduct(@RequestParam("shop") String shopID,
    									  @RequestParam("productID") String productID) {
    	
    } 
  
}