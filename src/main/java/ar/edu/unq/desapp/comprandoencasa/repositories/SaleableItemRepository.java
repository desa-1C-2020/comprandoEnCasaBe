package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;

import java.util.List;

public interface SaleableItemRepository {
    void save(SaleableItem saleableItem);

    void delete(SaleableItem removedItem);

    List<SaleableItem> getBetween(int start, int end);
}
