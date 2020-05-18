package ar.edu.unq.desapp.comprandoencasa.repositories;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;

import java.util.ArrayList;
import java.util.List;

public class SaleableItemRepositoryMem implements SaleableItemRepository {
    List<SaleableItem> repo;

    public SaleableItemRepositoryMem() {
        repo = new ArrayList<>();
    }

    @Override
    public void save(SaleableItem saleableItem) {
        repo.add(saleableItem);
    }

    @Override
    public void delete(SaleableItem removedItem) {
        repo.remove(removedItem);
    }
}
