package uk.gov.dwp.jsa.adaptors.http.api;

import java.time.LocalDate;

public class SubmittedClaimsTally {
    private LocalDate tallyDate;
    private Long onlineClaimCount;
    private Long assistedDigitalClaimCount;
    private Long totalCount;

    public SubmittedClaimsTally() {
    }

    public SubmittedClaimsTally(final LocalDate tallyDate, final Long onlineClaimCount,
                                final Long assistedDigitalClaimCount, final Long totalCount) {
        this.tallyDate = tallyDate;
        this.onlineClaimCount = onlineClaimCount;
        this.assistedDigitalClaimCount = assistedDigitalClaimCount;
        this.totalCount = totalCount;
    }

    public LocalDate getTallyDate() {
        return tallyDate;
    }

    public Long getOnlineClaimCount() {
        return onlineClaimCount;
    }

    public Long getAssistedDigitalClaimCount() {
        return assistedDigitalClaimCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTallyDate(final LocalDate tallyDate) {
        this.tallyDate = tallyDate;
    }

    public void setOnlineClaimCount(final Long onlineClaimCount) {
        this.onlineClaimCount = onlineClaimCount;
    }

    public void setAssistedDigitalClaimCount(final Long assistedDigitalClaimCount) {
        this.assistedDigitalClaimCount = assistedDigitalClaimCount;
    }

    public void setTotalCount(final Long totalCount) {
        this.totalCount = totalCount;
    }
}
