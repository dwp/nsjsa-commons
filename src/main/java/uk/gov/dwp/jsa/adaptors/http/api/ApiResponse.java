package uk.gov.dwp.jsa.adaptors.http.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class ApiResponse<T> {

    private ApiError error;
    private List<ApiSuccess<T>> success;

    public ApiResponse() {
    }

    public ApiResponse(final List<ApiSuccess<T>> success) {
        this.success = success;
    }

    public ApiResponse(final ApiError error) {
        this.error = error;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(final ApiError error) {
        this.error = error;
    }

    public List<ApiSuccess<T>> getSuccess() {
        return success;
    }

    public void setSuccess(final List<ApiSuccess<T>> success) {
        this.success = success;
    }

}
