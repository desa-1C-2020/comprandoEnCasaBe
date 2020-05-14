package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;

public interface SaleableItemRepository {
    void save(SaleableItem saleableItem);

    void delete(SaleableItem removedItem);
}
