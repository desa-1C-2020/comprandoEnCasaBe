package ar.edu.unq.desapp.comprandoencasa.controllers.to;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleStatus;

public class SaleUpdateTO {
    private SaleStatus newSaleStatus;
    private Long saleId;

    public SaleStatus getNewSaleStatus() {
        return newSaleStatus;
    }

    public void setNewSaleStatus(SaleStatus newSaleStatus) {
        this.newSaleStatus = newSaleStatus;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
