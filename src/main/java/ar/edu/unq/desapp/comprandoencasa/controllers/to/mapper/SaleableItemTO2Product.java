package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class SaleableItemTO2Product implements MapperFunction<SaleableItemTO, Product> {

    @Override
    public Product apply(SaleableItemTO saleableItemTO) {
        if (saleableItemTO == null) {
            return null;
        }

        Product product = new Product(saleableItemTO.getName(), saleableItemTO.getBrand(), saleableItemTO.getImageUrl());
        String saleableItemTOProductId = saleableItemTO.getProductId();
        if (saleableItemTOProductId != null) {
            product.setId(saleableItemTOProductId);
        }
        return product;
    }
}