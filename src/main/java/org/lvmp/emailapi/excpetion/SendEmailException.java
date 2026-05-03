package org.lvmp.emailapi.excpetion;

public class SendEmailException extends RuntimeException {
    public SendEmailException() {
        super();
    }

    public SendEmailException(String message) {
        super(message);
    }

    public SendEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendEmailException(Throwable cause) {
        super(cause);
    }
}
