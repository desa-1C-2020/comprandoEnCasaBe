package ar.edu.unq.desapp.comprandoencasa.controllers;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleUpdateTO;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.security.CurrentUser;
import ar.edu.unq.desapp.comprandoencasa.security.UserPrincipal;
import ar.edu.unq.desapp.comprandoencasa.service.SaleRegisterService;
import ar.edu.unq.desapp.comprandoencasa.service.SaleableItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private SaleRegisterService saleRegisterService;

    @PostMapping(value = "product")
    public List<SaleableItem> addSaleableProduct(@CurrentUser UserPrincipal userPrincipal, @RequestBody SaleableItemTO saleableItemTo) {
        SaleableItem saleableItem = converter.convertTo(SaleableItem.class, saleableItemTo);
        return saleableItemService.addSaleableProductByUserId(saleableItem, userPrincipal.getId());
    }

    @PostMapping(value = "productsBatch")
    public List<SaleableItem> addSaleableProductsBatch(@CurrentUser UserPrincipal userPrincipal, @RequestBody List<SaleableItemTO> saleableItemsTO) {
        saleableItemsTO.stream().forEach(saleableItemTO -> {
            SaleableItem saleableItem = converter.convertTo(SaleableItem.class, saleableItemTO);
            saleableItemService.addSaleableProductByUserId(saleableItem, userPrincipal.getId());
        });

        return saleableItemService.getProductsFor(userPrincipal.getId());
    }

    @DeleteMapping("product")
    public List<SaleableItem> removeSaleableProduct(@CurrentUser UserPrincipal userPrincipal, @RequestParam Long productId) {
        return saleableItemService.removeSaleableProductForUser(productId, userPrincipal.getId());
    }

    @PatchMapping("product")
    public List<SaleableItem> updateSaleableProduct(@CurrentUser UserPrincipal userPrincipal, @RequestBody SaleableItemTO saleableItemTo) {
        SaleableItem saleableItem = converter.convertTo(SaleableItem.class, saleableItemTo);
        return saleableItemService.updateSaleableProduct(saleableItem, userPrincipal.getId());
    }

    @GetMapping("products")
    public List<SaleableItem> products(@CurrentUser UserPrincipal userPrincipal) {
        return saleableItemService.getProductsFor(userPrincipal.getId());
    }

    @PostMapping("update/sale")
    public List<SaleRegister> updatePurchaseRegisterStatus(@CurrentUser UserPrincipal userPrincipal, @RequestBody SaleUpdateTO saleUpdateTO) {
        return saleRegisterService.update(saleUpdateTO, userPrincipal.getId());
    }

    @PostMapping("sales")
    public List<SaleRegister> getSalesRegister(@CurrentUser UserPrincipal userPrincipal) {
        return saleRegisterService.getAllFor(userPrincipal.getId());
    }
}

