package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.service.SaleableItemService;
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
    private SaleableItemService saleableItemService;

    @Autowired
    private ObjectConverter converter;

    @PostMapping(value = "product")
    public List<SaleableItem> addSaleableProduct(@RequestParam Long userId, @RequestBody SaleableItemTO saleableItemTo) {
        SaleableItem saleableItem = converter.convertTo(SaleableItem.class, saleableItemTo);
        return saleableItemService.addSaleableProductByUserId(saleableItem, userId);
    }

    @DeleteMapping("product")
    public List<SaleableItem> removeSaleableProduct(@RequestParam Long userId, @RequestParam Long productId) {
        return saleableItemService.removeSaleableProductForUser(productId, userId);
    }

    @PatchMapping("product")
    public List<SaleableItem> updateSaleableProduct(@RequestParam Long userId, @RequestBody SaleableItemTO saleableItemTo) {
        SaleableItem saleableItem = converter.convertTo(SaleableItem.class, saleableItemTo);
        return saleableItemService.updateSaleableProduct(saleableItem, userId);
    }
}

