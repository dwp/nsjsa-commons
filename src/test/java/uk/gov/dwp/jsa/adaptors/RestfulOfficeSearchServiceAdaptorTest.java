package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientException;
import uk.gov.dwp.jsa.adaptors.dto.LocalOffice;
import uk.gov.dwp.jsa.adaptors.dto.claim.LocalOfficeResponse;
import uk.gov.dwp.jsa.adaptors.exception.CommunicationException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulOfficeSearchServiceAdaptorTest {

    private static final String POSTCODE = "POSTCODE";
    private static final String OFFICE_SEARCH_SERVICE_VERSION = "1";
    private static final String OFFICE_SEARCH_SERVER = "http:///hello";
    private static final String GET_LOCAL_OFFICE_URL = String.format("%s/nsjsa/v%s/office/job-centre/postcode/%s", OFFICE_SEARCH_SERVER, OFFICE_SEARCH_SERVICE_VERSION, POSTCODE);
    private static final LocalOffice LOCAL_OFFICE = new LocalOffice();
    private static final RestClientException REST_CLIENT_EXCEPTION = new RestClientException("");

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulOfficeSearchServiceAdaptor adaptor;
    private CompletableFuture<Optional<LocalOffice>> localOfficeResponse;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }


    @Test
    public void getsLocalOffice() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenGetLocalOffice();
        thenTheLocalOfficeIsReturned();
    }

    @Test(expected = CommunicationException.class)
    public void getLocalOfficeThrowsCommunicationException() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        when(restfulExecutor.get(
                eq(GET_LOCAL_OFFICE_URL),
                eq(LocalOfficeResponse.class),
                any()))
                .thenThrow(REST_CLIENT_EXCEPTION);
        whenGetLocalOffice();
        thenAnExceptionIsThrow();
    }

    private void givenAnAdaptor() {
        adaptor = new RestfulOfficeSearchServiceAdaptor(
                servicesProperties,
                restfulExecutor);
        when(restfulExecutor.get(
                eq(GET_LOCAL_OFFICE_URL),
                eq(LocalOfficeResponse.class),
                any()))
                .thenReturn(Optional.of(LOCAL_OFFICE));
        when(servicesProperties.getOfficeSearchVersion()).thenReturn(OFFICE_SEARCH_SERVICE_VERSION);
        when(servicesProperties.getOfficeSearchServer()).thenReturn(OFFICE_SEARCH_SERVER);
    }


    private void whenGetLocalOffice() {
        localOfficeResponse = adaptor.getLocalOffice(POSTCODE);
    }

    private void thenTheLocalOfficeIsReturned() throws ExecutionException, InterruptedException {
        assertThat(localOfficeResponse.get().get(), is(LOCAL_OFFICE));
    }

    private void thenAnExceptionIsThrow() {
        //do nothing
    }

}
