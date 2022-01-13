package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimStatusHistory {

    private UUID claimantId;

    private boolean locked;

    private LocalDateTime createdTimestamp;

    private LocalDateTime updatedTimestamp;

    private List<Status> bookingStatuses = new ArrayList<>();

    public ClaimStatusHistory() {
    }

    public UUID getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(final UUID claimantId) {
        this.claimantId = claimantId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(final boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(final LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(final LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public List<Status> getBookingStatuses() {
        return bookingStatuses;
    }

    public void setBookingStatuses(final List<Status> bookingStatuses) {
        this.bookingStatuses = bookingStatuses;
    }

}
