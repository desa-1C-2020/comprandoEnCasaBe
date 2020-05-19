package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Product;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class SaleableItemTO2SaleableItem implements MapperFunction<SaleableItemTO, SaleableItem> {
    @Autowired
    private SaleableItemTO2Product saleableItemTO2Product;

    @Override
    public SaleableItem apply(SaleableItemTO saleableItemTO) {
        if (saleableItemTO == null) {
            return null;
        }

        Product product = saleableItemTO2Product.apply(saleableItemTO);
        return new SaleableItem(saleableItemTO.getStock(), saleableItemTO.getPrice(), product);
    }
}