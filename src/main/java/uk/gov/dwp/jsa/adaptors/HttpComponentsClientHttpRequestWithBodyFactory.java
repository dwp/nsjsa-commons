package uk.gov.dwp.jsa.adaptors;

import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

public class HttpComponentsClientHttpRequestWithBodyFactory extends HttpComponentsClientHttpRequestFactory {
    @Override
    protected HttpUriRequest createHttpUriRequest(final HttpMethod httpMethod, final URI uri) {
        if (httpMethod == HttpMethod.GET) {
            return new HttpGetRequestWithEntity(uri);
        }
        return super.createHttpUriRequest(httpMethod, uri);
    }
}
