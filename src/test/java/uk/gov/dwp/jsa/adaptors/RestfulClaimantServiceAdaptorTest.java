package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.gov.dwp.jsa.adaptors.dto.Nino;
import uk.gov.dwp.jsa.adaptors.dto.claim.Claimant;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimantResponse;
import uk.gov.dwp.jsa.adaptors.http.api.NextBookingResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulClaimantServiceAdaptorTest {

    private static final UUID CLAIMANT_ID = UUID.randomUUID();
    private static final String CLAIMANT_SERVICE_VERSION = "1";
    private static final String CLAIMANT_SERVER = "http:///hello";
    private static final String GET_NEXT_BOOKING_URL = String.format("%s/nsjsa/v%s/claim/status/to-book/", CLAIMANT_SERVER, CLAIMANT_SERVICE_VERSION);
    private static final String NINO = "NINO";
    private static final Claimant CLAIMANT = new Claimant();
    private static final String GET_BY_NINO_URL = String.format("%s/nsjsa/v%s/citizen/nino", CLAIMANT_SERVER, CLAIMANT_SERVICE_VERSION);

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulClaimantServiceAdaptor adaptor;
    private Optional<String> getNextBookingResponse;
    private Optional<List<Claimant>> claimant;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void getsClaimantByNino() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenIGetClaimantByNino();
        thenTheClaimantIsReturned();
    }

    @Test
    public void getsNextBookingAppointment() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenGetNextBooking();
        thenTheNextBookingIsReturned();
    }

    private void givenAnAdaptor() {
        adaptor = new RestfulClaimantServiceAdaptor(
                servicesProperties,
                restfulExecutor);
        when(restfulExecutor.get(
                eq(GET_NEXT_BOOKING_URL),
                eq(NextBookingResponse.class),
                any()))
                .thenReturn(Optional.of(CLAIMANT_ID.toString()));
        when(restfulExecutor.getListWithPayload(
                eq(GET_BY_NINO_URL),
                eq(new Nino(NINO)),
                eq(ClaimantResponse.class),
                any())).thenReturn(Optional.of(Collections.singletonList(CLAIMANT)));
        when(servicesProperties.getClaimantVersion()).thenReturn(CLAIMANT_SERVICE_VERSION);
        when(servicesProperties.getClaimantServer()).thenReturn(CLAIMANT_SERVER);
    }


    private void whenGetNextBooking() {
        getNextBookingResponse = adaptor.getNextBookingAppointment();
    }

    private void whenIGetClaimantByNino() throws ExecutionException, InterruptedException {
        claimant = adaptor.getClaimantByNino(NINO).get();
    }

    private void thenTheClaimantIsReturned() {
        assertThat(claimant.get().get(0), is(CLAIMANT));
    }

    private void thenTheNextBookingIsReturned() {
        assertThat(getNextBookingResponse.get(), is(CLAIMANT_ID.toString()));
    }

}
