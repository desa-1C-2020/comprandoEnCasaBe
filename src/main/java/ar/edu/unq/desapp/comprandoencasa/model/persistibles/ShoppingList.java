package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.UUID.randomUUID;

public class ShoppingList {
    public static final String itemsByCommerce_FIELD = "itemsByCommerce";
    private UserBasic userBasic;
    private List<ItemsByCommerce> itemsByCommerce;
    private Date creationDateTime;
    private BigDecimal total;
    private String id;

    public ShoppingList(UserBasic userBasic, List<ItemsByCommerce> itemsByCommerce, BigDecimal total, Date creationDateTime) {
        this.userBasic = userBasic;
        this.itemsByCommerce = itemsByCommerce;
        this.total = total;
        this.creationDateTime = creationDateTime;
        this.id = randomUUID().toString();
    }

    public UserBasic getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(UserBasic userBasic) {
        this.userBasic = userBasic;
    }

    public List<ItemsByCommerce> getItemsByCommerce() {
        return itemsByCommerce;
    }

    public void setItemsByCommerce(List<ItemsByCommerce> itemsByCommerce) {
        this.itemsByCommerce = itemsByCommerce;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean itIsFrom(UserBasic userBasic) {
        return userBasic.same(userBasic);
    }

    public String getUserId() {
        return userBasic.getUid();
    }

    public boolean sameId(String shoppingListToDeleteId) {
        return id.equals(shoppingListToDeleteId);
    }
}
