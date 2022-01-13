package uk.gov.dwp.jsa.adaptors.http.api;

import java.util.UUID;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class NotificationRequest {

    private UUID claimantId;

    public NotificationRequest() {
    }

    public NotificationRequest(final UUID claimantId) {
        this.claimantId = claimantId;
    }

    public UUID getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(final UUID claimantId) {
        this.claimantId = claimantId;
    }

    @Override
    public boolean equals(final Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
