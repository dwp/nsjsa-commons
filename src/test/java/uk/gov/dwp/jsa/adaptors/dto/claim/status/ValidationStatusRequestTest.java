package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ValidationStatusRequestTest {

    public static final PushStatus PUSH_STATUS = new PushStatus();
    public static final BookingStatus BOOKING_STATUS = new BookingStatus();

    @Test
    public void constuctorSetsFieldValues() {
        final ValidationStatusRequest request = new ValidationStatusRequest(BOOKING_STATUS, PUSH_STATUS);
        assertThat(request.getBookingStatus(), is(BOOKING_STATUS));
        assertThat(request.getPushStatus(), is(PUSH_STATUS));
    }

    @Test
    public void setBookingStatus() {
        final ValidationStatusRequest request = new ValidationStatusRequest(null, null);
        request.setBookingStatus(BOOKING_STATUS);
        assertThat(request.getBookingStatus(), is(BOOKING_STATUS));

    }

    @Test
    public void setPushStatus() {
        final ValidationStatusRequest request = new ValidationStatusRequest(null, null);
        request.setPushStatus(PUSH_STATUS);
        assertThat(request.getPushStatus(), is(PUSH_STATUS));
    }
}
