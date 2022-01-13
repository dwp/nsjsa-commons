package uk.gov.dwp.jsa.adaptors.exception;

public class CommunicationException extends RuntimeException {
    public CommunicationException(final String message, final Throwable exception) {
        super(message, exception);
    }
}
