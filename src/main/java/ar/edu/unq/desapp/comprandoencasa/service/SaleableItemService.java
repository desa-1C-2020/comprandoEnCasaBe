package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;

import java.util.List;

public class SaleableItemService {
    private final UserFinderService userFinder;
    private final SaleableItemRepository saleableItemRepository;
    private final CommerceRepository commerceRepository;

    public SaleableItemService(UserFinderService userFinder, SaleableItemRepository saleableItemRepository, CommerceRepository commerceRepository) {
        this.userFinder = userFinder;
        this.saleableItemRepository = saleableItemRepository;
        this.commerceRepository = commerceRepository;
    }

    public List<SaleableItem> addSaleableProductByUserId(SaleableItem saleableItem, Long userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerce();
        commerce.addSaleableItem(saleableItem);
        saleableItemRepository.save(saleableItem);
        commerceRepository.save(commerce);
        return commerce.getSaleableItems();
    }

    public List<SaleableItem> removeSaleableProductForUser(Long productId, Long userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerce();
        SaleableItem removedItem = commerce.removeSaleableItemByProductId(productId);
        saleableItemRepository.delete(removedItem);
        commerceRepository.save(commerce);
        return commerce.getSaleableItems();
    }

    public List<SaleableItem> updateSaleableProduct(SaleableItem saleableItem, Long userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerce();
        commerce.updateSaleableItem(saleableItem);
        commerceRepository.save(commerce);
        return commerce.getSaleableItems();
    }

    public List<SaleableItem> getProductsFor(Long userId) {
        UserSeller seller = userFinder.findSellerByUserId(userId);
        Commerce commerce = seller.getCommerce();
        return commerce.getSaleableItems();
    }
}
