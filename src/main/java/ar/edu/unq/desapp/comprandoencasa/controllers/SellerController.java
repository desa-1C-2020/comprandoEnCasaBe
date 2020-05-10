package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ProductTo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SellerController.basePath)
public class SellerController {

    public static final String basePath = "/seller";

    @Autowired
    private UserService userService;

    @PostMapping("product")
    public ResponseEntity addProduct(@RequestParam String userId, @RequestBody ProductTo productTo) {
        Product product = mapToProduct(productTo);
        userService.addProductByUserId(product, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("product")
    public ResponseEntity modifyProduct(@RequestParam String userId, @RequestBody ProductTo productTo) {
        Product product = mapToProduct(productTo);
        userService.removeProductByUserId(product, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("product")
    public ResponseEntity removeProduct(@RequestParam String userId, @RequestBody ProductTo productTo) {
        Product product = mapToProduct(productTo);
        userService.removeProductByUserId(product, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    private Product mapToProduct(ProductTo productTo) {
        return new Product(productTo.getName(), productTo.getBrand(), productTo.getStock(), productTo.getPrice(),
            productTo.getImageUrl());
    }
}

