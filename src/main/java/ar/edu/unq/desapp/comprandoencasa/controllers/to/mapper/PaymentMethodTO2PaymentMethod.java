package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.PaymentMethodTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class PaymentMethodTO2PaymentMethod implements MapperFunction<PaymentMethodTO, Efectivo> {
    @Override
    public Efectivo apply(PaymentMethodTO paymentMethodTO) {
        if (paymentMethodTO == null) {
            return null;
        }
        if (paymentMethodTO.getType().equals("Efectivo")) {
            return new Efectivo(paymentMethodTO.getAccept());
        }
        return new Efectivo(paymentMethodTO.getAccept());//Revisar esto
    }
}
