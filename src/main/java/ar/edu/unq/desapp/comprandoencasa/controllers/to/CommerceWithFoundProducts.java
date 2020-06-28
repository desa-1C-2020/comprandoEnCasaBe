package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import java.util.List;

public class CommerceWithFoundProducts {
    private Long commerceId;
    private String commerceName;
    private Long distance;
    private List<SaleableItemTO> saleableItems;

    public void setDistanceTo(Long distance) {
        this.distance = distance;
    }

    public void setProducts(List<SaleableItemTO> saleableItems) {
        this.saleableItems = saleableItems;
    }

    public Long getCommerceId() {
        return commerceId;
    }

    public void setCommerceId(Long id) {
        this.commerceId = id;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String name) {
        this.commerceName = name;
    }

    public Long getDistance() {
        return distance;
    }

    public List<SaleableItemTO> getSaleableItems() {
        return saleableItems;
    }
}
