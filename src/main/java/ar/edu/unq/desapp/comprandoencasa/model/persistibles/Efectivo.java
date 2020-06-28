package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import ar.edu.unq.desapp.comprandoencasa.support.PersistibleSupport;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_method")
public class Efectivo extends PersistibleSupport {
    private String accept;

    public Efectivo(){}

    public Efectivo(String pesos) {
        accept = pesos;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
