package ar.edu.unq.desapp.comprandoencasa.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.UserRol.BUYER;
import static ar.edu.unq.desapp.comprandoencasa.model.UserRol.SELLER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UserRolTest {

    @Test
    public void forTheSellerRolWhenSendIsSellerMessage_thenRespondsTrue() {
        assertTrue(SELLER.isSeller());
    }

    @Test
    public void forTheSellerRolWhenSendIsBuyerMessage_thenRespondsFalse() {
        assertFalse(SELLER.isBuyer());
    }

    @Test
    public void forTheBuyerRolWhenSendIsSellerMessage_thenRespondsFalse() {
        assertFalse(BUYER.isSeller());
    }

    @Test
    public void forTheBuyerRolWhenSendIsBuyerMessage_thenRespondsTrue() {
        assertTrue(BUYER.isBuyer());
    }
}