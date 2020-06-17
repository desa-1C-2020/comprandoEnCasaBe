package ar.edu.unq.desapp.comprandoencasa.repositories.mem;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;

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

    //Para fake data en memoria. Borrar cuando integre h2
    @Override
    public List<SaleableItem> getBetween(int start, int end) {
        return repo.subList(start, end);
    }
}
