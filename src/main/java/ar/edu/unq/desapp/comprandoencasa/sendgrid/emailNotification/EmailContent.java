package ar.edu.unq.desapp.comprandoencasa.sendgrid.emailNotification;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Personalization;

import java.util.HashMap;

public class EmailContent {
    private static String subject;
    private static Content content;
    private Email to;
    private Email from;
    private HashMap<String, String> mailContentParameters;

    public static EmailContent create(String from, String to, String subject, String content) {
        EmailContent emailContent = new EmailContent();
        emailContent.to = new Email(to);
        emailContent.from = new Email(from);
        EmailContent.subject = subject;
        EmailContent.content = new Content("text/plain", content);
        emailContent.mailContentParameters = new HashMap<>();
        return emailContent;
    }

    public Mail toSendgridEmail() {
        Mail mail = new Mail(from, subject, to, content);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mailContentParameters.forEach(personalization::addSubstitution);

        mail.addPersonalization(personalization);
        return mail;
    }
}
