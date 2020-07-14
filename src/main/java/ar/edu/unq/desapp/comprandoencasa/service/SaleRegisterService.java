package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleUpdateTO;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ItemsByCommerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleStatus;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingList;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.ShoppingListItem;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.repositories.CommerceRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleRegisterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaleRegisterService {
    private SaleRegisterRepository saleRegisterRepository;
    private CommerceRepository commerceRepository;
    private UserFinder userFinder;

    public SaleRegisterService(SaleRegisterRepository saleRegisterRepository, CommerceRepository commerceRepository, UserFinder userFinder) {
        this.saleRegisterRepository = saleRegisterRepository;
        this.commerceRepository = commerceRepository;
        this.userFinder = userFinder;
    }

    public List<SaleRegister> update(SaleUpdateTO saleUpdateTO, Long userSellerId) {
        Commerce commerce = getCommereByBuyer(userSellerId);
        Optional<SaleRegister> sale = saleRegisterRepository.findById(saleUpdateTO.getSaleId());
        if (sale.isPresent()) {
            SaleRegister saleRegister = sale.get();
            saleRegister.setSaleStatus(saleUpdateTO.getNewSaleStatus());
        }
        return saleRegisterRepository.findAllByCommerce(commerce);
    }

    private Commerce getCommereByBuyer(Long userSellerId) {
        UserSeller seller = userFinder.findSellerByUserId(userSellerId);
        return seller.getCommerce();
    }

    public List<SaleRegister> createAndSaveSale(ShoppingList shoppingList) {
        List<ItemsByCommerce> itemsByCommerce = shoppingList.getItemsByCommerce();
        return itemsByCommerce
            .stream()
            .map(itemByCommerce -> createPurchaseRegisterAndAffectStockFrom(itemByCommerce, shoppingList))
            .collect(Collectors.toList());
    }

    private SaleRegister createPurchaseRegisterAndAffectStockFrom(ItemsByCommerce itemByCommerce, ShoppingList shoppingList) {
        Commerce commerce = itemByCommerce.getCommerce();
        List<ShoppingListItem> items = itemByCommerce.getItems();
        List itemsCopy = new ArrayList<>();
        itemsCopy.addAll(items); //Para saltearme el tema de que es una coleccion persistente
        SaleRegister saleRegister = SaleRegister.create(commerce, shoppingList, itemsCopy, SaleStatus.PENDING, shoppingList.getUser());
        commerceRepository.save(commerce);
        saleRegisterRepository.save(saleRegister);
        return saleRegister;
    }
}
