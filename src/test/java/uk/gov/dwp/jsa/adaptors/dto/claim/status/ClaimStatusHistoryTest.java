package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ClaimStatusHistoryTest {

    public static final UUID CLAIMANT_ID = UUID.randomUUID();
    public static final boolean LOCKED = true;
    public static final LocalDateTime CREATED_TIMESTAMP = LocalDateTime.now();
    public static final LocalDateTime UPDATED_TIMESTAMP = LocalDateTime.now();
    public static final ArrayList<Status> BOOKING_STATUSES = new ArrayList<>();

    @Test
    public void hasDefaultValues() {
        final ClaimStatusHistory claimStatusHistory = new ClaimStatusHistory();
        assertThat(claimStatusHistory.getClaimantId(), is(nullValue()));
        assertThat(claimStatusHistory.isLocked(), is(false));
        assertThat(claimStatusHistory.getCreatedTimestamp(), is(nullValue()));
        assertThat(claimStatusHistory.getUpdatedTimestamp(), is(nullValue()));
        assertThat(claimStatusHistory.getBookingStatuses(), is(BOOKING_STATUSES));
    }

    @Test
    public void setClaimantId() {
        final ClaimStatusHistory claimStatusHistory = new ClaimStatusHistory();
        claimStatusHistory.setClaimantId(CLAIMANT_ID);
        assertThat(claimStatusHistory.getClaimantId(), is(CLAIMANT_ID));
    }

    @Test
    public void setLocked() {
        final ClaimStatusHistory claimStatusHistory = new ClaimStatusHistory();
        claimStatusHistory.setLocked(LOCKED);
        assertThat(claimStatusHistory.isLocked(), is(LOCKED));
    }

    @Test
    public void setCreatedTimestamp() {
        final ClaimStatusHistory claimStatusHistory = new ClaimStatusHistory();
        claimStatusHistory.setCreatedTimestamp(CREATED_TIMESTAMP);
        assertThat(claimStatusHistory.getCreatedTimestamp(), is(CREATED_TIMESTAMP));
    }

    @Test
    public void setUpdatedTimestamp() {
        final ClaimStatusHistory claimStatusHistory = new ClaimStatusHistory();
        claimStatusHistory.setUpdatedTimestamp(UPDATED_TIMESTAMP);
        assertThat(claimStatusHistory.getUpdatedTimestamp(), is(UPDATED_TIMESTAMP));
    }

    @Test
    public void setBookingStatuses() {
        final ClaimStatusHistory claimStatusHistory = new ClaimStatusHistory();
        claimStatusHistory.setBookingStatuses(BOOKING_STATUSES);
        assertThat(claimStatusHistory.getBookingStatuses(), is(BOOKING_STATUSES));
    }
}
