package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public enum DeliveryOptionType {
    TAKE_AWAY { //Retirar por local

        @Override
        public String type() {
            return "Retiro por el local";
        }
    },
    DELIVERY; //Envio a domicilio

    public String type() {
        return "Delivery";
    }
}
