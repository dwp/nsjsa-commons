package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class StatusTest {

    public static final UUID ID = UUID.randomUUID();
    public static final BookingStatusType BOOKING_STATUS_TYPE = BookingStatusType.NEW_CLAIM;
    public static final String SUB_STATUS = "SUB_STATUS";
    public static final String JOB_CENTRE_CODE = "JOB_CENTRE_CODE";
    public static final String AGENT = "AGENT";
    public static final LocalDateTime CREATED_TIMESTAMP = LocalDateTime.now();
    private static final String AGENT_DEFAUT = "Agent";

    @Test
    public void hasDefautValues() {
        final Status status = new Status();
        assertThat(status.getId(), is(nullValue()));
        assertThat(status.getStatusType(), is(nullValue()));
        assertThat(status.getSubstatus(), is(nullValue()));
        assertThat(status.getJobCentreCode(), is(nullValue()));
        assertThat(status.getAgent(), is(AGENT_DEFAUT));
        assertThat(status.getCreatedTimestamp(), is(nullValue()));
    }

    @Test
    public void setId() {
        final Status status = new Status();
        status.setId(ID);
        assertThat(status.getId(), is(ID));
    }

    @Test
    public void setStatusType() {
        final Status status = new Status();
        status.setStatusType(BOOKING_STATUS_TYPE);
        assertThat(status.getStatusType(), is(BOOKING_STATUS_TYPE));
    }

    @Test
    public void setSubstatus() {
        final Status status = new Status();
        status.setSubstatus(SUB_STATUS);
        assertThat(status.getSubstatus(), is(SUB_STATUS));
    }

    @Test
    public void setJobCentreCode() {
        final Status status = new Status();
        status.setJobCentreCode(JOB_CENTRE_CODE);
        assertThat(status.getJobCentreCode(), is(JOB_CENTRE_CODE));
    }

    @Test
    public void setAgent() {
        final Status status = new Status();
        status.setAgent(AGENT);
        assertThat(status.getAgent(), is(AGENT));
    }

    @Test
    public void setCreatedTimestamp() {
        final Status status = new Status();
        status.setCreatedTimestamp(CREATED_TIMESTAMP);
        assertThat(status.getCreatedTimestamp(), is(CREATED_TIMESTAMP));
    }
}
