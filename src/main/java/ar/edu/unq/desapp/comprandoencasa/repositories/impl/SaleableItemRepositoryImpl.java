package ar.edu.unq.desapp.comprandoencasa.repositories.impl;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleableItem;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleableItemRepository;
import ar.edu.unq.desapp.comprandoencasa.repositories.spring.SaleableItemRepositoryJpa;

import java.util.List;

public class SaleableItemRepositoryImpl implements SaleableItemRepository {

    private SaleableItemRepositoryJpa repoJpa;

    public static SaleableItemRepositoryImpl create(SaleableItemRepositoryJpa repoJpa) {
        SaleableItemRepositoryImpl repo = new SaleableItemRepositoryImpl();
        repo.repoJpa = repoJpa;
        return repo;
    }

    @Override
    public void save(SaleableItem saleableItem) {
        repoJpa.saveAndFlush(saleableItem);
    }

    @Override
    public void delete(SaleableItem removedItem) {
        repoJpa.delete(removedItem);
    }

    @Override
    public List<SaleableItem> getBetween(int start, int end) {
        return null;
    }
}