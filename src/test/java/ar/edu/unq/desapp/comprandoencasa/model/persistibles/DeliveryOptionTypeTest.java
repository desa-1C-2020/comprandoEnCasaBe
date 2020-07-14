package ar.edu.unq.desapp.comprandoencasa.model.persistibles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryOptionType.DELIVERY;
import static ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryOptionType.TAKE_AWAY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryOptionTypeTest {
    @Test
    public void whenParseDeliveryValue_getsDeliveryAsDeliveryOptionType() {
        String type = "DELIVERY";

        DeliveryOptionType deliveryOptionType = DeliveryOptionType.valueOf(type);

        assertThat(deliveryOptionType, is(DELIVERY));
    }

    @Test
    public void whenParseTakeAwayValue_getsTakeAwayAsDeliveryOptionType() {
        String type = "TAKE_AWAY";

        DeliveryOptionType deliveryOptionType = DeliveryOptionType.valueOf(type);

        assertThat(deliveryOptionType, is(TAKE_AWAY));
    }
}