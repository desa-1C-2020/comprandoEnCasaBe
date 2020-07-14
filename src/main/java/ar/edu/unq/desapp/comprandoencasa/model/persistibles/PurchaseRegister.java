package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "purchase_register")
public class PurchaseRegister extends PersistibleSupport {
    @OneToOne
    private final Commerce commerce;
    @OneToOne
    private final ShoppingList shoppingList;
    @OneToMany
    private final List<ShoppingListItem> items;
    private PurchaseStatus pending;

    public PurchaseRegister(Commerce commerce, ShoppingList shoppingList, List<ShoppingListItem> items, PurchaseStatus pending, User user) {
        this.commerce = commerce;
        this.shoppingList = shoppingList;
        this.items = items;
        this.pending = pending;
    }
}