package ar.edu.unq.desapp.comprandoencasa.sendgrid.emailNotification;

public class MailSenderError extends RuntimeException {
    public MailSenderError(Throwable cause) {
        super(cause);
    }
}
