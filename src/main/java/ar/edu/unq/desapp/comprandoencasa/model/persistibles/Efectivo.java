package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public class Efectivo implements PaymentMethod {
    private String accept;

    public Efectivo(String pesos) {
        accept = pesos;
    }

    @Override
    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
