package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public enum DeliveryOptionType {
    TAKE_AWAY { //Retirar por local

        @Override
        public String toShow() {
            return "Retiro por el local";
        }
    },
    DELIVERY; //Envio a domicilio

    public String toShow() {
        return "Delivery";
    }
}
