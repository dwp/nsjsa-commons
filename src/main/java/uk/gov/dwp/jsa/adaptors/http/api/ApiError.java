package uk.gov.dwp.jsa.adaptors.http.api;

import java.io.Serializable;

public class ApiError implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;

    private String code;
    private String message;

    private ApiError() {
    }

    public ApiError(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
