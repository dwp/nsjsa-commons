package uk.gov.dwp.jsa.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.dto.LocalOffice;
import uk.gov.dwp.jsa.adaptors.dto.claim.LocalOfficeResponse;
import uk.gov.dwp.jsa.adaptors.exception.CommunicationException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
public class RestfulOfficeSearchServiceAdaptor implements OfficeSearchServiceAdaptor {

    private static final String GET_LOCAL_OFFICE_URL = "/nsjsa/v%s/office/job-centre/postcode/%s";
    public static final String PROBLEM_COMMUNICATING_WITH_OFFICE_SEARCH_SERVICE =
            "Problem communicating with Office Search Service.";

    @Override
    @Async
    public CompletableFuture<Optional<LocalOffice>> getLocalOffice(final String postCode) {
        try {
            final Optional<LocalOffice> localOffice = restfulExecutor.get(
                    getUrl(GET_LOCAL_OFFICE_URL, postCode),
                    LocalOfficeResponse.class,
                    RestfulExecutor::okOrNotFound);

            return completedFuture(localOffice);
        } catch (Exception x) {
            throw new CommunicationException(PROBLEM_COMMUNICATING_WITH_OFFICE_SEARCH_SERVICE, x);
        }
    }
    private ServicesProperties servicesProperties;

    private RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulOfficeSearchServiceAdaptor(
            final ServicesProperties servicesProperties,
            final RestfulExecutor restfulExecutor) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }

    private String getUrl(final String urlTemplate, final String postcode) {
        return servicesProperties.getOfficeSearchServer()
                + format(urlTemplate, servicesProperties.getOfficeSearchVersion(), postcode);
    }

}
