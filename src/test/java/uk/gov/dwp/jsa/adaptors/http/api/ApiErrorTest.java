package uk.gov.dwp.jsa.adaptors.http.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApiErrorTest {

    public static final String CODE = "CODE";
    public static final String MESSAGE = "MESSAGE";

    @Test
    public void constuctorSetsFieldValues() {
        final ApiError apiError = new ApiError(CODE, MESSAGE);
        assertThat(apiError.getCode(), is(CODE));
        assertThat(apiError.getMessage(), is(MESSAGE));
    }

    @Test
    public void setCode() {
        final ApiError apiError = new ApiError(null, null);
        apiError.setCode(CODE);
        assertThat(apiError.getCode(), is(CODE));
    }

    @Test
    public void setMessage() {
        final ApiError apiError = new ApiError(null, null);
        apiError.setMessage(MESSAGE);
        assertThat(apiError.getMessage(), is(MESSAGE));
    }
}