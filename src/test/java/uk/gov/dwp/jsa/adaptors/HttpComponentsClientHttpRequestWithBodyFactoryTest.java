package uk.gov.dwp.jsa.adaptors;

import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpComponentsClientHttpRequestWithBodyFactoryTest {

    private static final String SOME_URI = "http://www.dav.com";

    private HttpComponentsClientHttpRequestWithBodyFactory factory;
    private HttpUriRequest httpUriRequest;

    @Test
    public void createHttpUriRequest() throws URISyntaxException {
        givenAFactory();
        whenICallCreateWith(HttpMethod.POST);
        thenTheHttpUriRequestIsCreated();
    }

    @Test
    public void createsHttpGetRequestWithEntity() throws URISyntaxException {
        givenAFactory();
        whenICallCreateWith(HttpMethod.GET);
        thenTheHttpGetRequestWithEntityIsCreated();
    }

    private void givenAFactory() {
        factory = new HttpComponentsClientHttpRequestWithBodyFactory();
    }

    private void whenICallCreateWith(HttpMethod method) throws URISyntaxException {
        final URI uri = new URI(SOME_URI);
        httpUriRequest = factory.createHttpUriRequest(method, uri);
    }

    private void thenTheHttpUriRequestIsCreated() throws URISyntaxException {
        final URI uri = new URI(SOME_URI);
        assertThat(httpUriRequest, instanceOf(HttpUriRequest.class));
        assertThat(httpUriRequest.getURI(), is(uri));
    }

    private void thenTheHttpGetRequestWithEntityIsCreated() throws URISyntaxException {
        final URI uri = new URI(SOME_URI);
        assertThat(httpUriRequest, instanceOf(HttpGetRequestWithEntity.class));
        assertThat(httpUriRequest.getURI(), is(uri));
    }


}