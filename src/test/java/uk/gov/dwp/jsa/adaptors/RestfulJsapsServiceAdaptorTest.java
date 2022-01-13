package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import uk.gov.dwp.jsa.adaptors.dto.jsaps.JsapsRequest;
import uk.gov.dwp.jsa.adaptors.http.api.JsapsPushResponse;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulJsapsServiceAdaptorTest {
    public static final String JSAPS_SERVER = "JSAPS_SERVER";
    public static final String JSAPS_VERSION = "V1";
    public static final UUID CLAIMANT_ID = UUID.randomUUID();
    private static final String JSAPS_ADAPTER_PUSH_URL = String.format("%s/jsaps/v%s/claim/%s", JSAPS_SERVER, JSAPS_VERSION, CLAIMANT_ID.toString());
    public static final String RESPONSE_TEXT = "RESPONSE_TEXT";

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;
    @Mock
    private JsapsRequest jsapsRequest;

    private RestfulJsapsServiceAdaptor adaptor;
    private Optional<String> result;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void pushToJsaps() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenIPushToJsaps();
        thenTheRequestIsPushedToJsaps();
    }

    private void givenAnAdaptor() {
        adaptor = new RestfulJsapsServiceAdaptor(servicesProperties, restfulExecutor);
        when(servicesProperties.getJsapsServer()).thenReturn(JSAPS_SERVER);
        when(servicesProperties.getJsapsVersion()).thenReturn(JSAPS_VERSION);
        when(restfulExecutor.updateMultiError(
                eq(JSAPS_ADAPTER_PUSH_URL),
                eq(jsapsRequest),
                eq(JsapsPushResponse.class),
                any())).thenReturn(Optional.of(RESPONSE_TEXT));
    }

    private void whenIPushToJsaps() throws InterruptedException, ExecutionException {
        result = adaptor.pushToJsaps(CLAIMANT_ID, jsapsRequest).get();
    }

    private void thenTheRequestIsPushedToJsaps() {
        assertThat(result.get(), is(RESPONSE_TEXT));
    }

}
