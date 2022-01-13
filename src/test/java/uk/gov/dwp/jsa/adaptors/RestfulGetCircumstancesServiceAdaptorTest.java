package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientException;
import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.Circumstances;
import uk.gov.dwp.jsa.adaptors.http.api.CircumstancesResponse;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulGetCircumstancesServiceAdaptorTest {
    private static final String CIRCUMSTANCES_SERVER = "CIRCUMSTANCES_SERVER";
    private static final String CIRCUMSTANCES_VERSION = "1";
    private static final UUID CLAIM_ID = UUID.randomUUID();

    private static final String GET_BY_CLAIM_ID_URL =
            String.format("%s/nsjsa/v%s/citizen/%s/claim",
                    CIRCUMSTANCES_SERVER,
                    CIRCUMSTANCES_VERSION,
                    CLAIM_ID);
    private static final Circumstances CIRCUMSTANCES = new Circumstances();
    private static final RestClientException REST_CLIENT_EXCEPTION = new RestClientException("");

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulCircumstancesServiceAdaptor adaptor;
    private CompletableFuture<Optional<Circumstances>> circumstances;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void getsCircumstances() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenIGetCircumstances();
        thenTheCircumstancesAreReturned();
    }

    @Test
    public void getCircumstancesReturnsEmptyIfObjectNotFound() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        when(restfulExecutor.get(eq(GET_BY_CLAIM_ID_URL), eq(CircumstancesResponse.class), any()
        )).thenReturn(Optional.empty());
        whenIGetCircumstances();
        thenTheCircumstancesAreNull();
    }

    @Test(expected = RestClientException.class)
    public void getCircumstancesReturnsNullIfRestClientException() {
        givenAnAdaptor();
        when(restfulExecutor.get(eq(GET_BY_CLAIM_ID_URL), eq(CircumstancesResponse.class), any()))
                .thenThrow(REST_CLIENT_EXCEPTION);
        whenIGetCircumstances();
    }

    private void givenAnAdaptor() {
        adaptor = new RestfulCircumstancesServiceAdaptor(servicesProperties, restfulExecutor);
        when(servicesProperties.getCircumstancesServer()).thenReturn(CIRCUMSTANCES_SERVER);
        when(servicesProperties.getCircumstancesVersion()).thenReturn(CIRCUMSTANCES_VERSION);
        when(restfulExecutor.get(eq(GET_BY_CLAIM_ID_URL), eq(CircumstancesResponse.class), any()))
                .thenReturn(Optional.of(CIRCUMSTANCES));
    }

    private void whenIGetCircumstances() {
        circumstances = adaptor.getCircumstancesByClaimantId(CLAIM_ID);
    }

    private void thenTheCircumstancesAreNull() throws ExecutionException, InterruptedException {
        assertNotNull("Should not be null", circumstances);
        Optional<Circumstances> circumstances = this.circumstances.get();
        assertNotNull("Should not be null", circumstances);
        assertFalse("Should not be present", circumstances.isPresent());
    }

    private void thenTheCircumstancesAreReturned() throws ExecutionException, InterruptedException {
        assertNotNull("Should not be null", circumstances);
        Optional<Circumstances> circumstances = this.circumstances.get();
        assertNotNull("Should not be null", circumstances);
        assertTrue("Should be present", circumstances.isPresent());
        Circumstances actual = circumstances.get();
        assertThat(actual, is(CIRCUMSTANCES));
    }
}
