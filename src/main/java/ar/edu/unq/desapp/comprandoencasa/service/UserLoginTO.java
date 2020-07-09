package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;

public class UserLoginTO {
    private User user;
    private Object rol;
    private Boolean missingAddress;

    public UserLoginTO(User user, Object rol, Boolean missingAddress) {
        this.user = user;
        this.rol = rol;
        this.missingAddress = missingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getRol() {
        return rol;
    }

    public void setRol(Object rol) {
        this.rol = rol;
    }

    public Boolean getMissingAddress() {
        return missingAddress;
    }

    public void setMissingAddress(Boolean missingAddress) {
        this.missingAddress = missingAddress;
    }
}
