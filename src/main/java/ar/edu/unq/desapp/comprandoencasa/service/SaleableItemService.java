package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;

import java.util.List;

public class SaleableItemService {
    private final UserFinder userFinder;
    private final SaleableItemRepository saleableItemRepository;

    public SaleableItemService(UserFinder userFinder, SaleableItemRepository saleableItemRepository) {
        this.userFinder = userFinder;
        this.saleableItemRepository = saleableItemRepository;
    }

    public List<SaleableItem> addSaleableProductByUserId(SaleableItem saleableItem, String userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerceOrThrow();
        commerce.addSaleableItem(saleableItem);
        saleableItemRepository.save(saleableItem);
        return commerce.getSaleableItems();
    }

    public List<SaleableItem> removeSaleableProductForUser(String productId, String userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerceOrThrow();
        SaleableItem removedItem = commerce.removeSaleableItemByProductId(productId);
        saleableItemRepository.delete(removedItem);
        return commerce.getSaleableItems();
    }

    public List<SaleableItem> updateSaleableProduct(SaleableItem saleableItem, String userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerceOrThrow();
        commerce.updateSaleableItem(saleableItem);
        return commerce.getSaleableItems();
    }
}
