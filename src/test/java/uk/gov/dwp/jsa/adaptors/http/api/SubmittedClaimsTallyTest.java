package uk.gov.dwp.jsa.adaptors.http.api;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SubmittedClaimsTallyTest {


    public static final LocalDate TALLY_DATE = LocalDate.now();
    public static final long ONLINE_CLAIM_COUNT = 1L;
    public static final long ASSISTED_DIGITAL_CLAIM_COUNT = 2L;
    public static final long TOTAL_COUNT = 3L;

    @Test
    public void hasDefaultFieldValues() {
        final SubmittedClaimsTally tally = new SubmittedClaimsTally();
        assertThat(tally.getTallyDate(), is(nullValue()));
        assertThat(tally.getOnlineClaimCount(), is(nullValue()));
        assertThat(tally.getAssistedDigitalClaimCount(), is(nullValue()));
        assertThat(tally.getTotalCount(), is(nullValue()));
    }

    @Test
    public void constuctorSetsFieldValues() {
        final SubmittedClaimsTally tally =
                new SubmittedClaimsTally(
                        TALLY_DATE,
                        ONLINE_CLAIM_COUNT,
                        ASSISTED_DIGITAL_CLAIM_COUNT,
                        TOTAL_COUNT);
        assertThat(tally.getTallyDate(), is(TALLY_DATE));
        assertThat(tally.getOnlineClaimCount(), is(ONLINE_CLAIM_COUNT));
        assertThat(tally.getAssistedDigitalClaimCount(), is(ASSISTED_DIGITAL_CLAIM_COUNT));
        assertThat(tally.getTotalCount(), is(TOTAL_COUNT));
    }

    @Test
    public void setTallyDate() {
        final SubmittedClaimsTally tally = new SubmittedClaimsTally();
        tally.setTallyDate(TALLY_DATE);
        assertThat(tally.getTallyDate(), is(TALLY_DATE));
    }

    @Test
    public void setOnlineClaimCount() {
        final SubmittedClaimsTally tally = new SubmittedClaimsTally();
        tally.setOnlineClaimCount(ONLINE_CLAIM_COUNT);
        assertThat(tally.getOnlineClaimCount(), is(ONLINE_CLAIM_COUNT));
    }

    @Test
    public void setAssistedDigitalClaimCount() {
        final SubmittedClaimsTally tally = new SubmittedClaimsTally();
        tally.setAssistedDigitalClaimCount(ASSISTED_DIGITAL_CLAIM_COUNT);
        assertThat(tally.getAssistedDigitalClaimCount(), is(ASSISTED_DIGITAL_CLAIM_COUNT));
    }

    @Test
    public void setTotalCount() {
        final SubmittedClaimsTally tally = new SubmittedClaimsTally();
        tally.setTotalCount(TOTAL_COUNT);
        assertThat(tally.getTotalCount(), is(TOTAL_COUNT));
    }
}
