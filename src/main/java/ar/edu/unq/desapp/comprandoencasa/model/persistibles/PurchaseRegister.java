package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "purchase_register")
public class PurchaseRegister extends PersistibleSupport {
    @OneToOne
    private Commerce commerce;
    @OneToOne
    private ShoppingList shoppingList;
    @OneToMany
    private List<ShoppingListItem> items;
    private PurchaseStatus purchaseStatus;
    @ManyToOne
    private User user;

    public PurchaseRegister() {
    }

    public PurchaseRegister(Commerce commerce, ShoppingList shoppingList, List<ShoppingListItem> items,
                            PurchaseStatus purchaseStatus, User user) {
        this.commerce = commerce;
        this.shoppingList = shoppingList;
        this.items = items;
        this.purchaseStatus = purchaseStatus;
        this.user = user;
    }

    public Commerce getCommerce() {
        return commerce;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
