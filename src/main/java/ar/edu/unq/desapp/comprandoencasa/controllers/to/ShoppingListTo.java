package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShoppingListTo {
    public static final String itemByCommerceTo_FIELD = "itemByCommerceTo";
    private String userId;
    private List<ItemByCommerceTo> itemByCommerceTo;
    private BigDecimal total;

    public List<ItemByCommerceTo> getItemByCommerceTo() {
        return itemByCommerceTo;
    }

    public void setItemByCommerceTo(List<ItemByCommerceTo> itemByCommerceTo) {
        this.itemByCommerceTo = itemByCommerceTo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
