package uk.gov.dwp.jsa.adaptors.exception;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommunicationExceptionTest {

    private final static String MESSAGE = "MESSAGE";
    private static final Throwable THROWABLE = new RuntimeException();

    @Test
    public void constructorSetsFields() {
        final CommunicationException exception = new CommunicationException(MESSAGE, THROWABLE);
        assertThat(exception.getMessage(), is(MESSAGE));
        assertThat(exception.getCause(), is(THROWABLE));
    }
}
