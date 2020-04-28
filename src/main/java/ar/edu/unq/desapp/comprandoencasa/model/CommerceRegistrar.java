package ar.edu.unq.desapp.comprandoencasa.model;

public class CommerceRegistrar {

    public static CommerceRegistrar create() {
        CommerceRegistrar commerceRegistrar = new CommerceRegistrar();
        return commerceRegistrar;
    }

    public void register(User seller, Commerce commerce) {
        if (!canAddCommerce(seller)) {
            throw new RuntimeException("No puede agregar el comercio por no ser vendedor.");
        }
        seller.addCommerce(commerce);
    }

    private boolean canAddCommerce(User seller) {
        return seller.isSeller();
    }

    public boolean hasCommerceRegistered(User seller) {
        return !seller.getCommerce().isAbsent();
    }
}
