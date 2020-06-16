package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class SaleableItem2SaleableItemTO implements MapperFunction<SaleableItem, SaleableItemTO> {
    @Override
    public SaleableItemTO apply(SaleableItem saleableItem) {
        if (saleableItem == null) {
            return null;
        }
        SaleableItemTO saleableItemTO = new SaleableItemTO();
        saleableItemTO.setProductId(saleableItem.getProductId());
        saleableItemTO.setImageUrl(saleableItem.getProduct().getImageUrl());
        saleableItemTO.setBrand(saleableItem.getProduct().getBrand());
        saleableItemTO.setName(saleableItem.getProduct().getName());
        saleableItemTO.setPrice(saleableItem.getPrice());
        saleableItemTO.setStock(saleableItem.getStock());

        return saleableItemTO;
    }
}