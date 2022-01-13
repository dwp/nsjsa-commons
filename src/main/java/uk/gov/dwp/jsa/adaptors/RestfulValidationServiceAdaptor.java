package uk.gov.dwp.jsa.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.dto.claim.AgentPerformances;
import uk.gov.dwp.jsa.adaptors.dto.claim.ClaimStatistics;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingStatus;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusHistory;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusHistoryResponse;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusUpdate;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ValidationStatusRequest;
import uk.gov.dwp.jsa.adaptors.dto.claim.validation.PushClaimApiResponse;
import uk.gov.dwp.jsa.adaptors.dto.claim.validation.PushClaimResponse;
import uk.gov.dwp.jsa.adaptors.http.api.AgentPerformanceResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimStatisticsResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimStatusUpdateResponse;
import uk.gov.dwp.jsa.adaptors.http.api.UUIDResponse;
import uk.gov.dwp.jsa.adaptors.http.api.InvalidateStatusResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
public class RestfulValidationServiceAdaptor implements ValidationServiceAdaptor {
    private static final String UPDATE_CLAIM_STATUS_URL = "/nsjsa/v%s/claim/%s/booking-status";
    private static final String GET_CLAIM_STATUS_URL = "/nsjsa/v%s/claim/%s/validation";
    private static final String GET_CLAIM_STATISTICS_URL = "/nsjsa/v%s/claim/statistics?date=%s";
    private static final String GET_AGENT_PERFORMANCE_URL = "/nsjsa/v%s/claim/statistics/agent?start=%s&end=%s";
    private static final String PUSH_CLAIM_URL = "/nsjsa/v%s/claim/%s/push";
    private static final String INVALIDATE_STATUS_URL = "/nsjsa/v%s/claim/invalidate";
    private ServicesProperties servicesProperties;
    private RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulValidationServiceAdaptor(
            final ServicesProperties servicesProperties,
            final RestfulExecutor restfulExecutor) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }


    @Override
    public Optional<String> updateClaimStatus(
            final UUID claimantId,
            final BookingStatus bookingStatus) {
        return restfulExecutor.post(
                getUrl(UPDATE_CLAIM_STATUS_URL, claimantId),
                new ClaimStatusUpdate(bookingStatus),
                ClaimStatusUpdateResponse.class,
                RestfulExecutor::created);
    }

    @Override
    @Async
    public CompletableFuture<Optional<ClaimStatusHistory>> getClaimStatusHistory(final UUID claimantId) {
        final Optional<ClaimStatusHistory> claimStatusHistory = restfulExecutor.get(
                getUrl(GET_CLAIM_STATUS_URL, claimantId),
                ClaimStatusHistoryResponse.class,
                RestfulExecutor::okOrNotFound);

        return completedFuture(claimStatusHistory);
    }

    @Override
    public Optional<ClaimStatistics> getClaimStatistics(final LocalDate date) {
        final Optional<ClaimStatistics> claimStatistics = restfulExecutor.get(
                getGetClaimStatisticsUrl(GET_CLAIM_STATISTICS_URL, date),
                ClaimStatisticsResponse.class,
                RestfulExecutor::okOrNotFound);
        return claimStatistics;
    }

    @Override
    public Optional<AgentPerformances> getAgentPerformances(final LocalDate from, final LocalDate to) {
        final Optional<AgentPerformances> agentPerformances = restfulExecutor.get(
                getAgentPerformanceUrl(GET_AGENT_PERFORMANCE_URL, from, to),
                AgentPerformanceResponse.class,
                RestfulExecutor::okOrNotFound);
        return agentPerformances;
    }

    @Override
    public Optional<PushClaimResponse> pushClaim(final UUID claimantId) {
        Optional<PushClaimResponse> result = restfulExecutor.post(
                getUrl(PUSH_CLAIM_URL, claimantId),
                "empty",
                PushClaimApiResponse.class,
                RestfulExecutor::accepted);
        return result;
    }

    @Async
    public CompletableFuture<Optional<UUID>> updateStatus(final UUID claimantId, final ValidationStatusRequest
            statusRequest) {
        final Optional<UUID> result = restfulExecutor.post(getUrl(GET_CLAIM_STATUS_URL, claimantId), statusRequest,
                UUIDResponse.class,
                RestfulExecutor::created);
        return completedFuture(result);
    }

    @Override
    public Optional<Boolean> invalidateStatus(final List<UUID> claimantIds) {
        Optional<Boolean> result = restfulExecutor.put(
                getUrl(INVALIDATE_STATUS_URL),
                claimantIds,
                InvalidateStatusResponse.class,
                RestfulExecutor::accepted);
        return result;
    }

    private String getUrl(final String urlTemplate) {
        return servicesProperties.getValidationServer()
                + format(urlTemplate, servicesProperties.getValidationVersion());
    }

    private String getUrl(final String urlTemplate, final UUID id) {
        return servicesProperties.getValidationServer()
                + format(urlTemplate, servicesProperties.getValidationVersion(), id);
    }

    private String getAgentPerformanceUrl(final String urlTemplate, final LocalDate start, final LocalDate end) {
        return servicesProperties.getValidationServer()
                + format(urlTemplate, servicesProperties.getValidationVersion(), start, end);
    }

    private String getGetClaimStatisticsUrl(final String urlTemplate, final LocalDate date) {
        return servicesProperties.getValidationServer()
                + format(urlTemplate, servicesProperties.getValidationVersion(), date);
    }
}


