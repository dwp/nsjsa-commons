package uk.gov.dwp.jsa.adaptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uk.gov.dwp.jsa.adaptors.dto.jsaps.JsapsRequest;
import uk.gov.dwp.jsa.adaptors.http.api.JsapsPushResponse;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
public class RestfulJsapsServiceAdaptor implements JsapsServiceAdaptor {

    private static final String JSAPS_ADAPTER_PUSH = "/jsaps/v%s/claim/%s";

    private ServicesProperties servicesProperties;
    private RestfulExecutor restfulExecutor;

    @Autowired
    public RestfulJsapsServiceAdaptor(
            final ServicesProperties servicesProperties,
            final RestfulExecutor restfulExecutor
    ) {
        this.servicesProperties = servicesProperties;
        this.restfulExecutor = restfulExecutor;
    }

    @Async
    public CompletableFuture<Optional<String>> pushToJsaps(
            final UUID claimantId,
            final JsapsRequest jsapsRequest) {
        Optional<String> result = restfulExecutor.updateMultiError(
                getUrl(JSAPS_ADAPTER_PUSH, claimantId.toString()),
                jsapsRequest,
                JsapsPushResponse.class,
                RestfulExecutor::createdMultiError);
        return completedFuture(result);

    }

    private String getUrl(final String urlTemplate, final String claimantId) {
        return servicesProperties.getJsapsServer()
                + format(urlTemplate, servicesProperties.getJsapsVersion(), claimantId);
    }

}
