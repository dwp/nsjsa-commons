package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.dto.claim.AgentPerformances;
import uk.gov.dwp.jsa.adaptors.dto.claim.ClaimStatistics;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.BookingStatus;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ClaimStatusHistory;
import uk.gov.dwp.jsa.adaptors.dto.claim.status.ValidationStatusRequest;
import uk.gov.dwp.jsa.adaptors.dto.claim.validation.PushClaimResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ValidationServiceAdaptor {

    Optional<String> updateClaimStatus(
            final UUID claimantId,
            final BookingStatus bookingStatus);


    CompletableFuture<Optional<ClaimStatusHistory>> getClaimStatusHistory(final UUID claimantId);

    Optional<ClaimStatistics> getClaimStatistics(final LocalDate date);

    Optional<AgentPerformances> getAgentPerformances(final LocalDate start, final LocalDate end);

    Optional<PushClaimResponse> pushClaim(final UUID claimantId);

    CompletableFuture<Optional<UUID>> updateStatus(final UUID claimantId, final ValidationStatusRequest statusRequest);

    Optional<Boolean> invalidateStatus(final List<UUID> claimantIds);
}
