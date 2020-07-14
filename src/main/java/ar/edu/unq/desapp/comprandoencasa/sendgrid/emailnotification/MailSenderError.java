package ar.edu.unq.desapp.comprandoencasa.sendgrid.emailnotification;

public class MailSenderError extends RuntimeException {
    public MailSenderError(Throwable cause) {
        super(cause);
    }
}
