package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.Circumstances;
import uk.gov.dwp.jsa.adaptors.http.api.ApiSuccess;
import uk.gov.dwp.jsa.adaptors.http.api.CircumstancesResponse;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulExecutorTest2 {
    private static final HttpEntity HTTP_ENTITY = new HttpEntity(null);
    private static final String URL = "URI";
    private static final Circumstances CIRCUMSTANCES = new Circumstances();
    private static final Throwable EXCEPTION = new RestClientException("");
    private static final Circumstances NULL_CIRCUMSTANCES = null;
    private static final URI PATH = URI.create("/path/to/object");

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private HttpEntityFactory httpEntityFactory;

    private RestfulExecutor executor;
    private Optional<Circumstances> circumstances;

    private CircumstancesResponse circumstancesResponse;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void executesGet() {
        givenAnAdaptor();
        givenServiceReturns(CIRCUMSTANCES, HttpStatus.OK);
        whenIExecuteGet();
        thenTheBankDetailsAreReturned();
    }

    @Test
    public void executeReturnsEmptyIfNoBankDetails() {
        givenAnAdaptor();
        givenServiceReturns(NULL_CIRCUMSTANCES, HttpStatus.OK);
        whenIExecuteGet();
    }

    @Test(expected = RestClientException.class)
    public void executeThrowsRestClientException() {
        givenAnAdaptor();
        givenServiceThrowsException();
        whenIExecuteGet();
        thenAnExceptionIsThrown();
    }

    @Test(expected = RestClientException.class)
    public void executeReturnsEmptyForInvalidResponse() {
        givenAnAdaptor();
        givenServiceReturns(CIRCUMSTANCES, HttpStatus.BAD_REQUEST);
        whenIExecuteGet();
    }


    private void givenAnAdaptor() {
        executor = new RestfulExecutor(restTemplate, httpEntityFactory);
        executor.init();
        when(httpEntityFactory.createWithJsonHeaders()).thenReturn(HTTP_ENTITY);
    }

    private void givenServiceThrowsException() {
        when(restTemplate
                .getForEntity(
                        eq(URL),
                        eq(CircumstancesResponse.class),
                        eq(HTTP_ENTITY))).thenThrow(EXCEPTION);
    }

    private void givenServiceReturns(final Circumstances circumstances, final HttpStatus httpStatus) {
        CircumstancesResponse response = new CircumstancesResponse();
        ApiSuccess<Circumstances> apiSuccess = new ApiSuccess<>();
        apiSuccess.setData(circumstances);
        List<ApiSuccess<Circumstances>> list = Arrays.asList(apiSuccess);
        apiSuccess.setPath(PATH);
        response.setSuccess(list);
        final ResponseEntity<CircumstancesResponse> responseEntity = new ResponseEntity<>(response, httpStatus);
        when(restTemplate
                .getForEntity(
                        eq(URL),
                        eq(CircumstancesResponse.class),
                        eq(HTTP_ENTITY))).thenReturn(responseEntity);
    }


    private void whenIExecuteGet() {
        circumstances = executor.get(URL, CircumstancesResponse.class, RestfulExecutor::okOrNotFound);
    }

    private void thenAnExceptionIsThrown() {
        //do nothing
    }

    private void thenNoBankDetailsAreReturned() {
        assertThat(circumstances.isPresent(), is(false));
    }



    private void thenTheBankDetailsAreReturned() {
        assertThat(circumstances.get(), is(CIRCUMSTANCES));
    }


}
