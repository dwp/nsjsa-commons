package uk.gov.dwp.jsa.adaptors;

import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpGetRequestWithEntityTest {

    private static String SOME_URI = "http://www.dav.com";

    @Test
    public void constructotSetsFields() throws URISyntaxException {
        final URI uri = new URI(SOME_URI);
        final HttpGetRequestWithEntity entity = new HttpGetRequestWithEntity(uri);
        assertThat(entity.getURI(), is(uri));
    }

    @Test
    public void getsMethod() throws URISyntaxException {
        final HttpGetRequestWithEntity entity = new HttpGetRequestWithEntity(new URI(SOME_URI));
        assertThat(entity.getMethod(), is(HttpMethod.GET.name()));
    }

}
