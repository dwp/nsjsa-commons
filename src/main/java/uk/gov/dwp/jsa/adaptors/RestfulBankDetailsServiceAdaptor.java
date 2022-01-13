package uk.gov.dwp.jsa.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.dto.claim.BankDetails;
import uk.gov.dwp.jsa.adaptors.http.api.BankDetailsResponse;
import uk.gov.dwp.jsa.adaptors.http.api.UUIDResponse;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class RestfulBankDetailsServiceAdaptor implements BankDetailsServiceAdaptor {
    private static final String GET_BY_CLAIMANT_ID_URL = "/nsjsa/v%s/claim/%s/bank-details";
    private static final String DELETE_BY_ID_URL = "/nsjsa/v%s/bank-details/%s";

    private ServicesProperties servicesProperties;
    private RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulBankDetailsServiceAdaptor(
            final ServicesProperties servicesProperties,
            final RestfulExecutor restfulExecutor) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }

    @Async
    public CompletableFuture<Optional<BankDetails>> getBankDetailsByClaimantId(final UUID claimantId) {
        Optional<BankDetails> bankDetailsOptional =
                restfulExecutor.get(getUrl(GET_BY_CLAIMANT_ID_URL, claimantId), BankDetailsResponse.class,
                        RestfulExecutor::okOrNotFound);
        return completedFuture(bankDetailsOptional);
    }

    @Override
    @Async
    public CompletableFuture<Optional<UUID>> postBankDetailsData(final BankDetails bankDetails, final UUID claimantId) {
        final Optional<UUID> result = restfulExecutor.post(getUrl(GET_BY_CLAIMANT_ID_URL, claimantId), bankDetails,
                UUIDResponse.class,
                RestfulExecutor::created);

        return CompletableFuture.completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture updateBankDetailsData(final Optional<BankDetails> dataFromClaimOptional,
                                                   final UUID claimantId,
                                                   final Optional<UUID> id) {
        if (id.isPresent()) {
            if (dataFromClaimOptional.isPresent() && !isEmpty(dataFromClaimOptional.get().getAccountNumber())) {
                final Optional<UUID> result = restfulExecutor.patch(getUrl(DELETE_BY_ID_URL, id.get()),
                        dataFromClaimOptional.get(),
                        UUIDResponse.class,
                        RestfulExecutor::okOrNotFound);
                return CompletableFuture.completedFuture(result);
            } else {
                return delete(id.get());
            }
        } else if (dataFromClaimOptional.isPresent() && !isEmpty(dataFromClaimOptional.get().getAccountNumber())) {
            return postBankDetailsData(dataFromClaimOptional.get(), claimantId);
        }
        return CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public CompletableFuture<Optional<Boolean>> delete(final UUID id) {
        Optional<Boolean> result = restfulExecutor.delete(getUrl(DELETE_BY_ID_URL, id), RestfulExecutor::deleted);
        return completedFuture(result);
    }

    private String getUrl(final String urlTemplate, final UUID claimId) {
        return servicesProperties.getBankDetailsServer()
                + format(urlTemplate, servicesProperties.getBankDetailsVersion(), claimId.toString());
    }


}
