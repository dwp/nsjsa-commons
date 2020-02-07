package uk.gov.dwp.jsa.adaptors.exception;

import uk.gov.dwp.jsa.adaptors.http.api.ApiError;

import java.util.List;

public class PushJsapsException extends RuntimeException {

    public List<ApiError> getApiErrors() {
        return apiErrors;
    }

    private List<ApiError> apiErrors;

    public PushJsapsException(final List<ApiError> apiErrors) {
       this.apiErrors = apiErrors;
    }

    public PushJsapsException() {
    }


}
