package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.Circumstances;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CircumstancesServiceAdaptor {
    CompletableFuture<Optional<Circumstances>> getCircumstancesByClaimantId(final UUID claimId);

    CompletableFuture<Optional<Boolean>> delete(UUID id);

    CompletableFuture<Optional<UUID>> postCircumstancesData(final Circumstances circumstances, final UUID claimantId);

    CompletableFuture<Optional<UUID>> updateCircumstancesData(final Circumstances circumstances,
                                                              final UUID claimantId, final UUID id);
}
