package uk.gov.dwp.jsa.adaptors.dto.claim.status;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PushStatusTest {

    public static final PushStatusType STATUS = PushStatusType.CANCELLED;
    public static final String SUB_STATUS = "SUB_STATUS";

    @Test
    public void hasDefaultFieldValues() {
        final PushStatus pushStatus = new PushStatus();
        assertThat(pushStatus.getStatus(), is(nullValue()));
        assertThat(pushStatus.getSubstatus(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final PushStatus pushStatus = new PushStatus(STATUS, SUB_STATUS);
        assertThat(pushStatus.getStatus(), is(STATUS));
        assertThat(pushStatus.getSubstatus(), is(SUB_STATUS));
    }

    @Test
    public void setStatus() {
        final PushStatus pushStatus = new PushStatus();
        pushStatus.setStatus(STATUS);
        assertThat(pushStatus.getStatus(), is(STATUS));
    }

    @Test
    public void setSubstatus() {
        final PushStatus pushStatus = new PushStatus();
        pushStatus.setSubstatus(SUB_STATUS);
        assertThat(pushStatus.getSubstatus(), is(SUB_STATUS));
    }
}
