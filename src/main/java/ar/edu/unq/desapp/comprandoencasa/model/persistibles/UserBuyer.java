package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

public class UserBuyer {
    private UserBasic userBasic;

    public UserBuyer(UserBasic userBasic) {
        this.userBasic = userBasic;
    }

    public UserBasic getUserBasic() {
        return userBasic;
    }

    public boolean sameUser(UserBasic userBasic) {
        return this.userBasic.same(userBasic);
    }
}
