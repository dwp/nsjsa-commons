package uk.gov.dwp.jsa.adaptors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.dto.Nino;
import uk.gov.dwp.jsa.adaptors.dto.claim.Claimant;
import uk.gov.dwp.jsa.adaptors.http.api.ClaimantResponse;
import uk.gov.dwp.jsa.adaptors.http.api.UUIDResponse;
import uk.gov.dwp.jsa.adaptors.http.api.ApiResponse;
import uk.gov.dwp.jsa.adaptors.http.api.NextBookingResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
public class RestfulClaimantServiceAdaptor implements ClaimantServiceAdaptor {
    private static final String GET_BY_CLAIMANT_ID_URL = "/nsjsa/v%s/citizen/%s";
    private static final String GET_BY_NINO_URL = "/nsjsa/v%s/citizen/nino";
    private static final String DELETE_BY_ID_URL = "/nsjsa/v%s/citizen/%s";
    private static final String POST_URL = "/nsjsa/v%s/citizen";
    private static final String PATCH_URL = "/nsjsa/v%s/citizen/%s";
    private static final String GET_NEXT_BOOKING_URL = "/nsjsa/v%s/claim/status/to-book/";

    private ServicesProperties servicesProperties;
    private RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulClaimantServiceAdaptor(
            final ServicesProperties servicesProperties,
            final RestfulExecutor restfulExecutor) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }

    @Override
    @Async
    public CompletableFuture<Optional<Claimant>> getClaimant(final UUID claimantId) {
        Optional<Claimant> claimantsOptional =
                restfulExecutor.get(getUrl(GET_BY_CLAIMANT_ID_URL, claimantId.toString()), ClaimantResponse.class,
                        RestfulExecutor::okOrNotFound);
        return completedFuture(claimantsOptional);

    }


    @Override
    @Async
    public CompletableFuture<Optional<List<Claimant>>> getClaimantByNino(final String nino) {
        Optional<List<Claimant>> claimantsOptional =
                restfulExecutor.getListWithPayload(
                        getUrl(GET_BY_NINO_URL),
                        new Nino(nino),
                        ClaimantResponse.class,
                        RestfulExecutor::okOrNotFoundList);
        return completedFuture(claimantsOptional);
    }



    @Override
    @Async
    public CompletableFuture<Optional<Boolean>> delete(final UUID claimantId) {
        Optional<Boolean> result =
                restfulExecutor.delete(getUrl(DELETE_BY_ID_URL, claimantId.toString()), RestfulExecutor::deleted);
        return completedFuture(result);
    }


    @Override
    @Async
    @SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
    public CompletableFuture<Optional<UUID>> postClaimantData(final Claimant claimant) {
        final Optional<UUID> result = restfulExecutor.post(getUrl(POST_URL), claimant, UUIDResponse.class,
                RestfulExecutor::created);
        return completedFuture(result);
    }

    @Override
    @Async
    public CompletableFuture<Optional<UUID>> updateClaimantData(final Claimant claimant) {

        final Optional<UUID> result = restfulExecutor.patch(
                getUrl(PATCH_URL, claimant.getClaimantId().toString()), claimant, ApiResponse.class,
                RestfulExecutor::okOrNotFound);
        return completedFuture(result);
    }

    private String getUrl(final String urlTemplate, final String id) {
        return servicesProperties.getClaimantServer()
                + format(urlTemplate, servicesProperties.getClaimantVersion(), id);
    }

    private String getUrl(final String urlTemplate) {
        return servicesProperties.getClaimantServer()
                + format(urlTemplate, servicesProperties.getClaimantVersion());
    }

    @Override
    public Optional<String> getNextBookingAppointment() {
        Optional<String> nextBookingOptional =
                restfulExecutor.get(
                        servicesProperties.getClaimantServer()
                                + String.format(
                                GET_NEXT_BOOKING_URL,
                                servicesProperties.getClaimantVersion()),
                        NextBookingResponse.class,
                        RestfulExecutor::okOrNotFound);
        return nextBookingOptional;
    }

}
