package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingListTO {
    public static final String itemByCommerceTo_FIELD = "itemByCommerceTo";
    private List<ItemByCommerceTO> itemByCommerceTo;
    private BigDecimal total;

    public List<ItemByCommerceTO> getItemByCommerceTo() {
        return itemByCommerceTo;
    }

    public void setItemByCommerceTo(List<ItemByCommerceTO> itemByCommerceTo) {
        this.itemByCommerceTo = itemByCommerceTo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
