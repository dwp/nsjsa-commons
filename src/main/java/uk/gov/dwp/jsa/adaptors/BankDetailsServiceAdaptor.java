package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.dto.claim.BankDetails;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface BankDetailsServiceAdaptor {
    CompletableFuture<Optional<BankDetails>> getBankDetailsByClaimantId(final UUID claimId);

    CompletableFuture<Optional<Boolean>> delete(UUID id);

    CompletableFuture<Optional<UUID>> postBankDetailsData(final BankDetails bankDetails, final UUID claimantId);

    CompletableFuture updateBankDetailsData(Optional<BankDetails> dataFromClaimOptional, final UUID claimantId,
                                            final Optional<UUID> id);
}
