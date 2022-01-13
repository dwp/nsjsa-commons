package uk.gov.dwp.jsa.adaptors.http.api;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ApiMultiErrorResponseTest {

    public static final ArrayList<ApiError> ERROR = new ArrayList<>();
    public static final ArrayList<Object> SUCCESS = new ArrayList<>();

    @Test
    public void hasDefaultFieldValues() {
        final ApiMultiErrorResponse response = new ApiMultiErrorResponse();
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.getSuccess(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final ApiMultiErrorResponse response = new ApiMultiErrorResponse(SUCCESS);
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.getSuccess(), is(SUCCESS));
    }

    @Test
    public void setError() {
        final ApiMultiErrorResponse response = new ApiMultiErrorResponse();
        response.setError(ERROR);
        assertThat(response.getError(), is(ERROR));
    }

    @Test
    public void setSuccess() {
        final ApiMultiErrorResponse response = new ApiMultiErrorResponse();
        response.setSuccess(SUCCESS);
        assertThat(response.getSuccess(), is(SUCCESS));
    }
}
