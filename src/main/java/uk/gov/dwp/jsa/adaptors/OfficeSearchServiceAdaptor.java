package uk.gov.dwp.jsa.adaptors;

import uk.gov.dwp.jsa.adaptors.dto.LocalOffice;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface OfficeSearchServiceAdaptor {

    CompletableFuture<Optional<LocalOffice>> getLocalOffice(final String postCode);
}
