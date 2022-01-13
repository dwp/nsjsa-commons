package uk.gov.dwp.jsa.adaptors.dto.claim;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ClaimStatisticsTest {

    private static final LocalDateTime OLDEST_CLAIM_OPEN = LocalDateTime.now();
    private static final int CASES_RECEIVED_IN_DAY = 1;
    private static final int HEAD_OF_WORK = 2;
    private static final int CASES_CLEARED_IN_DAY = 3;
    private static final double PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_24_HR = 4;
    private static final double PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_48_HR = 5;
    private static final int TOTAL_NUMBER_OF_CLAIMS_IN_WEEK = 6;
    private static final double PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_24_HR = 7;
    private static final double PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_48_HR = 8;
    private static final int OUTSTANDING_OUTSIDE_24HR = 9;
    private static final int OUTSTANDING_OUTSIDE_48HR = 10;

    @Test
    public void constructorSetsFields() {
        final ClaimStatistics claimStatistics = new ClaimStatistics(
                CASES_RECEIVED_IN_DAY,
                HEAD_OF_WORK,
                OLDEST_CLAIM_OPEN,
                CASES_CLEARED_IN_DAY,
                PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_24_HR,
                PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_48_HR,
                TOTAL_NUMBER_OF_CLAIMS_IN_WEEK,
                PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_24_HR,
                PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_48_HR,
                OUTSTANDING_OUTSIDE_24HR,
                OUTSTANDING_OUTSIDE_48HR);
        assertThat(claimStatistics.getOldestClaimOpen(), is(OLDEST_CLAIM_OPEN));
        assertThat(claimStatistics.getCasesReceivedInDay(), is(CASES_RECEIVED_IN_DAY));
        assertThat(claimStatistics.getCasesClearedInDay(), is(CASES_CLEARED_IN_DAY));
        assertThat(claimStatistics.getPercentageOfClaimsInDayClosedIn24hr(), is(PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_24_HR));
        assertThat(claimStatistics.getPercentageOfClaimsInDayClosedIn48hr(), is(PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_48_HR));
        assertThat(claimStatistics.getTotalNumberOfClaimsInWeek(), is(TOTAL_NUMBER_OF_CLAIMS_IN_WEEK));
        assertThat(claimStatistics.getPercentageOfClaimsInWeekClosedIn24hr(), is(PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_24_HR));
        assertThat(claimStatistics.getPercentageOfClaimsInWeekClosedIn48hr(), is(PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_48_HR));
        assertThat(claimStatistics.getHeadOfWork(), is(HEAD_OF_WORK));
        assertThat(claimStatistics.getCasesOutstandingOutside24hrKpi(), is(OUTSTANDING_OUTSIDE_24HR));
        assertThat(claimStatistics.getCasesOutstandingOutside48hrKpi(), is(OUTSTANDING_OUTSIDE_48HR));
    }

    @Test
    public void defaultConstructorDoesaNotSetFields() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        assertThat(claimStatistics.getOldestClaimOpen(), is(nullValue()));
        assertThat(claimStatistics.getCasesReceivedInDay(), is(0));
        assertThat(claimStatistics.getCasesClearedInDay(), is(0));
        assertThat(claimStatistics.getPercentageOfClaimsInDayClosedIn24hr(), is(0d));
        assertThat(claimStatistics.getPercentageOfClaimsInDayClosedIn48hr(), is(0d));
        assertThat(claimStatistics.getTotalNumberOfClaimsInWeek(), is(0));
        assertThat(claimStatistics.getPercentageOfClaimsInWeekClosedIn24hr(), is(0d));
        assertThat(claimStatistics.getPercentageOfClaimsInWeekClosedIn48hr(), is(0d));
        assertThat(claimStatistics.getHeadOfWork(), is(0));
    }

    @Test
    public void setsOldestClaimOpen() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setOldestClaimOpen(OLDEST_CLAIM_OPEN);
        assertThat(claimStatistics.getOldestClaimOpen(), is(OLDEST_CLAIM_OPEN));
    }

    @Test
    public void setsTotalNumberOfClaimsInWeekClosedIn48hr() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setPercentageOfClaimsInWeekClosedIn48hr(PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_48_HR);
        assertThat(claimStatistics.getPercentageOfClaimsInWeekClosedIn48hr(), is(PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_48_HR));
    }

    @Test
    public void setsTotalNumberOfClaimsInWeekClosedIn24hr() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setPercentageOfClaimsInWeekClosedIn24hr(PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_24_HR);
        assertThat(claimStatistics.getPercentageOfClaimsInWeekClosedIn24hr(), is(PERCENTAGE_OF_CLAIMS_IN_WEEK_CLOSED_IN_24_HR));
    }

    @Test
    public void setsTotalNumberOfClaimsInWeek() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setTotalNumberOfClaimsInWeek(TOTAL_NUMBER_OF_CLAIMS_IN_WEEK);
        assertThat(claimStatistics.getTotalNumberOfClaimsInWeek(), is(TOTAL_NUMBER_OF_CLAIMS_IN_WEEK));
    }

    @Test
    public void setsTotalNumberOfClaimsInDayClosedIn48hr() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setPercentageOfClaimsInDayClosedIn48hr(PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_48_HR);
        assertThat(claimStatistics.getPercentageOfClaimsInDayClosedIn48hr(), is(PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_48_HR));
    }

    @Test
    public void setsTotalNumberOfClaimsInDayClosedIn24hr() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setPercentageOfClaimsInDayClosedIn24hr(PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_24_HR);
        assertThat(claimStatistics.getPercentageOfClaimsInDayClosedIn24hr(), is(PERCENTAGE_OF_CLAIMS_IN_DAY_CLOSED_IN_24_HR));
    }

    @Test
    public void setsTotalNumberOfClaims() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setCasesReceivedInDay(CASES_RECEIVED_IN_DAY);
        assertThat(claimStatistics.getCasesReceivedInDay(), is(CASES_RECEIVED_IN_DAY));
    }

    @Test
    public void setsTotalNumberOfClaimsClosed() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setCasesClearedInDay(CASES_CLEARED_IN_DAY);
        assertThat(claimStatistics.getCasesClearedInDay(), is(CASES_CLEARED_IN_DAY));
    }

    @Test
    public void setsTotalNumberOfClaimsOpen() {
        final ClaimStatistics claimStatistics = new ClaimStatistics();
        claimStatistics.setHeadOfWork(HEAD_OF_WORK);
        assertThat(claimStatistics.getHeadOfWork(), is(HEAD_OF_WORK));
    }


}
