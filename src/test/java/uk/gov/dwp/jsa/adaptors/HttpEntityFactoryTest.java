package uk.gov.dwp.jsa.adaptors;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HttpEntityFactoryTest {

    public static final Object PAYLOAD = new Object();

    private HttpEntityFactory factory;
    private HttpEntity httpEntity;

    @Test
    public void createsWithJsonHeaders() {
        givenAFactory();
        whenICallCreateHeaders();
        thenTheHeadersAreSet();

    }

    @Test
    public void createWithJsonHeadersAndPayload() {
        givenAFactory();
        whenICallCreateHeadersAndPayload();
        thenTheHeadersAreSet();
        thenThePayloadIsSet();
    }

    private void givenAFactory() {
        factory = new HttpEntityFactory();
    }

    private void whenICallCreateHeaders() {
        httpEntity = factory.createWithJsonHeaders();
    }

    private void whenICallCreateHeadersAndPayload() {
        httpEntity = factory.createWithJsonHeaders(PAYLOAD);
    }

    private void thenThePayloadIsSet() {
        assertThat(httpEntity.getBody(), is(PAYLOAD));
    }

    private void thenTheHeadersAreSet() {
        assertThat(httpEntity.getHeaders().getAccept(), hasItem(MediaType.APPLICATION_JSON));
        assertThat(httpEntity.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    }

}
