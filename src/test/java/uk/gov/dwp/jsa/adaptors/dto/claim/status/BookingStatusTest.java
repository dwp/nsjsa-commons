package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BookingStatusTest {

    public static final BookingStatusType STATUS = BookingStatusType.NEW_CLAIM;
    public static final String SUB_STATUS = "SUB_STATUS";
    public static final String AGENT = "AGENT";
    public static final String JOB_CENTRE_CODE = "JOB_CENTRE_CODE";

    @Test
    public void hasDefaultFieldValues() {
        final BookingStatus bookingStatus = new BookingStatus();
        assertThat(bookingStatus.getStatus(), is(nullValue()));
        assertThat(bookingStatus.getSubstatus(), is(nullValue()));
        assertThat(bookingStatus.getAgent(), is(nullValue()));
        assertThat(bookingStatus.getJobCentreCode(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final BookingStatus bookingStatus = new BookingStatus(STATUS, SUB_STATUS, AGENT, JOB_CENTRE_CODE);
        assertThat(bookingStatus.getStatus(), is(STATUS));
        assertThat(bookingStatus.getSubstatus(), is(SUB_STATUS));
        assertThat(bookingStatus.getAgent(), is(AGENT));
        assertThat(bookingStatus.getJobCentreCode(), is(JOB_CENTRE_CODE));
    }

    @Test
    public void setStatus() {
        final BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setStatus(STATUS);
        assertThat(bookingStatus.getStatus(), is(STATUS));
    }

    @Test
    public void setSubstatus() {
        final BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setSubstatus(SUB_STATUS);
        assertThat(bookingStatus.getSubstatus(), is(SUB_STATUS));
    }

    @Test
    public void setAgent() {
        final BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setAgent(AGENT);
        assertThat(bookingStatus.getAgent(), is(AGENT));
    }

    @Test
    public void setJobCentreCode() {
        final BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setJobCentreCode(JOB_CENTRE_CODE);
        assertThat(bookingStatus.getJobCentreCode(), is(JOB_CENTRE_CODE));
    }
}
