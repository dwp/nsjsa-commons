package uk.gov.dwp.jsa.adaptors.exception;

import org.junit.Test;
import uk.gov.dwp.jsa.adaptors.http.api.ApiError;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PushJsapsExceptionTest {

    public static final ArrayList<ApiError> API_ERRORS = new ArrayList<>();

    @Test
    public void hasDefaultFieldValues() {
        final PushJsapsException exception = new PushJsapsException();
        assertThat(exception.getApiErrors(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final PushJsapsException exception = new PushJsapsException(API_ERRORS);
        assertThat(exception.getApiErrors(), is(API_ERRORS));
    }

}
