package ar.edu.unq.desapp.comprandoencasa.service;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.configurations.GoogleConnector;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.CommerceWithFoundProducts;
import ar.edu.unq.desapp.comprandoencasa.controllers.to.SaleableItemTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.ObjectConverter;
import ar.edu.unq.desapp.comprandoencasa.model.DistanceCalculator;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Address;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFinder {
    private final DistanceCalculator distanceCalculator;
    private CommerceFinder commerceFinder;
    private ObjectConverter converter;

    public ProductFinder(CommerceFinder commerceFinder, GoogleConnector googleConnector, ObjectConverter converter) {
        this.commerceFinder = commerceFinder;
        this.distanceCalculator = new DistanceCalculator(googleConnector);
        this.converter = converter;
    }

    public List<CommerceWithFoundProducts> findByNameInRangeForUser(String productToFind, String maxDistanceInMeters, Address userAddress) {
        LatLng latLngFrom = userAddress.getLatLng();
        List<Commerce> commerceNearUser = commerceFinder.findAllInsideRange(maxDistanceInMeters, latLngFrom);
        List<Commerce> commercesWithProduct = commercesWithProduct(productToFind, commerceNearUser);
        return commercesWithProduct.stream().map(commerce -> mapTocommerceWithFoundProducts(commerce, latLngFrom, productToFind)).collect(Collectors.toList());
    }

    public List<CommerceWithFoundProducts> productsByname(String productName) {
        return null; //Para esto, desde el front debería dejarte poner una dire tentativa,
        // para no buscar en todos los comercios todos los productos
    }

    private List<Commerce> commercesWithProduct(String productToFind, List<Commerce> commerceNearUser) {
        return commerceNearUser
            .stream()
            .filter(commerce -> commerce.containsProductByName(productToFind))
            .collect(Collectors.toList());
    }

    private CommerceWithFoundProducts mapTocommerceWithFoundProducts(Commerce commerce, LatLng latLngFrom, String productToFind) {
        Optional<Long> distanceInMeters = distanceCalculator.distanceInMetersBetweenTwoLatLng(latLngFrom, commerce.getLatLong());
        CommerceWithFoundProducts commerceWithFoundProducts = new CommerceWithFoundProducts();
        commerceWithFoundProducts.setCommerceId(commerce.getId());
        commerceWithFoundProducts.setCommerceName(commerce.getName());
        commerceWithFoundProducts.setDistanceTo(distanceInMeters.get());
        commerceWithFoundProducts.setProducts(getFoundProductsOf(commerce, productToFind));
        return commerceWithFoundProducts;
    }

    private List<SaleableItemTO> getFoundProductsOf(Commerce commerce, String productToFind) {
        List<SaleableItemTO> foundProducts = new ArrayList<>();
        commerce
            .getSaleableItems()
            .forEach(saleableItem -> mapSaleableItemsToFoundProducts(saleableItem, productToFind, foundProducts));
        return foundProducts;
    }


    private void mapSaleableItemsToFoundProducts(SaleableItem saleableItem, String productToFind,
                                                 List<SaleableItemTO> saleableItemTOS) {
        if (saleableItem.containsInProductName(productToFind)) {
            SaleableItemTO saleableItemTO = converter.convertTo(SaleableItemTO.class, saleableItem);
            saleableItemTOS.add(saleableItemTO);
        }
    }
}
