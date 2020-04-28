package ar.edu.unq.desapp.comprandoencasa.model;

public enum UserRol {
    BUYER {
        public boolean isSeller() {
            return false;
        }

        public boolean isBuyer() {
            return true;
        }
    },

    SELLER {
        public boolean isSeller() {
            return true;
        }

        public boolean isBuyer() {
            return false;
        }
    };

    abstract public boolean isSeller();
    abstract public boolean isBuyer();
}