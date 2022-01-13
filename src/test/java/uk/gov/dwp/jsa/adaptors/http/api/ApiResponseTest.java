package uk.gov.dwp.jsa.adaptors.http.api;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ApiResponseTest {
    public static final ApiError ERROR = new ApiError(null, null);
    public static final ArrayList<Object> SUCCESS = new ArrayList<>();

    @Test
    public void hasDefaultFieldValues() {
        final ApiResponse response = new ApiResponse();
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.getSuccess(), is(nullValue()));
    }

    @Test
    public void constuctorSetsSuccess() {
        final ApiResponse response = new ApiResponse(SUCCESS);
        assertThat(response.getError(), is(nullValue()));
        assertThat(response.getSuccess(), is(SUCCESS));
    }

    @Test
    public void constuctorSetsError() {
        final ApiResponse response = new ApiResponse(ERROR);
        assertThat(response.getError(), is(ERROR));
        assertThat(response.getSuccess(), is(nullValue()));
    }

    @Test
    public void setError() {
        final ApiResponse response = new ApiResponse();
        response.setError(ERROR);
        assertThat(response.getError(), is(ERROR));
    }

    @Test
    public void setSuccess() {
        final ApiResponse response = new ApiResponse();
        response.setSuccess(SUCCESS);
        assertThat(response.getSuccess(), is(SUCCESS));
    }
}
