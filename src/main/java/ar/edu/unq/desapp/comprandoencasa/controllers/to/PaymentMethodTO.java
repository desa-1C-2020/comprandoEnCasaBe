package ar.edu.unq.desapp.comprandoencasa.controllers.to;

public class PaymentMethodTO {
    private String type;
    private String accept;

    public PaymentMethodTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
