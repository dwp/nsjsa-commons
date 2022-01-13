package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.dto.jsaps.JsapsRequest;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface JsapsServiceAdaptor {

    CompletableFuture<Optional<String>> pushToJsaps(
            final UUID claimantId,
            final JsapsRequest jsapsRequest);
}
