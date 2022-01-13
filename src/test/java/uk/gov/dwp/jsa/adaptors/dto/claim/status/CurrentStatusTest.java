package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CurrentStatusTest {

    public static final BookingStatus BOOKING_STATUS = new BookingStatus();
    public static final LocalDateTime UPDATED_TIMESTAMP = LocalDateTime.now();
    public static final PushStatus PUSH_STATUS = new PushStatus();
    public static final LocalDateTime CREATED_TIMESTAMP = LocalDateTime.now();

    @Test
    public void hasDefaultFieldValues() {
        final CurrentStatus currentStatus = new CurrentStatus();
        assertThat(currentStatus.getBookingStatus(), is(notNullValue()));
        assertThat(currentStatus.getUpdatedTimestamp(), is(nullValue()));
        assertThat(currentStatus.getPushStatus(), is(notNullValue()));
        assertThat(currentStatus.getCreatedTimestamp(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final CurrentStatus currentStatus = new CurrentStatus(BOOKING_STATUS, PUSH_STATUS, CREATED_TIMESTAMP);
        assertThat(currentStatus.getBookingStatus(), is(BOOKING_STATUS));
        assertThat(currentStatus.getUpdatedTimestamp(), is(CREATED_TIMESTAMP));
        assertThat(currentStatus.getPushStatus(), is(PUSH_STATUS));
        assertThat(currentStatus.getCreatedTimestamp(), is(CREATED_TIMESTAMP));
    }


    @Test
    public void setBookingStatus() {
        final CurrentStatus currentStatus = new CurrentStatus();
        currentStatus.setBookingStatus(BOOKING_STATUS);
        assertThat(currentStatus.getBookingStatus(), is(BOOKING_STATUS));
    }

    @Test
    public void setUpdatedTimestamp() {
        final CurrentStatus currentStatus = new CurrentStatus();
        currentStatus.setUpdatedTimestamp(UPDATED_TIMESTAMP);
        assertThat(currentStatus.getUpdatedTimestamp(), is(UPDATED_TIMESTAMP));
    }

    @Test
    public void setPushStatus() {
        final CurrentStatus currentStatus = new CurrentStatus();
        currentStatus.setPushStatus(PUSH_STATUS);
        assertThat(currentStatus.getPushStatus(), is(PUSH_STATUS));
    }

    @Test
    public void setCreatedTimestamp() {
        final CurrentStatus currentStatus = new CurrentStatus();
        currentStatus.setCreatedTimestamp(CREATED_TIMESTAMP);
        assertThat(currentStatus.getCreatedTimestamp(), is(CREATED_TIMESTAMP));
    }
}
