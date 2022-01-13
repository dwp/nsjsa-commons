package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.dto.claim.Claimant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ClaimantServiceAdaptor {

    CompletableFuture<Optional<Claimant>> getClaimant(final UUID claimantId);

    CompletableFuture<Optional<List<Claimant>>> getClaimantByNino(final String nino);

    CompletableFuture<Optional<Boolean>> delete(UUID claimantId);

    Optional<String> getNextBookingAppointment();

    CompletableFuture<Optional<UUID>> postClaimantData(final Claimant claimant);

    CompletableFuture<Optional<UUID>> updateClaimantData(final Claimant claimant);

}
