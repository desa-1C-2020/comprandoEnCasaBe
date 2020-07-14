package ar.edu.unq.desapp.comprandoencasa.service;

import ar.edu.unq.desapp.comprandoencasa.model.persistibles.DeliveryOption;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.PaymentMethod;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.Purchase;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.SaleRegister;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.User;
import ar.edu.unq.desapp.comprandoencasa.model.persistibles.UserSeller;
import ar.edu.unq.desapp.comprandoencasa.sendgrid.SendGridClient;
import ar.edu.unq.desapp.comprandoencasa.sendgrid.emailNotification.EmailContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmailSender {
    public static Logger logger = LoggerFactory.getLogger(EmailSender.class);
    private SendGridClient sendGridClient;
    private UserFinder userFinder;

    public EmailSender(SendGridClient sendGridClient, UserFinder userFinder) {
        this.sendGridClient = sendGridClient;
        this.userFinder = userFinder;
    }

    public void sendEmailToSellers(List<SaleRegister> saleRegisters, Purchase purchase) {
        saleRegisters.forEach(saleRegister -> {
            UserSeller userSeller = userFinder.findByCommerce(saleRegister.getCommerce());
            sendEmailToSeller(saleRegister, userSeller, purchase);
        });
    }

    private void sendEmailToSeller(SaleRegister saleRegister, UserSeller userSeller, Purchase purchase) {
        DeliveryOption deliveryOption = purchase.getDeliveryOption();
        PaymentMethod paymentMethod = purchase.getPaymentMethod();
        String subject = "Realizaron una compra!";
        String mailContent = "Con el siguiente detalle:\n";
        String content = mailContent + "Medio de entrega: " + deliveryOption.getDeliveryOptionType().toShow()
            + ". Fecha aproximada: " + deliveryOption.getSuggestedDay() + "\n";
        String withPayment = content + "Método de pago: " + paymentMethod + "\n";
        String detail = withPayment + "Productos: " + saleRegister.showItems();
        String withTotal = detail + "Total: " + saleRegister.total();
        EmailContent email = EmailContent
            .create(sendGridClient.getFromEmail(), userSeller.getUser().getEmail(), subject, withTotal);

        sendGridClient.sendEmail(email);
    }

    public void sendEmailToBuyer(User user, Purchase purchase) {
        DeliveryOption deliveryOption = purchase.getDeliveryOption();
        PaymentMethod paymentMethod = purchase.getPaymentMethod();
        String subject = "Realizaste una compra!";
        String mailContent = "Este es tu detalle:\n";
        String content = mailContent + "Medio de entrega: " + deliveryOption.getDeliveryOptionType().toShow()
            + ". Fecha aproximada: " + deliveryOption.getSuggestedDay() + "\n";
        String withPayment = content + "Método de pago: " + paymentMethod + "\n";
        String detail = withPayment + "Productos: " + purchase.getShoppingList().show();
        String withTotal = detail + "Total: " + purchase.getTotal();
        EmailContent email = EmailContent
            .create(sendGridClient.getFromEmail(), user.getEmail(), subject, withTotal);

        sendGridClient.sendEmail(email);
    }
}
