package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientException;
import uk.gov.dwp.jsa.adaptors.dto.claim.Claimant;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimantResponse;

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

public class RestfulGetClaimantServiceAdaptorTest {
    private static final String CLAIMANT_SERVER = "CLAIMANT_SERVER";
    private static final String CLAIMANT_VERSION = "1";
    private static final UUID CLAIM_ID = UUID.randomUUID();
    private static final String GET_BY_CLAIMANT_ID_URL = String.format("%s/nsjsa/v%s/citizen/%s",
            CLAIMANT_SERVER,
            CLAIMANT_VERSION,
            CLAIM_ID);
    private static final Claimant CLAIMANT = new Claimant();
    private static final RestClientException REST_CLIENT_EXCEPTION = new RestClientException("");

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulClaimantServiceAdaptor adaptor;
    private CompletableFuture<Optional<Claimant>> claimant;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void getsClaimants() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenIGetClaimants();
        thenTheClaimantsAreReturned();
    }

    @Test
    public void getClaimantsReturnsMullIfObjectNotFound() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        when(restfulExecutor.get(eq(GET_BY_CLAIMANT_ID_URL), eq(ClaimantResponse.class), any())).thenReturn(Optional.empty());
        whenIGetClaimants();
        thenTheClaimantsAreNull();
    }

    @Test(expected = RestClientException.class)
    public void getClaimantsReturnsEmptyIfRestClientException() {
        givenAnAdaptor();
        when(restfulExecutor.get(eq(GET_BY_CLAIMANT_ID_URL), eq(ClaimantResponse.class), any())).thenThrow(REST_CLIENT_EXCEPTION);
        whenIGetClaimants();
    }

    private void givenAnAdaptor() {
        adaptor = new RestfulClaimantServiceAdaptor(servicesProperties, restfulExecutor);
        when(servicesProperties.getClaimantServer()).thenReturn(CLAIMANT_SERVER);
        when(servicesProperties.getClaimantVersion()).thenReturn(CLAIMANT_VERSION);
        when(restfulExecutor.get(eq(GET_BY_CLAIMANT_ID_URL), eq(ClaimantResponse.class), any())).thenReturn(Optional.of(CLAIMANT));
    }

    private void whenIGetClaimants() {
        claimant = adaptor.getClaimant(CLAIM_ID);
    }

    private void thenTheClaimantsAreNull() throws ExecutionException, InterruptedException {
        assertNotNull("Should not be null", claimant);
        Optional<Claimant> claimant = this.claimant.get();
        assertNotNull("Should not be null", claimant);
        assertFalse("Should not be present", claimant.isPresent());
    }

    private void thenTheClaimantsAreReturned() throws ExecutionException, InterruptedException {
        assertNotNull("Should not be null", claimant);
        Optional<Claimant> claimant = this.claimant.get();
        assertNotNull("Should not be null", claimant);
        assertTrue("Should be present", claimant.isPresent());
        Claimant actual = claimant.get();
        assertThat(actual, is(CLAIMANT));
    }

}
