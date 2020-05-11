package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.ProductTo;
import ar.edu.unq.desapp.comprandoencasa.model.ObjectMapper;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = SellerController.basePath, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SellerController {

    public static final String basePath = "/seller";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @PostMapping(value = "product")
    public List<Product> addProduct(@RequestParam String userId, @RequestBody ProductTo productTo) {
        Product product = mapper.mapToProduct(productTo);
        return userService.addProductByUserId(product, userId);
    }

    @DeleteMapping("product")
    public List<Product> removeProduct(@RequestParam String userId, @RequestParam String productId) {
        return userService.removeProductByUserId(productId, userId);
    }

    @PatchMapping("product")
    public List<Product> updateProduct(@RequestParam String userId, @RequestBody ProductTo productTo) {
        Product product = mapper.mapToProduct(productTo);
        return userService.updateProductById(product, userId);
    }
}

