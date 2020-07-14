package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "sale_register")
public class SaleRegister extends PersistibleSupport {
    @OneToOne
    private Commerce commerce;
    @OneToOne
    private ShoppingList shoppingList;
    @OneToMany
    private List<ShoppingListItem> items;
    private SaleStatus saleStatus;
    @ManyToOne
    private User user;

    public SaleRegister() {
        //Used for hibernate
    }

    public static SaleRegister create(Commerce commerce, ShoppingList shoppingList, List<ShoppingListItem> items,
                                      SaleStatus saleStatus, User user) {
        SaleRegister saleRegister = new SaleRegister();
        saleRegister.commerce = commerce;
        saleRegister.shoppingList = shoppingList;
        saleRegister.items = items;
        saleRegister.setSaleStatus(saleStatus);
        saleRegister.user = user;
        return saleRegister;
    }

    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItem> items) {
        this.items = items;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
        saleStatus.affectStock(commerce, items);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String showItems() {
        List<String> productsText = items
            .stream()
            .map(item -> "Producto: " + item.getProduct().getName() + ". Cantidad: " + item.getQuantity())
            .collect(Collectors.toList());
        return String.join("\n", productsText);
    }

    public BigDecimal total() {
        return items.stream().map(ShoppingListItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }
}
