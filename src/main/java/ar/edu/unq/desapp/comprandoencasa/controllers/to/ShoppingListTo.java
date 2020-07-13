package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShoppingListTo {
    public static final String itemByCommerceTo_FIELD = "itemByCommerceTo";
    private List<ItemByCommerceTo> itemByCommerceTo;
    private Date creationDateTime;
    private BigDecimal total;

    public List<ItemByCommerceTo> getItemByCommerceTo() {
        return itemByCommerceTo;
    }

    public void setItemByCommerceTo(List<ItemByCommerceTo> itemByCommerceTo) {
        this.itemByCommerceTo = itemByCommerceTo;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
