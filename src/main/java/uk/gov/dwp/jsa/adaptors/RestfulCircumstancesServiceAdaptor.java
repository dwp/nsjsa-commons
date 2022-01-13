package uk.gov.dwp.jsa.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.dto.claim.circumstances.Circumstances;
import uk.gov.dwp.jsa.adaptors.http.api.CircumstancesResponse;
import uk.gov.dwp.jsa.adaptors.http.api.UUIDResponse;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
public class RestfulCircumstancesServiceAdaptor implements CircumstancesServiceAdaptor {

    private static final String GET_BY_CLAIMANT_ID_URL = "/nsjsa/v%s/citizen/%s/claim";
    private static final String DELETE_BY_ID_URL = "/nsjsa/v%s/claim/%s";

    private ServicesProperties servicesProperties;
    private RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulCircumstancesServiceAdaptor(
            final ServicesProperties servicesProperties,
            final RestfulExecutor restfulExecutor) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }


    @Override
    @Async
    public CompletableFuture<Optional<Circumstances>> getCircumstancesByClaimantId(final UUID claimantId) {
        Optional<Circumstances> circumstancesOptional =
                restfulExecutor.get(getUrl(GET_BY_CLAIMANT_ID_URL, claimantId), CircumstancesResponse.class,
                        RestfulExecutor::okOrNotFound);
        return completedFuture(circumstancesOptional);
    }

    @Override
    @Async
    public CompletableFuture<Optional<UUID>> postCircumstancesData(final Circumstances circumstances,
                                                                   final UUID claimantId) {
        circumstances.setClaimantId(claimantId);
        final Optional<UUID> result = restfulExecutor.post(getUrl(GET_BY_CLAIMANT_ID_URL, claimantId),
                circumstances, UUIDResponse.class,
                RestfulExecutor::created);
        return completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<Optional<UUID>> updateCircumstancesData(final Circumstances circumstances,
                                                                     final UUID claimantId,
                                                                     final UUID id) {
        circumstances.setClaimantId(claimantId);
        final Optional<UUID> result = restfulExecutor.patch(getUrl(DELETE_BY_ID_URL, id),
                circumstances, UUIDResponse.class,
                RestfulExecutor::okOrNotFound);
        return completedFuture(result);
    }

    @Override
    public CompletableFuture<Optional<Boolean>> delete(final UUID id) {
        Optional<Boolean> result =
                restfulExecutor.delete(getUrl(DELETE_BY_ID_URL, id), RestfulExecutor::deleted);
        return completedFuture(result);
    }

    private String getUrl(final String urlTemplate, final UUID claimId) {
        return servicesProperties.getCircumstancesServer()
                + format(urlTemplate, servicesProperties.getCircumstancesVersion(), claimId.toString());
    }

}
