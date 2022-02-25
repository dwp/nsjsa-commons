package uk.gov.dwp.jsa.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimStats;
import uk.gov.dwp.jsa.adaptors.http.api.NotificationRequest;
import uk.gov.dwp.jsa.adaptors.http.api.SubmittedClaimsTally;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.lang.String.format;

@Service
public class RestfulNotificationServiceAdaptor implements NotificationServiceAdaptor {

    private String sendEmailUrl = "/nsjsa/v%s/notification/mail/claim-confirmation";
    private String sendSmsUrl = "/nsjsa/v%s/notification/sms/claim-confirmation";
    private String sendSubmittedClaimsCountEmailUrl = "/nsjsa/v%s/notification/mail/mi-claims-count";
    private String sendClaimStatsEmailUrl = "/nsjsa/v%s/notification/mail/mi-claim-stats";
    private String sendClaimProgressEmailUrl = "/nsjsa/v%s/notification/mail/claim-progress";
    private String sendClaimProgressSmsUrl = "/nsjsa/v%s/notification/sms/claim-progress";
    private String sendClaimSuccessMailUrl = "/nsjsa/v%s/notification/mail/claimant-success";
    private String sendClaimSuccessSmsUrl = "/nsjsa/v%s/notification/sms/claimant-success";
    private String sendDailyClaimStatsEmailUrl = "/nsjsa/v%s/notification/mail/daily-claim-stats-summary?"
            + "previousDayCount=%s";

    private final ServicesProperties servicesProperties;
    private final RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulNotificationServiceAdaptor(final ServicesProperties servicesProperties,
                                             final RestfulExecutor restfulExecutor) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }

    private CompletableFuture<String> sendNotification(final String baseUrl, final UUID claimantId) {
        restfulExecutor.post(getUrl(baseUrl), new NotificationRequest(claimantId),
                ApiResponse.class, (Function<ResponseEntity<ApiResponse>, Optional<ApiResponse>>)
                        apiResponseResponseEntity -> Optional.empty());
        return CompletableFuture.completedFuture(HttpStatus.OK.name());
    }

    private CompletableFuture<String> sendProgressNotification(final String baseUrl, final UUID claimantId) {
        restfulExecutor.post(getUrl(baseUrl), new NotificationRequest(claimantId),
                ApiResponse.class, (Function<ResponseEntity<ApiResponse>, Optional<ApiResponse>>)
                        apiResponseResponseEntity -> Optional.empty());
        return CompletableFuture.completedFuture(HttpStatus.OK.name());
    }

    private CompletableFuture<String> sendNotification(final String baseUrl,
                                                       final SubmittedClaimsTally submittedClaimsTally) {
        restfulExecutor.post(getUrl(baseUrl), submittedClaimsTally,
                ApiResponse.class, (Function<ResponseEntity<ApiResponse>, Optional<ApiResponse>>)
                        apiResponseResponseEntity -> Optional.empty());
        return CompletableFuture.completedFuture(HttpStatus.OK.name());
    }

    private CompletableFuture<String> sendNotification(final String baseUrl,
                                                       final ClaimStats claimStats) {
        restfulExecutor.post(getUrl(baseUrl), claimStats,
                ApiResponse.class, (Function<ResponseEntity<ApiResponse>, Optional<ApiResponse>>)
                        apiResponseResponseEntity -> Optional.empty());
        return CompletableFuture.completedFuture(HttpStatus.OK.name());
    }

    private CompletableFuture<String> sendNotification(final String baseUrl,
                                                       final int previousDayCount) {
        final String url = servicesProperties.getNotificationServer()
                + format(baseUrl, servicesProperties.getNotificationVersion(), previousDayCount);
        restfulExecutor.post(url, null,
                ApiResponse.class, (Function<ResponseEntity<ApiResponse>, Optional<ApiResponse>>)
                        apiResponseResponseEntity -> Optional.empty());
        return CompletableFuture.completedFuture(HttpStatus.OK.name());
    }

    private CompletableFuture<String> sendClaimSuccessNotification(final String baseUrl, final UUID claimantId) {
        restfulExecutor.post(getUrl(baseUrl), new NotificationRequest(claimantId),
                ApiResponse.class, (Function<ResponseEntity<ApiResponse>, Optional<ApiResponse>>)
                        apiResponseResponseEntity -> Optional.empty());
        return CompletableFuture.completedFuture(HttpStatus.OK.name());
    }

    @Async
    public CompletableFuture<String> sendSMS(final UUID claimantId) {
        return sendNotification(sendSmsUrl, claimantId);
    }

    @Async
    public CompletableFuture<String> sendEmail(final UUID claimantId) {
        return sendNotification(sendEmailUrl, claimantId);
    }

    @Async
    public CompletableFuture<String> sendClaimStatsEmail(final ClaimStats claimStats) {
        return sendNotification(sendClaimStatsEmailUrl, claimStats);
    }

    @Async
    public CompletableFuture<String> sendSubmittedClaimsCountEmail(final SubmittedClaimsTally submittedClaimsTally) {
        return sendNotification(sendSubmittedClaimsCountEmailUrl, submittedClaimsTally);
    }

    @Async
    public CompletableFuture<String> sendClaimSuccessEmail(final UUID claimantId) {
        return sendClaimSuccessNotification(sendClaimSuccessMailUrl, claimantId);
    }

    @Async
    public CompletableFuture<String> sendClaimSuccessSms(final UUID claimantId) {
        return sendClaimSuccessNotification(sendClaimSuccessSmsUrl, claimantId);
    }

    @Async
    public CompletableFuture<String> sendDailyClaimStatsMail(final int previousDayCount) {
        return sendNotification(sendDailyClaimStatsEmailUrl, previousDayCount);
    }

    @Async
    public CompletableFuture<String> sendClaimProgressEmail(final UUID claimantId) {
        return sendProgressNotification(sendClaimProgressEmailUrl, claimantId);
    }

    @Async
    public CompletableFuture<String> sendClaimProgressSMS(final UUID claimantId) {
        return sendProgressNotification(sendClaimProgressSmsUrl, claimantId);
    }

    private String getUrl(final String urlTemplate) {
        return servicesProperties.getNotificationServer()
                + format(urlTemplate, servicesProperties.getNotificationVersion());
    }
}
