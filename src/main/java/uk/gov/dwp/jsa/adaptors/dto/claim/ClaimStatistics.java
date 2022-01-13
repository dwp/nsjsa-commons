package uk.gov.dwp.jsa.adaptors.dto.claim;

import java.time.LocalDateTime;

public class ClaimStatistics {

    private int casesReceivedInDay;
    private int headOfWork;
    private LocalDateTime oldestClaimOpen;
    private int casesClearedInDay;
    private double percentageOfClaimsInDayClosedIn24hr;
    private double percentageOfClaimsInDayClosedIn48hr;
    private int totalNumberOfClaimsInWeek;
    private double percentageOfClaimsInWeekClosedIn24hr;
    private double percentageOfClaimsInWeekClosedIn48hr;
    private int casesOutstandingOutside24hrKpi;
    private int casesOutstandingOutside48hrKpi;

    public ClaimStatistics() { }

    public ClaimStatistics(
            final int casesReceivedInDay,
            final int headOfWork,
            final LocalDateTime oldestClaimOpen,
            final int casesClearedInDay,
            final double percentageOfClaimsInDayClosedIn24hr,
            final double percentageOfClaimsInDayClosedIn48hr,
            final int totalNumberOfClaimsInWeek,
            final double percentageOfClaimsInWeekClosedIn24hr,
            final double percentageOfClaimsInWeekClosedIn48hr,
            final int casesOutstandingOutside24hrKpi,
            final int casesOutstandingOutside48hrKpi) {
        this.casesReceivedInDay = casesReceivedInDay;
        this.headOfWork = headOfWork;
        this.oldestClaimOpen = oldestClaimOpen;
        this.casesClearedInDay = casesClearedInDay;
        this.percentageOfClaimsInDayClosedIn24hr = percentageOfClaimsInDayClosedIn24hr;
        this.percentageOfClaimsInDayClosedIn48hr = percentageOfClaimsInDayClosedIn48hr;
        this.totalNumberOfClaimsInWeek = totalNumberOfClaimsInWeek;
        this.percentageOfClaimsInWeekClosedIn24hr = percentageOfClaimsInWeekClosedIn24hr;
        this.percentageOfClaimsInWeekClosedIn48hr = percentageOfClaimsInWeekClosedIn48hr;
        this.casesOutstandingOutside24hrKpi = casesOutstandingOutside24hrKpi;
        this.casesOutstandingOutside48hrKpi = casesOutstandingOutside48hrKpi;
    }

    public int getCasesReceivedInDay() {
        return casesReceivedInDay;
    }

    public void setCasesReceivedInDay(final int casesReceivedInDay) {
        this.casesReceivedInDay = casesReceivedInDay;
    }

    public int getHeadOfWork() {
        return headOfWork;
    }

    public void setHeadOfWork(final int headOfWork) {
        this.headOfWork = headOfWork;
    }

    public LocalDateTime getOldestClaimOpen() {
        return oldestClaimOpen;
    }

    public void setOldestClaimOpen(final LocalDateTime oldestClaimOpen) {
        this.oldestClaimOpen = oldestClaimOpen;
    }

    public int getCasesClearedInDay() {
        return casesClearedInDay;
    }

    public void setCasesClearedInDay(final int casesClearedInDay) {
        this.casesClearedInDay = casesClearedInDay;
    }

    public double getPercentageOfClaimsInDayClosedIn24hr() {
        return percentageOfClaimsInDayClosedIn24hr;
    }

    public void setPercentageOfClaimsInDayClosedIn24hr(final double percentageOfClaimsInDayClosedIn24hr) {
        this.percentageOfClaimsInDayClosedIn24hr = percentageOfClaimsInDayClosedIn24hr;
    }

    public double getPercentageOfClaimsInDayClosedIn48hr() {
        return percentageOfClaimsInDayClosedIn48hr;
    }

    public void setPercentageOfClaimsInDayClosedIn48hr(final double percentageOfClaimsInDayClosedIn48hr) {
        this.percentageOfClaimsInDayClosedIn48hr = percentageOfClaimsInDayClosedIn48hr;
    }

    public int getTotalNumberOfClaimsInWeek() {
        return totalNumberOfClaimsInWeek;
    }

    public void setTotalNumberOfClaimsInWeek(final int totalNumberOfClaimsInWeek) {
        this.totalNumberOfClaimsInWeek = totalNumberOfClaimsInWeek;
    }

    public double getPercentageOfClaimsInWeekClosedIn24hr() {
        return percentageOfClaimsInWeekClosedIn24hr;
    }

    public void setPercentageOfClaimsInWeekClosedIn24hr(final double percentageOfClaimsInWeekClosedIn24hr) {
        this.percentageOfClaimsInWeekClosedIn24hr = percentageOfClaimsInWeekClosedIn24hr;
    }

    public double getPercentageOfClaimsInWeekClosedIn48hr() {
        return percentageOfClaimsInWeekClosedIn48hr;
    }

    public void setPercentageOfClaimsInWeekClosedIn48hr(final double percentageOfClaimsInWeekClosedIn48hr) {
        this.percentageOfClaimsInWeekClosedIn48hr = percentageOfClaimsInWeekClosedIn48hr;
    }

    public int getCasesOutstandingOutside24hrKpi() {
        return casesOutstandingOutside24hrKpi;
    }

    public void setCasesOutstandingOutside24hrKpi(final int casesOutstandingOutside24hrKpi) {
        this.casesOutstandingOutside24hrKpi = casesOutstandingOutside24hrKpi;
    }

    public int getCasesOutstandingOutside48hrKpi() {
        return casesOutstandingOutside48hrKpi;
    }

    public void setCasesOutstandingOutside48hrKpi(final int casesOutstandingOutside48hrKpi) {
        this.casesOutstandingOutside48hrKpi = casesOutstandingOutside48hrKpi;
    }
}
