package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.http.api.ClaimStats;
import uk.gov.dwp.jsa.adaptors.http.api.SubmittedClaimsTally;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface NotificationServiceAdaptor {

    CompletableFuture<String> sendSMS(final UUID claimantId);

    CompletableFuture<String> sendEmail(final UUID claimantId);

    CompletableFuture<String> sendSubmittedClaimsCountEmail(final SubmittedClaimsTally submittedClaimsTally);

    CompletableFuture<String> sendClaimStatsEmail(final ClaimStats claimStats);

    CompletableFuture<String> sendClaimProgressEmail(final UUID claimantId);

    CompletableFuture<String> sendClaimProgressSMS(final UUID claimantId);

    CompletableFuture<String> sendClaimSuccessEmail(final UUID claimantId);

    CompletableFuture<String> sendClaimSuccessSms(final UUID claimantId);

    CompletableFuture<String> sendDailyClaimStatsMail(final int previousDayCount);
}
