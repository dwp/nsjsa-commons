package uk.gov.dwp.jsa.adaptors;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientException;
import uk.gov.dwp.jsa.adaptors.dto.claim.BankDetails;
import uk.gov.dwp.jsa.adaptors.http.api.BankDetailsResponse;

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

public class RestfulGetBankDetailsServiceAdaptorTest {
    private static final String BANK_DETAILS_SERVER = "BANK_DETAILS_SERVER";
    private static final String BANK_DETAILS_VERSION = "1";
    private static final UUID CLAIM_ID = UUID.randomUUID();
    private static final String GET_BY_CLAIM_ID_URL =
            String.format("%s/nsjsa/v%s/claim/%s/bank-details",
                    BANK_DETAILS_SERVER,
                    BANK_DETAILS_VERSION,
                    CLAIM_ID);
    private static final BankDetails BANK_DETAILS = new BankDetails();
    private static final RestClientException REST_CLIENT_EXCEPTION = new RestClientException("");

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulBankDetailsServiceAdaptor adaptor;
    private CompletableFuture<Optional<BankDetails>> bankDetails;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void getsBankDetails() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenIGetBankDetails();
        thenTheBankDetailsAreReturned();
    }

    @Test
    public void getBankDetailsReturnsEmptyIfObjectNotFound() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        when(restfulExecutor.get(eq(GET_BY_CLAIM_ID_URL), eq(BankDetailsResponse.class), any())).thenReturn(Optional.empty());
        whenIGetBankDetails();
        thenTheBankDetailsAreNull();
    }

    @Test(expected = RestClientException.class)
    public void getBankDetailsReturnsNullIfRestClientException() {
        givenAnAdaptor();
        when(restfulExecutor.get(eq(GET_BY_CLAIM_ID_URL), eq(BankDetailsResponse.class), any())).thenThrow(REST_CLIENT_EXCEPTION);
        whenIGetBankDetails();
    }

    private void givenAnAdaptor() {
        adaptor = new RestfulBankDetailsServiceAdaptor(servicesProperties, restfulExecutor);
        when(servicesProperties.getBankDetailsServer()).thenReturn(BANK_DETAILS_SERVER);
        when(servicesProperties.getBankDetailsVersion()).thenReturn(BANK_DETAILS_VERSION);
        when(restfulExecutor.get(eq(GET_BY_CLAIM_ID_URL), eq(BankDetailsResponse.class), any())).thenReturn(Optional.of(BANK_DETAILS));
    }

    private void whenIGetBankDetails() {
        bankDetails = adaptor.getBankDetailsByClaimantId(CLAIM_ID);
    }

    private void thenTheBankDetailsAreNull() throws ExecutionException, InterruptedException {
        assertNotNull("Should not be null", bankDetails);
        Optional<BankDetails> bankDetails = this.bankDetails.get();
        assertNotNull("Should not be null", bankDetails);
        assertFalse("Should be not present", bankDetails.isPresent());
    }

    private void thenTheBankDetailsAreReturned() throws ExecutionException, InterruptedException {
        assertNotNull("Should not be null", bankDetails);
        Optional<BankDetails> bankDetails = this.bankDetails.get();
        assertNotNull("Should not be null", bankDetails);
        assertTrue("Should be present", bankDetails.isPresent());
        BankDetails actual = bankDetails.get();
        assertThat("Is the same", actual, is(BANK_DETAILS));
    }
}
