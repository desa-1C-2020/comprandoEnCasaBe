package ar.edu.unq.desapp.comprandoencasa.controllers.to.mapper;

import ar.edu.unq.desapp.comprandoencasa.controllers.to.PaymentMethodTO;
import ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct.MapperFunction;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Efectivo;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class PaymentMethodTO2PaymentMethod implements MapperFunction<PaymentMethodTO, PaymentMethod> {
    @Override
    public PaymentMethod apply(PaymentMethodTO paymentMethodTO) {
        if (paymentMethodTO == null) {
            return null;
        }
        if (paymentMethodTO.getType() == "Efectivo") {
            return new Efectivo(paymentMethodTO.getAccept());
        }
        return new Efectivo(paymentMethodTO.getAccept());//Revisar esto
    }
}
