package uk.gov.dwp.jsa.adaptors.http.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ApiMultiErrorResponse<T> {

    private List<ApiError> error;
    private List<ApiSuccess<T>> success;

    public ApiMultiErrorResponse() {
    }

    public ApiMultiErrorResponse(final List<ApiSuccess<T>> success) {
        this.success = success;
    }

    public List<ApiError> getError() {
        return error;
    }

    public void setError(final List<ApiError> error) {
        this.error = error;
    }

    public List<ApiSuccess<T>> getSuccess() {
        return success;
    }

    public void setSuccess(final List<ApiSuccess<T>> success) {
        this.success = success;
    }

}

