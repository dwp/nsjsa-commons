package uk.gov.dwp.jsa.adaptors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.gov.dwp.jsa.adaptors.dto.claim.AgentPerformance;
import uk.gov.dwp.jsa.adaptors.dto.claim.AgentPerformances;
import uk.gov.dwp.jsa.adaptors.dto.claim.ClaimStatistics;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingStatus;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingStatusType;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusHistory;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusHistoryResponse;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusUpdate;
import uk.gov.dwp.jsa.adaptors.dto.claim.validation.PushClaimApiResponse;
import uk.gov.dwp.jsa.adaptors.dto.claim.validation.PushClaimResponse;
import uk.gov.dwp.jsa.adaptors.http.api.AgentPerformanceResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimStatisticsResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimStatusUpdateResponse;
import uk.gov.dwp.jsa.adaptors.http.api.InvalidateStatusResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RestfulValidationServiceAdaptorTest {

    private static final UUID CLAIMANT_ID = UUID.randomUUID();
    private static final BookingStatusType STATUS = BookingStatusType.WITHDRAWN;
    private static final String SUB_STATUS = "SUB_STATUS";
    private static final String VALIDATION_SERVICE_VERSION = "1";
    private static final String VALIDATION_SERVER = "http:///hello";
    private static final String UPDATE_CLAIM_URL = String.format("%s/nsjsa/v%s/claim/%s/booking-status", VALIDATION_SERVER, VALIDATION_SERVICE_VERSION, CLAIMANT_ID.toString());
    private static final String GET_CLAIM_STATUS_URL = String.format("%s/nsjsa/v%s/claim/%s/validation", VALIDATION_SERVER, VALIDATION_SERVICE_VERSION, CLAIMANT_ID.toString());
    private static final String PUSH_CLAIM_URL = String.format("%s/nsjsa/v%s/claim/%s/push", VALIDATION_SERVER, VALIDATION_SERVICE_VERSION, CLAIMANT_ID.toString());
    private static final ClaimStatusHistory RESPONSE_HISTORY = new ClaimStatusHistory();
    private static final ClaimStatistics CLAIM_STATISTICS = new ClaimStatistics();
    private static final AgentPerformance AGENT_PERFORMANCE = new AgentPerformance("Agent 1", 1, 1, 1, 1);
    private static final AgentPerformances AGENT_PERFORMANCES = new AgentPerformances(LocalDate.now(), LocalDate.now(), new AgentPerformance[] { AGENT_PERFORMANCE });
    private static final String OFFICE_ID = "OFFICE_ID";
    private static final String AGENT = "AGENT";
    private static final LocalDate today = LocalDate.now();
    private static final String GET_CLAIM_STATISTICS_URL = String.format("%s/nsjsa/v%s/claim/statistics?date=%s", VALIDATION_SERVER, VALIDATION_SERVICE_VERSION, LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
    private static final String GET_AGENT_PERFORMANCE_URL = String.format("%s/nsjsa/v%s/claim/statistics/agent?start=%s&end=%s", VALIDATION_SERVER, VALIDATION_SERVICE_VERSION, today, today);
    private static final String INVALIDATE_STATUS_URL = String.format("%s/nsjsa/v%s/claim/invalidate", VALIDATION_SERVER, VALIDATION_SERVICE_VERSION);
    private static final List<UUID> claimantIds = Arrays.asList(new UUID[] { UUID.randomUUID() });
    private static final Optional<Boolean> invalidateResult = Optional.of(true);

    @Mock
    private ServicesProperties servicesProperties;
    @Mock
    private RestfulExecutor restfulExecutor;

    private RestfulValidationServiceAdaptor adaptor;
    private Optional<String> updateClaimStatusResponse;
    private CompletableFuture<Optional<ClaimStatusHistory>> getHistoryResponse;
    private Optional<PushClaimResponse> pushClaimResult;
    private PushClaimResponse validationResponse = new PushClaimResponse();
    private Optional<Boolean> invalidateResponse = Optional.of(new Boolean(true));
    private Optional<ClaimStatistics> claimStatistics;
    private Optional<AgentPerformances> agentPerformances;


    @Before
    public void beforeEachTest() {
        initMocks(this);
        when(servicesProperties.getValidationVersion()).thenReturn(VALIDATION_SERVICE_VERSION);
        when(servicesProperties.getValidationServer()).thenReturn(VALIDATION_SERVER);
        adaptor = new RestfulValidationServiceAdaptor(
                servicesProperties,
                restfulExecutor);
    }

    @Test
    public void updatesClaimStatus() {
        givenAnAdaptor();
        whenIUpdateTheStatus();
        thenTheStatusUpdated();
    }


    @Test
    public void getClaimStatusHistory() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenGetTheStatusHistory();
        thenTheStatusHistoryIsRetrieved();
    }

    @Test
    public void getClaimStatistics() throws ExecutionException, InterruptedException {
        givenAnAdaptor();
        whenGetTheClaimStatistics(LocalDate.now());
        thenTheClaimStatisticsAreReturned();
    }


    @Test
    public void pushClaim() {
        givenAdaptorForOPushClaim();
        whenPushClaim();
        thenTheResponseIsReceived();
    }

    @Test
    public void getAgentPerformances() {
        givenAnAdaptorForAgentPerformance();
        whenGetTheAgentPerformances();
        thenTheAgentPerformancesAreReturned();
    }

    @Test
    public void invalidateStatus() {
        givenAnAdaptorForInvalidatingStatus();
        whenInvalidatingTheStatus();
        thenTheResultOfTheInvalidateIsReturned();
    }

    private void givenAnAdaptor() {
        when(restfulExecutor.post(
                eq(UPDATE_CLAIM_URL),
                any(ClaimStatusUpdate.class),
                eq(ClaimStatusUpdateResponse.class),
                any()))
                .thenReturn(Optional.of(CLAIMANT_ID.toString()));

        when(restfulExecutor.get(
                eq(GET_CLAIM_STATUS_URL),
                eq(ClaimStatusHistoryResponse.class),
                any())
        ).thenReturn(Optional.of(RESPONSE_HISTORY));

        when(restfulExecutor.get(
                eq(GET_CLAIM_STATISTICS_URL),
                eq(ClaimStatisticsResponse.class),
                any())
        ).thenReturn(Optional.of(CLAIM_STATISTICS));
    }


    private void givenAdaptorForOPushClaim() {
        when(restfulExecutor.post(
                eq(PUSH_CLAIM_URL),
                eq("empty"),
                eq(PushClaimApiResponse.class),
                any())).thenReturn(Optional.of(validationResponse));
    }

    private void givenAnAdaptorForAgentPerformance() {
        when(restfulExecutor.get(
                eq(GET_AGENT_PERFORMANCE_URL),
                eq(AgentPerformanceResponse.class),
                any())
        ).thenReturn(Optional.of(AGENT_PERFORMANCES));
    }

    private void givenAnAdaptorForInvalidatingStatus() {
        when(restfulExecutor.put(
                eq(INVALIDATE_STATUS_URL),
                eq(claimantIds),
                eq(InvalidateStatusResponse.class),
                any())
        ).thenReturn(invalidateResponse);
    }

    private void whenIUpdateTheStatus() {
        updateClaimStatusResponse = adaptor.updateClaimStatus(CLAIMANT_ID, new BookingStatus(STATUS, SUB_STATUS, AGENT, OFFICE_ID));
    }

    private void whenGetTheStatusHistory() {
        getHistoryResponse = adaptor.getClaimStatusHistory(CLAIMANT_ID);
    }

    private void whenPushClaim() {
        pushClaimResult = adaptor.pushClaim(CLAIMANT_ID);
    }

    private void whenGetTheClaimStatistics(final LocalDate date) {
        claimStatistics = adaptor.getClaimStatistics(date);
    }

    private void whenGetTheAgentPerformances() {
        agentPerformances = adaptor.getAgentPerformances(today, today);
    }

    private void whenInvalidatingTheStatus() {
        invalidateResponse = adaptor.invalidateStatus(claimantIds);
    }

    private void thenTheStatusHistoryIsRetrieved() throws ExecutionException, InterruptedException {
        assertThat(getHistoryResponse.get().get(), is(RESPONSE_HISTORY));
    }

    private void thenTheStatusUpdated() {
        assertThat(updateClaimStatusResponse.get(), is(CLAIMANT_ID.toString()));
    }

    private void thenTheClaimStatisticsAreReturned() {
        assertThat(claimStatistics.get(), is(CLAIM_STATISTICS));
    }

    private void thenTheResponseIsReceived() {
        assertThat(pushClaimResult.get(), is(validationResponse));
    }

    private void thenTheAgentPerformancesAreReturned() {
        AgentPerformances actual = agentPerformances.get();
        AgentPerformance actualPerf = actual.getAgents()[0];
        assertThat(actual, is(AGENT_PERFORMANCES));
        assertThat(actualPerf, is(AGENT_PERFORMANCE));
        assertThat(actualPerf.getAgent(), is("Agent 1"));
        assertThat(actualPerf.getSuccess(), is(1));
        assertThat(actualPerf.getFail(), is(1));
        assertThat(actualPerf.getWithdrawn(), is(1));
        assertThat(actualPerf.getTotal(), is(1));
    }

    private void thenTheResultOfTheInvalidateIsReturned() {
        Optional<Boolean> actual = invalidateResponse;
        assertThat(actual, is(invalidateResult));
    }


}
