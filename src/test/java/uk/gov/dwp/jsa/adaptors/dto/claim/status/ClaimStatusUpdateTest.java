package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ClaimStatusUpdateTest {

    public static final BookingStatus BOOKING_STATUS = new BookingStatus();

    @Test
    public void hasDefaultFieldValues() {
        final ClaimStatusUpdate update = new ClaimStatusUpdate();
        assertThat(update.getBookingStatus(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final ClaimStatusUpdate update = new ClaimStatusUpdate(BOOKING_STATUS);
        assertThat(update.getBookingStatus(), is(BOOKING_STATUS));
    }

    @Test
    public void setBookingStatus() {
        final ClaimStatusUpdate update = new ClaimStatusUpdate();
        update.setBookingStatus(BOOKING_STATUS);
        assertThat(update.getBookingStatus(), is(BOOKING_STATUS));
    }

}
