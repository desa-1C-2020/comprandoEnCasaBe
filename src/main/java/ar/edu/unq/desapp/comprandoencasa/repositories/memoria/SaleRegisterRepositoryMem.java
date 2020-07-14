package ar.edu.unq.desapp.comprandoencasa.repositories.memoria;

import ar.com.kfgodel.nary.api.optionals.Optional;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Commerce;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;
import ar.edu.unq.desapp.comprandoencasa.repositories.SaleRegisterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaleRegisterRepositoryMem implements SaleRegisterRepository {

    private final List<SaleRegister> repo;

    public SaleRegisterRepositoryMem() {
        this.repo = new ArrayList();
    }

    @Override
    public void save(SaleRegister saleRegister) {
        repo.add(saleRegister);
    }

    @Override
    public Optional<SaleRegister> findById(Long saleId) {
        java.util.Optional<SaleRegister> first = repo.stream().filter(sale -> sale.getId().equals(saleId)).findFirst();
        return Optional.create(first);
    }

    @Override
    public List<SaleRegister> findAllByCommerce(Commerce commerce) {
        return repo
            .stream()
            .filter(sale -> sale.getCommerce().getId().equals(commerce.getId()))
            .collect(Collectors.toList());
    }
}
