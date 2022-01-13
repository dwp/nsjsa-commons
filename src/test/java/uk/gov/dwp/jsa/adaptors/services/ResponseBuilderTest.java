package uk.gov.dwp.jsa.adaptors.services;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.dwp.jsa.adaptors.http.api.ApiError;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ApiSuccess;

import java.net.URI;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResponseBuilderTest {

    public static final ApiSuccess<Object> API_SUCCESS = new ApiSuccess<>();
    public static final HttpStatus HTTP_STATUS = HttpStatus.ACCEPTED;
    public static final Object DATA = new Object();
    public static final URI PATH = URI.create("http://www.dav.com");
    public static final ApiError API_ERROR = new ApiError(null, null);
    public static final String MESSAGE = "MESSAGE";
    public static final String ERROR = "ERROR";

    @Test
    public void withStatus() {
        final ResponseBuilder<Object> responseBuilder = new ResponseBuilder<>();
        ResponseEntity<ApiResponse<Object>> response =
                responseBuilder
                        .withStatus(HTTP_STATUS)
                        .build();
        assertThat(response.getStatusCode(), is(HTTP_STATUS));
    }

    @Test
    public void withApiErrorObject() {
        final ResponseBuilder<Object> responseBuilder = new ResponseBuilder<>();
        ResponseEntity<ApiResponse<Object>> response =
                responseBuilder
                        .withStatus(HTTP_STATUS)
                        .withApiError(API_ERROR)
                        .build();
        assertThat(response.getBody().getError(), is(API_ERROR));
    }

    @Test
    public void withApiErrorAndMessage() {
        final ResponseBuilder<Object> responseBuilder = new ResponseBuilder<>();
        ResponseEntity<ApiResponse<Object>> response =
                responseBuilder
                        .withStatus(HTTP_STATUS)
                        .withApiError(ERROR, MESSAGE)
                        .build();
        assertThat(response.getBody().getError().getCode(), is(ERROR));
        assertThat(response.getBody().getError().getMessage(), is(MESSAGE));
    }

    @Test
    public void withSuccessDataWorksWithAPISuccessObject() {
        final ResponseBuilder<Object> responseBuilder = new ResponseBuilder<>();
        ResponseEntity<ApiResponse<Object>> response =
                responseBuilder
                        .withStatus(HTTP_STATUS)
                        .withSuccessData(API_SUCCESS).build();
        assertThat(response.getBody().getSuccess(), hasItem(API_SUCCESS));
    }

    @Test
    public void withSuccessDataWorksWithAPISuccessList() {
        final ResponseBuilder<Object> responseBuilder = new ResponseBuilder<>();
        ResponseEntity<ApiResponse<Object>> response =
                responseBuilder
                        .withStatus(HTTP_STATUS)
                        .withSuccessData(Arrays.asList(API_SUCCESS)).build();
        assertThat(response.getBody().getSuccess(), hasItem(API_SUCCESS));
    }

    @Test
    public void withSuccessDataWorksWithAPISuccessPathAndData() {
        final ResponseBuilder<Object> responseBuilder = new ResponseBuilder<>();
        ResponseEntity<ApiResponse<Object>> response =
                responseBuilder
                        .withStatus(HTTP_STATUS)
                        .withSuccessData(PATH, DATA).build();
        assertThat(response.getBody().getSuccess().get(0).getData(), is(DATA));
        assertThat(response.getBody().getSuccess().get(0).getPath(), is(PATH));
    }


}
