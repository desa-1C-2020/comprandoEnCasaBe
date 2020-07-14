package ar.edu.unq.desapp.comprandoencasa.sendgrid;

import ar.edu.unq.desapp.comprandoencasa.configurations.SendGridProperties;
import ar.edu.unq.desapp.comprandoencasa.sendgrid.emailNotification.EmailContent;
import ar.edu.unq.desapp.comprandoencasa.sendgrid.emailNotification.MailSenderError;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import java.io.IOException;

public class SendGridClient {
    private SendGrid sendgrid;

    private SendGridProperties configuration;

    public static SendGridClient create(SendGrid sendgrid, SendGridProperties configuration) {
        SendGridClient sendGridClient = new SendGridClient();
        sendGridClient.sendgrid = sendgrid;
        sendGridClient.configuration = configuration;
        return sendGridClient;
    }

    public void sendEmail(EmailContent email) {
        Mail mail = email.toSendgridEmail();
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendgrid.api(request);
        } catch (IOException exception) {
            throw new MailSenderError(exception);
        }
    }

    public String getFromEmail() {
        return configuration.getEmail();
    }
}
