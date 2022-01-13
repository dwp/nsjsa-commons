package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.gov.dwp.jsa.adaptors.dto.claim.BankDetails;
import uk.gov.dwp.jsa.adaptors.http.api.ApiMultiErrorResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ApiSuccess;
import uk.gov.dwp.jsa.adaptors.http.api.BankDetailsResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpMethod.GET;

public class RestfulExecutorTest {
    private static final HttpEntity HTTP_ENTITY = new HttpEntity(null);
    private static final String URL = "URI";
    private static final BankDetails BANK_DETAILS = new BankDetails();
    private static final Throwable EXCEPTION = new RestClientException("");
    private static final BankDetails NULL_BANK_DETAILS = null;
    private static final URI PATH = URI.create("/path/to/object");

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private HttpEntityFactory httpEntityFactory;

    private RestfulExecutor executor;
    private Optional<BankDetails> bankDetails;

    @Before
    public void beforeEachTest() {
        initMocks(this);
    }

    @Test
    public void executesGet() {
        givenAnAdaptor();
        givenServiceReturns(BANK_DETAILS, HttpStatus.OK);
        whenIExecuteGet();
        thenTheBankDetailsAreReturned();
    }

    @Test
    public void executeReturnsEmptyIfNoBankDetails() {
        givenAnAdaptor();
        givenServiceReturns(NULL_BANK_DETAILS, HttpStatus.OK);
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
        givenServiceReturns(BANK_DETAILS, HttpStatus.BAD_REQUEST);
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
                        eq(BankDetailsResponse.class),
                        eq(HTTP_ENTITY))).thenThrow(EXCEPTION);
    }

    private void givenServiceReturns(final BankDetails bankDetails, final HttpStatus httpStatus) {
        BankDetailsResponse response = new BankDetailsResponse();
        ApiSuccess<BankDetails> apiSuccess = new ApiSuccess<>();
        apiSuccess.setData(bankDetails);
        List<ApiSuccess<BankDetails>> list = Arrays.asList(apiSuccess);
        apiSuccess.setPath(PATH);
        response.setSuccess(list);
        final ResponseEntity<BankDetailsResponse> responseEntity = new ResponseEntity<>(response, httpStatus);
        when(restTemplate
                .getForEntity(
                        eq(URL),
                        eq(BankDetailsResponse.class),
                        eq(HTTP_ENTITY))).thenReturn(responseEntity);
    }

    private void whenIExecuteGet() {
        bankDetails = executor.get(URL, BankDetailsResponse.class, RestfulExecutor::okOrNotFound);
    }

    private void thenAnExceptionIsThrown() {
        //do nothing
    }

    private void thenNoBankDetailsAreReturned() {
        assertThat(bankDetails.isPresent(), is(false));
    }



    private void thenTheBankDetailsAreReturned() {
        assertThat(bankDetails.get(), is(BANK_DETAILS));
    }

    @Test
    public void testGetListWithPayload() {
        givenAnAdaptor();

        class IntegerResponse extends ApiResponse<Integer> {}
        IntegerResponse response = new IntegerResponse();
        response.setSuccess(Stream.of(1, 2, 3, 4)
                .map(a -> new ApiSuccess<>(PATH, a))
                .collect(Collectors.toList()));

        ResponseEntity<IntegerResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(GET), any(), eq(IntegerResponse.class)))
                .thenReturn(responseEntity);

        Optional<List<Integer>> result = executor.getListWithPayload(URL, "", IntegerResponse.class,
                RestfulExecutor::okOrNotFoundList);

        assertTrue(result.isPresent());
        assertThat(result.get(), is(Arrays.asList(1,2,3,4)));
    }

    @Test
    public void createdMultiErrorReturnsExpected() throws URISyntaxException {
        givenAnAdaptor();
        givenServiceReturns(BANK_DETAILS, HttpStatus.CREATED);
        Optional<String> result = whenICallCreatedMultiErrorWithSuccessfulApiMultiErrorResponse();
        assertThat(result.get(), is("data"));
    }


    private Optional<String> whenICallCreatedMultiErrorWithSuccessfulApiMultiErrorResponse() throws URISyntaxException {
        List<ApiSuccess> list = new ArrayList<>();
        URI path = new URI("path");
        Object data = "data";
        list.add(new ApiSuccess(path, data));
        ApiMultiErrorResponse mer = new ApiMultiErrorResponse(list);
        ResponseEntity re = new ResponseEntity<>(mer, HttpStatus.CREATED);
        return RestfulExecutor.createdMultiError(re);
    }

}
